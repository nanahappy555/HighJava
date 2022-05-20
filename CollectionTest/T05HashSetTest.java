package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class T05HashSetTest {
	/*
	 * -List와 Set의 차이점
	 * 
	 * 1. List
	 * - 입력한 데이터의 순서(인덱스가)가 있다.
	 * - 중복되는 데이터를 저장할 수 있다.
	 * 
	 * 2. Set
	 * - 입력한 데이터의 순서(인덱스)개념이 존재하지 않는다.
	 * - 중복되는 데이터를 저장할 수 없다.
	 */
	public static void main(String[] args) {
		
		Set hs1 = new HashSet();
		
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2); //(HashSet이 미리 가지고 있는 Integer객체클래스로)자동 박싱
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);
		
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();
		
		//Set은 이미 존재하는 데이터를 추가하면 False를 반환하고,
		//데이터는 추가되지 않는다.
		boolean isAdded = hs1.add("FF");
		System.out.println("중복되지 않을 때 : " + isAdded);
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();
		
		isAdded = hs1.add("CC");
		System.out.println("중복될 때 : " + isAdded);
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();
		
		/*
		 * Set의 데이터를 수정하려면 *수정하는 명령이 따로 없기 때문에*
		 * 해당 자료를 삭제 한 후 새로운 데이터를 추가해 주어야 한다.
		 * 
		 * 삭제하는 메서드
		 * 1) clear() => Set 데이터 전체 삭제
		 * 2) remove(삭제할자료) => 해당자료 삭제 (index가 없어서 객체만 넣어서)
		 */
		//'FF'를 'EE'로 수정하기 1.삭제 2.추가
		hs1.remove("FF"); //1.삭제
		System.out.println("삭제 후 Set 데이터 : " + hs1);
		System.out.println();
		
		hs1.add("EE"); //2.추가
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();
		
//		hs1.clear(); //전체자료삭제
//		System.out.println("clear 후 Set 데이터 : " + hs1);
		
		System.out.println("Set의 자료 개수 : " + hs1.size());
		System.out.println();
		
//		---------------------------------------------------------
		
		//Set은 데이터의 index가 없기 때문에  (내부적으로 순서는 있지만 배열형태가 아니라서 index가 없음)
		//하나씩 불러올 수 없다. 그래서 데이터를 하나씩 불러오기 위해
		//Iterator 객체를 이용한다.
		Iterator it = hs1.iterator(); //Iterator는 hasNext()와 next()를 가지고 있다=호출가능
		//Set은 이미 Iterator를 가지고 있음(오버라이드 필요)
		
		//데이터의 개수만큼 반복하기
		//hasNext() => 포인터 다음 위치에 데이터가 있으면 true, 없으면 false 반환함.
		while(it.hasNext()) {
			//next() => 포인터를 다음 자료 위치로 이동하고,이동한 위치의 자료를 반환한다.
			System.out.println(it.next());
		}
		
		//1~100사이의 중복되지 않는 정수 5개 만들기
		Set<Integer> intRnd = new HashSet<Integer>();
		
		while(intRnd.size() < 5) { //결과는 5번
			int num = (int) (Math.random() * 100 + 1);
			intRnd.add(num); 
			//Set은 중복이 허용되지 않아서 중복이 되면 다시 돌리기 때문에 결과는 5개라도 while돌린 횟수는 5은 아니다
		}
		
		System.out.println("만들어진 난수를 : " + intRnd);
		
		//Collection유형의 객체들은 서로 다른 자료구조로 쉽게 변행해서 사용할 수 있다. (List와 Set모두 Collection의 자식)
		//다른 종류의 객체를 생성할 때 생성자에 변형할 데이터를 넣어주면 된다.
		List<Integer> intRndList = new ArrayList<Integer>(intRnd); //HashSet에서 ArrayList로 바꿔끼움
		
		System.out.println("List의 자료출력...");
		for(Integer num : intRndList) {
			System.out.println(num + " ");
		}
		
	}
}
