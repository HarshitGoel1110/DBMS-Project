package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DisplayTheResume;
import com.example.myapplication.R;
import com.example.myapplication.RecieveResume;
import java.util.ArrayList;

public class ShowResumeAdapter extends RecyclerView.Adapter<ShowResumeAdapter.ShowResumeViewHolder> {

    ArrayList<RecieveResume> arrayList;
    Context context;

    public ShowResumeAdapter(Context context , ArrayList<RecieveResume> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ShowResumeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.show_resume_card_view , parent , false);
        return new ShowResumeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowResumeViewHolder holder, int position) {
        holder.firstSkill.setText("First Skill :- " + arrayList.get(position).getSkill()[0]);
        holder.secondSkill.setText("Second Skill :- " + arrayList.get(position).getSkill()[1]);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ShowResumeViewHolder extends RecyclerView.ViewHolder{

        TextView firstSkill,secondSkill;

        public ShowResumeViewHolder(@NonNull final View itemView) {
            super(itemView);

            firstSkill = itemView.findViewById(R.id.showResumeTextView1);
            secondSkill = itemView.findViewById(R.id.showResumeTextView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(context, DisplayTheResume.class);
                        intent.putExtra("resume", position);
                        context.startActivity(intent);
                    }
                }
            });


        }
    }

}
