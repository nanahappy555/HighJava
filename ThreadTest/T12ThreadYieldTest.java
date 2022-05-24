package kr.or.ddit.basic;

public class T12ThreadYieldTest {
/*
    yield() 에  대하여...
    
   1. 현재 실행 대기중인 동등한 우선순위 이상의 다른 스레드에게 실행기회를
      제공한다.(양보)
   2. 현재 실행중인 스레드의 상태를 Runnable 상태로 바꾼다.
   3. yield() 메서드를 실행한다고 해서 현재 실행중인 스레드가 곧바로
     Runnable 상태로 바뀐다고 확신할 수 없다.
*/
	public static void main(String[] args) {
		Thread th1 = new YieldThreadEx1();
		Thread th2 = new YieldThreadEx2();
		
		th1.start();
		th2.start();
	}
}


class YieldThreadEx1 extends Thread {
	public YieldThreadEx1() {
		super("양보 스레드"); //스레드 생성자 호출로 스레드 이름 부여하는 것
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			System.out.println(Thread.currentThread().getName() //현재 수행중인 스레드의 이름 : 정수 i
					+ " : " + i);
			Thread.yield();
		}
	}
}

class YieldThreadEx2 extends Thread {
	public YieldThreadEx2() {
		super("비양보 스레드");
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			System.out.println(Thread.currentThread().getName()
					+ " : " + i);
		}
	}
}




