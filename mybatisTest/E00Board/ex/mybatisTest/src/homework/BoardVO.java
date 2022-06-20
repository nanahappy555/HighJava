package homework;

public class BoardVO {
	
	private int boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private String boardDate;
	private String bEndDate;
	

	public int getboardNo() {
		return boardNo;
	}

	public void setboardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getboardDate() {
		return boardDate;
	}

	public void setboardDate(String boardDate) {
		this.boardDate = boardDate;
	}
	
	public String getbEndDate() {
		return bEndDate;
	}
	
	public void setbEndDate(String bEndDate) {
		this.bEndDate = bEndDate;
	}

	
	public String getboardTitle() {
		return boardTitle;
	}
	
	public void setboardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	
	public String getboardWriter() {
		return boardWriter;
	}
	
	public void setboardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	
	public String getboardContent() {
		return boardContent;
	}
	
	public void setboardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	
}
