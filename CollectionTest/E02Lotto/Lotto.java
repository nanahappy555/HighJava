package kr.or.ddit.basic.E02Lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Lotto {
	public static void main(String[] args) {
		
		while(true) {
			System.out.println("====================");
			System.out.println("Lotto 프로그램");
			System.out.println("--------------------");
			System.out.println("1. Lotto 구입");
			System.out.println("2. 프로그램종료");
			System.out.println("===================");
			System.out.print("메뉴선택: ");
			Scanner scanner = new Scanner(System.in);
			int menuChoice = scanner.nextInt(); //메뉴 선택번호를 담는 변수.
			if(menuChoice==1) {//메뉴 선택번호가 1이라면, 로또 구입시작.
				Lotto lotto = new Lotto();//새로운 로또 프로그램이 생성됨.
				System.out.println();
				System.out.println("Lotto 구입 시작");
				System.out.println();
				System.out.println("1000원에 로또번호 하나입니다.");
				System.out.print("금액 입력 : ");
				int money = scanner.nextInt();//낸 돈 담는 변수.
				
				System.out.println("행운의 로또번호는 아래와 같습니다.");
				
				for(int i = 0; i<lotto.calCount(money);i++) {//lotto.calCount(money)는 로또 구입 횟수계산 메서드.
					System.out.print("로또번호"+(i+1)+" : ");
					lotto.printLottoNum(lotto.createLottoNum());
				}
				
				System.out.println("받은 금액은 "+money+"이고 거스름돈은 "+lotto.balance(money)+"원입니다.");
			}												 //lotto.balance(money)는 거스름돈 반환 메서드.
			
			else {
				System.out.println();
				System.out.println("감사합니다.");
				break;
			}
			
		}
		
	}
	
	//-------------------------------------main 메서드 종료-----------------------------------------
	//-------------------------------------로또 클래스----------------------------------------------
	//필드 종류 : 로또 클래스에는 로또 한장가격과, 로또 구입개수 필드
	//메서드 종류 : 로또 구입 가능 장수 계산 메서드, 로또 번호 생성해주는 메서드, 거스름돈 계산해주는 메서드, 로또 번호 출력해주는 메서드
	
	private static final int lottoPrice=1000; //로또 한장 가격
	private int count;//로또 구입 개수
	
	public int calCount(int money) {//로또 구입 장수 계산 및 로또 구입 가능 횟수 필드 변환 메서드.
		this.count = money/1000;
		return count;
	}
	
	
	public List<Integer> createLottoNum() { //6개의 로또 번호를 랜덤하게 만들뒤 정렬한 리스트를 반환하는 메서드. 
		Set<Integer> lotto = new HashSet<Integer>();
		Random random = new Random();
		while(lotto.size()<6) {  //중복없이 1~45 사이의 6개의 정수들 집합에 저장. -- HashSet이 아니라, TreeSet에 받으면 정렬하기가 편함.
			int ranNum = random.nextInt(45)+1;  
			lotto.add(ranNum);
		}
		List<Integer> sortedLotto = new ArrayList<Integer>(lotto);//로또 번호의 출력결과가 오름차순이다.
		Collections.sort(sortedLotto);							  //로또번호를 HashSet에 저장하였기 때문에 정렬불가. 리스트에 담아서 정렬함.
		return sortedLotto;										  //만약 HashSet이 아니라 TreeSet에 받았다면 리스트로 변환 불필요.
	}
	
	
	public int balance(int money) {//거스름돈을 계산해주는 메소드
		return money-lottoPrice*count;
	}
	
	
	public void printLottoNum(List<Integer> sortedLotto) {//6개의 로또번호를 출력하는 메소드.
		for(int i=0; i<sortedLotto.size();i++) {
			if(i!=sortedLotto.size()-1) {
				System.out.print(sortedLotto.get(i)+",");
			}
			else {
				System.out.println(sortedLotto.get(i));
			}
		}
	}
}
