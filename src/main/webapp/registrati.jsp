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
<%@include file="header.jsp" %>

<div class="container">
    <div class="content">
        <h2>Registrazione</h2>
        <form method="post">
            <label for="name">Nome</label>
            <input type="text" id="namereg" class="name" name="name" required>

            <label for="surname">Cognome</label>
            <input type="text" class="surname" name="surname" required>

            <label for="birthdate">Data di nascita</label>
            <input type="date" class="birthdate" name="birthdate" required>
            <!-- usato in questo modo date è supportato da tutti i browser non deprecati,
            percui non ho messo altri controlli a differenza di come viene consigliato da https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/date -->

            <label for="email">Email</label>
            <input type="email" class="email" name="email" required>

            <label for="phone">Telefono</label>
            <input type="tel" class="phone" name="phone"
                   pattern="^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$" required>

            <label for="fieldreg">Registrati come</label>
            <fieldset id="fieldreg">
                <div class="radio-group" id="radioreg">
                    <input type="radio" id="sympathizer" name="registration-type" value="simpatizzante" required>
                    <label for="sympathizer">Simpatizzante</label>

                    <input type="radio" id="member" name="registration-type" value="aderente" required>
                    <label for="member">Aderente</label>
                </div>
            </fieldset>

            <label for="username">Username</label>
            <input type="text" class="username" name="username" required>

            <label for="password">Password (min. 8 caratteri, una lettera maiuscola, un carattere numerico e un
                carattere tra $, ! e ?)</label>
            <input type="password" class="password" id="passwordreg" name="password"
                   pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$!?\^%&]).{8,}" required>
            <i id="togglePasswordReg" class="fa fa-eye"></i>
            <label for="confirm-password">Conferma password</label>
            <input type="password" class="confirm-password" id="confirm-passwordreg" name="confirm-password"
                   pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$!?\^%&]).{8,}" required>
            <i id="togglePasswordRegC" class="fa fa-eye"></i>
            <button type="submit">Registrati</button>

            <button type="reset">Reset</button>
        </form>

    </div>
</div>

<script>
    const field = document.getElementById('fieldreg');
    const radio = document.getElementById('radioreg');
    const comp = document.getElementById('namereg');
    const reset = document.querySelector('button[type="reset"]');
    const style = getComputedStyle(comp);
    const defstyle = getComputedStyle(field);
    const color = style.borderColor;
    const defcolor = defstyle.borderColor;
    const password = document.getElementById("passwordreg");
    const confermaPassword = document.getElementById("confirm-passwordreg");
    const submit = document.querySelector("form button[type='submit']");

    //codice corretto da https://www.csestack.org/hide-show-password-eye-icon-html-javascript/
    const togglePassword = document.querySelector('#togglePasswordReg');
    const togglePasswordC = document.querySelector('#togglePasswordRegC');

    togglePassword.addEventListener('click', function (e) {
        // toggle the type attribute
        const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
        password.setAttribute('type', type);
        // toggle the eye slash icon
        this.classList.toggle('fa-eye-slash');
    });

    togglePasswordC.addEventListener('click', function (e) {
        // toggle the type attribute
        const type = confermaPassword.getAttribute('type') === 'password' ? 'text' : 'password';
        confermaPassword.setAttribute('type', type);
        // toggle the eye slash icon
        this.classList.toggle('fa-eye-slash');
    });

    field.style.borderColor = color;


    function check() { 									//controlla che password e conferma password siano identici
        if (password.value === confermaPassword.value) {
            submit.removeAttribute("disabled");
            submit.style.opacity = 1;
            submit.innerHTML = "Registrati";
        } else {
            submit.setAttribute("disabled", true);
            submit.style.opacity = 0.5;
            submit.innerHTML = "Password e Conferma password non corrispondono";
        }
    }

    reset.addEventListener('click', function () {		// quando si preme reset non si attiva l'event listener sotto
        field.style.borderColor = color; 				// uso lo style di un altro elemento per avere lo stile gestito da style.css

    });

    radio.addEventListener('change', (event) => { 		// se viene selezionato un radio il required è soddisfatto
        field.style.borderColor = defcolor;
    });

    password.addEventListener("input", check);
    confermaPassword.addEventListener("input", check);

</script>

<%@include file="footer.jsp" %>
</body>
</html>
