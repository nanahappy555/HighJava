package kr.or.ddit.basic.E01StudentList;

import java.util.Comparator;

public class SortStudent implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		if (s1.getTotal() > s2.getTotal()) {
			return -1;
		} else if (s1.getTotal() == s2.getTotal()) {
			return s1.compareTo(s2) * -1;
		}
		return 1;
	}
	/*
	 * 총점이 같을 때 
	 *  @Override
	public int compare(Student std1, Student std2) {
		if (std1.getTotalScore() == std2.getTotalScore()) {
			return std1.getStdNum().compareTo(std2.getStdNum()) * -1;
		} else {
			return Integer.compare(std1.getTotalScore(), std2.getTotalScore()) * -1;
		}
	}

	 */ 
//	std1.getStdNum().compareTo(std2.getStdNum()
	//getStdNum()은 String타입=이미 객체라서 new 생성을 할 필요가 x

}
