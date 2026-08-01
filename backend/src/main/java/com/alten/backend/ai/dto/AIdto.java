package com.alten.backend.ai.dto;

public class AIdto {
    private String category;
    private String priority;
    private String summary;

    public AIdto() {
    }

    public AIdto(String category, String priority, String summary) {
        this.category = category;
        this.priority = priority;
        this.summary = summary;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
}
