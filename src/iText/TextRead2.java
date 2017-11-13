package iText;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import dao.Marks;

public class TextRead2 {

	public static void main(String[] args) {
		try {

			PdfReader reader = new PdfReader("C:/46_TE2012_1.pdf");
			System.out.println(reader.getNumberOfPages());
			for (int i = 1; i <= reader.getNumberOfPages(); i++) {
				String page = PdfTextExtractor.getTextFromPage(reader,i);
			      String pattern1 = "[A-Z][0-9]+";
			      String pattern2 = "((([0-9]{1,3})|(--))\\/[0-9]{3,4})";

			      Pattern patterobj1 = Pattern.compile(pattern1);
			      Pattern patterobj2 = Pattern.compile(pattern2);

			      Matcher matcher1 = patterobj1.matcher(page);
			      Matcher matcher2 = patterobj2.matcher(page);
			      ArrayList<Integer> start_Index=new ArrayList<Integer>();
			      ArrayList<Integer> end_Index=new ArrayList<Integer>();
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
			    	  System.out.println("------------------------------------------------------------------------------------------------");
			    	  String data=page.substring(iter1.next(), iter2.next());
			    	  String prn=PersonalDetail.main(data);
			    	  ArrayList<String> SubCode=MarksDetail.main(data);
			    	  ArrayList<Marks> Marks=MarksDetail2.main(data);
			    	  dao.storemarks.store(prn,SubCode,Marks, TextRead.sem_data);
			      }
			      break;			//TEST SINGLE PAGE
			}
			
			} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Conversion Done...");
	}
}
