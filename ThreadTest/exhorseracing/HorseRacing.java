import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HorseRacing {
	static String strRank = "";
	public static void main(String[] args) {		
//		Horse h = new Horse("1번말");
		
		System.out.println("경기 시작");
		
//		h.start();
//
//		try {
//			h.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		List<Horse> hList = new ArrayList<Horse>();
		
		hList.add(new Horse("1번말"));
		hList.add(new Horse("2번말"));
		hList.add(new Horse("3번말"));
		hList.add(new Horse("4번말"));
		hList.add(new Horse("5번말"));
		hList.add(new Horse("6번말"));
		hList.add(new Horse("7번말"));
		hList.add(new Horse("8번말"));
		hList.add(new Horse("9번말"));
		hList.add(new Horse("10번말"));

		for(Thread th : hList) {
			th.start();
			System.out.println();
		}
		
		for(Thread th : hList) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Collections.sort(hList);
		
		System.out.println("경기 끝");
		System.out.println("---------------");
		System.out.println("경기 결과");
		System.out.println("순위" + strRank);
//		for(Horse h : hList) {
//			System.out.printf("%d등 : %s \n",h.getRank(), h.getHName());
//		}
		
		
		
	}
}


class Horse extends Thread implements Comparable<Horse> {
	private String hName; // 말이름
	private int rank; // 순위 0
	private Random random;

	public Horse(String name) {
		super();
		this.hName = name;
		this.random = new Random();
	}

	
	public String getHName() {
		return hName;
	}


	public void setHName(String hName) {
		this.hName = hName;
	}


	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	// this와 h의 랭크를 오름차순으로 정렬
	@Override
	public int compareTo(Horse h) {
		return Integer.compare(this.getRank(), h.getRank());
	}

	// 1~50까지 -를 출력한다. 10구간마다 위치를 >로 나타내고
	// 속도는 0.01초부터 0.5초까지 랜덤으로 설정
	@Override
	public void run() {
		System.out.print(getHName() + " ");
		for (int i = 1; i <= 50; i++) {
			if (i % 10 == 0) {
				System.out.print(">");
			}
			System.out.print("-");
			try {
				Thread.sleep(random.nextInt(500) + 10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(getHName() + "이 골에 도착했습니다.");
		HorseRacing.strRank += hName + " ";
		rank++;

	}

}
