package kr.or.ddit.basic.day1ex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class StudentExample {
	public static void main(String[] args) {
		List<Student> stuList = new ArrayList<Student>();
		
		stuList.add(new Student("1", "홍길동", 90, 80, 70));
		stuList.add(new Student("3", "변학도", 75, 60, 70));
		stuList.add(new Student("6", "성춘향", 80, 80, 65));
		stuList.add(new Student("2", "일지매", 50, 70, 85));
		stuList.add(new Student("5", "강감찬", 60, 75, 75));
		stuList.add(new Student("4", "이순신", 90, 80, 70));

		System.out.println("정렬 전 : ");
		for (Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println("---------------------------------------------");

		// 학번 오름차순 정렬
		Collections.sort(stuList);
		System.out.println("정렬 후(학번 오름차순) : ");
		for (Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println("---------------------------------------------");

		// 총점의 역순으로 정렬
		Collections.sort(stuList, new SortStudent());
		System.out.println("정렬 후(총점의 내림차순) : ");
		for (Student stu : stuList) {
			System.out.println(stu);
		}

		// 랭킹출력
		for(Student stu : stuList) {
			System.out.println(stu.getName() + "의 등수" + stu.getRank(stu)); //객체변수와 매개변수가 같은 객체인지 재정의가x
		}
		
//		public static void Ranking(List<Student> stdList) {
//			for (Student std1 : stdList) {
//				int rank = 1;
//				for (Student std2 : stdList) {
//					if (std1.getTotalScore() < std2.getTotalScore()) {
//						rank++;
//					}
//				}
//				std1.setRank(rank);
//			}
//		}

	}

}