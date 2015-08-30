package com.retrive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/Retrive")
public class Retrive
{
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String retriveData(@QueryParam("tablename") String tablename) throws SQLException
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

}
