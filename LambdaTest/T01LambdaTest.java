package kr.or.ddit.basic;

public class T01LambdaTest {
/*
 * 람다식 => 익명함수를 생성하기 위한 식
 * 	 	   자바에서는 '매개변수를 가진 코드블럭'
 * 			=> 런타임시 익명구현객체로 생성된다.
 * 형식) 인터페이스명 객체변수명 = 람다식;
 * 
 * 람다식의 형식) (매개변수들...) -> {처리할 코드들;...}
 * 
 * => 람다식으로 변환할 수 있는 인터페이스는 추상메서드가 1개인 인터페이스만 처리할 수 있다.
 * 	  이런 인터페이스를 *함수적 인터페이스*라고 한다.
 * 	  이 함수적 인터페이스를 생성할 때는 @FunctionalInterface로 지정한다.
 */
	public static void main(String[] args) {
		//람다식을 사용하지 않는 경우
		Thread th1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=1; i<=10; i++) {
					System.out.println(i);
				}
			}
		});
		
		th1.start();
		
		//람다식을 사용하는 경우...
		Thread th2 = new Thread(
			()->{  //()안에 메소드 이름이 없어도 메소드가 하나밖에 없으니까 알아서 정의됨
				for(int i=1; i<=10; i++) {
					System.out.println("람다" + i);
				}
			});
		th2.start();
	}
}
