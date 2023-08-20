<script>
	function updatePhrase() {
		fetch("RandomPhraseAPI", {
			method: "GET"
		})
				.then(response => response.text())
				.then(data => {
					console.log(data)
					document.getElementById("phrase").innerText = data;
					})

	}

	window.onload = function() {
		updatePhrase();
		setInterval(updatePhrase, 20000);
	};
</script>

<footer>
		<div class="footer">
			<p id="phrase"></p>
			<p>Tum4World</p>
			<p>Via Nome della Via, 123</p>
			<p>18000 Città (NA)</p>
			<p>Italia</p>
		</div>

	</footer>
</body>
</html>
