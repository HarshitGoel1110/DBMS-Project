package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.CreateResume;
import com.example.myapplication.InterviewQuestion;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerMainPageClassType;
import com.example.myapplication.ShowResume;

import java.util.ArrayList;

public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.MainViewHolder>{

    ArrayList<RecyclerMainPageClassType> mainAdapter;
    Context context;

    public MainPageAdapter(ArrayList<RecyclerMainPageClassType> a , Context context){
        this.mainAdapter = a;
        this.context = context;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_choosing , parent ,false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.name.setText(mainAdapter.get(position).getName());
        switch(position){
            case 0: holder.image.setImageResource(R.drawable.ic_note_add_black_24dp);
                    break;
            case 1: holder.image.setImageResource(R.drawable.ic_search_black_24dp);
                    break;
            case 2: holder.image.setImageResource(R.drawable.ic_record_voice_over_black_24dp);
        }
    }

    @Override
    public int getItemCount() {
        return mainAdapter.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView image;

        public MainViewHolder(@NonNull final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textHeadingView);
            image = itemView.findViewById(R.id.iconHeadingView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moveIt(name.getText().toString());
                }
            });
        }


    }

    private void moveIt(String name) {
        if(name == "Create new Resume")
            context.startActivity(new Intent(context , CreateResume.class));
        else if(name == "Show Resume")
            context.startActivity(new Intent(context , ShowResume.class));
        else
            context.startActivity(new Intent(context , InterviewQuestion.class));

    }

}
