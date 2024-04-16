package com.example.eyewearshopandroid.Adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eyewearshopandroid.Model.Cart;
import com.example.eyewearshopandroid.Model.Glasses;
import com.example.eyewearshopandroid.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GlassesAdapter extends ArrayAdapter<Glasses> {

    private Cart cart;

    public GlassesAdapter(Context context, List<Glasses> glassesList, Cart cart) {
        super(context, 0, glassesList);
        this.cart = cart;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Glasses currentGlasses = getItem(position);

        TextView glassesNameTextView = listItemView.findViewById(R.id.textViewGlassesName);
        TextView typeTextView = listItemView.findViewById(R.id.textViewType);
        TextView quantityTextView = listItemView.findViewById(R.id.textViewQuantity);
        TextView priceTextView = listItemView.findViewById(R.id.textViewPrice);
        ImageView imageView = listItemView.findViewById(R.id.imageUri);
        Button addToCartButton = listItemView.findViewById(R.id.addToCartButton);

        glassesNameTextView.setText(currentGlasses.getName());
        typeTextView.setText("Type: " + currentGlasses.getType());
        quantityTextView.setText("Quantity: " + currentGlasses.getQuantity());
        priceTextView.setText("Price: " + currentGlasses.getPrice() + "đ");

        String imageUrl = currentGlasses.getImageUri();
        if (imageUrl != null) {
            Picasso.get().load(imageUrl).into(imageView);
        }

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cart.addItem(currentGlasses);
                Toast.makeText(getContext(), "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });

        return listItemView;
    }
}