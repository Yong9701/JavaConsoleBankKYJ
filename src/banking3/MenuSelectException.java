package banking3;

public class MenuSelectException extends Exception {
		public MenuSelectException() {
			super("1~5 사이의 정수만 입력하세요.");
	}
}
