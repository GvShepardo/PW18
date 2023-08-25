package com.pw18.pw18.models;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.pw18.pw18.dataClasses.Donazioni;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class DonazioniModel {

    private static final String DB_URL = "jdbc:derby://localhost:1527/mainDB";
    private static final String DB_USER = "APP";
    private static final String DB_PASSWORD = "password";

    Connection connection = null;

    boolean closed;
    int year = Year.now().getValue();

    String[] mesi = {
            "Gennaio", "Febbraio", "Marzo", "Aprile",
            "Maggio", "Giugno", "Luglio", "Agosto",
            "Settembre", "Ottobre", "Novembre", "Dicembre"
    };

    List<Donazioni> donazioniList = new ArrayList<>();


    public DonazioniModel() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            closed = false;
        } catch (ClassNotFoundException | NullPointerException | SQLException ex) {
            System.out.println(ex);
        }
        donazioniList.add(new Donazioni("Gennaio", 0));
        donazioniList.add(new Donazioni("Febbraio", 0));
        donazioniList.add(new Donazioni("Marzo", 0));
        donazioniList.add(new Donazioni("Aprile", 0));
        donazioniList.add(new Donazioni("Maggio", 0));
        donazioniList.add(new Donazioni("Giugno", 0));
        donazioniList.add(new Donazioni("Luglio", 0));
        donazioniList.add(new Donazioni("Agosto", 0));
        donazioniList.add(new Donazioni("Settembre", 0));
        donazioniList.add(new Donazioni("Ottobre", 0));
        donazioniList.add(new Donazioni("Novembre", 0));
        donazioniList.add(new Donazioni("Dicembre", 0));
    }

    public JsonArray extract(){
        openConnection();
        JsonArray jsonArray = new JsonArray();

        try {
            String query =  "SELECT MESE, COALESCE(SUM(importo), 0) AS IMPORTI"                             +
                            "FROM ("                                                                        +
                            "       SELECT CAST(MONTH(DATA)-1 AS INTEGER) AS MESE, SUM(IMPORTO) AS IMPORTO" +
                            "       FROM DONAZIONE "                                                        +
                            "       WHERE YEAR(DATA) = ? "                                                  +
                            "       GROUP BY DATA)"                                                         +
                            "AS MOANEY_AND_MONTHS "                                                         +
                            "GROUP BY MESE";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, year);
            ResultSet resultSet = preparedStatement.executeQuery(query);

            while (resultSet.next()) {

                int mese = resultSet.getInt("MESE");
                int donazione = resultSet.getInt("IMPORTI");

                donazioniList.set(resultSet.getInt("MESE"), new Donazioni(mesi[mese], donazione));

            }

            for(Donazioni donazione : donazioniList){
                Gson gson = new Gson();
                jsonArray.add(gson.toJson(donazione));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        closeConnection();
        return jsonArray;
    }

    public void add(String username, int valore){
        try{
            String query ="INSERT INTO DONAZIONE (USERNAME, IMPORTO, DATA) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, valore);
            preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            preparedStatement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void openConnection(){
        if(closed){
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (ClassNotFoundException | NullPointerException | SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    private void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        closed = true;
    }

}
