<!DOCTYPE html>
<html>
<html lang="it">

<head>
    <meta charset="utf-8">
    <title>Tum4World</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="icon" type="image/x-icon" href="attivita1.png">
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
    <!-- avrei potuto riutilizzare il container dell'header ma per rendere le pagine più indipendenti lo ho rimesso qui -->
    <div class="content">
        <h2>Attività</h2>
        <p>Le attività dell'associazione Tum4World si concentrano su diverse tematiche, tra cui:</p>
        <figure>
            <div class="attivita">
                <a href="attivita1.jsp">
                    <img src="attivita1.png" alt="Attività 1">
                    <figcaption><h3>Sostenibilità ambientale</h3></figcaption>
                </a>
            </div>
        </figure>
        <figure>
            <div class="attivita">
                <a href="attivita2.jsp">
                    <img src="attivita2.png" alt="Attività 2">
                    <figcaption><h3>Giustizia sociale</h3></figcaption>
                </a>
            </div>
        </figure>
        <div class="attivita">
            <a href="attivita3.jsp">
                <img src="attivita3.png" alt="Attività 3">
                <figcaption><h3>Accesso all'istruzione e alle cure mediche</h3></figcaption>
            </a>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
</body>
</html>
