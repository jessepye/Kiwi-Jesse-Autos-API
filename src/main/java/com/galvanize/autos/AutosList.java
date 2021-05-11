package com.galvanize.autos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class AutosList {

    private List<Automobile> automobiles;
    private String searchParameters;

    public AutosList(List<Automobile> autos) {
        this.automobiles = autos;
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
