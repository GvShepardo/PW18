package com.pw18.pw18.models;

import com.google.gson.Gson;
import com.pw18.pw18.dataClasses.Visita;

import com.google.gson.JsonArray;
import java.io.PrintWriter;
import java.sql.*;

public class VisiteModel {

    private static final String DB_URL = "jdbc:derby://localhost:1527/mainDB";
    private static final String DB_USER = "APP";
    private static final String DB_PASSWORD = "password";

    Connection connection;
    boolean closed;

    public VisiteModel() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            closed = false;
        } catch (ClassNotFoundException | NullPointerException | SQLException ex) {
            System.out.println(ex);
        }
    }

    public JsonArray extract(){

        openConnection();

        JsonArray jsonArray = new com.google.gson.JsonArray();

        try{

            String query = "SELECT * FROM VISITE";
            ResultSet resultSet = connection.createStatement().executeQuery(query);

            while(resultSet.next()){
                Visita visita = new Visita(resultSet.getString("PAGINA"), resultSet.getInt("COUNTER"));
                Gson gson = new Gson();
                jsonArray.add(gson.toJson(visita));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        closeConnection();

        return jsonArray;

    }

    public void update(String columnName){

        openConnection();

        try{
            String query = "UPDATE VISITE SET COUNTER = COUNTER+1 WHERE PAGINA = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, columnName);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        closeConnection();

    }

    public void reset(){

        openConnection();

        try {

            String query = "UPDATE VISITE SET COUNTER = 0";
            connection.createStatement().executeUpdate(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        closeConnection();

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
