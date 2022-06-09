package kr.or.ddit.basic.E08Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class JDBCBoard {
	public static void main(String[] args) {
		JDBCBoard b = new JDBCBoard();
		b.start();
	}

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Scanner scan = new Scanner(System.in);

	public void displayMenu() {
		System.out.println("========자유게시판========");
		System.out.println("===자유롭게 글을 남겨주세요===");
		System.out.println("-----------------------");
		System.out.println("  ________________");
		System.out.println("  | 1. 전체 목록 출력 |");
		System.out.println("  | 2. 새 글 작성    |");
		System.out.println("  | 3. 글 수정하기   |");
		System.out.println("  | 4. 글 삭제하기   |");
		System.out.println("  | 5. 글 검색하기   |");
		System.out.println("  | 6. 게시판 종료   |");
		System.out.println("  ________________");
		System.out.print("메뉴 선택 >> ");

	}

	public void start() {
		int menu = 0;
		do {
			displayMenu(); // 메뉴 출력
			
			menu = scan.nextInt();
			
			switch (menu) {
			case 1:
				displayallpost();
				break;
			case 2:
				newPost();
				break;
			case 3:
				modifyPost();
				break;
			case 4:
				deletePost();
				break;
			case 5:
				searchPost();
				break;
			case 6:
				System.out.println("게시판을 종료합니다.");
				System.out.println("======================");
				break;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요.");
			}
		} while (menu != 6);

	}

	private void searchPost() {
		boolean isExist = false;
		
//		System.out.println("검색할 글번호를 입력해주세요");
//		System.out.print("글번호 | ");
//		String boardNo = scan.nextLine().trim();
//		
//		isExist = checkPost(boardNo);
//		
		scan.nextLine(); //버퍼제거
//			
//		
//		System.out.println("검색할 제목을 입력해주세요");
//		System.out.print("제목(부분검색가능)| ");
//		String boardTitle = scan.nextLine().trim();
		System.out.println("검색할 작성자를 입력해주세요");
		System.out.print("작성자(부분검색가능) | ");
		String writer = scan.nextLine();
//		System.out.println("검색할 내용을 입력해주세요");
//		System.out.print("내용(부분검색가능) | ");
//		String boardContent = scan.nextLine().trim();
		
		
		
		try {
			conn = BoardUtil.getConnection();
			
			String sql = " select * from jdbc_board "
//						+ " where board_no = ? "
//						+ " and board_title = ? "
						+ " where board_writer like  '%' || ? || '%' ";
//						+ " and board_content = ? ";
			
			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, boardNo);
//			pstmt.setString(2, boardWriter);
			pstmt.setString(1, writer);
//			pstmt.setString(4, boardContent);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					String boardNo = rs.getString("board_no");
					String boardTitle = rs.getString("board_title");
					String boardWriter = rs.getString("board_writer");
					String boardDate = rs.getString("board_date");
					String boardContent = rs.getString("board_content");
					
					System.out.println("----------------------------------------------");
					System.out.println("글번호 | " + boardNo);
					System.out.println("제목 | " + boardTitle);
					System.out.println("작성자 | " + boardWriter);
					System.out.println("작성일자 | " + boardDate);
					System.out.println("내용 | " + boardContent);
					System.out.println("----------------------------------------------");
				}
				System.out.println("검색을 완료했습니다.");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			BoardUtil.close(conn, stmt, pstmt, rs);
		}
	}

	private void deletePost() {
		String boardNo = "";

		boolean isExist = false;

		do {
			scan.nextLine();
			
			System.out.println("수정할 글번호를 입력해주세요");
			System.out.print("글번호 | ");
			boardNo = scan.nextLine();

			isExist = checkPost(boardNo);


		} while (!isExist);

		try {
			conn = BoardUtil.getConnection();

			String sql = " delete from jdbc_board where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(boardNo + "번 게시글 수정이 완료됐습니다.");
			} else {
				System.out.println(boardNo + "번 게시글 수정을 실패했습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BoardUtil.close(conn, stmt, pstmt, rs);
		}
	}

	private void modifyPost() {
		String boardNo = "";

		boolean isExist = false;

		do {
			
			scan.nextLine();
			
			System.out.println("수정할 글번호를 입력해주세요");
			System.out.print("글번호 | ");
			boardNo = scan.nextLine();

			isExist = checkPost(boardNo);


		} while (!isExist);

		System.out.print("제목 수정 | ");
		String boardTitle = scan.nextLine();
		System.out.print("작성자 수정 | ");
		String boardWriter = scan.nextLine();
		System.out.print("내용 수정 | ");
		String boardContent = scan.nextLine();

		try {
			conn = BoardUtil.getConnection();

			String sql = " update jdbc_board " + 
					" set BOARD_TITLE= ?, " + " BOARD_WRITER= ?, " + " BOARD_CONTENT = ? "
					+ " where Board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardWriter);
			pstmt.setString(3, boardContent);
			pstmt.setString(4, boardNo);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(boardNo + "번 게시글 수정이 완료됐습니다.");
			} else {
				System.out.println(boardNo + "번 게시글 수정을 실패했습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BoardUtil.close(conn, stmt, pstmt, rs);
		}
	}

	/**
	 * 존재하는 글번호인지 확인하는 메서드
	 * 
	 * @param boardNo
	 * @return
	 */
	private boolean checkPost(String boardNo) {

		boolean isExist = false;

		try {
			conn = BoardUtil.getConnection();

			String sql = " select count(*) as count " + " from jdbc_board where board_no = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);

			rs = pstmt.executeQuery();

			int cnt = 0;
			if (rs.next()) {
				cnt = rs.getInt("count");
			}

			if (cnt > 0) {
				isExist = true;
			} else {
				isExist = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BoardUtil.close(conn, stmt, pstmt, rs);
		}

		return isExist;
	}

	private void newPost() {

		System.out.println();
		
		scan.nextLine();
		
		System.out.print("제목 | ");
		String boardTitle = scan.nextLine();
		System.out.print("작성자 | ");
		String boardWriter = scan.nextLine();
		System.out.print("내용 | ");
		String boardContent = scan.nextLine();

		try {
			conn = BoardUtil.getConnection();

			String sql = " insert into jdbc_board "
					+ " ( BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT ) "
					+ " values ( BOARD_SEQ.NEXTVAL, ?, ?, SYSDATE, ? ) ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardWriter);
			pstmt.setString(3, boardContent);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("게시글 등록이 완료됐습니다.");
			} else {
				System.out.println("게시글 등록을 실패했습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BoardUtil.close(conn, stmt, pstmt, rs);
		}
	}

	private void displayallpost() {
		
		try {
			conn = BoardUtil.getConnection();
			
			String sql = "select * from jdbc_board";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println("----------------------------------------------");
				System.out.println("글번호 | " + rs.getString(1));
				System.out.println("제목 | " + rs.getString(2));
				System.out.println("작성자 | " + rs.getString(3));
				System.out.println("작성일자 | " + rs.getString(4));
				System.out.println("내용 | " + rs.getString(5));
				System.out.println("----------------------------------------------");
			}
			System.out.println("마지막 글입니다.");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			BoardUtil.close(conn, stmt, pstmt, rs);
		}
	
	}

}
