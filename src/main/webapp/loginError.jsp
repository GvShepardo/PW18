<!DOCTYPE html>
<html>
<html lang="it">

<script  src="./javascript/Visite.js"></script>

<head>
    <meta charset="utf-8">
    <title>Tum4World</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="icon" type="image/x-icon" href="attivita1.png">
</head>
<body>
<div class="container">
    <%@include file="header.jsp" %>
    <%@include file="cookie.jsp"%>
    <div class="container">
        <!-- avrei potuto riutilizzare il container dell'header ma per rendere le pagine più indipendenti lo ho rimesso qui -->
        <div class="content">
            <h2>INVALID USERNAME OR PASSWORD</h2>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</body>
</html>
