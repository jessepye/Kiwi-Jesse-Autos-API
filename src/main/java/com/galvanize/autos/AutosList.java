package com.galvanize.autos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class AutosList {

    private ArrayList<Automobile> automobiles;
    private String searchParameters;

    public AutosList() {
        this.automobiles = new ArrayList<>();
    }

    public AutosList(ArrayList<Automobile> automobiles) {
        this.automobiles = automobiles;
    }

    @JsonIgnore
    public boolean isEmpty() {
        return this.automobiles.size() == 0;
    }

    public int getCount() {
        return this.automobiles.size();
    }

    public String getSearchParameters() {
        return searchParameters;
    }

    public void setSearchParameters(String searchParameters) {
        this.searchParameters = searchParameters;
    }

    public ArrayList<Automobile> getAutomobiles() {
        return this.automobiles;
    }

    @Override
    public String toString() {
        return "AutosList{" +
                "automobiles=" + automobiles +
                '}';
    }
}
