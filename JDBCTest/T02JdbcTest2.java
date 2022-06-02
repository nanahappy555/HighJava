package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class T02JdbcTest2 {
/*
 * 문제1) 사용자로부터 lprod_id값을 입력받아 입력한 값보다 lprod_id가
 * 큰 자료들을 출력하시오
 * 
 * 문제2) lprod_id값을 2개 입력 받아서 두 값 중 작은 값부터 큰값 사이의 자료를 출력하시오.
 */
	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement prsmt = null;
		ResultSet rs = null;
		
		Scanner scan = new Scanner(System.in);
		System.out.print("첫번째 lprod_id를 입력하세요");
		int lpId1 = scan.nextInt();
		System.out.print("두번째 lprod_id를 입력하세요");
		int lpId2 = scan.nextInt();
		
		int max, min;
		max = Math.max(lpId1, lpId2); //두 매개변수 중 큰 값을 저장하는 메소드
		min = Math.min(lpId1, lpId2);
		
		try {
			String url = "jdbc:oracle:thin:@localhost/xe";
			String userId ="LHR91";
			String password = "java";

			//커넥션객체(변수)에 담기
			conn = DriverManager.getConnection(url, userId,password);
			
			//객체생성
			stmt = conn.createStatement();
			
			//쿼리문 작성
			String sql = "select * from lprod "
				+ " where lprod_id >= " + min + " and lprod_id <=" + max;
			
			rs = stmt.executeQuery(sql);
			
			System.out.println("실행한 쿼리문 : " + sql);
			System.out.println("=== 쿼리문 실행결과 ===");
			
			
			while(rs.next()) {
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString(2));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("-------------------------");
			}
			System.out.println("출력 끝...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//자원반납
			if(rs != null) try {rs.close();} catch(SQLException ex) {}
			if(stmt != null) try {stmt.close();} catch(SQLException ex) {}
			if(conn != null) try {conn.close();} catch(SQLException ex) {}
		}
	}
}
