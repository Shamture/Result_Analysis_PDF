package iText.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

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
@WebServlet("/gen_report_stats3")
public class gen_report_stats3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static String[] info;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gen_report_stats3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sess=request.getSession();
		String branchid=request.getParameter("branchid");

		if (branchid==null)
				branchid="BRANCH: 38 T.E.(2012 PAT.)ELECTRONICS)";

		sess.setAttribute("branchid", branchid);
//		System.out.println(branchid);
   		JDBC jdbc=new JDBC();
   		Connection con=jdbc.connect("db_pdf2text");
   		String sql;
   		ResultSet rs;
   		ArrayList<String> twlabels=new ArrayList<String>();
   		ArrayList<String> orlabels=new ArrayList<String>();
   		ArrayList<String> pplabels=new ArrayList<String>();
   		ArrayList<String> prlabels=new ArrayList<String>();
   		ArrayList<ArrayList<Integer>> data=new ArrayList<ArrayList<Integer>>();
   		int[] MINdata=new int[20];
   		Arrays.fill(MINdata, 999);
   		int[] MAXdata=new int[20];
   		Arrays.fill(MAXdata, 0);
   		int[] AVGdata=new int[20];
   		Arrays.fill(AVGdata, 0);
//   		ArrayList<Integer> MINdata=new ArrayList<Integer>();
//   		ArrayList<Integer> MAXdata=new ArrayList<Integer>();
   		sql="SELECT * FROM `subject` where branch='"+branchid+"' ORDER BY `subject`.idSubject ASC";   		
