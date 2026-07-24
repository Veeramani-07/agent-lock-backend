package controller;

import dto.ActionDecision;
import Entity.AgentActionEntity;
import service.AgentInterceptorService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agent")
@CrossOrigin(origins = "*") // Allows React Frontend Connection
public class AgentSupervisorController {

    private final AgentInterceptorService interceptorService;
    private final SimpMessagingTemplate messagingTemplate;

    public AgentSupervisorController(
            AgentInterceptorService interceptorService,
            SimpMessagingTemplate messagingTemplate
    ) {
        this.interceptorService = interceptorService;
        this.messagingTemplate = messagingTemplate;
    }

    // Endpoint for Codex Agent to send tool calls
    @PostMapping("/intercept")
    public ResponseEntity<AgentActionEntity> interceptAction(@RequestBody AgentActionEntity action) {
        AgentActionEntity evaluated = interceptorService.processAndEvaluate(action);

        // Broadcast the newly intercepted action to the React UI instantly via WebSocket
        messagingTemplate.convertAndSend("/topic/actions", evaluated);

        return ResponseEntity.ok(evaluated);
    }

    // Endpoint for Human Supervisor (React UI) to approve or override
    @PostMapping("/decision")
    public ResponseEntity<AgentActionEntity> makeDecision(@RequestBody ActionDecision decision) {
        AgentActionEntity resolved = interceptorService.resolveAction(
                decision.getCommandId(),
                decision.getDecision(),
                decision.getFeedbackPrompt()
        );

        if (resolved != null) {
            // Broadcast the resolved action (APPROVED/REJECTED) back to WebSocket so UI syncs
            messagingTemplate.convertAndSend("/topic/actions", resolved);
            return ResponseEntity.ok(resolved);
        }

        return ResponseEntity.notFound().build();
    }

    // Endpoint to get all live streams/logs from H2 DB
    @GetMapping("/actions")
    public ResponseEntity<List<AgentActionEntity>> getAllActions() {
        return ResponseEntity.ok(interceptorService.getAllActions());
    }
}