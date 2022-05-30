package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T03ByteArrayIOTest {
	public static void main(String[] args) throws IOException {
		
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		/*1. 직접 복사하기
		outSrc = new byte[inSrc.length];
		
		for(int i = 0; i<inSrc.length; i++) {
			outSrc[i] = inSrc[i];
		}
		*/
		
		/* 2. util기능 arraycopy를 이용한 배열 복사하기
		outSrc = new byte[inSrc.length];
		System.arraycopy(inSrc, 0, outSrc, 0, inSrc.length);
		*/
		
		/* 3. Stream클래스의 read() write() 사용해서 복사하기*/
		//스트림 선언 및 객체 생성
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc); //생성자 파라미터
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); //저장할 객체생성
		
		int data; //읽어온 자료를 저장할 변수
		
		//read()메서드 => byte단위로 자료를 읽어와 int형으로 반환한다.
		//				더 이상 읽어올 자료가 없으면 -1 반환되고 while문 종료
		while((data = bais.read()) != -1) { //InputStream에는 read()가 있다... 1byte씩 읽어옴. byte기반이니까~
			baos.write(data); //출력하기. 아웃풋스트림에 저장되고 있음
		}
		
		//출력된 스트림 값을 배열로 반환하기
		outSrc = baos.toByteArray(); //아웃풋스트림에 저장된 배열을 리턴한 것을 outSrc에 저장
		
		
		//복사 결과 확인. Arrays.toSrting
		System.out.println("inSrc => " + Arrays.toString(inSrc));
		System.out.println("inSrc => " + Arrays.toString(outSrc));
		
		//사용한 스트림 객체 닫아주기
		//try-catch쓰면 너무 길어져서 가독성 떨어져서 그냥 throws로 던졌음
		bais.close();
		baos.close();
	}
}
