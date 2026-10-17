package service;

import enums.RateLimitType;
import enums.UserTier;
import factory.RateLimiterFactory;
import limiter.RateLimiter;
import model.RateLimitConfig;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class RateLimiterService {
    private final Map<UserTier, RateLimiter> rateLimiters = new HashMap<>();

    public RateLimiterService() {
        // Configure per-tier limits + algorithms
        rateLimiters.put(
                UserTier.FREE,
                RateLimiterFactory.createRateLimiter(
                        RateLimitType.TOKEN_BUCKET,
                        new RateLimitConfig(10, 60) // 10 req/min
                )
        );

        rateLimiters.put(
                UserTier.PREMIUM,
                RateLimiterFactory.createRateLimiter(
                        RateLimitType.FIXED_WINDOW,
                        new RateLimitConfig(100, 60) // 100 req/min
                )
        );
    }

    public boolean allowRequest(User user) {
        RateLimiter limiter = rateLimiters.get(user.getTier());
        if (limiter == null) {
            throw new IllegalArgumentException("No limiter configured for tier: " + user.getTier());
        }
        return limiter.allowRequest(user.getUserId());
    }
}