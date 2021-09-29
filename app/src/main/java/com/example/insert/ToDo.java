package com.example.insert;

public class ToDo {

    private int id;
    private String title,descrpition;
    private long started,finished;

    public ToDo(){};

    public ToDo(int id, String title, String descrpition, long started, long finished) {
        this.id = id;
        this.title = title;
        this.descrpition = descrpition;
        this.started = started;
        this.finished = finished;
    }

    public ToDo(String title, String descrpition, long started, long finished) {
        this.title = title;
        this.descrpition = descrpition;
        this.started = started;
        this.finished = finished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrpition() {
        return descrpition;
    }

    public void setDescrpition(String descrpition) {
        this.descrpition = descrpition;
    }

    public long getStarted() {
        return started;
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public long getFinished() {
        return finished;
    }

    public void setFinished(long finished) {
        this.finished = finished;
    }
}
