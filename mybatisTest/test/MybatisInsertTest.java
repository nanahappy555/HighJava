package kr.or.ddit.basic;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.member.vo.MemberVO2;
import kr.or.ddit.util.MybatisUtil;

public class MybatisInsertTest {
	public static void main(String[] args) {
		
		SqlSession sqlSession = MybatisUtil.getInstance(true); //true : autocommit
		
		MemberVO2 mv = new MemberVO2();
		mv.setMemName("채희진");
		mv.setMemTel("123-4567");
		mv.setMemAddr("대구시 수성구");
		
		int cnt = sqlSession.insert("member2.insertMember", mv);
		
		if(cnt>0) {
			System.out.println("등록 성공...");
			System.out.println("memId : " + mv.getMemId());
			
			//등록 성공하면 사용자에게 메일 발송
		}
	}
	
}
