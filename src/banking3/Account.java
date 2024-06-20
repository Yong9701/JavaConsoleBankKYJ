package banking3;

public abstract class Account {
	public String accountNum;
	public String name;
	public int money;
	// 생성자 : 멤버변수 초기화. 멤버 구분을 위한 this 사용.
	public Account(String accountNum, String name, int money) {
		this.accountNum = accountNum;
		this.name = name;
		this.money = money;
	}
	// 멤버변수 전체를 출력하기 위한 멤버메서드
	public abstract void showAccInfo();
}
