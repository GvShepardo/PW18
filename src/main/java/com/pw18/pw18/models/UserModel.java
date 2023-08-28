package com.pw18.pw18.models;

import com.google.gson.Gson;
import com.google.gson.JsonArray;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserModel {
    private static final String DB_URL = "jdbc:derby://localhost:1527/mainDB";
    private static final String DB_USER = "APP";
    private static final String DB_PASSWORD = "password";

    Connection connection = null;
    boolean closed;

    public UserModel() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            closed = false;
        } catch (ClassNotFoundException | NullPointerException | SQLException ex) {
            System.out.println(ex);
        }
    }

    public void login(String username, String password, HttpServletResponse response) throws IOException {

        openConnection();
        System.out.println("CiAO");
        if(username.equals("18Adm1n") && password.equals("18Adm1n")){
            Cookie usernameCookie = new Cookie("username", "admin");
            usernameCookie.setMaxAge(600);
            response.addCookie(usernameCookie);

            Cookie typeOfProfileCookie = new Cookie("type", "admin");
            typeOfProfileCookie.setMaxAge(600);
            response.addCookie(typeOfProfileCookie);

            response.sendRedirect("home.jsp");

            System.out.println("CiAO a");
        }
        else{
        try {
            String query = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Cookie usernameCookie = new Cookie("username", resultSet.getString("username"));
                usernameCookie.setMaxAge(600);
                response.addCookie(usernameCookie);

                Cookie typeOfProfileCookie = new Cookie("type", resultSet.getString("type"));
                typeOfProfileCookie.setMaxAge(600);
                response.addCookie(typeOfProfileCookie);

                response.sendRedirect("home.jsp"); // Change to your secure page's URL
            } else {
                response.sendRedirect("loginError.jsp");
            }
            resultSet.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        closeConnection();}
        System.out.println("CiAO b");

    }

    public void register(String nome, String cognome, String dataNascitaStr, String email, String telefono,
                         String tipoUtente, String username, String password, String confermaPassword, HttpServletResponse response) throws IOException {


        try {


            if (!isValidDOB(dataNascitaStr) || !isValidEmail(email) || !isValidPhoneNumber(telefono) || !isValidPassword(password, confermaPassword)
                    || !isValidType(tipoUtente)) {
                throw new IOException();
            }
            Date dataNascita;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dataNascita = sdf.parse(dataNascitaStr);

            // Prepara l'inserimento dei dati nella tabella "USERS"
            String insertQuery = "INSERT INTO USERS (USERNAME, EMAIL, PASSWORD, TYPE, NOME, COGNOME, TEL, DOB) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = connection.prepareStatement(insertQuery);
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setString(4, tipoUtente);
            pstmt.setString(5, nome);
            pstmt.setString(6, cognome);
            pstmt.setString(7, telefono);
            pstmt.setTimestamp(8, new java.sql.Timestamp(dataNascita.getTime()));

            // Esegui l'inserimento
            pstmt.executeUpdate();
            pstmt.close();
            closed=true;
            // Invia una risposta di successo al frontend

            Cookie usernameCookie = new Cookie("username", username);
            usernameCookie.setMaxAge(600);
            response.addCookie(usernameCookie);

            Cookie typeOfProfileCookie = new Cookie("type", tipoUtente);
            typeOfProfileCookie.setMaxAge(600);
            response.addCookie(typeOfProfileCookie);

            response.setStatus(HttpServletResponse.SC_OK);

            response.sendRedirect("RegisterConfirm.jsp"); // Change to your secure page's URL
        } catch (SQLException e) {
            e.printStackTrace();
            if(e.getSQLState().equals("23505")) {
                response. sendError(HttpServletResponse.SC_BAD_REQUEST, "18: Username taken");
            }
            else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore DB.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore nell'input.");
        } catch (ParseException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato data di nascita non valido.");
        } catch (NullPointerException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to connect to DB");
        }


    }

    public void delete(String username, HttpServletResponse response){

        openConnection();

        try{
            String query = "DELETE FROM USERATTIVITA WHERE USERNAME = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();

            query = "DELETE FROM USERS WHERE USERNAME = ? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();


            Cookie deleteCookie = new Cookie("username", "");
            deleteCookie.setMaxAge(0);
            response.addCookie(deleteCookie);

            deleteCookie = new Cookie("type", "");
            deleteCookie.setMaxAge(0);
            response.addCookie(deleteCookie);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        closeConnection();

    }

    public JsonArray extract(String username){

        openConnection();


        JsonArray jsonArray = new com.google.gson.JsonArray();
        try {
            String selectQuery = "SELECT username,email,type,tel,nome,cognome FROM USERS WHERE username=?";
            PreparedStatement pstmt = connection.prepareStatement(selectQuery);
            pstmt.setString(1, username);
            ResultSet resultSet = pstmt.executeQuery();



            while (resultSet.next()) {
                Gson gson = new Gson();
                com.pw18.pw18.dataClasses.User user = new com.pw18.pw18.dataClasses.User(resultSet.getString("username"), resultSet.getString("nome"),
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

        private void openConnection(){
            if(closed){
                try {
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                    connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                } catch (ClassNotFoundException | NullPointerException | SQLException ex) {
                    System.out.println(ex);
                }
            }
        }

        private void closeConnection(){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            closed = true;
        }






























    private boolean isValidDOB(String dateOfBirthStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateOfBirth = sdf.parse(dateOfBirthStr);

            // Converte la data di nascita in LocalDate
            LocalDate dobLocalDate = dateOfBirth.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

            // Ottiene la data odierna
            LocalDate currentDate = LocalDate.now();

            // Calcola la differenza di anni tra la data odierna e la data di nascita
            int age = Period.between(dobLocalDate, currentDate).getYears();

            // Verifica se l'utente ha almeno 18 anni
            return age >= 18;
        } catch (ParseException e) {
            // Se la data di nascita non è nel formato corretto, considerala non valida
            return false;
        }
    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Metodo per il controllo del numero di telefono utilizzando espressioni regolari
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Il pattern specificato accetta numeri di telefono con 10 cifre (senza caratteri speciali)
        String phoneRegex = "^[0-9]{10}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    // Metodo per il controllo della password utilizzando espressioni regolari
    private boolean isValidPassword(String password,String conferma) {
        if(!password.equals(conferma)) return false;
        // La password deve essere lunga almeno 8 caratteri e contenere almeno 2 'N', 1 'I', almeno un numero e almeno un carattere maiuscolo.
        String passwordRegex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[N]{2})(?=.*[I])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    // Metodo per il controllo del campo "registration-type"
    private boolean isValidType(String registrationType) {
        // Verifica se il valore ricevuto è "aderente" o "simpatizzante"
        return registrationType.equals("aderente") || registrationType.equals("simpatizzante");
    }

}
