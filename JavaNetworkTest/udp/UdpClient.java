package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {
	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private byte[] msg; //패킷 수신용 바이트 배열
	
	public UdpClient() {
		try {
			msg= new byte[100];
			//소켓 객체 생성
			//(포트번호 명시하지 않으면 포트번호는 사용 가능한 임의의 포트 번호 할당됨)
			ds=new DatagramSocket();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//시작메서드
	public void start() {
		try {
			InetAddress serverAddr = 
					InetAddress.getByName("192.168.141.3");
			
			dp = new DatagramPacket(msg, 1, serverAddr, 8888);
			ds.send(dp); //패킷전송
			
			dp = new DatagramPacket(msg, msg.length);
			ds.receive(dp); //패킷 수신 대기
			
			System.out.println("서버의 현재 시간 => "
					+ new String(dp.getData()));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		new UdpClient().start();
	}
}
