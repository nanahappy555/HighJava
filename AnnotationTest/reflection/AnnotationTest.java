package kr.or.ddit.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import kr.or.ddit.basic.PrintAnnotation;
import kr.or.ddit.basic.Service;

public class AnnotationTest {
	public static void main(String[] args) {
		System.out.println("static 변수값 : " + PrintAnnotation.id);
		
		//reflection기능을 이용한 메서드 실행하기
		//선언된 메서드 목록 가져오기
		Class<?> clazz = Service.class;
		Method[] declaredMethods = clazz.getDeclaredMethods();
		
//		Method[] declaredMethods = Service.class.getDeclaredMethods(); //위 두문장과 같은내용
		
		for(Method m : declaredMethods) {
			System.out.println(m.getName()); //메서드명 출력
			
			Annotation[] annos = m.getDeclaredAnnotations();
			
			for(Annotation anno : annos) {
				if(anno.annotationType()
						.getSimpleName().equals("PrintAnnotation")) {
					PrintAnnotation printAnno = (PrintAnnotation) anno; //캐스팅해서 접근..
					for(int i=0; i<printAnno.count(); i++) {
						System.out.print(printAnno.value()); 
						
//						Service에 적어둔 @PrintAnnotation()를 기반으로 출력한 정보들
//						method1는 value가 설정되지 않아 디폴트값 => -가 10번
//						method2는 value %, count는 미설정=> %가 10번
//						method3은 value #, count가 25fktj => #이 25번 찍힘
					}
				}
			}
			System.out.println(); // 줄바꿈 처리
		}
	}
}