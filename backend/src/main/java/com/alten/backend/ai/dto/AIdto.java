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
<<<<<<< HEAD
    public void setCategory(String category) {
        this.category = category;
    }
=======
>>>>>>> feature/frontend-coreui

    public String getPriority() {
        return priority;
    }
<<<<<<< HEAD
    public void setPriority(String priority) {
        this.priority = priority;
    }
=======
>>>>>>> feature/frontend-coreui

    public String getSummary() {
        return summary;
    }
<<<<<<< HEAD
    public void setSummary(String summary) {
        this.summary = summary;
    }
=======
>>>>>>> feature/frontend-coreui
}
