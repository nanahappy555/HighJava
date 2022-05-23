package kr.or.ddit.basic;

public class T04ThreadTest {
/*
    1~20억까지의 합계를 구하는데 걸린 시간 체크해 보기
    전체 합계를 구하는 작업을 단독으로 했을 때(1개의 스레드를 이용)와
    여러 스레드로 분할해서 작업할 때의 시간을 확인해 보기. 
*/
	public static void main(String[] args) {
		//단독으로 처리할 때...
		SumThread sm = new SumThread(1L, 2000000000L);
		long startTime = System.currentTimeMillis();
		
		sm.start();
		
		try {
			sm.join(); // 현재 실행중인 스레드(메인스레드)에서 작업중인 스레드(sm스레드)
			           // (지금은 sm스레드)가 종료될 때까지 기다린다.
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("단독으로 처리할 때의 처리시간 : " 
				+ (endTime - startTime) + "(ms)");
		System.out.println();
		System.out.println();
		
		//여러 스레드가 협력해서 처리 할때...
		SumThread[] sumThs = new SumThread[] {
			new SumThread(1L,           500000000L),	
			new SumThread(500000001L,  1000000000L),	
			new SumThread(1000000001L, 1500000000L),	
			new SumThread(1500000001L, 2000000000L),	
		};
		
		startTime = System.currentTimeMillis();
		
		for(Thread th : sumThs) {
			th.start();
		}
		for(Thread th : sumThs) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		endTime = System.currentTimeMillis();
		
		System.out.println("렵력 처리(4개의 스레드) 처리할 때의 처리시간 : " 
				+ (endTime - startTime) + "(ms)");
	}
}

class SumThread extends Thread {
	private long min, max;
	
	//생성자의 파라미터로 범위를 받음
	public SumThread(long min, long max) {
		this.min = min;
		this.max = max;
	}
	
	@Override
	public void run() {
		long sum = 0L;
		for(long i=min; i<=max; i++) {
			sum += i;
		}
		System.out.println(min + " ~ " + max  + "까지의 합 : "
								+ sum);
	}
}
