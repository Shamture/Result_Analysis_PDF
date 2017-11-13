<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <%
        String exportToExcel = request.getParameter("exportToExcel");
        if (exportToExcel != null
                && exportToExcel.toString().equalsIgnoreCase("YES")) {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "inline; filename="
                    + "excel.xlsx");
 
        }
        HttpSession sess=request.getSession();
        ArrayList<ArrayList<String>> data=(ArrayList<ArrayList<String>>)sess.getAttribute("data");
        String[] header=(String[])sess.getAttribute("header");
%>
		<div id="header-wrapper">
			<div class="container">
				<div class="row">
					<div class="12u">
						
						<header id="header">
								<h1 style="text-transform: uppercase;"><a href="Home.jsp"  id="logo" >R.A.</a></h1>
							<nav id="nav">
								<a href="Home.jsp"  class="current-page-item">Home</a>
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
					<%	String branchid = (String)sess.getAttribute("branch");
						String resultid=(String)sess.getAttribute("result");
					 %>
					 <label class="textbox">Report For:</label>
					 <label class="textbox">Branch : <%=branchid %> </label>
					 <label class="textbox">Result : <%=resultid %> </label>
					 <label class="textbox">Sem : <%=header[4] %> </label>
					 <label class="textbox">Exam Year : <%=header[1] %><%=header[2] %> </label>
					 <label class="textbox">Course Year : <%=header[3] %> </label>
					 <label class="textbox">Course : <%=header[0] %> </label>
					 
						<div id="copyright">
		    <table style="width: 100%;">
		        <thead>
		            <tr>
		            <th class="textbox" style="width: 20%;  background-color: #118eb1">PNR</th>
		            <th class="textbox" style="width: 30%;  background-color: #118eb1">Marks</th>
		            <th class="textbox" style="width: 48%;  background-color: #118eb1">Name</th>
		            </tr>
		        </thead>
		        <tbody>
		            <%
		                for (int i = 0; i < data.size(); i++) {
		            %>
		            <tr>
		                <td align="center" class="textbox" style="width: 20%;"><%=data.get(i).get(0)%></td>
		                <td align="center" class="textbox" style="width: 30%;"><%=data.get(i).get(1)%></td>
		                <td align="center" class="textbox" style="width: 48%;"><%=data.get(i).get(2)%></td>
		            </tr>
		            <%
		                }
		            %>
		        </tbody>
		    </table>
		    </div>
					</div>
				</div>
				<div class="row">
					<div class="12u">
						<div id="copyright">
<%-- 
    <%
        if (exportToExcel == null) {
    %>
    <a href="Report_excel.jsp?exportToExcel=YES" class="textbox" style="background-color: #118eb1">Export to Excel</a>
    <%
        }
    %>
 --%>
						</div>
					</div>
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