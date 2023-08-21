package com.pw18.pw18;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/RandomPhraseAPI", name ="RandomPhraseAPI")
public class RandomPhraseServlet extends HttpServlet {
    private static final String[] phrases = {
            "Il segreto per ottenere ciò che vuoi è cominciare a farlo.",
            "Ogni giorno è una nuova opportunità per cambiare la tua vita.",
            "Le sfide sono l'opportunità di crescere.",
            "Credi in te stesso e tutto diventerà possibile.",
            "Il successo arriva quando superi le tue paure.",
            "La perseveranza è la chiave del successo.",
            "Non smettere mai di imparare e migliorare.",
            "Fai di oggi un giorno straordinario.",
            "Le difficoltà possono essere superate con determinazione.",
            "Sii la versione migliore di te stesso ogni giorno.",
            "La tua mentalità determina la tua realtà.",
            "I sogni diventano realtà con impegno e lavoro duro.",
            "Lascia che la tua luce interiore brilli.",
            "Le opportunità non arrivano, le crei tu stesso.",
            "Credi nel potere dei tuoi sogni.",
            "Le sfide sono solo occasioni per crescere.",
            "La gratitudine porta abbondanza nella tua vita.",
            "Fai ciò che ami e amerai ciò che fai.",
            "Sii l'energia che vuoi attirare.",
            "Ogni giorno è una pagina bianca, scrivi la tua storia."
    };

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String randomPhrase = getRandomPhrase();
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(randomPhrase);
    }

    private String getRandomPhrase() {
        Random random = new Random();
        int index = random.nextInt(phrases.length);
        return phrases[index];
    }
}
