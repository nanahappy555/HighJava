package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	//Service에는 DAO만 있으면 됨. 작업은 DAO가 하고 Service는 리턴값만 받으면 됨
	
	private IMemberDAO memDao;
	
	public MemberServiceImpl() {
		memDao = new MemberDAOImpl(); //호출되고나면 DAO가 생성. DAO를 쓸 수 있는 상태가 됨.
	}
	
	@Override
	public int registMember(MemberVO mv) {
		
		//주민등록번호 암호화 처리하기
		
		int cnt = memDao.insertMember(mv);
		
		//해당 사용자에 회원정보 등록 완료 메일 발송하기
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = memDao.updateMember(mv);
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = memDao.deleteMember(memId);
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		List<MemberVO> memList = memDao.getAllMemberList();
		return memList;
	}

	@Override
	public boolean checkMember(String memId) {
		boolean isExist = memDao.checkMember(memId);
		return isExist;
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		List<MemberVO> memList = memDao.searchMember(mv);
		return memList;
	}

}
