package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpServer {
	private DatagramSocket ds; //소켓:데이터주고받는행위
	private DatagramPacket dp; //패킷:데이터를 담음
	private byte[] msg; //패킷 송수신을 위한 바이트배열 선언
	//패킷 안에 데이터는 바이트 배열로 담김
	
	public UdpServer() {
		try {
			//메세지 수신을 위한 포트번호 설정 (안쓸거같은포트번호로 설정..)
			ds = new DatagramSocket(8888);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//시작메서드
	public void start() throws IOException {
		while(true) {
			//데이터를 수신하기 위한 패킷을 생성한다.
			msg = new byte[1]; 
			//1바이트짜리 바이트배열 생성. 상대방이 통신되는지 확인하는 목적으로 작은걸 생성
			dp = new DatagramPacket(msg,msg.length);//메세지,메세지크기
			//메세지를 받으려고 만든 거
			
			System.out.println("패킷 수신 대기중...");
			
			// 패킷을 통해 데이터를 수신(receive)
			ds.receive(dp); 
			//이게 없으면 상대방이 데이터를 보내도 못받음
			//상대가 패킷을 보낼때까지 스레드 멈춤
			//TCP와 비교해서 커넥션하는 과정이 없음.
			
			
			System.out.println("패킷 수신 완료...");
			
			//수신한 패킷으로부터 client의 IP주소와 Port번호를 얻는다.
			//IP로는 호스트까지만 알 수 있음. Port를 알아야 프로세스를(프로그램) 구분할 수 있다
			InetAddress addr = dp.getAddress();
			int port = dp.getPort();
			
			//서버의 현재 시간을 시분초 형태[hh:mmdss]로 반환한다.
			SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
			String time = sdf.format(new Date());
			msg = time.getBytes(); //시간문자열을 바이트배열로 변환
			
			//패킷을 생성해서 client에게 전송(send)한다.
			dp = new DatagramPacket(msg, msg.length, addr,port);
			ds.send(dp); //전송하기
		}
	}
	
	public static void main(String[] args) throws IOException {
		new UdpServer().start();
	}
}
