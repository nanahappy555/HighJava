package kr.or.ddit.basic;

/*
 * 제너릭 클래스 만드는 방법
 * 
 * 형식)
 * 
 * class 클래스명<제너릭타입글자> {
 * 		제너릭타입글자 변수명; //변수 선언에 제너릭을 사용할 경우
 * 		...
 * 
 * 		제너릭타입글자 메서드명() { //반환값이 있는 메서드에서 사용
 * 			...
 * 
 * 			return 값;
 * 		}
 * 
 * 		...
 * 	}
 * 
 * --제너릭타입글자--
 * T => Type
 * K => Key
 * V => Value
 * E => Element
 * 
 */
class NonGenericClass {
	//모든 타입을 처리하기 위해 Object를 씀
	private Object val;

	public Object getVal() {
		return val;
	}

	public void setVal(Object val) {
		this.val = val;
	}
	
}
//T는 스트링일수도...펄슨일수도...etc
//원래 해당 클래스를 다 써줘야하지만 제너릭으로 모든 클래스가 올 수 있게 정의함
//Object로 받을때의 차이
class MyGeneric<T> {
	//T라고만 썼는데 제너릭 클래스라서 에러나지 않음 
	private T val;

	public T getVal() {
		return val;
	}

	public void setVal(T val) {
		this.val = val;
	}
	
}

public class T02GenericTest {
	public static void main(String[] args) {
		
		NonGenericClass ng1 = new NonGenericClass();
		ng1.setVal("가나다라"); //스트링도 Object라 문제없음
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setVal(100);
		
		//getVal을 Object타입으로 저장해놔서 String으로 캐스팅을 해줘야함
		//캐스팅을 안하면 컴파일 에러
		String rtnVal1 = (String) ng1.getVal(); 
		System.out.println("문자열 반환값 rtnNg1 => " + rtnVal1);
		
		Integer irtnVal2 = (Integer) ng2.getVal();
		System.out.println("정수 반환값 irtnNg2 => " + irtnVal2);
		System.out.println();
		
		//객체 생성 시점에 <T>라고 적어둔게 사실 Stirng/Integer이라고 명시해줌
		//<T>는 모두 String이었다고 생각함
		MyGeneric<String> mg1 = new MyGeneric<String>();
		MyGeneric<Integer> mg2 = new MyGeneric<Integer>();
		
		mg1.setVal("우리나라");
//		mg1.setVal(600); String타입이 아니면 컴파일러 단계에서 에러가 남! 만약 논제너릭 Object였다면 이 시점에서는 에러가 안낫을것
		mg2.setVal(500);
		
		//처음부터 String으로 선언한것과 동일한 효과라 캐스팅필요없음
		rtnVal1 = mg1.getVal();
		irtnVal2 = mg2.getVal();
		
		System.out.println("제너릭 문자열 반환값 : " + rtnVal1);
		System.out.println("제너릭 정수형 반환값 : " + irtnVal2);
		
		
	}
}
