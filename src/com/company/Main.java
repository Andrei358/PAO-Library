package com.company;

public class Main {
    public static void main(String[] args) {
	    Gestiune gestiune = new Gestiune();

	    /*gestiune.adauga_autor("Eminescu", "Mihai", "RO");
        gestiune.adauga_autor("Creanga", "Ion", "RO");
        gestiune.adauga_autor("Schmidt", "Eric Emanuel", "DE");
        gestiune.adauga_autor("Obama", "Barrack", "US");

        gestiune.adauga_editura("Humanitas", "Piata Presei Libere nr.1");
        gestiune.adauga_editura("Curtea Veche", "Aurel Vlaicu nr. 35");

        gestiune.adauga_sectie("Clasici", 1, 5);
        gestiune.adauga_sectie("Biografii", 2, 11);
        gestiune.adauga_sectie("Internationala", 2,12);

        gestiune.adauga_carte("Poezii", "Eminescu", "Humanitas",
                "13897343243", "Clasici");
        gestiune.adauga_carte("Amintiri din copilarie", "Creanga", "Curtea Veche",
                "89247367234", "Clasici");
        gestiune.adauga_carte("Harap-Alb", "Creanga", "Curtea Veche",
                "43241232132", "Clasici");
        gestiune.adauga_carte("Oscar si tanti roz", "Schmidt", "Humanitas",
                "31234231232", "Internationala");
        gestiune.adauga_carte("A promised land", "Obama", "Curtea Veche",
                "76734343424", "Biografii");*/

        gestiune.adauga_elev("Calinescu", "Mihai", 2006, "500713873351", 7);
        gestiune.adauga_elev("Stanciu", "Ruxandra", 2002, "600202674909", 12);
        gestiune.adauga_elev("Raicu", "Alexandru", 2011, "500118362730", 3);

        gestiune.adauga_profesor("Tirlea", "Violeta", 1972,
                "372823743823", "Matematica");
        gestiune.adauga_profesor("Mancu", "Camelia", 1975,
                "375445637568", "Limba si Literatura Romana");

        gestiune.deschide_imprumut("Tirlea", "Violeta", "A promised land");
        gestiune.deschide_imprumut("Calinescu", "Mihai", "Poezii");
        gestiune.inchide_imprumut("Tirlea", "Violeta", "A promised land");
        gestiune.deschide_imprumut("Tirlea", "Violeta", "Oscar si tanti roz");
        gestiune.deschide_imprumut("Raicu", "Alexandru", "Harap-Alb");
        gestiune.inchide_imprumut("Raicu", "Alexandru", "Harap-Alb");

        gestiune.afiseaza_carti();
        gestiune.afiseaza_autori();
        gestiune.localizeaza_carte("A promised land");
        gestiune.afiseaza_imprumuturi_nereturnate();
    }
}
