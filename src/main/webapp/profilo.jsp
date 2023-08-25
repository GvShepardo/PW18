<!DOCTYPE html>
<html>
<html lang="it">


<head>
    <meta charset="utf-8">
    <title>Tum4World</title>
    <link rel="stylesheet" type="text/css" href="WEB-INF/style.css">
    <link rel="icon" type="image/x-icon" href="../logo.png">
</head>
<body>
<%@include file="alwaysPresentPages/header.jsp"%>
<%@include file="alwaysPresentPages/cookie.jsp"%>

<div class="container">

</div>

<%@include file="alwaysPresentPages/footer.jsp"%>
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

<script  src="javascript/Functions.js"></script>
