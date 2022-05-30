package kr.or.ddit.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class T04MethodMetadataTest {
	public static void main(String[] args) {
		
		Class<?> clazz = SampleVO.class;
		
		// 클래스에 선언된 모든 메서드의 메타데이터 정보 가져오기
		Method[] methodArr = clazz.getDeclaredMethods();
		
		for(Method m : methodArr) {
			
			System.out.println("메서드명 : " + m.getName());
			System.out.println("메서드 리턴타입 : " + m.getReturnType());
			
			// 해당 메서드의 접근제어자 정보 가져오기
			int modFlag = m.getModifiers();
			System.out.println("메서드 접근제어자 : " 
					+ Modifier.toString(modFlag));
			
			// 해당 메서드의 파라미터 타입 가져오기
			Class<?>[] paramTypeArr = m.getParameterTypes();
			System.out.println("메서드 파라미터 타입정보 :");
			for(Class<?> paramType : paramTypeArr) {
				System.out.print(paramType.getName() + " | ");
			}
			System.out.println();
			
			// 해당 메서드에서 던지는 예외타입 가져오기
			Class<?>[] exTypeArr = m.getExceptionTypes();
			System.out.println("메서드에서 던지는 예외타입 정보 :");
			for(Class<?> exType : exTypeArr) {
				System.out.print(exType + " | ");
			}
			System.out.println();
			
			// 해당 메서드에 존재하는 annotation 타입정보 가져오기
			Annotation[] annos = m.getDeclaredAnnotations();
			System.out.println("Annotation 타입 정보 : ");
			for(Annotation anno : annos) {
				System.out.print(anno.annotationType().getName() + " | ");
			}
			System.out.println();
			System.out.println("-----------------------------------------");
		}
	}
}
