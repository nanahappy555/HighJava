package kr.or.ddit.basic;

public class T08ThreadPriorityTest {
	public static void main(String[] args) {
		
		System.out.println("최대 우선순위 : " + Thread.MAX_PRIORITY);
		System.out.println("최소 우선순위 : " + Thread.MIN_PRIORITY);
		System.out.println("보통 우선순위 : " + Thread.NORM_PRIORITY);
		
		Thread[] ths = new Thread[] {
			new ThreadTest1(),
			new ThreadTest1(),
			new ThreadTest1(),
			new ThreadTest1(),
			new ThreadTest1(),
			new ThreadTest2()
		};
		
		//우선 순위는 start()메서드를 호출하기 전에 설정해야 한다.
		for(int i=0; i < ths.length; i++) {
			if(i == 5) {
				ths[i].setPriority(10); //가장높은거
			}else {
				ths[i].setPriority(1); //가장낮은거
			}
		}
		
		for(Thread th : ths) {
			th.start();
		}
		
		//우선순위 출력
		for(Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}

//대문자를 출력하기 위한 스레드
class ThreadTest1 extends Thread {
	@Override
	public void run() {
		for(char ch='A'; ch<='Z'; ch++) {
			System.out.println(ch);
			
			//아무것도 하지 않는 반복문(시간때우기용)
			for(long i=1; i<=1000000000L; i++) {
				
			}
		}
	}
}
//소문자를 출력하기 위한 스레드
class ThreadTest2 extends Thread {
	@Override
	public void run() {
		for(char ch='a'; ch<='z'; ch++) {
			System.out.println(ch);
			
			//아무것도 하지 않는 반복문(시간때우기용)
			for(long i=1; i<=1000000000L; i++) {
				
			}
		}
	}
}
