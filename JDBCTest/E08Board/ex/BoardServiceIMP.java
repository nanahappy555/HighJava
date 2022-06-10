package homework;

import java.util.List;

public class BoardServiceIMP implements BoardService {
	
	private static BoardService boardService;
	private BoardDAO boardDao;
	
	private BoardServiceIMP() {
		boardDao = BoardDaoIMP.getInstance();
	}
	
	public static BoardService getInstance() {
		if(boardService == null) {
			boardService = new BoardServiceIMP();
		}
		return boardService;
	}
	
	@Override
	public int registBoard(BoardVO bv) {
		int cnt = boardDao.insertBoard(bv);
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = boardDao.updateBoard(bv);
		return cnt;
	}

	@Override
	public int deleteBoard(String boardWriter) {
		int cnt = boardDao.deleteBoard(boardWriter);
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		List<BoardVO> boardList = boardDao.getAllBoardList();
		return boardList;
	}

	@Override
	public boolean checkBoard(String boardWriter) {
		boolean isExist = boardDao.checkBoard(boardWriter);
		return isExist;
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		List<BoardVO> boardList = boardDao.searchBoard(bv);
		return boardList;
	}

}
