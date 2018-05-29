//I did this homework alone, with only this semester's material. Shaocheng Yang
package assignment8;

import java.awt.List;
import java.util.ArrayList;

public class LongestCommonSub {
	public static final int conner = 0;
	public static final int up = 1;
	public static final int left = 2;
	

	public static void main(String[] args) {
		String x = "vbbnbv";
		String y = "nvbbv";
		System.out.println("LCS of vbbnbv and nvbbv:");
		System.out.println(longestCommonSubsequence(x,y));
		
		String x2 = "JIOOADW";
		String y2 = "AOADIWFJ";
		System.out.println("LCS of JIOOADW and AOADIWFJ:");
		System.out.println(longestCommonSubsequence(x2,y2));
	}
	
	/**
	 * this method calls LCSLength that could find the LCS of two strings 
	 * also calls LCSString to show the string
	 * return the largest common substring
	 * @param x
	 * @param y
	 * @return string
	 */
	public static String longestCommonSubsequence(String x, String y){
		int m = x.length();
		int n = y.length();
		int b[][] = new int[m+1][n+1];
		int c[][] = new int[m+1][n+1];
		LCSLength(x,y,b,c);
		ArrayList<String> common = new ArrayList<String>();
		
		
		LCSString(x,b,common,m,n);
		
		String string = new String();
		while (!common.isEmpty()){
			string+=common.get(0);
			common.remove(0);
		}
		return string;
		
	}
	
	
	/**
	 * this method use recursive method to build the LCS of two strings 
	 * @param x
	 * @param b
	 * @param common
	 * @param i
	 * @param j
	 */
	private static void LCSString(String x, int[][] b, ArrayList<String> common, int i, int j) {
		if(i==0 || j == 0){
			return;
		}
		if(b[i][j]==LongestCommonSub.conner){	//find the word, add to the arraylist
			LCSString(x,b,common,i-1,j-1);
			common.add(""+x.charAt(i-1));
			
		}
		else if(b[i][j]==LongestCommonSub.up){
			LCSString(x,b,common,i-1,j);
		}
		else{
			LCSString(x,b,common,i,j-1);
		}
		
		
	}

	/**
	 * this method uses the matrix to find the LCS of two strings
	 * @param x
	 * @param y
	 * @param b
	 * @param c
	 */
	private static void LCSLength(String x, String y, int[][] b, int[][] c) {
		int m = x.length();
		int n = y.length();
		
		//initial first row and column 
		for(int i = 1 ; i < m+1; i ++){	
			c[i][0]=0;
		}
		
		for(int j=0 ; j < n + 1 ; j++){
			c[0][j]=0;
		}
		
		for(int i=1;i<=m;i++){
			for (int j =1;j<=n;j++){
				
				if(x.charAt(i-1)==y.charAt(j-1)){
					c[i][j]=c[i-1][j-1]+1;
					b[i][j] = LongestCommonSub.conner;
				}
				else if(c[i-1][j]>=c[i][j-1]){
					c[i][j] = c[i-1][j];
					b[i][j] = LongestCommonSub.up;
				}
				else{
					c[i][j] = c[i][j-1];
					b[i][j] = LongestCommonSub.left;
				}
			}
		}
				
	}

}
