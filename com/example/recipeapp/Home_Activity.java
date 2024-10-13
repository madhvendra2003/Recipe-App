package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.recipeapp.repository.Recipe;
import com.example.recipeapp.repository.RecipeDatabase;
import com.example.recipeapp.repository.recipeDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Home_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    PopularAdapter rv_adapter;
    EditText editText_search;
     ImageView salad , main_dish , drinks, desserts;
     ImageView img;

    ArrayList<Recipe>recipeArrayList = new ArrayList<>();
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.RecyclerViewPopularRecipes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

            RecipeDatabase rb = Room.databaseBuilder(getApplicationContext(), RecipeDatabase.class, "recipe.db")
                    .createFromAsset("recipe.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

            recipeDAO recipeDAO = rb.recipeDAO();
            List<Recipe> list = recipeDAO.getAllByCategory("Popular");
            rv_adapter = new PopularAdapter(new ArrayList<>(list), Home_Activity.this);
            recyclerView.setAdapter(rv_adapter);



        editText_search = findViewById(R.id.editTextText);
        editText_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home_Activity.this , SearchActivity.class));
            }
        });


        salad = findViewById(R.id.salad);
        main_dish = findViewById(R.id.Main_Dish);
        drinks = findViewById(R.id.Drinks);
        desserts = findViewById(R.id.Main_Dessert);


        salad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent to = new Intent(Home_Activity.this, Category_Activity.class);
                to.putExtra("Category_type","Salad");
                startActivity(to);
            }
        });

        main_dish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent to = new Intent(Home_Activity.this, Category_Activity.class);
                to.putExtra("Category_type","Dish");
                startActivity(to);
            }
        });

        desserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent to = new Intent(Home_Activity.this, Category_Activity.class);
                to.putExtra("Category_type","Desserts");
                startActivity(to);
            }
        });

        drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent to = new Intent(Home_Activity.this, Category_Activity.class);
                to.putExtra("Category_type","Drinks");
                startActivity(to);
            }
        });

        img = findViewById(R.id.imageView);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(Home_Activity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.bottom_sheet);

                dialog.show();
                dialog.getWindow().setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT

                );

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setGravity(Gravity.BOTTOM);
            }
        });
    }












}