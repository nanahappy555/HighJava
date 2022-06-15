package basic;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
//InetAddress : IP번호를 처리할 때 사용

public class InetAddressTest {
	public static void main(String[] args) throws IOException {
	/*
	    InetAddress 클래스 => IP주소를 다루기 위한 클래스
	    
	    getByName()은 www.naver.com 또는 SEM-PC 등과 같은 머신이름이나
	    IP주소를 파라미터를 이용하여 유효한 InetAddress 객체를 제공한다.
	    IP주소 자체를 넣으면 주소 구성 자체의 유효성 체크만 이루어진다.
	*/
		// 네이버 사이트의 IP정보 가져오기
		InetAddress naverIp = InetAddress.getByName("www.naver.com"); //InetAddress객체생성
		System.out.println("Host Name => " + naverIp.getHostName()); //없을수도 있음.없으면 IP주소나옴. 만든 이유:인간편의를 위한거라. 도메인 서버에서 
		System.out.println("Host Address => " + naverIp.getHostAddress()); // 중요
		System.out.println();
		
		// 자기 자신 컴퓨터의 IP주소 가져오기
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("내 컴퓨터의 Host Name => " 
						+ localIp.getHostName());
		System.out.println("내 컴퓨터의 Host Address => " 
				+ localIp.getHostAddress());
		System.out.println();
		
		// IP주소가 여러개인 호스트의 정보 가져오기
		// 서버가 여러개 있음
		InetAddress[] naverIps = InetAddress.getAllByName("www.naver.com");
		for(InetAddress iAddr : naverIps) {
			System.out.println(iAddr.toString());
		}
	}
}
