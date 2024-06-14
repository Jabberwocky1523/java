package com.bean;

import java.util.ArrayList;
import java.util.List;

public class student {
    private List<lesson> lesson = new ArrayList<>();

    private String name;
    private int id;

    public student() {
    }

    public student(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public student(int id, String name, List<lesson> lesson) {
        this.id = id;
        this.name = name;
        this.lesson = lesson;
    }

    public String getname() {
        return this.name;
    }

    public int getid() {
        return this.id;
    }

    public List<lesson> getLessons() {
        return this.lesson;
    }

    public void setid(int id) {
        this.id = id;
    }

    public void setname(String name) {
        this.name = name;
    }

    public void setlesson(List<lesson> lessons) {
        this.lesson = lessons;
    }
}
