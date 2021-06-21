package com.galvanize.autos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class AutosList {
    private List<Automobile> automobiles;

    public AutosList() {
        this.automobiles = new ArrayList<>();
    }

    public AutosList(List<Automobile> automobiles) {
        this.automobiles = automobiles;
    }

    @JsonIgnore
    public boolean isEmpty() {
        return this.automobiles.size() == 0;
    }

    public int getCount() {
        return this.automobiles.size();
    }

    public List<Automobile> getAutomobiles() {
        return this.automobiles;
    }

    @Override
    public String toString() {
        return "AutosList{" +
                "automobiles=" + automobiles +
                '}';
    }
}
