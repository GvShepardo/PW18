package com.pw18.pw18.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.pw18.pw18.dataClasses.Visita;
import com.pw18.pw18.models.VisiteModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "visite", value = "/visite")
public class VisiteAPI extends HttpServlet {

    VisiteModel visiteModel = new VisiteModel();

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String columnName = request.getReader().readLine();
        visiteModel.update(columnName);

    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        visiteModel.reset();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
            JsonArray jsonArray = visiteModel.extract();

            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            System.out.println(jsonArray.toString());
            PrintWriter out = response.getWriter();
            out.println(jsonArray);

    }
}
