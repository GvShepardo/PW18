<!DOCTYPE html>
<html>
<html lang="it">


<head>
	<meta charset="utf-8">
	<title>Tum4World</title>
	<link rel="stylesheet" type="text/css" href="style.css" id="stylesheet">
	<link rel="icon" type="image/x-icon" href="attivita1.png">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<%@include file="header.jsp"%>
<%@include file="cookie.jsp"%>
    <div class="container">
		<div class="content">
			<h2>Login</h2>
			<form action="login" method="post">
				<label for="username">Username</label>
				<input type="text" id="username" class="username" name="username" required>
				<label for="password">Password</label>
				<input type="password" id="password" class="password" name="password" required>
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
<%@include file="footer.jsp"%>
</body>
</html>

<script  src="javascript/Functions.js"></script>
<script>
	updateVisite("LogIn")
</script>
