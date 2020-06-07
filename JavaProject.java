package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

// id 순으로 정렬하기 위한 클래스
class Gender2 {
	int id;
	String fullName;

	String email;
	String gender;
	String signup_date;

	Date last_login_date;
	Date last_login_time;
	String point;
	// 날짜 포맷을 만들어서 출력
	SimpleDateFormat simTime = new SimpleDateFormat("h:mm a", Locale.US);
	SimpleDateFormat simDay = new SimpleDateFormat("M/d/yyyy");

	public Gender2(int id, String fullName, String email, String gender, String signup_date, Date last_login_date,
			Date last_login_time, String point) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.gender = gender;
		this.signup_date = signup_date;
		this.last_login_date = last_login_date;
		this.last_login_time = last_login_time;
		this.point = point;
	}

	@Override
	public String toString() {
		return id + "," + fullName + "," + email + "," + gender + "," + signup_date + ","
				+ simDay.format(last_login_date) + "," + simTime.format(last_login_time) + "," + point;
	}

}

// 마지막 로그인 날짜 비교 위한 클래스
class Gender implements Comparable<Gender> {
	int id;
	String first_name;
	String last_name;
	String email;
	String gender;
	String signup_date;
	Date last_login_date;
	String last_login_time;
	Double point;

	SimpleDateFormat sim = new SimpleDateFormat("M/d/yyyy");

	public Gender(int id, String first_name, String last_name, String email, String gender, String signup_date,
			Date last_login_date, String last_login_time, Double point) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.gender = gender;
		this.signup_date = signup_date;
		this.last_login_date = last_login_date;
		this.last_login_time = last_login_time;
		this.point = point;
	}

	@Override // 마지막 로그인 날짜 비교 위한 컴페어투 메소드 오버라이드
	public int compareTo(Gender o) {
		return last_login_date.compareTo(o.last_login_date);
	}

	@Override
	public String toString() {
		return id + "," + first_name + " " + last_name + "," + email + "," + gender + "," + signup_date + ","
				+ sim.format(last_login_date) + "," + last_login_time + "," + point;
	}
}

public class JavaProject {
	public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {
		List<Gender> gList = methodA(); // log.csv 파일을 읽어서 객체에 담고 List로 반환된걸 gList에 할당
		compareDate(gList); // gList를 토대로 날짜를 비교하여 last_login.csv 파일로 저장
		extractMale(gList); // gList를 토대로 남자만 추출하여 male.csv 파일로 저장
		extractFemale(gList); // gList를 토대로 여자만 추출하여 femal.csv 파일로 저장
		compareId(); // last_login.csv 파일을 읽어와서 특정 시간대에 로그인 했는지 확인하고 id 순으로 정렬하고 am.csv 파일로 저장
		List<Gender> gList2 = lastMethodB(); // 마지막 문제, 점수가 높은 순으로 100명을 추출하여 객체에 넣고 List에 담아서 반환된걸 gList2에 할당
		extractPoint(gList2); // gList2를 토대로 점수가 많은 순으로 정렬하여 best.csv 파일로 저장
		System.out.println("완료했습니다");
	}

