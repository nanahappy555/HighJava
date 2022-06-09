package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil3;

public class MemberDAOImplForJDBC implements IMemberDAO {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static IMemberDAO memDao; //싱글톤. 인터페이스 기반(부모)
//	private static IMemberDAOImpl memDaoImpl; //싱글톤. 자기자신
//	List<?> list = new ArrayList<>();와 같은방식
	
	private MemberDAOImplForJDBC() {
		
	}
	
	public static IMemberDAO getInstance() {
		if(memDao == null) {
			memDao = new MemberDAOImplForJDBC();
			//객체가 한번도 안만들어졌을 때 자기자신객체 생성
		}
		
		return memDao;
	}
	
	@Override
	public int insertMember(MemberVO mv) {
		
		int cnt = 0; //작업성공여부를 반환해주는 cnt
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = " INSERT INTO mymember "
					+ " ( mem_id, mem_name, mem_tel, mem_addr, reg_dt ) "
					+ " VALUES ( ?, ?, ?, ?, sysdate ) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemId());
			pstmt.setString(2, mv.getMemName());
			pstmt.setString(3, mv.getMemTel());
			pstmt.setString(4, mv.getMemAddr());
			
			cnt = pstmt.executeUpdate();
			
			//view 역할이라서 지움
//			if(cnt > 0) {
//				System.out.println(memId + "회원 추가 작업 성공!");
//			}else {
//				System.out.println(memId + "회원 추가 작업 실패...");
//			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO mv) {
		
		int cnt = 0;
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "update mymember " 
					+ " set mem_name= ?, " 
					+ " mem_tel= ?, " 
					+ " mem_addr= ? " 
					+ " where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql); 
			//물음표를 실제값으로 넣어주는과정
			pstmt.setString(1, mv.getMemName());
			pstmt.setString(2, mv.getMemTel());
			pstmt.setString(3, mv.getMemAddr());
			pstmt.setString(4, mv.getMemId());
			
			cnt = pstmt.executeUpdate();
		
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "delete from mymember where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			//물음표세팅과정
			pstmt.setString(1, memId);
			
			//쿼리실행
			cnt= pstmt.executeUpdate();
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		//데이터를 VO에 담고 다시 List에 담아줘야됨. 회원이 10명이면 VO도 10개
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "select * from mymember";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql); //select=>Query
			
			while(rs.next()) {
				String memId = rs.getString("mem_id"); //getString으로 해당정보 뽑아옴
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				//뽑아온 정보를 VO에 담아줌
				MemberVO mv = new MemberVO();
				mv.setMemId(memId);
				mv.setMemName(memName);
				mv.setMemTel(memTel);
				mv.setMemAddr(memAddr);
				
				memList.add(mv); //ArrayList에 추가
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return memList;
	}

	@Override
	public boolean checkMember(String memId) {
boolean isExist = false;
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = " select count(*) as cnt " +  " from mymember where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt>0) {
				isExist = true;
			}else {
				isExist = false;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return isExist;
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		//리턴할 값이 List형태라서 arraylist먼저 선언초기화
		
		try {
			conn = JDBCUtil3.getConnection();
			
			//동적쿼리 (어떤 값을 입력하냐에 따라 다이나믹하게 바뀜
			String sql = "select * from mymember where 1=1 "; //항상 들어가는 sql문
			if(mv.getMemId() != null && !mv.getMemId().equals("")) {
				//memId가 not null && not "" empty string
				sql += " and mem_id = ? "; //sql문을 누적
			}
			if(mv.getMemName() != null && !mv.getMemName().equals("")) {
				sql += " and mem_name = ? ";
			}
			if(mv.getMemTel() != null && !mv.getMemTel().equals("")) {
				sql += " and mem_tel = ? ";
			}
			if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
				sql += " and mem_addr like '%' || ? || '%' ";
			}
			//최종 쿼리문 확인용
			System.out.println("최종 SQL : " + sql);
			
			pstmt = conn.prepareStatement(sql);
			
			int index = 1; //물음표 위치 지정.(sql은 1부터 count) ?갯수도 다이나믹하고 index도 유동적으로 바뀐다.
			if(mv.getMemId() != null && !mv.getMemId().equals("")) {
				pstmt.setString(index++, mv.getMemId());
			}
			if(mv.getMemName() != null && !mv.getMemName().equals("")) {
				pstmt.setString(index++, mv.getMemName());
			}
			if(mv.getMemTel() != null && !mv.getMemTel().equals("")) {
				pstmt.setString(index++, mv.getMemTel());
			}
			if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
				pstmt.setString(index++, mv.getMemAddr());
			}
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String memId = rs.getString("mem_id"); //getString으로 해당정보 뽑아옴
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				//뽑아온 정보를 VO에 담아줌. mv2인 이유: 매개변수가 mv라서. mv는 사용자가 검색을 위해 입력한 값
				MemberVO mv2 = new MemberVO();
				mv2.setMemId(memId);
				mv2.setMemName(memName);
				mv2.setMemTel(memTel);
				mv2.setMemAddr(memAddr);
				
				memList.add(mv2); //ArrayList에 추가
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return memList;
	}
}
