package com.company;

class Editura {
    private String nume;
    private String adresa;

    public Editura(String nume, String adresa){
        this.nume = nume;
        this.adresa = adresa;
    }
    public String get_nume(){
        return this.nume;
    }
    public String get_adresa(){
        return this.adresa;
    }
}