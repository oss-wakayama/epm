package Developer_Recommendation;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Utilities.Access;

public class MkArffFromDB extends MkArff {
	Access access;

//=============================================================================//
	public MkArffFromDB(String _file_name)  throws Exception{
		super(_file_name);
		access = new Access(Util.host, Util.DB, Util.user, Util.passwd);
	}

//=============================================================================//
	public void attribute() throws Exception{
		int i=0;
		 pw.println("@attribute body string");
		 pw.print("@attribute class{");
		 String sql = "select distinct login from issues,users where status_id=3 and users.id=assigned_to_id";
			access.setState(access.getCon().prepareStatement(sql));
	        ResultSet Rs = access.getState().executeQuery();
	        while(Rs.next()) {
	        	if(i!=0) pw.print(",");
	        	pw.print(trim(Rs.getString("login")));
	        }
		 pw.println("}");
	}

//=============================================================================//
	public void data() throws Exception{
		pw.println("@data");
		String sql = "select login,subject,description from issues,users where status_id=3 and users.id=assigned_to_id";
		access.setState(access.getCon().prepareStatement(sql));
        ResultSet Rs = access.getState().executeQuery();
        while(Rs.next()) {
        	pw.print("\"");
        	pw.print(trim(Rs.getString("subject"))+" "+trim(Rs.getString("description")));
        	pw.print("\",");
        	pw.println(trim(Rs.getString("login")));
        }
	}

//=============================================================================//
    public void predictPart() throws Exception{
		String sql = "select subject,description from issues where status_id=1";
		access.setState(access.getCon().prepareStatement(sql));
        ResultSet Rs = access.getState().executeQuery();
        while(Rs.next()) {
        	pw.print("\"");
        	pw.print(trim(Rs.getString("subject"))+" "+trim(Rs.getString("description")));
        	pw.print("\",");
        	pw.println("?");
        }
    }

}
