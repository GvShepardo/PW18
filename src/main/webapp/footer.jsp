<script>
	function updatePhrase() {
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4 && xhr.status === 200) {
				document.getElementById("phrase").innerText = xhr.responseText;
			}
		};
		xhr.open("GET", "RandomPhraseServlet", true);
		xhr.send();
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
