package kr.or.ddit.basic.E08Board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class BoardUtil {
	static ResourceBundle bundle; //Properties 객체변수 선언
	
	//메모리에 로딩될 때 드라이버를 1번 로딩 시키고 계속 사용
	static {
		
		//1. 드라이버 로딩 확인
		try {
			bundle = ResourceBundle.getBundle("db");
			//res폴더에 있는 db.properties파일에서 가져옴
			
			Class.forName(bundle.getString("driver"));
			//oracle.jdbc.driver.OracleDriver
			
//			System.out.println("드라이버 로딩 성공");
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(bundle.getString("url"), 
					bundle.getString("user"), 
					bundle.getString("password"));
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void close(Connection conn,
							Statement stmt,
							PreparedStatement pstmt,
							ResultSet rs) {
		if(conn != null) try {conn.close();}catch(SQLException e){}
		if(stmt != null) try {stmt.close();}catch(SQLException e){}
		if(pstmt != null) try {pstmt.close();}catch(SQLException e){}
		if(rs != null) try {rs.close();}catch(SQLException e){}
	}
	
}
