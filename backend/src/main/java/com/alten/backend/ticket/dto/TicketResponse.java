package com.alten.backend.ticket.dto;

import com.alten.backend.ticket.entity.Ticket;

import java.time.LocalDateTime;

public class TicketResponse {
    private Long id;
    private String title;
    private String description;
    private String priority;
    private String status;
    private String category;
    private String requester;
    private String assignee;
    private LocalDateTime createdAt;
    private String owner;

    public TicketResponse(Ticket ticket) {
        this.id = ticket.getId();
        this.title = ticket.getTitle();
        this.description = ticket.getDescription();
        this.priority = ticket.getPriority();
        this.status = ticket.getStatus();
        this.category = ticket.getCategory();
        this.requester = ticket.getRequester();
        this.assignee = ticket.getAssignee();
        this.createdAt = ticket.getCreatedAt();
        this.owner = ticket.getUser() != null ? ticket.getUser().getUsername() : null;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public String getCategory() {
        return category;
    }

    public String getRequester() {
        return requester;
    }

    public String getAssignee() {
        return assignee;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getOwner() {
        return owner;
    }
}
