package com.example.consultafipe;

import com.example.consultafipe.service.AutomovelCallback;
import com.example.consultafipe.service.FipeService;
import com.example.consultafipe.model.Automovel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetornaObj {


    public void consultaAutomovel(String fipeCode, String yearId, String token, AutomovelCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.invertexto.com/v1/fipe/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FipeService service = retrofit.create(FipeService.class);

        Call<Automovel> call = service.getHistoricoFipe(token, fipeCode, yearId);

        call.enqueue(new Callback<Automovel>() {
            @Override
            public void onResponse(Call<Automovel> call, Response<Automovel> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new Exception("Erro na resposta: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<Automovel> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}

