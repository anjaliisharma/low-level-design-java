package strategy;

import domain.Agent;
import domain.Issue;

import java.util.List;
import java.util.Optional;

public interface IssueAssignmentStrategy {
    Optional<Agent> assignIssue(Issue issue, List<Agent> availableAgents);
}
