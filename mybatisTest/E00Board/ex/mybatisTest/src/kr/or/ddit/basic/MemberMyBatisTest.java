package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.member.vo.MemberVO;

public class MemberMyBatisTest {
	public static void main(String[] args) {
		
		// mybatis를 이용하여 DB자료를 처리하는 작업 순서
		// 1. mybatis의 환경설정파일을 읽어와 실행시킨다.
		
		SqlSession sqlSession = null;
		
		try {
			// 1-1. xml문서 읽어오기
			// 설정파일의 인코딩 설정(한글처리를 위해서...)
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("mybatis-config.xml");
			
			// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성하기.
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(rd);
			sqlSession = sessionFactory.openSession();
			rd.close(); // Reader 객체 닫기
		
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
	// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.
	
	// 2-1. INSERT 작업 연습
	System.out.println("INSERT 작업 시작");
	
	// 1) 저장할 데이터를 VO에 담는다.
	MemberVO mv = new MemberVO();
	mv.setMemId("d001");
	mv.setMemName("강감찬");
	mv.setMemTel("1111-3333");
	mv.setMemAddr("경남 사천시");
	
	// 2) SqlSession 객체를 이용하여 해당 쿼리문을 실행한다.
	//    형식) mybatis.insert("namespace값.id값", 파라미터객체);
	//        반환값 : 성공하면 int 값이 반환된다.
	
	try {
		int cnt = sqlSession.insert("memberTest.insertMember", mv);
		
		if(cnt > 0) {
			System.out.println("INSERT 작업 성공");
			sqlSession.commit();
		} else {
			System.out.println("INSERT 작업 실패");
		}
	} catch(PersistenceException ex) {
		ex.printStackTrace();
	} 
	System.out.println("------------------------------------------------");
	
	// 2-2. update작업 연습
	System.out.println("UPDATE 작업 시작");
	
	mv = new MemberVO();
	mv.setMemId("d001");
	mv.setMemName("이순신");
	mv.setMemTel("2222-2222");
	mv.setMemAddr("서울시 영등포구");
	
	// update의 반환값 : 성공한 레코드 수
	int cnt = sqlSession.update("memberTest.updateMember", mv);
	
	if(cnt > 0) {
		System.out.println("UPDATE 작업 성공");
		sqlSession.commit();
	} else {
		System.out.println("UPDATE 작업 실패");
		sqlSession.rollback();
	}
	System.out.println("------------------------------------------------");
	
	// 2-3. delete 연습
	System.out.println("DELETE 작업 시작");
	
	// delete의 반환값 : 성공한 레코드 수
	cnt = sqlSession.delete("memberTest.deleteMember", "d001");
	
	if(cnt > 0) {
		System.out.println("DELETE 작업 성공");
		sqlSession.commit();
	} else {
		System.out.println("DELETE 작업 실패");
		sqlSession.rollback();
	}
	System.out.println("------------------------------------------------");
	
	// 2-4. select 연습
	// 1) 응답의 결과가 여러개일 경우
	System.out.println("SELECT 작업 시작 - 결과값이 여러개일 경우");
	
	List<MemberVO> memList = null;
	
	/*
	 * 	1) 응답결과가 여러개 일 경우에는 selectList() 메서드를 이용한다.
	 *  이 메서드는 여러개의 레코드를 VO에 담은 후 이 VO데이터를 
	 *  List에 추가해주는 작업을 자동으로 수행한다.
	 */
	
	memList = sqlSession.selectList("memberTest.getMemberAll2");
	
	for(MemberVO mv2 : memList) {
		System.out.println("------------------------------------------------");
		System.out.println(" ID   : " + mv2.getMemId());
		System.out.println(" 이름 : " + mv2.getMemName());
		System.out.println(" 전화 : " + mv2.getMemTel());
		System.out.println(" 주소 : " + mv2.getMemAddr());
		System.out.println("------------------------------------------------");
	}
	System.out.println(" 출력 완료.");
	System.out.println("------------------------------------------------");
	
	// 2) 응답결과가 1개인 경우
	System.out.println("SELECT 작업 시작 - 결과값이 1개인 경우");
	
	// selectOne() 메서드를 이용한다.
	MemberVO mv3 = (MemberVO) sqlSession.selectOne("memberTest.getMember", "a001");
	
	System.out.println("------------------------------------------------");
	System.out.println(" ID   : " + mv3.getMemId());
	System.out.println(" 이름 : " + mv3.getMemName());
	System.out.println(" 전화 : " + mv3.getMemTel());
	System.out.println(" 주소 : " + mv3.getMemAddr());
	System.out.println("------------------------------------------------");
	System.out.println(" 출력 완료.");
	System.out.println("------------------------------------------------");
	
	}

}
