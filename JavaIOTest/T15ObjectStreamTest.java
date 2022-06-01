package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class T15ObjectStreamTest {
	public static void main(String[] args) {

		// Member 인스턴스 생성
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("일지매", 30, "경기");
		Member mem3 = new Member("이몽룡", 40, "부산");
		Member mem4 = new Member("성춘향", 50, "서울");

		ObjectOutputStream oos = null; // 오브젝터 데이터 출력을 위한 코드
		

		try {
			// 객체를 파일에 저장하기

			// 출력용 스트림 객체 생성
			oos = new ObjectOutputStream(
					new BufferedOutputStream(   //버퍼 끼워넣기 속도향상
					new FileOutputStream("d:/D_Other/memObj.bin"))); // Object를 File에 저장.FileOutputStream
			//2줄로 표현한 것. 1줄로 표현할 때 장점: 한번만 쓰고 싶을 때 따로 변수를 만들 필요 없으니까...
			//FileOutputStream fos = new FileOutputStream("d:/D_Other/memObj.bin");
			//ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			// 쓰기 작업 시작...
			oos.writeObject(mem1); // 직렬화 byte단위로 핸들링하기 위한 과정
			oos.writeObject(mem2); // 직렬화
			oos.writeObject(mem3); // 직렬화
			oos.writeObject(mem4); // 직렬화

			System.out.println("쓰기 작업 완료");//이렇게 직렬화한 데이터는 class정보도 같이 가지고 있음

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		////////////////////////////////////////////////////
		ObjectInputStream ois = null; //보조스트림 보조스트림의 매개변수에는 기본스트림을...(매개변수=>얘가 무엇을 보조할건지=>기본스트림을 보조)

		try {
			ois = new ObjectInputStream(
					new BufferedInputStream(   //버퍼 끼워넣기 속도향상
					new FileInputStream("d:/D_Other/memObj.bin")));

			Object obj = null;
			//readObject() 호출시 역직렬화 발생함. 역직렬화: byte단위로 읽어들여 객체화하는 것.
			while ((obj = ois.readObject()) != null) { //이 조건문이 return되는 것은 객체화에 성공했다는 뜻
				//데이터 읽다가 마지막에 다다르면 EOF 예외가 발생함!
				
				//읽어온 데이터를 원래의 객체형으로 변환 후 사용한다.
				Member mem = (Member) obj;
				System.out.println("이름 : " + mem.getName());
				System.out.println("나이 : " + mem.getAge());
				System.out.println("주소 : " + mem.getAddr());
				System.out.println("--------------------");
				
			}

		} catch (ClassNotFoundException ex) { //역직렬화 할 때는 class정보도 필요하다.
			//Unhandled exception type ClassNotFoundException class정보가 없으면 클래스 낫 파운드 익셉션이 발생할 수 있다는 뜻
			ex.printStackTrace();
		} catch (IOException ex) {
//			ex.printStackTrace();
			System.out.println("출력 완료..."); //EOFException대신 띄워줄 문구
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

/**
 * VO객체
 * 
 * @author PC-17
 *
 */
class Member implements Serializable {
	// 자바는 Serializable 인터페이스를 구현한 클래스만 직렬화 할 수 있도록
	// 제한하고 있음
	// Serializable ; 직렬화 일렬로 늘어뜨리는것
	//implements Serializable(직렬화 가능하다는 표시용 태그인터페이스(내용없고껍데기만있음))
	//구현하지 않으면 NotSerializableException발생!

	/*
	 * transient => 직렬화가 되지 않을 멤버변수에 지정한다. 
	 * 				직렬화가 되지 않는 멤버변수는 기본값으로 저장된다.
	 * 				(참조형 변수: null, 숫자형 변수:0)
	 * 				*static 필드도 직렬화가 되지 않는다. static은 객체정보가 아니니까 당연함!
	 * transient;일시적인,유지되지 않는다는 뜻
	 */
	private String name;
	private int age;
//	private transient int age; //직렬화x 민감한 개인정보
	private String addr;

	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "Member [name=" + name + ", age=" + age + ", addr=" + addr + "]";
	}
}
