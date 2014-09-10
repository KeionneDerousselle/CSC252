
public class EditDistanceCalculator {
	public int editDistance(String a, String b) {
		return editDistanceHelper(a, b, new int[a.length()+1][b.length()+1]);
	}
	
	private int editDistanceHelper(String a, String b, int[][] eds) 
	{
		
		for ( int i = 1; i <= a.length(); i++ ) {
			eds[i][0] = b.length();
		}
		for ( int j = 1; j <= b.length(); j++ ) {
			eds[0][j] = a.length();
		}
		
		for ( int i = 1; i <= a.length(); i++ ) {
			for ( int j = 1;  j <= b.length(); j++ ) {
				int delete = eds[i-1][j] + 1;
				int insert = eds[i][j-1] + 1;
				int remove = eds[i-1][j-1] + (a.charAt(i-1) == b.charAt(j-1) ? 0 : 1);
				eds[i][j] = Math.min(delete, Math.min(insert, remove));
			}
		}
		
		return eds[a.length()][b.length()];
	}
	
	/**
	 * #2.  Add a recursive version here, which uses a memory function.  Change your internal implementation
	 * to use this version. (1 point)
	 */
}
