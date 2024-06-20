package banking7;

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
	// HashSet 선언
	private HashSet<Account> myAccount;
	private AutoSaver dt = new AutoSaver();
	// 특판계좌 축하금을 위해 입금횟수를 카운팅
	static int depositCnt = 0;
	
	public AccountManager(int num) {
		myAccount = new HashSet<Account>();
	}
	// 메뉴 보여주기
	public static void showMenu() {
		System.out.println("-----Menu-----");
		System.out.println("1.계좌개설");
		System.out.println("2.입 금");
		System.out.println("3.출 금");
		System.out.println("4.계좌정보출력");
		System.out.println("5.계좌정보삭제");
		System.out.println("6.저장옵션");
		System.out.println("7.프로그램종료");
	}
	// 계좌개설 선택 시 보통계좌와 신용신뢰계좌 보여주기
	public static void showAccount() {
		System.out.println("---계좌선택---");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
	}
	// 계좌개설
	public void makeAccount() {
		Scanner scan = new Scanner(System.in);
		String iAccountNum, iName, iLank = null;
		int iMoney, iInterest = 0;
		// 보통계좌, 신용계좌, 특판계좌를 구별
		int selectAcc, spSel = 0;
		NormalAccount normal = null;
		SpecialAccount special = null;
		HighCreditAccount high = null;
		boolean yn = false, yn2 = false, yn3 = false;
		
		System.out.println("***신규개좌개설***");
		showAccount();
		System.out.print("선택:"); selectAcc = scan.nextInt();
		scan.nextLine(); // 버퍼 비우기
		
		if (selectAcc == 1) {
			// 보통계좌 중 보통예금계좌와 특판계좌 선택, 축하금 외 다른점이 없으므로 동일하게 구성함
			System.out.println("1. 보통예금계좌 / 2. 특판계좌 중 선택하세요.");
			System.out.print("선택:"); spSel = scan.nextInt();
			scan.nextLine(); // 버퍼 비우기
        }
		// 보통, 특판, 신용신뢰의 공통적인 계좌번호, 고객이름, 잔고를 사용자로부터 입력받음
		System.out.print("계좌번호:"); iAccountNum = scan.nextLine();
		System.out.print("고객이름:"); iName = scan.nextLine();
		System.out.print("잔고:"); iMoney = scan.nextInt();	
		
		// 보통, 특판일 경우 기본이자 값만 입력 받아서 
		if (selectAcc == 1) {
            System.out.print("기본이자%(정수형태로입력):"); iInterest = scan.nextInt();
            scan.nextLine(); // 버퍼 비우기
            // 보통예금계좌 선택할 경우 보통예금계좌에 저장 및 저장가능여부 판단
            if(spSel == 1) {
            	normal = new NormalAccount(iAccountNum, iName, iMoney, iInterest);
        		yn = myAccount.add(normal);
        	// 특판계좌 선택할 경우 특판계좌에 저장 및 저장가능여부 판단
            } else if(spSel == 2) {
            	special = new SpecialAccount(iAccountNum, iName, iMoney, iInterest);
        		yn2 = myAccount.add(special);
            }
            // 신용신뢰계좌 선택할 경우 기본이자, 신용등급 값을 입력받아 저장 및 저장가능여부 판단
        } else if (selectAcc == 2) {
            System.out.print("기본이자%(정수형태로입력):"); iInterest = scan.nextInt();
            scan.nextLine(); // 버퍼 비우기
            System.out.print("신용등급(A,B,C등급):"); iLank = scan.nextLine();
            // 신용등급은 A,B,C로만 구분되므로 그 외의 값이 올 수 없도록 설정
            if(!((iLank.charAt(0) >= 'A' && iLank.charAt(0) <= 'C') || (iLank.charAt(0) >= 'a' && iLank.charAt(0) <= 'c'))) {
            	System.out.println("신용등급은 대소문자 구분없이 A,B,C 중에서만 입력해주세요.");
            	return;
            }
            high = new HighCreditAccount(iAccountNum, iName, iMoney, iInterest, iLank);
    		yn3 = myAccount.add(high);
        }
		
		/*
		 저장가능여부는 선택한 계좌 외 항목은 false이나 선택 항목에
		 저장이 가능하면 true로 or연산자에 의해 true값을 반환하면 계좌개설이 완료된다.
		 */
		
		if(yn || yn2 || yn3) {
			System.out.println("계좌개설이 완료되었습니다.");
			// 중복일 경우 false를 반환하여 세 값 모두 false로 중복계좌임을 구별한다.
		} else {
			System.out.println("중복 계좌 발견됨. 덮어쓸까요? (y or n)");	
	        String isCover = scan.nextLine();
	         
	        if(isCover.equalsIgnoreCase("y")) {
	        	 if((selectAcc == 1) && (spSel == 1)) {
	        		 /*
	        		  hashSet 컬렉션으로 hashCode와 equals 함수를 이용하여
	        		  remove와 add만으로 간단하게 중복제거 및 덮어쓰기가 가능하다.
	        		  */
	        		 myAccount.remove(normal);
	        		 myAccount.add(normal);
	        	 } else if((selectAcc == 1) && (spSel == 2)) {
	        		 myAccount.remove(special);
	        		 myAccount.add(special);
	        	 } else if(selectAcc == 2) {
	        		 myAccount.remove(high);
	        		 myAccount.add(high);
	        	 }
	        	 System.out.println("업데이트가 완료되었습니다.");
			} else {
					// 무시
			}
		}
	}
	
	// 입금
	public void depositMoney() {
		Scanner scan = new Scanner(System.in);
		String iAccountNum;
		int iMoney;
		// 계좌가 일치하지 않는 경우를 위한 여부 정의
		boolean isFind = false;
		
		try {
			System.out.println("***입 금***");
			System.out.println("계좌번호와 입금할 금액을 입력하세요");
			System.out.print("계좌번호:"); iAccountNum = scan.nextLine();
			System.out.print("입금액:"); iMoney = scan.nextInt();
		
			// 입금액은 양수만 가능하며
			if(iMoney > 0) {
				// 500원 단위로만 입금이 가능하게 구현
				if(iMoney % 500 == 0) {
					// for-each문을 통해 개설된 모든 계좌를 확인
					for(Account ac : myAccount) {
						// compareTo로 입력한 계좌와 개설되어있던 계좌를 비교하여 값이 같으면 0을 반환
						if(iAccountNum.compareTo(ac.accountNum)==0) {
							// 기본이자 정의
							double interestRate = 0.0;
							// 신용등급의 추가이자 정의
							double addInterestRate = 0.0;
							// ac가 보통계좌일 경우
							if(ac instanceof NormalAccount) {
								interestRate = ((NormalAccount)ac).getInterest();
								ac.money += ((ac.money * interestRate) + iMoney);
							}
							// ac가 특판계좌일 경우
							if(ac instanceof SpecialAccount) {
								interestRate = ((SpecialAccount)ac).getInterest();
								// 입금마다 입금횟수를 증가시켜서
								depositCnt++;
								// 입금횟수가 짝수번째일 경우 축하금으로 500원을 더함
								if(depositCnt % 2 == 0) {
									System.out.println("특판계좌 " + depositCnt +"회차 입금으로 축하금 500원을 더 드립니다!");
									ac.money += ((ac.money * interestRate) + iMoney) + 500;
								} else {
									// 입금횟수가 홀수번째일 경우 축하금 없이 입금되며 회차를 출력
									System.out.println("특판계좌 " + depositCnt +"회차 입금입니다.");
									ac.money += ((ac.money * interestRate) + iMoney);
								}
							}
							// ac가 신용신뢰계좌일 경우
							if(ac instanceof HighCreditAccount) {
								interestRate = ((HighCreditAccount)ac).getInterest();
								addInterestRate = ((HighCreditAccount)ac).getLank();
								ac.money += ((ac.money * interestRate) + (ac.money * addInterestRate) + iMoney);
							}
							System.out.println("입금이 완료되었습니다.");
							// 입금이 완료되면 isFind를 true로 변환
							isFind = true;
						}
					}
					// isFind가 false일 경우 계좌를 찾지 못한 것으로 판단
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
			// try~catch를 통해 입금액은 숫자 외 문자를 입력받을 수 없게 설정
		} catch(InputMismatchException e) {
			System.out.println("입금액은 숫자만 가능합니다.");
		}
	}
	
	// 출금
	public void withdrawMoney() {
		Scanner scan = new Scanner(System.in);
		String iAccountNum;
		int iMoney;
		// 계좌가 일치하지 않는 경우를 위한 여부 정의
		boolean isFind = false;
		
		System.out.println("***출 금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.print("계좌번호:"); iAccountNum = scan.nextLine();
		System.out.print("출금액:"); iMoney = scan.nextInt();
		
		// 출금액은 양수만 가능하며
		if(iMoney > 0) {
			// for-each문을 통해 개설된 모든 계좌를 확인
			for(Account ac : myAccount) {
				// compareTo로 입력한 계좌와 개설되어있던 계좌를 비교하여 값이 같으면 0을 반환
				if(iAccountNum.compareTo(ac.accountNum) == 0) {
					// 1000원 단위로만 출금이 가능하게 구현
					if(iMoney % 1000 == 0) {
						// 기존 잔고가 출금액보다 클 경우
						if(ac.money > iMoney) {
							// 기존 잔고에서 출금액을 뺀다.
							ac.money -= iMoney;
							System.out.println("출금이 완료되었습니다.");
						}
						// 기존 잔고가 출금액보다 작을 경우
						else {
							System.out.print("잔고가 부족합니다. 금액전체를 출금할까요? ");
							System.out.println("y : 금액전체 출금처리, n : 출금요청취소");
							char wdyn = scan.next().charAt(0);
							
							// 잔고부족으로 금액전체를 출금할 경우 잔고는 0원이 된다.
							if(wdyn == 'y' || wdyn == 'Y') {							
								ac.money = 0;
								System.out.println("출금이 완료되었습니다.");
							} else if(wdyn == 'n' || wdyn == 'N') {
								System.out.println("출금요청을 취소합니다.");
							} else {
								// y또는 n 이외의 문자를 입력할 경우 오류 발생
								System.out.println("대소문자 구분 없이 y또는 n만 입력해주세요.");
							}
						}
					} else {
						System.out.println("출금액은 1000원 단위로 가능합니다.");
					}
					// 출금이 끝나면 isFind를 true로 변환
					isFind = true;
				}	
			}
			// isFind가 false일 경우 계좌를 찾지 못한 것으로 판단
			if(isFind == false) {
				System.out.println("계좌번호가 일치하지않습니다.");
			}
		} else {
			System.out.println("출금은 양수만 가능합니다.");
		}
		
	}
	
	// 계좌정보출력
	public void showAccInfo() {
		System.out.println("***계좌정보출력***");
		for(Account ac : myAccount) {
			System.out.println("-------------");
			ac.showAccInfo();
			System.out.println("-------------");
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	
	// 계좌정보삭제
	public void deleteAccount() {
		Scanner scan = new Scanner(System.in);
		System.out.print("삭제할 계좌번호를 입력하세요:");
		String deleteAccount = scan.nextLine();
		// 계좌삭제 판단을 위한 값 정의 
		int deleteIndex = -1;
		
		for(Account ac : myAccount) {
			if(deleteAccount.compareTo(ac.accountNum)==0) {
				myAccount.remove(ac);
				// 삭제 후 deleteIndex를 1로 변환
				deleteIndex = 1;
				break;
			}
		}
		// deleteIndex가 -1일 경우 삭제하지 못한 것이므로 계좌를 찾지 못한 것으로 판단
		if(deleteIndex == -1) {
			System.out.println("일치하는 계좌가 없습니다.");
		}
		else {
			System.out.println("계좌를 삭제하였습니다.");
		}
	}
	
	// 자동저장옵션
	public void saveOption() {
		Scanner scan = new Scanner(System.in);
		
		try {
			System.out.println("***자동옵션을 선택하세요.***");
			System.out.println("1. 자동저장on, 2. 자동저장off");
			System.out.print("선택:"); int autoSave = scan.nextInt();
			scan.nextLine();
			
			// 1을 입력시 자동저장on
			if (autoSave == 1) {
				/*
				 isAlive()를 통해 데몬쓰레드가 이미 실행중인지 판단. not(!)를 통해
				 실행중이지 않은 경우가 if의 true값이 된다.
				 */
				if(!dt.isAlive()) {
					// 실행중이지 않다면 새로운 AutoSaver를 생성
					dt = new AutoSaver();
					// AutoSave의 setAccount로 계좌정보 불러오기
					dt.setAccounts(myAccount);
					// 쓰레드를 데몬쓰레드로 설정
					dt.setDaemon(true);
					// 데몬쓰레드 시작
		            dt.start(); 
		            System.out.println("자동저장을 시작합니다.");
		            // 데몬쓰레드가 이미 실행중일 경우
				} else {
					System.out.println("자동저장이 이미 실행중입니다.");
				}
				// 2를 입력시 자동저장off
	        } else if (autoSave == 2) {
	        	// 마찬가지로 isAlive()를 통해 데몬쓰레드가 실행 중인 경우에
	        	if (dt.isAlive()) {
	        		// interrupt함수를 통해 데몬쓰레드를 중지한다.
	        		dt.interrupt();
	                System.out.println("자동 저장이 중지되었습니다.");
	            } else {
	                System.out.println("자동 저장이 이미 중지되어 있습니다.");
	            }
	        } 
			// 문자를 입력할 경우 예외 발생
		} catch(InputMismatchException e) {
			System.out.println("정수를 입력하세요");
	    } catch(Exception e) {
	    	System.out.println("예외발생");
	    	e.printStackTrace();
	    }
	}
	
	// IO입출력, 7번 프로그램종료시 AccountInfo.obj에 개설된 모든 계좌를 저장한다.
	public void saveAccountInfo() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
									 new FileOutputStream("src/banking7/AccountInfo.obj"));
			
			// for-each를 통해 개설된 모든 계좌를 확인
			for(Account ac : myAccount) {
				// ac 계좌를 저장
				out.writeObject(ac);
			}
			out.close();
		} catch(IOException e) {
			System.out.println("직렬화 중 예외발생");
		}
	}
	// IO입출력, 메인함수 실행시 AccountInfo.obj에 저장된 개설계좌를 모두 불러온다.
	public void readAccountInfo() {
		try {
			ObjectInputStream in = new ObjectInputStream(
									new FileInputStream("src/banking7/AccountInfo.obj"));

			// 저장된 계좌의 개수를 모르기에 무한루프로 설정
			while(true) {
				// 계좌정보를 불러오기
				Account ac = (Account)in.readObject();
				myAccount.add(ac);
			}				
			// 모든 계좌를 불러오면 catch문으로 자동으로 이동되어 무한루프 종료
		} catch(EOFException e) {
			// 오류 대신 계좌의 정보가 복원되었다는 출력문 생성
			System.out.println("계좌의 정보가 복원되었습니다.");	
		} catch (Exception e) {
			System.out.println("역직렬화 중 예외발생");
			e.printStackTrace();
		}
	}
}
