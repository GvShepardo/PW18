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
        <h2 style="text-align: center"> LISTA VISITE</h2>
        <table id="listaVisite">
            <tr>
                <th>PAGINA</th>
                <th>VISITE</th>
            </tr>
        </table>
        <button style="display: flex; align-self: center" onclick="reset()"> RESETTA VISITE </button>
    </div>
</div>

<%@include file="footer.jsp"%>
</body>

<script>

    fetch("GetVisits", {
        method:"GET"
    })
        .then(response => response.json())
        .then(data => {
            var tabella = document.getElementById("listaVisite")
            data.forEach(item => {
                console.log(item)
                var json = JSON.parse(item)
                var riga = document.createElement("tr");
                var pagina = document.createElement("td");
                pagina.textContent = json.pagina;
                var visite = document.createElement("td");
                visite.textContent = json.visite;

                riga.appendChild(pagina);
                riga.appendChild(visite)
                console.log(riga)
                tabella.appendChild(riga)
            })
        })

    function reset(){
        fetch("visite", {
            method: "DELETE",
        })
            .then(location.reload)
    }

</script>


</html>

