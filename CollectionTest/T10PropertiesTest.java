package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class T10PropertiesTest {
/*
 * Properties는 Map보다 축소된 기능의 객체라고 할 수 있다.
 * Map은 모든 형태의 객체 데이터를 key와 value값으로 사용할 수 있지만
 * Properties는 key와 value값으로 *String만* 사용할 수 있다.
 * 
 * Map은 put(), get() 메서드를 이용하여 데이터를 입출력 하지만,
 * Properties는 setProperty() getProperty()메서드를 이용해서
 * 데이터를 입출력 한다.
 * 
 * 유저 아이디 비밀번호 같은것을 properties형태로 편리하게 관리가능
 */
	
	public static void main(String[] args) throws IOException, IOException {
		Properties prop = new Properties();
		
		//저장 set
		prop.setProperty("name", "홍길동");
		prop.setProperty("tel", "010-1234-5678");
		prop.setProperty("addr", "대전");
		
		//반환 get
		String name = prop.getProperty("name");
		String tel = prop.getProperty("tel");
		
		System.out.println("이름 : " + name);
		System.out.println("전화 : " + tel);
		System.out.println("주소 : " + prop.getProperty("addr"));
		
		//내용을 해당 경로에 파일로 저장하기
		prop.store(
				new FileOutputStream(
						"src/kr/or/ddit/basic/test.properties"),
						"코멘트입니다.");
		//iso???
		//properties에서 허용되는 인코딩이 제한적이라 유니코드로 저장됨
		// #은 주석
		
	}
}
