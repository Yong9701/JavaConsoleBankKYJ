package banking2;

public class NormalAccount extends Account {
		public int interest;

		public NormalAccount(String accountNum, String name, int money, int interest) {
			super(accountNum, name, money);
			this.interest = interest;
		}
		public double getInterest() {
			double interestRate = (double)interest/100;
			return interestRate;
		}
		@Override
		public void showAccInfo() {
			super.showAccInfo();
			System.out.println("기본이자:" + interest + "%");
		}
}
