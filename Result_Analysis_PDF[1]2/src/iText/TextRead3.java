package iText;

import iText.dbutil.DatabaseConnection;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class TextRead3 {
private static PrintStream out;

	//	private static String text="";
	public static void main(String[] args) {
		try {

			PdfReader reader = new PdfReader(
					"C:/46_TE2012.pdf");
			System.out.println("This PDF has " + reader.getNumberOfPages()
					+ " pages.");
			// Pattern p = Pattern.compile("-?\\d+");
			Pattern rollNo = Pattern.compile("[A-Z][0-9]+");

			HashMap<String, String> hm = new HashMap<String, String>();
			File file=new File("E:\\data.txt");
			out = new PrintStream(file);
			for (int i = 1; i <= reader.getNumberOfPages(); i++) {
				System.out.println(PdfTextExtractor.getTextFromPage(reader,i));

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Conversion Done...");
	}
}
