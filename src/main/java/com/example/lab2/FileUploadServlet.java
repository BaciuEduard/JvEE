package com.example.lab2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@WebServlet("/FileUploadServlet")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {

    private static final String SECRET_KEY = "6LcREWoqAAAAAKFzYHNODA6Sg030B8T_Pbyoq-d-";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String captchaResponse = request.getParameter("g-recaptcha-response");
        boolean isCaptchaValid = verifyCaptcha(captchaResponse);

        if (!isCaptchaValid) {
            response.getWriter().write("CAPTCHA validation failed.");
            return;
        }
        Part filePart = request.getPart("file");
        BufferedReader reader = new BufferedReader(new InputStreamReader(filePart.getInputStream()));
        String line;
        List<String> lines = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        Collections.shuffle(lines); // Shuffle the lines for the response
        request.getSession().setAttribute("lines", lines); // Store lines in session

        // Redirect to the result JSP
        response.sendRedirect("result.jsp");
    }
    private boolean verifyCaptcha(String captchaResponse) throws IOException {
        String url = "https://www.google.com/recaptcha/api/siteverify";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);

        String postParams = "secret=" + SECRET_KEY + "&response=" + captchaResponse;

        // Send post request
        con.getOutputStream().write(postParams.getBytes());

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Parse JSON response
        JSONObject jsonObject = new JSONObject(response.toString());
        return jsonObject.getBoolean("success");
    }

}
