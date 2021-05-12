package com.company;

class Persoana {
    protected String nume;
    protected String prenume;
    protected Integer an_nastere;
    protected String cnp;

    public Persoana(String nume, String prenume, Integer an_nastere, String cnp){
        this.nume = nume;
        this.prenume = prenume;
        this.an_nastere = an_nastere;
        this.cnp = cnp;
    }
    public String get_nume(){
        return this.nume;
    }
    public String get_prenume(){
        return this.prenume;
    }
    public Integer get_an_nastere(){
        return this.an_nastere;
    }
    public String get_cnp(){
        return this.cnp;
    }
}