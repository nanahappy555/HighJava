package kr.or.ddit.basic;

public class T10ThreadStateTest {
/*
    < 스레드의 상태 >
    
   - NEW : 스레드가 생성되고 아직 start()가 호출되지 않은 상태
   - RUNNABLE : 실행중 또는 실행 가능한 상태
   - BLOCKED : 동기화블럭에 의해서 일시 정지된 상태
               (Lock이 풀릴때까지 기다리는 상태)
   - WAITING, TIMED_WAITING : 스레드의 작업이 종료되지는 않았지만 실행가능
          하지는 않은 일시정지 상태. TIMED_WAITING은 일시정지시간이
          지정된 경우임.
   - TERMINATED : 스레드의 작업이 종료된 상태
*/
	public static void main(String[] args) {
		Thread th = new StatePrintThread(new TargetThread());
		th.start(); //메인스레드는 할 일 끝나면 죽음
		
//		th.join(); //메인스레드가 마지막에 죽음
	}
}


// 스레드의 상태를 출력하는 클래스(이 클래스도 스레드로 작성)
class StatePrintThread extends Thread {
	private Thread targetThread;  // 상태를 모니터링할 스레드 저장용 변수
	
	public StatePrintThread(Thread targetThread) {
		this.targetThread = targetThread;
	}
	
	@Override
	public void run() { //실제 스레드가 하는 작업
		while(true) { //무한루프
			// Thread의 상태 구하기 스레드.getState() enum타입 객체로 반환
			Thread.State state = targetThread.getState();
			System.out.println("타겟스레드의 상태 : " + state);
			
			// NEW 상태인지 검사
			if(state == Thread.State.NEW) {
				targetThread.start(); // 구동 시키기. 지금까지는 메인스레드로 start()시켰었음.
			}
			
			// 타겟스레드가 종료 상태인지 검사
			if(state == Thread.State.TERMINATED) {
				break; //무한루프를 빠져나옴
			}
			
			try {
				Thread.sleep(500); //44Line이 break가 안되면 5초 쉬었다가 다시 루프
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	

	
	
	
}


// 타켓용 스레드
class TargetThread extends Thread {
	@Override
	public void run() {
		for(long i=1; i<=1000000000L; i++) {} // 시간 지연용
		try {
			Thread.sleep(1500); //타임웨이팅 1.5sec
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		for(long i=1; i<=1000000000L; i++) {} // 시간 지연용
	}
}	

