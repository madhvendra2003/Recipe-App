package com.example.recipeapp;

import static androidx.core.content.ContextCompat.startActivities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

public class search_activity_rv_adapter extends RecyclerView.Adapter<search_activity_rv_adapter.rv_ViewHolder> {

     ArrayList<Recipe>recipes;
     Context ctx;

    public search_activity_rv_adapter(ArrayList<Recipe> recipes, Context ctx) {
        this.recipes = recipes;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public rv_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.rv_item_search_activity,parent,false);

        return new rv_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull rv_ViewHolder holder, int position) {
        String tittle = recipes.get(position).getTittle().toString();

        holder.text_search_item.setText(tittle);

        Glide.with(ctx)
                .load(recipes.get(position).getImg().toString())
                .centerCrop()
                .into(holder.image_search_item);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx , Recipe_Activity.class);
                intent.putExtra("Recipe_img",recipes.get(holder.getAdapterPosition()).getImg());
                intent.putExtra("Recipe_ing",recipes.get(holder.getAdapterPosition()).getIng());
                intent.putExtra("Recipe_title",recipes.get(holder.getAdapterPosition()).getTittle());
                intent.putExtra("Recipe_des",recipes.get(holder.getAdapterPosition()).getDes());



                startActivities(ctx, new Intent[]{intent});
            }
        });

    }




    @Override
    public int getItemCount() {
        return recipes.size();
    }
    @SuppressLint("NotifyDataSetChanged")
    void filterList(ArrayList<Recipe>newrecipes)
    {
        this.recipes = newrecipes;
        notifyDataSetChanged();
    }


    public static  class rv_ViewHolder extends RecyclerView.ViewHolder{

       public  ImageView image_search_item;
       public  TextView  text_search_item;

        public rv_ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_search_item = (ImageView) itemView.findViewById(R.id.imageView_search_view);
            text_search_item  = (TextView) itemView.findViewById(R.id.textView_search_view_item);
        }
    }
}
