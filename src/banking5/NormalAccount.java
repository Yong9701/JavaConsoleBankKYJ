package banking5;

import java.util.Objects;

public class NormalAccount extends Account {
		public int interest;

		public NormalAccount(String accountNum, String name, int money, int interest) {
			super(accountNum, name, money);
			this.interest = interest;
		}
		
		public void setAccountNum(String accountNum) {
			this.accountNum = accountNum;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setMoney(int money) {
			this.money = money;
		}
		public void setInterest(int interest) {
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
