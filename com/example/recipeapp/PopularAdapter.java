package com.example.recipeapp;

import static androidx.core.content.ContextCompat.startActivities;
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;

import com.bumptech.glide.Glide;
import com.example.recipeapp.repository.Recipe;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.viewholder>{

    ArrayList<Recipe> recipes;
    Context ctx;

    public PopularAdapter(ArrayList<Recipe> recipes, Context ctx) {
        this.recipes = recipes;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_popular,parent,false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        String[] time = recipes.get(position).getIng().split("\n");
        String tofill = time[0];
        holder.text_item_time.setText(tofill);

        holder.text_item.setText(recipes.get(position).getTittle());

        Glide.with(ctx)
                .load(recipes.get(position).getImg())
                .centerCrop()
                .into(holder.imgView);

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

    public static class viewholder extends RecyclerView.ViewHolder{

        public ImageView imgView;
        public TextView  text_item;
        public TextView  text_item_time;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            imgView = (ImageView) itemView.findViewById(R.id.popular_image);
            text_item = (TextView) itemView.findViewById(R.id.popular_txt);
            text_item_time = (TextView) itemView.findViewById(R.id.popular_time);
        }
    }
}
