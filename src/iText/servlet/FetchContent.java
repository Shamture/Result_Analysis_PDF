package iText.servlet;

import iText.dbutil.DatabaseConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FetchContent")
public class FetchContent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FetchContent() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.print("<html>");
		out.print("<head>");
		out.print("<title>Report");
		out.print("</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("<table>");
		try {
			Connection con = DatabaseConnection.prepareConn();
			Statement stmt = con.createStatement();

			String sql = "SELECT * FROM student order by fulname";
			ResultSet rs = stmt.executeQuery(sql);
			
			out.print("<tr>");
			out.print("<th>SR NO</th>"
					+ "<th>ROLL NO</th>"
					+ "<th>FULL NAME</th>"
					+ "<th>MOTHERS NAME</th>"
					+ "<th>PRN</th>"
					+ "<th>COLLEGE NAME</th>"
					+ "<th>Branch ID</th>"
					+ "<th colspan='2'>Operations</th>");
			out.print("</tr>");
			int i=0;
			while (rs.next()) {
				i++;
				out.print("<tr>");
				out.print("<td>"+i+"</td>"
						+ "<td>"+rs.getString(1)+"</td>"
						+ "<td>"+rs.getString(2)+"</td>"
						+ "<td>"+rs.getString(3)+"</td>"
						+ "<td>"+rs.getString(4)+"</td>"
						+ "<td>"+rs.getString(5)+"</td>"
						+ "<td>"+rs.getString(6)+"</td>"
//						+ "<td><a href='delete?id="+rs.getString(1)+"'>delete</a></td>"
//						+ "<td><a href='edit?id="+rs.getString(1)+"'>edit</a></td>"
						+ "<td><a href=\"StudentResult?q=select * from resultview where rollno='"+rs.getString(1)+"'\">Check</a></td>");
				out.print("</tr>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print("</table>");
		out.print("</body>");
		out.print("</html>");
	}

}
