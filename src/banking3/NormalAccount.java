package banking3;

public class NormalAccount extends Account {
		public int interest;

		public NormalAccount(String accountNum, String name, int money, int interest) {
			super(accountNum, name, money);
			this.interest = interest;
		}
		public String getAccountNum() {
			return accountNum;
		}
		public double getInterest() {
			double interestRate = (double)interest/100;
			return interestRate;
		}
		@Override
		public void showAccInfo() {
			System.out.println("계좌번호:"+ accountNum);
			System.out.println("이름:"+ name);
			System.out.println("잔액:"+ money);
			System.out.println("기본이자:" + interest + "%");
		}
}
