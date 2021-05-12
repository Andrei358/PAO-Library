package com.company;

class Elev extends Persoana{
    private Integer clasa;

    public Elev(String nume, String prenume, Integer an_nastere, String cnp, Integer clasa){
        super(nume, prenume, an_nastere, cnp);
        this.clasa = clasa;
    }
    public Integer get_clasa(){
        return this.clasa;
    }
}