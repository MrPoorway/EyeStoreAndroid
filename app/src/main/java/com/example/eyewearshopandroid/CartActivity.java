package com.example.eyewearshopandroid;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eyewearshopandroid.Adapter.CartAdapter;
import com.example.eyewearshopandroid.Model.Cart;

public class CartActivity extends AppCompatActivity {

    private CartAdapter cartAdapter;
    private ListView cartListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartListView = findViewById(R.id.cartListView);
        cartAdapter = new CartAdapter(this, Cart.getInstance().getItems());
        cartListView.setAdapter(cartAdapter);
    }
}