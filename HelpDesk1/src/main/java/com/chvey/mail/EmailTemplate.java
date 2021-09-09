package com.chvey.mail;

public enum EmailTemplate {
    NEW_TICKET_FOR_APPROVAL("thml/New_ticket_for_approval.html","New ticket for approval");

    private  String templateName;
    private String subject;

    EmailTemplate(String templateName, String subject) {
        this.templateName = templateName;
        this.subject = subject;
    }

    public String getTemplateName() {
        return templateName;
    }

    public String getSubject() {
        return subject;
    }
}
