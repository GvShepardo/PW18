package com.pw18.pw18;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet(name = "getDonazioni", value = "/getDonazioni")
public class donazioniAPI extends HttpServlet {

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(closed) {
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (ClassNotFoundException | NullPointerException | SQLException ex) {
                System.out.println(ex);
            }
        }

        try {
            String query = "SELECT MESE, COALESCE(SUM(importo), 0) AS IMPORTI FROM (SELECT CAST(MONTH(DATA)-1 AS INTEGER) AS MESE, SUM(IMPORTO) AS IMPORTO FROM DONAZIONE WHERE YEAR(DATA) = " + year + " GROUP BY DATA) AS SOTTOTABELLA GROUP BY MESE";            /*SELECT                CASE MESE
                    WHEN 1 THEN 'Gennaio'
                    WHEN 2 THEN 'Febbraio'
                    WHEN 3 THEN 'Marzo'
                    WHEN 4 THEN 'Aprile'
                    WHEN 5 THEN 'Maggio'
                    WHEN 6 THEN 'Giugno'
                    WHEN 7 THEN 'Luglio'
                    WHEN 8 THEN 'Agosto'
                    WHEN 9 THEN 'Settembre'
                    WHEN 10 THEN 'Ottobre'
                    WHEN 11 THEN 'Novembre'
                    WHEN 12 THEN 'Dicembre'
                    END AS MESE,
                COALESCE(SUM(importo), 0) AS IMPORTI
            FROM (
                     SELECT
                         MONTH(DATA) AS MESE,
                         SUM(IMPORTO) AS IMPORTO
                     FROM DONAZIONE
                     WHERE YEAR(DATA) = 2023
                     GROUP BY DATA
                 ) AS SOTTOTABELLA
            GROUP BY MESE;*/

            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);


            List<Donazioni> donazioniList = new ArrayList<>();

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

            while (resultSet.next()) {

                int mese = resultSet.getInt("MESE");
                int donazione = resultSet.getInt("IMPORTI");

                donazioniList.set(resultSet.getInt("MESE"), new Donazioni(mesi[mese], donazione));

            }

            JsonArray jsonArray = new JsonArray();

            for(Donazioni donazione : donazioniList){
                Gson gson = new Gson();
                jsonArray.add(gson.toJson(donazione));
            }


            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = response.getWriter();
            System.out.println(jsonArray);
            out.println(jsonArray);
            out.flush();
            connection.close();
            closed = true;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int valore = parseInt(request.getParameter("donazione"));

        if(closed) {
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (ClassNotFoundException | NullPointerException | SQLException ex) {
                System.out.println(ex);
            }
        }

        Cookie[] cookies = request.getCookies();
        String username = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                    break;
                }
            }
        }

        try{
            String query ="INSERT INTO DONAZIONE (USERNAME, IMPORTO, DATA) VALUES ('"+username+"', '"+valore+"', '"+new Timestamp(System.currentTimeMillis())+"')";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
