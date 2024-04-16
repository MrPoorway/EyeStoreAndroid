package com.example.eyewearshopandroid;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eyewearshopandroid.Adapter.GlassesAdapter;
import com.example.eyewearshopandroid.Entities.Glasses;
import com.example.eyewearshopandroid.Services.GlassesService;
import com.example.eyewearshopandroid.Services.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView glassesListView = findViewById(R.id.glassesListView);

        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        GlassesService glassesApi = retrofitClient.getGlassesService();

        Call<List<Glasses>> glassesCall = glassesApi.getAllGlasses();

        glassesCall.enqueue(new Callback<List<Glasses>>() {
            @Override
            public void onResponse(Call<List<Glasses>> call, Response<List<Glasses>> response) {
                if (response.isSuccessful()) {
                    List<Glasses> glassesList = response.body();
                    GlassesAdapter glassesAdapter = new GlassesAdapter(MainActivity.this, glassesList);
                    glassesListView.setAdapter(glassesAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Glasses>> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Faield to fetch glasses", Toast.LENGTH_SHORT).show();
            }
        });
    }
}