	public static void extractPoint(List<Gender> gList2) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("best.csv")));
		bw.write("\uFEFF"); // 한글 깨짐 방지 위해 설정 BOM(Byte Order Mark)을 파일내 추가하는 형식
		bw.write("id,name,email,gender,signup_date,last_login_date,last_login_time,point\n");
		for (int i = 0; i < 100; i++) { // 100개만 추출
			bw.write(gList2.get(i).toString());
			if (i < gList2.size() - 2) {
				bw.write("\n");
			}
		}
		bw.close();
	}

	// 3 4 5월에 로그인 했는지 확인하여 날짜가 오래된 순으로 젤 위로 나오게 하는 메소드
	public static void compareDate(List<Gender> glist) throws IOException, ParseException {
		// last_login.csv에 담기 위한 버퍼writer
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("last_login.csv")));
		SimpleDateFormat sim = new SimpleDateFormat("M/d/yyyy"); // 날짜 포맷
		Date date1 = sim.parse("3/1/2020"); // 3 4 5월에 로그인 했는지 확인 때 쓸 Date
		Date date2 = sim.parse("5/22/2020");
		Collections.sort(glist); // 날짜 순으로 정렬
		bw.write("\uFEFF"); // 한글 깨짐 방지
		bw.write("id,name,email,gender,signup_date,last_login_date,last_login_time,point\n");
		for (int i = 0; i < glist.size(); i++) { // glist의 사이즈 만큼 for문을 돌린다
			if (glist.get(i).last_login_date.after(date1) && glist.get(i).last_login_date.before(date2)) {
				bw.write(glist.get(i).toString()); // 각 객체의 last_login_date가 3 4 5월에 속해있는지 체트, 있다면 파일에 담기
				if (i < glist.size() - 1) { // 마지막 줄에서 개행을 하지 않기 위한 if 조건문
					bw.write("\n");
				}
			}
		}
		bw.close();

	}

	// id 순으로 출력하기 위한 메소드
	public static void compareId() throws IOException, ParseException {
		FileReader fr;
		fr = new FileReader("last_login.csv"); // 해당 파일을 읽어온다
		BufferedReader br = new BufferedReader(fr); // 버퍼걸기
		String sLine = null; // 데이터를 한 줄 식 담을 변수
		List<Gender2> gender2List = new ArrayList<Gender2>(); // Gender2 객체를 담을 List
		SimpleDateFormat simDay = new SimpleDateFormat("M/d/yyyy"); // 날짜 포맷
		SimpleDateFormat simTime = new SimpleDateFormat("h:mm a", Locale.US); // 시간 포맷 (AM,PM으로 출력키 위해 Locale.US 설정)
		while ((sLine = br.readLine()) != null) { // 파일에 담겨 있는 데이터를 한 줄씩 읽기, 단 한 줄을 읽어서 null이 아닐 때
			String[] arrLine = sLine.split(","); // 한 라인을 받아서 콤마로 스플릿해서 배열에 담는다
			if (arrLine[1].equals("name")) { // 두번째 요소가 name과 동일하면 건너띄기 (처음에 첫 뻔째 요소가 id와 동일하면 이라는 조건을 설정했는데 의도데로 되지 않음)
				continue;
			}
			Gender2 gender = new Gender2(Integer.parseInt(arrLine[0]), arrLine[1], arrLine[2], arrLine[3], arrLine[4],
					simDay.parse(arrLine[5]), simTime.parse(arrLine[6]), arrLine[7]); // Gender2는 Gender와는 다르게 id순으로
																						// 정렬하기 위해 만든 클래서
			gender2List.add(gender);

		}
		br.close();
		// am1.csv 파일로 보내기 위한 버퍼writer

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("am.csv")));
		Date date1 = simTime.parse("00:00 AM"); // 특정 시간이 오전 0시 ~ 6시 사이에 있는 지 확인하기 위한 Date
		Date date2 = simTime.parse("06:00 AM");

		// id 순으로 정렬할 때 쓸 comparator 클래스를 만들어서 사용
		Collections.sort(gender2List, new Comparator<Gender2>() {
			@Override
			public int compare(Gender2 o1, Gender2 o2) {
				if (o1.id < o2.id) {
					return -1;
				} else if (o1.id > o2.id) {
					return 1;
				} else {
					return 0;
				}
			}

		});
		bw.write("\uFEFF"); // 한글 깨짐 방지
		bw.write("id,name,email,gender,signup_date,last_login_date,last_login_time,point\n");
		for (int i = 0; i < gender2List.size(); i++) {
			if (gender2List.get(i).last_login_time.after(date1) && gender2List.get(i).last_login_time.before(date2)) {
				bw.write(gender2List.get(i).toString()); // 각 객체의 last_login_time 필드가 오전0시 ~6시 사이에 있는지 체크, 있다면 파일에 넣기
				if (i < gender2List.size() - 1) { // 마지막 줄에서 개행을 하지 않기 위한 if 조건문
					bw.write("\n");
				}
			}
		}
		bw.close();
	}

	// 여자 추출 메소드
	public static void extractFemale(List<Gender> gList) throws IOException {
		// 데이터를 담을 female.csv, 그리고 버퍼를 걸어준다
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("female.csv")));
		bw.write("\uFEFF"); // 한글 깨짐 방지 위해 설정 BOM(Byte Order Mark)을 파일내 추가하는 형식
		bw.write("id,name,email,gender,signup_date,last_login_date,last_login_time,point\n");
		for (int i = 0; i < gList.size(); i++) { // 파라미터로 받은 List 사이즈 만큼 for문을 돌린다
			if (gList.get(i).gender.equals("Female")) { // gender 필터가 "Female" 이면 한글 "여"로 바꾼다
				gList.get(i).gender = "여";
				bw.write(gList.get(i).toString()); // 객체를 하나씩 write 한다
				if (i < gList.size() - 2) { // 마지막 줄에서 개행을 하지 않기 위한 if 조건문
					bw.write("\n");
				}
			}
		}
		bw.close(); // 닫기
	}

	// 남자 추출 메소드
	public static void extractMale(List<Gender> gList) throws IOException {
		// 데이터를 담을 male.csv, 그리고 버퍼를 걸어준다
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("male.csv")));
		bw.write("\uFEFF"); // 한글 깨짐 방지 위해 설정 BOM(Byte Order Mark)을 파일내 추가하는 형식
		bw.write("id,name,email,gender,signup_date,last_login_date,laist_login_time,point\n");
		for (int i = 0; i < gList.size(); i++) { // 파라미터로 받은 List 사이즈 만큼 for문을 돌린다
			if (gList.get(i).gender.equals("Male")) { // gender 필터가 male 이면 한글 남으로 바꾼다
				gList.get(i).gender = "남";
				bw.write(gList.get(i).toString()); // 객체를 하나씩 write
				if (i < gList.size() - 2) { // 마지막 줄에서 개행을 하지 않기 위한 if 조건문
					bw.write("\n");
				}
			}
		}
		bw.close(); // 닫기
	}

	// log.csv 파일을 읽어서 각 데이터를 Gender 객체에 넣어서 List에 하나씩 담는 메소드
	public static List methodA() throws IOException, ParseException {
		FileReader fr;
		fr = new FileReader("log.csv"); // log.csv를 가져오는 객체
		BufferedReader bufferedReader = new BufferedReader(fr); // 버퍼를 걸어주는 객체
		String sLine = null; // 데이터 한 줄을 읽어 담을 변수
		List<Gender> genderList = new ArrayList<Gender>(); // Gender 객체를 담을 List
		SimpleDateFormat sim = new SimpleDateFormat("M/d/yyyy"); // 날짜 포맷
		while ((sLine = bufferedReader.readLine()) != null) { // log.csv에 담겨 있는 데이터를 한 줄씩 읽기, 단 한 줄을 읽어서 null이 아닐 때
			String[] arrLine = sLine.split(","); // 한 줄의 데이터를 콤마로 나눠서 배열에 담기
			if (arrLine[0].equals("id")) { // 배열의 첫번째 요소가 id이면 건너뛰기
				continue;
			}
			Gender gender = new Gender(Integer.parseInt(arrLine[0]), arrLine[1], arrLine[2], arrLine[3], arrLine[4],
					arrLine[5], sim.parse(arrLine[6]), arrLine[7], Double.valueOf(arrLine[8])); // 객체에 데이터 담기
			genderList.add(gender); // List에 담기

		}
		bufferedReader.close(); // 닫기
		return genderList; // List를 반환
	}

	// 마지막 문제, 점수가 높은 순으로 100명을 추출하여 객체에 넣고 List에 하나씩 담아 반환하려는 메소드
	public static List lastMethodB() throws IOException, ParseException {
		FileReader fr;
		fr = new FileReader("log.csv"); // log.csv 파일을 불러온다
		BufferedReader bufferedReader = new BufferedReader(fr); // 버퍼를 씌운다
		String sLine = null; // 데이터를 할당할 변수 선언
		List<Gender> genderList = new ArrayList<Gender>(); // 객체를 담을 List
		SimpleDateFormat sim = new SimpleDateFormat("M/d/yyyy"); // 날짜 포맷을 log.csv에 있는 것과 맞춰준다
		while ((sLine = bufferedReader.readLine()) != null) { // 한 줄 식 읽어온다, 단 null이 아닐 때까지만
			String[] arrLine = sLine.split(","); // 읽어온 한 줄의 데이터를 콤마를 기준으로 나눠서 배열에 담기
			if (arrLine[0].equals("id")) { // 배열의 첫 요소가 id라면 건너뛰기
				continue;
			}
			Gender gender = new Gender(Integer.parseInt(arrLine[0]), arrLine[1], arrLine[2], arrLine[3], arrLine[4],
					arrLine[5], sim.parse(arrLine[6]), arrLine[7], Double.valueOf(arrLine[8])); // 문자열인 point를 더블로 형 변환
			genderList.add(gender);
		}
		Collections.sort(genderList, new Comparator<Gender>() { // Gender 객체를 비교하면 점수가 높은 객체가 젤 위로 가도록 하기 위해 Comparator
																// 임시 클래스 만들기
			@Override
			public int compare(Gender o1, Gender o2) {
				if (o1.point < o2.point) {
					return 1;
				} else if (o1.point > o2.point) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		bufferedReader.close();
		return genderList;
	}
}
