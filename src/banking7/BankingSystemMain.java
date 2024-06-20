package banking7;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain {

	public static void main(String[] args) {

		AccountManager manager = new AccountManager(50);
		
		// 저장된 계좌 불러오기
		manager.readAccountInfo();
		
		// 무한루프로 작업을 마친 후에도 계속 메뉴를 띄움
		while(true) {
			manager.showMenu();
			System.out.print("선택:");
			
			try {
				// 문자를 입력하거나 1~7 이외의 숫자를 입력할 경우 오류 발생
				int menu = integerEr();
				
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
				case ICustomDefine.DELETE:
					manager.deleteAccount();
					break;
				case ICustomDefine.SAVEOPTION:
					manager.saveOption();
					break;
				case ICustomDefine.EXIT:
					System.out.println("프로그램종료");
					manager.saveAccountInfo();
					return;
				} // switch 끝
			}  catch(MenuSelectException e) {
				System.out.println(e.getMessage());
			}
		} // while 끝
		
		
	}
	// 개발자정의 예외처리
	public static int integerEr() throws MenuSelectException {
		Scanner sc = new Scanner(System.in);
		int menu = 0;
		try {
			menu = sc.nextInt();
			sc.nextLine();
		}
		catch(InputMismatchException e) {
			System.out.println("[예외발생]메뉴는 정수로만 입력하세요.");
		}
		if(menu<1 || menu>7) {
			MenuSelectException ex = new MenuSelectException();
			throw ex;
		}
		return menu;
		
	}
}
