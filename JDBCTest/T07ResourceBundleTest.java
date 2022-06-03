package kr.or.ddit.basic;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class T07ResourceBundleTest {
/*
	ResourceBundle 객체 => 확장자가 properties인 파일 정보를 읽어와 key값과 value값을 분리한 정보를 갖는 객체
	=> 읽어올 파일은 'key값=value값' 형태로 되어 있어야 한다.
 */
	public static void main(String[] args) {
		
		//ResourceBundle 객체를 이용하여 파일 읽어오기
		
		//ResourceBundle 객체 생성하기
		//=> 파일을 지정할 때는 '파일명'명만 지정하고 확장자는 지정하지 않는다.(항상 properties라서)
		System.out.println(Locale.getDefault()); //디폴트국가설정
//		ResourceBundle bundle = ResourceBundle.getBundle("db"); //디폴트국가설정에 맞춰서 프로퍼티파일을 뿌려줌
		ResourceBundle bundle = ResourceBundle.getBundle("db", Locale.JAPANESE); //국가설정을 변경
		//찾는 위치:bin폴더
		//src폴더에 db.properties를 넣으면 소스파일이 아니라도 자동으로 컴파일해줘서 bin에도 생김 
		//=>컴파일 되어야 할 소스는 소스폴더 안에 있어야한다
		//res폴더에 자바소스 외 파일을 넣어두면 관리하기가 편하다
		
		// key값들만 읽어오기
		Enumeration<String> keys = bundle.getKeys();
		
		while(keys.hasMoreElements()) {
			String key = keys.nextElement();
			
			// key값을 이용하여 value값을 읽어오는 방법
			//  => bundle객체변수.getString(key값);
			String value = bundle.getString(key);
			
			System.out.println(key + " => " + value);
		}
		System.out.println("출력 끝...");
		
	}
}
