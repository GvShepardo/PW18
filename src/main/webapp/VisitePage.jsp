<!DOCTYPE html>
<html>
<html lang="it">
<script src="https://code.highcharts.com/highcharts.js"></script>




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
        <div id="chart" style="width:100%; height:400px;"></div>
        <button style="display: flex; align-self: center" onclick="reset()"> RESETTA VISITE </button>
    </div>
</div>

<%@include file="footer.jsp"%>
</body>

<script>

    var pagine = [];
    var visite = [];

    fetch("GetVisits", {
        method:"GET"
    })
        .then(response => response.json())
        .then(data => {
            var tabella = document.getElementById("listaVisite")
            var i=0;
            data.forEach(item => {
                console.log(item)
                var json = JSON.parse(item)
                var riga = document.createElement("tr");
                var pagina = document.createElement("td");
                pagina.textContent = json.pagina;
                pagine[i]=json.pagina;
                var visite = document.createElement("td");
                visite.textContent = json.visite;
                visite[i]=json.pagina;
                i++;

                riga.appendChild(pagina);
                riga.appendChild(visite)
                console.log(riga)
                tabella.appendChild(riga)
            })
        })

    document.addEventListener('DOMContentLoaded', function () {
        const chart = Highcharts.chart('chart', {
            chart: {
                type: 'column'
            },
            bar:{
              color: '#46556e'
            },
            title: {
                text: 'Visite Pagine'
            },
            xAxis: {
                //categories: ["home", "profilo", "a", "b", "c", "d", "e", "a", "a", "a", "a"] //PER TEST
                categories: pagine
            },
            yAxis: {
                title: {
                    text: 'visite'
                }
            },
            series: [{
                //data: [3,7,1,2,3,4,5,6,7,8,9],   //PER TEST
                color: '#333',
                data: visite
            }]
        });
    });

    function reset(){
        fetch("visite", {
            method: "DELETE",
        })
            .then(location.reload)
    }

</script>


</html>

