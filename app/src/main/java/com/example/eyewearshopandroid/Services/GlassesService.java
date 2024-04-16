package com.example.eyewearshopandroid.Services;

import com.example.eyewearshopandroid.Model.Glasses;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GlassesService {


    @GET("glassess")
    Call<List<Glasses>> getAllGlasses();
    @POST("glassess")
    Call<Void> addGlasses(@Body Glasses product);

    @PUT("glassess")
    Call<Void> updateGlasses(@Body Glasses product);

    @DELETE("glassess/{id}")
    Call<Void> deleteGlassesById(@Path("id") int id);
}
