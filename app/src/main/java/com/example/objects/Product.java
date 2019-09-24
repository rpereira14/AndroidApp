package com.example.objects;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Product implements Parcelable {

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    private String productName;
    private int ID;
    private int quantity;
    private boolean isAvailable;
    private double price;
    public boolean wasReseted = false;


    protected Product(Parcel in) {
        productName = in.readString();
        ID = in.readInt();
        quantity = in.readInt();
        isAvailable = in.readByte() != 0;
        price = in.readDouble();
        wasReseted = in.readByte() != 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    //metodo para reiniciar a quantidade e devolver um boolean
    public boolean resetQuantity(Product product){

            setQuantity(0);
            wasReseted = true;
            return wasReseted;

    }



    //Construtores
    public Product(String nome, int ID){
       this.productName = nome;
       this.ID = ID;
    }
    public Product (String nome, int ID, boolean isAvailable){
        this(nome, ID);
        this.isAvailable = isAvailable;
    }
    public Product (String nome, int ID, int quantity){
        this (nome, ID);
        this.quantity = quantity;
    }
    public Product (String nome, int ID, int quantity, boolean isAvailable) {
        this(nome, ID, quantity);
        this.isAvailable = isAvailable;
    }

    public Product (String nome, int ID, double price) {
        this(nome, ID);
        this.price = price;
    }


    //Getters & Setter


    public double getPrice() {
        return price;
    }

    public String getProductName() {
        return productName;
    }

    public int getID() {
        return ID;
    }

    public void setQuantity(int quantity){
        if(quantity<0){wasReseted = false;}
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeInt(ID);
        dest.writeInt(quantity);
        dest.writeByte((byte) (isAvailable ? 1 : 0));
        dest.writeDouble(price);
        dest.writeByte((byte) (wasReseted ? 1 : 0));
    }
}
