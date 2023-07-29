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
			<h2>Contatti</h2>
			<p>Indirizzo: Via Nome della Via, 123 - 18000 Città (NA)</p>
			<p>Telefono: +39 312 345 6789</p>
			<h3>Inviaci un messaggio</h3>
			<form class="contact-form" method="post">	<!-- dato che nel testo non era specificato (a differenza della pagina sign-in) ho messo come required solo la e-mail -->
				<label for="email">Indirizzo email</label>
				<input type="email" class="email" name="email" required> 	<!-- questo tipo controlla in automatico se è una mail valida -->

				<label for="reason">Motivo di contatto</label>
				<select id="reason" name="reason" >
					<option value="" disabled selected>Scegli una opzione</option>
					<option value="Volontariato">Volontariato</option>
					<option value="Collaborazione">Collaborazione</option>
					<option value="Richiesta informazioni">Richiesta informazioni</option>
					<option value="Altro">Altro</option>
				</select>
				
				<textarea id="Altra-Ragione" name="Altra-Ragione" placeholder="Descrivi perché ci contatti"></textarea>


				<label for="message">Messaggio</label>
				<textarea class="message" name="message" rows="5" ></textarea>

				<button type="submit">Invia</button>
				<button type="reset">Reset</button>
			</form>
			<script>
				const selectElement = document.getElementById('reason');
				const altro = document.getElementById('Altra-Ragione');
				const resetButton = document.querySelector('button[type="reset"]');
				
				altro.style.display = 'none';

				resetButton.addEventListener('click', function() { 	// quando si preme reset non si attiva l'event listener sotto
					altro.style.display = 'none'; 					//questo serve a nascondere Altra-Ragione quando si resetta il form
				});


				selectElement.addEventListener('change', (event) => { //logica della combobox, con questo metodo dovrebbe funzionare per tutti i browser
					if (event.target.value === 'Altro') {
						altro.style.display = 'block';
					} else {
						altro.style.display = 'none';
					}
				});
			</script>
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
