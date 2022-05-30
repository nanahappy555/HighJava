package kr.or.ddit.basic;

public class T15SyncThreadTest {
	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();
		Thread th1 = new WorkThread("1번쓰레드", sObj);
		Thread th2 = new WorkThread("2번쓰레드", sObj);
		
		th1.start();
		th2.start();
		
		
	}
}

// 공통으로 사용할 객체
class ShareObject {
	private int sum = 0;
	
	//동기화 하는 방법1: 메서드 자체에 동기화 설정하기 (위치 중요x) 실행하는 동안 동기화하기
	//synchronized public void add() {
	synchronized public void add() {
		
		//동기화 하는 방법2 : 동기화 블럭으로 설정하기
		//(mutex) 동기화 시킬 객체
		//공유객체에 접근 자체를 막는 것. 공유객체에 한 명만 들어올 수 있다.
		synchronized (this) {
			
		for(int i=0; i<1000000000; i++) {} // 동기화처리 전까지 시간벌기용
			
		int n = sum;
		n += 10;
		sum = n;
		
		System.out.println(Thread.currentThread().getName() //작업중인 스레드 확인
				+ "합계: " + sum);
		}
	}
}

// 작업을 수행하는 스레드
class WorkThread extends Thread {
	private ShareObject sObj;
	
	public WorkThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			synchronized (sObj) {
				sObj.add();
			}
		}
	}
}

