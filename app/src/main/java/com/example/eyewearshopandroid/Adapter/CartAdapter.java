package com.example.eyewearshopandroid.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eyewearshopandroid.Model.Cart;
import com.example.eyewearshopandroid.Model.Glasses;
import com.example.eyewearshopandroid.R;

import java.util.List;

public class CartAdapter extends ArrayAdapter<Glasses> {

    public CartAdapter(Context context, List<Glasses> glassesList) {
        super(context, 0, glassesList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.cart_list_item, parent, false);
        }

        Glasses currentGlasses = getItem(position);

        TextView glassesNameTextView = listItemView.findViewById(R.id.textViewGlassesName);
        TextView typeTextView = listItemView.findViewById(R.id.textViewType);
        TextView quantityTextView = listItemView.findViewById(R.id.textViewQuantity);
        TextView priceTextView = listItemView.findViewById(R.id.textViewPrice);
        Button removeFromCartButton = listItemView.findViewById(R.id.removeFromCartButton);

        glassesNameTextView.setText(currentGlasses.getName());
        typeTextView.setText("Type: " + currentGlasses.getType());
        quantityTextView.setText("Quantity: " + currentGlasses.getQuantity());
        priceTextView.setText("Price: " + currentGlasses.getPrice() + "đ");

        removeFromCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart.getInstance().removeItem(currentGlasses);
                notifyDataSetChanged();
            }
        });

        return listItemView;
    }
}