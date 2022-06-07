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
 * db.properties파일의 내용으로 DB정보를 설정하는 방법
 * 방법1) Properties 객체 이용하기 (properties파일만 수정하면 되니까 유지보수가 쉬워짐)
 * @author PC-17
 *
 */
public class JDBCUtil2 {
	static Properties prop; //Properties객체변수 선언
	
	static {
		prop = new Properties(); //객체 생성
		
		// 1. 드라이버 로딩 확인 (필수과정x)
		try {
			prop.load(new FileInputStream("res/db.properties"));
			
			Class.forName(prop.getProperty("driver")); //oracle.jdbc.driver.OracleDriver

			//System.out.println("드라이버 로딩 완료");
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패..."); // 굳이 없어도 되는 부분
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("파일이 없거나 입출력 오류입니다.");
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
					prop.getProperty("url"), 
					prop.getProperty("user"), 
					prop.getProperty("password"));
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
