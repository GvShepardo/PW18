package com.pw18.pw18.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.pw18.pw18.dataClasses.Donazioni;
import com.pw18.pw18.models.DonazioniModel;

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
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet(name = "donazioni", value = "/donazioni")
public class DonazioniAPI extends HttpServlet {

    DonazioniModel donazioniModel = new DonazioniModel();


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            JsonArray jsonArray = donazioniModel.extract();

            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = response.getWriter();
            out.println(jsonArray);
            out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int valore = parseInt(request.getParameter("donazione"));

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

        donazioniModel.add(username, valore);
        response.sendRedirect("profilo.jsp");

    }

}
