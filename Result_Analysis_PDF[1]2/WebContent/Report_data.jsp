<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.connectivity.jdbc.JDBC"%>
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
								<h1 style="text-transform: uppercase;"><a href="Home.jsp"  id="logo" >Result Analysis</a></h1>
							<nav id="nav">
								<a href="Home.jsp">Process PDF</a>
								<a href="Report_stats.jsp">statics report</a>
								<a href="Report_stats2.jsp">student analysis</a>
								<a href="Report_stats3.jsp">subject wise</a>
								<a href="Report_data2.jsp" >percent wise</a>
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
						<form action="./gen_report_data" method="post" class="form">
					<br>
				<div class="row">			
					<div class="12u" align="center">
						   <select id="branchid" name="branchid" " class="textbox">
						   <option selected="selected" style="color: red;" value="any">--all branches--</option>
						   <%
						   		JDBC jdbc=new JDBC();
						   		Connection con=jdbc.connect("db_pdf2text");
						   		String sql="SELECT distinct branch FROM `result_data`;";
						   		ResultSet rs=jdbc.select_Data(con, sql);
								while(rs.next())
								{
									%>
										<option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>										
									<%
								}						   		
						   %>
						</select>
					</div>
					<div class="12u" align="center">
						   <select id="resultid" name="resultid" " class="textbox">
						   <option selected="selected" style="color: red;" value="any">--all classes--</option>
						   <%
						   		sql="SELECT distinct result FROM `result_data`;";
						   		rs=jdbc.select_Data(con, sql);
								while(rs.next())
								{
									%>
										<option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>										
									<%
								}						   		
						   %>
						</select>
					</div>
				</div>
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