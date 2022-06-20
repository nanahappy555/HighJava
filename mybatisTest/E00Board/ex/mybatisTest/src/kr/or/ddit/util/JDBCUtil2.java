package kr.or.ddit.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * db.properties 파일의 내용으로 DB정보를 설정하는 방법
 * 방법 1) Properties 객체 이용하기
 * @author PC 10
 *
 */
public class JDBCUtil2 {
	static Properties prop; // Properties 객체변수 선언
	
	static {
		// 1. 드라이버 로딩(옵션)
		try {
			prop.load(new FileInputStream("res/db.properties"));
			
			Class.forName(prop.getProperty("driver"));
			
			System.out.println("드라이버 로딩 완료.");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패.");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("파일이 없거나 입출력 오류입니다.");
			e.printStackTrace();
		}
	}
	/**
	 * 커넥션
	 * @return Connection
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
			
					prop.getProperty("url"),
					prop.getProperty("user"),
					prop.getProperty("password"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 자원 반납
	 * @param conn
	 * @param stmt
	 * @param pstmt
	 * @param rs
	 */
	public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		if(rs != null) try {rs.close();} catch(SQLException ex) {}
		if(stmt != null) try {stmt.close();} catch(SQLException ex) {}
		if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
		if(conn != null) try {conn.close();} catch(SQLException ex) {}
	}
	
}






