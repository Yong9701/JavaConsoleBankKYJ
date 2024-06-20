package banking5;

public class MenuSelectException extends Exception {
		public MenuSelectException() {
			super("1~6 사이의 정수만 입력하세요.");
	}
}
