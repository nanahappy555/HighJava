package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.member.dao.MemberDAOImplForJDBC;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	
	private static IMemberService memService;
	private IMemberDAO memDao;
	
	private MemberServiceImpl() {
		memDao = MemberDAOImpl.getInstance();
	}
	
	public static IMemberService getInstance() {
		if(memService == null) {
			memService = new MemberServiceImpl();
		}
		return memService;
	}

	@Override
	public int registMember(MemberVO mv) {
		// 주민등록번호 암호화 처리하기
		int cnt = memDao.insertMember(mv);
		// 해당 사용자에 회원정보 등록 완료 메일 발송하기
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
