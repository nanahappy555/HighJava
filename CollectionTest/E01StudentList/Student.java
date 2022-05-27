package kr.or.ddit.basic.E01StudentList;

import java.util.List;
import java.util.Objects;


public class Student implements Comparable<Student> {
	private String studentNo; // 학번
	private String name;
	private int korScore;
	private int engScore;
	private int mathScore;
	private int total;
//	private int rank;

	public Student(String studentNo, String name, int korScore, int engScore, int mathScore) {
		super();
		this.studentNo = studentNo;
		this.name = name;
		this.korScore = korScore;
		this.engScore = engScore;
		this.mathScore = mathScore;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKorScore() {
		return korScore;
	}

	public void setKorScore(int korScore) {
		this.korScore = korScore;
	}

	public int getEngScore() {
		return engScore;
	}

	public void setEngScore(int engScore) {
		this.engScore = engScore;
	}

	public int getMathScore() {
		return mathScore;
	}

	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}

	public int getTotal() {
		return korScore + engScore + mathScore;
	}

	public int getRank(List<Student> list) {
		return list.indexOf(this); //매개변수 Student객체를 넣어줘야됨
	}

	@Override
	public int compareTo(Student s) {
		return this.getStudentNo().compareTo(s.getStudentNo()); //인티저 타입(stuNo가 int)에 정의된 compareTo()사용
	}

	@Override
	public String toString() {
		return "Student [studentNo=" + studentNo + ", name=" + name + ", korScore=" + korScore + ", engScore="
				+ engScore + ", mathScore=" + mathScore + ", total=" + getTotal() + " ]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(engScore, korScore, mathScore, name, studentNo, total);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Student)) {
			return false;
		}
		Student other = (Student) obj;
		return engScore == other.engScore && korScore == other.korScore && mathScore == other.mathScore
				&& Objects.equals(name, other.name) && Objects.equals(studentNo, other.studentNo)
				&& total == other.total;
	}

}
