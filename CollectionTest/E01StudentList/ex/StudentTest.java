package kr.or.ddt.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentTest {

	public static void main(String[] args) {
		Student std1 = new Student("한태훈", "20223154", 100, 100, 100);
		Student std2 = new Student("홍길동", "20221648", 50, 34, 67);
		Student std3 = new Student("김영희", "20156487", 100, 100, 100);
		Student std4 = new Student("이나영", "20195678", 100, 100, 100);
		Student std5 = new Student("박현빈", "20461325", 76, 55, 67);
		
		List<Student> list = new ArrayList<Student>();
		list.add(std1);
		list.add(std2);
		list.add(std3);
		list.add(std4);
		list.add(std5);
		
		
		
		System.out.println("정렬 전 : ");
		for (Student student : list) {
			System.out.println(student);
		}
		System.out.println("----------------------------------------------------------");
		
		Collections.sort(list);
		
		System.out.println("Student 클래스의 정렬기준(학번 오름차순 정렬)으로 정렬 후 : ");
		for (Student student : list) {
			System.out.println(student);
		}
		
		System.out.println("-----------------------------------------------------------");
		
		//리스트를 섞음.
		Collections.shuffle(list);
		System.out.println("리스트 섞기");
		System.out.println("------------------------------------------------------------");
		
		System.out.println("정렬 전 : ");
		for (Student student : list) {
			System.out.println(student);
		}
		System.out.println("------------------------------------------------------------");
		Student.setRank(list);
		System.out.println("등수 출력");
		for (Student student : list) {
			System.out.println(student);
		}
		
		System.out.println("Student 클래스 외부 기준(총점 역순 정렬)으로 정렬 후");
		Collections.sort(list,new StudentSumComparator());
		for (Student student : list) {
			System.out.println(student);
		}
		
		System.out.println("--------------------------------------");
		
		
	
	}

}
