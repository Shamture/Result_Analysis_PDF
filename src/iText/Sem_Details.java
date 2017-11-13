package iText;

//import iText.dbutil.DatabaseConnection;

import java.io.IOException;/*
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;*/
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class Sem_Details {

	public static StringBuilder main(PdfReader reader) {
		StringBuilder data = new StringBuilder();
		try {

//			PdfReader reader = new PdfReader("C:/46_TE2012.pdf");
//			System.out.println("This PDF has " + reader.getNumberOfPages()+ " pages.");

///*/*---------------semester details--------------------------------
			String result="",Engg_year="",Month="",Year="",Course="";

//			Pattern SemData = Pattern.compile("([A-Z](.)[A-Z](.)[(][0-9]+(\\s)+[A-Z]+)(([)](\\s)*[A-Z]*(,)*[A-Z]*(\\s)*[0-9]*)|((.)[)][(][A-Z]+(.)*[)](\\s)*[A-Z]+(\\s)*[A-Z]+(.)(\\s)*[0-9]+))");

			String headerpatern="(SAVITRIBAI PHULE PUNE UNIVERSITY, )\\w.\\w.[\\(]([0-9]{4} COURSE\\)EXAMINATION, [A-Z]+\\/[A-Z]+ [0-9]{4})";
			Pattern SemData = Pattern.compile(headerpatern);
			
			//for (int i = 1; i <= reader.getNumberOfPages(); i++) {
				Matcher m = SemData.matcher(PdfTextExtractor.getTextFromPage(reader, 2));
				while (m.find()) {
				 result = m.group();
				}
		Pattern engg_year = Pattern.compile("[F|S|T|B](.)[E](.)");		
		Matcher m_engg_year = engg_year.matcher(result);	
		while (m_engg_year.find()) {
			Engg_year = m_engg_year.group();
			}
		System.out.println(result);
		data.append(Engg_year+"!");
		System.out.println("Engg year :" +Engg_year);
			
		Pattern month = Pattern.compile("[A-Z]{3,5}");
		Matcher m_month = month.matcher(result);	
		while (m_month.find()) {
			Month = m_month.group();
			}
		System.out.println("Month :"+Month);
		data.append(Month+"!");

		Pattern year = Pattern.compile("(\\s)[0-9]{4}");
		Matcher m_year = year.matcher(result);	
		while (m_year.find()) {
			Year = m_year.group();
			}
		System.out.println("Year : "+Year);
		data.append(Year+"!");

		Pattern course = Pattern.compile("[0-9]{4}(\\s)(COURSE)");
		Matcher m_course = course.matcher(result);	
		while (m_course.find()) {
			Course = m_course.group();
			}
		System.out.println("Course :"+Course);
		data.append(Course+"!");
		int i=1;
		int Semester = i;
		
		if((Engg_year.startsWith("F"))&&((Month.startsWith("O")||Month.startsWith("N")))){//STANDS FOR F.E & MONTH OCT
			System.out.println("Semester : "+i);
			Semester=i;
		}
		else if((Engg_year.startsWith("F"))&&((Month.startsWith("J")||Month.startsWith("M")))){
			System.out.println("Semester : "+(i+1));
			Semester=i+1;
		}
		else if((Engg_year.startsWith("S"))&&((Month.startsWith("O")||Month.startsWith("N")))){
			System.out.println("Semester : "+(i+2));
			Semester=i+2;
			}
		else if((Engg_year.startsWith("S"))&&((Month.startsWith("J")||Month.startsWith("M")))){
			System.out.println("Semester : "+(i+3));
			Semester=i+3;
			}
		else if((Engg_year.startsWith("T"))&&((Month.startsWith("O")||Month.startsWith("N")))){
			System.out.println("Semester : "+(i+4));
			Semester=i+4;
		}
		else if((Engg_year.startsWith("T"))&&((Month.startsWith("J")||Month.startsWith("M")))){
			System.out.println("Semester : "+(i+5));
			Semester=i+5;
		}
		else if((Engg_year.startsWith("B"))&&((Month.startsWith("O")||Month.startsWith("N")))){
			System.out.println("Semester : "+(i+6));
			Semester=i+6;
		}
		else if((Engg_year.startsWith("B"))&&((Month.startsWith("J")||Month.startsWith("M")))){
			System.out.println("Semester : "+(i+7));
			Semester=i+7;
		}
		else {
			System.out.println("Error");
		}
		data.append(Semester);


///*/*---------------semester details--------------------------------	*/*/	

		
///*/*---------------college name ends------------------------------
		String College_Name="";
		Pattern CollegeName = Pattern.compile("(,)[A-Z]+[-]*(\\s)*[A-Z]*[-]*(\\s)*(,)");
		Matcher CollegeName_Matcher = CollegeName.matcher(PdfTextExtractor.getTextFromPage(reader, 2));
		if (CollegeName_Matcher.find()) {
			College_Name = CollegeName_Matcher.group();
			}
		College_Name=College_Name.substring(1, 12)+", PUNE";
		System.out.println(College_Name);
		
//---------------college name ends------------------------------*/*/		
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

}
