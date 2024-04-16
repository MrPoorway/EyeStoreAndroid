package com.example.eyewearshopandroid;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eyewearshopandroid.Adapter.CartAdapter;
import com.example.eyewearshopandroid.Adapter.GlassesAdapter;
import com.example.eyewearshopandroid.Model.Cart;
import com.example.eyewearshopandroid.Model.Glasses;
import com.example.eyewearshopandroid.Services.GlassesService;
import com.example.eyewearshopandroid.Services.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public Cart cart;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cart = new Cart();

        ListView glassesListView = findViewById(R.id.glassesListView);

        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        GlassesService glassesApi = retrofitClient.getGlassesService();

        Call<List<Glasses>> glassesCall = glassesApi.getAllGlasses();

        Button buttonCart = findViewById(R.id.buttonCart);
        buttonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        glassesCall.enqueue(new Callback<List<Glasses>>() {
            @Override
            public void onResponse(Call<List<Glasses>> call, Response<List<Glasses>> response) {
                if (response.isSuccessful()) {
                    List<Glasses> glassesList = response.body();
                    GlassesAdapter glassesAdapter = new GlassesAdapter(MainActivity.this, glassesList, cart);
                    glassesListView.setAdapter(glassesAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Glasses>> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Failed to fetch glasses", Toast.LENGTH_SHORT).show();
            }
        });
    }
}