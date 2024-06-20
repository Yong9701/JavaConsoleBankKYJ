package banking6;

public class MenuSelectException extends Exception {
		public MenuSelectException() {
			super("1~7 사이의 정수만 입력하세요.");
	}
}
