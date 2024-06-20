package banking7.threeby3_2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class threeby3Main {

	public static void main(String[] args) {
		
		String[][] ansArr = new String[3][3];
		String[][] arr = new String[3][3];
		Scanner sc = new Scanner(System.in);
		String key, replay;
		String target = " ";
		
		answerArr ans = new answerArr();
		shufflePuzzle shf = new shufflePuzzle();
		showPuzzle sp = new showPuzzle();
		findPosition fp = new findPosition();
		moveValue mv = new moveValue();
		
		ansArr = ans.answer();
		arr = ans.answer();
		arr = shf.shuffle(arr, target);
		sp.showPz(arr);
       
        	while(true) {
        		int[] position = fp.finding(arr, target);
        		int x = position[0];
        		int y = position[1];
        		try {
	        		System.out.print("키를 입력해주세요:"); key = sc.next();
	        		
	        		if(key.equalsIgnoreCase("a")) {
	        			mv.move(arr, x, y, x, y + 1);
	        		} else if(key.equalsIgnoreCase("d")) {
	        			mv.move(arr, x, y, x, y - 1);
	        		} else if(key.equalsIgnoreCase("w")) {
	        			mv.move(arr, x, y, x + 1, y);
	        		} else if(key.equalsIgnoreCase("s")) {
	        			mv.move(arr, x, y, x - 1, y);
	        		} else if(key.equalsIgnoreCase("x")) {
	        			System.out.println("게임을 종료합니다.");
	        			break;
	        		}
	        		
	        		System.out.println("==3 by 3==");
	        		for(int i=0; i<3; i++) {
	        			for(int j=0; j<3; j++) {
	        				System.out.printf("%2s ",arr[i][j]);
	        			}
	        			System.out.println();
	        		}
	        		System.out.println("==========");
	        		if(Arrays.deepEquals(ansArr, arr)) {
	        			System.out.println("정답입니다!");
	        			System.out.println("재시작하시겠습니까? (y:재시작, n:종료)");
	        			System.out.print("선택:"); replay = sc.next();
	        			if(replay.equalsIgnoreCase("y")) {
	        				arr = shf.shuffle(arr,target);
	        				sp.showPz(arr);
	        			} else {
	        				System.out.println("종료합니다.");	        				
	        				return;
	        			}
	        		}
        		} catch(ArrayIndexOutOfBoundsException e) {
        			System.out.println("*****이동불가*****");
        	} 
    	}
	
	}
}