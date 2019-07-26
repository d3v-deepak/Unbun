package com.example.lenovo.any;

public class Notification {
    private  String id;
    private int priorityofdoc;
    private String location;
    private String text;
public Notification(){}

    public Notification(String id, int priorityofdoc,String location,String text){
this.id=id;
this.priorityofdoc=priorityofdoc;
this.location=location;
this.text=text;
    }

    public String getLocation() {
        return location;
    }

    public String getId() {
        return id;
    }

    public int getPriorityofdoc() {
        return priorityofdoc;
    }

    public String getText() {
        return text;
    }
}
