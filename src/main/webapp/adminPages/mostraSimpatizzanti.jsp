<!DOCTYPE html>
<html>
<html lang="it">

<head>
    <meta charset="utf-8">
    <title>Tum4World</title>
    <link rel="stylesheet" type="text/css" href="../WEB-INF/style.css">
    <link rel="icon" type="image/x-icon" href="../logo.png">
</head>
<body>
<%@include file="../alwaysPresentPages/header.jsp"%>
<%@include file="../alwaysPresentPages/cookie.jsp"%>

<div class="container">
    <%@include file="adminContent.jsp"%>
    <div class="content">
        <h2 style="text-align: center"> UTENTI SIMPATIZZANTI</h2>
        <table id="listaUtenti">
            <tr>
                <th> NOME</th>
                <th> COGNOME</th>
                <th> USERNAME</th>
                <th> EMAIL</th>
                <th> CELL</th>
            </tr>
        </table>
    </div>
</div>

<%@include file="../alwaysPresentPages/footer.jsp"%>
</body>
</html>

<script>

    const valori = new URLSearchParams();
    valori.append('filter', "Simpatizzanti");

    fetch("getAllUsers?" + valori,{
        method: "GET"
    })
        .then(response => response.json())
        .then(data => {
            var lista = document.getElementById("listaUtenti")
            data.forEach(item =>{
                var json = JSON.parse(item)

                var tr = document.createElement("tr");

                var nomeTd = document.createElement("td");
                var cognomeTd = document.createElement("td");
                var usernameTd = document.createElement("td");
                var emailTd = document.createElement("td");
                var telTd = document.createElement("td");

                nomeTd.textContent = json.nome;
                cognomeTd.textContent = json.cognome;
                usernameTd.textContent = json.username;
                emailTd.textContent = json.email;
                telTd.textContent = json.tel;

                tr.appendChild(nomeTd)
                tr.appendChild(cognomeTd)
                tr.appendChild(usernameTd)
                tr.appendChild(emailTd)
                tr.appendChild(telTd)

                document.getElementById("listaUtenti").appendChild(tr)
            })
        })
</script>

<script  src="../javascript/Functions.js"></script>