package udp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpFileReceiver {
	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private byte[] buffer;
	
	public UdpFileReceiver(int port) {
		//초기화할 때 원하는 포트로 리시브할 수 있도록
		try {
			ds = new DatagramSocket(port);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	private byte[] receiveData() throws IOException {
		
		buffer = new byte[1000]; //버퍼 초기화
		
		dp = new DatagramPacket(buffer, buffer.length);
		ds.receive(dp);
		
		return dp.getData();
	}
	
	public void start() throws IOException {
		
		long fileSize = 0;
		long totalReadBytes = 0; //
		
		int readBytes = 0; //실제 읽은 바이트
		
		System.out.println("파일 수신 대기중...");
		
		String str = new String(receiveData()).trim(); // 1. "start"
		
		if(str.equals("start")) { //Sender에서 전송을 시작한 경우
			
			//2. 파일명
			str = new String(receiveData()).trim();
			FileOutputStream fos = new FileOutputStream("D:/C_Lib/" + str);
			
			//3.파일크기(bytes)받기
			str = new String(receiveData()).trim();
			fileSize = Long.parseLong(str);
			
			long startTime = System.currentTimeMillis();
			
			while(true){
				byte[] data = receiveData(); //1000byte쏠때까지 멈춤(block상태)
				readBytes = dp.getLength(); //현재 패킷으로부터 몇 바이트를 받았는지. 받은데이터 길이
				fos.write(data, 0, readBytes);
				
				totalReadBytes += readBytes;
				System.out.println("진행 상태 : " + totalReadBytes 
						+ "/" + fileSize + "Byte(s) (" 
						+ (totalReadBytes *100 / fileSize) + "%)");
				
				if(totalReadBytes >= fileSize) { 
					//다 받았으면 무한루프 종료
					break;
				}
			}
			
			long endTime = System.currentTimeMillis();
			long diffTime = endTime - startTime;
			double transferSpeed = fileSize / diffTime;
			
			System.out.println("걸린 시간 : " + diffTime + "(ms)");
			System.out.println("평균 수신속도 : " + transferSpeed + "Bytes/ms");
			System.out.println("수신 종료");
			
			fos.close();
			ds.close();
		}
	
	}
	
	public static void main(String[] args) throws IOException {
		new UdpFileReceiver(7777).start();
	}
	
}
