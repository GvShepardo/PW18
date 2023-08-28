package com.pw18.pw18.api;

import com.pw18.pw18.models.SendEmailModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "SendEmail", value = "/SendEmail")
public class SendEmailAPI extends HttpServlet {
    SendEmailModel sendEmailModel = new SendEmailModel();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String inputEmail = request.getParameter("email");
        String body = request.getParameter("message");
        String reason = request.getParameter("reason");

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

        sendEmailModel.send(request, response, inputEmail, body, reason, username);

    }
}
 
