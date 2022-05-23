package kr.or.ddit.basic.exPlanet;

public class PlanetTest {
	public static void main(String[] args) {
		//데이터를 배열로 가져옴
		Planet[] enumArr = Planet.values();

		for (Planet p : enumArr) {
			// System.out.println(행성이름(반지름) 면적 4 * Math.PI * r * r);
			System.out.println(p.name() + "(" + p.getR() + ")" 
					+ "의 면적 : " + (4 * Math.PI * p.getR() * p.getR()) + "km^2");
		}

	}

	public enum Planet {
		수성(2439), 금성(6052), 지구(6371), 화성(3390), 
		목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);

		// 필드
		private int r;

		// 생성자(private)
		Planet(int data) {
			this.r = data;
		}

		// getter(상수니까 setter는 필요없음..)
		public int getR() {
			return r;
		}
	}
}