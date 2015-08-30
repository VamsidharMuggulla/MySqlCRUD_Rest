package com.delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/Delete")
public class Delete
{
	

	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String deleteTable(@QueryParam("tablename") String tablename) throws SQLException
	{
		
		Connection con=null;
		PreparedStatement ps=null;
		String statement="";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con=DriverManager.getConnection("jdbc:mysql://localhost/mysql_rest","vamsi" ,"vamsi");
			
			statement= "DROP TABLE "+tablename+";";
			System.out.println("STATEMENT : "+statement);
			ps=con.prepareStatement(statement);
			
			int x=ps.executeUpdate(statement);
			System.out.println("STATEMENT EXECUTED : "+x);
			
			return statement+" SUCCESSFULLY DROPPED TABLE!!";
			
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
		return "UNABLE TO DELETE TABLE :(";
		
	}


}
