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
    <div class="content">
        <!-- da formattare meglio -->
        <h2>Giustizia sociale</h2>
        <p>Tum4World si impegna per la giustizia sociale, promuovendo l'uguaglianza e la solidarietà. L'associazione
            organizza progetti e iniziative per combattere la discriminazione, l'esclusione sociale e difendere i
            diritti umani e delle minoranze. L'obiettivo è creare una società più inclusiva e giusta, attraverso eventi
            e campagne di sensibilizzazione per coinvolgere la comunità locale.</p>
        <img src="socialJustice.png" alt="Attività 2" class="activity-image">
        <p>L'attività di Tum4World per la giustizia sociale si concentra sulla promozione dell'uguaglianza e della
            giustizia per tutti, combattendo la discriminazione e le disuguaglianze sociali. L'obiettivo è quello di
            creare una società più inclusiva e solidale, dove ogni individuo possa godere degli stessi diritti e
            opportunità.

            Per raggiungere questo obiettivo, l'associazione organizza progetti e iniziative sul territorio, in
            collaborazione con organizzazioni e istituzioni locali. Tra le attività principali ci sono: la promozione
            dell'educazione e della formazione per tutti, la lotta alla povertà e all'esclusione sociale, la difesa dei
            diritti umani e delle minoranze, la promozione dell'accesso alle cure mediche e ai servizi essenziali.

            Inoltre, Tum4World organizza eventi e campagne di sensibilizzazione per promuovere la giustizia sociale e la
            solidarietà, coinvolgendo attivamente la comunità locale. L'obiettivo è quello di promuovere un cambiamento
            culturale che conduca ad una società più giusta ed equa, dove ogni individuo possa vivere dignitosamente e
            senza discriminazioni.</p>
    </div>
</div>
<!-- sotto qui sarebbe tutto footer.html -->

<%@include file="footer.jsp" %>
</body>
</html>

<script  src="javascript/Functions.js"></script>
<script>
    updateVisite("Attivita2")
</script>
