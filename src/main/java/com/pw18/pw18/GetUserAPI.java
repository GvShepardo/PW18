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

@WebServlet(name="getUser", value = "/getUser")
public class GetUserAPI extends HttpServlet {
    private static final long serialVersionUID = 1L;
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
        System.out.println(username);
        try{
            String selectQuery = "SELECT username,email,type,tel,nome,cognome FROM USERS WHERE username=?";
            PreparedStatement pstmt = connection.prepareStatement(selectQuery);
            pstmt.setString(1, username);
            ResultSet resultSet = pstmt.executeQuery();

            JsonArray jsonArray = new JsonArray();

            while(resultSet.next()){
                Gson gson = new Gson();
                User user = new User(resultSet.getString("username"), resultSet.getString("nome"),
                                    resultSet.getString("cognome"),resultSet.getString("email"),
                                    resultSet.getString("tel"),resultSet.getString("type"));
                jsonArray.add(gson.toJson(user)); //riga 49
            }


            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = response.getWriter();
            System.out.println(jsonArray);
            out.println(jsonArray);
            out.flush();
            pstmt.close();
            connection.close();
            closed = true;

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("An error occurred.");
        }

    }

}
