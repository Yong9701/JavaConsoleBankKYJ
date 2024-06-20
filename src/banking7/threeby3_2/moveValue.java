package banking7.threeby3_2;

public class moveValue {
	String[][] arr;
	int x1, y1, x2, y2;
	
	public void move(String[][] arr, int x1, int y1, int x2, int y2) {
        String moveValue = arr[x1][y1];
        arr[x1][y1] = arr[x2][y2];
        arr[x2][y2] = moveValue;
    }
}
