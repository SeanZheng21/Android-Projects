package com.example.tp3;


import androidx.annotation.NonNull;

public class Task {
    private String name;
    private int duration;
    private String description;
    private Category category;


    public Task(String name, int duration, String description, Category category) {
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.category = category;
    }

    public Task(String name, int duration, String description, String category) {
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.category = Category.valueOf(category);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }
}

enum Category {
    courses, enfant, lecture, menage, question, sport, travail;

    public Category fromString(String str) {
        switch (str) {
            case "courses":
                return courses;
            case "enfant":
                return enfant;
            case "lecture":
                return lecture;
            case "menage":
                return menage;
            case "sport":
                return sport;
            case "travail":
                return travail;
            default:
                return question;
        }
    }
}