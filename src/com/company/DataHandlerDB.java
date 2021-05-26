package com.company;

import java.sql.*;
import java.util.ArrayList;

public class DataHandlerDB{
    private static String dbURL = "jdbc:mysql://localhost:3306/librarydb?characterEncoding=latin1";
    private static String username = "root";
    private static String password = "pass1234";
    private static Connection conn;

    private static com.company.DataHandlerDB dataHandler;

    private DataHandlerDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, username, password);

            if (conn != null) {
                System.out.println("Connected");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static com.company.DataHandlerDB getDataHandlerDB(){
        if(dataHandler == null){
            dataHandler = new com.company.DataHandlerDB();
        }
        return dataHandler;
    }

    public void loadAutori(ArrayList<Autor> lista_autori){
        try{
            String sql = "SELECT * FROM autori";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            int count = 0;

            while (result.next()){
                String nume = result.getString("nume");
                String prenume = result.getString("prenume");
                String tara = result.getString("tara");

                Autor autor_nou = new Autor(nume, prenume, tara);
                lista_autori.add(autor_nou);

                count++;
            }

            if(count == 0){
                System.out.println("No autor loaded!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void loadEdituri(ArrayList<Editura> lista_edituri){
        try{
            String sql = "SELECT * FROM edituri";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            int count = 0;

            while (result.next()){
                String nume = result.getString("nume");
                String adresa = result.getString("adresa");

                Editura editura_noua = new Editura(nume, adresa);
                lista_edituri.add(editura_noua);

                count++;
            }

            if(count == 0){
                System.out.println("No editura loaded!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void loadSectii(ArrayList<Sectie> lista_sectii){
        try{
            String sql = "SELECT * FROM sectii";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            int count = 0;

            while (result.next()){
                String nume = result.getString("nume");
                Integer raion = result.getInt("raion");
                Integer etaj = result.getInt("etaj");

                //Sectie sectie_noua = new Sectie(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]));
                Sectie sectie_noua = new Sectie(nume, raion, etaj);
                lista_sectii.add(sectie_noua);

                count++;
            }

            if(count == 0){
                System.out.println("No sectie loaded!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void loadCarti(ArrayList<Carte> lista_carti, ArrayList<Autor> lista_autori,
                          ArrayList<Editura> lista_edituri, ArrayList<Sectie> lista_sectii){
        try{
            String sql = "SELECT * FROM carti";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            int count = 0;

            while (result.next()){
                String nume = result.getString("titlu");
                String autor_nume = result.getString("autor_nume");
                String editura_nume = result.getString("editura_nume");
                String isbn = result.getString("isbn");
                String sectie_nume = result.getString("sectie_nume");

                Integer na = lista_autori.size();
                Integer ne = lista_edituri.size();
                Integer ns = lista_sectii.size();

                Autor autor = null;
                Editura editura = null;
                Sectie sectie = null;

                for(Integer i=0;i<na;++i){
                    if(lista_autori.get(i).get_nume().equals(autor_nume)){
                        autor = lista_autori.get(i);
                    }
                }

                for(Integer i=0;i<ne;++i){
                    if(lista_edituri.get(i).get_nume().equals(editura_nume)){
                        editura = lista_edituri.get(i);
                    }
                }

                for(Integer i=0;i<ns;++i){
                    if(lista_sectii.get(i).get_nume().equals(sectie_nume)){
                        sectie = lista_sectii.get(i);
                    }
                }

                Carte carte_noua = new Carte(nume, autor, editura, isbn, sectie);
                lista_carti.add(carte_noua);

                count++;
            }

            if(count == 0){
                System.out.println("No sectie loaded!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void write(Autor autor){
        try{
            String sql_insert = "INSERT INTO autori (nume, prenume, tara) VALUES (?, ?, ?)";
            PreparedStatement sql_statement = conn.prepareStatement(sql_insert);

            sql_statement.setString(1, autor.get_nume());
            sql_statement.setString(2, autor.get_prenume());
            sql_statement.setString(3, autor.get_tara());

            int rowsInserted = sql_statement.executeUpdate();
            if (rowsInserted == 0) {
               System.out.println("Error writing autor into DB");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void write(Editura editura){
        try{
            String sql_insert = "INSERT INTO edituri (nume, adresa) VALUES (?, ?)";
            PreparedStatement sql_statement = conn.prepareStatement(sql_insert);

            sql_statement.setString(1, editura.get_nume());
            sql_statement.setString(2, editura.get_adresa());

            int rowsInserted = sql_statement.executeUpdate();
            if (rowsInserted == 0) {
                System.out.println("Error writing editura into DB");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void write(Sectie sectie) {
        try{
            String sql_insert = "INSERT INTO sectii (nume, etaj, raion) VALUES (?, ?, ?)";
            PreparedStatement sql_statement = conn.prepareStatement(sql_insert);

            sql_statement.setString(1, sectie.get_nume());
            sql_statement.setInt(2, sectie.get_etaj());
            sql_statement.setInt(3, sectie.get_raion());

            int rowsInserted = sql_statement.executeUpdate();
            if (rowsInserted == 0) {
                System.out.println("Error writing sectie into DB");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void write(Carte carte) {
        try{
            String sql_insert = "INSERT INTO carti (titlu, autor_nume, editura_nume, isbn, sectie_nume) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement sql_statement = conn.prepareStatement(sql_insert);

            sql_statement.setString(1, carte.get_titlu());
            sql_statement.setString(2, carte.get_autor().get_nume());
            sql_statement.setString(3, carte.get_editura().get_nume());
            sql_statement.setString(4, carte.get_isbn());
            sql_statement.setString(5, carte.get_sectie().get_nume());

            int rowsInserted = sql_statement.executeUpdate();
            if (rowsInserted == 0) {
                System.out.println("Error writing carte into DB");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}