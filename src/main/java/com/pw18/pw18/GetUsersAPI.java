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
import java.io.StringWriter;
import java.sql.*;

@WebServlet(name="GetAll", value = "/GetAll")
public class GetUsersAPI extends HttpServlet {

    private static final String DB_URL = "jdbc:derby://localhost:1527/mainDB";
    private static final String DB_USER = "APP";
    private static final String DB_PASSWORD = "password";

    Connection connection = null;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try{
            String query = "SELECT USERNAME FROM USERS";
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            JsonArray jsonArray = new JsonArray();

            while(resultSet.next()){
                Gson gson = new Gson();
                User user = new User(resultSet.getString("username"));
                jsonArray.add(gson.toJson(user)); //riga 49
            }


            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            System.out.println(jsonArray);
            out.println(jsonArray);
            out.flush();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
