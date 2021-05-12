package com.galvanize.autos;

@SuppressWarnings("unused")
public class UpdateAutoRequest {
    private int price;
    private Preowned preowned;

    public UpdateAutoRequest(int price, Preowned preowned) {
        this.price = price;
        this.preowned = preowned;
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
}
