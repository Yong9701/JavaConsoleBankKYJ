package banking5;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountManager {
	private HashSet<Account> myAccount;
	
	public AccountManager(int num) {
		myAccount = new HashSet<Account>();
	}
	public static void showMenu() {
		System.out.println("-----Menu-----");
		System.out.println("1.계좌개설");
		System.out.println("2.입 금");
		System.out.println("3.출 금");
		System.out.println("4.계좌정보출력");
		System.out.println("5.계좌정보삭제");
		System.out.println("6.프로그램종료");
	}
	public static void showAccount() {
		System.out.println("---계좌선택---");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
	}
	
	public void makeAccount() {
		Scanner scan = new Scanner(System.in);
		String iAccountNum, iName, iLank = null;
		int iMoney, iInterest = 0;
		int selectAcc;
		
		System.out.println("***신규개좌개설***");
		showAccount();
		System.out.print("선택:"); selectAcc = scan.nextInt();
		scan.nextLine();
		
		System.out.print("계좌번호:"); iAccountNum = scan.nextLine();
		System.out.print("고객이름:"); iName = scan.nextLine();
		System.out.print("잔고:"); iMoney = scan.nextInt();
		if (selectAcc == 1) {
            System.out.print("기본이자%(정수형태로입력):"); iInterest = scan.nextInt();
            scan.nextLine(); // 버퍼 비우기
        } else if (selectAcc == 2) {
            System.out.print("기본이자%(정수형태로입력):"); iInterest = scan.nextInt();
            scan.nextLine(); // 버퍼 비우기
            System.out.print("신용등급(A,B,C등급):"); iLank = scan.nextLine();
        }
		
		boolean isDuplicate = false;
	    
	    for(Account ac : myAccount) {
	        if(iAccountNum.equals(ac.accountNum)) {
	            System.out.println("중복 계좌 발견됨. 덮어쓸까요? (y or n)");
	            char isCover = scan.nextLine().charAt(0);
	            
	            // 사용자가 덮어쓰기를 선택한 경우
	            if (isCover == 'y' || isCover == 'Y') {
	                if (ac instanceof NormalAccount && selectAcc == 1) {	                	
	                    ((NormalAccount) ac).setAccountNum(iAccountNum);
	                    ((NormalAccount) ac).setName(iName);
	                    ((NormalAccount) ac).setMoney(iMoney);
	                    ((NormalAccount) ac).setInterest(iInterest);
	                    System.out.println("계좌 정보가 성공적으로 업데이트 되었습니다.");
	                } else if (ac instanceof HighCreditAccount && selectAcc == 2) {	                    
	                    ((HighCreditAccount) ac).setAccountNum(iAccountNum);
	                    ((HighCreditAccount) ac).setName(iName);
	                    ((HighCreditAccount) ac).setMoney(iMoney);
	                    ((HighCreditAccount) ac).setInterest(iInterest);
	                    ((HighCreditAccount) ac).setLank(iLank);
	                    System.out.println("계좌 정보가 성공적으로 업데이트 되었습니다.");
	                }
	            } else {
	                System.out.println("덮어쓰기를 취소하였습니다.");
	            }
	            
	            isDuplicate = true;
	            break; // 중복이 발견되면 반복문 종료
	        }
	    }
	    if (!isDuplicate) {
	        if (selectAcc == 1) {
	            myAccount.add(new NormalAccount(iAccountNum, iName, iMoney, iInterest));
	            System.out.println("계좌개설이 완료되었습니다.");
	        } else if (selectAcc == 2) {
	            myAccount.add(new HighCreditAccount(iAccountNum, iName, iMoney, iInterest, iLank));
	            System.out.println("계좌개설이 완료되었습니다.");
	        }
	    }
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
					for(Account ac : myAccount) {
						if(iAccountNum.compareTo(ac.accountNum)==0) {
							double interestRate = 0.0;
							double addInterestRate = 0.0;
							if(ac instanceof NormalAccount) {
								interestRate = ((NormalAccount)ac).getInterest();
							} else if(ac instanceof HighCreditAccount) {
								interestRate = ((HighCreditAccount)ac).getInterest();
								addInterestRate = ((HighCreditAccount)ac).getLank();
							}
							ac.money += ((ac.money * interestRate)
												+ (ac.money * addInterestRate)
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
			for(Account ac : myAccount) {
				if(iAccountNum.compareTo(ac.accountNum) == 0) {
					if(iMoney % 1000 == 0) {
						if(ac.money > iMoney) {
							ac.money -= iMoney;
							System.out.println("출금이 완료되었습니다.");
						}
						else {
							System.out.print("잔고가 부족합니다. 금액전체를 출금할까요? ");
							System.out.println("y : 금액전체 출금처리, n : 출금요청취소");
							char wdyn = scan.next().charAt(0);
							
							if(wdyn == 'y' || wdyn == 'Y') {							
								ac.money = 0;
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
		for(Account ac : myAccount) {
			System.out.println("-------------");
			ac.showAccInfo();
			System.out.println("-------------");
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	
	public void deleteAccount() {
		Scanner scan = new Scanner(System.in);
		System.out.print("삭제할 계좌번호를 입력하세요:");
		String deleteAccount = scan.nextLine();
		
		int deleteIndex = -1;
		for(Account ac : myAccount) {
			if(deleteAccount.compareTo(ac.accountNum)==0) {
				myAccount.remove(ac);
				deleteIndex = 1;
				break;
			}
		}
		
		if(deleteIndex == -1) {
			System.out.println("일치하는 계좌가 없습니다.");
		}
		else {
			System.out.println("계좌를 삭제하였습니다.");
		}
	}
	
	public void saveAccountInfo() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
									 new FileOutputStream("src/banking5/AccountInfo.obj"));
			
			for(Account ac : myAccount) {
				out.writeObject(ac);
			}
			out.close();
		} catch(IOException e) {
			System.out.println("직렬화 중 예외발생");
		}
	}
	public void readAccountInfo() {
		try {
			ObjectInputStream in = new ObjectInputStream(
									new FileInputStream("src/banking5/AccountInfo.obj"));

			while(true) {
				Account ac = (Account)in.readObject();
				myAccount.add(ac);
			}				
		} catch(EOFException e) {
			System.out.println("계좌의 정보가 복원되었습니다.");	
		} catch (Exception e) {
			System.out.println("역직렬화 중 예외발생");
			e.printStackTrace();
		}
	}
}
