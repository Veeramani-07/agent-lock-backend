package service;

import Entity.AgentActionEntity;
import Repository.AgentActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentInterceptorService {

    @Autowired
    private AgentActionRepository repository;

    /**
     * Evaluates action risk score, sets status, and saves to H2 Database
     */
    public AgentActionEntity processAndEvaluate(AgentActionEntity action) {
        // High risk actions (> 40) require human-in-the-loop validation
        if (action.getRiskScore() > 40) {
            action.setStatus("PAUSED");
        } else {
            action.setStatus("APPROVED_AUTO");
        }

        // Save directly to H2 Database
        return repository.save(action);
    }

    /**
     * Resolves human decision coming from React Frontend & updates DB record
     */
    public AgentActionEntity resolveAction(String commandId, String decision, String feedbackPrompt) {
        Optional<AgentActionEntity> optionalAction = repository.findById(commandId);

        if (optionalAction.isPresent()) {
            AgentActionEntity action = optionalAction.get();

            // Fixed String Comparison: Accepts both "APPROVED" and "APPROVE"
            String updatedStatus;
            if ("APPROVED".equalsIgnoreCase(decision) || "APPROVE".equalsIgnoreCase(decision)) {
                updatedStatus = "APPROVED_HUMAN";
            } else {
                updatedStatus = "REJECTED_HUMAN";
            }

            action.setStatus(updatedStatus);

            if (feedbackPrompt != null && !feedbackPrompt.trim().isEmpty()) {
                action.setReasoning(action.getReasoning() + " | Supervisor Feedback: " + feedbackPrompt);
            }

            // Update in H2 Database
            return repository.save(action);
        }

        return null;
    }

    /**
     * Retrieves all logs directly from H2 Database
     */
    public List<AgentActionEntity> getAllActions() {
        return repository.findAll();
    }
}