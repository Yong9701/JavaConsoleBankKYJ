package banking4;

import java.util.Objects;

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
	
	@Override
    public int hashCode() {
        return Objects.hash(accountNum);
    }
	
	@Override
    public boolean equals(Object obj) {
		Account ac = (Account)obj;
		
		if(ac.accountNum.equals(this.accountNum)) {
			// 모든 내용이 일치하면 true를 반환한다. 그러면 set에는 추가되지 않는다.
			return true;
		} else {
			// 내용이 다르면 정상적으로 추가된다.
			return false;
		}
    }
}
