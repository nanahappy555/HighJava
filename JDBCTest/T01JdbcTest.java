package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class T01JdbcTest {
/*
  JDBC를 이용한 데이터베이스 처리 순서
    
  순서 : JDBC드라이버 로딩 -> 해당 DB에 접속 -> 질의(SQL명령을 수행)
         -> 질의 결과를 받아서 처리한다. -> 종료(자원반납)
         
   1. JDBC드라이버 로딩
     => JDBC드라이버는 DB를 만든 회사에서 제공한다.
     Class.forName("oracle.jdbc.driver.OracleDriver"); 
     
   2. 접속하기 : 접속이 성공하면 Connection객체가 생성된다.
       DriverManager.getConnection() 메서드 이용한다.
       
   3. 질의 : Statement객체 또는 PreparedStatement객체를 이용하여
             SQL문장을 실행한다.
             
   4. 결과 : 
      1) SQL문이 select일 경우 => ResultSet 객체가 만들어진다.
         ResultSet 객체에는 select한 결과가 저장된다.
      2) SQL문이 insert, update, delete 일 경우 => 정수값을 반환한다.
        (정수값은 보통 실행에 성공한 레코드 수를 말한다.)
*/
	public static void main(String[] args) {
		
		//JDBC 작업에 필요한 객체변수 선언 (4개의 인터페이스로 작업)
		//JDBC에 필요한 기능을 모두 설계구현해둔 인터페이스라서 변수로만 핸들링하면 됨! 
		//우리는 어떻게 설계됐는지 알 필요 없어~
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement prstmt = null;
		ResultSet rs = null;
		
		try {
			//1. 드라이버 로딩 확인 (필수과정x)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//(리플렉션)
			
			//2. DB에 접속연결 (Connection 객체 생성)
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "LHR91";
			String password = "java";
			
			//커넥션 객체에 담아야 해서 아까 만들어둔 커넥션 객체(변수)에 담는다 좌항=커넥션객체 리턴
			conn = DriverManager.getConnection(url, userId, password);
			
			//3. Statement객체 생성 => Connection객체를 이용한다.
			//prepared는 미리 준비해놓고 파라미터로 넣어줌
			stmt = conn.createStatement();
			
			//4. SQL문을 Statement객체를 이용하여 실행하고 
			//실행결과를 ResultSet객체에 저장한다.
			String sql = "select * from lprod"; //실행할 쿼리문
			
			//SQL문이 select인 경우 executeQuery()메서드를 사용한다. (데이터를 담은 ResultSet 객체가 만들어진다)
			// 그외의 경우에는 executeUpdate()메서드를 사용한다. (int값 리턴)
			rs = stmt.executeQuery(sql); //쿼리날려..
			
			//5. ResultSet객체에 저장되어 있는 데이터를 반복문과
			// 	 next()메서드를 이용하여 차례대로 읽어와 처리한다.
			System.out.println("실행한 쿼리문 : " + sql);
			System.out.println("=== 쿼리문 실행결과 ===");
			
			//rs.next() => ResultSet객체의 데이터를 가리키는 포인터를 다음 레코드로 이동 시키고
			//			 그 곳에 데이터가 있으면 true, 없으면 false를 반환한다.
			while(rs.next()) {
				//컬럼의 자료를 가져오는 방법
				//방법1) rs.get자료형이름("컬럼명")
				//방법2) rs.get자료형이름(컬럼번호) => 컬럼번호는 1부터 시작
				System.out.println("lprod_id : " + rs.getInt("lprod_id")); //Number
//				System.out.println("lprod_gu : " + rs.getString("lprod_gu")); //VARCHAR
				System.out.println("lprod_gu : " + rs.getString(2)); //lprod의 2번째 컬럼
			}
			System.out.println("출력 끝...");
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			//6. **종료** (사용했던 자원을 모두 반납한다. 자원반납은 반드시해줘야됨.안하면 오라클에서 문제 생김. 반드시 해야해서 finally에 씀)
			if(rs != null) try {rs.close();} catch(SQLException ex) {}
			if(stmt != null) try {stmt.close();} catch(SQLException ex) {}
			if(conn != null) try {conn.close();} catch(SQLException ex) {}
			//PreparedStatement는 사용 안해서 얘는 close 안했음
		}
	
	}
}
