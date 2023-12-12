package com.example.retrofit.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.R;
import com.example.retrofit.model.CountryModel;
import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private ArrayList<CountryModel> countriesList;

    public CountryAdapter(ArrayList<CountryModel> countriesList) {
        this.countriesList = countriesList;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item,parent,false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.textView.setText(countriesList.get(position).getName());
        holder.textView1.setText(countriesList.get(position).getCode());
        holder.textView2.setText(countriesList.get(position).getRegion());
        Object statesData = countriesList.get(position).getStates();
        String textToShow = "";

        if (statesData instanceof ArrayList<?>) {
            ArrayList<String> statesList = (ArrayList<String>) statesData;
            textToShow = !statesList.isEmpty() ? TextUtils.join(", ", statesList) : "No states available";
        } else if (statesData instanceof String) {
            textToShow = (String) statesData;
        } else {
            textToShow = "States data format not supported";
        }

        holder.textView3.setText(textToShow);


    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    // View Holder

    class CountryViewHolder extends  RecyclerView.ViewHolder{

        TextView textView,textView1,textView2,textView3;
        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.country_textView);
            textView1 = itemView.findViewById(R.id.code);
            textView2 = itemView.findViewById(R.id.region);
            textView3 = itemView.findViewById(R.id.state);

        }
    }
}
