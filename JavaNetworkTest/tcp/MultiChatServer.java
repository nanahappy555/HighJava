package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MultiChatServer {
		//key대화명, value클라이언트의 Socket을 저장하기 위한 Map변수 선언
		Map<String, Socket> clients;
		
		public MultiChatServer() {
			//동기화 처리가 가능하도록 Map객체 생성하기
			clients = Collections.synchronizedMap(new HashMap<String, Socket>());
		}
		
		//서버 시작 메서드
		public void serverStart() {
			
			ServerSocket server = null;
			Socket socket = null;
			
			try {
				System.out.println("서버가 시작되었습니다.");
				
				server = new ServerSocket(7777);
				
				while(true) { //무한루프
					//클라이언트의 접속을 대기한다.
					socket = server.accept();
					
					System.out.println("[" + socket.getInetAddress() 
					+ " : " + socket.getPort()
					+ "에서 접속하였습니다.");
					
					//메세지 전송 처리를 하는 스레드 객체 생성 및 실행
					ServerReceiver receiver = new ServerReceiver(socket);
					receiver.start();
					
				}
				
				
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				//서버소켓 닫기
				if(server != null) {
					try {server.close();}catch(IOException e) {}
				}
			}
		}
		
		///////////////////////////////////////////////////////////////////
		
		/**
		 * 대화방 즉, Map에 저장된 전체 유저에게 안내메세지를 전송하는 메서드
		 * @param msg 안내 메세지
		 */
		public void sendMessage(String msg) {
			Iterator<String> it = clients.keySet().iterator();
			while(it.hasNext()){
				try {
					String name = it.next(); //대화명(key)구하기
					
					//대화명에 해당하는 Socket객체에서 OutputStream 객체 구하기
					DataOutputStream dos = new DataOutputStream(
							clients.get(name).getOutputStream()); //clients.get(name) =>소켓
					dos.writeUTF(msg); //메세지 보내기
					
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}

		/**
		 * 대화방 즉, Map에 저장된 전체 유저에게 대화 메세지를 전송하는 메서드
		 * @param msg
		 * @param from
		 */
		public void sendMessage(String msg, String from) {
			sendMessage("[" + from + "]" + msg); //이름 : 메세지 형태로 출력되도록
		}
		
		
		/**
		 * 가장중요.. 접속한 사람 명수만큼 생김. 소켓 10개 ServerReceiver10개
		 * 서버에서 클라이언트로 메세지를 전송할 Thread클래스를 Inner클래스로 정의한다.
		 * (Inner클래스에서는 부모클래스의 멤버들을 직접 사용할 수 있다.
		 */
		//내부클래스로 만드는 경우 장점??. (Map에서 내부 인터페이스 썼음) 
		//1. 부모(외부) 클래스와 내부 클래스가 서로의 멤버에 접근하기 쉽다.
		//2. 외부에는 불필요한 클래스를 은닉함으로써 코드의 복잡성을 줄일 수 있다.(캡슐화)
		class ServerReceiver extends Thread {
			private Socket socket;
			private DataInputStream dis;
			private String name;
			
			public ServerReceiver(Socket socket) {
				this.socket = socket;
				
				try {
					dis = new DataInputStream(socket.getInputStream());
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}
			
			@Override
			public void run() {
				try {
					//서버에서는 클라이언트가 보내는 최초의 메세지
					//즉, 대화명을 수신해야 한다.
					//클라이언트가 서버에 소켓????
					name = dis.readUTF();
					
					//대화명을 받아서 다른 **모든 클라이언트**에 대화방 참여 메세지를 보낸다
					sendMessage("#" + name + "님이 입장했습니다.");
					
					//대화명과 소켓정보를 Map에 저장
					clients.put(name, socket);
					
					System.out.println("현재 서버 접속자 수는" 
							+ clients.size() + "명 입니다." );
					
					//이후의 메세지처리는 반복문으로 처리한다.
					//한 클라이언트가 보낸 메세지를 모든 클라이언트에게 보내준다.
					while(dis != null){
						sendMessage(dis.readUTF(), name);
					}
				}catch(IOException ex) { //네트워크에 문제 생겼을 때... 바로 finally실행
//					ex.printStackTrace();
				}finally {
					//finally영역이 실행된다는 의미는 클라이언트 접속이 종료됐다는 의미
					sendMessage(name + "님이 나가셨습니다");
					
					//Map에서 해당 대화명을 삭제한다. =관리대상에서 
					clients.remove(name);
					
					System.out.println("[" + socket.getInetAddress()
							+ " : " + socket.getPort()
							+ "]에서 접속을 종료했습니다.");
					System.out.println("현재 서버 접속자 수는" 
							+ clients.size() + "명 입니다." );
				}
			}
		}
		
		public static void main(String[] args) {
			new MultiChatServer().serverStart();
		}
}
