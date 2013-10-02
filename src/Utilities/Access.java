package Utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

	public class Access {
	        private Connection con = null;
	        private PreparedStatement state = null;

	 public Access(String host, String DB, String user, String passwd){
	        try {
	            // ドライバクラスをロード
	            Class.forName("org.postgresql.Driver");
	            // データベースへ接続
	            setCon(DriverManager.getConnection("jdbc:postgresql://" + host + "/" + DB, user, passwd));
	            System.out.println(getCon());
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	 }

	public PreparedStatement getState() {
		return state;
	}

	public void setState(PreparedStatement state) {
		this.state = state;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}


}
