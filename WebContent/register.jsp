<html>
	<head>
		<title>Result Analysis</title>
		<link href="http://fonts.googleapis.com/css?family=Ubuntu+Condensed" rel="stylesheet">
		<script src="js/jquery.min.js"></script>
		<script src="js/skel.min.js"></script>
		<script src="js/skel-layers.min.js"></script>
		<script src="js/init.js"></script>
		<noscript>
			<link rel="stylesheet" href="css/skel.css" />
			<link rel="stylesheet" href="css/style.css" />
			<link rel="stylesheet" href="css/style-desktop.css" />
		</noscript>
	</head>
	<body>
		<div id="header-wrapper">
			<div class="container">
				<div class="row">
					<div class="12u">
						
						<header id="header">
							<h1><a href="index.jsp" id="logo">Result Analysis</a></h1>
							<nav id="nav">
								<a href="index.jsp" >Home</a>
								<a href="login.jsp">Login</a>
								<a href="register.jsp" class="current-page-item">Register</a>
								<a href="contact.jsp">Contact Us</a>
							</nav>
						</header>
					
					</div>
				</div>
			</div>
		</div>
		<div id="main">
		<br>
			<div class="container">
				<div class="row">
					<div class="12u">
						<form class="form" id="regform" action="./reg" method="post">
					<div class="12u"  align="center">
					<div class="8u" align="center">
							<input class="textbox" type="email" name="email" id="email" placeholder="Email Address" required>
					</div>
					</div>
					<br>
					<div class="12u" align="center">
					<div class="8u" align="center">
							<input class="textbox" type="password" id="pass1" name="pass1" placeholder="Password" required>
					</div>
					</div>
					<br>
					<div class="12u"  align="center">
					<div class="8u" align="center">
							<input class="textbox" type="password" id="pass2" name="pass2" placeholder="Retype Password" required>
					</div>
					</div>
					<br>
					<div class="12u"  align="center">
					<div class="6u" align="center">
							<input class="button" name="reg" type="submit" onclick=check(); value="Register">
					</div>
					</div>
						</form>
					</div>
				</div>
		</div>
		<div id="footer-wrapper">
			<div class="container">
				<div class="row">
					<div class="12u">
						<div id="copyright">
							&copy; All rights reserved. | Design: Group A3
						</div>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">

		$(document).on("submit", "form", function(e){
			if($("#pass1").val()!=$("#pass2").val()){
				alert('passwords does not match')
			    e.preventDefault();
				return false;
			}
	    return;
		});

		</script>
		</body>
</html>