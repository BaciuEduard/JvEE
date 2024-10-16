package com.example.lab1;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/compulsory")
public class compulsory extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the string parameter from the request
        String inputString = request.getParameter("inputString");

        // Set the content type for the response
        response.setContentType("text/html");

        // Get the PrintWriter object to write the HTML response
        PrintWriter out = response.getWriter();

        // Start HTML content
        out.println("<html>");
        out.println("<head><title>Character List</title></head>");
        out.println("<body>");

        if (inputString != null && !inputString.isEmpty()) {
            for (int i = 0; i < inputString.length(); i++) {
                out.println("<li>" + inputString.charAt(i) + "</li>");
            }
        } else {
            out.println("<h2>No input string provided!</h2>");
        }
        out.println("</body>");
        out.println("</html>");
    }
}
