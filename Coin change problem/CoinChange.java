//I did this work alone, with only this semester's material. Shaocheng Yang

package assignment8;

import java.util.ArrayList;
import java.util.Collections;

public class CoinChange {

	public static void main(String[] args) {
		ArrayList<Integer> coinarray = new ArrayList<Integer>();
		coinarray.add(3);
		coinarray.add(1);
		coinarray.add(5);	
		
		int money = 35;
		System.out.println("coins set: 1, 3, 5. And money is 35");
		System.out.println("coin change 1:");
		System.out.println(coinChange1(money,coinarray));
		System.out.println("coin change 2:");
		System.out.println(coinChange2(money,coinarray));
		System.out.println("coin change 3:");
		System.out.println(coinChange3(money,coinarray));
		
		coinarray.clear();
		coinarray.add(7);
		coinarray.add(3);
		coinarray.add(6);	
		
		money = 22;
		System.out.println("coins set: 7, 3, 6. And money is 22");
		System.out.println("coin change 1:");
		System.out.println(coinChange1(money,coinarray));
		System.out.println("coin change 2:");
		System.out.println(coinChange2(money,coinarray));
		System.out.println("coin change 3:");
		System.out.println(coinChange3(money,coinarray));
	
	
	}
	
	
	/**
	 * this method finds and returns the smallest set of coins to change a given amount of money. The coins parameter
contains the denominations of coins available.
	 * @param money
	 * @param coins
	 * @return sets Arraylist of coins chosen. Return empty list if not found.
	 */
	public static ArrayList<Integer> coinChange1(int money, ArrayList<Integer> coins){
		int min = Integer.MAX_VALUE;
		
		ArrayList<Integer> sets = new ArrayList<Integer>();
		
		for(int i=0; i<coins.size(); i++){
			if(money>=coins.get(i)){
				int sum = 0;
				ArrayList<Integer> helper = coinChange1(money-coins.get(i),coins);	//recursive 
				helper.add(coins.get(i));
				
				for (int j = 0; j < helper.size();j++){
					sum =sum+ helper.get(j);
				}
			
				if (sum == money && helper.size()<min){	//compare and find the smallest size
					sets.clear();
					sets.addAll(helper);
					min=helper.size();		//update the min size
				}
			}
		}
		return sets;
		
		
	}
	
	/**
	 * this finds and returns the smallest set of coins to change a given amount of money. The coins parameter
contains the denominations of coins available.
	 * @param money
	 * @param coins
	 * @return sets Arraylist of coins chosen. Return empty list if no solution
	 */
	public static ArrayList<Integer> coinChange2(int money, ArrayList<Integer> coins){
		int a[] = new int[money+1];
		int b[] = new int[money+1];
		ArrayList<Integer> sets = new ArrayList<Integer>(); 
		a[0] = 0;	//set the first one 0
		for (int i = 1; i < money+1;i++){		//initialize all
			a[i]=Integer.MAX_VALUE-1;
			b[i]= -1;
		}
		
		for (int i = 0; i < coins.size();i++){
			for (int j = 1 ; j <= money; j++){
				if(j>=coins.get(i)){
					if(a[j-coins.get(i)]+1<a[j]){		//find the min
						a[j]=1 +a[j-coins.get(i)];
						b[j]=i;
					}
				}
			}
		}
		
		if(a[money]==Integer.MAX_VALUE-1){
			return sets;
		}
		//backtrack
		int index = money;
		while (index != 0){
			int index2 = b[index];
			sets.add(coins.get(index2));
			index=index-coins.get(index2);
		}
		return sets;
		
	}

	/**
	 * this method finds and returns the smallest set of coins to change a given amount
of money. 
	 * @param money
	 * @param coins	The coins parameter contains the denominations of coins available.
	 * @return sets The array of coins to get the money
	 */
	public static ArrayList<Integer> coinChange3(int money, ArrayList<Integer> coins){
		int index = coins.size()-1;
		Collections.sort(coins);		//sort the coins array
		//System.out.println(coins);
		ArrayList<Integer> sets = new ArrayList<Integer>();
		int sum = 0;
		while (sum!=money){
			if(index<0){
				System.out.println("no solution");
				break;
			}
			if(sum+coins.get(index)<=money){	//find the largest item such that sum + coins.get(index) < money
				sum+=coins.get(index);
				sets.add(coins.get(index));
			}
			else if (index >= 0){
				index--;
				
			}
			//System.out.println(index);
			
		}
		if(sum == money){
			return sets;
		}
		else{	//if no solution , return an empty set.
			ArrayList<Integer> a = new ArrayList<Integer>();
			return a;
		}
	}


}