//   		System.out.println(sql);
   		rs=jdbc.select_Data(con, sql);
		try {
			while(rs.next())
			{
//				System.out.println("subject id"+rs.getString(1));
				if(rs.getString(3).equals("PP"))
				{
//					System.out.println(rs.getString(3)+"	"+rs.getString(2));
					pplabels.add(rs.getString(2));
				}
				if(rs.getString(3).equals("PR"))
				{
//					System.out.println(rs.getString(3)+"	"+rs.getString(2));
					prlabels.add(rs.getString(2));					
				}
				if(rs.getString(3).equals("OR"))
				{
//					System.out.println(rs.getString(3)+"	"+rs.getString(2));
					orlabels.add(rs.getString(2));										
				}
				if(rs.getString(3).equals("TW"))
				{
//					System.out.println(rs.getString(3)+"	"+rs.getString(2));
					twlabels.add(rs.getString(2));										
				}
			}
			int length;
			length=0;
			while(length<pplabels.size())
			{
				ArrayList<Integer> e=new ArrayList<Integer>();
				e.add(0);
				e.add(0);
				data.add(e);
				length++;
			}
			length=0;
			while(length<prlabels.size())
			{
				ArrayList<Integer> e=new ArrayList<Integer>();
				e.add(0);
				e.add(0);
				data.add(e);
				length++;
			}
			length=0;
			while(length<orlabels.size())
			{
				ArrayList<Integer> e=new ArrayList<Integer>();
				e.add(0);
				e.add(0);
				data.add(e);
				length++;
			}
			length=0;
			while(length<twlabels.size())
			{
				ArrayList<Integer> e=new ArrayList<Integer>();
				e.add(0);
				e.add(0);
				data.add(e);
				length++;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		sql="SELECT * FROM `result_data`";
   		if(!branchid.equals("any"))
   			sql="SELECT * FROM `result_data` where branch = '"+branchid+"' ";
   		System.out.println(sql);
   		rs=jdbc.select_Data(con, sql);
		try {
			int resultcnt=0;
			while(rs.next())
			{
				info=rs.getString(2).split("!");
				int count=0;
				for (int j = 6; j < 26; j++) {
					int marks;
					try
					{
						marks=rs.getInt(j);
					}
					catch(Exception ex)
					{
						marks=0;
					}
					if(MINdata[count]>marks && marks!=0 && rs.getString(j).matches("[1-9]+"))
					{
						MINdata[count]= marks;
					}
					if(MAXdata[count]<marks && marks!=0 && rs.getString(j).matches("[1-9]+"))
					{
						MAXdata[count]= marks;
					}
					count++;
				}
				int datacount,lblcnt;
				datacount=0;
				lblcnt=0;
				resultcnt++;
//				System.out.println("Student "+resultcnt);
				for(int i=46;i<56 && lblcnt<pplabels.size();i++,lblcnt++)
				{
					try
					{
					if(rs.getString(i).equals("P") && !rs.getString(i-40).equals("AA"))
					{
						ArrayList<Integer> e=new ArrayList<Integer>();
						int temppasscnt=data.get(datacount).get(0);
						int temptotalcnt=data.get(datacount).get(1);
						e.add(++temppasscnt);
						e.add(++temptotalcnt);
//						System.out.println(datacount+" "+temppasscnt+" "+temptotalcnt);
						data.set(datacount, e);
						datacount++;
					}
					else if(rs.getString(i).equals("F") && !rs.getString(i-40).equals("AA"))
					{
						ArrayList<Integer> e=new ArrayList<Integer>();
						int temppasscnt=data.get(datacount).get(0);
						int temptotalcnt=data.get(datacount).get(1);
						e.add(temppasscnt);
						e.add(++temptotalcnt);
//						System.out.println(datacount+" "+temppasscnt+" "+temptotalcnt);
						data.set(datacount, e);						
						datacount++;
					}
					}
					catch(Exception e)
					{
						
					}
				}
				lblcnt=0;
				for(int i=56;i<66 && lblcnt<prlabels.size()+orlabels.size();i++,lblcnt++)
				{
					try
					{
						if(rs.getString(i).equals("P") && !rs.getString(i-40).equals("AA"))
						{
							ArrayList<Integer> e=new ArrayList<Integer>();
							int temppasscnt=data.get(datacount).get(0);
							int temptotalcnt=data.get(datacount).get(1);
							e.add(++temppasscnt);
							e.add(++temptotalcnt);
//							System.out.println(datacount+" "+temppasscnt+" "+temptotalcnt);
							data.set(datacount, e);
							datacount++;
						}
						else if(rs.getString(i).equals("F") && !rs.getString(i-40).equals("AA"))
						{
							ArrayList<Integer> e=new ArrayList<Integer>();
							int temppasscnt=data.get(datacount).get(0);
							int temptotalcnt=data.get(datacount).get(1);
							e.add(temppasscnt);
							e.add(++temptotalcnt);
//							System.out.println(datacount+" "+temppasscnt+" "+temptotalcnt);
							data.set(datacount, e);						
							datacount++;
						}
					}
					catch(Exception e)
					{
						
					}
				}
				lblcnt=0;
				for(int i=66;i<76 && lblcnt<twlabels.size();i++,lblcnt++)
				{
					try
					{
						if(rs.getString(i).equals("P") && !rs.getString(i-40).equals("AA"))
						{
							ArrayList<Integer> e=new ArrayList<Integer>();
							int temppasscnt=data.get(datacount).get(0);
							int temptotalcnt=data.get(datacount).get(1);
							e.add(++temppasscnt);
							e.add(++temptotalcnt);
//							System.out.println(datacount+" "+temppasscnt+" "+temptotalcnt);
							data.set(datacount, e);
							datacount++;
						}
						else if(rs.getString(i).equals("F") && !rs.getString(i-40).equals("AA"))
						{
							ArrayList<Integer> e=new ArrayList<Integer>();
							int temppasscnt=data.get(datacount).get(0);
							int temptotalcnt=data.get(datacount).get(1);
							e.add(temppasscnt);
							e.add(++temptotalcnt);
//							System.out.println(datacount+" "+temppasscnt+" "+temptotalcnt);
							data.set(datacount, e);						
							datacount++;
						}
					}
					catch(Exception e)
					{
						
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}						   		

		Iterator<ArrayList<Integer>> iter=data.iterator();
		while(iter.hasNext())
		{
			ArrayList<Integer> dispdata=iter.next();
//			System.out.println(dispdata.get(0)+" "+dispdata.get(1));
		}

		sql="SELECT Avg(result_data.sub1),Avg(result_data.sub2),Avg(result_data.sub3),Avg(result_data.sub4),Avg(result_data.sub5),Avg(result_data.sub6),Avg(result_data.sub7),Avg(result_data.sub8),Avg(result_data.sub9),Avg(result_data.sub10),Avg(result_data.p1),Avg(result_data.p2),Avg(result_data.p3),Avg(result_data.p4),Avg(result_data.p5),Avg(result_data.p6),Avg(result_data.p7),Avg(result_data.p8),Avg(result_data.p9),Avg(result_data.p10),Avg(result_data.tw1),Avg(result_data.tw2),Avg(result_data.tw3),Avg(result_data.tw4),Avg(result_data.tw5),Avg(result_data.tw6),Avg(result_data.tw7),Avg(result_data.tw8),Avg(result_data.tw9),Avg(result_data.tw10) FROM result_data";
		rs=jdbc.select_Data(con, sql);
		try {
			while(rs.next())
			{
				for (int i = 0; i < 20; i++) {
					AVGdata[i]=rs.getInt(i+1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*		
		for (int i = 0; i < MAXdata.length; i++) {
			System.out.println(i+" MAX "+MAXdata[i]);
			System.out.print(" AVG "+AVGdata[i]);			
			System.out.print(" MIN "+MINdata[i]);	
			System.out.println();
		}


  		for (int i = 0; i < MINdata.length; i++) {
			System.out.println("MIN "+MINdata[i]);			
		}
		for (int i = 0; i < AVGdata.length; i++) {
			System.out.println("AVG "+AVGdata[i]);			
		}
*/
		
		sess.setAttribute("pp", pplabels);
		sess.setAttribute("pr", prlabels);
		sess.setAttribute("or", orlabels);
		sess.setAttribute("tw", twlabels);
		sess.setAttribute("data", data);
		sess.setAttribute("min", MINdata);
		sess.setAttribute("max", MAXdata);
		sess.setAttribute("avg", AVGdata);
		sess.setAttribute("branch", branchid);
		sess.setAttribute("header", info);		
		RequestDispatcher rd=request.getRequestDispatcher("Report_excel3.jsp");
		rd.forward(request, response);				
	}

}