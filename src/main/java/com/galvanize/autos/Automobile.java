package com.galvanize.autos;

import java.math.BigDecimal;
import java.util.Currency;

@SuppressWarnings("unused")
public class Automobile {

    private int id;
    private String make;
    private String model;
    private int year;
    private String color;
    private double miles;
    private String vin;
    private Preowned preowned;
    private Grade grade;
    private Currency price;

    public Automobile() {

    }

    public Automobile(int id, String make, String model, int year, String vin) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.vin = vin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getMiles() {
        return miles;
    }

    public void setMiles(double miles) {
        this.miles = miles;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Preowned getPreowned() {
        return preowned;
    }

    public void setPreowned(Preowned preowned) {
        this.preowned = preowned;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Currency getPrice() {
        return price;
    }

    public void setPrice(Currency price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Automobile{" +
                "id='" + this.id + '\'' +
                ", make='" + this.make + '\'' +
                ", model='" + this.model + '\'' +
                ", year='" + this.year + '\'' +
                ", color='" + this.color + '\'' +
                ", miles='" + this.miles + '\'' +
                ", vin='" + this.preowned + '\'' +
                ", preowned='" + this.preowned + '\'' +
                ", grade='" + this.grade + '\'' +
                ", price='" + this.price + '\'' +
                '}';
    }
}
