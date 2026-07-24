package dto;

public class ActionDecision {
    private String commandId;
    private String decision; // Accepts "APPROVE", "APPROVED", "REJECT", "REJECTED_HUMAN"
    private String feedbackPrompt;

    public ActionDecision() {}

    public String getCommandId() { return commandId; }
    public void setCommandId(String commandId) { this.commandId = commandId; }

    public String getDecision() { return decision; }
    public void setDecision(String decision) { this.decision = decision; }

    public String getFeedbackPrompt() { return feedbackPrompt; }
    public void setFeedbackPrompt(String feedbackPrompt) { this.feedbackPrompt = feedbackPrompt; }
}