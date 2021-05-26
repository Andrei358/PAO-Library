package com.company;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

class Gestiune {
    ArrayList<Autor> lista_autori = new ArrayList<>();
    ArrayList<Editura> lista_edituri = new ArrayList<>();
    ArrayList<Sectie> lista_sectii = new ArrayList<>();
    ArrayList<Carte> lista_carti = new ArrayList<>();
    ArrayList<Persoana> lista_persoane = new ArrayList<>();
    NavigableSet<Imprumut> lista_imprumuturi = new TreeSet<>();
    //DataHandler dataHandler;
    DataHandlerDB dataHandler;

    public Gestiune(){
        dataHandler = DataHandlerDB.getDataHandlerDB();
        dataHandler.loadAutori(lista_autori);
        dataHandler.loadEdituri(lista_edituri);
        dataHandler.loadSectii(lista_sectii);
        dataHandler.loadCarti(lista_carti, lista_autori, lista_edituri, lista_sectii);
    }
    public void adauga_autor(String nume, String prenume, String tara){
        Autor autor_nou = new Autor(nume, prenume, tara);
        lista_autori.add(autor_nou);
        this.dataHandler.write(autor_nou);
    }
    public void adauga_editura(String nume, String adresa){
        Editura editura_noua = new Editura(nume, adresa);
        lista_edituri.add(editura_noua);
        this.dataHandler.write(editura_noua);
    }
    public void adauga_sectie(String nume, Integer etaj, Integer raion){
        Sectie sectie_noua = new Sectie(nume, etaj, raion);
        lista_sectii.add(sectie_noua);
        this.dataHandler.write(sectie_noua);
    }
    public void adauga_carte(String titlu, String nume_autor, String nume_editura, String isbn, String nume_sectie){
        Integer na = lista_autori.size();
        Integer ne = lista_edituri.size();
        Integer ns = lista_sectii.size();

        Autor autor = null;
        Editura editura = null;
        Sectie sectie = null;

        for(Integer i=0;i<na;++i){
            if(lista_autori.get(i).get_nume().equals(nume_autor)){
                autor = lista_autori.get(i);
            }
        }

        for(Integer i=0;i<ne;++i){
            if(lista_edituri.get(i).get_nume().equals(nume_editura)){
                editura = lista_edituri.get(i);
            }
        }

        for(Integer i=0;i<ns;++i){
            if(lista_sectii.get(i).get_nume().equals(nume_sectie)){
                sectie = lista_sectii.get(i);
            }
        }

        if(autor != null && editura != null && sectie != null){
            Carte carte_noua = new Carte(titlu, autor, editura, isbn, sectie);
            lista_carti.add(carte_noua);
            this.dataHandler.write(carte_noua);
        }
    }
    public void afiseaza_carti(){
        Integer n = lista_carti.size();

        System.out.println("\nLista carti:");
        for(Integer i=0;i<n;++i){
            Carte carte = lista_carti.get(i);

            Autor autor = carte.get_autor();
            Editura editura = carte.get_editura();
            Sectie sectie = carte.get_sectie();

            System.out.print("Cartea #");
            System.out.print(i+1);
            System.out.print(": ");
            System.out.print(carte.get_titlu());
            System.out.print(", ");
            System.out.print(autor.get_prenume());
            System.out.print(" ");
            System.out.print(autor.get_nume());
            System.out.print(", ");
            System.out.print(editura.get_nume());
            System.out.print(", ");
            System.out.print(carte.get_isbn());
            System.out.print(", ");
            System.out.println(sectie.get_nume());
        }
    }
    public void afiseaza_autori(){
        Integer n = lista_autori.size();

        System.out.println("\nLista autori:");
        for(Integer i=0;i<n;++i){
            Autor autor = lista_autori.get(i);
            System.out.print("Autorul #");
            System.out.print(i+1);
            System.out.print(": ");
            System.out.print(autor.get_prenume());
            System.out.print(" ");
            System.out.print(autor.get_nume());
            System.out.print(", ");
            System.out.println(autor.get_tara());
        }
    }
    public void localizeaza_carte(String titlu_carte){
        Integer n = lista_carti.size();

        for(Integer i=0;i<n;++i){
            if(lista_carti.get(i).get_titlu().equals(titlu_carte)){
                Sectie sectie = lista_carti.get(i).get_sectie();
                System.out.print("\nCartea ");
                System.out.print(titlu_carte);
                System.out.print(" se afla la etajul ");
                System.out.print(sectie.get_etaj());
                System.out.print(" in raionul ");
                System.out.print(sectie.get_raion());
                System.out.println(".");
            }
        }
    }
    public void adauga_elev(String nume, String prenume, Integer an_nastere, String cnp, Integer clasa){
        Elev elev_nou = new Elev(nume, prenume, an_nastere, cnp, clasa);
        lista_persoane.add(elev_nou);
    }
    public void adauga_profesor(String nume, String prenume, Integer an_nastere, String cnp, String materie){
        Profesor profesor_nou = new Profesor(nume, prenume, an_nastere, cnp, materie);
        lista_persoane.add(profesor_nou);
    }
    public void deschide_imprumut(String nume_persoana, String prenume_persoana, String titlu_carte){
        Integer nc = lista_carti.size();
        Integer np = lista_persoane.size();

        Carte carte = null;
        Persoana persoana = null;
        for(Integer i=0;i<nc;++i){
            if(lista_carti.get(i).get_titlu().equals(titlu_carte)){
                carte = lista_carti.get(i);
            }
        }
        for(Integer i=0;i<np;++i){
            Persoana persoana_curenta = lista_persoane.get(i);
            if(persoana_curenta.get_nume().equals(nume_persoana) &&
                    persoana_curenta.get_prenume().equals(prenume_persoana)){
                persoana = persoana_curenta;
            }
        }

        if(carte != null && persoana != null){
            Imprumut imprumut_nou = new Imprumut(persoana, carte, LocalDateTime.now());
            lista_imprumuturi.add(imprumut_nou);
        }
    }
    public void inchide_imprumut(String nume_persoana, String prenume_persoana, String titlu_carte){
        Imprumut imprumut = null;

        Iterator iterator = lista_imprumuturi.descendingIterator();
        while(iterator.hasNext()) {
            imprumut = (Imprumut) iterator.next();
        }

        if(imprumut != null){
            imprumut.inchide(LocalDateTime.now());
        }
    }
    public void afiseaza_imprumuturi_nereturnate(){
        Iterator iterator = lista_imprumuturi.descendingIterator();

        while(iterator.hasNext()) {

            Imprumut imprumut = (Imprumut) iterator.next();
            if (!imprumut.get_status()) {
                System.out.print("\n");
                imprumut.afiseaza();
            }
        }
    }
}
