import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*굳이 고객정보를 객체로 저장할 필요가 없음
 * -Hotel프로그램
 * 필드
 * 스캐너타입 참조변수
 * Map타입 참조변수
 * 
 * 생성자()
 * 스캐너
 * HashMap객체
 * 
 * 메소드
 * 메뉴출력
 * 프로그램 시작
 * 	
 * 체크인(insert)
 * 체크아웃(remove)
 * 객실상태(iterator? key값 불러와서 key값을 Set에 저장하고 get(keySet)메소드로 출력

 */

public class Hotel {
	public static void main(String[] args) {
		new Hotel().startMenu();
	}
	private Scanner scan;
	private Map<Integer, String> guestMap;
	
	public Hotel() {
		scan = new Scanner(System.in);
		guestMap = new HashMap<Integer, String>();
	}
	
	public void displayMenu() {
		System.out.println();
		System.out.println("*******************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
		System.out.println("*******************************************");
		System.out.print("메뉴선택 => ");
		
	}
	
	public void startMenu() {
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		
		
		while(true) {
			
			displayMenu();
			
			int selectMenu = scan.nextInt();
			
			switch (selectMenu) {
			case 1:
				checkin(); //체크인
				break;
			case 2:
				checkout(); //체크아웃
				break;
			case 3:
				roomList(); //객실상태
				break;
			case 4:
				System.out.println("**************************");
				System.out.println("호텔 문을 닫았습니다.");
				System.out.println("**************************");
				return;
				
			default:
				System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
				break;
			}
		}
	}

//	private void roomList() {
//		Set<Integer> keySet = guestMap.keySet();
//		if(keySet.size() == 0) {
//			System.out.println("현재 투숙객이 없습니다.");
//		}
//		Iterator<Integer> it = keySet.iterator();
//		while(it.hasNext()) {
//			int roomNo = it.next();
//			
//			System.out.println("방번호 : " + roomNo + ", 투숙객 : " + guestMap.get(roomNo));
//		}
	
	private void roomList() {
		Set<Map.Entry<Integer, String>> mapSet = guestMap.entrySet();
		
		Iterator<Map.Entry<Integer, String>> entryIt = mapSet.iterator();
		while(entryIt.hasNext()) {
			Map.Entry<Integer, String> entry = entryIt.next();
			System.out.printf("방번호 : %d, 투숙객 : %s", entry.getKey(), entry.getValue());
		}
		
	}

	private void checkout() {
		//remove
		System.out.println("어느방에 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int roomNo = scan.nextInt();
		
		//투숙객이 없을 때
		if(guestMap.get(roomNo) == null) {
			System.out.println(roomNo + "호는 투숙객이 없습니다.");
			return; //더이상 할 작업이 없으니 메소드 종료...
		}
		
		//투숙객이 있을 때
		guestMap.remove(roomNo);
		System.out.println("체크아웃 되었습니다.");
	}

	private void checkin() {
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int roomNo = scan.nextInt();
		
		if(guestMap.get(roomNo) != null) {
			System.out.print(roomNo + "호는 이미 투숙객이 있습니다.");
			return; //더이상 할 작업이 없으니 메소드 종료...
		}
		
		//자료가 없음
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
//		scan.nextInt(); //버퍼에 남아있는 엔터 읽고 변수에 담지 않고 사라짐 중간에엔터칠일x
		String name = scan.next();
		
		System.out.println();
		
		guestMap.put(roomNo, name);
		System.out.println("체크인 되었습니다.");
		
	}

}