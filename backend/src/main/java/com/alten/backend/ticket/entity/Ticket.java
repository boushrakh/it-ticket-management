package com.alten.backend.ticket.entity;
 
import com.alten.backend.user.entity.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;
 
@Entity
@Table(name = "tickets")
public class Ticket {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(nullable = false, length = 255)
    private String title;
 
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
 
    @Column(nullable = false, length = 50)
    private String priority;
 
    @Column(nullable = false, length = 50)
    private String status;
 
    @Column(length = 50)
    private String category;
 
    @Column(nullable = false, updatable = false)
    private java.time.LocalDateTime createdAt;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;
 
    protected Ticket() {
    }
 
    public Ticket(String title, String description, String priority, String status, String category, User user) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.category = category;
        this.user = user;
    }
 
    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = java.time.LocalDateTime.now();
        }
    }
 
    public void setCategory(String category) {
        this.category = category;
    }
 
    public String getCategory() {
        return category;
    }
 
    public Long getId() {
        return id;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public String getPriority() {
        return priority;
    }
 
    public void setPriority(String priority) {
        this.priority = priority;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
 
    public java.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }
 
    public User getUser() {
        return user;
    }
 
    public void setUser(User user) {
        this.user = user;
    }
 
}
 
 