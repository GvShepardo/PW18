
<head>
	<meta charset="utf-8">
	<title>Tum4World</title>
	<link rel="stylesheet" type="text/css" href="style/style.css" id="stylesheet">
	<link rel="icon" type="image/x-icon" href="logo.png">
</head>
<body>
<%@include file="header.jsp"%>
<%@include file="cookie.jsp"%>
		<!-- sopra qui sarebbe tutto header.html -->
	<div class="container">	<!-- avrei potuto riutilizzare il container dell'header ma per rendere le pagine più indipendenti lo ho rimesso qui -->
		<div class="content">
			<h2>Benvenuti in Tum4World</h2>
			<p>Tum4World è un'associazione no-profit che si dedica alla promozione della conoscenza e della consapevolezza dei problemi globali, con l'obbiettivo di promuovere un cambiamento positivo nel mondo. Nonostante il nome possa far pensare alla più nota associazione Tumaini, si tratta di un'organizzazione totalmente diversa, con finalità e obbiettivi distinti.</p>
			<p>Il nostro lavoro si concentra su diverse aree tematiche, tra cui la sostenibilità ambientale, la giustizia sociale, l'uguaglianza di genere e l'accesso all'istruzione e alle cure mediche. Ci impegniamo a sensibilizzare la società civile sui problemi globali e a promuovere azioni concrete per affrontarli.</p>
			<p>Per raggiungere i nostri obbiettivi, ci affidiamo all'aiuto di volontari appassionati e competenti, che mettono a disposizione le proprie competenze e il proprio tempo per sostenere la nostra attività. Organizziamo eventi, convegni, campagne di sensibilizzazione e progetti sul territorio, coinvolgendo attivamente la comunità locale.</p>
			<p>Siamo convinti che solo attraverso l'impegno comune e la collaborazione possiamo sperare di fare la differenza nel mondo. Se anche tu sei interessato a sostenere la nostra causa e vuoi contribuire al cambiamento, unisciti a noi e diventa parte della nostra comunità di volontari impegnati!</p>

			<img src="logo.png" alt="Tum4World Immagine Rappresentativa">
			<div class="button">
				<a href="volantino.pdf" download>Scarica il volantino</a>
			</div>
		</div>
	</div>
<%@include file="footer.jsp"%>


</body>

<script  src="javascript/Functions.js"></script>
<script>
	updateVisite("Home")
</script>


