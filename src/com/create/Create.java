package com.create;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/Operations/Create")

public class Create {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String createTable(@QueryParam("tablename")String tablename,
			@QueryParam("col1")String col1) throws SQLException
	{
		
		Connection con=null;
		PreparedStatement ps=null;
		String statement="";
		
		
		try { 
		Class.forName("com.mysql.jdbc.Driver"); 
		System.out.println("Loaded driver"); 
		con = DriverManager.getConnection("jdbc:mysql://localhost/mysql_rest", "vamsi", "vamsi");  
		System.out.println("Connected to MySQL"); 
		System.out.println(" nn  "+tablename);
		statement="CREATE TABLE IF NOT EXISTS "+tablename+"("+col1+" varchar(20));";
		System.out.println("STATEMENT : "+statement);
		ps=con.prepareStatement(statement);
		//ResultSet rs=ps.executeQuery();
		int x1=ps.executeUpdate(statement);
		System.out.println(" EXECUTED  "+x1);
		
		
		/*		if(ps.)
		{
			return "Table Not Created";
		}
		else
			return "Table "+table+"Created In Database";
		while(rs.next())
		{
		   x=x+rs.getString("name")+"\n";
		}
*/

		System.out.println("Table Created"); 
		
		
		ps.close();
		con.close();
		return "Table Created :)";
		
		} 
		catch (Exception ex) { 
		ex.printStackTrace();
		} 
		
		finally {
			if(con!=null)
			{
				con.close();
			}
		}
		return "Unable To Create Table :(";
		
	}
	/*
	@Path("/Read")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String readTable(@QueryParam("tablename") String tablename) throws SQLException
	{
		Connection con=null;
		PreparedStatement ps=null;
		String statement="";
		String retString="";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			System.out.println("Driver Loaded");
			
			con=DriverManager.getConnection("jdbc:mysql://localhost/mysql_rest","vamsi","vamsi");
			
			System.out.println("Connected To MySql");
			
			System.out.println("FROM TABLE NAME : "+tablename);
			statement="SELECT * FROM "+tablename+";";
			
			System.out.println("STATEMENT : "+statement);
			ps=con.prepareStatement(statement);
			
			ResultSet rs=ps.executeQuery();
			
			ResultSetMetaData rsmd=rs.getMetaData();
			String name=rsmd.getColumnName(1);
			
			
			while(rs.next())
			{
				
				retString=retString+name+" : "+rs.getString(name)+"<br>";
				System.out.println(retString);
				
			}
			
			
			
			System.out.println("Table FOUND!!!"); 
			
			
			ps.close();
			con.close();
			return retString;
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		finally {
			if(con!=null)
				con.close();
		}
		
		return "Unable To Read Table :(";
		
	}
*/
		

}
