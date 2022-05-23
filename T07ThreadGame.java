package kr.or.ddit.basic;
import javax.swing.JOptionPane;

/*
	컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.

	컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
	사용자의 가위 바위 보는 showInputDialog()메서드를 이용하여 입력받는다.

	입력시간은 5초로 제한하고 카운트 다운을 진행한다.
	5초안에 입력이 없으면 게임을 진것으로 처리한다.

	5초안에 입력이 완료되면 승패를 출력한다.

	결과예시)
		=== 결 과 ===
		컴퓨터 : 가위
		당  신 : 바위
		결  과 : 당신이 이겼습니다.

*/
public class T07ThreadGame {
	public static boolean inputCheck = false;
	public static String man = ""; // 사용자의 가위바위보가 저장될 변수
	

	public static void main(String[] args) {
		
		// 난수를 이용하여 컴퓨터의 가위 바위 보를 정한다.
		String[] data = {"가위", "바위", "보"};
		int index = (int)(Math.random()*3); // 0~2사이의 난수 만들기
		String com = data[index];
		// 카운트 다운 쓰레드 실행
		GameTimer gt = new GameTimer();
		gt.start();

		// 사용자로 부터 가위, 바위, 보 입력 받기
		UserInput input = new UserInput();
		input.start();
		
		try {
			input.join(); // 입력이 끝날때까지 기다린다.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		// 결과 판정하기
		String result = "";
		if( man.equals(com) ){
			result = "비겼습니다.";
		}else if( (man.equals("가위") && com.equals("보"))
				 || (man.equals("바위") && com.equals("가위"))
				 || (man.equals("보") && com.equals("바위")) ){
			result = "당신이 이겼습니다.";
		}else{
			result = "당신이 졌습니다.";
		}

		// 결과 출력
		System.out.println("=== 결 과 ===");
		System.out.println("컴퓨터 : " + com);
		System.out.println("당  신 : " + man);
		System.out.println("결  과 : " + result);
	}

}

/**
 * 게임 타이머
 */
class GameTimer extends Thread{
	@Override
	public void run() {
		for(int i=5; i>=1; i--){
			if(T07ThreadGame.inputCheck==true){
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("시간이 초과되어 당신이 졌습니다.");
		System.exit(0);//프로그램종료. 
//		System.exit(0)없으면 사용자 입력이 끝날때까지 프로세스가 종료되지 않는다.
		
//		모든 스레드가 종료되면 프로세스가 종료됨
//		즉, 프로세스가 종료되지 않으면 스레드가 남아있다는 뜻.
//		System.exit(0)는 남아있는 스레드가 있어도 프로세스를 종료시키는 명령
//		조심해서 써야됨!

	}
}

/**
 * 사용자 입력 처리를 위한 스레드
 */
class UserInput extends Thread {
	@Override
	public void run() {
		
		String inputData = "";
		
		//가위바위보 중 하나가 입력할때까지 입력을 반복
		do{
			inputData = JOptionPane.showInputDialog("가위, 바위, 보를 입력하세요");
		}while(!inputData.equals("가위") && !inputData.equals("바위") && !inputData.equals("보"));

		T07ThreadGame.inputCheck = true;  // 입력이 완료됨을 알려주는 변수값을 변경한다.
		T07ThreadGame.man = inputData;	   //  입력값 설정
	}
}







