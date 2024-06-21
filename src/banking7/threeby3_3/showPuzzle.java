package banking7.threeby3_3;

public class showPuzzle {

	public void showPz(String[][] arr) {
		System.out.println("==3 by 3==");
        for(String[] move : arr) {
        	for(String value : move) {
        		System.out.printf("%2s ", value);	
        	}
        	System.out.println();
        }
        System.out.println("==========");
		System.out.println("[이동] a:Left, d:Right, w:Up, s:Down");
		System.out.println("[종료] x:Exit");
	}
}
