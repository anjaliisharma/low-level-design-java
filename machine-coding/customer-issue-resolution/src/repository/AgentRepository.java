package repository;

import domain.Agent;
import domain.IssueType;

import java.util.List;
import java.util.Optional;

public interface AgentRepository {
    Agent save(Agent agent);

    Optional<Agent> findById(String agentId);

    Optional<Agent> findByEmail(String email);

    List<Agent> findAll();

    List<Agent> findBySpecializedIssueType(IssueType issueType);

    List<Agent> findAvailableAgents();

    Agent update(Agent agent);

    String getNextAgentId();
}
