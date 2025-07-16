package com.example.QuizApp.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Response {
    @Id
    private int id;
    private String response;

    public Response() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Response{" +
                "id=" + id +
                ", response='" + response + '\'' +
                '}';
    }
}
