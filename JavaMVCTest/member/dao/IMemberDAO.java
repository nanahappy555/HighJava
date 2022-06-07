package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 조회한 후
 * 서비스에 전달하는 DAO의(데이터액세스오브젝트)의 인터페이스
 * @author PC-17
 *
 */
public interface IMemberDAO {
	/**
	 * MemberVO에 담긴 데이터를 DB에 insert하는 메서드
	 * @param mv DB에 inser할 데이터가 저장된 MemberVO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고 실패하면 0이 반환됨
	 */
//	public int insertMember(String memId, String memName, String memTel, String memAddr);
	//파라미터가 컬럼 갯수만큼 길어져서 좋지 못한 방법 => MemberVO 클래스 따로 생성
	public int insertMember(MemberVO mv);
	
	/**
	 * 하나의 MemberVO 데이터를 이용하여 회원정보를 업데이트하는 메서드
	 * @param mv update할 회원정보가 들어있는 MemberVO 객체
	 * @return 작업 성공 : 1 / 작업 실패 : 0
	 */
	public int updateMember(MemberVO mv);
	
	/**
	 * 회원ID를 매개변수로 받아서 그 회원 정보를 삭제하는 메서드
	 * @param memId 삭제할 회원ID
	 * @return 작업 성공 : 1 / 작업 실패 : 0
	 */
	public int deleteMember(String memId);
	
	/**
	 * DB의 mymember테이블에 존재하는 전체 레코드를 가져와 
	 * List에 담아서 반환하는 메서드
	 * @return MemberVO객체를 담고 있는 List 객체
	 */
	public List<MemberVO> getAllMemberList();
	
	/**
	 * 주어진 회원ID를 이용하여 회원정보가 존재하는지 체크하기 위한 메서드
	 * @param memId 체크할 회원ID
	 * @return 해당 회원ID의 회원정보가 존재하면 true, 없으면 false
	 */
	public boolean checkMember(String memId);
	
	/**
	 * 주어진 회원정보를 이용하여 해당 회원을 검색하는 메서드
	 * @param mv 검색에 사용할 회원정보
	 * @return 검색된 회원정보를 담은 리스트
	 */
	public List<MemberVO> searchMember(MemberVO mv);
	
}
