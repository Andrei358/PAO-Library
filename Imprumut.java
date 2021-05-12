package com.company;

import java.time.LocalDateTime;

class Imprumut implements Comparable<Imprumut> {
    private Boolean status;
    private Persoana persoana;
    private Carte carte;
    private LocalDateTime data_inchiriere;
    private LocalDateTime data_returnare;

    public Imprumut(Persoana persoana, Carte carte, LocalDateTime data_inchiriere){
        this.persoana = persoana;
        this.carte = carte;
        this.data_inchiriere = data_inchiriere;
        this.status = false;
        this.data_returnare = null;
    }
    public Boolean get_status(){
        return this.status;
    }
    public Persoana get_persoana(){
        return this.persoana;
    }
    public Carte get_carte(){
        return this.carte;
    }
    public LocalDateTime get_data_inchiriere(){
        return this.data_inchiriere;
    }
    public LocalDateTime get_data_returnare(){
        return this.data_returnare;
    }
    public int compareTo(Imprumut other) {
        return this.data_inchiriere.compareTo(other.data_inchiriere);
    }
    public void inchide(LocalDateTime data_returnare){
        this.status = true;
        this.data_returnare = data_returnare;
    }
    public void afiseaza(){
        System.out.print("Cartea ");
        System.out.print(this.carte.get_titlu());
        System.out.print(" a fost imprumutata de ");
        System.out.print(this.persoana.get_prenume());
        System.out.print(" ");
        System.out.print(this.persoana.get_nume());
        System.out.print(" la data de ");
        System.out.print(this.data_inchiriere);
        System.out.print(".");
    }
}