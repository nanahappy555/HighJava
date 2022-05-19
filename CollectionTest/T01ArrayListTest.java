package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T01ArrayListTest {
	public static void main(String[] args) {
		List list1 = new ArrayList();
//		List list1 = new Vector();
//		Vector도 List를 implements하는 클래스라 사용해서 오류x
//		타입을 인터페이스로 선언.
//		인터페이스 기반해서 코딩하면 이처럼 다음에 다른 좋은 클래스가 나오면 쉽게 바꿔끼울수있다
		
		//add()메서드를 사용해서 데이터를 추가한다.
		list1.add("aaa");
		list1.add("bbb");
		list1.add(111); //OPP라서 객체화 하는게 처리하기 편함-> int가 아닌 Integer클래스
		//컴파일 시키는 시점에서 컴파일러가 객체화시켜줌 list1.add(new Integer(111));
		//객체화->계층화 가능
		list1.add('k'); //Character클래스
		list1.add(true);
		list1.add(12.34); //Double
		
		//size() => 데이터 갯수
		System.out.println("size => " + list1.size() );
		
		//get()으로 데이터 꺼내오기 *매개변수필요(인덱스값)* = 순서가있음
		System.out.println("1번째 자료 : " + list1.get(0));
		
		//데이터 끼워넣기도 같다.
		list1.add(0,"zzz"); //(데이터 넣고싶은 인덱스 위치,넣고싶은데이터)
		//데이터를 밀어내는 과정을 배열 길이만큼 함. 비효율적...
		System.out.println("list1 => " + list1);
		
		//데이터 변경하기(set메서드) *반드시* 매개변수2개
		String temp = (String) list1.set(0, "yyy");
		System.out.println("temp => " + temp);
		System.out.println("list1 => " + list1);
		
		//삭제하기도 같다 매개변수:인덱스값
		list1.remove(0); //(int index)
		System.out.println("삭제 후 : " + list1);
		
		list1.remove("bbb"); //(Object o)
		System.out.println("bbb 삭제 후 : " + list1);
		
//		list1.remove(111); 111을 인덱스값으로 인식해서 IndexOutOfBoundsException에러
		list1.remove(new Integer(111)); //근데 이렇게 객체 만드는건 안좋긴 함ㅠㅠ
		System.out.println("111 삭제 후 : " + list1);
		
		System.out.println("====================================");
		
		//제너릭을 지정하여 선언할 수 있다.
		//<String>으로 타입 지정해서 String만 넣을 수 있다.
		List<String> list2 = new ArrayList<String>();
		list2.add("AAA");
		list2.add("BBB");
		list2.add("CCC");
		list2.add("DDD");
		list2.add("EEE");
		
		for(int i=0; i<list2.size(); i++) {
			System.out.println(i + " : " + list2.get(i));
		}
		System.out.println("--------------------------------");
		
		//contain포함하다..
		//contains(비교객체); => 리스트에 '비교객체'가 있으면 true
		//					   그렇지 않으면 false가 반환됨.
		System.out.println(list2.contains("DDD"));
		System.out.println(list2.contains("ZZZ"));
		
		//indexOf(비교객체); =>리스트에서 '비교객체'를 찾아 '비교객체'가 존재하는 index값을 반환한다.
		//					리스트에 존재하지 않으면 -1을 반환한다.
		System.out.println("DDD의 index값 : " + list2.indexOf("DDD"));
		System.out.println("ZZZ의 index값 : " + list2.indexOf("ZZZ"));
		System.out.println("---------------------------------------");
		
		//toArray() => 리스트 안의 데이터들을 배열로 변환하여 반환한다.
//					=> 기본적으로 Object형 배열로 반환한다.
		Object[] strArr = list2.toArray();
		String[] strArr1 = list2.toArray(new String[0]); //[]안의 인덱스값은 큰 의미가 없다..
		System.out.println("배열의 개수 : " + strArr.length);
		
		System.out.println(list2);
		for(int i=0; i<list2.size(); i++) {
			list2.remove(i);
			System.out.println(list2);
		}
		System.out.println("list2의 크기 : " + list2.size());
//		5개-> 2개가 나옴
//		1번째를 지우면 2345가 앞으로 땡겨옴
//		2번째를 지우면 245가 앞으로 땡겨옴
//		그래서 뒤에서부터 지워야 안땡겨옴
//		for(int i=list2.size()-1; i>=0; i--) { 
//			list2.remove(i);
//		}
//		이렇게 지워야됨
		
	}
}
