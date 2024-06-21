package banking7.threeby3_3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class AutoSaver extends Thread {
	private HashSet<Account> myAccount = new HashSet<>();
	
	 public void setAccounts(HashSet<Account> accounts) {
	        this.myAccount = accounts;
	    }
	@Override
	public void run() {
		// 쓰레드가 정지될 때까지 동작
		while (!Thread.interrupted()) {
            try {
            	// 5초의 block 생성
                sleep(5000);
                System.out.println("계좌정보가 텍스트로 자동저장됩니다.");
                
                BufferedWriter out = new BufferedWriter(
                        new FileWriter("src/banking7/AutoSaveAccount.txt"));
                // 보통계좌, 특판계좌, 신용신뢰계좌 구별을 위해 boolean 타입 정의
                boolean nor, high, special;
                // for-each문을 통해 개설된 모든 계좌 정보 확인
                for (Account ac : myAccount) {
                	// ac의 toString값을 contains를 통해 해당 값이 존재하는지 확인하여 있을경우 true를 반환
                	nor = (ac.toString()).contains("NormalAccount");
                	high = (ac.toString()).contains("HighCreditAccount");
                	special = (ac.toString()).contains("SpecialAccount");
                	// nor이 true일 경우 보통계좌
                	if(nor) {
                		out.write("[보통계좌]" + "계좌번호=" + ac.accountNum + ", 이름=" + ac.name
                        		+ ", 잔고=" + ac.money + ", 기본이자=" + ((NormalAccount)ac).interest);
                	}
                	// high이 true일 경우 신용신뢰계좌
                	if(high) {
                		out.write("[신용신뢰계좌]" + "계좌번호="+ac.accountNum +", 이름=" + ac.name
                        		+", 잔고=" + ac.money + ", 기본이자=" + ((HighCreditAccount)ac).interest
                        		+ ", 신용등급=" + ((HighCreditAccount)ac).lank);
                	}
                	// special이 true일 경우 특판계좌
                	if(special) {
                		out.write("[특판계좌]" + "계좌번호=" + ac.accountNum + ", 이름=" + ac.name
                        		+ ", 잔고=" + ac.money + ", 기본이자=" + ((SpecialAccount)ac).interest);
                	}
                	// 줄바꿈
                	out.newLine();
                }

                out.close(); // BufferedWriter 닫기
            } catch (InterruptedException e) {
                // InterruptedException 발생 시 스레드 종료
                return;
            } catch (IOException e) {
                System.out.println("파일 쓰기 중 오류발생");
                e.printStackTrace(); // 파일 쓰기 오류 디버깅을 위한 스택 트레이스 출력
            }
        }
		System.out.println("자동저장이 종료되었습니다.");
	}
}
