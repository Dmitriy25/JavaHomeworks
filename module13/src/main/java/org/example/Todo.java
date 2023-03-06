package org.example;

public class Todo {

    private int userId;
    private int Id;
    private String title;
    private boolean completed;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "\nTodos{" +
                "\n   \"userId\": " + userId +
                "\n   \"id\": " + Id +
                "\n   \"title\": " + title +
                "\n   \"completed\": " + completed +
                "\n}";
    }
}
