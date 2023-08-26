<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="body">
    <h1>Dettagli Utente</h1>
    <div class="content">
    <div>
        <ul>
            <label for="nome">Nome:</label>
            <strong><li id="nome"></li></strong>
            .<label for="cognome">Cognome:</label>
            <strong><li id="cognome"></li></strong>
            <label for="username">Username:</label>
            <strong><li id="username"></li></strong>
            <label for="email">Email:</label>
            <strong><li id="email"></li></strong>
            <label for="telefono">Telefono:</label>
            <strong><li id="telefono"></li></strong>

        </ul>
    </div>
    <div class="container" style="text-align: center">
        <div class="content" style="display: inline-block">
            <div class="attivitaProfilo" >
                <h2 style="font-size: 10px">SENSIBILITA</h2>
                <img src="sostenibilita.png" alt="Immagine 1">
                <button id="attivita1" onclick="changeStatusAttivita('attivita1')" class="attivitaPulsante">ISCRIVITI</button>
            </div>

            <div class="attivitaProfilo">
                <h2 style="font-size: 10px">GIUSTIZIA SOCIALE</h2>
                <img src="socialJustice.png" alt="Immagine 2">
                <button id="attivita2" onclick="changeStatusAttivita('attivita2')" class="attivitaPulsante">ISCRIVITI</button>
            </div>

            <div class="attivitaProfilo">
                <h2 style="font-size: 10px">EDUCAZIONE E OSPEDALIZZAZIONE GRATUITA </h2>
                <img src="freeEducation.png" alt="Immagine 3">
                <button id="attivita3" onclick="changeStatusAttivita('attivita3')" class="attivitaPulsante">ISCRIVITI</button>
            </div>
        </div>
    </div>

    <button class="green-button final-button" onclick="deleteUser()"> CANCELLA PROFILO </button>
</div>
</div>
</body>
</html>

<script>

    document.getElementById("attivita1").status = "disicritto"
    document.getElementById("attivita2").status = "disicritto"
    document.getElementById("attivita3").status = "disicritto"


    console.log(document.getElementById("attivita1").innerHTML)

    const cookies = document.cookie.split('; ');
    var username

    for (const cookie of cookies) {
        const [name, value] = cookie.split('=');
        if (name === "username") {
            username =  decodeURIComponent(value);
        }
    }

    fetch("user",{
        method: "GET"
    })
        .then(response => response.json())
        .then(data => {
            var nome = document.getElementById("nome");
            var cognome = document.getElementById("cognome");
            var username = document.getElementById("username");
            var email = document.getElementById("email");
            var telefono = document.getElementById("telefono");

            console.log(data)
            data.forEach(item =>{
                var json = JSON.parse(item)
                nome.textContent = json.nome;
                cognome.textContent = json.cognome;
                username.textContent = json.username;
                email.textContent = json.email;
                telefono.textContent = json.tel;
            })
        })


    function deleteUser(){
        fetch("user", {
            method: "DELETE"
        })
            .then(window.location.href = "home.jsp")
    }

    function changeStatusAttivita(nomeAttivita){


        const valori = new URLSearchParams();
        valori.append('username', username);
        valori.append('attivita', nomeAttivita);

        var thisAttivita = document.getElementById(nomeAttivita)
        console.log(thisAttivita.status)

        if(thisAttivita.status === "iscritto"){
            fetch("attivitaPersonali?" + valori.toString(), {
                method: "DELETE"
            })
                .then(
                    thisAttivita.status = "disicritto",
                    thisAttivita.textContent = "ISCRIVITI"
                )
        }
        else {
            fetch("attivitaPersonali?" + valori.toString(), {
                method: "POST"
            })
                .then(
                    thisAttivita.status = "iscritto",
                    thisAttivita.textContent = "DISICRIVITI"
                )
        }
    }

    fetch("attivitaPersonali?username=" + username, {
        method: "GET"
    })
        .then( response => response.json())
        .then(data => {
            console.log(data)
            data.forEach( item => {
                console.log(item)
                var json = JSON.parse(item)
                var attivita = json.nomeAttivita
                var attivitaTag = document.getElementById(attivita)
                attivitaTag.status = "iscritto"
                attivitaTag.innerHTML = "DISICRIVITI"
            })
        })

</script>

