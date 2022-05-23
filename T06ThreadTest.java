package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/**
 * 멀티 스레드에서의 사용자 입력 처리하기
 */

public class T06ThreadTest {
	// 입력 여부를 확인하기 위한 변수 선언
	// 모든 스레드에서 공통으로 사용할 변수
	static boolean inputCheck = false; //(true 사용자가 입력을 안했음)
	
	public static void main(String[] args) {
		Thread th1 = new DataInput();
		Thread th2 = new CountDown();
		
		th1.start();
		th2.start();
	}
}

//사용자 입력 처리 스레드 클래스
class DataInput extends Thread {
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
		
		//입력이 완료되면 intputCheck변수를 true로 변경한다. (true 사용자가 입력을 했음)
		T06ThreadTest.inputCheck = true;
		System.out.println("입력한 값은 : " + str + "입니다.");
	}
}

//카운튿운을 처리하는 스레드 클래스
class CountDown extends Thread {
	@Override
	public void run() {
		for(int i=10; i>=1; i--) {  
			//입력이 완료 됐는지 여부를 검사하고 입력이 완료되면
			//run()를 종료시킨다. 즉 현재 스레드를 종료시킨다.
			if(T06ThreadTest.inputCheck == true) {
				return; //run()메서드가 종료되면 스레드도 종료된다.
			}
			
			System.out.println(i);
			try {
				Thread.sleep(1000);
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	
}