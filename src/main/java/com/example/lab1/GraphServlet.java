package com.example.lab1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet("/homework")
public class GraphServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();
        String clientIp = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        String clientLang = request.getHeader("Accept-Language");
        String numVerticesStr = request.getParameter("numVertices");
        String numEdgesStr = request.getParameter("numEdges");

        System.out.println("HTTP Method: " + method);
        System.out.println("Client IP: " + clientIp);
        System.out.println("User-Agent: " + userAgent);
        System.out.println("Client Language(s): " + clientLang);
        System.out.println("Parameters: numVertices = " + numVerticesStr + ", numEdges = " + numEdgesStr);

        int numVertices = Integer.parseInt(numVerticesStr);
        int numEdges = Integer.parseInt(numEdgesStr);
        int[][] adjacencyMatrix = generateAdjacencyMatrix(numVertices, numEdges);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body><h2>Matrix</h2><table border='1'>");

        for (int i = 0; i < numVertices; i++) {
            out.println("<tr>");
            for (int j = 0; j < numVertices; j++) {
                out.println("<td>" + adjacencyMatrix[i][j] + "</td>");
            }
            out.println("</tr>");
        }
        out.println("</table></body></html>");
    }

    private int[][] generateAdjacencyMatrix(int numVertices, int numEdges) {
        int[][] matrix = new int[numVertices][numVertices];
        Random rand = new Random();

        for (int i = 0; i < numEdges; i++) {
            int u = rand.nextInt(numVertices);
            int v = rand.nextInt(numVertices);
            if (u != v && matrix[u][v] == 0) {
                matrix[u][v] = 1;
                matrix[v][u] = 1;
            }
        }
        return matrix;
    }
}
