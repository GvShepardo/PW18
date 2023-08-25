<!DOCTYPE html>
<html>
<html lang="it">
<script src="https://code.highcharts.com/highcharts.js"></script>
<script  src="./javascript/Visite.js"></script>

<head>
    <meta charset="utf-8">
    <title>Tum4World</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="icon" type="image/x-icon" href="attivita1.png">
</head>
<body>

<%@include file="header.jsp" %>
<%@include file="cookie.jsp" %>

<div class="container">
    <%@include file="adminContent.jsp" %>
    <div class="content">
        <h2 style="text-align: center"> DONAZIONI DI QUEST ANNO </h2>
        <div id="columnChart" style="width:100%; height:400px;"></div>
        <div id="pieChart" style="width:100%; height:400px;"></div>
    </div>
</div>

<%@include file="footer.jsp" %>
</body>

<script>

    var mesi = []
    var donazioni = []
    var year = new Date().getFullYear();
    var titolo = "Donazioni " + year
    var i=0;

    fetch("getDonazioni", {
        method: "GET"
    })
        .then(response => response.json())
        .then(data => {
            console.log(data)
            data.forEach(item => {
                var json = JSON.parse(item)
                mesi[i] = json.mese
                donazioni[i] = json.soldi
                i++
            })
            console.log("Mesi" + mesi)
            console.log("Donazioni" + donazioni)

            columnChart.xAxis[0].categories=mesi
            columnChart.redraw()
            columnChart.series[0].setData(donazioni);


            var mappa = mesi
                .map((mese, index) => [mese, donazioni[index]])
                .filter(([mese, donazione]) => donazione !== 0);
            pieChart.series[0].setData(mappa, true)

        })


    const columnChart = Highcharts.chart('columnChart', {
        chart: {
            type: 'column'
        },
        legend:{ enabled:false },
        bar:{
            color: '#46556e'
        },
        title: {
            text: titolo
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

    const pieChart = Highcharts.chart('pieChart', {
        chart: {
            type: 'pie'
        },
        accessibility: {
            point: {
                valueSuffix: '%'
            }
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },

        series: [{

        }]
    });


</script>