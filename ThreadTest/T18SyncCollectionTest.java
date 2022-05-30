package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 리스 동기화 처리 예제
 * @author sem
 *
 */
public class T18SyncCollectionTest {
/*
    Vector, Hashtable등 예전부터 존재하던 Collection 클래스들은 내부에
    동기화 처리가 되어 있다. 그런데, 최근에 새로 구성된 Collection 클래스
    들은 동기화 처리가 되어 있지 않다. 그래서 동기화가 필요한 경우에는
    동기화 처리를 한 후에 사용해야 한다. 
*/
	// 동기화 처리를 하지 않은 경우...
	//Arraryindexbound 에러나면서 제대로 add되지않음
	private static List<Integer> list1 = new ArrayList<Integer>();
	
	// 동기화 처리를 한 경우...)
	private static List<Integer> list2 = Collections.synchronizedList(new ArrayList<Integer>());
	
	public static void main(String[] args) {
		// 익명 클래스를 이용한 스레드 객체 생성하기
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				for(int i=1; i<=10000; i++) {
					list1.add(i); // 동기화 처리하지 않은 리스트 이용
					list2.add(i); // 동기화 처리한 리스트 이용
				}
			}
		};
		
		Thread[] ths = new Thread[] {
			new Thread(r), new Thread(r),
			new Thread(r), new Thread(r), new Thread(r)
		};
		
		for(Thread th : ths) {
			th.start();
		}
		
		for(Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("list2의 개수 : " + list2.size());
	}
}
