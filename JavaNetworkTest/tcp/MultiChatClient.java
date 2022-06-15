package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MultiChatClient {
	
	//시작메서드
	public void clientStart() {
		
		Socket socket = null;
		
		try {
			socket = new Socket("192.168.141.3", 7777);
			
			System.out.println("서버에 연결되었습니다.");
			
			//송신용 스레드 생성
			ClientSender sender = new ClientSender(socket);
			
			//수신용 스레드 생성
			ClientReceiver receiver = new ClientReceiver(socket);
			
			sender.start();
			receiver.start();
			//메인스레드 죽어버림

		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	//메세지를 전송하는 스레드 클래스
	class ClientSender extends Thread {
		private DataOutputStream dos;
		private Scanner scanner;
		
		public ClientSender(Socket socket) {
			scanner = new Scanner(System.in);
			
			try {
				dos = new DataOutputStream(socket.getOutputStream());
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			try {
				//null check.
				if(dos != null) {
					//시작하자 마자 자신의 대화명을 서버로 전송한다. 1번만
					System.out.print("대화명 >> ");
					dos.writeUTF(scanner.nextLine());
				}
				
				while(dos != null) {
					//키보드로 입력받은 메세지를 서버로 전송. 무한루프
					dos.writeUTF(scanner.nextLine());
				}
				
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	//메세지를 수신하는 스레드 클래스
	class ClientReceiver extends Thread {
		private DataInputStream dis;
		
		public ClientReceiver(Socket socket) {
			try {
				dis = new DataInputStream(socket.getInputStream());
				
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			while(dis != null) {
				try {
					//서버로부터 수신한 메세지를 콘솔에 출력
					System.out.println(dis.readUTF());
					
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new MultiChatClient().clientStart();
	}
	
}
