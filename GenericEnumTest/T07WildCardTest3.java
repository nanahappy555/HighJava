package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

/*
 * 상한 및 하한 제한 와일드 카드 예제
 * 상한.... 최대 Member까지만..자식들은 얼마든지 가능
 * 하한.... 최소 Member까지만..부모들은 얼마든지 가능
 */
public class T07WildCardTest3 {
	/*
	 * 회원정보 출력
	 */
	public void printMemberInfo(List<? extends Member> list) { 
		/*
		 * extends키워드를 이용한 상한 제한 (Upper Bounds) 예제
		 * list 안의 객체는 반드시 Member타입의 객체임을 보장 할 수 있다.
		 */
		for(Member mem : list) { //Fruit와 Fruit의 하위클래스라면 Fruit로 선언할 수 있으니까 Fruit타입의 객체일수있다
			System.out.println(mem);
		}
	}
	
	public void printMemberInfo2(List<? super Member> list) {
		/*
		 * super 키워드를 이용한 하한 제한 (Lower Bounds)
		 * Member 타입의 변수를 이용하여 List로부터 객체를 꺼내올 수 없다.
		 * 타입은 Member일수도 있지만 Member가 아닐수도 있다. (다형성...)
		 */
		for(Member obj : list) { //컴파일 에러 발생
			System.out.println(obj);
		}
	}
//	30Line list에 컴파일에러
//	Type mismatch: cannot convert from element type capture#2-of ? super Member to Member
//	Membe
	
	/*
	 * 회원정보등록
	 */
	public void registerMemberInfo(List<? extends Member> list) {
		/*
		 * Member 타입의 객체라고 항상 list에 추가할 수 있음을 보장할 수 없다.
		 * (=Member일수도 있고 아닐수도 있다...)
		 * (Member타입 또는 Member를 상속한 그 어떤 타입을 의미하므로 아직 구체적인 타입이 정해지지 않았다.
		 * 	=> 컴파일 에러 발생)
		 */
		Member m = new Member("홍길동", 33); //Fruit하위(Apple)이라면 Apple한테 Fruit는 담을수없다(Grape가 일수도 있으니까???)
		list.add(m); //등록불가
	}
	
	public void registerMemberInfo2(List<? super Member> list) {
		/*
		 * super 키워드를 이용한 하한 제한 (Lower Bound)예제
		 * list는 Member타입의 객체를 포함한다는 것을 보장할 수 있다.
		 * => Member타입 또는 Member타입의 슈퍼타입을 담은 리스트를 의미하기 때문에...
		 */
		Member m = new Member("홍길동", 33); //Fruit상위라면 Fruit도 담을 수 있다.
		list.add(m);
	}
	
	public static void main(String[] args) {
		T07WildCardTest3 wc = new T07WildCardTest3();
		
		List<Member> memList = new ArrayList<Member>();
		
		wc.registerMemberInfo2(memList);
		wc.registerMemberInfo(memList);
	}
}

class Member {
	private String name;
	private int age;
	
	public Member(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Member [name=" + name + ", age=" + age + "]";
	}
}