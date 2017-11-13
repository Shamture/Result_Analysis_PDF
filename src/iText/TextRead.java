package iText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import dao.Marks;

public class TextRead {

	
	public static String branch="";
	public static String result="";
	public static String total="";
	public static String max="";
	public static String header = null;
	public static StringBuilder sem_data=new StringBuilder();
	
	public static void main(String[] args) {
		TextRead tr=new TextRead();
		tr.processpdf("C:/46_TE2012.pdf");
	}
	
	public void processpdf(String path){
		try {
			PdfReader reader = new PdfReader(path);
			sem_data=Sem_Details.main(reader);
			//			System.out.println(reader.getNumberOfPages());
			for (int i = 2; i <= reader.getNumberOfPages(); i++) {
//				System.out.println("\n********************************************************************************************************************");
				String page = PdfTextExtractor.getTextFromPage(reader,i);
				try
				{					
					int start=page.indexOf("BRANCH");
					int end=page.indexOf("DATE");
					branch=page.substring(start,end);
					branch=branch.substring(0, branch.lastIndexOf(")")+1);
//					System.out.println(branch.substring(0,branch.lastIndexOf(")")+1));
				}
				catch(Exception e)
				{
//					e.printStackTrace();
				}
				  String headerpatern="(SAVITRIBAI PHULE PUNE UNIVERSITY, )\\w.\\w.[\\(]([0-9]{4} COURSE\\)EXAMINATION, [A-Z]+\\/[A-Z]+ [0-9]{4})";
			      String pattern1 = "[\\s]+[A-Z][0-9]+";
			      String pattern2 = "(Result)(\\s)(:)(\\s)[A-Z]+(.)*";
			      
			      // Create a Pattern object
			      Pattern patterobj = Pattern.compile(headerpatern);
			      Pattern patterobj1 = Pattern.compile(pattern1);
			      Pattern patterobj2 = Pattern.compile(pattern2);

			      // Now create matcher object.
			      Matcher matcher = patterobj.matcher(page);
			      Matcher matcher1 = patterobj1.matcher(page);
			      Matcher matcher2 = patterobj2.matcher(page);
			      ArrayList<Integer> start_Index=new ArrayList<Integer>();
			      ArrayList<Integer> end_Index=new ArrayList<Integer>();
			      
			      while (matcher.find()) {
			    	  header=matcher.group(0);
			    	  System.out.println(header);
			      }
			      while (matcher1.find()) {
			          start_Index.add(matcher1.start());
			      }
			      while (matcher2.find()) {
			          end_Index.add(matcher2.end());
			      }
			      
			      Iterator<Integer> iter1=start_Index.iterator();
			      Iterator<Integer> iter2=end_Index.iterator();
			      while(iter1.hasNext() && iter2.hasNext())
			      {
//			    	  System.out.println("------------------------------------------------------------------------------------------------");
			    	  String data=page.substring(iter1.next(), iter2.next());
			    	  String prn=PersonalDetail.main(data);
			    	  ArrayList<Marks> Marks=MarksDetail2.main(data);
			    	  ArrayList<String> SubCode=MarksDetail.main(data);
			    	  int total_index=data.indexOf("GRAND TOTAL");
			    	  int result_index=data.indexOf("Result");
			    	  total = data.substring(total_index+14,result_index-4); 
			    	  max=total.substring(total.indexOf("/")+1);
			    	  total=total.substring(0, total.indexOf("/"));
			    	  result=data.substring(result_index+9);
			          result=result.substring(0, result.length()-8);
			    	  System.out.println("*"+total+"*");
			    	  System.out.println("*"+result+"*");
			    	  dao.storemarks.store(prn,SubCode,Marks,sem_data);
			      }
//			break;      \\TEST SINGLE PAGE
			}
			} catch (IOException e) {
//			e.printStackTrace();
		}
//		System.out.println("Conversion Done...");

	}
}