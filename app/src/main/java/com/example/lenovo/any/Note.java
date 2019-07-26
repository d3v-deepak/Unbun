package com.example.lenovo.any;

public class Note {

    private String title;
    private String description;
    private String answer;
    private String date;
    private int priority;
    private String username;
    private int report_count;


    public Note() {
        //empty constructor needed
    }

    public Note(String title, String description,String answer,String date, int priority,String username,int report_count) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.answer=answer;
        this.date=date;
        this.username=username;
        this.report_count=report_count;

    }

    public String getAnswer() {
        return answer;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public String getUsername() {
        return username;
    }

    public int getReport_count() {
        return report_count;
    }
}
