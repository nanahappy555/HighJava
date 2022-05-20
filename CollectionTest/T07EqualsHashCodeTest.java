package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class T07EqualsHashCodeTest {
	/*
	 * 해시함수(hash function)는 임의의 길이의 데이터를 *고정된 길이*의 데이터로 매핑하는 함수이다. 해시함수에 의해 얻어지는 값은
	 * 해시값, 해시코드, 해시체크섬 또는 간단하게 해시라고 한다.
	 * 
	 * HashSet, HashMap, Hashtable과 같은 컬렉션객체들을 사용할 경우 객체가 서로 같은지를 비교하기 위해
	 * equals()메서드와 hashcode()메서드를 호출한다. 그래서 객체가 서로 같은지 여부를 결정하려면 두 메서드를 재정의 해야한다.
	 * 객체가 같은지 여부는 데이터를 추가할 때 검사한다.
	 * 
	 * -equals()메서드는 두 객체의 내용(값)이 같은지 비교하기 위한 메서드이고, hashCode()메서드는 객체에 대한 해시코드값을
	 * 반환하는 메서드다.
	 * 
	 * -equals()메서드와 hashCode()메서드에 관련된 규칙 1. 두 객체가 같으면 반드시 같은 hashcode를 가져야 한다. 2.
	 * 두 객체가 같으면 equals()를 호출했을 때 true를 반환해야 한다. 즉, 객체 a, b가 같다면 a.equals(b)와
	 * b.equals(a) 둘 다 true여야한다. 3. 두 객체의 hashcode가 같다고 해서 두 객체가 반드시 같은 객체는 아니다.
	 * 하지만, 두 객체가 같으면 반드시 hashCode가 같아야 한다. 4. equals()메서드를 override하면 반드시
	 * hashCode()도 override해야 한다. 5. hashCode()는 기본적으로 Heap메모리에 있는 각 객체에 대한 메모리 주소
	 * 매핑 정보를 기반으로 한 정수값을 반환한다. 그러므로, 클래스에서 hashCode()메서드를 override하지 않으면 절대로 두 객체가
	 * 같은 것으로 간주될 수 없다.
	 * 
	 */

//	public static void main(String[] args) {
//		Object obj = new Object();
//		//1.객체가 같으면 같은 해쉬코드
//		System.out.println(obj.hashCode() + ":" + obj.hashCode());
//		//2.
//		String str1 = "안녕하세요.";
//		String str2 = "안녕하세요.";
//		System.out.println(str1.equals(str2) + ":" + str2.equals(str1));
//		
//		//3.
//		//두 해쉬코드가 같다고 해서 두 객체가 반드시가 같은 객체는 아니다
//		System.out.println("Aa".hashCode() + " : " + "Aa".hashCode()); //같음
//		System.out.println("Aa".hashCode() + " : " + "BB".hashCode()); //다른 문자(객체)인데 해쉬코드는 같음.(충돌발생)
//		
//		//하지만 두 객체가 같으면 해쉬코드가 반드시 같다. StringPool에 저장
//		System.out.println(str1.hashCode() + " : " + str2.hashCode());
//		
//		//4. rule~~~~
//		//두 객체를 비교하려면 둘 다 똑같이 hashcode와 equals를 override를 해야한다.
//		//둘 중 하나만 오버라이드해야 예상치 못한 문제가 발생하지 않는다.
//		
//		//
//	}

	public static void main(String[] args) {
		Person p1 = new Person(1, "홍길동");
		Person p2 = new Person(1, "홍길동");
		Person p3 = new Person(1, "이순신");

		// 오버라이드를 안하면 홍길동은 홍길동인데 다른 Object로 인식하기 때문에 equals도 false로 나온다
		System.out.println("p1.equals(p2) : " + p1.equals(p2));
		System.out.println("p1 == p2 : " + (p1 == p2));

		Set<Person> set = new HashSet<Person>();
		// HashSet에 홍길동을 넣어보고 빼보고
		set.add(p1);
		set.add(p2);

		System.out.println("p1,p2등록 후 데이터 : ");
		for (Person p : set) {
			System.out.println(p.getId() + " : " + p.getName());
		} // Iterator를 이용하는게 정석이지만 foreach문도 가능함

		System.out.println("add(p3) 성공여부 : " + set.add(p3));

		System.out.println("add(p3) 후 데이터 : ");
		for (Person p : set) {
			System.out.println(p.getId() + " : " + p.getName());
		}

		System.out.println("remove(p2) 성공여부 : " + set.remove(p2));

		System.out.println("remove(p2) 후 데이터 : ");
		for (Person p : set) {
			System.out.println(p.getId() + " : " + p.getName());

		}
	}
}

class Person {
	private int id;
	private String name;

	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	@Override
//	public int hashCode() {
//		return Objects.hash(id, name);
//	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Person)) {
			return false;
		}
		Person other = (Person) obj;
		return id == other.id && Objects.equals(name, other.name);
	}
	
//우리가 직접 해본거
//
//	@Override
//	public boolean equals(Object obj) {
//
//		Person p = (Person) obj; // Object 타입을 Person타입으로 캐스팅
//
//		return this.id == p.getId() && this.getName().equals(p.getName()); // int인 아이디가 같고 String인 이름이 같으면 같음
//	}
//	
//	@Override
//	public int hashCode() {
//		return (name + id).hashCode(); //이미 만들어져있는 String클래스의 hashCode재활용
//	}
}
