<body>
<div class="container">
    <header>
        <div class="logo">
            <img src="attivita1.png" alt="Tum4World Logo">
        </div>
        <h1>Tum4World</h1>
    </header>

    <ul class="menu">
        <li><a href="home.jsp"> Home </a></li>
        <li><a href="chisiamo.jsp"> Chi siamo </a></li>
        <li><a href="attivita.jsp"> Attività </a></li>
        <li><a href="contatti.jsp"> Contatti </a></li>
        <li class="logged-out"><a href="registrati.jsp"> Sign-in </a></li>
        <li class="logged-out"><a href="login.jsp"> Login </a></li>
        <li class="logged-in"><a href="profilo.jsp"> Profilo </a></li>
        <li class="logged-in"><a onclick="logOut()"> ESCI </a></li>
    </ul>
</div>

<script>
    if (document.cookie.includes("username")) {
        const loggedInLis = document.querySelectorAll(".logged-out");
        loggedInLis.forEach(li => {
            li.style.display = "none";
        });
    } else {
        const loggedOutlis = document.querySelectorAll(".logged-in");
        loggedOutlis.forEach(li => {
            li.style.display = "none";
        });
    }


    function logOut() {
        document.cookie = `username=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;`;
        document.cookie = `type=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;`;
        window.location.href = "home.jsp"
    }

</script>