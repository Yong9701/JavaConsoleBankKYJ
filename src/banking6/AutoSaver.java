package banking6;

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
		while (!Thread.interrupted()) {
            try {
                sleep(5000);
                System.out.println("계좌정보가 텍스트로 자동저장됩니다.");
                
                BufferedWriter out = new BufferedWriter(
                        new FileWriter("src/banking6/AutoSaveAccount.txt"));
                boolean find;
                for (Account ac : myAccount) {
                	find = (ac.toString()).contains("NormalAccount");
                	if(find) {
                		out.write("[보통계좌]" + "계좌번호=" + ac.accountNum + ", 이름=" + ac.name
                        		+ ", 잔고=" + ac.money + ", 기본이자=" + ((NormalAccount)ac).interest);
                	} else {
                		out.write("[신용신뢰계좌]" + "계좌번호="+ac.accountNum +", 이름=" + ac.name
                        		+", 잔고=" + ac.money + ", 기본이자=" + ((HighCreditAccount)ac).interest
                        		+ ", 신용등급=" + ((HighCreditAccount)ac).lank);
                	}
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
