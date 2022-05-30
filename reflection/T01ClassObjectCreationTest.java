package kr.or.ddit.reflection;

public class T01ClassObjectCreationTest {
/*
    Java Reflection에 대하여...
    
   1. 리플렉션은 런타임 시점에 클래스 또는 멤버변수, 메서드, 생성자에 대한 정보를
      가져오거나 수정할 수 있고, 새로운 객체를 생성하거나, 메서드를 실행할 수 있다.
      (컴파일 시점에 해당 정보를 알수 없는 경우(소스코드 부재)에 유용하게 사용됨)
      
   2. Reflection API는 java.lang.reflect 패키지와 java.lang.Class를 통해 제공된다.
   3. java.lang.Class 의 주요 메서드
     - getName(), getSuperclass(), getInterfaces(), getModifiers() 등.
   4. java.lang.reflect 패키지의 주요클래스
     - Field, Method, Constructor, Modifier 등.
*/
	public static void main(String[] args) throws ClassNotFoundException {
		//Class 오브젝트(클래스 정보를 담고 있는) 생성하기
		
		//첫번째 방법 : Class.forName() 메서드 이용  (스태틱임..)
		//("") 안의 클래스명을 ???하기 위해 클래스에 throws ClassNotFoundException를 던져줌
		Class<?> kclass = Class.forName("kr.or.ddit.reflection.T01ClassObjectCreationTest");
		
		//두번째 방법 : getClass() 메서드 이용  (Object의 인스턴스메서드..)
		T01ClassObjectCreationTest obj = new T01ClassObjectCreationTest();
		kclass = obj.getClass();
		
		// 세번째 방법 :  .class 이용
		kclass = kr.or.ddit.reflection.T01ClassObjectCreationTest.class;
	}
}