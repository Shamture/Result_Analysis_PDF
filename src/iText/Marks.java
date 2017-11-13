package iText;

import iText.dbutil.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class Marks {

	public static void main(String[] args) {
		try {

			PdfReader reader = new PdfReader(
					"C:/Workspace/iText/iText/WebContent/46_TE2012.pdf");
			System.out.println("This PDF has " + reader.getNumberOfPages()
					+ " pages.");

			Pattern allRecord = Pattern.compile("\\s+[A-Z]\\d+[\\W\\w\\s\\d]+");

			//for (int i = 1; i <= reader.getNumberOfPages(); i++) {
				Matcher m = allRecord.matcher(PdfTextExtractor.getTextFromPage(reader, 2));
				while (m.find()) {
					String result = m.group();
				
				}
			//}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
