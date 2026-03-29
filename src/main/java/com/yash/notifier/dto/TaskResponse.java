package com.yash.notifier.dto;

public class TaskResponse {
    private long id;
    private String title;
    private boolean completed;

    public TaskResponse(boolean completed, long id, String title) {
        this.completed = completed;
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }
}
