package kr.or.ddit.basic;

/**
 * 스레드의 수행시간 계산하기
 */
public class T03ThreadTest {
	
	public static void main(String[] args) {
		Thread th = new Thread(new MyRunner());
		
		// UTC(Universal Time Coodinated 협정 세계 표준시)를 사용하여
		// 1970년 1월 1일 0시 0분 0초를 기준으로 경과한 시간을 밀리세컨드
		// 단위로 나타낸다.
		long startTime = System.currentTimeMillis();
		System.out.println("startTime :  " + startTime);
		th.start(); // 스레드 시작
		
		try {
			th.join(); // 현재 실행중인 스레드(메인스레드)에서 작업중인 스레드(th스레드)
			           // (지금은 th스레드)가 종료될 때까지 기다린다.
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("endTime :  " + endTime);
		
		System.out.println("경과 시간 : " + (endTime - startTime));
	}
}


// 1~1000000000 까지의 합계를 구하는 스레드 클래스
class MyRunner implements Runnable {

	@Override
	public void run() {
		long sum = 0;
		for(int i=1; i<=1000000000; i++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
	
}









