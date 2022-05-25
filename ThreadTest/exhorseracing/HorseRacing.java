package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HorseRacing {
//	static String strRank="";

	public static void main(String[] args) {
		
		System.out.println();
		System.out.println("경기 시작");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}

//		
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

		Position p = new Position(hList);
		
		for (Thread th : hList) {
			th.start();
		}
		p.start();
		
		
		for (Thread th : hList) {
			try {
				th.join();
				p.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(500); //0.2초씩 출력 텀
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Collections.sort(hList);
		System.out.print("\n\n\n");
		System.out.println("------------------------");
		System.out.println("경기 끝");
		System.out.println("------------------------");
		System.out.println("경기 결과");
		System.out.println("축하합니다! 1등은 " + hList.get(0).getHName() +  " 입니다.\n");
		for (Horse h : hList) {
			System.out.println(h);
//			System.out.printf("%d등 : %s \n", h.getRank(), h.getHName());
		}

	}
}
