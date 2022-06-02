package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	
	//실행될때 한번만 하면 됨
	//JDBCUtil이 메모리에 로딩될 때 한번만 실행됨
	static {
		
		//1. 드라이버 로딩 확인 (필수과정x)
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패..."); //굳이 없어도 되는 부분
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 커넥션 연결
	 * @return Connection 객체
	 */
	public static Connection getConnection() {
	
		try {//url,userId,password
			return DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe", 
					"LHR91", 
					"java");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 자원반납
	 * 닫고싶은 것을 파라미터로 넣어주면 알아서 닫김
	 * @param conn
	 * @param stmt
	 * @param pstmt
	 * @param rs
	 */
	public static void close(
			Connection conn, 
			Statement stmt, 
			PreparedStatement pstmt, 
			ResultSet rs) {
		
		if(conn != null) try {conn.close();} catch(SQLException ex) {}
		if(stmt != null) try {stmt.close();} catch(SQLException ex) {}
		if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
		if(rs != null) try {rs.close();} catch(SQLException ex) {}
	}
}
