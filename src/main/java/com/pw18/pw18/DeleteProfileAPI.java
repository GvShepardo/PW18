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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "deleteProfile", value = "/deleteProfile")
public class DeleteProfileAPI extends HttpServlet {

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

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        if (closed) {
            try {
                System.out.println("CIAO");
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (ClassNotFoundException | NullPointerException | SQLException ex) {
                System.out.println(ex);
            }
        }


        String targetCookieValue = null;
        try {
            Cookie[] cookies = request.getCookies();

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("username")) {
                        targetCookieValue = cookie.getValue();
                    }
                }
            }
            

            System.out.println(targetCookieValue);

            String query = "DELETE FROM USERATTIVITA WHERE USERNAME = '" + targetCookieValue + "'";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);

            System.out.println("HEY, FUNZI?");

            query = "DELETE FROM USERS WHERE USERNAME = '" + targetCookieValue + "'";
            stmt = connection.createStatement();
            stmt.executeUpdate(query);

            System.out.println("CANCELLATO");

            Cookie deleteCookie = new Cookie("username", "");
            deleteCookie.setMaxAge(0);
            response.addCookie(deleteCookie);

            deleteCookie = new Cookie("type", "");
            deleteCookie.setMaxAge(0);
            response.addCookie(deleteCookie);

            response.setContentType("plain/text");
            PrintWriter out = response.getWriter();
            out.println("Ciao");
            out.flush();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
