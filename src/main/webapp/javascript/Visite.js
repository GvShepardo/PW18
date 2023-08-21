function updateVisite(nomePagina){

    console.log("ciao")

    fetch("visite", {
        method: "PUT",
        headers: {
            "Content-Type": "text/plain"
        },
        body: nomePagina
    })

}