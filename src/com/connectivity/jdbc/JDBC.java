package com.connectivity.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class JDBC {

	public Connection connect(String db)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/"+db, "root", "root");
			return c;
		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		} catch (SQLException e) {
//			e.printStackTrace();
		}
		return null;
	}	
	
	public ResultSet select_Data(Connection c,String sql)
	{
		Statement st;
		try {
			st = c.createStatement();
			ResultSet rs=st.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
//			e.printStackTrace();
		}
		return null;
	}
	
	public int insert_Data(Connection c,String sql)
	{
		Statement st;
		try {
			st = c.createStatement();
			return st.executeUpdate(sql);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
		return 0;
	}
	
	public int update_Data(Connection c,String sql)
	{
		Statement st;
		try {
			st = c.createStatement();
			return st.executeUpdate(sql);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
		return 0;
	}

	public void truncate_table(Connection c,String table)
	{
		try {
			Statement st=c.createStatement();
			st.executeQuery("Truncate "+table);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}
	
	public ArrayList<String> colname(Connection c,String db,String table)
	{
		ArrayList<String> col_names=new ArrayList<String>();
		Statement st;
			try
			{
				st = c.createStatement();
				ResultSet rs=st.executeQuery("SELECT `COLUMN_NAME`FROM `INFORMATION_SCHEMA`.`COLUMNS` WHERE `TABLE_SCHEMA`='"+db+"' AND `TABLE_NAME`='"+table+"';");
				while(rs.next())
				{
					col_names.add(rs.getString("COLUMN_NAME"));
				}
			}
			catch(Exception e)
			{
//				e.printStackTrace();
			}
			Iterator<String> ListIterator=col_names.iterator();
/*			while(ListIterator.hasNext())
			{
				System.out.println(ListIterator.next());
			}
*/			return col_names;
	}

	
	public static void main(String[] args) {
		JDBC jdbc=new JDBC();
		String sql;
		String db="test";
		String table="temp";
		sql="select * from temp";
		Connection c=jdbc.connect(db);
		jdbc.select_Data(c, sql);
		jdbc.colname(c, db, table);
	}
	
}
