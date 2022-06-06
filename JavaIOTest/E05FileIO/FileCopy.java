import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
	public static void main(String[] args) throws FileNotFoundException {
		// 1. IO Stream에 경로 직접 입력
//		FileInputStream fis = new FileInputStream("c:/D_Other/Tulips.jpg");
//		FileOutputStream fos = new FileOutputStream("c:/D_Other/Tulips_복사본.jpg");
//		
//		int data = 0;
//		
//		try {
//			while((data = fis.read()) != -1) {
//				fos.write(data);
//			}
//			fis.close();
//			fos.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		// 2. 경로를 변수로 선언 + 작업 소요 시간 확인
		String oriFilePath = "c:/D_Other/Tulips.jpg";
		String copyFilePath = "c:/D_Other/Tulips_복사본.jpg";

		File oriFile = new File(oriFilePath);
		File copyFile = new File(copyFilePath);

		FileInputStream fis = new FileInputStream(oriFile);
		FileOutputStream fos = new FileOutputStream(copyFile);

		int fileSize = 0;
		// fileSize가 -1이면 파일을 다 읽은 것

		try {
			// 작업시작시간 millisecond
			System.out.println("일반 복사 시작");
			long startTime = System.currentTimeMillis();
			
			while ((fileSize = fis.read()) != -1) {
				fos.write(fileSize);
			}
			// 작업종료시간
			long endTime = System.currentTimeMillis();
			
			// 작업시간 계산
			long runTime = endTime - startTime;
			
			System.out.println("일반 복사 완료");
			System.out.println("소요 시간 : " + runTime/1000 + "초"); //24초 걸렸음

			// 자원반납
			fis.close();
			fos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//3. 버퍼 이용 + 작업 소요 시간 확인
		String oriFile2 = "c:/D_Other/Tulips.jpg";
		String copyFile2 = "c:/D_Other/Tulips_버퍼복사본.jpg";
		
		try {
			FileInputStream fis2 = new FileInputStream(oriFile2);
//			FileOutputStream fos2 = new FileOutputStream(copyFile);
//			BufferedOutputStream bos = new BufferedOutputStream(fos2, 10240);
			BufferedOutputStream bos = new BufferedOutputStream(
							new FileOutputStream(copyFile2),10240);
							//10KB짜리 버퍼
			
			int fileSize2 = 0;
			
			// 작업시작시간 millisecond
			System.out.println("버퍼 복사 시작");
			long startTime2 = System.currentTimeMillis();
			
			while((fileSize2 = fis2.read()) != -1 ) {
				bos.write(fileSize2); //데이터의 크기만큼 write(int size);
			}
			
			// 작업종료시간
			long endTime2 = System.currentTimeMillis();
			
			// 작업시간 계산
			long runTime2 = endTime2 - startTime2;
			
			System.out.println("버퍼 복사 완료");
			System.out.println("소요 시간 : " + runTime2/1000 + "초"); //24초 걸렸음
			
			bos.flush(); //작업 종료 전 버퍼에 남은 데이터 모두 출력
			bos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
