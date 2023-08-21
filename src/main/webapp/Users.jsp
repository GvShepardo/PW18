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
<%@include file="header.jsp"%>
<%@include file="cookie.jsp"%>

<div class="container">
    <%@include file="adminContent.jsp"%>
    <div class="content">
    <h2 style="text-align: center"> UTENTI REGISTRATI</h2>
    <ul id="listaUtenti">
    </ul>
    </div>
</div>

<%@include file="footer.jsp"%>
</body>
</html>

<script>
    fetch("GetAll",{
        method: "GET"
    })
    .then(response => response.json())
    .then(data => {
        var lista = document.getElementById("listaUtenti")
        console.log(data)
    data.forEach(item =>{
        var json = JSON.parse(item)
            var newLi = document.createElement("li");
            newLi.textContent = json.username;
            lista.appendChild(newLi);
    })
    })
</script>