package iText.dbutil;
import java.sql.*;
import java.util.*;

public class DatabaseConnection {
	public static Connection prepareConn() {
		Connection c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/db_pdf2text",
							"root", "root");
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return c;
	}// prepareConn

	public static void closeConn(Connection c) {
		try {
			if (c != null)
				c.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}// closeConn
}