package kr.or.ddit.basic;

public class T19WaitNotifyTest {
/*
    wait() => 동기화 영역에서 락을 풀고 Wait-Set영역(공유객체별 존재)으로
              이동 시킨다.
    notify() 또는 notifyAll() => Wait-Set영역에 있는 스레드를 깨워서
                                실행될 수 있도록 한다.
                        (notify()는 하나, notifyAll()은 전부를 깨운다.)
                        
                        
   => wait()과 notify(), notifyAll()은 동기화 영역에서만 실행할 수 있고,
     Object클래스에서 제공하는 메서드 이다.
*/
	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
	}
}

// 공통으로 사용할 객체
class WorkObject {
	public synchronized void methodA() {
		System.out.println("methodA()메서드 작업 중...");
		
		notify(); //깨우고
		
		try {
			wait(); //대기실로..
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public synchronized void methodB() { //동기화영역. 여기 들어오면 락이 걸림
		System.out.println("methodB()메서드 작업 중...");
		
		notify();
		
		try {
			wait(); //시간을 안주면 깨워줄 사람이 없어서 계속 종료안되고 남아있
//			wait(1000); //시간을 주면 시간이 지나면 알아서 깨어나서 작업다하고 끝남
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


// WorkObject의 methodA()메서드만 호출하는 스레드
class ThreadA extends Thread {
	private WorkObject workObj;
	
	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			workObj.methodA();
		}
		
		System.out.println("ThreadA 종료.");
	}
}
// WorkObject의 methodB()메서드만 호출하는 스레드
class ThreadB extends Thread {
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			workObj.methodB();
		}
		
		System.out.println("ThreadB 종료.");
	}
}

