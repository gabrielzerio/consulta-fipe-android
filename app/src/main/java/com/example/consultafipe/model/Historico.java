package com.example.consultafipe.model;

public class Historico {
    private String month;
    private int price;

    // Construtor
    public Historico(String month, int price) {
        this.month = month;
        this.price = price;
    }

    // Getters e Setters
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

