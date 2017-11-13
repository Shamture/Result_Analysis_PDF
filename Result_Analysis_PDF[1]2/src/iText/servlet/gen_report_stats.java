package iText.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connectivity.jdbc.JDBC;

/**
 * Servlet implementation class gen_report
 */
@WebServlet("/gen_report_stats")
public class gen_report_stats extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static String[] info;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gen_report_stats() {
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
		HttpSession sess=request.getSession();
		String branchid=request.getParameter("branchid");
		sess.setAttribute("branchid", branchid);
		System.out.println(branchid);
   		JDBC jdbc=new JDBC();
   		Connection con=jdbc.connect("db_pdf2text");
   		String sql;
   		
   		sql="SELECT count(*),result FROM `result_data` where result in (select distinct result from `result_data`) group by result";
   		if(!branchid.equals("any"))
   			sql="SELECT count(*),result FROM `result_data` where result in (select distinct result from `result_data`) and branch = '"+branchid+"' group by result ";
   		System.out.println(sql);
   		ResultSet rs=jdbc.select_Data(con, sql);
		int fcd = 0,fc = 0,hsc = 0,sc = 0,pc = 0;
   		int kt_1=0;
   		int kt_2=0;
   		int kt_3=0;
   		int kt_others=0;
		try {
			while(rs.next())
			{
				if(rs.getString(2).equals("FIRST CLASS WITH DISTINCTION"))
				{
					fcd+=rs.getInt(1);
				}
				if(rs.getString(2).equals("FIRST CLASS") || rs.getString(2).equals("FIRST CLASS  [O.2]") || rs.getString(2).equals("FIRST CLASS  [#  O.4]"))
				{
					fc+=rs.getInt(1);					
				}
				if(rs.getString(2).equals("HIGHER SECOND CLASS") || rs.getString(2).equals("HIGHER SECOND CLASS  [O.2]"))
				{
					hsc+=rs.getInt(1);					
				}
				if(rs.getString(2).equals("SECOND CLASS") || rs.getString(2).equals("SECOND CLASS  [O.2]"))
				{
					sc+=rs.getInt(1);					
				}
				if(rs.getString(2).equals("PASS CLASS") || rs.getString(2).equals("PASS CLASS  [O.2]"))
				{					
					pc+=rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}						   		
     	sql="SELECT * FROM `result_data` where result='FAILS A.T.K.T.'";
   		rs=jdbc.select_Data(con, sql);
   		
		try {
			while(rs.next())
			{
				info=rs.getString(2).split("!");
				int paperktcount=0;
				int otherktcount=0;
				for (int i = 46; i < 56; i++) {
					if(rs.getString(i)!=null)
						if(rs.getString(i).equals("F"))
							paperktcount++;
				}
				for (int i = 56; i < 76; i++) {
					if(rs.getString(i)!=null)
						if(rs.getString(i).equals("F"))
							otherktcount++;
				}
				if(paperktcount+otherktcount==1)
					kt_1++;
				else if(paperktcount+otherktcount==2)
					kt_2++;
				else if(paperktcount+otherktcount==3)
					kt_3++;
				else
					kt_others++;
			}
			ArrayList<Integer> stats = new ArrayList<Integer>();
			stats.add(fcd);
			stats.add(fc);
			stats.add(hsc);
			stats.add(sc);
			stats.add(pc);
			stats.add(kt_1);
			stats.add(kt_2);
			stats.add(kt_3);
			stats.add(kt_others);
			sess.setAttribute("stats", stats);
		} catch (SQLException e) {
			e.printStackTrace();
		}						   		
		sess.setAttribute("branch", branchid);
		sess.setAttribute("header", info);		
		RequestDispatcher rd=request.getRequestDispatcher("Report_excel.jsp");
		rd.forward(request, response);				
	}

}