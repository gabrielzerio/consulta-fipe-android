package com.example.consultafipe.service;
import com.example.consultafipe.model.Automovel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface FipeService {
    @GET("/v1/fipe/history")
    Call<Automovel> getHistoricoFipe(
            @Query("token") String token,
            @Query("fipe_code") String fipeCode,
            @Query("year_id") String yearId
    );

}
