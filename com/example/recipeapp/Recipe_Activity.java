package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Recipe_Activity extends AppCompatActivity {

    ScrollView ing_scrool , step_scrool;
    ImageView fullScreen,back,food_img;
    Boolean imgCrop = true;

    String recipe_img, recipe_title ,recipe_des, recipe_ing;

    TextView title , ing_text,step_text,time;

    Button ing, steps;

    String[] temp;
    Intent get;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        fullScreen = findViewById(R.id.full_screen_button_recipeActivity);
        back  = findViewById(R.id.back_button_recipeActivity);
        food_img = findViewById(R.id.food_image_view_recipe);
        time = findViewById(R.id.time_recipe_activity);
        ing_scrool = findViewById(R.id.ing_scrool);
        step_scrool = findViewById(R.id.step_scrool);




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        get = getIntent();

        recipe_img = get.getStringExtra("Recipe_img");
        recipe_title = get.getStringExtra("Recipe_title");
        recipe_des = get.getStringExtra("Recipe_des");
        recipe_ing = get.getStringExtra("Recipe_ing");

        assert recipe_ing != null;
        temp = recipe_ing.split("\n");

        time.setText(temp[0]);
        ing_text = findViewById(R.id.ing_text);
        step_text = findViewById(R.id.steps_text);


        step_text.setText(recipe_des);

       try{ StringBuilder ingredientText = new StringBuilder(ing_text.getText());

        for (int i = 1; i < temp.length; i++) {
            ingredientText.append("\n🟢 ").append(temp[i]);
        }

        ing_text.setText(ingredientText.toString().trim());}
       catch (Exception e)
       {
           Log.d("error_in_ing", e.toString());
       }



        title = findViewById(R.id.tittle_recipeActivity);

        title.setText(recipe_title);

        Glide.with(Recipe_Activity.this).load(recipe_img).dontTransform().into(food_img);



        fullScreen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (imgCrop)
                {
                    food_img.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    imgCrop = false;
                    fullScreen.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
                }

                else {
                    food_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    fullScreen.setColorFilter(null);
                    imgCrop = true;
                }
            }
        });


        ing  = findViewById(R.id.button_ingredients);
        steps = findViewById(R.id.button_steps);


        steps.setBackground(null);
        steps.setTextColor(getColor(R.color.black));

        steps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                steps.setTextColor(getColor(R.color.white));
                steps.setBackgroundResource(R.drawable.btn_ing);
                ing.setBackground(null);
                ing.setTextColor(getColor(R.color.black));
                step_scrool.setVisibility(View.VISIBLE);
                ing_scrool.setVisibility(View.GONE);

            }
        });
        
        ing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ing.setBackgroundResource(R.drawable.btn_ing);
                ing.setTextColor(getColor(R.color.white));
                steps.setBackground(null);
               steps.setTextColor(getColor(R.color.black));
                ing_scrool.setVisibility(View.VISIBLE);
                step_scrool.setVisibility(View.GONE);
            }
        });













    }
}