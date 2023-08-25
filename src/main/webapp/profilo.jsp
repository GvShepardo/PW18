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
<%@include file="header.jsp"%>
<%@include file="cookie.jsp"%>

<div class="container">

</div>

<%@include file="footer.jsp"%>
</body>
</html>

<script>

    function getCookieValue(cookieName) {
        const cookies = document.cookie.split('; ');

        for (let i = 0; i < cookies.length; i++) {
            const cookieParts = cookies[i].split('=');
            const name = decodeURIComponent(cookieParts[0]);
            const value = decodeURIComponent(cookieParts[1]);

            if (name === cookieName) {
                return value;
            }
        }

        return null;
    }

    var cookie = getCookieValue("type");

    if(cookie === "simpatizzante"){
        window.location.href = "Simpatizzante.jsp"
    }
    else if(cookie === "aderente"){
        window.location.href = "Aderente.jsp"
    }
    else if(cookie === "admin"){
        window.location.href = "AdminHeader.jsp"
    }
    else{
        window.location.href = "ERRORE.jsp"
    }

</script>