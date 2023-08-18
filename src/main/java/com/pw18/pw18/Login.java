package com.pw18.pw18;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.UUID;

/*                              PER APRIRE IL DB:
      java -jar C:/Users/iusti/db-derby-10.14.2.0-bin/lib/derbyrun.jar server start

                          Poi, su un secondo terminale:
      java -jar C:/Users/iusti/db-derby-10.14.2.0-bin/lib/derbyrun.jar ij
      connect 'jdbc:derby://localhost:1527/mainDB;create=true';       */

@WebServlet(name = "login", value = "/login")
public class Login extends HttpServlet {

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {

            String query = "SELECT * FROM USERS WHERE USERNAME = '" + username + "' AND PASSWORD = '" + password + "'";
            System.out.println(query);
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            if (resultSet.next()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("username", username);
                session.setMaxInactiveInterval(600);

                Cookie usernameCookie = new Cookie("username", username);
                usernameCookie.setMaxAge(600);
                response.addCookie(usernameCookie);

                response.sendRedirect("home.jsp"); // Change to your secure page's URL
            } else {
                request.getRequestDispatcher("loginError.jsp").forward(request, response);
            }

            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("An error occurred.");
        }
    }
}

