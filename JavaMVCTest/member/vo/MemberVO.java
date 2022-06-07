package kr.or.ddit.member.vo;
/**
 * DB 테이블에 있는 컬럼을 기준으로 데이터를 객체화한 클래스
 * @author PC-17
 * 
 * <p>
 * DB테이블의 컬럼과 클래스의 멤버변수를 매핑하는 역할을 수행한다.
 * </p>
 */
public class MemberVO {
	//value object = 데이터를 담기 위한 클래스
	
	private String memId;
	private String memName;
	private String memTel;
	private String memAddr;
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemTel() {
		return memTel;
	}
	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}
	public String getMemAddr() {
		return memAddr;
	}
	public void setMemAddr(String memAddr) {
		this.memAddr = memAddr;
	}
	
	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", memName=" + memName + ", memTel=" + memTel + ", memAddr=" + memAddr
				+ "]";
	}
	
	
}
