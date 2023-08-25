<!DOCTYPE html>
<html>
<html lang="it">
<script src="https://code.highcharts.com/highcharts.js"></script>

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

<%@include file="../alwaysPresentPages/footer.jsp"%>
</body>

<script>


    var pagine = [];
    var visita = [];

    fetch("visite", {
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
                visita[i]=json.visite;
                i++;

                riga.appendChild(pagina);
                riga.appendChild(visite)
                console.log(riga)
                tabella.appendChild(riga)
            })

            console.log("Pagine: " + pagine)
            console.log("Visite: " + visita)

            chart.xAxis[0].categories=pagine
            chart.redraw()
            chart.series[0].setData(visita);
        })

        const chart = Highcharts.chart('chart', {
            chart: {
                type: 'column'
            },
            legend:{ enabled:false },
            bar:{
              color: '#46556e'
            },
            title: {
                text: 'Visite Pagine'
            },
            xAxis: {
            },
            yAxis: {
                title: {
                    text: 'visite'
                }
            },
            series: [{
                color: '#333',
            }]
    });

    function reset(){
        fetch("visite", {
            method: "DELETE",
        })
            .then(location.reload())
    }

</script>


</html>

<script  src="../javascript/Functions.js"></script>

