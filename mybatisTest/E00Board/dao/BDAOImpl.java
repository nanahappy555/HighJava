package E00Board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import E00Board.vo.BoardVO;
import kr.or.ddit.util.MybatisUtil;

public class BDAOImpl implements BoardDAO{

	private static BoardDAO boardDao;
	private SqlSession sqlSession; //mybatis
	
	private BDAOImpl() {
		sqlSession = MybatisUtil.getInstance(); 
	}
	
	public static BoardDAO getInstance() {
		if(boardDao == null) {
			boardDao = new BDAOImpl();
		}
		return boardDao;
	}
	
	@Override
	public List<BoardVO> getPostAll() {
		List<BoardVO> bList = sqlSession.selectList("board1.getPostAll");
		return bList;
	}

	@Override
	public List<BoardVO> searchPost(BoardVO bv) {
		List<BoardVO> bList = sqlSession.selectList("board1.searchPost", bv);
		return bList;
	}

	@Override
	public int insertPost(BoardVO bv) {
		int cnt = sqlSession.insert("board1.insertPost", bv);
		if(cnt > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		return cnt;
	}

	@Override
	public int updatePost(BoardVO bv) {
		int cnt = sqlSession.update("board1.updatePost", bv);
		if(cnt > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		return cnt;
	}

	@Override
	public int deletePost(int bNo) {
		int cnt = sqlSession.delete("board1.deletePost", bNo);
		if(cnt > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		return cnt;
	}

	@Override
	public boolean checkPost(String bWriter) {
		boolean isExist = false;
		
		int cnt = (int) sqlSession.selectOne("board1.checkPost", bWriter);
		
		//게시글 3개 제한을 넘기면 true반환(더이상 글을 등록할 수 없음
		if(cnt >= 3) {
			isExist = true;
		}
		
		return isExist;
	}

}
