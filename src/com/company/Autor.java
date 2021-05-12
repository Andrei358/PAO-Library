package com.company;

class Autor {
    private String nume;
    private String prenume;
    private String tara;

    public Autor(String nume, String prenume, String tara){
        this.nume = nume;
        this.prenume = prenume;
        this.tara = tara;
    }
    public String get_nume(){
        return this.nume;
    }
    public String get_prenume(){
        return this.prenume;
    }
    public String get_tara(){
        return this.tara;
    }
}