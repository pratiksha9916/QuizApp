package com.example.QuizApp.dao;

public class Answer {


    private int ansId;

    private String name;

    private int qId;


    public int getAnsId() {
        return ansId;
    }

    public void setAnsId(int ansId) {
        this.ansId = ansId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }

    public Answer() {
    }

    @Override
    public String toString() {
        return "Answer{" +
                "ansId=" + ansId +
                ", name='" + name + '\'' +
                ", qId=" + qId +
                '}';
    }
}
