package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T06WildCardTest2 {
	
	//장바구니를 항목조회를 위한 메서드 (모든타입을 받는 장바구니)
	//아직 정해지지 않은 타입이라는 의미에서 ?와일드카드 썼음
	public static void displayCartItemInfo(Cart<?> cart) { 
		//왜 static이냐면???
		//메인메소드에서 new T06WildCardTest2();없이 쉽게 호출하려고 걍 이렇게 만들었음
		//static에서는 static메소드를 객체생성없이 바로 호출할수있음
		System.out.println("=음식류 장바구니 항목 리스트=");
		for(Object obj : cart.getList()) {
			System.out.println(obj.toString());
		}
		System.out.println("-------------------------------");
	}
	
	//장바구니를 항목조회를 위한 메서드2 (음료와 그 하위만 받는 장바구니)
	//아직 정해지지 않은 타입이라는 의미에서 ?와일드카드 썼음
	public static void displayCartItemInfo2(Cart<? extends Drink> cart) { 
		//왜 static이냐면???
		System.out.println("=음료류 장바구니 항목 리스트=");
		for(Object obj : cart.getList()) {
			System.out.println(obj.toString());
		}
		System.out.println("-------------------------------");
	}
	
	//장바구니를 항목조회를 위한 메서드3 (고기류나 그 상위만 받는 장바구니)
	//아직 정해지지 않은 타입이라는 의미에서 ?와일드카드 썼음
	public static void displayCartItemInfo3(Cart<? super Meat> cart) { 
		//왜 static이냐면???
		System.out.println("=음료류 장바구니 항목 리스트=");
		for(Object obj : cart.getList()) {
			System.out.println(obj.toString());
		}
		System.out.println("-------------------------------");
	}
	
	public static void main(String[] args) {
		
		Cart<Food> foodCart = new Cart<Food>();
//		Cart<Food> foodCart = new Cart<>(); 생략가능>< JDK 1.8 이하에서는 x
		foodCart.addItem(new Meat("돼지고기", 5000));
		foodCart.addItem(new Meat("소고기", 10000));
		foodCart.addItem(new Juice("오렌지주스", 1500));
		foodCart.addItem(new Coffee("아메리카노", 2000));
		
		Cart<Meat> meatCart = new Cart<>();
		foodCart.addItem(new Meat("돼지고기", 5000));
		foodCart.addItem(new Meat("소고기", 10000));
		
		Cart<Drink> drinkCart = new Cart<>();
		foodCart.addItem(new Juice("오렌지주스", 1500));
		foodCart.addItem(new Coffee("아메리카노", 2000));
		
		//전체타입 (전체타입 허용 와일드카드 Cart<?>)
		displayCartItemInfo(foodCart);
		displayCartItemInfo(meatCart);
		displayCartItemInfo(drinkCart);
		
		//음료와 그 하위 Cart<? extends Drink>
//		displayCartItemInfo2(foodCart); //에러
//		displayCartItemInfo2(meatCart); //에러
		displayCartItemInfo2(drinkCart);
		
		//고기와 그 상위만 Cart<? super Meat>
		displayCartItemInfo3(foodCart);
		displayCartItemInfo3(meatCart);
//		displayCartItemInfo3(drinkCart); //에러
		
		
	}
}

class Food {
	private String name; //음식이름
	private int price; //음식가격
	
	public Food(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return this.name + "(" + this.price + "원)";
	}
}


//Food를 extends하는 자식클래스
class Meat extends Food {

	public Meat(String name, int price) {
		super(name, price);
	}
	
}

class Drink extends Food {

	public Drink(String name, int price) {
		super(name, price);
	}
}

class Juice extends Drink {

	public Juice(String name, int price) {
		super(name, price);
	}
}

class Coffee extends Drink {

	public Coffee(String name, int price) {
		super(name, price);
	}
}

//---------------------------------------------------

class Cart<T> {
	private List<T> list = new ArrayList<T>();

	public List<T> getList() {
		return list;
	}
	
	public void addItem(T item) {
		list.add(item);
	}
}