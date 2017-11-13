import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import com.connectivity.jdbc.JDBC;

public class test {
public static void main(String[] args) {
	JDBC jdbc=new JDBC();
	Connection con=jdbc.connect("INFORMATION_SCHEMA");
	ArrayList<String> Cols=jdbc.colname(con, "db_pdf2text", "resultview");
	Iterator<String> iter=Cols.iterator();
	int i=1;
	while(iter.hasNext())
	{
		System.out.println("out.print(\"<tr>\");");
		System.out.println("out.print(\"<td>\");");
		System.out.println("out.print(\""+iter.next()+"\");");
		System.out.println("out.print(\"</td>\");");
		System.out.println("out.print(\"<td>\");");
		System.out.println("out.print(rs.getString("+(i++)+"));");
		System.out.println("out.print(\"</td>\");");
		System.out.println("out.print(\"</tr>\");");
	}
}
}
