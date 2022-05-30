package kr.or.ddit.reflection;

import java.lang.reflect.Modifier;

/*
 * 클래스 정보 꺼내보는 클래스
 */
public class T02ClassMetadataTest {
	public static void main(String[] args) throws ClassNotFoundException {
		// 클래스 오브젝트 생성하기
		//()클래스의 정보가져오기. 클래스의 정보에 접근할수있게됨
		Class<?> clazz = Class.forName("kr.or.ddit.reflection.SampleVO");
		
		System.out.println("심플클래스명 : " +  clazz.getSimpleName());
		System.out.println("클래스명 : " +  clazz.getName());
		System.out.println("상위클래스명 : " +  clazz.getSuperclass().getName());
		
		//패키지 정보 가져오기
		Package pkg = clazz.getPackage();
		System.out.println("패키지 정보 : " + pkg.getName());
		
		//해당 클래스에서 구현하고 있는 인터페이스목록 가져오기
		//인터페이스는 다중상속 가능해서 s 복수형
		Class<?>[] interfaces = clazz.getInterfaces();
		
		System.out.println("인터페이스 목록 => ");
		for(Class<?> inf : interfaces ) {
			System.out.println(inf.getName() + " | ");
		}
		System.out.println();
		
		// 클래스의 접근제어자 가져오기(flag bit값 반환됨) 복수형s
		int modFlag = clazz.getModifiers();
		
		System.out.println("접근제어자 : " + Modifier.toString(modFlag));
	}
}
