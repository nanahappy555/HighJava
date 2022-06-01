package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * FileReader을 이용해 파일에서 데이터를 읽어오기
 * @author PC-17
 *
 */
public class T08FileReaderTest {
	public static void main(String[] args) {

		FileReader fr = null;
		FileInputStream fis = null;
		//파일인풋스트림(byte기반)으로 읽어온 결과 글 깨짐
		InputStreamReader isr = null;
		//byte기반을 char기반처럼 작동되게 해주기 위한 보조 스트림
		
		try {
			
//			fr = new FileReader("d:/D_Other/testChar.txt");
			fis = new FileInputStream("d:/D_Other/testChar.txt");
			isr = new InputStreamReader(fis);
			int data = 0;
			
//			while((data = fr.read()) != -1) {
			while((data = isr.read()) != -1) {
				System.out.print((char)data);
			}
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
//				fr.close();
				isr.close(); //보조스트림이 닫히면 기본스트림도 알아서 닫아줌. 그래서 fis.close()를 닫을 필요 없음
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
