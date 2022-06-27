package E00Board;

import java.util.List;
import java.util.Scanner;

import E00Board.service.BServiceImpl;
import E00Board.service.BoardService;
import E00Board.vo.BoardVO;

public class BoardMain {
	private Scanner scan = new Scanner(System.in);
	private BoardService bService;
	
	public BoardMain() {
		bService = BServiceImpl.getInstance();
	}
	
	public void displayMenu() {
		System.out.println("================================================================================");
		System.out.println("                               질문 답변 게시판                                 ");
		System.out.println("================================================================================");
		System.out.println();
		System.out.println("                             < 메뉴를 선택하세요 >");
		System.out.println();
		System.out.println("                              1. 게시판 글 쓰기");
		System.out.println("                              2. 게시판 글 수정");
		System.out.println("                              3. 게시판 글 삭제");
		System.out.println("                              4. 자료 선택 열람");
		System.out.println("                              5. 자료 전체 열람");
		System.out.println("                              6. 프로그램 종료");
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------");
		System.out.print(" 선택 >> ");
	}
	
	public void start() {
		int choice = 0;
		do {
			displayMenu();
			choice = scan.nextInt();
			System.out.println("--------------------------------------------------------------------------------");
			switch(choice) {
			case 1:
				write();
				break;
			case 2:
				modify();
				break;
			case 3:
				delete();
				break;
			case 4:
				searchRead();
				break;
			case 5:
				readAll();
				break;
			case 6:
				System.out.println(" 게시판 프로그램을 종료합니다.");
				System.out.println("================================================================================");
				break;
			default:
				System.out.println(" 잘못된 입력입니다.");
			}
			
		}while(choice != 6);
	}

	private boolean checkBoard(String bWriter) {
		boolean isExist = bService.checkPost(bWriter);
		return isExist;
	}
	
	private void write() {
		boolean isExist =false;
		String bWriter = null;
		
		do {
			System.out.println();
			System.out.println(" 게시판 글은 최대 3개까지 작성가능합니다.");
			System.out.print(" 작성자 이름을 입력하세요 : ");
			
			bWriter = scan.next();
			
			isExist = checkBoard(bWriter);
			
			if(isExist) {
				System.out.println(" " + bWriter + "님은 작성 가능한 게시글 수를 모두 사용하였습니다.");
				System.out.println(" 다른 작성자 이름을 입력해주세요.");
			}
		}while(isExist); //false일 때 종료
		
		System.out.print(" 제목을 입력하세요 : ");
		String bTitle = scan.next();
		
		scan.nextLine(); //버퍼제거
		
		System.out.println(" 게시글 내용을 입력하세요 ↓ ");
		String bcontent = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setbWriter(bWriter);
		bv.setbTitle(bTitle);
		bv.setbContent(bcontent);
		
		int cnt = bService.registPost(bv);
		
		if(cnt > 0 ) {
			System.out.println(" " + bv.getbWriter() + "님의 게시글이 등록되었습니다.");
		}else {
			System.out.println(" " + bv.getbWriter() + "님의 게시글이 등록에 실패하였습니다.");
		}
	}

	private void modify() {
		List<BoardVO> bList = bService.getPostAll();
		String bWriter = "";
		boolean isExist = false;
		
		System.out.println();
		System.out.println(" 수정할 게시글의 작성자 이름을 입력하세요.");
		System.out.print(" 입력 : ");
		bWriter = scan.next();
		
		BoardVO bv = new BoardVO();
		bv.setbWriter(bWriter);
		
		bList = bService.searchPost(bv);
		
		if(bList.size() == 0) {
			System.out.println(" 등록된 게시글이 0 개 입니다.");
			return;
		}else {
			for(BoardVO b : bList) {
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 작성자 : " + b.getbWriter() + "            작성일자 : " + b.getbDate());
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 제목 : " + b.getbTitle());
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 게시글번호 : " + b.getbNo() + "번, 내용 ↓");
				System.out.println();
				System.out.println(" " + b.getbContent());
				System.out.println("--------------------------------------------------------------------------------");
			}
		}
		System.out.println(" 수정할 게시글의 번호를 입력하세요.");
		System.out.print(" 입력 : ");
		int bNo = scan.nextInt();
		
		scan.nextLine();
		
		System.out.print(" 수정할 제목 : ");
		String bTitle = scan.next();
		
		scan.nextLine();
		
		System.out.println(" 수정할 내용 ↓ ");
		String bContent = scan.nextLine();
		
		bv.setbNo(bNo);
		bv.setbTitle(bTitle);
		bv.setbContent(bContent);
		
		int cnt = bService.updatePost(bv);
		
		if(cnt > 0) {
			System.out.println(" " + bWriter + "님의" + bNo + "번 게시글을 수정하였습니다.");
		}else {
			System.out.println(" " + bWriter + "님의" + bNo + "번 게시글 수정에 실패하였습니다.");
		}
		
	}

