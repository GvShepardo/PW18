package com.pw18.pw18.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.pw18.pw18.models.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name="user", value = "/user")
public class UserAPI extends HttpServlet {

    UserModel userModel = new UserModel();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

        JsonArray jsonArray = userModel.extract(username);


            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = response.getWriter();
            System.out.println(jsonArray);
            out.println(jsonArray);
            out.flush();


    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("name");
        String cognome = request.getParameter("surname");
        String dataNascitaStr = request.getParameter("birthdate");
        String email = request.getParameter("email");
        String telefono = request.getParameter("phone");
        String tipoUtente = request.getParameter("registration-type");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confermaPassword = request.getParameter("confirm-password");

        userModel.register(nome, cognome, dataNascitaStr, email, telefono, tipoUtente, username, password, confermaPassword, response);

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = null;

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                }
            }
        }

        userModel.delete(username, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        if(request.getParameter("register") == null)
        {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        userModel.login(username,password,response);}
        else{
            String nome = request.getParameter("name");
            String cognome = request.getParameter("surname");
            String dataNascitaStr = request.getParameter("birthdate");
            String email = request.getParameter("email");
            String telefono = request.getParameter("phone");
            String tipoUtente = request.getParameter("registration-type");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confermaPassword = request.getParameter("confirm-password");

            userModel.register(nome, cognome, dataNascitaStr, email, telefono, tipoUtente, username, password, confermaPassword, response);
        }
    }

}

