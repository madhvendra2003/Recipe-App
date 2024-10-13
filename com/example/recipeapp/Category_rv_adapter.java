package com.example.recipeapp;

import static androidx.core.content.ContextCompat.startActivities;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recipeapp.repository.Recipe;

import java.util.ArrayList;

public class Category_rv_adapter extends RecyclerView.Adapter<Category_rv_adapter.viewHolder>{

    ArrayList<Recipe>recipes;
    Context ctx;

    public Category_rv_adapter(ArrayList<Recipe> recipes, Context ctx) {
        this.recipes = recipes;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(ctx).inflate(R.layout.category_rv_row_item,parent,false);
       return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Glide.with(ctx).load(recipes.get(position).getImg()).centerCrop().into(holder.img_row_item);
        holder.title.setText(recipes.get(position).getTittle().toString());
        String[] time = recipes.get(position).getIng().split("\n");
        String tofill = time[0];
        holder.time.setText(tofill);

        try{
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, Recipe_Activity.class);
                intent.putExtra("Recipe_img",recipes.get(holder.getAdapterPosition()).getImg());
                intent.putExtra("Recipe_ing",recipes.get(holder.getAdapterPosition()).getIng());
                intent.putExtra("Recipe_title",recipes.get(holder.getAdapterPosition()).getTittle());
                intent.putExtra("Recipe_des",recipes.get(holder.getAdapterPosition()).getDes());
                // Add FLAG_ACTIVITY_NEW_TASK if you're calling startActivity from a non-activity context
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);
            }
        });}
        catch (
                Exception e
        ){
            Log.d("nextButton_error", e.toString());
        }
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        ImageView img_row_item;
        TextView title;
        TextView time;

        ImageView imageView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time_Category_item);
            title = itemView.findViewById(R.id.title_Category_rv_item);
            img_row_item = itemView.findViewById(R.id.img_catergory_row_item);
            imageView = itemView.findViewById(R.id.nextButton_rv_category_item);


        }
    }
}
