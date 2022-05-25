package kr.or.ddit.basic;

import java.util.List;
import java.util.Random;

public class Horse extends Thread implements Comparable<Horse> {
	public static int currentRank;
	private String hName; // 말이름
	private int rank; // 순위 0
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
		for (int i = 1; i <= 50; i++) {
			setPosition(i);
			// 말의 속도는 0.01초부터 0.5초까지 랜덤
			try {
				Thread.sleep(random.nextInt(200) + 10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		setRank(++currentRank);

	}

}

//위치를 출력하는 스레드
class Position extends Thread {
	private List<Horse> hs;
//	private Random random;

	public Position(List<Horse> hs) {
		super();
		this.hs = hs;
//		this.random = new Random();
	}

	// 1~50까지 -를 출력한다. 말의 위치는 >로 찍어준다
	@Override
	public void run() {
		while (true) {
			if (Horse.currentRank == hs.size()) {
				break;
			}
			
			for(int i = 1; i<=10; i++) {
				System.out.println();
			}
			
			for (Horse h : hs) {
				System.out.print(h.getHName() + " : ");
				for (int i = 1; i <= 50; i++) {
					if (h.getPosition() == i) {
						System.out.print(">");
					}else {
						System.out.print("-");
					}
				}
				if(h.getPosition() == 50) {
					System.out.print("골인");
				}
				System.out.println();
			}
			try {
				Thread.sleep(200); //0.2초마다 말 위치 출력
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}
}
