function updateVisite(nomePagina){

    fetch("visite", {
        method: "PUT",
        headers: {
            "Content-Type": "text/plain"
        },
        body: nomePagina
    })

}