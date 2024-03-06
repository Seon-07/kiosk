package study.kiosk;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Kiosk3 {
	private static Colors[] colorArr = new Colors[10];
	private static Colors[] basket = new Colors[10];
	private Scanner sc = new Scanner(System.in);
	private boolean run = true; // 메인 메소드 while
	private int cnt = 0; // 바스켓 색상 수량 체크
	private int ScannerNum = 0;

	public static void main(String[] args) {
		Kiosk3 kiosk = new Kiosk3();
		kiosk.defaultColors(1); // 1 or 2
		while (kiosk.run) {
			kiosk.flatBox("#", 40, " Color List ");
			kiosk.kindOfColors();
			kiosk.select();
			kiosk.quit(); // while
		}
	}

	// nextInt -> next로
	// 숫자 아닌 문자열 입력될때 ,반대도
	// 0이하 입력될때
	// null 값 객체 호출될때
	// 가격, 수량이 음수나 문자열일때
	
	//기본 컬러 저장
	public void defaultColors(int number) {
		boolean a = (number == 1) ? true : false; // true = 1번 , false = 2번 , deadcode
		if (a) {
			// 기본 색상 1번, 객체 직접 생성
			colorArr[0] = new Colors("black", 10000);
			colorArr[1] = new Colors("white", 12000);
			colorArr[2] = new Colors("blue", 14000);
			colorArr[3] = new Colors("red", 16000);
			colorArr[4] = new Colors("green", 18000);
			colorArr[5] = new Colors("purple", 20000);
		} else {
			// 기본 색상 2번 ,defaultColor메소드 사용
			// 기본 컬러 배열값
			String[][] defaultColorArr = { { "black", "1000" }, { "white", "1200" }, { "blue", "1400" },
					{ "red", "1600" }, { "green", "1800" }, { "purple", "2000" } };
			setDefaultColor(defaultColorArr);
		}
	}

	// 기본 컬러 2번 객체 생성 메소드
	public void setDefaultColor(String[][] defaultColorArr) {
		for (int i = 0; i < defaultColorArr.length; i++) {
			colorArr[i] = new Colors(defaultColorArr[i][0], Integer.parseInt(defaultColorArr[i][1]));
		}
	}

	// 문자열 검사
	public int strScanner(String message) {
		System.out.print(message);
		String str = sc.next();
		try {
			ScannerNum = Integer.parseInt(str); // 예외 발생, str이 문자열인 경우 예외, 숫자는 ㄱㅊ
			if (ScannerNum < 0) {
				flat("-", 40);
				System.out.println("음수 값 변경: " + Math.abs(ScannerNum));
				ScannerNum = Math.abs(ScannerNum);
			} else if (ScannerNum == 0) {
				flat("-", 40);
				System.out.println("잘못된 입력  @숫자가 0 입니다. 입력값: " + str);
				flat("-", 40);
				strScanner(message);
			}
		} catch (Exception e) {
			flat("-", 40);
			System.out.println("잘못된 입력  @숫자를 입력해주세요. 입력값: " + str);
			flat("-", 40);
			strScanner(message);
		}
		return ScannerNum;
	}

	public void addColorStrCheck(String inputColor) { // 예외가 발생해야지 코드 진행 가능, 반대
		try {
			if (Integer.parseInt(inputColor) * 1 == Integer.parseInt(inputColor)) { // 예외가 발생하면 ->catch
				System.out.println("색상 이름은 숫자 불가능 | 입력 값: " + inputColor);
				addColor();
			}
		} catch (Exception e) {
			// ?
		}
	}

	// 플랫
	public void flat(String elem, int num) {
		for (int i = 1; i <= num; i++) {
			System.out.print(elem);
		}
		System.out.println();
	}

	// 플랫 오버로딩, true만 개행
	public void flat(String elem, int num, boolean b) {
		for (int i = 1; i <= num; i++) {
			System.out.print(elem);
		}
		if (b) {
			System.out.println();
		}
	}

	// 플랫 박스, 개행 기본값
	public void flatBox(String elem, int num, String str) {
		int e = (num - str.length()) / 2;
		flat(elem, num);
		flat(elem, e, false);
		System.out.print(str);
		if (str.length() % 2 != 0) {
			e = e + 1;
		}
		flat(elem, e);
		flat(elem, num);
	}

	// colorArr
	public void kindOfColors() {
		int k = 1; // 색상 순번
		for (Colors i : colorArr) {
			if (i != null) {
				String index = "0123456789012";
				int strLeng = index.length() - i.getName().length();
				System.out.print("-" + k + "번 색상:  " + i.getName());
				flat(" ", strLeng, false);
				System.out.println("가격: " + deci(i.getPrice()));
				k++;

			} else {
				if (colorArr[0] == null) {
					System.out.println("색상이 없습니다.  @설정>색상추가");
					flat("-", 40);
					select();
				} else {
					break;
				}
			}
		}
		flat("-", 40);
	}

	// 메뉴 선택
	public void select() {
		if (run) { // 메인메소드의 while이 이미 종료 되었는지 검사
			System.out.println("1.색상선택 | 2.장바구니 | 3.설정 | 4.종료");
			int inputNum = strScanner("선택>");
			if (colorArr[0] == null && inputNum == 1) {
				flat("-", 40);
				System.out.println("색상이 없습니다.  @설정>색상추가");
				flat("-", 40);
				select();
			} else {
				switch (inputNum) {
				case 1:
					choice();
					break;
				case 2:
					basketList();
					break;
				case 3:
					login(); // setting, 진행 안되면 select로 원복
					break;
				case 4:
					flat("#", 40);
					System.out.println("프로그램 종료");
					flat("#", 40);
					quit();
					break;
				default:
					flat("-", 40);
					System.out.println("잘못된 선택 | 입력값: " + inputNum);
					flat("-", 40);
					select();
					break;
				}
			}
		}

	}

	// 총 결제 가격
	public int totalPrice(Colors[] basket) {
		int totalPrice = 0;
		for (Colors i : basket) {
			if (i != null) {
				totalPrice += i.getPayMoney();
			} else {
				break;
			}
		}
		return totalPrice;
	}

	// 색상 선택
	public void choice() {
		flat("-", 40);
		kindOfColors(); // 색상 종류 출력
		int inputNum = strScanner("색상 선택>");
		// null 예외 처리 하기
		if (inputNum <= 0) {
			System.out.println("잘못된 선택 | 입력값: " + inputNum);
			choice();
		} else {
			if (colorArr[inputNum - 1] == null) {
				System.out.println("잘못된 선택 | 입력값: " + inputNum);
				choice();
			} else {
				int inputCnt = strScanner(colorArr[inputNum - 1].getName() + "의 수량>");
				basket[cnt] = colorArr[inputNum - 1];
				basket[cnt].setCnt(inputCnt);
				basket[cnt].setPayMoney(basket[cnt].getPrice() * basket[cnt].getCnt());
				flat("-", 40);
				System.out.println("장바구니에 추가: ->" + basket[cnt].getName() + "  " + basket[cnt].getCnt() + "개");
				System.out.println("계산할 금액: +" + deci(basket[cnt].getPayMoney()) + "원");
				cnt++; // 클래스 cnt 필드 값 증가, 바스켓 색상 삭제할때 cnt--해야함
				flat("-", 40);
				System.out.println("1.더 고르기 | 2.결제하기 | 3.뒤로가기 ");
				int value = strScanner("선택>");
				switch (value) {
				case 1:
					choice();
					break;
				case 2:
					basketList();
					break;
				case 3:
					kindOfColors();
					select();
					break;
				default:
					System.out.println("잘못된 선택 | 입력값: " + value);
					choice();
					// break;
				}
			}
		}

	}

	// 결제
	public void pay() { // 프로그램 종료 메소드
		flat("#", 40);
		System.out.println(deci(totalPrice(basket)) + "원 결제 완료");
		flat("-", 40);
		System.out.println("프로그램 종료");
		flat("#", 40);
		quit();
	}

	// 설정
	public void setting() {
		flatBox("#", 40, " Setting ");
		flat("-", 40);
		System.out.println("1.색상추가 | 2.색상삭제 | 3.가격변경 | 4.설정완료");
		int inputNum = strScanner("선택>");
		switch (inputNum) {
		case 1:
			addColor();
			break;
		case 2:
			delColor();
			break;
		case 3:
			changePrice();
			break;
		case 4:
			flat("-", 40);
			System.out.println("설정완료");// return아니고 뒤로가기
			flat("-", 40);
			kindOfColors();
			select();
			break;
		default:
			flat("-", 40);
			System.out.println("잘못된 선택 | 입력값: " + inputNum);
			flat("-", 40);
			setting();
		}
	}

	// 설정 로그인
	public void login() {
		String id = "admin";
		flatBox("#", 40, " Login ");
		System.out.println("ID: >admin< "); // 임시 아이디 출력
		System.out.print("ID:>");
		String idInput = sc.next();
		if (!idInput.equals(id)) {
			flat("-", 40);
			System.out.println("로그인 실패 | 입력값: " + idInput);
			flat("-", 40);
			kindOfColors();
			select();
		} else {
			flat("-", 40);
			System.out.println(idInput + " 로그인 완료");
			flat("-", 40);
			setting();
		}

	}

	// 색상 추가
	public void addColor() {
		flat("-", 40);
		System.out.print("추가할 색상 이름:>");
		String inputColor = sc.next();
		addColorStrCheck(inputColor); // 숫자 입력 검사 메소드
		for (Colors k : colorArr) {
			if (k != null) { // 중복검사
				if (inputColor.equals(k.getName())) {
					System.out.println("이미 있는 색상입니다.");
					addColor();
					break;
				} else {
					continue; // 체크
				}
			} else {
				break; // 체크
			}
		}
		if (run) { // 중복 색상에서 빠져나갈때(색상추가 메소드로) 남은 코드들 처리
			int inputPrice = strScanner(inputColor + "의 판매 가격>");
			if (inputPrice <= 0) {
				System.out.println("0원 이하로 설정 불가");
				addColor();
			} else {
				for (int i = 0; i < colorArr.length; i++) {
					if (colorArr[i] == null) {
						colorArr[i] = new Colors();
						colorArr[i].setName(inputColor); // set말고 객체 만들수도 있음, 생성자 매개변수 name, price
						colorArr[i].setPrice(inputPrice);
						flat("-", 40);
						String index = "0123456789";
						int strLeng = index.length() - colorArr[i].getName().length();
						System.out.print("추가된 색상: " + colorArr[i].getName());
						flat(" ", strLeng, false);
						System.out.println("가격: " + deci(colorArr[i].getPrice()));
						flat("-", 40);
						setting();
						break;
					}
				}
			}
		}

	}

	// 색상 삭제
	public void delColor() {
		// 선택한 번호 배열을 널값으로 안바꾸고 선택된 색상보다 위의 객체 -1배열에 넣기, 그전에 널값인지 검사
		flat("-", 40);
		kindOfColors();
		int inputColor = strScanner("삭제할 색상 번호:>");
		if (inputColor <= 0) {
			System.out.println("잘못된 선택 | 입력값: " + inputColor);
			delColor();
		} else {
			if (colorArr[inputColor - 1] == null) {
				System.out.println("잘못된 선택 | 입력값: " + inputColor);
				delColor();
			} else {
				String memoryName = colorArr[inputColor - 1].getName();
				for (int i = inputColor - 1; i < colorArr.length; i++) {
					if (colorArr[i] != null) {
						colorArr[i] = colorArr[i + 1]; // 요기
					} else {
						flat("-", 40);
						System.out.println(memoryName + " 삭제완료");
						flat("-", 40);
						setting();
						break;
					}
				}
			}
		}

	}

	// 가격변경
	public void changePrice() {
		flat("-", 40);
		kindOfColors();
		int inputNum = strScanner("선택할 색상:>");
		if (inputNum <= 0) {
			System.out.println("잘못된 선택 | 입력값: " + inputNum);
			setting();
		} else {
			if (colorArr[inputNum - 1] == null) {
				System.out.println("잘못된 선택 | 입력값: " + inputNum);
				setting();
			} else {
				System.out.println(colorArr[inputNum - 1].getName() + "의 가격 변경");
				System.out.print(deci(colorArr[inputNum - 1].getPrice()) + "--->");
				int inputPrice = sc.nextInt();
				colorArr[inputNum - 1].setPrice(inputPrice);
				System.out.println("변경 완료");
				flat("-", 40);
				setting();
			}
		}
	}

	// int 쉼표, 포맷
	public String deci(int num) {
		DecimalFormat df = new DecimalFormat("#,###");
		String str = df.format(num);
		return str;
	}

	// 장바구니, Colors basket[]
	public void basketList() {
		flat("-", 40);
		int q = 1; // 바스켓 색상 순번
		for (Colors i : basket) {
			if (i != null) {
				if (i.getCnt() != 0) {
					String index = "0123456789";
					int strLeng = index.length() - i.getName().length();
					System.out.print(q + "번 " + i.getName());
					flat(" ", strLeng, false);
					System.out.println(i.getCnt() + "개 " + deci(i.getPayMoney()) + "원");
					q++;
				} else {
					break; // 체크
				}
			} else {
				break;
			}
		}
		System.out.println("계산할 총액: " + deci(totalPrice(basket)) + "원");
		flat("-", 40);
		if (totalPrice(basket) == 0) {
			System.out.println("계산할 항목 없음");
			flat("-", 40);
			kindOfColors();
			select();
		} else {
			System.out.println("1.결제하기 | 2.항목제거 | 3.뒤로가기");
			int inputNum = strScanner("선택>");
			switch (inputNum) {
			case 1:
				pay();
				break;
			case 2:
				delBasket();
				break;
			case 3:
				kindOfColors();
				select();
				break;
			default:
				System.out.println("잘못된 선택 | 입력값: " + inputNum);
				basketList();
				// break;
			}
		}
	}

	// 장바구니 항목 제거
	// 선택한 번호 삭제 -> 널값 검사 -> 선택한 번호 이상의 널값이 아닌 번호 배열에서 한 칸씩 당기기
	public void delBasket() {
		flat("-", 40);
		int input = strScanner("삭제할 색상 번호:>");
		if (input <= 0) {
			System.out.println("잘못된 선택 | 입력값: " + input);
			delBasket();
		} else {
			if (basket[input - 1] == null) {
				System.out.println("잘못된 선택 | 입력값: " + input);
				delBasket();
			} else {
				String memoryName = basket[input - 1].getName();
				int memoryCnt = basket[input - 1].getCnt();
				for (int i = input - 1; i < basket.length; i++) {
					if (basket[i] != null) {
						basket[i] = basket[i + 1];
					} else {
						System.out.println(memoryName + "       " + memoryCnt + "개 삭제완료");
						cnt--; // 체크
						break;
					}
				}
				basketList();
			}
		}
	}

	public void quit() { // 종료 메소드
		run = false;
	}
}

//Colors 클래스
class Colors {

	private String name = "default";

	private int price = 0;

	private int cnt = 0;

	private int payMoney = 0;

	// private int payMoney = this.getPrice() * this.getCnt();

	public Colors() {
	}

	public Colors(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getCnt() {
		return cnt;
	}

	public int getPayMoney() {
		return payMoney;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public void setPayMoney(int payMoney) {
		this.payMoney = payMoney;
	}

}