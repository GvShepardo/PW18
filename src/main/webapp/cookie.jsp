<% boolean cookieAccettati = false; %><%-- cookieMessage.jsp --%>
<%-- Questo è il messaggio di accettazione dei cookie --%>
<div class="cookie-container">
    <div class="cookie-banner">
        Questo sito utilizza i cookie per garantire una migliore esperienza utente. Clicca il pulsante per accettare.
        <button class="cookie-btn" onclick="accettaCookie()">Accetta</button>
    </div>
    <div class="overlay <%=(cookieAccettati ? "hidden" : "")%>"></div>
</div>


<script>

    window.addEventListener("resize", posizionaBanner);

    function posizionaBanner() {
        var windowHeight = window.innerHeight;
        var bannerHeight = cookieBanner.offsetHeight;
        cookieBanner.style.bottom = "0";
        cookieBanner.style.left = "0";
        cookieBanner.style.transform = "translateX(0)";
    }

    window.onload = function () {
        if(document.cookie.includes("cookieAccettati=true")){
            var cookieBanner = document.querySelector(".cookie-banner");
            cookieBanner.style.display = "none";
        }
        posizionaBanner();
    };

    function accettaCookie() {
        // Imposta un cookie per indicare che l'utente ha accettato i cookie
        cookieAccettati = true;
        var data = new Date();
        data.setTime(data.getTime() + (365 * 24 * 60 * 60 * 1000)); // Scade in un anno
        var scadenza = "expires=" + data.toUTCString();
        document.cookie = "cookieAccettati=true;" + scadenza + ";path=/";

        // Nascondi la barra dei cookie
        var cookieBanner = document.querySelector(".cookie-banner");
        cookieBanner.style.display = "none";

        var overlay = document.querySelector(".overlay");
        overlay.style.display = "none"
    }

    // Controlla se l'utente ha già accettato i cookie
    var cookieAccettati = document.cookie.includes("cookieAccettati=true");
    if (cookieAccettati) {
        var cookieBanner = document.querySelector(".cookie-banner");
        cookieBanner.style.display = "none";
        var overlay = document.querySelector(".overlay");
        overlay.style.display = "none";
    }


</script>

<style>
    .cookie-banner {
        position: fixed;
        bottom: 0;
        left: 0;
        transform: translateX(0); /* Aggiunto per evitare conflitto di posizionamento */
        background-color: #fff;
        padding: 10px;
        z-index: 9999;
    }

    .overlay {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.5);
    }

    .hidden {
        display: none;
    }
</style>
