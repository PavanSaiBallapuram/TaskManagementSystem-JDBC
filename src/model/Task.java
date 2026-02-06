package model;

import java.sql.Date;

public class Task {
    private int id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private Date dueDate;
    private int projectId;

    public Task() {}

    public Task(int id, String title, String description, String status, String priority, Date dueDate, int projectId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
        this.projectId = projectId;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public String getPriority() { return priority; }
    public Date getDueDate() { return dueDate; }
    public int getProjectId() { return projectId; }

    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(String status) { this.status = status; }
    public void setPriority(String priority) { this.priority = priority; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
    public void setProjectId(int projectId) { this.projectId = projectId; }
}
