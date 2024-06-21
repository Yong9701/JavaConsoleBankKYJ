package banking7.threeby3_3;

// 메뉴 선택시 1~7 이외의 숫자를 입력할 경우 예외처리 문구 출력
public class MenuSelectException extends Exception {
		public MenuSelectException() {
			super("1~8 사이의 정수만 입력하세요.");
	}
}
