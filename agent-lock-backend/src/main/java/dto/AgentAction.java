package dto;


public class AgentAction {
    private String commandId;
    private String agentName;
    private String command;       // e.g., "rm -rf /", "git commit", "read file"
    private String targetFile;    // e.g., "src/main/resources/schema.sql"
    private int riskScore;        // Calculated 0-100
    private String status;       // "PENDING", "APPROVED_AUTO", "PAUSED", "REJECTED"
    private String reasoning;    // Agent's rationale

    public AgentAction() {}

    public AgentAction(String commandId, String agentName, String command, String targetFile, String reasoning) {
        this.commandId = commandId;
        this.agentName = agentName;
        this.command = command;
        this.targetFile = targetFile;
        this.reasoning = reasoning;
        this.status = "PENDING";
    }

    // Getters and Setters
    public String getCommandId() { return commandId; }
    public void setCommandId(String commandId) { this.commandId = commandId; }

    public String getAgentName() { return agentName; }
    public void setAgentName(String agentName) { this.agentName = agentName; }

    public String getCommand() { return command; }
    public void setCommand(String command) { this.command = command; }

    public String getTargetFile() { return targetFile; }
    public void setTargetFile(String targetFile) { this.targetFile = targetFile; }

    public int getRiskScore() { return riskScore; }
    public void setRiskScore(int riskScore) { this.riskScore = riskScore; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getReasoning() { return reasoning; }
    public void setReasoning(String reasoning) { this.reasoning = reasoning; }
}