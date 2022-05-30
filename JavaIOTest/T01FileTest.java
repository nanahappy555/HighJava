package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

public class T01FileTest {
	public static void main(String[] args) throws IOException {
		
		//File 객체 만들기 연습
		
		//1. new File(String 파일 또는 경로명)
		//	=> 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 구분문자는
		//		'\'를 사용하거나 '/'를 사용할 수 있다.
		File file = new File("d:/D_Other/test.txt"); //test.txt의 파일을 핸들링하는 목적의 객체
		System.out.println("파일명 : " + file.getName());
		System.out.println("파일 여부 : " + file.isFile()); //파일이 있으면 true,없으면 false
		System.out.println("디렉토리(폴더) 여부 : " + file.isDirectory());
		System.out.println("---------------------------------------");
		
		File file2 = new File("d:/D_Other");
		System.out.print(file2.getName() + "은 ");
		if(file2.isFile()) {
			System.out.println("파일");
		}else if(file2.isDirectory()) {
			System.out.println("디렉토리(폴더)");
		}
		System.out.println("---------------------------------------");
		
		//2. new File(File parent, String child)
		//	=> 'parent'디렉토리 안에 있는 'child'파일 또는 디렉토리를 갖는다.
		File file3 = new File(file2, "test.txt");
		System.out.println(file3.getName() + "의 용량 크기 : " 
				+ file3.length() + "(bytes)");
		
		//3. new File(String parent, String child)
		File file4 = new File(".\\D_Other\\test\\..", "test.txt"); //상대경로 (../ == .\\ 역슬래시는 두개쓰는 이유.. 하나는 이스케이프문자용. 슬래시가 더 운영체제 호환이 좋다
		//현재경로 : 현재 프로그램이 작성되고 있는 Project 폴더(현재 프로그램의 위치에 따라 그때그때 다르다)
		//상대경로라는 것은 '현재경로'라는 '기준'이 필요하다
		System.out.println("절대 경로 : " + file4.getAbsolutePath());
		System.out.println("경로 : " + file4.getPath()); //생성자에 넣어준 경로 그대로 출력
		System.out.println("표준 경로 : " + file4.getCanonicalPath());
		
		
		/*
		 * 디렉토리(폴더) 만들기
		 * 1. mkdir() => File객체의 경로 중 마지막 위치의 디렉토리를 만든다.
		 * 				 중간의 경로가 모두 미리 만들어져 있어야 한다.
		 * 
		 * 2. mkdirs() => 중간의 경로가 없으면 중간의 경로도 새롭게 만든 후 마지막 위치의 디렉토리를 만들어 준다.
		 * 	
		 * => 위 두 메서드 모두 만들기를 성공하면 true, 실패하면 false를 반환함.
		 * (이미 폴더가 있거나 폴더생성 권한이 없을수도 있다.)
		 */
		
		File file5 = new File("d:/D_Other/연습용");
		if(file5.mkdir()) {
			System.out.println(file5.getName() + "만들기 성공!");
		}else {
			System.out.println(file5.getName() + "만들기 실패...");
		}
		System.out.println();
		
		File file6 = new File("d:/D_Other/test/java/src");
		if(file6.mkdirs()) {
			System.out.println(file6.getName() + "만들기 성공!");
		}else {
			System.out.println(file6.getName() + "만들기 실패...");
		}
		
		
		
	}
}
