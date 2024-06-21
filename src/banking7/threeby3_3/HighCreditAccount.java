package banking7.threeby3_3;

// 신용신뢰계좌
public class HighCreditAccount extends Account implements ICustomDefine {
	public int interest;
	public String lank;

	public HighCreditAccount(String accountNum, String name, int money, int interest, String lank) {
		super(accountNum, name, money);
		this.interest = interest;
		this.lank = lank;
	}
	
	// 이자율 계산을 위해 이자값을 백분위로 변환
	public double getInterest() {
		double interestRate = (double)interest/100;
		return interestRate;
	}
	// 등급이 A, B, C에 따라 추가이자율로 변환 IcoustomDefine에서 이자율을 정의하였다.
	public double getLank() {
		if(lank.charAt(0) == 'A' || lank.charAt(0) == 'a') {
			return ICustomDefine.A;
		}
		else if(lank.charAt(0) == 'B' || lank.charAt(0) == 'b') {
			return ICustomDefine.B;
		}
		else if(lank.charAt(0) == 'C' || lank.charAt(0) == 'c') {
			return ICustomDefine.C;
		}
		else return 0;
	}

	@Override
	public void showAccInfo() {
		System.out.println("[신용신뢰계좌]");
		System.out.println("계좌번호:"+ accountNum);
		System.out.println("이름:"+ name);
		System.out.println("잔액:"+ money);
		System.out.println("기본이자:" + interest + "%");
		System.out.println("신용등급:" + lank);
	}
}
