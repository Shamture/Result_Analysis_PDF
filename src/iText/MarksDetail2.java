package iText;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.connectivity.jdbc.JDBC;

import dao.Marks;

public class MarksDetail2 {

	static JDBC jdbc=new JDBC();
	static Connection con= jdbc.connect("db_pdf2text");
	static HashMap<String, String[]> Subject_Table;
	public static ArrayList<dao.Marks> SubMarks;;
	public static ArrayList<Marks> main(String rsdata) {
		try {
			String pattern_rear;
			pattern_rear="[\\s]{3}([0-9|AA|\\-\\-]{1,2}[\\s]+){1,3}[PF][\\s][C]*";
			Pattern Rear_detail = Pattern.compile(pattern_rear);
			Subject_Table=new HashMap<>();
			SubMarks=new ArrayList<dao.Marks>();
			Matcher mr = null;
			mr = Rear_detail.matcher(rsdata);
			dao.Marks marks = new dao.Marks();
			while (mr.find()) 
			{
				marks=new dao.Marks(); 
				String result = mr.group();
				String insem="NA",endsem="NA",total="NA";
				String status="NA",carry="NA";
					String []data=result.split("[ ]{1,5}");
					if(data.length>4)
					{
						insem=data[1];
						endsem=data[2];
						total=data[3];
						status=data[4];
						marks.insem=insem;
						marks.endsem=endsem;
						marks.total=total;
						marks.status=status;
						if(data.length==6)
						{
							carry=data[5];
							marks.carry=carry;
						}
/*							System.out.println("Theory");
							System.out.println("Insem "+insem);
							System.out.println("Endsem "+endsem);
							System.out.println("Total "+total);
							System.out.println("Status "+status);
							System.out.println("Carry "+carry);
*/					}
					else
					{
						total=data[1];
						status=data[2];
						marks.total=total;
						marks.status=status;
						if(data.length==4)
						{
							carry=data[3];
							marks.carry=carry;
						}
/*							System.out.println("Other");
							System.out.println("Total "+total);
							System.out.println("Status "+status);
							System.out.println("Carry "+carry);
*/					}
					SubMarks.add(marks);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SubMarks;
	}
	
	public void print_array(String[] array)
	{
		System.out.println();
		for(int i=0;i<array.length;i++)
		{
			System.out.print(array[i]+"\t");
		}
	}
	
	public void print_hashmap(HashMap<String, String[]> HM)
	{
		Set<String> keyset=  HM.keySet();
		Iterator<String> set_iterator=keyset.iterator();
		while(set_iterator.hasNext())
		{
			String key=set_iterator.next();
			System.out.println();
			System.out.println("MarksKey : "+key);
//			print_array(HM.get(key));
		}
	}
}
