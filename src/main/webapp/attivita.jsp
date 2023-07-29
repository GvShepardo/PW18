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
	<div class="container">
		<header>
			<div class="logo">
				<img src="attivita1.png" alt="Tum4World Logo">
			</div>
			<h1>Tum4World</h1>
		</header>
		
		<ul class="menu">
			<li><a href="home.jsp">Home</a></li>
			<li><a href="chisiamo.jsp">Chi siamo</a></li>
			<li><a href="attivita.jsp">Attività</a></li>
			<li><a href="contatti.jsp">Contatti</a></li>
			<li><a href="registrati.jsp">Sign-in</a></li>
			<li><a href="login.jsp">Login</a></li>
		</ul>
	</div>
		<!-- sopra qui sarebbe tutto header.html -->
	<div class="container">	<!-- avrei potuto riutilizzare il container dell'header ma per rendere le pagine più indipendenti lo ho rimesso qui -->
		<div class="content">
			<h2>Attività</h2>
			<p>Le attività dell'associazione Tum4World si concentrano su diverse tematiche, tra cui:</p>
			<figure>
				<div class="attivita">
					<a href="attivita1.html">
						<img src="attivita1.png" alt="Attività 1">
						<figcaption><h3>Sostenibilità ambientale</h3></figcaption>
					</a>
				</div>
			</figure>
			<figure>
				<div class="attivita">
					<a href="attivita2.html">
						<img src="attivita2.png" alt="Attività 2">
						<figcaption><h3>Giustizia sociale</h3></figcaption>
					</a>
				</div>
			</figure>
				<div class="attivita">
					<a href="attivita3.html">
						<img src="attivita3.png" alt="Attività 3">
						<figcaption><h3>Accesso all'istruzione e alle cure mediche</h3></figcaption>
					</a>
				</div>
		</div>
	</div>
	<!-- sotto qui sarebbe tutto footer.html -->
	
	<footer>
		<div class="footer">
			<p>Tum4World</p>
			<p>Via Nome della Via, 123</p>
			<p>18000 Città (NA)</p>
			<p>Italia</p>
		</div>
	</footer>
	</body>
</html>
