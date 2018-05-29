package assignment2;

import java.util.Random;

//i did this problem alone, with only this semester's material
//Shaocheng Yang

public class ComparisonSorting {

	public static void main(String[] args) {
		String[] x = randomArray(3,4);
		for (int i = 0; i <3 ; i++){
			System.out.println(x[i]);
		}
		int n =250;
		System.out.println(measureHeapSort(n));
		System.out.println(measureMergeSort1(n));
		System.out.println(measureMergeSort2(n));
		System.out.println(measureSelectionSort(n));
	}
	/**
	 * this method creates and returns an array of n random strings of m random alphanumeric characters each (
	 * @param n
	 * @param m
	 * @return String array x
	 */
	public static String[] randomArray(int n, int m){
		String string = "qwertyuioplkjhgfdaszxcvbnmQWERTYUIOPKLJHGFDSAZXCVBNM0123456789";
		Random random = new Random();
		String[] x= new String[n];
		for(int i = 0; i<n;i++){
			String tmp = "" ;
			for (int j = 0; j<m;j++){
				tmp = tmp+string.charAt(random.nextInt(62));
			}
			x[i] = tmp.toString();
		}
		
		return x;
		
	}
	/**
	 * this method measure the time for the computer to do heapsort 1000 times for n values in second;
	 * @param n
	 * @return time 
	 */
	public static double measureHeapSort(int n){
		double time = 0;
		for (int i = 0; i < 1000; i++){
			String[] x = randomArray(n,5);
			double starttime = System.currentTimeMillis();
			Sort.heapSort(x, n, true);
			double endtime = System.currentTimeMillis();
			time = time+ endtime - starttime;
		}
		time = time/ 1000; 		//convert time to second 
		return time;
	}
	
	/**
	 * this method measure the time for the computer to do top-down mergesort 1000 times for n values in second;
	 * @param n
	 * @return time 
	 */
	public static double measureMergeSort1(int n){
		double time = 0;
		for (int i = 0; i < 1000; i++){
			String[] x = randomArray(n,5);
			double starttime = System.currentTimeMillis();
			Sort.mergeSort1(x, n, true);
			double endtime = System.currentTimeMillis();
			time = time+ endtime - starttime;
		}
		time = time/ 1000; 		//convert time to second 
		return time;
	}
	
	/**
	 * this method measure the time for the computer to do bottom-up mergesort 1000 times for n values in second;
	 * @param n
	 * @return time 
	 */
	public static double measureMergeSort2(int n){
		double time = 0;
		for (int i = 0; i < 1000; i++){
			String[] x = randomArray(n,5);
			double starttime = System.currentTimeMillis();
			Sort.mergeSort2(x, n, true);
			double endtime = System.currentTimeMillis();
			time = time+ endtime - starttime;
		}
		time = time/ 1000; 		//convert time to second 
		return time;
	}
	
	/**
	 * this method measure the time for the computer to do selection sort 1000 times for n values in second;
	 * @param n
	 * @return time 
	 */
	public static double measureSelectionSort(int n){
		double time = 0;
		for (int i = 0; i < 1000; i++){
			String[] x = randomArray(n,5);
			double starttime = System.currentTimeMillis();
			Sort.selectionSort(x, n, true);
			double endtime = System.currentTimeMillis();
			time = time+ endtime - starttime;
		}
		time = time/ 1000; 		//convert time to second 
		return time;
	}
	

}
