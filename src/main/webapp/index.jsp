<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>File Upload</title>
</head>
<body>
<h2>Upload a Text File</h2>
<form action="FileUploadServlet" method="post" enctype="multipart/form-data">
    <input type="file" name="file" accept=".txt" required />
    <div class="g-recaptcha" data-sitekey="6LcREWoqAAAAAFXkJhiqd-vTx6fHpRxDkUwBgcNt">
    </div>
    <input type="submit" value="Upload File" />
</form>
<script src="https://www.google.com/recaptcha/api.js" async defer></script>

<br>
</body>
</html>
