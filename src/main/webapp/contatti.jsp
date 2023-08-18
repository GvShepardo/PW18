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
	<div class="container">	<!-- avrei potuto riutilizzare il container dell'header ma per rendere le pagine più indipendenti lo ho rimesso qui -->
		<div class="content">
			<h2>Contatti</h2>
			<p>Indirizzo: Via Nome della Via, 123 - 18000 Città (NA)</p>
			<p>Telefono: +39 312 345 6789</p>
			<h3>Inviaci un messaggio</h3>
			<form class="contact-form" method="post">	<!-- dato che nel testo non era specificato (a differenza della pagina sign-in) ho messo come required solo la e-mail -->
				<label for="emailreg">Indirizzo email</label>
				<input type="email" class="email" name="email" id="emailreg" required> 	<!-- questo tipo controlla in automatico se è una mail valida -->

				<label for="reason">Motivo di contatto</label>
				<select id="reason" name="reason" >
					<option value="" disabled selected>Scegli una opzione</option>
					<option value="Volontariato">Volontariato</option>
					<option value="Collaborazione">Collaborazione</option>
					<option value="Richiesta informazioni">Richiesta informazioni</option>
					<option value="Altro">Altro</option>
				</select>
				
				<textarea id="Altra-Ragione" name="Altra-Ragione" placeholder="Descrivi perché ci contatti"></textarea>


				<label for="messagereg">Messaggio</label>
				<textarea class="message" name="message" rows="5" id="messagereg" ></textarea>

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
<%@include file="footer.jsp"%>
</body>
</html>
