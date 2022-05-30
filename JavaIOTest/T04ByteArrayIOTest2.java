package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04ByteArrayIOTest2 {
	public static void main(String[] args) throws IOException {
		
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		byte[] buffer = new byte[4];//4byte
		
		//스트림 선언 및 객체 생성
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc); //생성자 파라미터
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); //저장할 객체생성
		
		int data; //읽어온 자료를 저장할 변수. int는 4byte
		int readBytes; //버퍼용으로 따로 만들었음
		
		//read()메서드 => byte단위로 자료를 읽어와 int형으로 반환한다.
		//				더 이상 읽어올 자료가 없으면 -1 반환되고 while문 종료
//		while((data = bais.read(buffer)) != -1) { //read에 4byte짜리 버퍼를 넣어줌
//			System.out.println("butter: " + Arrays.toString(buffer)); //while문 몇번 도는지 확인용
//			baos.write(buffer); //출력하기. 아웃풋스트림에 저장되고 있음
//			//원래 10번(length)돌아야 하는데 3번 돈다.
//			//buffer가 4byte배열이라 한번에 4개씩 저장
////			butter: [0, 1, 2, 3]
////			butter: [4, 5, 6, 7]
////			butter: [8, 9, 6, 7] //이전에 복사한 것 찌꺼기가 남아서 따라옴
//			//buffer를 쓸 때 조심해야하는 이유임
//			
//		}
		
		//찌꺼기를 남기지 않기 위해 write(매개변수3개)짜리를 사용
		while((readBytes = bais.read(buffer)) != -1) { //read에 4byte짜리 버퍼를 넣어줌
			System.out.println("butter: " + Arrays.toString(buffer)); //while문 몇번 도는지 확인용
			baos.write(buffer, 0, readBytes); //출력하기. 아웃풋스트림에 저장되고 있음
			//처음부터 읽은 갯수만 맞게 가져온다
		}
		
		//출력된 스트림 값을 배열로 반환하기
		outSrc = baos.toByteArray(); //아웃풋스트림에 저장된 배열을 리턴한 것을 outSrc에 저장
		
		
		//복사 결과 확인. Arrays.toSrting
		System.out.println("inSrc => " + Arrays.toString(inSrc));
		System.out.println("inSrc => " + Arrays.toString(outSrc));
		
		//사용한 스트림 객체 닫아주기
		bais.close();
		baos.close();
	}
}
