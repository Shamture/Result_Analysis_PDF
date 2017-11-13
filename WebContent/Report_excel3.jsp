<%@page import="com.itextpdf.text.log.SysoCounter"%>
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
            response.setHeader("Content-Disposition", "inline; filename="+ "excel.xlsx");
 
        }
        HttpSession sess=request.getSession();
   		ArrayList<String> pplabels=(ArrayList<String>) sess.getAttribute("pp");
   		ArrayList<String> prlabels=(ArrayList<String>) sess.getAttribute("pr");
   		ArrayList<String> orlabels=(ArrayList<String>) sess.getAttribute("or");
   		ArrayList<String> twlabels=(ArrayList<String>) sess.getAttribute("tw");
   		int [] min=(int[])sess.getAttribute("min");
   		int [] max=(int[])sess.getAttribute("max");
   		int [] avg=(int[])sess.getAttribute("avg");
   		ArrayList<ArrayList<Integer>> data=(ArrayList<ArrayList<Integer>>)sess.getAttribute("data");
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
		            <th class="textbox" style="width: 38%; background-color: #118eb1">Subject</th>
		            <th class="textbox" style="width: 10%;  background-color: #118eb1">Appeared</th>
		            <th class="textbox" style="width: 10%;  background-color: #118eb1"> Passed</th>
		            <th class="textbox" style="width: 10%;  background-color: #118eb1">% Passed</th>
		            <th class="textbox" style="width: 10%;  background-color: #118eb1">Min</th>
		            <th class="textbox" style="width: 10%;  background-color: #118eb1">Max</th>
		            <th class="textbox" style="width: 10%;  background-color: #118eb1">Avg</th>
		            </tr>
		        </thead>
		        <tbody>
		            <%
		            	int index=0;
		            	int statindex=0;
//		            	System.out.println(pplabels.size());
		                for (int i = 0; i < pplabels.size(); i++) {
		                	statindex++;
		            %>
		            <tr>
		                <td align="center" class="textbox" style="width: 38%;"><%=pplabels.get(i)%>(TH)</td>
		                <td align="center" class="textbox" style="width: 10%;"><%=data.get(i).get(1)%></td>
		                <td align="center" class="textbox" style="width: 10%;"><%=data.get(i).get(0)%></td>
		                <%
		                String percent=String.format("%.2f", data.get(i).get(0)*100.0/data.get(i).get(1));
		                %>
		                <td align="center" class="textbox" style="width: 10%;"><%=percent%></td>
<%-- 		                <td align="center" class="textbox" style="width: 10%;"><%=data.get(i).get(0)*100.0/data.get(i).get(1)%></td>
 --%>		                <td align="center" class="textbox" style="width: 10%;"><%=min[statindex]%></td>
		                <td align="center" class="textbox" style="width: 10%;"><%=max[statindex]%></td>
		                <td align="center" class="textbox" style="width: 10%;"><%=avg[statindex]%></td>
		            </tr>
		            <%
		            	index=i+1;
		                }
		                int tempindex=index;
		            %>
		            <%
//		            	statindex=0;
		                for (int i = 0; i < prlabels.size(); i++) {
		                	statindex++;
//		        			System.out.println(statindex);
		            %>
		            <tr>
		                <%//System.out.println(tempindex+i); %>
		                <td align="center" class="textbox" style="width: 38%;"><%=prlabels.get(i)%>(PR)</td>
		                <td align="center" class="textbox" style="width: 10%;"><%=data.get(tempindex+i).get(1)%></td>
		                <td align="center" class="textbox" style="width: 10%;"><%=data.get(tempindex+i).get(0)%></td>
		                <%
		                String percent=String.format("%.2f", data.get(tempindex+i).get(0)*100.0/data.get(tempindex+i).get(1));
		                %>
		                <td align="center" class="textbox" style="width: 10%;"><%=percent%></td>
<%-- 		                <td align="center" class="textbox" style="width: 10%;"><%=data.get(tempindex+i).get(0)*100.0/data.get(tempindex+i).get(1)%></td>
 --%>		                <td align="center" class="textbox" style="width: 10%;"><%=min[statindex]%></td>
		                <td align="center" class="textbox" style="width: 10%;"><%=max[statindex]%></td>
		                <td align="center" class="textbox" style="width: 10%;"><%=avg[statindex]%></td>
		            </tr>
		            <%
		            	index=i+1;
		                }
			            tempindex=index+pplabels.size();
		            %>
		            <%
		            	statindex=statindex-1;
		                for (int i = 0; i < orlabels.size(); i++) {
		                	statindex++;
		            %>
		            <tr>
		                <td align="center" class="textbox" style="width: 38%;"><%=orlabels.get(i)%>(OR)</td>
		                <%//System.out.println(tempindex+i); %>
		                <td align="center" class="textbox" style="width: 10%;"><%=data.get(tempindex+i).get(1)%></td>
		                <td align="center" class="textbox" style="width: 10%;"><%=data.get(tempindex+i).get(0)%></td>
		                <%
		                String percent=String.format("%.2f", data.get(tempindex+i).get(0)*100.0/data.get(tempindex+i).get(1));
		                %>
		                <td align="center" class="textbox" style="width: 10%;"><%=percent%></td>
<%-- 		                <td align="center" class="textbox" style="width: 10%;"><%=data.get(tempindex+i).get(0)*100.0/data.get(tempindex+i).get(1)%></td>
 --%>		                <td align="center" class="textbox" style="width: 10%;"><%=min[statindex]%></td>
		                <td align="center" class="textbox" style="width: 10%;"><%=max[statindex]%></td>
		                <td align="center" class="textbox" style="width: 10%;"><%=avg[statindex]%></td>
		            </tr>
		            <%
	            	index=i+1;
		                }
			            tempindex=index+pplabels.size()+prlabels.size();	            
			         %>
		            <%
		            	statindex=statindex-5;
		                for (int i = 0; i < twlabels.size(); i++) {
		                	statindex++;
		            %>
		            <tr>
		                <%//System.out.println(tempindex+i); %>
		                <td align="center" class="textbox" style="width: 38%;"><%=twlabels.get(i)%>(TW)</td>
		                <td align="center" class="textbox" style="width: 10%;"><%=data.get(tempindex+i).get(1)%></td>
		                <td align="center" class="textbox" style="width: 10%;"><%=data.get(tempindex+i).get(0)%></td>
		                <%
		                String percent=String.format("%.2f", data.get(tempindex+i).get(0)*100.0/data.get(tempindex+i).get(1));
		                %>
		                <td align="center" class="textbox" style="width: 10%;"><%=percent%></td>
		                <td align="center" class="textbox" style="width: 10%;"><%=min[statindex]%></td>
		                <td align="center" class="textbox" style="width: 10%;"><%=max[statindex]%></td>
		                <td align="center" class="textbox" style="width: 10%;"><%=avg[statindex]%></td>
		            </tr>
		            <%
	            	index=i;
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
    <a href="Report_excel3.jsp?exportToExcel=YES" class="textbox" style="background-color: #118eb1">Export to Excel</a>
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