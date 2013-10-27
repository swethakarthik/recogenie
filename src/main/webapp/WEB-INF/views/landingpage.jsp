<html>
<head>
	<title>RecoGenie - LinkedIn Hackathon 2013</title>
	<link rel="stylesheet" href="styles/styles.css" type="text/css">
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	
	<script>
	function formReset()
	{
		document.getElementById("request").reset();
	}
</script>
</head>

<body>

	<div id="wrapper">
	  <header id="globalHeader">
		
	  </header>

		<div id="main">
			<div class="box1">
				<div class="logo">
					<img class="logo" src="images/RecoGenie-logo.png" alt="RecoGenie">
				</div>
				
				<div class="landing-page">
					<h2 class="test">Test the Strength of a Relationship at RecoGenie</xh2>
					<form id="request" class="form-inline" action="http://localhost:8080/reco/strength">
						<input name="memberId1" type="text" id="candidate" class="input-large" placeholder="Candidate's Name">
						<input name="memberId2" type="text" id="recommender" class="input-large" placeholder="Recommender's Name">
						<button class="landing submit" type="submit" href="#">Submit</button>
						<button class="landing cancel" type="cancel" onclick="formReset()" value="Reset form">Clear</button>
					</form>
				</div>
			</div>

		</div><!-- /#main -->

	</div><!-- /#wrapper -->
<script>
$(document).ready(function() {
	$('#request').submit(function(event) {
		var candidateID = $('#candidate').val();
		var recommenderID = $('#recommender').val();
		var json = {"candidate":candidate,"recommender":recommender};
		
		$.ajax({
			url: $('#request').attr("action"),
			data: JSON.stringify(json),
			type: "POST",			
		});
	});
});
</script>
</body>
</html>
