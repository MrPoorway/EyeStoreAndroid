//package com.example.eyewearshopandroid;
//
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.PopupMenu;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.eyewearshopandroid.Model.Glasses;
//import com.example.eyewearshopandroid.Services.GlassesService;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class MainActivityOrigin extends AppCompatActivity {
//    private ListView listview;
//    private ArrayList<Glasses> datalist;
//    private EditText editTextGlassesId,editTextGlassesName,editTextGlassesType, editTextGlassesPrice ,editTextGlassesQuantity, editTextImageUri;
//    private Button buttonaddGlasses;
//    private List<String> glassesList;
//    private ArrayAdapter<String> adapter;
//
//    private static final int EDIT_GLASSES_REQUEST_CODE = 1; // Bạn có thể sử dụng bất kỳ giá trị nguyên nào
//
//    public MainActivityOrigin() {
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        listview= findViewById(R.id.glassesListView);
//        editTextGlassesId = findViewById(R.id.editTextGlassesId);
//        editTextGlassesName = findViewById(R.id.editTextGlassesName);
//        editTextGlassesType = findViewById(R.id.editTextGlassesType);
//        editTextGlassesPrice = findViewById(R.id.editTextGlassesPrice);
//        editTextGlassesQuantity = findViewById(R.id.editTextGlassesQuantity);
//        editTextImageUri = findViewById(R.id.editTextImageUri);
//        buttonaddGlasses = findViewById(R.id.buttonaddGlasses);
//        // Register the ListView for a context menu
//        registerForContextMenu(listview);
//        // Set onItemLongClickListener for ListView
//        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                // Show options dialog
//                showOptionsDialog(position);
//                return true;
//            }
//        });
//        //put data list to productListview
//
//        loadData();
//        // add product to database
//        buttonaddGlasses.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int glassesId = Integer.parseInt(editTextGlassesId.getText().toString());
//                String glassesName = editTextGlassesName.getText().toString();
//                String glassesType = editTextGlassesType.getText().toString();
//                double glassesPrice = Double.parseDouble(editTextGlassesPrice.getText().toString());
//                int glassesQuantity = Integer.parseInt(editTextGlassesQuantity.getText().toString());
//                String image = editTextImageUri.getText().toString();
//
//                Glasses newGlasses = new Glasses(glassesId, glassesName, glassesType, glassesPrice, glassesQuantity, image);
//                addGlasses(newGlasses);
//            }
//        });
//        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                PopupMenu popupMenu = new PopupMenu(MainActivityOrigin.this, view);
//                popupMenu.getMenuInflater().inflate(R.menu.menu_context, popupMenu.getMenu());
//                // Lấy sản phẩm được chọn từ danh sách dựa vào vị trí
//                Glasses selectedGlasses = datalist.get(position);
//                int glassesID=selectedGlasses.getId();
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        if (item.getItemId() == R.id.action_update) {
//                            // Xử lý khi nhấn vào update
////                            updateGlasses(glassesID,selectedGlasses);
//                            showUpdateDialog(position);
//                            return true;
//                        } else if (item.getItemId() == R.id.action_delete) {
//                            // Xử lý khi nhấn vào delete
//                            deleteGlassesById(glassesID);
//                            return true;
//                        } else {
//                            return false;
//                        }
//                    }
//                });
//                popupMenu.show();
//                return true;
//            }
//        });
//
//    }
//    // Show options dialog
//    private void showOptionsDialog(final int position) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Select an option")
//                .setItems(new String[]{"Update ", "delete"}, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch (which) {
//                            case 0:
//                                // Update name option
//                                Toast.makeText(MainActivityOrigin.this, "Update name for: " + glassesList.get(position), Toast.LENGTH_SHORT).show();
//                                break;
//                            case 1:
//                                // Update price option
//                                Toast.makeText(MainActivityOrigin.this, "Update price for: " + glassesList.get(position), Toast.LENGTH_SHORT).show();
//                                break;
//                        }
//                    }
//                });
//        builder.create().show();
//    }
//    private void loadData() {
//
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://10.0.2.2:8080/eye-wear-shop/rest/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        GlassesService glassesService = retrofit.create(GlassesService.class);
//
//        Call<List<Glasses>> call = glassesService.getAllGlasses();
//        call.enqueue(new Callback<List<Glasses>>() {
//            @Override
//            public void onResponse(Call<List<Glasses>> call, Response<List<Glasses>> response) {
//
//
//                if (response.isSuccessful()) {
//                    List<Glasses> glassesList = response.body();
//                    ArrayAdapter<Glasses> adapter = new ArrayAdapter<>(MainActivityOrigin.this, android.R.layout.simple_list_item_1, glassesList);
//                    listview.setAdapter(adapter);
//                    datalist = new ArrayList<>(adapter.getCount());
//                    for (int i = 0; i < adapter.getCount(); i++) {
//                        Glasses glasses = adapter.getItem(i);
//                        datalist.add(glasses);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Glasses>> call, Throwable t) {
//                Log.e("MainActivity", "Error: " + t.getMessage());
//                Toast.makeText(MainActivityOrigin.this, "Failed to fetch glassess", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    private void addGlasses(Glasses glasses) {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://10.0.2.2:8080/eye-wear-shop/rest/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        GlassesService glassesService = retrofit.create(GlassesService.class);
//
//        Call<Void> call = glassesService.addGlasses(glasses);
//        call.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                if (response.isSuccessful()) {
//
//                    loadData();
//                    Toast.makeText(MainActivityOrigin.this, "Glasses added successfully", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(MainActivityOrigin.this, "Failed to add glasses", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                Toast.makeText(MainActivityOrigin.this, "Failed to add glasses: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    private void updateGlasses( Glasses updatedGlasses) {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://10.0.2.2:8080/eye-wear-shop/rest/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        GlassesService glassesService = retrofit.create(GlassesService.class);
//
//        Call<Void> call = glassesService.updateGlasses(updatedGlasses);
//
//        call.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                if (response.isSuccessful()) {
//                    Toast.makeText(MainActivityOrigin.this, "Glasses updated successfully", Toast.LENGTH_SHORT).show();
//                    loadData();
//                }
//                else {
//                    Toast.makeText(MainActivityOrigin.this, "Failed to update glasses", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                Toast.makeText(MainActivityOrigin.this, "Failed to update glasses: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void showUpdateDialog(final int position) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Update Glasses");
//
//        // Inflate the custom layout for updating a product
//        View updateGlassesView = getLayoutInflater().inflate(R.layout.edit_glasses, null);
//        builder.setView(updateGlassesView);
//
//        // Find the EditTexts and the Update button in the custom layout
//        EditText udGlassesId = updateGlassesView.findViewById(R.id.ud_GlassesId);
//        EditText udGlassesName = updateGlassesView.findViewById(R.id.ud_GlassesName);
//        EditText udGlassesType = updateGlassesView.findViewById(R.id.ud_GlassesType);
//        EditText udGlassesPrice = updateGlassesView.findViewById(R.id.ud_GlassesPrice);
//        EditText udGlassesQuantity = updateGlassesView.findViewById(R.id.ud_GlassesQuantity);
//        EditText udImageUri = updateGlassesView.findViewById(R.id.ud_ImageUri);
//
//
//        // Set initial values for the EditTexts based on the selected product
//        Glasses selectedGlasses = datalist.get(position);
//        udGlassesId.setText(String.valueOf(selectedGlasses.getId()));
//        udGlassesName.setText(selectedGlasses.getName());
//        udGlassesType.setText(selectedGlasses.getType());
//        udGlassesPrice.setText(String.valueOf(selectedGlasses.getPrice()));
//        udGlassesQuantity.setText(String.valueOf(selectedGlasses.getQuantity()));
//        udImageUri.setText(selectedGlasses.getImageUri());
//
//        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // Get the updated values from the EditTexts
//                int updatedGlassesId = Integer.parseInt(udGlassesId.getText().toString());
//                String updatedGlassesName = udGlassesName.getText().toString();
//                String updatedGlassesType = udGlassesType.getText().toString();
//                String updatedGlassesPrice = udGlassesPrice.getText().toString();
//                String updatedGlassesQuantity = udGlassesQuantity.getText().toString();
//                String updatedImageUri = udImageUri.getText().toString();
//
//                // Assuming datalist is a List<Product> containing your products
//                if (position >= 0  && position < datalist.size()) {
//                    // Update the product at the specified position
//                    Glasses selectedGlasses = datalist.get(position);
//                    selectedGlasses.setId(Integer.parseInt(String.valueOf(updatedGlassesId)));
//                    selectedGlasses.setName(updatedGlassesName);
//                    selectedGlasses.setType(updatedGlassesType);
//                    selectedGlasses.setPrice(Double.parseDouble(updatedGlassesPrice));
//                    selectedGlasses.setQuantity(Integer.parseInt(updatedGlassesQuantity));
//                    selectedGlasses.setImageUri(updatedImageUri);
//
//                    // Call the updateGlasses method with the updated values
//                    updateGlasses( selectedGlasses);
//
//                    // Notify the adapter that the data has changed
//                } else {
//                    // If position is out of bounds, it means we're adding a new product
//                    // Call the addGlasses method with the new product
//                    Glasses newGlasses = new Glasses(updatedGlassesId, updatedGlassesName, updatedGlassesType, Double.parseDouble(updatedGlassesPrice),Integer.parseInt(updatedGlassesQuantity), updatedImageUri);
//                    addGlasses(newGlasses);
//                }
//            }
//        });
//
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//
//        builder.show();
//    }
//
//
//
//
//
//    private void deleteGlassesById(int glassesId) {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://10.0.2.2:8080/eye-wear-shop/rest/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        GlassesService glassesService = retrofit.create(GlassesService.class);
//
//        Call<Void> call = glassesService.deleteGlassesById(glassesId);
//        call.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                if (response.isSuccessful()) {
//                    loadData();
//                    Toast.makeText(MainActivityOrigin.this, "Glasses deleted successfully", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(MainActivityOrigin.this, "Failed to delete glasses", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                Toast.makeText(MainActivityOrigin.this, "Failed to delete glasses", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}