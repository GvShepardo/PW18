package com.pw18.pw18.api;

import com.pw18.pw18.models.RandomPhraseModel;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/randomPhraseAPI", name ="randomPhraseAPI")
public class RandomPhraseAPI extends HttpServlet {
    RandomPhraseModel randomPhraseModel = new RandomPhraseModel();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String randomPhrase = randomPhraseModel.getRandomPhrase();
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(randomPhrase);
    }


}
