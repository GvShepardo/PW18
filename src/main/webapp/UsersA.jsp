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
        <h2 style="text-align: center"> UTENTI ADERENTI</h2>
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

<%@include file="footer.jsp"%>
</body>
</html>

<script>
    fetch("GetAllA",{
        method: "GET"
    })
        .then(response => response.json())
        .then(data => {
            var lista = document.getElementById("listaUtenti")
            console.log(data)
            data.forEach(item =>{
                var json = JSON.parse(item)

                var tr = document.createElement("tr");

                var nomeTd = document.createElement("td");
                var cognomeTd = document.createElement("td");
                var usernameTd = document.createElement("td");
                var emailTd = document.createElement("td");
                var cellTd = document.createElement("td");

                nomeTd.textContent = json.nome;
                cognomeTd.textContent = json.cognome;
                usernameTd.textContent = json.username;
                emailTd.textContent = json.email;
                cellTd.textContent = json.cell;

                tr.appendChild(nomeTd)
                tr.appendChild(cognomeTd)
                tr.appendChild(usernameTd)
                tr.appendChild(emailTd)
                tr.appendChild(cellTd)

                document.getElementById("listaUtenti").appendChild(tr)
            })
        })
</script>