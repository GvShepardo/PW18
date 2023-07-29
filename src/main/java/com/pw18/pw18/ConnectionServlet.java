package com.pw18.pw18;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ConnectionServlet")
public class ConnectionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // I dettagli del database Derby
    private static final String DB_URL = "jdbc:derby://localhost:1527/mainDB";
    private static final String DB_USER = "admin";
    private static final String DB_PASS = "password";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Carica il driver JDBC di Apache Derby
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            // Ottieni una connessione al database
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // Utilizza la connessione per eseguire query SQL
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM YOUR_TABLE_NAME");

            // Processa il ResultSet o esegui altre operazioni sul database

            // Chiudi il ResultSet, Statement e la Connection
            resultSet.close();
            statement.close();
            connection.close();

            // Invia la risposta al client
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Connessione al database Derby avvenuta con successo!</h1>");
            out.println("</body></html>");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
