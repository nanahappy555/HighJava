package kr.or.ddt.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student implements Comparable<Student> {
	private String name;
	private String num;
	private int korean;
	private int english;
	private int math;
	private int sum;
	private int rank;
	
	
	public Student(String name, String num, int korean, int english, int math) {
		super();
		this.num = num;
		this.name = name;
		this.korean = korean;
		this.english = english;
		this.math = math;
		this.sum=korean+english+math;
	}
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKorean() {
		return korean;
	}
	public void setKorean(int korean) {
		this.korean = korean;
	}
	public int getEnglish() {
		return english;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	
	public int getSum() {
		return sum;
	}
	public void setSum() {
		this.sum = korean+math+english;
	}
	public void setRank(int rank) {
		this.rank=rank;
	}
	public static void setRank(List<Student> list) {
	      Collections.sort(list,new StudentSumComparator());
	      int rank=1;
	      list.get(0).rank=rank;
	      for(int i=1;i<list.size();i++) {
	         if(list.get(i).getSum()!=list.get(i-1).getSum()) {
	            rank++;
	         }
	         list.get(i).rank=rank;
	      }
	}
	/*
	 * public static void setRank(List<Student> list) {
	 * 
	 * for (Student student1 : list) { int rank = 1; List<Integer> score = new
	 * ArrayList<Integer>(); for (Student student2 : list) {
	 * 
	 * if (student1.getSum() < student2.getSum()) { if
	 * (!score.contains(student2.getSum())) { score.add(student2.getSum()); rank++;
	 * }
	 * 
	 * } student1.rank = rank; } } }
	 */
	
	public int getRank() {
		return rank;
	}

	

	@Override
	public String toString() {
		return String.format("이름 : %s, 학번 : %s, 국어 점수 : %s, 영어 점수 : %s, 수학 점수 : %s, 총점 : %s, 등수 : %s", name, num,
				korean, english, math, sum, rank);
	}

	@Override
	public int compareTo(Student student) {
		return this.getNum().compareTo(student.getNum());
	}
	
	
}
