package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

/**
 * Service 객체는 DAO에 설정된 메서드를 원하는 작업에 맞게 호출하여
 * 결과를 받아오고, 받아온 자료를 Controller에게 전달하는 역할을 수행한다.
 * 보통 DAO의 메서드와 같은 구조를 갖는다.
 * @author PC 10
 *
 */
public interface IMemberService {
	
	/**
	 * MemverVO에 담긴 데이터를 DB에 insert 시키는 메서드
	 * @param mv DB에 insert할 데이터가 저장된 MemberVO 객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고 실패하면 0이 반환된다.
	 */
	public int registMember(MemberVO mv);
	
	/**
	 * 하나의 MemberVO 데이터를 이용하여 회원정보를 업데이트하는 메서드
	 * @param mv update 할 회원정보가 들어있는 MemberVO 객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateMember(MemberVO mv);
	
	/**
	 * 회원ID를 매개변수로 받아서 그 회원 정보를 삭제하는 메서드
	 * @param memId 삭제할 회원ID
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int deleteMember(String memId);
	
	/**
	 * DB의 mymember테이블에 존재하는 전체 레코드를 가져와 List에 담아서 반환하는 메서드
	 * @return MemberVO객체를 담고있는 List 객체
	 */
	public List<MemberVO> getAllMemberList();
	
	/**
	 * 주어진 회원ID를 이용하여 회원정보 존재 여부를 체크하기위한 메서드
	 * @param memId 체크할 회원ID
	 * @return 해당 회원ID의 회원정보가 존재하면 true, 존재하지않으면 false
	 */
	public boolean checkMember(String memId);
	
	/**
	 * 주어진 회원정보를 이용하여 해당 회원정보를 검색하는 메서드 
	 * @param mv 검색에 사용할 회원정보
	 * @return 검색된 회원정보를 담은 리스트
	 */
	public List<MemberVO> searchMember(MemberVO mv);
	
}
