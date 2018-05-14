package com.example.asafl.calci;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class birthadapter extends RecyclerView.Adapter<birthadapter.MyViewHolder> {

    private List<BirthDay> birthlist;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, fname, date;

        public MyViewHolder(View view) {
            super(view);
            //name = (TextView) view.findViewById(R.id.name);
           // fname = (TextView) view.findViewById(R.id.fname);
            //date = (TextView) view.findViewById(R.id.date);
        }
    }


    public birthadapter(List<BirthDay> birthlist) {
        this.birthlist = birthlist;
    }

    //

   // @Override
   // public void onBindViewHolder(MyViewHolder holder, int position) {
    //    BirthDay birthDay = birthlist.get(position);
    //    holder.name.setText(birthDay.getName());
     //   holder.fname.setText(birthDay.getFname());
       // holder.date.setText(birthDay.getDate());
    //}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return birthlist.size();
    }
}
