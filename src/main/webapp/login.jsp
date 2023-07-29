<!DOCTYPE html>
<html>
<html lang="it">

<head>
	<meta charset="utf-8">
	<title>Tum4World</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	<link rel="icon" type="image/x-icon" href="attivita1.png">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
	<!-- sopra qui sarebbe tutto headerpass.html -->
	
    <div class="container">
		<div class="content">
			<h2>Login</h2>
			<form method="post">
				<label for="username">Username</label>
				<input type="text" class="username" name="username" required>
				<label for="password">Password</label>
				<input type="password" class="password" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$!?\^%&]).{8,}" required>
				<i id="togglePasswordLog" class="fa fa-eye"></i>
				<button type="submit">Entra</button>
			</form>
		
		</div>
    </div>
	<script>
		//codice corretto da https://www.csestack.org/hide-show-password-eye-icon-html-javascript/ 
		const togglePassword = document.querySelector('#togglePasswordLog');
		const password = document.querySelector('.password');

		togglePassword.addEventListener('click', function (e) {
			// toggle the type attribute
			const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
			password.setAttribute('type', type);
			// toggle the eye slash icon
			this.classList.toggle('fa-eye-slash');
		});
	</script>
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
