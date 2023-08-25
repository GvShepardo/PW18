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

if (type === "simpatizzante") {
    document.getElementById("stylesheet").setAttribute("href", "simpatizzante.css");
} else if (type === "aderente") {
    document.getElementById("stylesheet").setAttribute("href", "aderente.css");
} else if (type === "admin") {
        document.getElementById("stylesheet").setAttribute("href", "admin.css");
} else {
    document.getElementById("stylesheet").setAttribute("href", "default.css");
}
