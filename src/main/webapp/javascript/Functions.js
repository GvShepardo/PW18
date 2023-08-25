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

function setStyleSheet() {
var type = getCookie("type");

console.log("QUESTO E IL COOKIE: " + type)
if (type === "simpatizzante") {
    console.log(document.getElementById("username"))
    document.getElementById("stylesheet").setAttribute("href", "simpatizzante.css");
    console.log("QUESTO E SIMPATIZZANTE")
} else if (type === "aderente") {
    document.getElementById("stylesheet").setAttribute("href", "aderente.css");
    console.log("QUESTO E ADERENTE")
} else if (type === "admin") {
        document.getElementById("stylesheet").setAttribute("href", "admin.css");
    console.log("QUESTO E ADMIN")
} else {
    document.getElementById("stylesheet").setAttribute("href", "style.css");
}
}

window.onload(setStyleSheet())
