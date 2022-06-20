package homework;

import java.util.List;
import java.util.Scanner;

public class BoardMain {
	
	private Scanner scanner = new Scanner(System.in);
	private BoardService boardService;
	
	public BoardMain() {
		boardService = BoardServiceIMP.getInstance();
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
			choice = scanner.nextInt();
			System.out.println("--------------------------------------------------------------------------------");
			switch (choice) {
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
			
		} while(choice != 6);
	}

	private void readAll() {
		List<BoardVO> boardList = boardService.getAllBoardList();
		
		if(boardList.size() == 0) {
			System.out.println(" 등록된 게시글이 없습니다.");
		} else {
			for(BoardVO bv : boardList) {
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 작성자 : " + bv.getboardWriter() + "            작성일자 : " + bv.getboardDate());
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 제목 : " + bv.getboardTitle());
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 게시글번호 : " + bv.getboardNo() + "번, 내용 ↓");
				System.out.println();
				System.out.println(" " + bv.getboardContent());
				System.out.println("--------------------------------------------------------------------------------");
			}
		}
	}

	private void delete() {
		List<BoardVO> boardList = boardService.getAllBoardList();
		
		System.out.print(" 삭제할 게시글의 작성자명을 입력하세요 : ");
		String boardWriter = scanner.next();
		
		BoardVO bv = new BoardVO();
		bv.setboardWriter(boardWriter);
		
		boardList = boardService.searchBoard(bv);
		
		if(boardList.size() == 0) {
			System.out.println(" 등록된 게시글이 없습니다.");
			return;
		} else {
			for(BoardVO bv2 : boardList) {
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 작성자 : " + bv2.getboardWriter() + "            작성일자 : " + bv2.getboardDate());
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 제목 : " + bv2.getboardTitle());
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 게시글번호 : " + bv2.getboardNo() + "번, 내용 ↓");
				System.out.println();
				System.out.println(" " + bv2.getboardContent());
				System.out.println("--------------------------------------------------------------------------------");
			}
		}
		System.out.print(" 삭제할 게시글의 번호를 입력하세요 : ");
		
		int boardNo = scanner.nextInt();
		int cnt = boardService.deleteBoard(boardNo);
		
		if(cnt > 0) {
			System.out.println(" " + boardNo + "번 게시글이 성공적으로 삭제되었습니다.");
		} else {
			System.out.println(" " + boardNo + "번 게시글 삭제에 실패하였습니다.");
		}
	}
	
	private void searchRead() {
		scanner.nextLine();
		System.out.println(" 검색할 정보를 입력하세요.");
		System.out.println();
		System.out.print(" 작성자 : ");
		String boardWriter = scanner.nextLine().trim();
		
		System.out.println(" (제목에 포함되는 정보를 입력해주세요.)");
		System.out.print(" 제목 : ");
		String boardTitle = scanner.nextLine().trim();
		
		System.out.println(" (내용에 포함되는 정보를 입력해주세요.)");
		System.out.print(" 내용 : ");
		String boardContent = scanner.nextLine().trim();
		
		System.out.println(" 날짜 기간별로도 데이터 조회가 가능합니다. 날짜 입력 예시 - 20220101");
		System.out.print(" 시작 날짜를 입력하세요 : ");
		String boardDate = scanner.nextLine().trim();
		System.out.print(" 종료 날짜를 입력하세요 : ");
		String bEndDate = scanner.nextLine().trim();
		
		BoardVO bv = new BoardVO();
		bv.setboardWriter(boardWriter);
		bv.setboardTitle(boardTitle);
		bv.setboardContent(boardContent);
		bv.setboardDate(boardDate);
		bv.setbEndDate(bEndDate);
	
		List<BoardVO> boardList = boardService.searchBoard(bv);
		
		if(boardList.size() == 0) {
			System.out.println(" 등록된 게시글이 없습니다.");
			return;
		} else {
			for(BoardVO bv2 : boardList) {
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 작성자 : " + bv2.getboardWriter() + "          작성일자 : " + bv2.getboardDate());
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 제목 : " + bv2.getboardTitle());
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 게시글번호 : " + bv2.getboardNo() + "번, 내용 ↓");   	
				System.out.println();
				System.out.println(" " + bv2.getboardContent());
				System.out.println("--------------------------------------------------------------------------------");
			}
		}
	}

