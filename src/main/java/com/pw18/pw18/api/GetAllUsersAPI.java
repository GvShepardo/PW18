package com.pw18.pw18.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.pw18.pw18.dataClasses.User;
import com.pw18.pw18.models.GetAllUsersModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name="getAllUsers", value = "/getAllUsers")
public class GetAllUsersAPI extends HttpServlet {

    GetAllUsersModel getAllUsersModel = new GetAllUsersModel();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String filter = request.getParameter("filter");
        JsonArray jsonArray = new JsonArray();

        if(filter == null){
            jsonArray = getAllUsersModel.extractAll();
        }
        else if(filter == "Aderenti"){
            jsonArray = getAllUsersModel.extractAderenti();
        }
        else if(filter == "Simpatizzanti"){
            jsonArray = getAllUsersModel.extractSimpatizzanti();
        }
        else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            System.out.println(jsonArray);
            out.println(jsonArray);
            out.flush();


    }

}
