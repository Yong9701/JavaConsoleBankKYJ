package banking2;

public class HighCreditAccount extends Account implements ICustomDefine {
	public int interest;
	public String lank;

	public HighCreditAccount(String accountNum, String name, int money, int interest, String lank) {
		super(accountNum, name, money);
		this.interest = interest;
		this.lank = lank;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public double getInterest() {
		double interestRate = (double)interest/100;
		return interestRate;
	}
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
		super.showAccInfo();
		System.out.println("기본이자:" + interest + "%");
		System.out.println("신용등급:" + lank);
	}
}
