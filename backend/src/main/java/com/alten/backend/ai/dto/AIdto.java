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

    public String getPriority() {
        return priority;
    }

    public String getSummary() {
        return summary;
    }
}
