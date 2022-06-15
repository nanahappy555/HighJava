package tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread {
	private Scanner scanner;
	private String name; //사용자 정보 이름
	private DataOutputStream dos;
	
	public Sender(Socket socket) {
		//사용자 정보 : IP주소와 포트번호
		name = "[" + socket.getInetAddress() + " : "
				+ socket.getLocalPort() + "]";
		scanner = new Scanner(System.in);
		
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			//왜 DataOutputStream? writeUTF쓰려고.
			//문자기반 써도 됨. 근데 이게 더 심플하고 좋음...
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		//null체크하고 null이 아닌 동안 무한루프
		while(dos != null) {
			try {
				dos.writeUTF(name + " >>> " + scanner.nextLine());
				//엔터칠때까지 block. 엔터치면 writeUTF로 날아감(send)
				//또 엔터칠때까지 block. 엔터치면 ... 반복
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