	private void modify() {
		List<BoardVO> boardList = boardService.getAllBoardList();
		String boardWriter = "";
		boolean isExist = false;
		
		System.out.println();
		System.out.println(" 수정할 게시글의 작성자 이름을 입력하세요.");
		System.out.print(" 입력 : ");
		boardWriter = scanner.next();
		
		BoardVO bv = new BoardVO();
		bv.setboardWriter(boardWriter);
		
		boardList = boardService.searchBoard(bv);
		
		if(boardList.size() == 0) {
			System.out.println(" 등록된 게시글이 없습니다.");
			return;
		} else {
			for(BoardVO bv2 : boardList) {
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 작성자 : " + bv2.getboardWriter() + "            작성일자 : " + bv2.getboardDate());
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 제목 : " + bv2.getboardTitle());
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 게시글번호 : " + bv2.getboardNo() + "번, 내용 ↓");
				System.out.println();
				System.out.println(" " + bv2.getboardContent());
				System.out.println("--------------------------------------------------------------------------------");
			}
		}
		System.out.println(" 수정할 게시글의 번호를 입력하세요.");
		System.out.print(" 입력 : ");
		int boardNo = scanner.nextInt();
		
		System.out.print(" 수정할 제목 : ");
		String boardTitle = scanner.next();
		
		scanner.nextLine();
		
		System.out.println(" 수정할 내용 ↓ ");
		String boardContent = scanner.nextLine();
		
		bv = new BoardVO();
		
		bv.setboardNo(boardNo);
		bv.setboardWriter(boardWriter);
		bv.setboardTitle(boardTitle);
		bv.setboardContent(boardContent);
		
		int cnt = boardService.updateBoard(bv);
		
		if(cnt > 0) {
			System.out.println(" " + boardWriter + "님의 " + boardNo + "번 게시글을 수정하였습니다.");
		} else {
			System.out.println(" " + boardWriter + "님의 " + boardNo + "번 게시글 수정에 실패하였습니다.");
		}
	}

	private void write() {
		boolean isExist = false;
		String boardWriter = null;
		
		do {
			System.out.println();
			System.out.println(" 게시판 글은 최대 3개까지 작성 가능합니다.");
			System.out.print(" 작성자 이름을 입력하세요 : ");
			
			boardWriter = scanner.next();
			
			isExist = checkBoard(boardWriter);
			
			if(isExist) {
				System.out.println(" " + boardWriter + "님은 작성 가능한 게시글 수를 모두 사용하였습니다.");
				System.out.println(" 다른 작성자 이름을 입력해주세요.");
			}
		} while(isExist);
		
		System.out.print(" 제목을 입력하세요 : ");
		String boardTitle = scanner.next();
		
		scanner.nextLine();
		
		System.out.println(" 게시글 내용을 입력하세요 ↓ ");
		String boardContent = scanner.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setboardWriter(boardWriter);
		bv.setboardTitle(boardTitle);
		bv.setboardContent(boardContent);
		
		int cnt = boardService.registBoard(bv);
		
		if(cnt > 0) {
			System.out.println(" " + bv.getboardWriter() + "님 게시글이 등록되었습니다.");
		} else {
			System.out.println(" " + bv.getboardWriter() + "님 게시글이 등록에 실패하였습니다.");
		}
	}	
	
	private boolean checkBoard(String boardWriter) {
		boolean isExist = boardService.checkBoard(boardWriter);
		return isExist;
	}

	public static void main(String[] args) {
		BoardMain board = new BoardMain();
		board.start();
	}

}
