package com.example.recipeapp.repository;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipe")
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    int uid;

    @NonNull
    @ColumnInfo(name = "img")
    String img;

    @NonNull
    @ColumnInfo(name = "tittle")  // Keep the typo as it matches the database
    String tittle;

    @NonNull
    @ColumnInfo(name = "des")
    String des;

    @NonNull
    @ColumnInfo(name = "ing")
    String ing;

    @NonNull
    @ColumnInfo(name = "category")
    String category;

    // Constructor
    public Recipe(@NonNull String img, @NonNull String tittle, @NonNull String des, @NonNull String ing, @NonNull String category) {
        this.img = img;
        this.tittle = tittle;
        this.des = des;
        this.ing = ing;
        this.category = category;
    }





    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String title) {
        this.tittle = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getIng() {
        return ing;
    }

    public void setIng(String ing) {
        this.ing = ing;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
