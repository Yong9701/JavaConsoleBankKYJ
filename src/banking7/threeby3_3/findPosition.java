package banking7.threeby3_3;

public class findPosition {
	
	public int[] finding(String[][] arr, String target) {
		for(int i=0; i < arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				if(arr[i][j] == target) {
					return new int[] {i, j};
				}
			}
		}
		return null;
	}
}
