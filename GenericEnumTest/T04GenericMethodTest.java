package kr.or.ddit.basic;


class Util2 {
	//제한된 타입 파라미터 *extends*
	//<T extends Number>는 Number를 상속한 타입이고 Number자신도포함 
	public static <T extends Number> int compare(T t1, T t2) {
		
		//제한된 타입 파라미터를 주기 전가지는 모든 타입이 들어올 수 있어서
		//doubleValue()메서드를 사용할 수 있는지 알 수 없기 때문에 에러
		double v1 = t1.doubleValue(); //Number타입이 가지고 있는 doubleValue()
		double v2 = t2.doubleValue();
		
		return Double.compare(v1, v2);
		
	}
}

/*
 *제한된 타입파라미터(Bounded Parameter) 예제 
 */
public class T04GenericMethodTest {
	public static void main(String[] args) {
		
		//compare() 앞이 크면 1
		int result1 = Util2.compare(10, 20); //<Integer,Integer>
		System.out.println(result1);// -1 /20이 커서
		
		int result2 = Util2.compare(3.14, 3);
		System.out.println(result2); // 1 /3.14가 커서
		
//		int result2 = Util2.compare(3.14, "JAVA"); //컴파일러에러
	}
}
