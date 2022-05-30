package kr.or.ddit.reflection;

import kr.or.ddit.basic.PrintAnnotation;

public class SampleVO implements Comparable<SampleVO>{

	public String id;
	protected String name;
	private int age;

	//생성자1
	public SampleVO() throws RuntimeException{ //T03에서 예외타입 출력되는걸 보려고 던진 exception
	}
	//생성자2
	public SampleVO(String id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	
	@PrintAnnotation
	public String getId() throws RuntimeException {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "SampleVO [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

	@Override
	public int compareTo(SampleVO o) {
		return name.compareTo(o.name);
	}


	
}
