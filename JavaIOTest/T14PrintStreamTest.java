package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * 프린터기능 보조 스트림
 * @author PC-17
 *
 */
public class T14PrintStreamTest {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		//기본스트림으로 File스트림 이용해서 파일로 출력...
		FileOutputStream fos = new FileOutputStream("d:/D_Other/print.txt");
		
		/*
		 * PrintStream은 모든 자료형을 출력할 수 있는 기능을 제공하는
		 * OutputStream의 서브 클래스이다.
		 */
		PrintStream out = new PrintStream(fos);
		out.print("안녕하세요.PrintStream입니다.\n");
		out.println("안녕하세요.PrintStream입니다.2");
		out.println("안녕하세요.PrintStream입니다.3");
		out.println(out); //객체출력됨
		out.println(3.14);
		//System.out.println();
		
		out.close();
		
		/*
		 * PrintWriter가 PrintStream보다 다양한 언어의 문자를 출력하는데 적합하다.
		 */
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos, "UTF-8"));
		pw.print("안녕하세요.PrintWriter입니다.\n");
		pw.println("안녕하세요.PrintWriter입니다.2");
		pw.println("안녕하세요.PrintWriter입니다.3");
		pw.println(pw);
		
		pw.close(); //스트림닫기
	}
}
