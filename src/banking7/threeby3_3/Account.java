package banking7.threeby3_3;

import java.io.Serializable;
import java.util.Objects;

/*
 abstract로 추상메서드로 변환 클래스를 입출력의 대상으로 파일로 저장하기 위해 Serializable
 인터페이스를 구현하여 정의함. 상속관계이므로 부모인 Account에만 정의하면 된다.
 */
public abstract class Account implements Serializable  {
	public String accountNum;
	public String name;
	public int money;
	// 생성자 : 멤버변수 초기화. 멤버 구분을 위한 this 사용.
	public Account(String accountNum, String name, int money) {
		this.accountNum = accountNum;
		this.name = name;
		this.money = money;
	}
	
	// 멤버변수 전체를 출력하기 위한 멤버메서드, 추상메서드이므로 내용을 입력하지않는다.
	public abstract void showAccInfo();
	
	@Override
    public int hashCode() {
        return accountNum.hashCode();
    }
	
	@Override
    public boolean equals(Object obj) {
		Account ac = (Account)obj;
		
		// 계좌번호 중복판단을 위해 비교
		if(ac.accountNum.equals(this.accountNum)) {
			// 계좌가 일치하면 true를 반환한다. 그러면 set에는 추가되지 않는다.
			return true;
		} else {
			// 내용이 다르면 정상적으로 추가된다.
			return false;
		}
    }
}
