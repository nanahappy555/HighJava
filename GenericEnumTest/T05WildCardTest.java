package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.PrinterMakeAndModel;

public class T05WildCardTest {
	/*
	 * 와일드 카드에 대하여...
	 * 
	 * 와일드카드(?)는 제너릭 타입을 이용한 타입 안전한 코드를 위해 사용하는 특별한 종류의 인수(Argument)로서, 변수선언, 객체생성 및
	 * 메서드 정의 할 때 사용된다.
	 * 
	 * <? extends T> => 와일드 카드의 상한 제한. T와 그 자손들만 가능 
	 * <? super T> => 와일드카드의 하한 제한. T와 그 조상들만 가능 
	 * <?> => 모든 타입이 가능 <? extends Object>와 동일.
	 */
	public static void main(String[] args) {
		
		FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();//과일상자
		FruitBox<Apple> appleBox = new FruitBox<Apple>();//사과상자
		
		fruitBox.add(new Apple());
		fruitBox.add(new Grape());
		
		appleBox.add(new Apple());
		appleBox.add(new Apple());
//		appleBox.add(new Grape()); //Apple외에는 넣을 수 없는 제너릭한 ArrayList임
		
		Juicer.makeJuice(fruitBox);
//	메서드를 <Fruit>로 선언했을 때
//		static void makeJuice(FruitBox<Fruit> box) {  //오직 Fruit만 넣을수 있음
//		Juicer.makeJuice(appleBox); //appleBox는 <Fruit>타입이 아니라 에러
//	메서드를 제너릭메서드<T>로 선언했을 때
//		static <T> void makeJuice(FruitBox<Tt> box) {  ///매개변수의 <T>는 메소드의 <T>라서 모든 클래스 넣을 수 있음
//		Juicer.makeJuice(appleBox); //모든타입가능해서 에러x

	}
}

class Juicer {
//	static 일반 메서드
//	static void makeJuice(FruitBox<Fruit> box) {  //Fruit만 넣을수 있음
//	static <T> void makeJuice(FruitBox<T> box) { //과일만 아니라 모든 클래스를 넣을 수 있음
//	static <T extends Fruit> void makeJuice(FruitBox<T> box) { //과일 또는 과일의 자식 클래스만 올 수 있음.
	
	//파라미터가 제너릭한 타입이라는것을 알려주는 형태
	static void makeJuice(FruitBox<? extends Fruit> box) { //일반메서드. Fruit와 그 상속클래스만. 제너릭클래스로 만들지 않아도 에러x
		//제너릭이 아닌 일반메서드로 쓰는 장점이 뭐지???
		//딱히 장점이라기보다... 이런 형태도 가능하다.
		//제너릭메서드로 선언하지 않아도 일반메서드에서 같은 기능을 구현가능.
		//메서드 선언 시점에 제너릭메서드로 선언하는 문법. => 이유: T라는 제너릭한 타입을 파라미터로 받아야하는데 그 표현방법으로 제너릭
		String fruitListStr = "";
		
		int cnt = 0;
		for(Fruit f : box.getFruitList()) { //box(후르츠 리스트)에 있는 과일객체를 f에 담아서 출력하려고
			if(cnt == 0) { //후르츠 리스트에 있는 과일객체를 담은 f를 String타입에 순차적으로 담음(f, f, f형태의 텍스트 )
				fruitListStr += f; //
			} else {
				fruitListStr += ", " + f;
			}
			
			cnt++; //,위치를 위해...
		}
		
		System.out.println(fruitListStr + " => 쥬스 완성!!");
	}
}

class Fruit {
	private String name;

	public Fruit(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "과일 (name=" + name + ")";
	}
}


class Apple extends Fruit {

	public Apple() {
		super("사과");
	}
}


class Grape extends Fruit {

	public Grape() {
		super("포도");
	}
}


class FruitBox<T> {
	private List<T> fruitList;

	public FruitBox() {
		fruitList = new ArrayList<T>();
	}
	
	public List<T> getFruitList() {
		return fruitList;
	}
	
	public void add(T fruit) {
		fruitList.add(fruit);
	}
}
