package basic;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class URLConnectionTest {
	public static void main(String[] args) throws IOException {
		//URLConnection => 어플리케이션과 URL간의 통신 연결을 위한 추상클래스
		//
		
		// 특정 서버(예:naver서버)의 정보와 파일내용을 출력하는 예제
		URL url = new URL("https://www.naver.com/index.html");
		
		// Header정보 가져오기
		
		// URLConnection객체 구하기
		//해당 url의 connection을 맺어서 urlConnection중에 하위인 http객체를 반환해줌
		URLConnection urlConn = url.openConnection();
		
		System.out.println("Content-Type : " + urlConn.getContentType());
		System.out.println("Encoding : " + urlConn.getContentEncoding());
		System.out.println("Content : " + urlConn.getContent()); //Stream으로 꺼내옴
		//sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@7722c3c3
		System.out.println();
		
		// 전체 Header정보 출력하기 http header
		Map<String, List<String>> headerMap = urlConn.getHeaderFields();
		  
		// Header의 key값 구하기
		Iterator<String> iterator = headerMap.keySet().iterator();
		while(iterator.hasNext()) {
			String key = iterator.next();
			System.out.println(key + " : " + headerMap.get(key));
		}
		System.out.println("-----------------------------------------");
		
		//해당 호스트의 페이지 내용 가져오기
		
		//파일을 읽어오기 위한 *스트림* 생성하기
		InputStream is = urlConn.getInputStream();
		
		InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		
		BufferedReader br = new BufferedReader(isr); //문자열기반
		
		//내용 출력
		//URL을 이용하여 URL컬렉션을 통해 네이버의 Stream을 가져옴
		String str = "";
		while((str = br.readLine()) != null) {
			System.out.println(str);
		}
		
		//스트림닫기
		br.close();
		
		
	}
}
