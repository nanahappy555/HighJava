package tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TcpFileClient {
/*
	클라이언트는 서버에 접속하여 서버가 보내주는 파일을 D:C_Lib폴더에 저장한다.
 */
	private Socket socket;
	private FileOutputStream fos;
	
	public void clientStart() {
		try {
			socket = new Socket("192.168.141.3", 7777);
			System.out.println("파일 다운로드 시작");
			
			fos = new FileOutputStream("D:/C_Lib/aaa.jpg");
			
			BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			
			int data = 0;
			while((data = bis.read()) != -1) {
				bos.write(data);
			}
			
			bis.close();
			bos.close();
			
			System.out.println("파일 다운로드 완료");
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(fos != null) {
				try {fos.close();}catch(IOException e) {}
			}
			if(socket != null) {
				try {socket.close();}catch(IOException e) {}
			}
		}
	}
	
	public static void main(String[] args) {
		new TcpFileClient().clientStart();
	}
}
