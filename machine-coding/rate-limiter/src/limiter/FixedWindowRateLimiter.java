package limiter;

import enums.RateLimitType;
import model.RateLimitConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

//chop time into fixed-size buckets (windows), count requests per bucket, block once you hit the limit.
public class FixedWindowRateLimiter extends RateLimiter {
    private final Map<String, Integer> requestCount = new ConcurrentHashMap<>();//for each user, how many requests they've made in the current window.
    private final Map<String, Long> windowStart = new HashMap<>();//for each user, which window they're currently in.

    public FixedWindowRateLimiter(RateLimitConfig config) {
        super(config, RateLimitType.FIXED_WINDOW);
    }

    @Override
    public boolean allowRequest(String userId) {
        AtomicBoolean allowed = new AtomicBoolean(false);

        //if the window is 60 seconds, every 60-second chunk of time gets its own whole number ID — window 0, window 1, window 2
        long currentReqWindow = System.currentTimeMillis() / 1000 / config.getWindowInSeconds();

        //compute is an atomic "read-modify-write" operation on the map for one key — meaning no other thread can sneak in
        // and change userId's entry while this runs. That's the whole reason requestCount is a ConcurrentHashMap: to make
        // each user's counter update thread-safe.
        requestCount.compute(userId, (id, count) -> {
            long lastReqWindow = windowStart.getOrDefault(id, currentReqWindow);

            if (lastReqWindow != currentReqWindow) {
                // window expired -> reset counter and window of last req
                windowStart.put(id, currentReqWindow);
                allowed.set(true);
                return 1; // first request in new window
            }

            if (count == null) count = 0;

            //Same window as before. Have they used fewer than the max?
            // If yes, let them through and bump the count. If no, deny — and leave the count unchanged.
            if (count < config.getMaxRequests()) {
                allowed.set(true);
                return count + 1; // increment count
            }

            return count; // remain same
        });

        return allowed.get();
    }
}


//If asked "why would you pick Fixed Window over Sliding Window," the honest answer:
// it's simpler and uses less memory (just a counter and a window number per user, vs. Sliding Window Log
// which stores every timestamp). Trade precision for simplicity/performance.