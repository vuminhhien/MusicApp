package com.minhien.musicappasm.model;

public class MessageModel {
    private String data;
    private Boolean fromMe;

    public MessageModel() {
    }

    public MessageModel(String data, Boolean fromMe) {
        this.data = data;
        this.fromMe = fromMe;
    }

    public Boolean getFromMe() {
        return fromMe;
    }

    public void setFromMe(Boolean fromMe) {
        this.fromMe = fromMe;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
