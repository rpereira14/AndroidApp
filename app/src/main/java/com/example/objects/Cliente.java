package com.example.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Cliente implements Parcelable {

    private String nomeCliente;
    private int numCliente;
    private double desconto;
    boolean selecionado;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nomeCliente);
        dest.writeInt(numCliente);
        dest.writeDouble(desconto);
        dest.writeByte((byte) (selecionado ? 1 : 0));
    }

    public static final Creator<Cliente> CREATOR = new Creator<Cliente>() {
        @Override
        public Cliente createFromParcel(Parcel in) {
            return new Cliente(in);
        }

        @Override
        public Cliente[] newArray(int size) {
            return new Cliente[size];
        }
    };


    //construtores
    public Cliente (String nomeCliente, int numCliente){
        this.nomeCliente = nomeCliente;
        this.numCliente = numCliente;
        this.selecionado = false;
    }
    public Cliente (String nomeCliente, int numCliente, double desconto){
        this(nomeCliente, numCliente);
        this.desconto = desconto;
        this.selecionado= false;
    }

    //getters & setters


    protected Cliente(Parcel in) {
        nomeCliente = in.readString();
        numCliente = in.readInt();
        desconto = in.readDouble();
        selecionado = in.readByte() != 0;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public int getNumCliente() {
        return numCliente;
    }

    public double getDesconto() {
        return desconto;
    }


}
