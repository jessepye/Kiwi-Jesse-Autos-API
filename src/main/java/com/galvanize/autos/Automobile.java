package com.galvanize.autos;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "automobiles")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Automobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private int year;
    private String color;
    private double miles;
    private String vin;
    private Preowned preowned;
    private Grade grade;
    private int price;

    public Automobile() {

    }

    public Automobile(String make, String model, int year, String vin) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.vin = vin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
                ", vin='" + this.vin + '\'' +
                ", preowned='" + this.preowned + '\'' +
                ", grade='" + this.grade + '\'' +
                ", price='" + this.price + '\'' +
                '}';
    }
}
