function updateVisite(nomePagina){

    fetch("visite", {
        method: "PUT",
        headers: {
            "Content-Type": "text/plain"
        },
        body: nomePagina
    })

}

function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}


    var type = getCookie("type");

    console.log("QUESTO E IL COOKIE: " + type)
    console.log(document.getElementById("stylesheet"));
    if (type === "simpatizzante") {
        console.log(document.getElementById("username"))
        document.getElementById("stylesheet").setAttribute("href", "style/simpatizzante.css");
        console.log(document.getElementById("stylesheet"));
        console.log("QUESTO E SIMPATIZZANTE")
    } else if (type === "aderente") {
        document.getElementById("stylesheet").setAttribute("href", "style/aderente.css");
        console.log(document.getElementById("stylesheet"));
        console.log("QUESTO E ADERENTE")
    } else if (type === "admin") {
        document.getElementById("stylesheet").setAttribute("href", "style/admin.css");
        console.log(document.getElementById("stylesheet"));
        console.log("QUESTO E ADMIN")
    } else {
        document.getElementById("stylesheet").setAttribute("href", "style/style.css");
        console.log(document.getElementById("stylesheet"));
    }
