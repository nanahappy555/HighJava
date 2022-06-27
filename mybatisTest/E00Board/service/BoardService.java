package E00Board.service;

import java.util.List;

import E00Board.vo.BoardVO;

public interface BoardService {

	/**
	 * 전체글 출력 메서드
	 * @return
	 */
	public List<BoardVO> getPostAll();
	
	/**
	 * 글을 검색하는 메서드
	 * @param bv List에 담아서 반환하는 boardVO List
	 * @return
	 */
	public List<BoardVO> searchPost(BoardVO bv);
	
	/**
	 * BoardVO에 담긴 데이터를 DB에 insert하는 메서드
	 * @param bv DB에 insert할 데이터가 저장된 BoardVO bv
	 * @return
	 */
	public int registPost(BoardVO bv);
	
	/**
	 * 하나의 BoardVO 데이터를 이용하여 게시글 업데이트(수정)하는 메서드
	 * @param bv
	 * @return
	 */
	public int updatePost(BoardVO bv);
	
	/**
	 * 글번호를 매개변수로 받아서 그 게시글을 삭제하는 메서드
	 * @param bNo 글
	 * @return
	 */
	public int deletePost(int bNo);
	
	/**
	 * 존재하는 포스트인지 작성자 이름으로 확인하는 메서드
	 * @param bWriter 작성자 이름
	 * @return
	 */
	public boolean checkPost(String bWriter);
}
