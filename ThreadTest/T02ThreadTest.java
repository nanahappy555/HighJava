package kr.or.ddit.basic;

/**
 * 멀티스레드 프로그램
 * 총 4개의 스레드 존재 => 메인스레드, 마이스레드1, 마이스레드2, 마이스레드3
 * (메인스레드 : main메소드의 코드가 끝나기 전, return문이 나오기 전까지항상 run)
 */
public class T02ThreadTest {
	public static void main(String[] args) {
		// 방법1: Thread클래스를 상속한 class의 인스턴스를 생성한 후
		//       이 인스턴스의 start() 메서드를 호출한다.
		MyThread1 th1 = new MyThread1();
		th1.start();
		
		// 방법2: Runnable *인터페이스*를 구현한 클래스의 인스턴스를 생성한 후
		//		 이 인스턴스를 Thread객체의 인스턴스를 생성할 때 생성자의 매개변수로 넘겨준다.
		//		 이때 생성된 Thread 객체의 인스턴스의 start()메서드를 호출한다.
		Runnable r = new MyThread2();
		Thread th2 = new Thread(r);
		th2.start();
		
		// 방법3: 익명클래스를 사용하는 방법 *일회성*
		//		 Runnable 인터페이스를 구현한 익명클래스를 이용하여 스레드 객체를 생성한다.
		Thread th3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=1; i<=200; i++) {
					System.out.print("@");
					
					try {
						// Thread.sleep(시간) =>  주어진 시간동안 작업을 잠시 멈춘다.
						// 시간은 밀리세컨드 단위를 사용한다. (1초 = 1ms)
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		th3.start();
	}
}

//상속하면 본인도 Thread클래스가 됨
class MyThread1 extends Thread {
	
	//run() 안에 작업할 내용 작성
	@Override
	public void run() {
		for(int i=1; i<=200; i++) {
			System.out.print("*");
			
			try {
				// Thread.sleep(시간) =>  주어진 시간동안 작업을 잠시 멈춘다.
				// *찍고 1초멈추고 *찍고 1초 멈추고 반복함
				// 시간은 밀리세컨드 단위를 사용한다. (1초 = 1ms)
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class MyThread2 implements Runnable {

	@Override
	public void run() {
		for(int i=1; i<=200; i++) {
			System.out.print("$");
			
			try {
				// Thread.sleep(시간) =>  주어진 시간동안 작업을 잠시 멈춘다.
				// 시간은 밀리세컨드 단위를 사용한다. (1초 = 1ms)
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}



