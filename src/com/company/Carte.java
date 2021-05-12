package com.company;

class Carte {
    private String titlu;
    private Autor autor;
    private Editura editura;
    private String isbn;
    private Sectie sectie;

    public Carte(String titlu, Autor autor, Editura editura, String isbn, Sectie sectie){
        this.titlu = titlu;
        this.autor = autor;
        this.editura = editura;
        this.isbn = isbn;
        this.sectie = sectie;
    }

    public String get_titlu(){
        return this.titlu;
    }
    public Autor get_autor(){
        return this.autor;
    }
    public Editura get_editura(){
        return this.editura;
    }
    public String get_isbn(){
        return this.isbn;
    }
    public Sectie get_sectie(){
        return this.sectie;
    }
}