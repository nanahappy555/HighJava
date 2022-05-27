package kr.or.ddit.basic;

import java.util.List;
import java.util.Random;

public class Horse extends Thread implements Comparable<Horse> {
	public static int currentRank; //현재순위
	private String hName; // 말이름
	private int rank; // 순위
	private int position;
	private Random random = new Random();

	public Horse(String name) {
		super();
		this.hName = name;
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

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	// this와 h의 랭크를 오름차순으로 정렬
	@Override
	public int compareTo(Horse h) {
		return Integer.compare(this.getRank(), h.getRank());
	}

	@Override
	public String toString() {
		return String.format("%d등 : %s", rank, hName);
	}

	@Override
	public void run() {
//		int s = random.nextInt(200) + 10;
		for (int i = 1; i <= 50; i++) {
			setPosition(i);
			
			// 말이 달리는 속도 조절(랜덤)
			try {
//				Thread.sleep(s); //말이 일정한 속도로 달리게 함
				Thread.sleep(random.nextInt(200) + 10); //말의 속도는 0.01초부터 0.2초까지 랜덤
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		setRank(++currentRank); //먼저 골인한 순서대로 1~10이 저장됨
	}
}

//말의 위치를 출력하는 스레드
class Position extends Thread {
	private List<Horse> hs;

	public Position(List<Horse> hs) {
		super();
		this.hs = hs;
	}

	// 1~50까지 -를 출력한다. 말의 위치는 >로 찍어준다
	@Override
	public void run() {
		while (true) {
			//currentRank는 Horse의 run()이 실행될때마다 증가하므로 Horse객체의 갯수와 같다
			//	⇒ Horse를 List에 담아서 관리하므로 List<Horse>의 size와  같음
			if (Horse.currentRank == hs.size()) { 
				break;
			}

			for (int i = 1; i <= 10; i++) {
				System.out.println();
			}

			for (Horse h : hs) {
				System.out.print(h.getHName() + " : ");
				for (int i = 1; i <= 50; i++) {
					if (h.getPosition() == i) {
						System.out.print(">");
					} else {
						System.out.print("-");
					}
				}
				if (h.getPosition() == 50) {
					System.out.print("골인");
				}
				System.out.println();
			}
			
			//while문을 도는 시간 조절
			try {
				Thread.sleep(200); // 0.2초마다 말 위치 출력
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
