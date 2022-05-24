package kr.or.ddit.basic;

class Util {
	/*
	 * 제너릭 메서드 <T, R> R method(T t)
	 * 
	 * 파라미터 타입과 리턴타입으로 타입 파라미터를 가지는 메소드
	 * 
	 * 선언타입 : 리턴타입 앞에 <> 기호를 추가하고 타입파라미터를 기술 후 사용함.
	 */
	//리턴 타입 앞에 제너릭타입을 써야됨
	public static <K, V> boolean compare(Pair<K,V> p1, Pair<K,V> p2) {
		boolean keyCompare = p1.getKey().equals(p2.getKey()); //key가 같으면 true (밑에서 선언한 Pair클래스의 필드)
		boolean valueCompare = p1.getValue().equals(p2.getValue()); //value가 같으면 true
		
		return keyCompare && valueCompare; //true&&true여야 true 둘 중 하나라도 false면 false
	}
	
}

/*
 * 멀티타입 <K,V>를 가지는 제너릭 클래스
 * parameter <K> <V>
 */
class Pair<K, V> {
	private K key; //K = xkdlq
	private V value;
	
	public Pair(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	
	//일반메서드. 제너릭메서드가 아님
	//K랑 V가 오류가 안 나는 이유 : 제너릭 클래스 영역 안이라서 K와 V를 타입으로 인식중
	//매개변수의 K, V는 클래스의 K와 V
//	public void displayAll(K key, V val) {
//		System.out.println(key.toString() + " : " + val.toString());
//		//
//	}
	
	//매개변수의 K, V 는 메소드의 K와 V
	public <K, V> void displayAll(K key, V val) {
		System.out.println(key.toString() + " : " + val.toString());
		//
	}
	
}

public class T03GenericMethodTest {
	public static void main(String[] args) {
		
		//제너릭타입의 객체 생성. *반드시 타입을 알려줘야함.*
		//알려주지 않으면 객체 생성을 위한 메모리를 할당할 수 없어서 객체 생성x
		Pair<Integer, String> p1 = new Pair<Integer, String>(1,"홍길동");
		Pair<Integer, String> p2 = new Pair<Integer, String>(1,"홍길동");
		
		//구체적 타입을 명시적으로 지정(생략가능)
		//선언시에 알려줬기 때문에 생략가능. 근데 써두면 제너릭메소드인걸 바로 알 수 있으니까 쓰면 좋겠다
		boolean result1 = Util.<Integer,String>compare(p1, p2); //true
		
		if(result1) {
			System.out.println("논리(의미)적으로 동일한 객체임."); //출력
		}else {
			System.out.println("논리(의미)적으로 동일한 객체 아님.");
		}
		
		
		Pair<String, String> p3 = new Pair<String, String>("001","홍길동");
		Pair<String, String> p4 = new Pair<String, String>("002","홍길동");
		
		//타입 생략했어도 정상작동
		boolean result2 = Util.compare(p3, p4); //false. key가 다름
		
		if(result2) {
			System.out.println("논리(의미)적으로 동일한 객체임.");
		}else {
			System.out.println("논리(의미)적으로 동일한 객체 아님.");
		}
		
		p1.displayAll("키", 100); //아래와 동일함. 제너릭은 생략가능
		p1.<String,Integer>displayAll("키", 100); //p1에 정해둔 타입은 <인티저,스트링>인데 타입이 안맞아서 에러나는 코드
		//displayAll이 제너릭 메소드라서 지금 지정된 <인티저,스트링>타입과 매개변수 타입이 같으면 에러x
		//만약 displayAll이 일반 메소드였다면 매개변수의 타입은 클래스에 선언된 <스트링, 인티저>타입을 따라야하기 때문에 에러O

	}
}
