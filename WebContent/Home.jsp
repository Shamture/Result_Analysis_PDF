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
						<nav id="nav">
<!--
								<a href="Home.jsp"  class="current-page-item">Process PDF</a>
 -->
 								<a href="Report_stats.jsp">Stat. Report</a>
								<a href="Report_stats2.jsp">Stat. Report2</a>
								<a href="Report_stats3.jsp">Stat. Report3</a>
								<a href="Report_data.jsp" >Data Report</a>
								<a href="Report_data2.jsp" >Data Report2</a>
								<a href="./logout">Logout</a>
						</nav>
						</header>
					
					</div>
				</div>
			</div>
		</div>
		<div id="main">
			<div class="container">
				<div class="row">
					<div class="12u">
						<form action="./report" method="post" class="form" enctype="multipart/form-data">
					<div class="12u"  align="center">
					<div class="8u" align="center">
							<input class="textbox" type="file" name="file" id="file" placeholder="Select File" required>
					</div>
					</div>
					<br>
<!-- 				<div class="row">			
					<div class="6u" align="center">
						   <select id="year" name="year" " class="textbox">
						   <option selected="selected" style="color: red;" value="">--select Year--</option>
							<option value="FE">First Year</option>
							<option value="SE">Second Year</option>
							<option value="TE">Third Year</option>
							<option value="BE">Final Year</option>
						</select>
					</div>
					<div class="6u" align="center">
						   <select id="Branch" name="branch" " class="textbox">
						   <option selected="selected" style="color: red;" value="">--select Branch--</option>
							<option value="Computer">Computer Engineering</option>
							<option value="IT">IT</option>
							<option value="ELX">Electronics  Engineering</option>
							<option value="ENTC">E&TC</option>
						</select>
					</div>
					<div class="6u" align="center">
						   <select id="sem" name="sem" " class="textbox">
						   <option selected="selected" style="color: red;" value="">--select Semester--</option>
							<option value="1">Odd Semester</option>
							<option value="2">Even Semester</option>
						</select>
					</div>
					<div class="6u" align="center">
						   <select id="result" name="result" " class="textbox">
						   <option selected="selected" style="color: red;" value="">--select Result Class--</option>
							<option value="1">FIrst Class with Distinction</option>
							<option value="2">First Class</option>
							<option value="3">Higher Second Class</option>
							<option value="4">Second Class</option>
							<option value="5">Pass Class</option>
							<option value="6">Fails</option>
						</select>
					</div>
 -->				</div>
					<br>
					<div class="12u"  align="center">
					<div class="6u" align="center">
							<input class="button" type="submit" value="Submit">
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
		</body>
</html>