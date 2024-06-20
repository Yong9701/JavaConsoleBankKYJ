package banking1;

import java.util.Scanner;

public class AccountManager {
	private Account[] myAccount;
	private int numOfAccount;
	
	public AccountManager(int num) {
		myAccount = new Account[num];
		numOfAccount = 0;
	}
	public static void showMenu() {
		System.out.println("-----Menu-----");
		System.out.println("1.계좌개설");
		System.out.println("2.입 금");
		System.out.println("3.출 금");
		System.out.println("4.계좌정보출력");
		System.out.println("5.프로그램종료");
	}
	
	public void makeAccount() {
		Scanner scan = new Scanner(System.in);
		String iAccountNum, iName;
		int iMoney;
		System.out.println("***신규개좌개설***");
		System.out.print("계좌번호:"); iAccountNum = scan.nextLine();
		System.out.print("고객이름:"); iName = scan.nextLine();
		System.out.print("잔고:"); iMoney = scan.nextInt();
		
		Account i = new Account(iAccountNum, iName, iMoney);
		
		myAccount[this.numOfAccount++] = i;
		
		System.out.println("계좌개설이 완료되었습니다.");
	}
	
	public void depositMoney() {
		Scanner scan = new Scanner(System.in);
		String iAccountNum;
		int iMoney;
		
		System.out.println("***입 금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.print("계좌번호:"); iAccountNum = scan.nextLine();
		System.out.print("입금액:"); iMoney = scan.nextInt();
		
		for(int i=0; i<numOfAccount; i++) {
			if(iAccountNum.compareTo(myAccount[i].accountNum) == 0) {
				myAccount[i].money += iMoney;
				System.out.println("입금이 완료되었습니다.");
			} else {
				System.out.println("계좌번호가 일치하지않습니다.");
			}
		}
	}
	
	public void withdrawMoney() {
		Scanner scan = new Scanner(System.in);
		String iAccountNum;
		int iMoney;
		
		System.out.println("***출 금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.print("계좌번호:"); iAccountNum = scan.nextLine();
		System.out.print("출금액:"); iMoney = scan.nextInt();
		
		for(int i=0; i<numOfAccount; i++) {
			if(iAccountNum.compareTo(myAccount[i].accountNum) == 0) {
				myAccount[i].money -= iMoney;
				System.out.println("출금이 완료되었습니다.");
			} else {
				System.out.println("계좌번호가 일치하지않습니다.");
			}
		}
	}
	
	public void showAccInfo() {
		System.out.println("***계좌정보출력***");
		for(int i=0; i<numOfAccount; i++) {
			System.out.println("-------------");
			myAccount[i].showAccInfo();
			System.out.println("-------------");
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	
}
