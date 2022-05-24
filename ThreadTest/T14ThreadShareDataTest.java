package kr.or.ddit.basic;

public class T14ThreadShareDataTest {
/*
    스레드에서 데이터를 공통으로 사용하는 방법
    
    1. 공통으로 사용할 데이터를 클래스로 정의한다.
    2. 공통으로 사용할 클래스의 인스턴스를 만든다.
    3. 이 인스턴스를 각각의 스레드에 넘겨준다.
    4. 각각의 스레드는 이 인스턴스의 참조값을 저장한 변수를
      이용하여 공통 데이터를 사용한다.
      
    예) 원주율을 계산하는 스레드가 있고, 계산된 원주율을 출력하는 
         스레드가 있다. 원주율을 계산한 후 이 값을 출력하는 프로그램을
         작성하시오.(이 때 원주율을 저장하는 객체가 필요하다.)
*/
	public static void main(String[] args) {
		ShareData sd = new ShareData();
		
		//같은 공유객체를 생성자 파라미터로 넣어줌
		Thread th1 = new CalcPIThread(sd);
		Thread th2 = new PrintPIThread(sd);
	
		th1.start();
		th2.start();
		//각 스레드를 스타트시켜주고 메인메서드는 더 할 일이 없으니 죽음...
	}
}

// 원주율을 관리하는 클래스(공통으로 사용할 클래스)
class ShareData {
	public double result; // 원주율이 저장될 변수
	/*
	    volatile => 선언된 변수를 컴파일러의 최적화 대상에서 제외시킨다.
	              즉, 값이 변경되는 즉시 변수에 적용시킨다.
	             다중 스레드에서 하나의 변수가 완벽하게 한번에 작동하도록
	              보장하는 키워드(일종의 동기화) 
	*/
	public volatile boolean isOk = false; // 원주율 계산 완료여부 체크용
	//volatile ; 휘발성
	//원본에 있는 값을 참조하라는 뜻... 메인 메모리로부터 읽고 쓰는 작업 이상의 것을 보장
	//장점 : 이상하게 동작하는 것x
	//단점 : 최적화에서 제외되니까 속도가 느려짐. (강제 동기화)
	//제대로 된 결과가 나오는 것이 중요하니까 속도 느린건 어쩔수 없음.
	
}

// 원주율을 계산하는 스레드
class CalcPIThread extends Thread {
	private ShareData sd;
	
	public CalcPIThread(ShareData sd) { //위에서 정의한 공유객체
		this.sd = sd;
	}
	
	@Override
	public void run() {
	/*
	    원주율 = (1/1 - 1/3 + 1/5 - 1/7 + 1/9 ......) * 4;
	               1  -  3  +  5  -  7  +  9  => 분모
	               0     1     2     3     4  => 2로 나눈 몫
	               나눈 몫이 홀수면 -, 짝수면 +라는 것을 알 수 있다.
	*/
		double sum = 0.0;
		for(int i=1; i<=1500000000; i+=2) {
			if(((i/2) % 2) == 0) { // 2로 나눈 몫이 짝수일 때...
				sum += (1.0/i);
			}else { // 2로 나눈 몫이 홀수일 때...
				sum -= (1.0/i);
			}
		}
		
		sd.result = sum * 4; // 계산된 원주율을 공통객체의 멤버변수에 저장
		sd.isOk = true;      // 계산이 완료되었음을 나타낸다.
	}
}

// 계산된 원주율을 출력하는 스레드
class PrintPIThread extends Thread {
	private ShareData sd;
	public PrintPIThread(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		while(true) {
			// 원주율 계산이 완료될 때까지 기다린다.
			if(sd.isOk) { //sd.isOk가 true일 때 break;
				break;
			}
		}
		System.out.println();
		System.out.println("계산된 원주율 : " + sd.result);
		System.out.println("           PI : " + Math.PI);
	}
}







