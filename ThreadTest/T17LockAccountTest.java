package kr.or.ddit.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 은행의 입출금을 스레드로 처리하는 예제
 * (Lock 객체를 이용한 동기화 처리)
 */
public class T17LockAccountTest {
/*
   락 기능을 제공하는 클래스
   
   - ReentrantLock : Read 및 Write 구분없이 사용하기 위한 락 클래스로
                    동기화 처리를 위해 사용된다. Synchronized를 이용한
                    동기화 처리보다 부가적인 기능이 제공된다.
                 ex) Fairness 설정 등. 
                 => 가장 오래 기다린 스레드가 가정먼저 락을 획득하게 함.
*/
	public static void main(String[] args) {
		
		ReentrantLock lock = new ReentrantLock(true);
		
		LockAccount lAcc = new LockAccount(lock);
		lAcc.deposit(10000);
		
		Thread th1 = new BankThread2(lAcc);
		Thread th2 = new BankThread2(lAcc);
		
		th1.start();
		th2.start();
	}
}

// 입출금을 담당하는 클래스 (공유객체)
class LockAccount {
	private int balance; 
	
	// lock객체 생성 => 되도록이면 private final로 만든다.
	private final Lock lock;
	
	public LockAccount(Lock lock) {
		this.lock = lock;
	}

	public int getBalance() {
		return balance;
	}
	
	// 입금하는 메서드
	public void deposit(int money) {
		// Lock객체의 lock()가 동기화 시작이고, unlock()메서드가
		// 동기화의 끝을 나타낸다.
		// lock()으로 동기화를 설정한 곳에서는 *반드시* unlock()메서드로
		// *해제*해 주어야 한다.
		lock.lock(); // 락 설정
		balance += money;  // 동기화 처리 부분
		lock.unlock(); // 락 해제
	}
	
	// 출금하는 메서드(출금 성공: true, 출금 실패: false 반환)
	public boolean withdraw(int money) {
		boolean chk = false;
		//예외가 발생할 수도 있으니까 try-catch문으로 감싸줌
		try {
			lock.lock(); // 락설정
			
			if(balance >= money) {
				for(int i=1; i<= 1000000000; i++) {} // 시간 때우기
				balance -= money;
				System.out.println("메서드 안에서 balance = " 
						+ getBalance());
				chk = true;
			}
		}catch(Exception ex) {
			chk = false;
		}finally {
			// try~catch블럭을 사용할 경우에는 unlock() 호출은
			// finally 블럭에서 하도록 한다. (finally는 반드시 출력되는 부분이니까 lock은 반드시 풀린다)
			lock.unlock(); // 락 해제(반납)
		}
		
		return chk;
	}
}

class BankThread2 extends Thread {
	private LockAccount lAcc;
	
	public BankThread2(LockAccount lAcc) {
		this.lAcc = lAcc;
	}
	
	@Override
	public void run() {
		boolean result = lAcc.withdraw(6000);
		System.out.println(
			Thread.currentThread().getName() 
			+ " 스레드 안에서 result = " + result
			+ ", balance = " + lAcc.getBalance());
	}
}

