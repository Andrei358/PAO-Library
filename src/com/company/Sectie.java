package com.company;

class Sectie {
    private String nume;
    private Integer etaj;
    private Integer raion;

    public Sectie(String nume, Integer etaj, Integer raion){
        this.nume = nume;
        this.etaj = etaj;
        this.raion = raion;
    }
    public String get_nume(){
        return this.nume;
    }
    public Integer get_etaj(){
        return this.etaj;
    }
    public Integer get_raion(){
        return this.raion;
    }
}