package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class T11BufferedIOTest {
	public static void main(String[] args) {
		
		BufferedOutputStream bos = null;//입출력 성능향상을 위해 버퍼를 이용하는 보조스트림
		FileOutputStream fos = null; //기본스트림
		
		try {
			fos = new FileOutputStream("d:/D_Other/bufferTest.txt");
			//버퍼의 크기를 지정하지 않으면 기본으로 버퍼의 크기가 8192byte(8KB)로 설정됨
			
			//버퍼의 크기가 5인 스트림 생성
			bos = new BufferedOutputStream(fos,5); //9byte있으면 되는데 5b
			
			for(int i='1'; i<='9'; i++) { 
				bos.write(i); //1~9까지 저장. 9byte만 있으면 됨.
//				fos.write(i); //버퍼를 사용하지 않을 때는 직접 FileOutputStream으로 write.
			}
			
			bos.flush(); //작업을 종료하기 전에 버퍼에 남아있는 데이터를 모두 출력시킨다.
						//(close시 자동으로 호출됨.)
						//(근데 안먹힐때가 있어서 그럴때는 flush를 명시적으로 써주면 됨)
			//버퍼 크기를 5로 정해놨는데 9는 5/4니까 flush안해주면 나머지 1이 찰때까지 출력되지않음
			
			System.out.println("출력 작업 끝...");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
//			try { 
//				bos.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
		
		
		
		//보조스트림만 닫아도 됨
	}
}
