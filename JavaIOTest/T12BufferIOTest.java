package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class T12BufferIOTest {
	public static void main(String[] args) throws IOException {
		
		// 문자 기반의 Buffered 보조스트림 사용하기
		// 이클립스에서 만든 자바 프로그램이 실행되는 기본 위치는
		// 해당 프로젝트 폴더가 기본 위치가 된다
		
		FileReader fr = new FileReader(
				"src/kr/or/ddit/basic/T11BufferedIOTest.java");//상대경로 ./생략
		/*
		int data = 0;
		while((data = fr.read()) != -1) {
			System.out.print((char) data);
		}
		
		fr.close();
		*/
		
		//한줄씩 읽을 수 있도록 해주는 readLine을 이용하기 위해
		//BufferedReader 사용하기
		BufferedReader br = new BufferedReader(fr);
		
		String readLine = "";
		for(int i=1; (readLine = br.readLine()) != null; i++) {
			System.out.printf("%4d : %s\n", i, readLine);
		}
		
		br.close();
	}
}
