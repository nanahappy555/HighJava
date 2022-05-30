package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class T02FileTest {
	public static void main(String[] args) {
		
		File f1 = new File("d:/D_Other/sample.txt");
		File f2 = new File("d:/D_Other/test.txt");
		
		if(f1.exists()) { //exist존재하다
			System.out.println(f1.getAbsolutePath() + "은 존재합니다.");
		}else {
			System.out.println(f1.getAbsolutePath() + "은 없는 파일입니다.");
			try {
				if(f1.createNewFile()) {
					System.out.println(f1.getAbsolutePath() + "파일을 만들었습니다.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(f2.exists()) {
			System.out.println(f2.getAbsolutePath() + "은 존재합니다.");
		}else {
			System.out.println(f2.getAbsolutePath() + "은 없는 파일입니다.");
		}
		System.out.println("-----------------------------------------");
		
		File f3 = new File("d:/D_Other");
		File[] files = f3.listFiles(); //디렉토리에 포함된 파일들을 File객체 배열로 리턴해줌
		for(File file : files) {
			System.out.print(file.getName() + " => ");
			if(file.isFile()) {
				System.out.println("파일");
			}else if(file.isDirectory()) {
				System.out.println("디렉토리(폴더)");
			}
		}
		System.out.println("=========================================");
		
		String[] strFiles = f3.list(); //
		for(String str : strFiles) {
			System.out.println(str);
		}
		System.out.println("=========================================");
		System.out.println();
		
		//출력할 디렉토리 정보를 갖는 File객체 생성
		File f4 = new File("D:/D_Other");
		
		displayFileList(f4);
		
	}
	
	/**
	 * 지정된 디렉토리(폴더)에 포함된 파일과 디렉토리 목록을 보여주는 메서드
	 * @param dir 디렉토리 정보를 보고자하는 디렉토리
	 */
	private static void displayFileList(File dir) {
		System.out.println("[" + dir.getAbsolutePath() + "] 디렉토리 내용");
		
		//디렉토리의 모든 파일목록을 가져온다.
		File[] files = dir.listFiles();
		
		//하위 디렉토리 정보를 저장할 ArrayList 생성(File배열의 인덱스값 저장)
		List<Integer> subDirList = new ArrayList<Integer>();
		
		//날짜를 출력하기 위한 형식 설정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd a hh:mm");
		
		for(int i=0; i<files.length; i++) {
			String attr = ""; //파일의 속성(읽기, 쓰기, 히든, 디렉토리 구분)
			String size = ""; //파일 용량
			
			if(files[i].isDirectory()) {
				attr = "<DIR>";
				subDirList.add(i); //디렉토리가 맞으면 index를 List에 추가하기. length는 폴더갯수와 같다
			}else {
				size = files[i].length() + "";
				attr = files[i].canRead()? "R" : " "; //읽기 권한 여부 확인
				attr += files[i].canWrite()? "W" : " ";// +=누적 쓰기 권한 여부 확인
				attr += files[i].isHidden()? "H" : " "; //숨김파일 여부 확인
			}
			System.out.printf("%s %5s %12s %s\n", 
					sdf.format(new Date(files[i].lastModified())),
					attr, size, files[i].getName());
			//printf 날짜(글자전부), attr(5자 <DIR>), size(12자), 파일이름(글자전부)
		}
		
		int dirCount = subDirList.size(); //폴더 안의 하위폴더 개수 구하기
		int fileCount = files.length - dirCount; //폴더안의 파일개수구하기
		
		System.out.println(fileCount + "개의 파일, "
				+ dirCount + "개의 디렉토리(폴더)");
		
		System.out.println();
		
		for(Integer idx : subDirList) {
			displayFileList(files[idx]); //재귀호출 ; 자기가 자신을 계속해서 호출하므로, 끝없이 반복
		}
		
	}
}
