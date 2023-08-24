package com.pw18.pw18;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name="attivitaPersonali", value = "/attivitaPersonali")
public class AttivitàPersonaliAPI extends HttpServlet {

    private static final String DB_URL = "jdbc:derby://localhost:1527/mainDB";
    private static final String DB_USER = "APP";
    private static final String DB_PASSWORD = "password";

    Connection connection = null;

    boolean closed;

    @Override
    public void init() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            closed = false;
        } catch (ClassNotFoundException | NullPointerException | SQLException ex) {
            System.out.println(ex);
        }
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String attivita = request.getParameter("attivita");

        if(closed) {
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (ClassNotFoundException | NullPointerException | SQLException ex) {
                System.out.println(ex);
            }
        }

        try{
            String query = "DELETE FROM USERATTIVITA WHERE USERNAME = '" + username + "' AND ATTIVITA = '" + attivita + "'";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);

            closed = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String attivita = request.getParameter("attivita");

        if(closed) {
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (ClassNotFoundException | NullPointerException | SQLException ex) {
                System.out.println(ex);
            }
        }

        try{
            String query = "INSERT INTO USERATTIVITA (USERNAME, ATTIVITA) VALUES ('"+ username +"', '"+ attivita +"')";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);

            closed = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");

        if(closed) {
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (ClassNotFoundException | NullPointerException | SQLException ex) {
                System.out.println(ex);
            }
        }

        try{
            String query = "SELECT * FROM USERATTIVITA WHERE USERNAME='"+ username + "'";
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            JsonArray jsonArray = new JsonArray();

            while(resultSet.next()){
                Gson gson = new Gson();
                Attivita attivita = new Attivita(resultSet.getString("USERNAME"), resultSet.getString("ATTIVITA"));
                jsonArray.add(gson.toJson(attivita));
            }


            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            System.out.println(jsonArray);
            out.println(jsonArray);
            out.flush();
            closed = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
