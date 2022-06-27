package E00Board.service;

import java.util.List;

import E00Board.dao.BDAOImpl;
import E00Board.dao.BDAOImpl2;
import E00Board.dao.BoardDAO;
import E00Board.vo.BoardVO;

public class BServiceImpl implements BoardService{
	private BoardDAO bDao;
	private static BoardService bService;
	
	private  BServiceImpl() {
		bDao = BDAOImpl.getInstance();
	}
	
	public static BoardService getInstance() {
		if(bService == null) {
			bService = new BServiceImpl();
		}
		return bService;
	}
	
	@Override
	public List<BoardVO> getPostAll() {
		List<BoardVO> bList = bDao.getPostAll();
		return bList;
	}

	@Override
	public List<BoardVO> searchPost(BoardVO bv) {
		List<BoardVO> bList = bDao.searchPost(bv);
		return bList;
	}

	@Override
	public int registPost(BoardVO bv) {
		int cnt = bDao.insertPost(bv);
		return cnt;
	}
  
	@Override
	public int updatePost(BoardVO bv) {
		int cnt = bDao.updatePost(bv);
		return cnt;
	}

	@Override
	public int deletePost(int bNo) {
		int cnt = bDao.deletePost(bNo);
		return 0;
	}

	@Override
	public boolean checkPost(String bWriter) {
		boolean isExist = bDao.checkPost(bWriter);
		return isExist;
	}

}
