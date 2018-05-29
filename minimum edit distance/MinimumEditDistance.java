package assignment7;
//I did this homework alone, with only this semester's material.  Shaocheng Yang
import java.util.ArrayList;
import java.util.List;

public class MinimumEditDistance {
	public static void main(String args[]){
		
		
		String y = "APPLETREE";
		String x = "APARTMENT";
		
		System.out.println("From APARTMENT to APPLETREE: ");
		List<String> resultOpartions = new ArrayList<String>();
		System.out.println(editDistance(x,y,1,1,1,resultOpartions));
		System.out.println(resultOpartions);
		System.out.println("now I set the replace cost to be very high:");
		System.out.println(editDistance(x,y,1,1,100,resultOpartions));
		System.out.println(resultOpartions);
		System.out.println("now I set the insert cost to be very high:");
		System.out.println(editDistance(x,y,100,1,1,resultOpartions));
		System.out.println(resultOpartions);
		System.out.println("now I set the remove cost to be very high:");
		System.out.println(editDistance(x,y,1,100,1,resultOpartions));
		System.out.println(resultOpartions);
		
		String x2 = "HELLOWORLD";
		String y2 = "HEXWORK";
		System.out.println();
		System.out.println("From HELLOWORLD to HEXWORK: ");
		System.out.println(editDistance(x2,y2,1,1,1,resultOpartions));
		System.out.println(resultOpartions);
	}
	
	
	public static final int replace = 0;
	public static final int remove = 1;
	public static final int insert = 2;
	public static final int same = 3;
	
	/**
	 * this method creates a table for each operations and a table for cost 
	 * @param x
	 * @param y
	 * @param insertCost
	 * @param deleteCost
	 * @param replaceCost
	 * @param resultOperations
	 * @return minimum edit distance
	 */
	public static int editDistance(String x, String y, int insertCost, int deleteCost, int replaceCost, List<String> resultOperations){
		resultOperations.clear();
		int row = x.length();
		int col = y.length();
		
		int[][]	op = new int[row+1][col+1];
		int[][] cost = new int[row+1][col+1];
		
		editdistancetable(x,y,insertCost, deleteCost, replaceCost, cost, op);
		optable(x, y, op.length-1, op[0].length-1, resultOperations,op);
		
		return cost[row][col];
		
		
	}

	/**
	 * this method stores the sequence of operations to transform x into y to resultOperations
	 * @param x
	 * @param y
	 * @param i
	 * @param j
	 * @param resultOperations
	 * @param op
	 */
	private static void optable(String x, String y, int i, int j, List<String> resultOperations, int[][] op) {
		if(i==0 || j ==0){
			return;
		}
		if(op[i][j]==MinimumEditDistance.same){	//if two characters are the same 
			optable(x,y,i-1,j-1,resultOperations,op);
		}
		else if(op[i][j]==MinimumEditDistance.insert){	//if insert
			optable(x,y,i,j-1,resultOperations,op);
			resultOperations.add(" insert " + y.charAt(j-1));
		}
		else if(op[i][j]==MinimumEditDistance.remove){	//if remove
			optable(x,y,i-1,j,resultOperations,op);
			resultOperations.add(" remove " + x.charAt(i-1));
		}
		else{	//if replace
			optable(x,y,i-1,j-1,resultOperations,op);
			resultOperations.add(" replace " + x.charAt(i-1)+" to "+y.charAt(j-1));
		}
		
	}

	
	/**
	 * this method creates a cost table to find the minimum edit distance for all cases.
	 * @param x
	 * @param y
	 * @param insertCost
	 * @param deleteCost
	 * @param replaceCost
	 * @param cost
	 * @param op
	 */
	private static void editdistancetable(String x, String y, int insertCost, int deleteCost, int replaceCost,
			int[][] cost, int[][] op) {
		int row = x.length();
		int col = y.length();
		
		for(int i=1;i<=row;i++){	//cost for null to y
			cost[i][0] =i*insertCost;
		}
		for(int j=0;j<=col;j++){
			cost[0][j] =j*deleteCost;	//cost for y to null
		}
		
		for(int i=1;i<=row;i++){
			for (int j =1;j<=col;j++){
				//if two characters are not same
				if(x.charAt(i-1)!=y.charAt(j-1)){
					//compare three costs
					int InCost = cost[i][j-1]+insertCost;
					int DeCost = cost[i-1][j]+deleteCost;
					int ReCost = cost[i-1][j-1]+replaceCost;
					
					if(DeCost <= InCost &&DeCost <= ReCost){
						//if delete costs lowest
						cost[i][j] = DeCost;
						op[i][j] = MinimumEditDistance.remove;
					}
					else if(InCost <=DeCost && InCost<=ReCost){
						//insert is lowest
						cost[i][j] = InCost;
						op[i][j] = MinimumEditDistance.insert;
					}
					else{
						//replace is lowest
						cost[i][j] = ReCost;
						op[i][j] = MinimumEditDistance.replace;
					}
				}
				else{
					//if two characters are the same
					cost[i][j] = cost[i-1][j-1];
					op[i][j]= MinimumEditDistance.same;
				}
			}
		}
		
	}
	
	
}