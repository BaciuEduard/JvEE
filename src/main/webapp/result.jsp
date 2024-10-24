<!-- result.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Collections" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Shuffled Lines</title>
</head>
<body>
<h2>Shuffled File Lines</h2>
<ul>
  <%
    List<String> lines = (List<String>) session.getAttribute("fileLines");

    if (lines != null && !lines.isEmpty()) {
      Collections.shuffle(lines);

      for (String line : lines) {
  %>
  <li><%= line %></li>
  <%
    }
  } else {
  %>
  <p>No file uploaded or file is empty.</p>
  <%
    }
  %>
</ul>
</body>
</html>
