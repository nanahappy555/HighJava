package mybatisboard;

public class BoardVO {
	private int bNo;
	private String bTitle;
	private String bWriter;
	private String bContent;
	private String bDate;
	private String bEndDate; //검색
	public int getbNo() {
		return bNo;
	}
	public void setbNo(int bNo) {
		this.bNo = bNo;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbWriter() {
		return bWriter;
	}
	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public String getbDate() {
		return bDate;
	}
	public void setbDate(String bDate) {
		this.bDate = bDate;
	}
	public String getbEndDate() {
		return bEndDate;
	}
	public void setbEndDate(String bEndDate) {
		this.bEndDate = bEndDate;
	}
	
	
	
}
