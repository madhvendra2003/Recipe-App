package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.recipeapp.repository.Recipe;
import com.example.recipeapp.repository.RecipeDatabase;
import com.example.recipeapp.repository.recipeDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearchActivity extends AppCompatActivity {

    EditText search_bar;

    search_activity_rv_adapter rvAdapter;

    RecyclerView rv;

    ImageView back;

    ArrayList<Recipe>recipeArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


      // Setting the recycler view adapter from room database
                  rv = findViewById(R.id.recycler_view_search_bar);
                  rv.setLayoutManager( new LinearLayoutManager(this));


                   RecipeDatabase rb = Room.databaseBuilder(getApplicationContext(), RecipeDatabase.class, "recipe.db")
                    .createFromAsset("recipe.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

                    recipeDAO recipeDAO = rb.recipeDAO();

                    recipeArrayList = (ArrayList<Recipe>) recipeDAO.getAll();

                    rvAdapter = new search_activity_rv_adapter(recipeArrayList, SearchActivity.this);
                    rv.setAdapter(rvAdapter);

      // Setting up search bar for recycler view

          search_bar = findViewById(R.id.search_bar);
          search_bar.requestFocus();
           search_bar.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (s.toString() != null)
                            {
                                filterData(s.toString());
                            }

                        }

                        private void filterData(String filtertext) {
                            ArrayList<Recipe> filter = new ArrayList<>();
                            for(int i=0;i<recipeArrayList.size();i++)
                            {
                                if (recipeArrayList.get(i).getTittle()!=null && recipeArrayList.get(i).getTittle().toLowerCase().contains(filtertext.toLowerCase()))
                                {
                                    filter.add(recipeArrayList.get(i));
                                }
                            }

                            rvAdapter.filterList(filter);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });


       // Setting up back button
            back = findViewById(R.id.back_button_imageview);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });




    }
}