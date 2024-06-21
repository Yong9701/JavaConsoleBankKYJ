package banking2;

import java.util.Scanner;

public class BankingSystemMain {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		AccountManager manager = new AccountManager();
		
		while(true) {
			// 1. 메뉴를 출력
			manager.showMenu();
			System.out.print("선택:");
			
			// 2.사용자로부터 수행할 기능의 메뉴를 입력받음
			int menu = scan.nextInt();
			
			// 3. 선택한 번호에 따라 메서드를 호출
			switch(menu) {
			case ICustomDefine.MAKE:
				manager.makeAccount();
				break;
			case ICustomDefine.DEPOSIT:
				manager.depositMoney();
				break;
			case ICustomDefine.WITHDRAW:
				manager.withdrawMoney();
				break;
			case ICustomDefine.INQUIRE:
				manager.showAccInfo();
				break;
			case ICustomDefine.EXIT:
				System.out.println("프로그램종료");
				return;
			} // switch 끝
		} // while 끝
	}

}
