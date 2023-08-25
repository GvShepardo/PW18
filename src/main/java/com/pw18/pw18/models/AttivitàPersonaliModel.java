package com.pw18.pw18.models;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.pw18.pw18.dataClasses.Attivita;

import java.io.PrintWriter;
import java.sql.*;

public class AttivitàPersonaliModel {

    private static final String DB_URL = "jdbc:derby://localhost:1527/mainDB";
    private static final String DB_USER = "APP";
    private static final String DB_PASSWORD = "password";

    Connection connection;
    boolean closed;

    public AttivitàPersonaliModel() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            closed = false;
        } catch (ClassNotFoundException | NullPointerException | SQLException ex) {
            System.out.println(ex);
        }
    }

    public void add(String username, String attivita){
        openConnection();

        try{

            String query = "INSERT INTO USERATTIVITA (USERNAME, ATTIVITA) VALUES ( ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, attivita);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        closeConnection();
    }

    public void delete(String username, String attivita){
        openConnection();

        try{
            String query = "DELETE FROM USERATTIVITA WHERE USERNAME = ? AND ATTIVITA = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, attivita);
            preparedStatement.executeUpdate(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        closeConnection();
    }

    public JsonArray exctract(String username){
        openConnection();
        JsonArray jsonArray = new JsonArray();

        try {
            String query = "SELECT * FROM USERATTIVITA WHERE USERNAME= ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery(query);

            while (resultSet.next()) {
                Gson gson = new Gson();
                Attivita attivita = new Attivita(resultSet.getString("USERNAME"), resultSet.getString("ATTIVITA"));
                jsonArray.add(gson.toJson(attivita));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        closeConnection();
        return  jsonArray;
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
