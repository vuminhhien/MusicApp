package com.minhien.musicappasm.model;

public class SongCategory {
    int id_category;
    String category_name;

    public SongCategory() {
    }

    public SongCategory(int id_category, String catrgory_name) {
        this.id_category = id_category;
        this.category_name = catrgory_name;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getCatrgory_name() {
        return category_name;
    }

    public void setCatrgory_name(String catrgory_name) {
        this.category_name = catrgory_name;
    }
}
