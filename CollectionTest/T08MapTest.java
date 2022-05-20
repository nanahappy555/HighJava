package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.print.DocFlavor.STRING;

public class T08MapTest {
	/*
	 * Map => key값과 value값을 한 쌍으로 관리하는 객체 key값은 중복을 허용하지 않고 순서가 없다.(Set의 특징) value값은
	 * 중복을 허용한다.(List의 특징)
	 */
	public static void main(String[] args) {

		Map<String, String> map = new HashMap<String, String>();

		// 자료추가 => put(key값, value값);
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1234-5678");

		System.out.println("map => " + map);

		// 자료 수정 => 데이터를 저장할 때 key값이 같으면 나중에 입력한 값이 저장된다.
		// put(수정할key값, 새로운value값);
//					 해당되는 key가 없으면 아예 새로운 데이터가 추가됨
//					 List에서는 인덱스 또는 데이터를 매개값으로 넣었따
		map.put("addr", "서울");
		System.out.println("map => " + map);

		// 자료 삭제 => remove(삭제할key값);
		map.remove("name");
		System.out.println("map => " + map);

		// 자료 읽기 => get(key값); key값을 알고있을 때
		System.out.println("addr => " + map.get("addr"));
		System.out.println("===================================");

		// key값들을 읽어와 자료를 출력하는 방법

		// 방법1 => keySet() 이용하기
		// keySet()메서드 => Map의 *key값들**만** 읽어와 Set형으로 반환한다.
		Set<String> keySet = map.keySet(); //key만 모아둔 Set

		System.out.println("Iterator를 이용한 방법");
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String key = it.next();
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("-------------------------------------");

		// 방법2 => Set형의 데이터를 '향상된 for문'으로 처리하면 Iterator를 사용하지 않아도 된다.
		//향상된 for문을 쓸 수 있는 거 : Collection, 배열
		System.out.println("향상된 for문을 이용하는 방법");
		for (String key : keySet) {
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("-------------------------------------");

		// 방법3 => value값만 읽어와 출력하기 => values() 이용
		//value값은 중복이 될수도 있다
		System.out.println("values() 이용한 방법");
		for (String value : map.values()) {
			System.out.println(value);
		}
		System.out.println("-------------------------------------");

		//가장 Map스러운 방법 entry=key+value
		// 방법4 => Map관련 클래스에는 Map.Entry타입의 내부 Class가 만들어져 있다.
//			      이 내부 클래스는 key와 value라는 멤버변수로 구성되어 있다.
//				  Map에서 이 Map.Entry타입의 객체들을 Set형으로 가져올 수 있다.
//				  => *entrySet()*메서드 이용
		Set<Map.Entry<String, String>> mapSet = map.entrySet();
		//Map 안의 내부 인터페이스 Entry (Map에서만 사용할거라서 Map안에 있음 

		// 가져온 Entry객체들을 순서대로 처리하기 위해서 Iterator객체로 반환
		Iterator<Map.Entry<String, String>> entryIt = mapSet.iterator();
		while (entryIt.hasNext()) {
			Map.Entry<String, String> entry = entryIt.next();
			System.out.println("key값 : " + entry.getKey());
			System.out.println("value값 : " + entry.getValue());
			System.out.println();
		}

	}
}
