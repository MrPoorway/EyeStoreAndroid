package com.example.eyewearshopandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.eyewearshopandroid.Entities.Glasses;
import com.example.eyewearshopandroid.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GlassesAdapter extends ArrayAdapter<Glasses> {

    public GlassesAdapter(@NonNull Context context, List<Glasses> glassesList) {
        super(context, 0, glassesList);
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

        glassesNameTextView.setText(currentGlasses.getName());
        typeTextView.setText("Type: " + currentGlasses.getType());
        quantityTextView.setText("Quantity: " + currentGlasses.getQuantity());
        priceTextView.setText("Price: " + currentGlasses.getPrice() + "đ");

        String imageUrl = currentGlasses.getImageUri();
        if (imageUrl != null) {
            Picasso.get().load(imageUrl).into(imageView);
        }

        return listItemView;
    }
}