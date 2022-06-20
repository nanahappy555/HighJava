package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.MyBatisUtil;

public class MemberDAOImpl implements IMemberDAO {
	
	private static IMemberDAO memDao;
	private SqlSession sqlSession;
	
	private MemberDAOImpl() {
		sqlSession = MyBatisUtil.getInstance();
	}
	
	public static IMemberDAO getInstance() {
		if(memDao == null) {
			memDao = new MemberDAOImpl();
		}
		return memDao;
	}

	@Override
	public int insertMember(MemberVO mv) {
		int cnt = sqlSession.insert("member.insertMember", mv);
		
		if(cnt > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = sqlSession.update("member.updateMember", mv);
		
		if(cnt > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = sqlSession.delete("member.deleteMember", memId);
		
		if(cnt > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		List<MemberVO> memList = sqlSession.selectList("member.getMemberAll");
		return memList;
	}

	@Override
	public boolean checkMember(String memId) {
		
		boolean isExist = false;
		
		int cnt = (int) sqlSession.selectOne("member.checkMember", memId);
		
		if(cnt > 0) {
			isExist = true;
		}
		return isExist;
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		List<MemberVO> memList = sqlSession.selectList("member.searchMember", mv);
		return memList;
	}

}
