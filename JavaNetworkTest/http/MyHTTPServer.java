package http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 간단한 웹서버 예제
 * @author PC-17
 *
 */
public class MyHTTPServer {
	private final int port = 80;
	private final String encoding = "UTF-8";
	
	//시작 메서드
	public void start() {
		System.out.println("HTTP 서버가 시작되었습니다.");
		
//		ServerSocket server = null;
		//try(자동으로 자원반납을 해줄 리소스){} finally쓰는거랑 똑같은 기능. 트라이-리소스?
		try(ServerSocket server = new ServerSocket(this.port)){
//			server = new ServerSocket(this.port); + finally에서 server.close();
			
			while(true) { //멀티챗과 비슷함. 여러 사용자가 요청하니까 그때마다 소켓을 만들고 핸들러 스타트 시키고 다시 루프
				//멀티챗처럼 계속 연결되어 있을 필요가 없음. 스레드 스타트시켜서 response 던져주면 끝임
				
				try {
					Socket socket = server.accept(); //메인스레드에서 기다림. 클라이언트가 소켓을 만들면 block이 풀림
					
					HttpHandler handler = new HttpHandler(socket);
					
					new Thread(handler).start(); //요청 처리 시작
					
				}catch(IOException e) {
					e.printStackTrace();
				}
				
			}
			
		}catch(IOException e) {
			
		}
		
	}
	
	/**
	 * HTTP 요청 처리를 위한 Runnable 클래스
	 */
	private class HttpHandler implements Runnable {
		private final Socket socket;
		
		public HttpHandler(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			OutputStream out = null;
			BufferedReader br = null;
			
			try {
				out = new BufferedOutputStream(socket.getOutputStream());
				br = new BufferedReader(
						new InputStreamReader(
								socket.getInputStream())); //바이트기반을 문자 기반으로 바꿔주는 인풋스트림리더
				
				//요청헤더 정보 파싱하기
				StringBuilder sb = new StringBuilder(); //누적시켜서?빠름?ㅠㅠ
				while(true) {
					String str = br.readLine();
					
					if(str.equals("")) break; //empty line 체크. emptyline이면 빠져나옴
					
					sb.append(str + "\n"); //누적
				}
				
				//헤더 정보 (Request Line + Header)
				String reqHeaderStr = sb.toString(); 
				
				System.out.println("요청 헤더:\n" + reqHeaderStr);
				System.out.println("-----------------------------");
				
				
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				try {
					socket.close();//소켓 닫기(연결 끊기)
				} catch (IOException e) {
					e.printStackTrace();
				} 
				
			}
		}
		
	}
	public static void main(String[] args) {
		new MyHTTPServer().start();
	}
}
