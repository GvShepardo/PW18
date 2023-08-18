package com.pw18.pw18;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "register", value = "/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Ottieni la connessione al database dal ServletContext

        try (Connection connection = ConnectionManager.getConnection()) {
            String nome = request.getParameter("name");
            String cognome = request.getParameter("surname");
            String dataNascitaStr = request.getParameter("birthdate");
            String email = request.getParameter("email");
            String telefono = request.getParameter("phone");
            String tipoUtente = request.getParameter("registration-type");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confermaPassword = request.getParameter("confirm-password");
            // Altri controlli e operazioni rimangono invariati
            // ...
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

            // Invia una risposta di successo al frontend

            Cookie usernameCookie = new Cookie("username", username);
            usernameCookie.setMaxAge(600);
            response.addCookie(usernameCookie);
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("home.jsp"); // Change to your secure page's URL
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore DB.");
        } catch (IOException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore nell'input.");
        } catch (ParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato data di nascita non valido.");
        } catch (NullPointerException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to connect to DB");
        }
    }

    // Metodi di validazione, ecc. (se necessario) possono essere aggiunti qui.
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
        // La password deve essere lunga almeno 8 caratteri e contenere almeno 3 'N', 1 'I', almeno un numero e almeno un carattere maiuscolo.
        String passwordRegex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[N]{3})(?=.*[I])(?=\\S+$).{8,}$";
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
