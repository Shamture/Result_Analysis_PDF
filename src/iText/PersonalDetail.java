package iText;

	
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.connectivity.jdbc.JDBC;

public class PersonalDetail {

	static JDBC jdbc=new JDBC();
	static Connection con = jdbc.connect("db_pdf2text");
	public static String PRN_No;
	public static String main(String data) {
		try {
			Pattern personal_detail = Pattern.compile(
					"[A-Z][0-9]+((\\s)*[A-Z]+(\\s)*)+,[0-9]+[A-Z](\\s)+,[A-Z]+(-)[A-Z]+");
			
			Matcher m = null;
			Map<String, String> hm = new HashMap<String, String>();
			int cnt = 1;
				m = personal_detail.matcher(data);
				while (m.find()) {
					String result = m.group();
//					System.out.println(result);

					String seat_no = result.substring(0, 10);
					String[] ful_name = result
							.split("[A-Z][0-9]+[\\s]+|[\\s]+([A-Z]+(\\s))+[\\s]+,[0-9]+[A-Z](\\s)+,[A-Z]+(-)[A-Z]+");
					String[] mother_name = result
							.split("[A-Z][0-9]+[\\s]+((\\s)*[A-Z]+(\\s)*){1,3}|,[0-9]+[A-Z](\\s)+,[A-Z]+(-)[A-Z]+");
					String[] prn = result.split(
							"[A-Z][0-9]+[\\s]+((\\s)*[A-Z]+(\\s)*)+,|(\\s)+,[A-Z]+(-)[A-Z]+");
					String[] college_name = result
							.split("[A-Z][0-9]+[\\s]+((\\s)*[A-Z]+(\\s)*)+,[0-9]+[A-Z](\\s)+,");

						if (!seat_no.equals("")) {
							hm.put("rollNo" + cnt, seat_no);
//							System.out.println("Seat No :"+seatNo);
						}
					

					
					for (String fulName : ful_name) {
						if (!fulName.equals("")) {
//							System.out.println(fulName);
							hm.put("fulName" + cnt, fulName);
						}
					}

					for (String motherName : mother_name) {
						if (!motherName.equals("")) {
//							System.out.println(motherName);
							hm.put("motherName" + cnt, motherName);
						}
					}

					for (String PRN : prn) {
						if (!PRN.equals("")) {
//							System.out.println(PRN);
							hm.put("prn" + cnt, PRN);
							PRN_No=PRN;
						}
						else{
//							System.out.println("no prn");
						}
					}

					for (String collegeName : college_name) {
						if (!collegeName.equals("")) {
//							System.out.println(collegeName);
							hm.put("collegeName" + cnt, collegeName);
						}

					}

					cnt++;
				}

			try {
				Statement stmt = con.createStatement();

				int total_students = hm.size() / 5;
//				System.out.println(total_students);
				String sql = null;
				for (int i = 1; i <= total_students; i++) {
					String rollNo = hm.get("rollNo" + i);
					String fulName = hm.get("fulName" + i);
					String motherName = hm.get("motherName" + i);
					String prn = hm.get("prn" + i);
					String collegeName = hm.get("collegeName" + i);
					sql = "INSERT INTO student (rollNo, fulName, motherName, prnno, collegeName,branchid) values ('"
							+ rollNo + "', '" + fulName + "', '" + motherName + "', '" + prn + "', '"
							+ collegeName+"', '" +TextRead.branch + "')";

					stmt.executeUpdate(sql);
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return PRN_No;

	}

}
