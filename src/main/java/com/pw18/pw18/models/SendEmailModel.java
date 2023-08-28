package com.pw18.pw18.models;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmailModel {

    private static final String DB_URL = "jdbc:derby://localhost:1527/mainDB";
    private static final String DB_USER = "APP";
    private static final String DB_PASSWORD = "password";

    Connection connection = null;
    boolean closed;

    public SendEmailModel() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            closed = false;
        } catch (ClassNotFoundException | NullPointerException | SQLException ex) {
            System.out.println(ex);
        }
    }

    public void send(HttpServletRequest request, HttpServletResponse response, String inputEmail, String body, String reason, String username) throws IOException {

        openConnection();
        try {
            System.out.println(username);
            String selectQuery = "SELECT email FROM USERS WHERE username=?";
            PreparedStatement pstmt = connection.prepareStatement(selectQuery);
            pstmt.setString(1, username);
            ResultSet resultSet = pstmt.executeQuery();
            String email = resultSet.getString("email");
            pstmt.close();
            connection.close();
            closed = true;
            if (!inputEmail.equals(email)) {
                throw new IOException();
            }

            String to = "nonexistent@example.com"; // Indirizzo email del destinatario
            String from = email; // Il tuo indirizzo email
            String host = "smtp.example.com"; // Server SMTP

            // Impostazioni per la sessione email
            Properties properties = System.getProperties();
            properties.setProperty("mail.smtp.host", host);
            Session session = Session.getDefaultInstance(properties);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(reason);
            message.setText(body);

            // Invio del messaggio
            Transport.send(message);
            System.out.println("Email inviata con successo.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Errore durante l'invio dell'email.");
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("confermainvio.jsp");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("email non corrisponde");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email non corrispondente");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore nella connessione al DB");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore");
        }
        closeConnection();
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
