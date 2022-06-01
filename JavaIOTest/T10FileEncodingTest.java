package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class T10FileEncodingTest {
	/*
	 * OutputStreamWriter => OutputStream(바이트기반의 출력용 객체)를 Writer(문자기반스트림 객체)로 변환하는
	 * 객체 =>이 객체도 출력할 때 '인코딩방식'을 지정해서 출력할 수 있다.
	 */
	public static void main(String[] args) throws IOException {
		/*
		 * 키보드로 입력한 내용을 파일로 저장하는데 
		 * out_utf8.txt 파일은 'utf-8'인코딩 방식으로 
		 * out_ansi.txt 파일은 'ms949'인코딩 방식으로 저장한다.
		 */
		InputStreamReader isr = new InputStreamReader(System.in);

		// 파일 출력용
		FileOutputStream fos1 = new FileOutputStream("d:/D_Other/out_utf8.txt");
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/out_ansi.txt");

		OutputStreamWriter osw1 = new OutputStreamWriter(fos1, "utf-8");
		OutputStreamWriter osw2 = new OutputStreamWriter(fos2, "ms949");

		System.out.println("아무거나 입력하세요.");

		int data = 0;
		while ((data = isr.read()) != -1) { // 파일이 동시에 2개가 만들어짐
			osw1.write(data);
			osw2.write(data);
		}

		System.out.println("작업완료...");

		isr.close();
		osw1.close();
		osw2.close();

		///////////////////////////////////////////
		
		//파일 읽어오기
		isr = new InputStreamReader(new FileInputStream("d:/D_Other/out_ansi.txt"), "euc-kr");

		data = 0;
		while ((data = isr.read()) != -1) {
			System.out.print((char) data);
		}

		isr.close();
	}

}
