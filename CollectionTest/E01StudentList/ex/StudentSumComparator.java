package kr.or.ddt.basic;

import java.util.Comparator;

public class StudentSumComparator implements Comparator<Student> {

	@Override
	public int compare(Student student1, Student student2) {
		if(student1.getSum()==student2.getSum()) {
			return student1.compareTo(student2)*-1;
		}	
		else {
			return Integer.valueOf(student1.getSum()).compareTo(Integer.valueOf(student2.getSum()))*-1;
		}
	}

}
