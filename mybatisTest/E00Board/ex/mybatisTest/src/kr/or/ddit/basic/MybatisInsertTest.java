package kr.or.ddit.basic;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.member.vo.MemberVO2;
import kr.or.ddit.util.MyBatisUtil;

public class MybatisInsertTest {
	public static void main(String[] args) {
		
		SqlSession sqlSession = MyBatisUtil.getInstance(true);
		
		MemberVO2 mv = new MemberVO2();
		
		mv.setMemName("심재린");
		mv.setMemTel("010-0000-0003");
		mv.setMemAddr("경기 평택시");
		
		int cnt = sqlSession.insert("member2.insertMember", mv);
		
		if(cnt > 0) {
			System.out.println("등록 성공.");
			
			System.out.println("memId => " + mv.getMemId());
			
			// 메일 발송
		} else {
			System.out.println("등록 실패.");
		}
		
	}

}
