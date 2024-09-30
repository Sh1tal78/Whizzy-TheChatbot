package com.example.chatbotapplication;

public class MessageModel {

    String  message, viewType;

    public MessageModel(String message, String viewType) {
        this.message = message;
        this.viewType = viewType;
    }
    public MessageModel(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }
}
