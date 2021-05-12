package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.NavigableSet;
import java.util.Iterator;
import java.util.TreeSet;
import java.io.FileWriter;

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

class DataHandler{
    private static String pathToLog = "log.csv";
    private static String pathToCsvAutori = "autori.csv";
    private static String pathToCsvEdituri = "edituri.csv";
    private static String pathToCsvSectii = "sectii.csv";
    private static String pathToCsvCarti = "carti.csv";
    private static FileWriter logWriter;
    private static FileWriter csvWriterAutori;
    private static FileWriter csvWriterEdituri;
    private static FileWriter csvWriterSectii;
    private static FileWriter csvWriterCarti;
    private static BufferedReader csvReaderAutori;
    private static BufferedReader csvReaderEdituri;
    private static BufferedReader csvReaderSectii;
    private static BufferedReader csvReaderCarti;

    private static DataHandler dataHandler;

    private DataHandler(){
        try {
            logWriter = new FileWriter(pathToLog, true);
            csvWriterAutori = new FileWriter(pathToCsvAutori, true);
            csvWriterEdituri = new FileWriter(pathToCsvEdituri, true);
            csvWriterSectii = new FileWriter(pathToCsvSectii, true);
            csvWriterCarti = new FileWriter(pathToCsvCarti, true);
            csvReaderAutori = new BufferedReader(new FileReader(pathToCsvAutori));
            csvReaderEdituri = new BufferedReader(new FileReader(pathToCsvEdituri));
            csvReaderSectii = new BufferedReader(new FileReader(pathToCsvSectii));
            csvReaderCarti = new BufferedReader(new FileReader(pathToCsvCarti));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public static DataHandler getDataHandler(){
        if(dataHandler == null){
            dataHandler = new DataHandler();
        }
        return dataHandler;
    }

    public void loadAutori(ArrayList<Autor> lista_autori){
        String row;
        try {
            logWriter.append("loadAutori, " + LocalDateTime.now().toString() + "\n");
            logWriter.flush();
            while ((row = csvReaderAutori.readLine()) != null) {
                String[] data = row.split(",");
                Autor autor_nou = new Autor(data[0], data[1], data[2]);
                lista_autori.add(autor_nou);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void loadEdituri(ArrayList<Editura> lista_edituri){
        String row;
        try {
            logWriter.append("loadEdituri, " + LocalDateTime.now().toString() + "\n");
            logWriter.flush();
            while ((row = csvReaderEdituri.readLine()) != null) {
                String[] data = row.split(",");
                Editura editura_noua = new Editura(data[0], data[1]);
                lista_edituri.add(editura_noua);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void loadSectii(ArrayList<Sectie> lista_sectii){
        String row;
        try {
            logWriter.append("loadSectii, " + LocalDateTime.now().toString() + "\n");
            logWriter.flush();
            while ((row = csvReaderSectii.readLine()) != null) {
                String[] data = row.split(",");
                Sectie sectie_noua = new Sectie(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]));
                lista_sectii.add(sectie_noua);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void loadCarti(ArrayList<Carte> lista_carti, ArrayList<Autor> lista_autori,
                          ArrayList<Editura> lista_edituri, ArrayList<Sectie> lista_sectii){
        String row;
        try {
            logWriter.append("loadCarti, " + LocalDateTime.now().toString() + "\n");
            logWriter.flush();
            while ((row = csvReaderCarti.readLine()) != null) {
                String[] data = row.split(",");

                Integer na = lista_autori.size();
                Integer ne = lista_edituri.size();
                Integer ns = lista_sectii.size();

                Autor autor = null;
                Editura editura = null;
                Sectie sectie = null;

                for(Integer i=0;i<na;++i){
                    if(lista_autori.get(i).get_nume().equals(data[1])){
                        autor = lista_autori.get(i);
                    }
                }

                for(Integer i=0;i<ne;++i){
                    if(lista_edituri.get(i).get_nume().equals(data[2])){
                        editura = lista_edituri.get(i);
                    }
                }

                for(Integer i=0;i<ns;++i){
                    if(lista_sectii.get(i).get_nume().equals(data[4])){
                        sectie = lista_sectii.get(i);
                    }
                }

                Carte carte_noua = new Carte(data[0], autor, editura, data[3], sectie);
                lista_carti.add(carte_noua);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void write(Autor autor){
        try {
            logWriter.append("writeAutor, " + LocalDateTime.now().toString() + "\n");
            csvWriterAutori.append(autor.get_nume());
            csvWriterAutori.append(",");
            csvWriterAutori.append(autor.get_prenume());
            csvWriterAutori.append(",");
            csvWriterAutori.append(autor.get_tara());
            csvWriterAutori.append("\n");
            csvWriterAutori.flush();
            logWriter.flush();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void write(Editura editura){
        try {
            logWriter.append("writeEditura, " + LocalDateTime.now().toString() + "\n");
            csvWriterAutori.append(editura.get_nume());
            csvWriterAutori.append(",");
            csvWriterAutori.append(editura.get_adresa());
            csvWriterAutori.append("\n");
            csvWriterAutori.flush();
            logWriter.flush();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void write(Sectie sectie) {
        try {
            logWriter.append("writeSectie, " + LocalDateTime.now().toString() + "\n");
            csvWriterSectii.append(sectie.get_nume());
            csvWriterSectii.append(",");
            csvWriterSectii.append(sectie.get_etaj().toString());
            csvWriterSectii.append(",");
            csvWriterSectii.append(sectie.get_raion().toString());
            csvWriterSectii.append("\n");
            csvWriterSectii.flush();
            logWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void write(Carte carte) {
        try {
            logWriter.append("writeCarte, " + LocalDateTime.now().toString() + "\n");
            csvWriterCarti.append(carte.get_titlu());
            csvWriterCarti.append(",");
            csvWriterCarti.append(carte.get_autor().get_nume());
            csvWriterCarti.append(",");
            csvWriterCarti.append(carte.get_editura().get_nume());
            csvWriterCarti.append(",");
            csvWriterCarti.append(carte.get_isbn());
            csvWriterCarti.append(",");
            csvWriterCarti.append(carte.get_sectie().get_nume());
            csvWriterCarti.append("\n");
            csvWriterCarti.flush();
            logWriter.flush();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}

class Gestiune {
    ArrayList<Autor> lista_autori = new ArrayList<>();
    ArrayList<Editura> lista_edituri = new ArrayList<>();
    ArrayList<Sectie> lista_sectii = new ArrayList<>();
    ArrayList<Carte> lista_carti = new ArrayList<>();
    ArrayList<Persoana> lista_persoane = new ArrayList<>();
    NavigableSet<Imprumut> lista_imprumuturi = new TreeSet<>();
    DataHandler dataHandler;

    public Gestiune(){
        dataHandler = DataHandler.getDataHandler();
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
