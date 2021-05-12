package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DataHandler{
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

    private static com.company.DataHandler dataHandler;

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
    public static com.company.DataHandler getDataHandler(){
        if(dataHandler == null){
            dataHandler = new com.company.DataHandler();
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