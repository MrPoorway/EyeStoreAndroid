package com.example.eyewearshopandroid;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

    private ListView glassesListView;
    private GlassesAdapter glassesAdapter;
    private Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cart = Cart.getInstance();

        glassesListView = findViewById(R.id.glassesListView);

        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        GlassesService glassesApi = retrofitClient.getGlassesService();

        Call<List<Glasses>> glassesCall = glassesApi.getAllGlasses();

        glassesCall.enqueue(new Callback<List<Glasses>>() {
            @Override
            public void onResponse(Call<List<Glasses>> call, Response<List<Glasses>> response) {
                if (response.isSuccessful()) {
                    List<Glasses> glassesList = response.body();
                    glassesAdapter = new GlassesAdapter(MainActivity.this, glassesList, cart);
                    glassesListView.setAdapter(glassesAdapter);

                    // Register context menu for long click on list items
                    registerForContextMenu(glassesListView);
                }
            }

            @Override
            public void onFailure(Call<List<Glasses>> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Failed to fetch glasses", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Glasses glasses = glassesAdapter.getItem(info.position);

        int itemId = item.getItemId();
        if (itemId == R.id.action_add_to_cart) {
            cart.addItem(glasses);
            Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.action_view_cart) {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        }

        return super.onContextItemSelected(item);
    }
}