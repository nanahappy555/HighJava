package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.sql.RowSetInternal;

import kr.or.ddit.util.JDBCUtil;

/*
    LPROD 테이블에 새로운 데이터를 추가하기
    
    lprod_gu와 lprod_nm은 직접 입력받아 처리하고
    lprod_id는 현재의 lprod_id들 중 제일 큰 값보다 1 증가된 값으로 한다.
    (기타사항 : lprod_gu도 중복되는지 검사한다.)
    
    1. lprod_id의 max값을 select해서 가져와야함
 */
public class T04JdbcTest {
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Scanner scanner = new Scanner(System.in);
		
		try {
			//////////////////반복되는 부분 util class로 만들었음//////////////////////
			conn = JDBCUtil.getConnection();
			/*
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "LHR91";
			String password = "java";
			
			conn = DriverManager.getConnection(url, userId, password);
			*/
			//lprod_id 최대값+1/////////////////////////////////////////////////////////
			stmt = conn.createStatement();//select용
			
			//lprod_id의 최대값을 가져와서 1증가
			String sql= "select max(lprod_id) as lprodmax from lprod";
			
			rs = stmt.executeQuery(sql); //맥스값 받아올거라 	한번만 리턴
			
			int num = 0;
			while(rs.next()) {
				num = rs.getInt(1); //lprodmax
			}
			num++; //max + 1 
			
			int cnt = 0;
			
			////////////////////////////////////////////////////////////
			
			//gu 중복확인
			String sql2 = "select count(*) as cnt from lprod where lprod_gu = ?";
			
			pstmt = conn.prepareStatement(sql2);
			
			String gu = "";
			do {
				System.out.print("상품분류코드(lprod_gu)를 입력하세요");
				gu = scanner.next();
				pstmt.setString(1, gu);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					cnt = rs.getInt("cnt");
				}
				
				if(cnt > 0) { //cnt > 0이면 중복임
					System.out.println("상품 분류 코드" + gu + "은(는)" + " 이미 있는 상품입니다.");
					System.out.println("다시 입력하세요.");
					System.out.println();
				}
				
			}while(cnt > 0); //cnt가 0일때까지 돈다. (중복이 아닌 값을 입력할때까지)cnt는 실행 건수만큼의 정수로 나온다 (1건 => 1)
			
			////////////////////////////////////////////////////////
			//nm입력
			System.out.println("상품이름(lprod_nm)을 입력하세요");
			String nm = scanner.next();
			
			scanner.close();
			
			//insert
			sql2 = " insert into lprod (lprod_id, lprod_gu, lprod_nm) " 
				    + " values (?, ?, ?) ";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1,num);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);
			
			cnt = pstmt.executeUpdate(); //쿼리 실행
			
			if(cnt > 0) {
				System.out.println(gu + "를 추가했습니다.");
			}else {
				System.out.println(gu + "를 추가하는데 실패했습니다.");
			}
			
			System.out.println("작업 끝...");
		}catch(SQLException ex) {
			ex.printStackTrace();
		//} catch (ClassNotFoundException ex) {
		//	ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
			/*
			if(rs != null) try {rs.close();} catch(SQLException ex) {}
			if(stmt != null) try {stmt.close();} catch(SQLException ex) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
			if(conn != null) try {conn.close();} catch(SQLException ex) {}
			*/
		}
		
	}
}
