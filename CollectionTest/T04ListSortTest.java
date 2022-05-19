package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T04ListSortTest {
	public static void main(String[] args) {
		List<Member> memList = new ArrayList<Member>();
		
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "변학도", "010-2222-1111"));
		memList.add(new Member(9, "성춘향", "010-3333-1111"));
		memList.add(new Member(3, "이순신", "010-4444-1111"));
		memList.add(new Member(6, "강감찬", "010-5555-1111"));
		memList.add(new Member(2, "일지매", "010-6666-1111"));
		
		System.out.println("정렬 전 : ");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("------------------------------------------------");
		
		Collections.sort(memList); //정렬하기
		
		System.out.println("정렬 후(이름의 오름차순) : ");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("------------------------------------------------");
		
		Collections.sort(memList, new SortNumDesc()); //외부 정렬자로 정렬하기
		
		System.out.println("정렬 후(번호의 내림차순) : ");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("------------------------------------------------");
	}
}

/*
 * 외부정렬자 클래스
 * (Member의 번호(Num)의 내림차순으로 정렬하기)
 */
class SortNumDesc implements Comparator<Member> {
	//정렬할 대상이 Member

	@Override
	public int compare(Member mem1, Member mem2) {
		//1.
//		if(mem1.getNum() > mem2.getNum()) {
//			return -1;
//		} else if(mem1.getNum() == mem2.getNum()) {
//			return 0;
//		} else {
//			return 1;
//		}
		//2.
//		return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1;
		//num이 int니까 Integer객체에 구현되어있는 compareTo 메소드를 이용해서 내림차순으로 정렬가능
		
		//3.
		return Integer.compare(mem1.getNum(), mem2.getNum()) * -1;
		//Integer객체 내부에 있는 compare메소드를 직접 호출해서 mem1.num과 mem2.num을 비교
	}
	
}


/*
 * 회원의 정보를 저장할 클래스
 * (회원이름을 기준으로 오름차순정렬이 될 수 있는 클래스 생성하기)
 */
class Member implements Comparable<Member> {
	//<Member> Member끼리 비교

	private int num; //번호
	private String name; //이름
	private String tel; //전화번호
	
	
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	@Override
	public int compareTo(Member mem) {
		//name이 String타입이라 String클래스에 있는 compareTo()메소드를 이용해서 비교
		//기본이 오름차순이라서 그대로 쓰면 됨.
		return this.getName().compareTo(mem.getName());
	}


	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	} 
	
}
