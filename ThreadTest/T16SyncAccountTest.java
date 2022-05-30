package kr.or.ddit.basic;

/**
 * 은행의 입출금을 스레드로 처리하는 예제
 * (synchronized를 이용한 동기화 처리)
 */
public class T16SyncAccountTest {
	public static void main(String[] args) {
		SyncAccount sAcc = new SyncAccount();
		sAcc.deposit(10000);
		
		Thread th1 = new BankThread(sAcc);
		Thread th2 = new BankThread(sAcc);
		
		th1.start();
		th2.start();
		
	}
}

// 은행의 입출금을 관리하기 위한 클래스
class SyncAccount {
	private int balance; // 잔액

	public synchronized int getBalance() {
		return balance;
	}
	
	// 입금 처리를 수행하는 메서드
	public void deposit(int money) {
		balance += money;
	}
	
	// 출금을 처리하는 메서드(출금 성공: true, 출금 실패: false 반환)
	// 동기화 영역에서 호출하는 메서드도 동기화 처리를 해 주어야 한다.
	public synchronized boolean withdraw(int money) {
		
		if(balance >= money) { // 잔액이 많은 경우...
			for(int i=1; i<=1000000000; i++) {} // 시간 때우기
			balance -= money;
			System.out.println("메서드 안에서 balance = " 
					+ getBalance());
			return true;
		}else {
			return false;
		}
	}
}

// 은행 업무를 처리하는 스레드
class BankThread extends Thread {
	private SyncAccount sAcc;
	
	public BankThread(SyncAccount sAcc) {
		this.sAcc = sAcc;
	}
	
	@Override
	public void run() {
		boolean result = sAcc.withdraw(6000); // 6000원 인출
		System.out.println("스레드 안에서 result = " + result
				+ ", balance = " + sAcc.getBalance());
	}
}

