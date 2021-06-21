package com.galvanize.autos.model;

public class UpdateAutoRequest {
    private int price;
    private Preowned preowned;
    private Grade grade;

    public UpdateAutoRequest(int price, Preowned preowned, Grade grade) {
        this.price = price;
        this.preowned = preowned;
        this.grade = grade;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
}
