<!DOCTYPE html>
<html>
<head>

</head>
<body>
<div class="body">
<h1>Dettagli Utente</h1>

<div>
    <p><strong>Nome:</strong> John</p>
    <p><strong>Cognome:</strong> Doe</p>
    <p><strong>Username:</strong> johndoe</p>
</div>
<div class="contenitore-wrapper">
    <div class="contenitore">
        <h2>Container 1</h2>
        <img src="image1.jpg" alt="Immagine 1">
        <button class="green-button">Bottone 1</button>
    </div>

    <div class="contenitore">
        <h2>Container 2</h2>
        <img src="image2.jpg" alt="Immagine 2">
        <button class="green-button">Bottone 2</button>
    </div>

    <div class="contenitore">
        <h2>Container 3</h2>
        <img src="image3.jpg" alt="Immagine 3">
        <button class="green-button">Bottone 3</button>
    </div>
</div>

<button class="green-button final-button">Conferma</button>
</div>
</body>
</html>


<style>


    h1 {
        text-align: center;
        padding: 1rem;
    }

    .body {
        margin: 1rem;
        padding: 1rem;
        background-color: #fff;
        border-radius: 5px;
        color: black;
    }

    .contenitore-wrapper {
        display: flex;
        justify-content: center;
        align-items: center;
        margin-top: 1rem;
    }


    .contenitore {
        display: inline-block;
        align-items: center;
        margin: 1rem;
        padding: 1rem;
        background-color: #f7f7f7;
        border: 1px solid #ccc;
        border-radius: 5px;
        text-align: center;
        color: #333;
    }

    .contenitore h2 {
        margin-bottom: 0.5rem;
    }

    .contenitore img {
        max-width: 100px;
        height: auto;
        display: block;
        margin: 0 auto 0.5rem;
    }

    .green-button {
        background-color: #4CAF50;
        color: #fff;
        border: none;
        border-radius: 5px;
        padding: 0.5rem 1rem;
        cursor: pointer;
        opacity: 1;
        transition: opacity 0.3s;
    }

    .green-button:hover {
        opacity: 0.8;
    }

    .final-button {
        display: block;
        margin: auto;
        margin-top: 2rem;
    }

</style>