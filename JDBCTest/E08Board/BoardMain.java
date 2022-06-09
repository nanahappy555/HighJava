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
				System.out.println(" 작성자 : " + bv.getbWriter() + "                    작성일자 : " + bv.getbDate());
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 제목 : " + bv.getbTitle());
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 내용");
				System.out.println();
				System.out.println(" " + bv.getbContent());
				System.out.println("--------------------------------------------------------------------------------");
			}
		}
	}

	private void delete() {
		System.out.println(" 등록하신 게시글이 여러개일 경우, 먼저 등록한 순으로 삭제됩니다.");
		System.out.print(" 삭제할 게시글의 작성자 이름을 입력하세요 : ");
		
		String bWriter = scanner.next();
		int cnt = boardService.deleteBoard(bWriter);
		
		if(cnt > 0) {
			System.out.println(" " + bWriter + "님의 게시글이 성공적으로 삭제되었습니다.");
		} else {
			System.out.println(" " + bWriter + "님의 게시글 삭제에 실패하였습니다.");
		}
	}
	
	private void searchRead() {
		scanner.nextLine();
		System.out.println(" 검색할 정보를 입력하세요.");
		System.out.println();
		System.out.print(" 작성자 : ");
		String bWriter = scanner.nextLine().trim();
		
		System.out.println(" (제목에 포함되는 정보를 입력해주세요.)");
		System.out.print(" 제목 : ");
		String bTitle = scanner.nextLine().trim();
		
		System.out.println(" (내용에 포함되는 정보를 입력해주세요.)");
		System.out.print(" 내용 : ");
		String bContent = scanner.nextLine().trim();
		
		System.out.println(" 날짜 기간별로도 데이터 조회가 가능합니다. 날짜 입력 예시 - 20220101");
		System.out.print(" 시작 날짜를 입력하세요 : ");
		String bDate = scanner.nextLine().trim();
		System.out.print(" 종료 날짜를 입력하세요 : ");
		String eDate = scanner.nextLine().trim();
		
		BoardVO bv = new BoardVO();
		bv.setbWriter(bWriter);
		bv.setbTitle(bTitle);
		bv.setbContent(bContent);
		bv.setbDate(bDate);
		bv.setbEndDate(eDate);
	
		List<BoardVO> boardList = boardService.searchBoard(bv);
		
		if(boardList.size() == 0) {
			System.out.println(" 등록된 게시글이 없습니다.");
		} else {
			for(BoardVO bv2 : boardList) {
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 작성자 : " + bv2.getbWriter() + "                  작성일자 : " + bv2.getbDate());
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 제목 : " + bv2.getbTitle());
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(" 내용");
				System.out.println();
				System.out.println(" " + bv2.getbContent());
				System.out.println("--------------------------------------------------------------------------------");
			}
		}
	}

	private void modify() {
		String bWriter = "";
		boolean isExist = false;
		
		System.out.println();
		System.out.println(" 수정할 게시글의 작성자 이름을 입력하세요.");
		System.out.print(" 입력 : ");
			
		bWriter = scanner.next();
			
		isExist = checkBoard(bWriter);
			
		if(!isExist) {
			System.out.println(" 작성자 이름이 " + bWriter + "인 게시글이 존재하지 않습니다.");
			System.out.println(" 다시 입력해주세요.");
		}
		
		System.out.print(" 수정할 제목 : ");
		String bTitle = scanner.next();
		
		scanner.nextLine();
		
		System.out.println(" 수정할 내용 ↓ ");
		String bContent = scanner.nextLine();
		
		BoardVO bv = new BoardVO();
		
		bv.setbWriter(bWriter);
		bv.setbTitle(bTitle);
		bv.setbContent(bContent);
		
		int cnt = boardService.updateBoard(bv);
		
		if(cnt > 0) {
			System.out.println(" " + bWriter + "님의 게시글을 수정하였습니다.");
		} else {
			System.out.println(" " + bWriter + "님의 게시글 수정에 실패하였습니다.");
		}
	}

	private void write() {
		boolean isExist = false;
		
		System.out.println();
		System.out.print(" 작성자 이름을 입력하세요 : ");
		String bWriter = scanner.next();
		
		System.out.print(" 제목을 입력하세요 : ");
		String bTitle = scanner.next();
		
		scanner.nextLine();
		
		System.out.println(" 게시글 내용을 입력하세요 ↓ ");
		String bContent = scanner.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setbWriter(bWriter);
		bv.setbTitle(bTitle);
		bv.setbContent(bContent);
		
		int cnt = boardService.registBoard(bv);
		
		if(cnt > 0) {
			System.out.println(" " + bv.getbWriter() + "님 게시글이 등록되었습니다.");
		} else {
			System.out.println(" " + bv.getbWriter() + "님 게시글이 등록에 실패하였습니다.");
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
