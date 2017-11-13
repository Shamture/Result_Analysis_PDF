package iText;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.connectivity.jdbc.JDBC;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class MarksDetail {

	static JDBC jdbc=new JDBC();
	static Connection con= jdbc.connect("db_pdf2text");
	static HashMap<String, String[]> Subject_Table=new HashMap<>();
	public static ArrayList<String> Subcode=new ArrayList<String>();
	public static ArrayList<String> main(String data) {
//		System.out.println(data);
		try {
			String pattern_front,pattern_rear;
			pattern_front="[0-9]{6}[\\sA-Z\\s&\\-\\s-\\s.\\/–]+[0-9]{2,3}(\\s)+[0-9]{2,3}";
			pattern_rear="([0-9]{1,2}[\\s\\-\\s-\\s.]+){3}[PF][\\s]+[C\\s]+";
			Pattern Front_detail = Pattern.compile(pattern_front);
			Pattern Rear_detail = Pattern.compile(pattern_front);			
			Matcher mf = null;
			Matcher mr = null;
			int cnt = 1;
			Subcode.clear();
			Subject_Table.clear();
				mf = Front_detail.matcher(data);
				mr = Rear_detail.matcher(data);

				String sub_id,sub_name,sub_type,max,min;
				while (mf.find()) 
				{
					String result = mf.group();
					String temp_result=result.replaceAll(" ", "");
					sub_id=result.substring(0, 6);
//					System.out.println(result);
					if(!Subject_Table.containsKey(sub_id))
					{
						int length=temp_result.length();
						min=temp_result.substring(length-2,length);
						if(min.equals("40"))
						{
							max="100";
							sub_type=temp_result.substring(length-7,length-5);
						}
						else
						{
							max=temp_result.substring(length-4,length-2);
							sub_type=temp_result.substring(length-6,length-4);
						}
						int subname_loc=result.lastIndexOf(sub_type);
						sub_name=result.substring(6,subname_loc);
						String[] subdata={sub_name,sub_type,min,max};
						Subject_Table.put(sub_id+sub_type, subdata);
						Subcode.add(sub_id+sub_type);
						try
						{
							String sql="insert into subject values('"+sub_id+"','"+sub_name+"','"+sub_type+"','"+max+"','"+min+"','"+TextRead.branch+"')";
							jdbc.insert_Data(con, sql);
						}
						catch(Exception e)
						{
							
						}
					}
				}
			MarksDetail pd=new MarksDetail();
//			pd.print_hashmap(Subject_Table);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return Subcode;
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
			System.out.println("Key : "+key);
			print_array(HM.get(key));
		}
		System.out.println();
	}
}
