package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HorseRacing {

	public static void main(String[] args) {

		System.out.println();
		System.out.println("경기 시작");
		
		//텀을 위한 sleep() 큰 의미 없음
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}

		//ArrayList로 말 추가. 스레드 생성
		List<Horse> hList = new ArrayList<Horse>();

		hList.add(new Horse("01번말"));
		hList.add(new Horse("02번말"));
		hList.add(new Horse("03번말"));
		hList.add(new Horse("04번말"));
		hList.add(new Horse("05번말"));
		hList.add(new Horse("06번말"));
		hList.add(new Horse("07번말"));
		hList.add(new Horse("08번말"));
		hList.add(new Horse("09번말"));
		hList.add(new Horse("10번말"));

		//말 위치 출력 스레드 생성
		Thread p = new Position(hList);

		//foreach문으로 모든 말 스레드 실행 (10개스레드)
		for (Thread th : hList) {
			th.start();
		}
		
		//말 위치 출력 스레드 실행
		p.start();

			try {
				p.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		try {
			Thread.sleep(1000); // 1초 멈췄다가 다음코드 실행
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		Collections.sort(hList);
		System.out.print("\n\n\n");
		System.out.println("------------------------");
		System.out.println("경기 끝");
		System.out.println("------------------------");
		System.out.println("경기 결과");
		System.out.println("축하합니다! 1등은 " + hList.get(0).getHName() + " 입니다.\n");
		//Horse객체(toString)출력
		for (Horse h : hList) {
			System.out.println(h);
		}
	}
}
