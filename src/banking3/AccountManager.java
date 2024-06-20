package banking3;

import java.util.InputMismatchException;
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
	public static void showAccount() {
		System.out.println("---계좌선택---");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
	}
	public void makeAccount() {
		Scanner scan = new Scanner(System.in);
		String iAccountNum, iName, iLank;
		int iMoney, iInterest;
		int selectAcc;
		
		System.out.println("***신규개좌개설***");
		showAccount();
		System.out.print("선택:"); selectAcc = scan.nextInt();
		scan.nextLine();
		
		switch(selectAcc) {
			case 1: {
					System.out.print("계좌번호:"); iAccountNum = scan.nextLine();
					System.out.print("고객이름:"); iName = scan.nextLine();
					System.out.print("잔고:"); iMoney = scan.nextInt();
					System.out.print("기본이자%(정수형태로입력):"); iInterest = scan.nextInt();
					
					myAccount[this.numOfAccount++] = new NormalAccount(iAccountNum, iName, iMoney, iInterest);
					break;
				}
			case 2: {
					System.out.print("계좌번호:"); iAccountNum = scan.nextLine();
					System.out.print("고객이름:"); iName = scan.nextLine();
					System.out.print("잔고:"); iMoney = scan.nextInt();
					System.out.print("기본이자%(정수형태로입력):"); iInterest = scan.nextInt();
					scan.nextLine();
					System.out.print("신용등급(A,B,C등급):"); iLank = scan.nextLine();
					
					myAccount[this.numOfAccount++] = new HighCreditAccount(iAccountNum, iName, iMoney, iInterest, iLank);
					break;
				}
			default: System.out.println("잘못된 값을 입력하셨습니다.");
			break;
		}
		System.out.println("계좌개설이 완료되었습니다.");
	}
	
	public void depositMoney() {
		Scanner scan = new Scanner(System.in);
		String iAccountNum;
		int iMoney;
		boolean isFind = false;
		
		try {
			System.out.println("***입 금***");
			System.out.println("계좌번호와 입금할 금액을 입력하세요");
			System.out.print("계좌번호:"); iAccountNum = scan.nextLine();
			System.out.print("입금액:"); iMoney = scan.nextInt();
		
			if(iMoney > 0) {
				if(iMoney % 500 == 0) {
					for(int i=0; i<numOfAccount; i++) {
						if(iAccountNum.compareTo(myAccount[i].accountNum)==0) {
							double interestRate = 0.0;
							double addInterestRate = 0.0;
							if(myAccount[i] instanceof NormalAccount) {
								interestRate = ((NormalAccount)myAccount[i]).getInterest();
							} else if(myAccount[i] instanceof HighCreditAccount) {
								interestRate = ((HighCreditAccount)myAccount[i]).getInterest();
								addInterestRate = ((HighCreditAccount)myAccount[i]).getLank();
							}
							myAccount[i].money += ((myAccount[i].money * interestRate)
												+ (myAccount[i].money * addInterestRate)
												+ iMoney);
							
							System.out.println("입금이 완료되었습니다.");
							isFind = true;
						}
					}
					if(isFind == false) {
						System.out.println("계좌번호가 일치하지않습니다.");
					}
				}
				else {
					System.out.println("입금액은 500원 단위로 가능합니다.");
				}		
			} else {
				System.out.println("입금액은 양수만 가능합니다.");
			}
		} catch(InputMismatchException e) {
			System.out.println("입금액은 숫자만 가능합니다.");
		}
		
	}
	
	public void withdrawMoney() {
		Scanner scan = new Scanner(System.in);
		String iAccountNum;
		int iMoney;
		boolean isFind = false;
		
		System.out.println("***출 금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.print("계좌번호:"); iAccountNum = scan.nextLine();
		System.out.print("출금액:"); iMoney = scan.nextInt();
		
		if(iMoney > 0) {
			for(int i=0; i<numOfAccount; i++) {
				if(iAccountNum.compareTo(myAccount[i].accountNum) == 0) {
					if(iMoney % 1000 == 0) {
						if(myAccount[i].money > iMoney) {
							myAccount[i].money -= iMoney;
							System.out.println("출금이 완료되었습니다.");
						}
						else {
							System.out.print("잔고가 부족합니다. 금액전체를 출금할까요? ");
							System.out.println("y : 금액전체 출금처리, n : 출금요청취소");
							char wdyn = scan.next().charAt(0);
							
							if(wdyn == 'y' || wdyn == 'Y') {							
								myAccount[i].money = 0;
								System.out.println("출금이 완료되었습니다.");
							} else if(wdyn == 'n' || wdyn == 'N') {
								System.out.println("출금요청을 취소합니다.");
							} else {
								System.out.println("대소문자 구분 없이 y또는 n만 입력해주세요.");
							}
						}
					} else {
						System.out.println("출금액은 1000원 단위로 가능합니다.");
					}
					isFind = true;
				}
				if(isFind == false) {
					System.out.println("계좌번호가 일치하지않습니다.");
				}
			}
		} else {
			System.out.println("출금은 양수만 가능합니다.");
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
