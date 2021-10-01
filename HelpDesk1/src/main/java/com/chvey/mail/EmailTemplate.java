package com.chvey.mail;

public enum EmailTemplate {
    NEW_TICKET_FOR_APPROVAL("html/New_ticket_for_approval.html","New ticket for approval"),
    TICKET_WAS_APPROVED("html/Ticket_was_approved.html","Ticket was approved"),
    TICKET_WAS_DECLINED("html/Ticket_was_declined.html","Ticket was declined"),
    TICKET_WAS_CANCELLED_MANAGER("html/Ticket_was_cancelled_manager.html","Ticket was cancelled"),
    TICKET_WAS_CANCELLED_ENGINEER("html/Ticket_was_cancelled_engineer.html","Ticket was cancelled"),
    TICKET_WAS_DONE("html/Ticket_was_done.html","Ticket was done"),
    TICKET_WAS_PROVIDED("html/Ticket_was_provided.html","Ticket was provided");

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
