package banking7.threeby3_2;

public class answerArr {
	
	public String[][] answer() {
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
}
