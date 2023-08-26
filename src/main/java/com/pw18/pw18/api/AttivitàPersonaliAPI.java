package com.pw18.pw18.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.pw18.pw18.dataClasses.Attivita;
import com.pw18.pw18.models.AttivitàPersonaliModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name="attivitaPersonali", value = "/attivitaPersonali")
public class AttivitàPersonaliAPI extends HttpServlet {

    AttivitàPersonaliModel attivitàPersonaliModel = new AttivitàPersonaliModel();

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String attivita = request.getParameter("attivita");

        attivitàPersonaliModel.delete(username, attivita);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String attivita = request.getParameter("attivita");

        attivitàPersonaliModel.add(username, attivita);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");

        JsonArray jsonArray = attivitàPersonaliModel.extract(username);

            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            System.out.println(jsonArray);
            out.println(jsonArray);
            out.flush();

    }

}
