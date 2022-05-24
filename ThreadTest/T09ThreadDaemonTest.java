package kr.or.ddit.basic;

public class T09ThreadDaemonTest {
	public static void main(String[] args) {
		Thread th = new AutoSaveThread();
		
		// 데몬스레드로 설정하기( start메서드 호출전에 설정한다.)
		th.setDaemon(true); //default가 false라서 true로 바꿔야 실행됨
		
		th.start();
		
		try {
			for(int i=1; i<=20; i++) {
				System.out.println("작업 " + i);
				Thread.sleep(1000); //1초
			}
			
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		
		System.out.println("메인 스레드 종료.");
	}
}

/**
 * 자동저장 기능을 제공하는 스레드
 * (3초에 한번씩 저장하기)
 * 데몬스레드가 아닌 일반스레드로 실행시 종료되지 않고 계속 실행됨. 메인스레드는 먼저 죽음
 * 데몬스레드면 일반스레드 종료시 종료됨.
 * 
 */
class AutoSaveThread extends Thread {
	public void save() {
		System.out.println("작업 내용을 저장합니다...");
	}
	
	@Override
	public void run() { // 3초마다 저장
		while(true) { //1. 무한루프
			try {
				Thread.sleep(3000); //2. 3초 멈춤
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
			save(); // 3. 저장기능 호출
		}
	}
}
