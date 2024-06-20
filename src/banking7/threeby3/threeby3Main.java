package banking7.threeby3;

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
		
		ansArr = answerArr();
		arr = answerArr();
		arr = shufflePuzzle(arr, target);
		showPuzzle(arr);
       
        	while(true) {
        		int[] position = findPosition(arr, target);
        		int x = position[0];
        		int y = position[1];
        		try {
	        		System.out.print("키를 입력해주세요:"); key = sc.next();
	        		
	        		if(key.equalsIgnoreCase("a")) {
	        			moveValue(arr, x, y, x, y + 1);
	        		} else if(key.equalsIgnoreCase("d")) {
	        			moveValue(arr, x, y, x, y - 1);
	        		} else if(key.equalsIgnoreCase("w")) {
	        			moveValue(arr, x, y, x + 1, y);
	        		} else if(key.equalsIgnoreCase("s")) {
	        			moveValue(arr, x, y, x - 1, y);
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
	        				arr = shufflePuzzle(arr, target);
	        				showPuzzle(arr);
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
	
	public static int[] findPosition(String[][] arr, String target) {
		for(int i=0; i < arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				if(arr[i][j] == target) {
					return new int[] {i, j};
				}
			}
		}
		return null;
	}
	
	public static String[][] answerArr() {
		  String[][] arr = new String[3][3];
		    int num = 1;

		    for (int i = 0; i < 3; i++) {
		        for (int j = 0; j < 3; j++) {
		            arr[i][j] = String.valueOf(num);
		            if ((i == 2) && (j == 2)) {
		                arr[i][j] = " ";
		            }
		            num++;
		        }
		    }
		    return arr;
	}
	
	public static void moveValue(String[][] arr, int x1, int y1, int x2, int y2) {
        String moveValue = arr[x1][y1];
        arr[x1][y1] = arr[x2][y2];
        arr[x2][y2] = moveValue;
    }
	
	public static String[][] shufflePuzzle(String[][] arr, String target) {

	    Random rand = new Random();
	    for (int k = 0; k < 100; k++) {
	    	
	    	int[] position = findPosition(arr, target);
	    	int x = position[0];
	    	int y = position[1];
	    	
	        int shuffle = rand.nextInt(4);
	        
	        switch(shuffle) {
	        	case 0:
	        		if(y < arr[x].length-1) {
	        			moveValue(arr, x, y, x, y + 1);
	        		}
	        		break;
	        	case 1:
	        		if(y > 0) {
	        			moveValue(arr, x, y, x, y - 1);
	        		}
	        		break;
	        	case 2:
	        		if(x < arr.length-1) {
	        			moveValue(arr, x, y, x + 1, y);
	        		}
	        		break;
	        	case 3:
	        		if(x > 0) {
	        			moveValue(arr, x, y, x - 1, y);
	        		}
	        		break;
	        }
	    }

	    return arr;
	}
	public static void showPuzzle(String[][] arr) {
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