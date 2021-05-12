package com.company;

class Profesor extends Persoana{
    private String materie;

    public Profesor(String nume, String prenume, Integer an_nastere, String cnp, String materie){
        super(nume, prenume, an_nastere, cnp);
        this.materie = materie;
    }
    public String get_materie(){
        return this.materie;
    }
}