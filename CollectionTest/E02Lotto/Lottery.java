package kr.or.ddit.basic.E02Lotto;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
//로또 기능은 한 클래스에 모아두는게 좋을듯!
public class Lottery {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean run = true;

		// 프로그램 break될때까지 true
		while (run) {
			System.out.println("==========================");
			System.out.println("Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println("1. Lotto 구입");
			System.out.println("2. 프로그램 종료");
			System.out.println("==========================");

			System.out.print("메뉴선택 : ");
			int menuSelect = scanner.nextInt();
			
			//메뉴선택
			switch (menuSelect) {
			case 1:
				System.out.println("Lotto 구입 시작");
				System.out.println();
				System.out.println("(1000원에 로또번호 하나입니다.)");
				System.out.print("금액입력 : ");
				int moneyInsert = scanner.nextInt();

				int times = moneyInsert / 1000;
				int change = moneyInsert % 1000;

				System.out.println("행운의 로또번호는 아래와 같습니다.");
				for (int i = 1; i <= times; i++) {
					Set<Integer> num = new HashSet<Integer>();
					Random random = new Random();
					while (num.size() < 6) {
						int lottoNum = random.nextInt(45) + 1;
						num.add(lottoNum);
					}

					System.out.println("로또번호" + i + " : " + num);
				}

				System.out.println();
				System.out.println("받은 금액은 " + moneyInsert + "이고, 거스름돈은 " + change + "원입니다.");
				System.out.println();
				break;

			case 2:
				System.out.println();
				System.out.println("감사합니다");
				run = false;
				break;
			}
			

		}

	}
}

//메뉴 선택 if문 버전
//			if(menuSelect == 1) {
//				System.out.println("Lotto 구입 시작");
//				System.out.println();
//				System.out.println("(1000원에 로또번호 하나입니다.)");
//				System.out.print("금액입력 : ");
//				int moneyInsert = scanner.nextInt();
//				
//				int times = moneyInsert / 1000;
//				
//				System.out.println("행운의 로또번호는 아래와 같습니다.");
//				for(int i = 1; i<=times; i++) {
//				Set<Integer> num = new HashSet<Integer>();
//				Random random = new Random();
//				while(num.size() < 6) {
//					int lottoNum = random.nextInt(45)+1;
//					num.add(lottoNum);
//				}
//				
//					System.out.println("로또번호" + i + " : " + num);
//				}
//				
//				System.out.println();
//				System.out.println("받은 금액은 " + moneyInsert + "이고, 거스름돈은 " + moneyInsert%1000 + "원입니다.");
//				System.out.println();
//				
//			} else {
//				System.out.println("감사합니다");
//				break;
//			}