package Utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

	public class Access {
	        Connection con = null;
	        PreparedStatement state = null;

	 public Access(String host, String DB, String user, String passwd){
	        try {
	            // ドライバクラスをロード
	            Class.forName("org.postgresql.Driver");
	            // データベースへ接続
	            con = DriverManager.getConnection("jdbc:postgresql://" + host + "/" + DB, user, passwd);
	            System.out.println(con);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	 }


}
