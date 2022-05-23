package kr.or.ddit.basic;
/*
 * 싱글 스레드 프로그램
 */
public class T01ThreadTest {
	public static void main(String[] args) {
		for(int i=1; i<=200; i++) {
			System.out.print("*");
		}
		for(int i=1; i<=200; i++) {
			System.out.print("$");
		}
	}
}
