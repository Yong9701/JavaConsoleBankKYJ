package banking7.threeby3_2;

import java.util.Random;

public class shufflePuzzle {

	public String[][] shuffle(String[][] arr, String target) {

	    Random rand = new Random();
	    for (int k = 0; k < 100; k++) {
	    	
	    	findPosition position = new findPosition();
	    	int[] x = position.finding(arr, target);
	    	int xPos = x[0]; 
	    	int[] y = position.finding(arr, target);
	    	int yPos = y[1];
	    	
	    	moveValue mv = new moveValue();
	    	
	        int shuffle = rand.nextInt(4);
	        
	        switch(shuffle) {
	        	case 0:
	        		if(yPos < arr[xPos].length-1) {
	        			mv.move(arr, xPos, yPos, xPos, yPos + 1);
	        		}
	        		break;
	        	case 1:
	        		if(yPos > 0) {
	        			mv.move(arr, xPos, yPos, xPos, yPos - 1);
	        		}
	        		break;
	        	case 2:
	        		if(xPos < arr.length-1) {
	        			mv.move(arr, xPos, yPos, xPos + 1, yPos);
	        		}
	        		break;
	        	case 3:
	        		if(xPos > 0) {
	        			mv.move(arr, xPos, yPos, xPos - 1, yPos);
	        		}
	        		break;
	        }
	    }

	    return arr;
	}
}
