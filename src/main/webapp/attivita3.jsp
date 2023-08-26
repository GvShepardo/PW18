<!DOCTYPE html>
<html>
<html lang="it">


<head>
    <meta charset="utf-8">
    <title>Tum4World</title>
    <link rel="stylesheet" type="text/css" href="style/style.css" id="stylesheet">
    <link rel="icon" type="image/x-icon" href="logo.png">
</head>
<body>
<%@include file="header.jsp" %>
<%@include file="cookie.jsp"%>
<div class="container">
    <!-- avrei potuto riutilizzare il container dell'header ma per rendere le pagine più indipendenti lo ho rimesso qui -->
    <!-- da formattare meglio -->
    <div class="content">
        <h2>Accesso all'istruzione e alle cure mediche</h2>
        <p>Tum4World promuove l'accesso all'istruzione e alle cure mediche per tutti, senza discriminazioni.
            L'associazione organizza progetti e iniziative sul territorio, in collaborazione con organizzazioni e
            istituzioni locali, per garantire a tutti l'accesso a un'istruzione di qualità e alle cure mediche. Inoltre,
            Tum4World promuove la sensibilizzazione sulla importanza dell'accesso ai servizi essenziali, coinvolgendo la
            comunità locale. L'obiettivo è quello di creare una società più giusta e inclusiva, dove ogni individuo
            possa sviluppare le proprie potenzialità.</p>
        <img src="freeEducation.png" alt="Attività 3" class="activity-image">
        <p>L'attività di Tum4World per l'accesso all'istruzione e alle cure mediche mira a promuovere l'uguaglianza
            nell'accesso ai servizi essenziali. L'obiettivo è quello di garantire a tutti l'accesso a un'istruzione di
            qualità e alle cure mediche, senza discriminazioni di alcun tipo.

            Per raggiungere questo obiettivo, l'associazione organizza progetti e iniziative sul territorio, in
            collaborazione con organizzazioni e istituzioni locali. Tra le attività principali ci sono: la promozione
            dell'educazione e della formazione, la costruzione e il sostegno di scuole e centri di formazione, la
            promozione dell'accesso alle cure mediche e ai servizi sanitari.

            Inoltre, Tum4World organizza eventi e campagne di sensibilizzazione per promuovere l'importanza dell'accesso
            all'istruzione e alle cure mediche, coinvolgendo la comunità locale e diffondendo la cultura
            dell'uguaglianza e dell'inclusione. L'obiettivo è quello di creare una società più giusta e solidale, dove
            ogni individuo abbia accesso ai servizi essenziali e possa sviluppare le proprie potenzialità.</p>
    </div>
</div>
<!-- sotto qui sarebbe tutto footer.html -->

<%@include file="footer.jsp" %>
</body>
</html>

<script  src="javascript/Functions.js"></script>
<script>
    updateVisite("Attivita3")
</script>
