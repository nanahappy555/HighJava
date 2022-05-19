package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T03ListSortTest {
/*
 * 정렬과 관련된 Interface는 Comparable과 Comparator 두가지가 있다.
 * - 보통 객체 자체에 정렬기능을 넣기 위해서는 Comparable을 구현하고
 * 	정렬기준을 별도로 구현하고 싶을 때는 Comparator를 구현하여 사용하면 된다.
 * 
 *- Comparable에서는 comparaeTo()를 구현해야 하고,
 *	Comparator에서는 compare()를 구현해야 한다.
 */
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		
		System.out.println("정렬 전 : " + list);
		
/*
 * 정렬은 Collections.sort()를 이용하여 정렬한다.
 * 정렬은 기본적으로 '오름차순'정렬을 수행한다.
 * 
 * 정렬방식을 변경하려면 정렬방식을 결정하는 객체를 만들어서
 * Collections.sort()메서드의 인수로 넘겨주면 된다.
 * 기존에 만들어둔걸 수정하는게 아니라 새로운 정렬기능을 만들 때 Comparator
 */
		Collections.sort(list); //오름차순으로 정렬. 사전순정렬
		//Collection의 static메서드를 구현한 Collections클래스
		System.out.println("정렬 후 : " + list);
		
		
		Collections.shuffle(list); //섞기
		
		//외부정렬자를 이용한 리스트 정렬(내림차순
		Collections.sort(list, new Desc()); // (정렬할 객체, 정렬기준이 있는 객체생성)
		System.out.println("외부정렬자 이용한 정렬 후 : " + list);
	}
}

/*
 * 정렬방식을 결정하는 Class는 Comparator라는 인터페이스를 구현해야 한다.
 * 이 Comparator인터페이스의 compare() 메서드를 재정의하여 구현하면 된다.
 */
class Desc implements Comparator<String> { //외부정렬자 클래스 생성

	@Override
	public int compare(String str1, String str2) {
		/*
		 * compare()의 반환값을 결정하는 방법
		 * => 오름차순이 기본임.
		 * 
		 * - 오름차순 정렬일 경우...
		 * => 앞의 값이 크면 양수, 같으면 0, 앞의 값이 작으면 음수 반환하도록 구현한다.
		 */
		return str1.compareTo(str2) * -1; //음수를 곱하면 결과가 내림차순처럼 된다.
	}
	
}
