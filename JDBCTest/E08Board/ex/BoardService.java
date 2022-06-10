package homework;

import java.util.List;

public interface BoardService {

	public int registBoard(BoardVO bv);
	
	public int updateBoard(BoardVO bv);
	
	public int deleteBoard(String boardWriter);
	
	public List<BoardVO> getAllBoardList();
	
	public boolean checkBoard(String boardWriter);
	
	public List<BoardVO> searchBoard(BoardVO bv);
	
}
