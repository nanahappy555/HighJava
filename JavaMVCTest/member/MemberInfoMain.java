package kr.or.ddit.member;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

/*
    
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력	---> select
		5. 작업 끝.
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128),    -- 주소
    reg_dt DATE DEFAULT sysdate, -- 등록일
    CONSTRAINT MYMEMBER_PK PRIMARY KEY (mem_id)
);

*/
/**
 * MVC 패턴과 유사하게 만들어보기
 * view & controller
 * @author PC-17
 *
 */
public class MemberInfoMain {
	//JDBC작업은 DAO가 해서 필요없음
//	private Connection conn;
//	private Statement stmt;
//	private PreparedStatement pstmt;
//	private ResultSet rs;
	
	private IMemberService memService;
	private Scanner scan = new Scanner(System.in); 
	
	public MemberInfoMain() {
		memService = new MemberServiceImpl(); //service객체 생성
	}
	
	/**
	 * 메뉴를 출력하는 메서드. view에서 필요함
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 검색");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드 Controller
	 */
	public void start(){
		int choice = 0;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertMember();
					break;
				case 2 :  // 자료 삭제
					deleteMember();
					break;
				case 3 :  // 자료 수정
					updateMember();
					break;
				case 4 :  // 전체 자료 출력
					displayMemberAll();
					break;
				case 5 : //회원 검색
					searchMember();
					break;
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6); //6이 아닐때는 계속 while 돈다
	}
	
	/**
	 * 회원정보를 검색하는 메서드
	 */
	private void searchMember() {
		/*
		 검색할 회원ID, 회원이름, 전화번호, 주소 등을 입력하면
		 입력한 정보만 사용하여 검색하는 기능을 구현하시오.
		 주소는 입력한 값이 포함만 되어도 검색 되도록 한다. (sql like)
		 입력을 하지 않을 자료는 엔터키로 다음 입력으로 넘긴다. //입력한 내용이 없어도 엔터만 치면 넘어가도록 nextLine()
		*/
		scan.nextLine(); //혹시 버퍼에 뭔가 남아있을지도 모르니까 일단 비움
		System.out.println();
		System.out.println("검색할 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.nextLine().trim(); //공백이 들어가도 스캐너가 넘어갈 수 있으니 공백제거메서드도 함께 사용
		
		System.out.print("회원 이름 >> ");
		String memName = scan.nextLine().trim();
		
		System.out.print("회원 전화번호 >> ");
		String memTel = scan.nextLine().trim();
		
		System.out.print("회원 주소 >> ");
		String memAddr = scan.nextLine().trim();
		
		MemberVO mv = new MemberVO(); //입력한 값을 셋팅
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr); //'대전'만 검색하면 '대전'만 저장됨
		
		//검색기능 호출
		//키워드로 검색하면 결과가 여러건이 나올 수 있으니까 List로 받음
		List<MemberVO> memList = memService.searchMember(mv); //dao에서 sql실행한 결과가 담김
		
		System.out.println("---------------------------------------------");
		System.out.println(" ID\t이 름\t전화번호\t\t주  소");
		System.out.println("---------------------------------------------");
		
		//가져온 데이터 확인용
		if(memList.size() == 0) {
			System.out.println("검색된 회원 정보가 없습니다.");
		}else {
			for(MemberVO mv2 : memList) {
				System.out.println(mv2.getMemId() + "\t" + mv2.getMemName() + "\t"
						+ mv2.getMemTel() + "\t\t" + mv2.getMemAddr());
			}
		}
		System.out.println("---------------------------------------------");
		System.out.println("회원 검색 끝...");
		
	}

	/**
	 * 회원정보 삭제
	 */
	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		System.out.print("회원 ID >> ");
		
		String memId = scan.next();
		
		/*
		 * service가 DAO에게 JDBC 작업을 시킴
		 */
		int cnt = memService.deleteMember(memId);
		
		if(cnt > 0) {
			System.out.println(memId + "회원정보 삭제 성공");
		}else {
			System.out.println(memId + "회원정보 삭제 실패...");
		}
	}

	/**
	 * 회원정보 수정 메서드
	 * insert와 거의 비슷...
	 */
	private void updateMember() {
		String memId = "";
		boolean isExist = false; //중복체크용
		
		//중복체크 (do먼저 실행->isExist ==true (회원정보가 있음)일 경우 반복)
		do {
			System.out.println();
			System.out.println("수정할 회원 정보를 입력하세요.");
			System.out.print("회원 ID >> ");
			
			memId = scan.next();
			
			//존재하는지 체크
			isExist = checkMember(memId);
			
			//isExist == false와 같은 코드
			if(!isExist) { 
				System.out.println("회원ID가 " + memId + "인 회원은 "
						+ "존재하지 않습니다.");
				System.out.println("다시 입력해 주세요.");
			}
		}while(!isExist);
		
		System.out.print("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.print("회원 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine(); // 입력버퍼 비우기
		
		System.out.print("회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		/*
		 * try-catch-finally가 있던 부분
		 * JDBC작업은 DAO가 함
		 */
		
		//입력받은 정보를 MemberVO에 setter메서드로 저장
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		
		int cnt = memService.updateMember(mv);
		
		if(cnt > 0) {
			System.out.println(mv.getMemId() + "회원정보 수정 완료");
		}else {
			System.out.println(mv.getMemId() + "회원정보 수정 실패...");
		}
		
	}

	/**
	 * 전체 회원정보를 출력하는 메서드
	 */
	private void displayMemberAll() {
		System.out.println("---------------------------------------------");
		System.out.println(" ID\t이 름\t전화번호\t\t주  소");
		System.out.println("---------------------------------------------");
		
		List<MemberVO> memList = memService.getAllMemberList();
		//가져온 데이터 확인용
		if(memList.size() == 0) {
			System.out.println("회원 정보가 없습니다.");
		}else {
			for(MemberVO mv : memList) {
				System.out.println(mv.getMemId() + "\t" + mv.getMemName() + "\t"
						+ mv.getMemTel() + "\t\t" + mv.getMemAddr());
			}
		}
		
		System.out.println("---------------------------------------------");
		System.out.println("출력 작업 끝...");
		
	}

	/**
	 * 회원정보 추가 메서드
	 */
	private void insertMember() {
		String memId = "";
		boolean isExist = false; //중복체크용
		
		//중복체크 (do먼저 실행->isExist ==true (회원정보가 있음)일 경우 반복)
		do {
			System.out.println();
			System.out.println("추가할 회원 정보를 입력하세요.");
			System.out.print("회원 ID >> ");
			
			memId = scan.next();
			
			//존재하는지 체크
			isExist = checkMember(memId);
			
			//isExist == true과 같은 코드
			if(isExist) { 
				System.out.println("회원ID가 " + memId + "인 회원은 "
						+ "이미 존재합니다.");
				System.out.println("다시 입력해 주세요.");
			}
		}while(isExist);
		
		System.out.print("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.print("회원 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine(); // 입력버퍼 비우기
		
		System.out.print("회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		/*
		 * try-catch-finally가 있던 부분
		 * JDBC작업은 DAO가 함
		 */
		
		//입력받은 정보를 MemberVO에 setter메서드로 저장
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		
		int cnt = memService.registMember(mv);
		
		if(cnt > 0) {
			System.out.println(mv.getMemId() + "회원정보 등록 성공");
		}else {
			System.out.println(mv.getMemId() + "회원정보 등록 실패...");
		}
	}

	/**
	 * 회원이 존재하는지 확인하기 위한 메서드
	 * @param memId 회원ID
	 * @return 존재하면 true, 존재하지 않으면 false 반환함.
	 */
	private boolean checkMember(String memId) {
		
		boolean isExist = memService.checkMember(memId);
		
		return isExist;
	}

	/**
	 * 프로그램 실행
	 * @param args
	 */
	public static void main(String[] args) {
		MemberInfoMain memObj = new MemberInfoMain();
		memObj.start();
	}

}

