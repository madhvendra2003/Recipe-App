package com.example.recipeapp.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface recipeDAO {

    @Query("SELECT * FROM recipe")
    List<Recipe>getAll();

    @Insert
    void insertRecipe(Recipe recipe);

    @Delete
    void deleteRecipe(Recipe recipe);


    @Query("SELECT * FROM recipe WHERE category = :category")
    List<Recipe> getAllByCategory(String category);

    @Query("SELECT * FROM recipe WHERE uid = :uid")
    Recipe get(int uid);
}
