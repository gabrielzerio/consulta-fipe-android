package com.example.consultafipe.service;

import com.example.consultafipe.model.Automovel;

public interface AutomovelCallback {
    void onSuccess(Automovel automovel);
    void onFailure(Throwable t);
}

