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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
@WebServlet(name="GetVisits", value="/GetVisits")
public class GetVisiteAPI extends HttpServlet {

    private static final String DB_URL = "jdbc:derby://localhost:1527/mainDB";
    private static final String DB_USER = "APP";
    private static final String DB_PASSWORD = "password";

    Connection connection;
    @Override
    public void init() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | NullPointerException | SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try{

            String query = "SELECT * FROM VISITE";
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            JsonArray jsonArray = new JsonArray();

            while(resultSet.next()){
                Visita visita = new Visita(resultSet.getString("PAGINA"), resultSet.getInt("COUNTER"));
                Gson gson = new Gson();
                jsonArray.add(gson.toJson(visita));
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            System.out.println(jsonArray.toString());
            PrintWriter out = response.getWriter();
            out.println(jsonArray);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
