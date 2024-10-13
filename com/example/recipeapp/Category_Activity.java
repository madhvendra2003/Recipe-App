package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipeapp.repository.Recipe;
import com.example.recipeapp.repository.RecipeDatabase;
import com.example.recipeapp.repository.recipeDAO;

import java.util.ArrayList;

public class Category_Activity extends AppCompatActivity {

    TextView textView;
    RecyclerView recyclerView;
    Category_rv_adapter adapter;

    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Intent get = getIntent();
        String type = get.getStringExtra("Category_type");
        textView = findViewById(R.id.text_Category_category);
        textView.setText(type);

        back  =findViewById(R.id.back_button_category);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
         recyclerView = findViewById(R.id.rv_category);
        try{
            RecipeDatabase rb = Room.databaseBuilder(getApplicationContext(), RecipeDatabase.class, "recipe.db")
                    .createFromAsset("recipe.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

            recipeDAO recipeDAO = rb.recipeDAO();
            ArrayList<Recipe> list = new ArrayList<>();

            list = (ArrayList<Recipe>) recipeDAO.getAllByCategory(type);

            adapter = new Category_rv_adapter(list,getApplicationContext());

            recyclerView.setLayoutManager(new LinearLayoutManager(Category_Activity.this));

            recyclerView.setAdapter(adapter);

        }catch (Exception e){
            Log.d("Search_Activity_error", e.toString());
        }








    }
}