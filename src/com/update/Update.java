package com.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/Update")
public class Update 
{
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String updateTable(@QueryParam("tablename") String tablename,
			@QueryParam("value") String value) throws SQLException
	{
		
		Connection con=null;
		PreparedStatement ps=null;
		String statement="";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con=DriverManager.getConnection("jdbc:mysql://localhost/mysql_rest","vamsi" ,"vamsi");
			
			statement= " INSERT INTO "+tablename+" VALUES('"+value+"');";
			System.out.println("STATEMENT : "+statement);
			ps=con.prepareStatement(statement);
			
			int x=ps.executeUpdate(statement);
			System.out.println("STATEMENT EXECUTED : "+x);
			
			return statement+" SUCCESSFULLY INSERTED!!";
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			if(con!=null)
				con.close();
		}
		return "UNABLE TO INSERT DATA :(";
		
	}
	
	

}
