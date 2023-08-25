package com.pw18.pw18.dataClasses;

public class Donazioni {

    private String mese;
    private int soldi;

    public Donazioni(String month, int money){
        mese = month;
        soldi = money;
    }

    public String getMese() { return mese; }
    public int getSoldi() { return soldi; }

}
