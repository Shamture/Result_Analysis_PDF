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
/*         String exportToExcel = request.getParameter("exportToExcel");
        if (exportToExcel != null
                && exportToExcel.toString().equalsIgnoreCase("YES")) {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "inline; filename="
                    + "excel.xlsx");
 
        } */
        HttpSession sess=request.getSession();
        ArrayList<Integer> data=(ArrayList<Integer>)sess.getAttribute("stats");
        String []datalabels={"FIRST CLASS WITH DISTINCTION","FIRST CLASS","HIGHER SECOND CLASS","SECOND CLASS","PASS CLASS","1 ATKT","2  ATKT","3  ATKT","4-5  ATKT"};
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
					 %>
					 <label class="textbox">Report For:</label>
					 <label class="textbox">Branch : <%=branchid %> </label>
					 <label class="textbox">Sem : <%=header[4] %> </label>
					 <label class="textbox">Exam Year : <%=header[1] %><%=header[2] %> </label>
					 <label class="textbox">Course Year : <%=header[3] %> </label>
					 <label class="textbox">Course : <%=header[0] %> </label>

						<div id="copyright">
		    <table style="width: 100%;">
		        <thead>
		            <tr>
		            <th class="textbox" style="width: 80%; background-color: #118eb1"><%=sess.getAttribute("branchid") %></th>
		            <th class="textbox" style="width: 19%;  background-color: #118eb1">Count</th>
		            </tr>
		        </thead>
		        <tbody>
		            <%
		                for (int i = 0; i < data.size(); i++) {
		            %>
		            <tr>
		                <td align="center" class="textbox" style="width: 80%;"><%=datalabels[i]%></td>
		                <td align="center" class="textbox" style="width: 19%;"><%=data.get(i)%></td>
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