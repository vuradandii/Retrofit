package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.retrofit.adapter.CountryAdapter;
import com.example.retrofit.model.CountryModel;
import com.example.retrofit.model.Result;
import com.example.retrofit.service.GetCountryDataService;
import com.example.retrofit.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<CountryModel> countries;
    RecyclerView recyclerView;
    private CountryAdapter countryAdapter;
    String stateData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetCountries();
    }

    public Object GetCountries() {

        GetCountryDataService getCountryDataService = RetrofitInstance.getService();
        Call<Result> call = getCountryDataService.getResult();

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();

               if(result != null && result.getResult() !=null ){
                   countries = new ArrayList<>();

                   for (CountryModel getCountryDataService : result.getResult()) {
                       Object statesData = getCountryDataService.getStates();
                       ArrayList<String> statesList = new ArrayList<>();

                       if (statesData instanceof ArrayList<?>) {
                           statesList = (ArrayList<String>) statesData;
                       } else if (statesData instanceof String) {
                           statesList.add((String) statesData);
                       } else {
                           statesList.add("No states available");
                       }

                       getCountryDataService.setStates(statesList);
                       countries.add(getCountryDataService);
                   }

                    ViewData();
                }
           }


            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });

        return countries;
    }
    private void ViewData() {

        recyclerView = findViewById(R.id.recyclerview);
        countryAdapter = new CountryAdapter(countries);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(countryAdapter);
    }

}