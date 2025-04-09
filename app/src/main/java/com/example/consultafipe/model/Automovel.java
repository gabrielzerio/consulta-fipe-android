package com.example.consultafipe.model;

import java.util.List;

public class Automovel {
    private String brand;
    private String model;
    private String model_year;
    private String fuel;
    private List<Historico> history;

    // Construtor
    public Automovel(String brand, String model, String model_year, String fuel, List<Historico> history) {
        this.brand = brand;
        this.model = model;
        this.model_year = model_year;
        this.fuel = fuel;
        this.history = history;
    }
    public Automovel(){

    }
    // Getters e Setters
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModelYear() {
        return model_year;
    }

    public void setModelYear(String model_year) {
        this.model_year = model_year;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public List<Historico> getHistory() {
        return history;
    }

    public void setHistory(List<Historico> history) {
        this.history = history;
    }
}
