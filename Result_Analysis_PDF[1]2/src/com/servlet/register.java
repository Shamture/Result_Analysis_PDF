package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connectivity.jdbc.JDBC;

/**
 * Servlet implementation class register
 */
@WebServlet("/reg")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		String mail=request.getParameter("email");
		String pass=request.getParameter("pass1");
		String sql="insert into users (email,pass) values ('"+mail+"','"+pass+"')";
		JDBC jdbc=new JDBC();
		Connection con= jdbc.connect("db_pdf2text");
		try {
			Statement stmt=con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			RequestDispatcher rd=request.getRequestDispatcher("failure.jsp");
			rd.forward(request, response);
//			e.printStackTrace();
		}
		RequestDispatcher rd=request.getRequestDispatcher("success.jsp");
		rd.forward(request, response);
	}

}
