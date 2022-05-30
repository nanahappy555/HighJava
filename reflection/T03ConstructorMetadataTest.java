package kr.or.ddit.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class T03ConstructorMetadataTest {
	public static void main(String[] args) {
		
		Class<?> klass = SampleVO.class;
		
		//해당 클래스의 모든 생성 정보 가져오기(접근제어자가 public인 것만)
		Constructor<?>[] constructorArr = klass.getConstructors();
		
		for(Constructor<?> constructor : constructorArr) {
			System.out.println("생성자명 : " + constructor.getName());
			
			//생성자의 접근제어자 정보 가져오기
			int modFlag = constructor.getModifiers();
			System.out.println("생성자의 접근제어자 : " + Modifier.toString(modFlag));
			
			//생성자의 파라미터타입 가져오기
			Class<?>[] paramTypeArr = constructor.getParameterTypes();
			System.out.println("생성자의 파라미터 타입 : ");
			for(Class<?> paramType : paramTypeArr) {
				System.out.print(paramType.getName() + " | ");
			}
			System.out.println();
			
			//생성자에서 던지는 예외타입 가져오기
			Class<?>[] exTypeArr = constructor.getExceptionTypes();
			System.out.println("생성자가 던지는 예외타입 목록 : ");
			for(Class<?> exType : exTypeArr) {
				System.out.println(exType.getName() + " | ");
			}
			System.out.println("-------------------------------------");
		}
	}
}
