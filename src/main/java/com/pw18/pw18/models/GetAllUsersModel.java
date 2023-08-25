package com.pw18.pw18.models;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.pw18.pw18.dataClasses.User;

import java.sql.*;

public class GetAllUsersModel {

    GetAllUsersModel getAllUsersModel = new GetAllUsersModel();

    private static final String DB_URL = "jdbc:derby://localhost:1527/mainDB";
    private static final String DB_USER = "APP";
    private static final String DB_PASSWORD = "password";

    Connection connection = null;

    Boolean closed;


    public GetAllUsersModel() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            closed = false;
        } catch (ClassNotFoundException | NullPointerException | SQLException ex) {
            System.out.println(ex);
        }
    }

    public JsonArray extractAll() {
        openConnection();
        JsonArray jsonArray = new JsonArray();

        try {
            String query = "SELECT * FROM USERS";
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {

                Gson gson = new Gson();
                User user = new User(resultSet.getString("username"), resultSet.getString("nome"),
                        resultSet.getString("cognome"), resultSet.getString("email"),
                        resultSet.getString("tel"), resultSet.getString("type"));
                jsonArray.add(gson.toJson(user)); //riga 49
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        closeConnection();
        return jsonArray;
    }

    public JsonArray extractAderenti() {
        openConnection();
        JsonArray jsonArray = new JsonArray();

        try{
            String query = "SELECT * FROM USERS WHERE TYPE='aderente'";
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            while(resultSet.next()){
                Gson gson = new Gson();
                User user = new User(resultSet.getString("username"), resultSet.getString("nome"),
                        resultSet.getString("cognome"),resultSet.getString("email"),
                        resultSet.getString("tel"),resultSet.getString("type"));
                jsonArray.add(gson.toJson(user)); //riga 49
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        closeConnection();
        return jsonArray;
    }

        public JsonArray extractSimpatizzanti() {
            openConnection();
            JsonArray jsonArray = new JsonArray();

            try{
                String query = "SELECT * FROM USERS WHERE TYPE='simpatizzante'";
                Statement stmt = connection.createStatement();
                ResultSet resultSet = stmt.executeQuery(query);

                while(resultSet.next()){
                    Gson gson = new Gson();
                    User user = new User(resultSet.getString("username"), resultSet.getString("nome"),
                            resultSet.getString("cognome"),resultSet.getString("email"),
                            resultSet.getString("tel"),resultSet.getString("type"));
                    jsonArray.add(gson.toJson(user)); //riga 49
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            closeConnection();
            return jsonArray;
    }

    private void openConnection() {
        if (closed) {
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (ClassNotFoundException | NullPointerException | SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        closed = true;
    }


}
