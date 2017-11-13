package iText.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.oracle.jrockit.jfr.RequestDelegate;

/**
 * Servlet implementation class gen_report
 */
@WebServlet("/gen_report_data2")
public class gen_report_data2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static String[] info;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gen_report_data2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw=response.getWriter();
		String branchid=request.getParameter("branchid");
		String resultid=request.getParameter("resultid");				//percentage num. value
		String resultop=request.getParameter("resultop");
		System.out.println(branchid);
//		doGet(request, response);
   		JDBC jdbc=new JDBC();
   		Connection con=jdbc.connect("db_pdf2text");
   		String sql;
   		
/*				similar data
 
     		sql="SELECT * FROM `result_data";
    		if(!resultid.equals("any") && !branchid.equals("any"))
   			sql="SELECT * FROM `result_data` where branch = '"+branchid+"' and result like '"+resultid+"%'";
   		else if(!branchid.equals("any"))
   			sql="SELECT * FROM `result_data` where branch = '"+branchid+"'";
   		else if(!resultid.equals("any"))
			sql="SELECT * FROM `result_data` where result like '"+resultid+"%'";
*/
   		
   			sql="SELECT *,(total/`db_pdf2text`.`result_data`.max*100) as per FROM result_data ,student where result_data.PRN = student.prnno and result_data.total <> '--' AND result_data.max IS NOT NULL and (result_data.total/result_data.max*100)"+resultop+" "+resultid+" group by prn order by per  desc";
   		if(!resultid.equals("any") && !branchid.equals("any"))
   			sql="SELECT *,(total/`db_pdf2text`.`result_data`.max*100) as per FROM result_data , student where branch = '"+branchid+"' and result_data.PRN = student.prnno and result_data.total <> '--' AND result_data.max IS NOT NULL and (result_data.total/result_data.max*100)"+resultop+" "+resultid+" group by prn order by per  desc";
   		else if(!branchid.equals("any"))
   			sql="SELECT *,(total/`db_pdf2text`.`result_data`.max*100) as per FROM result_data , student where branch = '"+branchid+"' and result_data.PRN = student.prnno and result_data.total <> '--' AND result_data.max IS NOT NULL and (result_data.total/result_data.max*100)"+resultop+" "+resultid+" group by prn order by per  desc";
   		else if(!resultid.equals("any"))
			sql="SELECT *,(total/`db_pdf2text`.`result_data`.max*100) as per FROM result_data , student where result_data.PRN = student.prnno and result_data.total <> '--' AND result_data.max IS NOT NULL and (result_data.total/result_data.max*100)"+resultop+" "+resultid+" group by prn order by per desc ";

    	System.out.println(sql);
   		pw.println(sql);
   		ResultSet rs=jdbc.select_Data(con, sql);
		ArrayList<ArrayList<String>> data=new ArrayList<>();
		try {
			int index=0;
			while(rs.next())
			{
				info=rs.getString(2).split("!");
				ArrayList<String> element=new ArrayList<>();
				element.add(rs.getString(1));
				element.add(rs.getString(78));
				double per=rs.getDouble(83);
                String percent=String.format("%.2f", per);
				element.add(percent);
				data.add(index++, element);
//				pw.println(rs.getString(1)+"\t\t"+rs.getString(4)+"\t\t\t"+rs.getString(77));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		HttpSession sess=request.getSession();
		sess.setAttribute("data", data);
		sess.setAttribute("branch", branchid);
		sess.setAttribute("result", resultid);
		sess.setAttribute("header", info);		
		RequestDispatcher rd=request.getRequestDispatcher("Report_excel_data2.jsp");
		rd.forward(request, response);
	}
}