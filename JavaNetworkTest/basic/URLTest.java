package basic;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class URLTest {
	public static void main(String[] args) throws IOException, URISyntaxException {
		//URL 클래스 => 인터넷에 존재하는 서버들의 자원에 접근할 수 있는 주소를 관리하는 클래스
		//URL 소스(주소 및 정보)를 알아내기 위해 필요
		//Uniform Resource Locator 통합 리소스 위치
		
						//http프로토콜 사용, 호스트이름, 포트번호(디폴트), 가져올 리소스
		URL url = new URL("http", "ddit.or.kr", 80, "/main/index.html?ttt=123#kkk");
		System.out.println("전체 URL주소 : " + url.toString());
		
		System.out.println("protocol : " + url.getProtocol()); //http
		System.out.println("host : " + url.getHost());
		System.out.println("query : " + url.getQuery()); //ttt=123
		System.out.println("file : " + url.getFile()); //쿼리정보 포함.(?ttt=123)
		System.out.println("path : " + url.getPath()); //쿼리정보 미포함
		System.out.println("port : " + url.getPort()); //(#)kkk (id)
		System.out.println("ref : " + url.getRef());
		
		System.out.println(url.toExternalForm());
		System.out.println(url.toString());
		System.out.println(url.toURI().toString()); //throws URISyntaxException
		//Uniform Resource Identifier식별자 특정 리소스를 구분용도.중복x
		//URL은 URI에 포함되니까 URI로 바꿀수있다.
		
	}
}
