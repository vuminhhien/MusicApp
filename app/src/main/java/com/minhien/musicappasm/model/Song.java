package com.minhien.musicappasm.model;

import java.io.Serializable;

public class Song {
    private int id_song;
    private String song_name;
    private String singer_name;
    private String image_url;
    private int category_id;

    public Song() {
    }

    public Song(int id_song, String song_name, String singer_name, String image_url, int category_id) {
        this.id_song = id_song;
        this.song_name = song_name;
        this.singer_name = singer_name;
        this.image_url = image_url;
        this.category_id = category_id;
    }
    public Song(Integer id_song, Integer category_id, String singer_name, String song_name, String image_url) {
        this.id_song = id_song;
        this.category_id = category_id;
        this.singer_name = singer_name;
        this.song_name = song_name;
        this.image_url = image_url;
    }

    public int getId_Song() {
        return id_song;
    }

    public void setId_Song(int id) {
        this.id_song = id;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getSinger_name() {
        return singer_name;
    }

    public void setSinger_name(String singer_name) {
        this.singer_name = singer_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
