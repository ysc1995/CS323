//i did this homework alone, with only this semester's material.  Shaocheng Yang

package assignment7;

import java.util.ArrayList;
import java.util.List;

public class Rodcut {

	public static void main(String[] args) {
		List<Double> prices = new ArrayList<Double>();
		double pricelist[] = {1,5,8,9,10,17,17,20,24,30};
		for(int i = 0 ; i < pricelist.length; i++){
			prices.add(pricelist[i]);
		}
		List<Integer> resultcuts = new ArrayList<Integer>();
		System.out.println("rod cut 1 with distance from 1 to 10 using book's example:");
		System.out.println(rodCut1(1,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut1(2,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut1(3,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut1(4,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut1(5,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut1(6,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut1(7,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut1(8,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut1(9,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut1(10,prices,resultcuts));
		System.out.println(resultcuts);
		
		System.out.println();
		System.out.println();
		System.out.println();

		System.out.println("rod cut 2 with distance from 1 to 10 using book's example:");
		System.out.println(rodCut2(1,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut2(2,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut2(3,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut2(4,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut2(5,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut2(6,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut2(7,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut2(8,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut2(9,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut2(10,prices,resultcuts));
		System.out.println(resultcuts);
		
		System.out.println();
		System.out.println();
		System.out.println();

		System.out.println("rod cut 3 with distance from 1 to 10 using book's example:");
		System.out.println(rodCut3(1,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut3(2,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut3(3,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut3(4,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut3(5,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut3(6,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut3(7,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut3(8,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut3(9,prices,resultcuts));
		System.out.println(resultcuts);
		System.out.println(rodCut3(10,prices,resultcuts));
		System.out.println(resultcuts);
	}
	

	
	
	/**
	 * This method uses the recursive method to find the most profitable set of cuts for a rod of a given length
	 * @param length
	 * @param prices
	 * @param resultCuts
	 * @return q (profit)
	 */
	private static double rodCut1(int length, List<Double> prices, List<Integer> resultCuts) {
		double q =Double.NEGATIVE_INFINITY;
	
		if(length==0){	//if cut distance is 0, return 0
			
			return 0;
		}

		else{
			for(int i = 1 ; i < length+1; i ++){
				List<Integer> helper = new ArrayList<Integer>();	//create a new array for each recursive call
				
				double curr = prices.get(i-1)+rodCut1(length-i,prices,helper);
				
				if(i+1==length){	//do not need 0 distance for result
					helper.add(i);
				}
				
				//q = max(q,prices.get(i-1)+rodCut1help(length-i,prices));
				if(q<curr){
					q=curr;
					resultCuts.clear();
					if(i+1 != length){		//add cut distance into resultcuts
						resultCuts.add(i);
					}
					for(int j =0; j < helper.size();j++){
						resultCuts.add(helper.get(j));
					}
					
				}
				
			}
		}

		return q;
	
	}
	
	/**
	 * this method uses top-down recursion with memorization to find the most profitable set of cuts for a rod of a given length.
	 * @param length
	 * @param prices
	 * @param resultCuts
	 * @return	q (profit)
	 */
	public static double rodCut2(int length, List<Double> prices, List<Integer> resultCuts){
		
		double[] r = new double[length+2];
		for(int i = 0; i < length+2 ; i++){
			r[i]= Double.NEGATIVE_INFINITY;
		}
		
		
		return memoizedcutrodaux(prices,length+1,r,resultCuts);
	}




	private static double memoizedcutrodaux(List<Double> prices, int length, double[] r,List<Integer> resultCuts){
		double q;
		if(r[length]>=0){	//length's profit has been recorded
			return r[length];
			
		}
		
		if(length-1==0){	//length 0 has profit 0
		
			return 0;
		}
		
		else{
			q=Double.NEGATIVE_INFINITY;
		
		
			for(int i = 1; i < length; i ++){
				List<Integer> helper = new ArrayList<Integer>();
				double curr = prices.get(i-1)+memoizedcutrodaux(prices,length-i,r,resultCuts);
				
				if (q<curr){
					q = curr;
					
					resultCuts.clear();
					resultCuts.add(i);
					for(int j =0; j < helper.size();j++){
						resultCuts.add(helper.get(j));
						
					}
					if(length-resultCuts.get(0)-1!= 0){
				    	resultCuts.add(length-resultCuts.get(0)-1);
				    }
				}
			
			}
			
			
		}
		r[length]=q;		//set the profit for given length
		return q;
		
	}


	/**
	 * this method uses bottom-up iteration with memorization to find the most profitable set of cuts for a rod of a given length.
	 * @param length
	 * @param prices
	 * @param resultCuts
	 * @return r[length] (profit)
	 */
	public static double rodCut3(int length, List<Double> prices, List<Integer> resultCuts){
		int [] helper = new int[length+1];
		double r[] = new double[length+1];
		r[0] = 0;
		for(int j = 1 ; j < length+1;j++){
			double q = Double.NEGATIVE_INFINITY;
			for (int i = 1; i < j+1; i++){
				double curr = prices.get(i-1)+r[j-i];
				if (q<curr){
					q = curr;
					helper[j]=i;
				}
			}
			r[j]=q;
			
		}
		
		resultCuts.clear();
		int length2 = length;
		while(length2>0){
			resultCuts.add(helper[length2]);
			length2 = length2-helper[length2];
		}
		return r[length];
		
	}



//	private static double max(double q, double d) {
//		if(q>d){
//			return q;
//		}
//		return d;
//	}


}
