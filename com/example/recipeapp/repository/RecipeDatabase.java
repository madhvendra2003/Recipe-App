package com.example.recipeapp.repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Recipe.class},version = 1)
public abstract class RecipeDatabase extends RoomDatabase {
    public abstract recipeDAO recipeDAO();
}
