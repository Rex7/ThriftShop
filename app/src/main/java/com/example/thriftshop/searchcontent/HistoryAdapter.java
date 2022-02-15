package com.example.thriftshop.searchcontent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thriftshop.R;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.Viewholder> {
    String[] listOfHistory={"jeans","shirt","reebook","glasses"};

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
           holder.historyText.setText(listOfHistory[position]);
    }

    @Override
    public int getItemCount() {
        return listOfHistory.length;
    }

     class Viewholder  extends  RecyclerView.ViewHolder{
        TextView historyText;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            historyText=itemView.findViewById(R.id.title_row);
        }
    }
}