	private void delete() {
		List<BoardVO> bList = bService.getPostAll();
		String bWriter = "";
		boolean isExist = false;
		
		System.out.println();
		System.out.println(" 삭제할 게시글의 작성자 이름을 입력하세요.");
		System.out.print(" 입력 : ");
		bWriter = scan.next();
		
		BoardVO bv = new BoardVO();
		bv.setbWriter(bWriter);
		
		bList = bService.searchPost(bv);
		
		if(bList.size() == 0) {
			System.out.println(" 등록된 게시글이 0 개 입니다.");
			return;
		}else {
			for(BoardVO b : bList) {
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 작성자 : " + b.getbWriter() + "            작성일자 : " + b.getbDate());
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 제목 : " + b.getbTitle());
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 게시글번호 : " + b.getbNo() + "번, 내용 ↓");
				System.out.println();
				System.out.println(" " + b.getbContent());
				System.out.println("--------------------------------------------------------------------------------");
			}
		}
		System.out.println(" 삭제할 게시글의 번호를 입력하세요 : ");
		int bNo = scan.nextInt();
		
		int cnt = bService.deletePost(bNo);
		
		if(cnt > 0) {
			System.out.println(" " + bWriter + "님의" + bNo + "번 게시글을 삭제하였습니다.");
		}else {
			System.out.println(" " + bWriter + "님의" + bNo + "번 게시글 삭제에 실패하였습니다.");
		}
	}

	private void searchRead() {
		scan.nextLine();//버퍼제거
		
		System.out.println(" 검색할 정보를 입력하세요.");
		System.out.println();
		System.out.print(" 작성자 : ");
		String bWriter = scan.nextLine().trim(); //공백 제거
		
		System.out.println(" 제목을 입력하세요.(키워드 검색 가능)");
		System.out.print(" 제목 : ");
		String bTitle = scan.nextLine().trim(); //공백 제거
		
		System.out.println(" 날짜 기간별로도 데이터 조회가 가능합니다. 날짜 입력 예시 - 20220101");
		System.out.print(" 시작 날짜를 입력하세요 : ");
		String bDate = scan.nextLine().trim();
		System.out.print(" 종료 날짜를 입력하세요 : ");
		String bEndDate = scan.nextLine().trim();
		
		BoardVO bv = new BoardVO();
		bv.setbWriter(bWriter);
		bv.setbTitle(bTitle);
		bv.setbContent(bEndDate);
		bv.setbDate(bDate);
		bv.setbEndDate(bEndDate);
		
		List<BoardVO> bList = bService.searchPost(bv);
		
		if(bList.size() == 0) {
			System.out.println(" 등록된 게시글이 0 개 입니다.");
			return;
		}else {
			for(BoardVO b : bList) {
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 작성자 : " + b.getbWriter() + "            작성일자 : " + b.getbDate());
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 제목 : " + b.getbTitle());
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 게시글번호 : " + b.getbNo() + "번, 내용 ↓");
				System.out.println();
				System.out.println(" " + b.getbContent());
				System.out.println("--------------------------------------------------------------------------------");
			}
		}
		
	}

	private void readAll() {
		List<BoardVO> bList = bService.getPostAll();
		
		if(bList.size() == 0) {
			System.out.println(" 등록된 게시글이 0 개 입니다.");
		}else {
			for(BoardVO b : bList) {
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 작성자 : " + b.getbWriter() + "            작성일자 : " + b.getbDate());
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 제목 : " + b.getbTitle());
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 게시글번호 : " + b.getbNo() + "번, 내용 ↓");
				System.out.println();
				System.out.println(" " + b.getbContent());
				System.out.println("--------------------------------------------------------------------------------");
			}
		}
	}

	public static void main(String[] args) {
		BoardMain board = new BoardMain();
		board.start();
	}
	
	
	
}
