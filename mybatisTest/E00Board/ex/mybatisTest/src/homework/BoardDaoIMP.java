package homework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.JDBCUtil3;
import kr.or.ddit.util.MyBatisUtil;

public class BoardDaoIMP implements BoardDAO {
	
	private static BoardDAO boardDao;
	private SqlSession sqlSession;
	
	private BoardDaoIMP() {
		sqlSession = MyBatisUtil.getInstance();
	}
	
	public static BoardDAO getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDaoIMP();
		}
		return boardDao;
	}

	@Override
	public int insertBoard(BoardVO bv) {
		int cnt = sqlSession.insert("jdbcboard.insertBoard", bv);
		if(cnt > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = sqlSession.update("jdbcboard.updateBoard", bv);
		if(cnt > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = sqlSession.delete("jdbcboard.deleteBoard", boardNo);
		if(cnt > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		List<BoardVO> boardList = sqlSession.selectList("jdbcboard.getAllBoardList");
		return boardList;
	}

	@Override
	public boolean checkBoard(String boardWriter) {
		boolean isExist = false;
		
		int cnt = (int) sqlSession.selectOne("jdbcboard.checkBoard", boardWriter);
		
		if(cnt >= 3) {
			isExist = true;
		}
		return isExist;
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		List<BoardVO> boardList = sqlSession.selectList("jdbcboard.searchBoard", bv);
		return boardList;
	}

}
