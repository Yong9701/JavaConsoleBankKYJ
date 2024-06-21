package banking7.threeby3_3;

// 특판계좌
public class SpecialAccount extends NormalAccount {
	
	public SpecialAccount(String accountNum, String name, int money, int interest) {
		super(accountNum, name, money, interest);
	}
	
	// 이자율 계산을 위해 이자값을 백분위로 변환
	public double getInterest() {
		double interestRate = (double)interest/100;
		return interestRate;
	}
	@Override
	public void showAccInfo() {
		System.out.println("[특판계좌]");
		System.out.println("계좌번호:"+ accountNum);
		System.out.println("이름:"+ name);
		System.out.println("잔액:"+ money);
		System.out.println("기본이자:" + interest + "%");
	}
}
