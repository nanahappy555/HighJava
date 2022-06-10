package homework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.util.JDBCUtil3;

public class BoardDaoIMP implements BoardDAO {
	
	private static BoardDAO boardDao;
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private BoardDaoIMP() {
		
	}
	
	public static BoardDAO getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDaoIMP();
		}
		return boardDao;
	}

	@Override
	public int insertBoard(BoardVO bv) {
		int cnt = 0;
		try {
			 conn = JDBCUtil3.getConnection();
			 
			 String sql = "INSERT INTO JDBC_BOARD (board_no, board_title, board_writer, board_date, board_content) " +
		                	"VALUES(board_seq.nextVal, ? , ? , sysdate, ? )";
			 
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, bv.getbTitle());
			 pstmt.setString(2, bv.getbWriter());
			 pstmt.setString(3, bv.getbContent());
			 
			 cnt = pstmt.executeUpdate();
					 
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql2 = "update jdbc_board set board_title = ?, board_writer = ?, board_content = ? ";
			   
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, bv.getbTitle());
			pstmt.setString(2, bv.getbWriter());
			pstmt.setString(3, bv.getbContent());
			
			cnt = pstmt.executeUpdate();
			 
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int deleteBoard(String boardWriter) {
		int cnt = 0;
		int primaryKey = 0;
		try {
			 conn = JDBCUtil3.getConnection();
			 
			 String sql2 = "select * from jdbc_board where board_writer = '" + boardWriter + "'";
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery(sql2);
			 
			 if(rs.next()) {
				 primaryKey = rs.getInt("board_no");
			 }
			 
			 String sql3 = "delete from jdbc_board where board_no = " + primaryKey;
			 stmt = conn.createStatement();
			 cnt = stmt.executeUpdate(sql3);
			 
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			 conn = JDBCUtil3.getConnection();
			 
			 String sql = "select * from jdbc_board";
			 
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery(sql);
			 
			 while(rs.next()) {
				 int bNo = rs.getInt("board_no");
				 String bTitle = rs.getString("board_title");
				 String bWriter = rs.getString("board_writer");
				 String bDate = rs.getString("board_date");
				 String bContent = rs.getString("board_content");
				 
				 BoardVO bv = new BoardVO();
				 bv.setbNo(bNo);
				 bv.setbTitle(bTitle);
				 bv.setbWriter(bWriter);
				 bv.setbDate(bDate);
				 bv.setbContent(bContent);
				 
				 boardList.add(bv);
			 }
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return boardList;
	}

	@Override
	public boolean checkBoard(String boardWriter) {
		boolean isExist = false;
		int primaryKey = 0;
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql1 = "select * from jdbc_board where board_writer = '" + boardWriter + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			
			while(rs.next()) {
				primaryKey = rs.getInt("board_no");
			}
			
			String sql2 = "select count(*) as cnt from jdbc_board where board_no = " + primaryKey;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql2);
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if(cnt > 0) {
				isExist = true;
			} else {
				isExist = false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return isExist;
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "select * from jdbc_board where 1=1 ";
			
			if(bv.getbWriter() != null && !bv.getbWriter().equals("")) {
				sql += " and board_writer = ? ";
			}
			if(bv.getbTitle() != null && !bv.getbTitle().equals("")) {
				sql += " and board_title like '%' || ? || '%' ";
			}
			if(bv.getbContent() != null && !bv.getbContent().equals("")) {
				sql += " and board_content like '%' || ? || '%' ";
			}
			if(bv.getbDate() != null && !bv.getbDate().equals("")) {
				sql += " and board_date between to_date(?)";
			}
			if(bv.getbEndDate() != null && !bv.getbEndDate().equals("")) {
				sql += " and to_date(?)";
			} else {
				sql += " and sysdate";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			int index = 1;

			if(bv.getbWriter() != null && !bv.getbWriter().equals("")) {
				pstmt.setString(index++, bv.getbWriter());
			}
			if(bv.getbTitle() != null && !bv.getbTitle().equals("")) {
				pstmt.setString(index++, bv.getbTitle());
			}
			if(bv.getbContent() != null && !bv.getbContent().equals("")) {
				pstmt.setString(index++, bv.getbContent());
			}
			if(bv.getbDate() != null && !bv.getbDate().equals("")) {
				pstmt.setString(index++, bv.getbDate());
			}
			if(bv.getbEndDate() != null && !bv.getbEndDate().equals("")) {
				pstmt.setString(index++, bv.getbEndDate());
			}
	
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bNo = rs.getInt("board_no");
				String bWriter = rs.getString("board_writer");
				String bTitle = rs.getString("board_title");
				String bContent = rs.getString("board_content");
				String bDate = rs.getString("board_date");
				
				BoardVO bv2 = new BoardVO();
				bv2.setbNo(bNo);
				bv2.setbWriter(bWriter);
				bv2.setbTitle(bTitle);
				bv2.setbContent(bContent);
				bv2.setbDate(bDate);
				
				boardList.add(bv2);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return boardList;
	}

}
