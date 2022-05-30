package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 파일읽기 예제
 * @author PC-17
 *
 */
public class T05FileStreamTest {
	public static void main(String[] args) {
		FileInputStream fis = null; //읽는용 누가읽어? 프로그램기준. 프로그램이 파일의 데이터를 읽는다.
		
		try {
			fis = new FileInputStream("d:/D_Other/test2.txt"); 
			
			int data=0;
			
			while((data = fis.read()) != -1) {
				//읽어온 데이터 출력하기
				System.out.print((char)data); 
				//문자기반이 아니라서 결과가 깨지지만 아무튼 읽어오긴 했음. 글자가 깨지는 것 때문에 문자기반스트림이 필요해짐
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				fis.close(); //finally에 쓰면 무조건 종료해주니까 안전하다.
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
