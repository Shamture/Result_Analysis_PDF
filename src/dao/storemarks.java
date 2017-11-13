package dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.connectivity.jdbc.JDBC;

import iText.MarksDetail;
import iText.MarksDetail2;
import iText.PersonalDetail;
import iText.TextRead;

public class storemarks {

	static JDBC jdbc=new JDBC();
	static Connection con = jdbc.connect("db_pdf2text");
	static Statement stmt;
	private static String substatus;
	private static String pstatus;
	private static String twstatus;
	public static void store(String prn, ArrayList<String> subCode, ArrayList<Marks> marks, StringBuilder sem_data)
	{
		Iterator<String> iter1=subCode.iterator();
		Iterator<Marks> iter2=marks.iterator();
		String sql = null;
		try{
			stmt=con.createStatement();
	    	  System.out.println("Max : "+TextRead.max);
			sql="insert into result_data (PRN,id_sem,branch,total,result,max) values ('"+prn+"','"+sem_data+"','"+TextRead.branch+"','"+TextRead.total+"','"+TextRead.result+"','"+TextRead.max+"')";
			stmt.executeUpdate(sql);
		}
		catch(Exception e)
		{
			System.out.println(sql);
//			e.printStackTrace();
		}
		int subid=1;
		int pid=1;
		int twid=1;
		String sub;
		String insem;
		String p;
		String tw;
		while(iter1.hasNext()&&iter2.hasNext())
		{
			sub="sub"+subid;
			substatus="sub"+subid+"status";
			insem="insem"+subid;
			p="p"+pid;
			pstatus="pstatus"+pid;
			tw="tw"+twid;
			twstatus="twstatus"+twid;
			try
			{
				stmt = con.createStatement();
				System.out.println("*******"+prn+"*******");
				String subcode=iter1.next();
//				System.out.println(subcode);	
				dao.Marks marksdata=iter2.next();
//				System.out.println(marksdata.insem);
//				System.out.println(marksdata.endsem);
//				System.out.println(marksdata.total);
//				System.out.println(marksdata.status);
//				System.out.println(marksdata.carry);
				
				if(subcode.contains("PP"))
				{
					sql="update result_data set "+insem+"='"+marksdata.insem+"',"+sub+"='"+marksdata.endsem+"',"+substatus+"='"+marksdata.status+"' where prn='"+prn+"'";
					subid++;
				}
				if(subcode.contains("PR") || subcode.contains("OR"))
				{
					sql="update result_data set "+p+"='"+marksdata.total+"',"+pstatus+" = '"+marksdata.status+"' where prn='"+prn+"'";
					pid++;
				}
				if(subcode.contains("TW"))
				{
					sql="update result_data set "+tw+"='"+marksdata.total+"',"+twstatus+" = '"+marksdata.status+"' where prn='"+prn+"'";
					twid++;
				}
				stmt.executeUpdate(sql);
			}
			catch(Exception e)
			{
				System.out.println(sql);
//				e.printStackTrace();	
//				break;
			}
		}
 	  PersonalDetail.PRN_No=null;
  	  MarksDetail.Subcode.clear();
  	  MarksDetail2.SubMarks.clear();

	}
	
}
