package com.minhien.musicappasm.model;

public class Response2PikModel {
    private String saved;

    public Response2PikModel() {
    }

    public Response2PikModel(String saved) {
        this.saved = saved;
    }

    public String getSaved() {
        return "https://2.pik.vn/" + saved;
    }

    public void setSaved(String saved) {
        this.saved = saved;
    }
}
