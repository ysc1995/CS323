package assignment2;
//i did this homework alone, with only this semester's material
//Shaocheng Yang


public class Sort {

	public static void main(String[] args) {
		String [] x = new String[5];
		x[0]="2";
		x[1]="4";
		x[2]="1";
		x[3]="5";
		x[4]="0";
		for (int i = 0; i<5;i++){
			System.out.print(x[i]+" ");
		}
		System.out.println("descending selection sort:");
		selectionSort(x,5,true);
		for (int i = 0; i<5;i++){
			System.out.print(x[i]+" ");
		}
		System.out.println();
		
		
		x[0]="2";
		x[1]="4";
		x[2]="1";
		x[3]="5";
		x[4]="0";
		for (int i = 0; i<5;i++){
			System.out.print(x[i]+" ");
		}
		System.out.println("increasing selection sort:");
		selectionSort(x,5,false);
		for (int i = 0; i<5;i++){
			System.out.print(x[i]+" ");
		}
		System.out.println();
		
		x[0]="2";
		x[1]="4";
		x[2]="1";
		x[3]="5";
		x[4]="0";
		for (int i = 0; i<5;i++){
			System.out.print(x[i]+" ");
		}
		System.out.println("increasing top-down merge sort:");
		mergeSort1(x,5,false);
		for (int i = 0; i<5;i++){
			System.out.print(x[i]+" ");
		}
		System.out.println();
		
		x[0]="2";
		x[1]="4";
		x[2]="1";
		x[3]="5";
		x[4]="0";
		for (int i = 0; i<5;i++){
			System.out.print(x[i]+" ");
		}
		System.out.println("decreasing top-down merge sort:");
		mergeSort1(x,5,true);
		for (int i = 0; i<5;i++){
			System.out.print(x[i]+" ");
		}
		System.out.println();
		
	
		x[0]="2";
		x[1]="4";
		x[2]="1";
		x[3]="5";
		x[4]="0";
		for (int i = 0; i<5;i++){
			System.out.print(x[i]+" ");
		}
		System.out.println("increasing bottom-up merge sort:");
		mergeSort2(x,5,false);
		for (int i = 0; i<5;i++){
			System.out.print(x[i]+" ");
		}
		System.out.println();
		
		x[0]="2";
		x[1]="4";
		x[2]="1";
		x[3]="5";
		x[4]="0";
		for (int i = 0; i<5;i++){
			System.out.print(x[i]+" ");
		}
		System.out.println("decreasing bottom-up merge sort:");
		mergeSort2(x,5,true);
		for (int i = 0; i<5;i++){
			System.out.print(x[i]+" ");
		}
		System.out.println();
		
		
		x[0]="2";
		x[1]="4";
		x[2]="1";
		x[3]="5";
		x[4]="0";
		for (int i = 0; i<5;i++){
			System.out.print(x[i]+" ");
		}
		System.out.println("increasing heap sort:");
		heapSort(x,5,false);
		for (int i = 0; i<5;i++){
			System.out.print(x[i]+" ");
		}
		System.out.println();
	
		x[0]="2";
		x[1]="4";
		x[2]="1";
		x[3]="5";
		x[4]="0";
		for (int i = 0; i<5;i++){
			System.out.print(x[i]+" ");
		}
		System.out.println("decreasing heap sort:");
		heapSort(x,5,true);
		for (int i = 0; i<5;i++){
			System.out.print(x[i]+" ");
		}
		System.out.println();
	}
	/**
	 * this method makes the string array x to a heap
	 * then based on the descending behavior to choose the max heap or min heap
	 * after building the heap, we could make sure that the first one is the biggest or smallest of the array
	 * we switch the first to the last value of the array, and do max heap or min heap again
	 * find the max or min value and do the above again until all values are sorted 
	 * @param String array x
	 * @param n number of values used in x
	 * @param descending  if descending is true,the elements are sorted high to low; otherwise they are sorted low to high.
	 */
	 public static void heapSort(String[] x, int n, boolean descending){
		 if (!descending){
			 Heap.buildMaxHeap(x,n);
			 
			 for (int i = n -1 ; i > 0 ; i --){
				 String helper = x[0];		//switch the first and the i th value in the string array 
				 x[0] = x[i];
				 x[i] = helper; 
				 
				 Heap.MaxHeapify(x,i,0);			//do the max heapify for the first i values
				 
			 }
		 }
		 else {
			 Heap.buildMinHeap(x,n);
			 
			 for (int i = n -1 ; i > 0 ; i --){
				 String helper = x[0];		//switch the first and the i th value in the string array 
				 x[0] = x[i];
				 x[i] = helper; 
				 
				 Heap.MinHeapify(x,i,0);			//do the min heapify for the first i values
				 
			 }
			 
			 
		 }
	 }
	 
	 
	 /**
	  * this method is a top-down merge sort
	  * cited from professor cheung's class note
	  * @param x	string array
	  * @param n	number of values used in string array
	  * @param descending	if descending is true,the elements are sorted high to low; otherwise they are sorted low to high.
	  */
	 public static void mergeSort1(String[] x, int n, boolean descending){
		 String [] right;
		 String [] left;
		 if (n==1){
			 //no need to sort!
			 return;
		 }
		 
		 
		 int mid = n/2;		//divide the array into 2 arrays
		 left = new String[mid];
		 
		 for (int  i = 0; i<mid;i++){
			 left[i]=x[i];
		 }
		 
		 right = new String [n-mid];
		 
		 for (int i = 0; i<n-mid;i++){
			 right[i]=x[i+mid];
		 }
		 
		 // do merge sort for each part
		 mergeSort1(left,left.length,descending);
		 mergeSort1(right,right.length,descending);
		 // merge them together
		 merge(x,left,right,descending);
		 
		 
		 
		 
		 
	 }
	 
	 
	 
	 
	 
	 /**
	  * this method is from professor cheung's classnote
	  * this method merges two sorted arrays
	  * @param result string array
	  * @param a	string array
	  * @param b	string array
	  * @param descending	if descending is true,the elements are sorted high to low; otherwise they are sorted low to high.
	  */
	 public static void merge(String[] result, String[] a, String[] b, boolean descending)      
	   {
	      int i, j, k;

	      i = j = k = 0;
	      
	      if(!descending){

	    	  while ( i < a.length || j < b.length )
	    	  {
	    		  if ( i < a.length && j < b.length )
	    		  { 				 // when both array have elements
	    			  if ( a[i].compareTo(b[j])<0)
	    				  result[k++] = a[i++];
	    			  else
	    				  result[k++] = b[j++];
	    		  }
	    		  else if ( i == a.length )
	    			  result[k++] = b[j++];     // a is empty
	    		  else if ( j == b.length )
	    			  result[k++] = a[i++];     // b is empty
	    	  }
	      }
	      else {
	    	  while ( i < a.length || j < b.length )
	    	  {
	    		  if ( i < a.length && j < b.length )
	    		  { 				 // when both array have elements
	    			  if ( a[i].compareTo(b[j])>0)
	    				  result[k++] = a[i++];
	    			  else
	    				  result[k++] = b[j++];
	    		  }
	    		  else if ( i == a.length )
	    			  result[k++] = b[j++];     // a is empty
	    		  else if ( j == b.length )
	    			  result[k++] = a[i++];     // b is empty
	    	  }
	      }
	   }
	 
	 /**
	  * this method is a bottom up merge sort
	  * cited from professor cheung's class note
	  * @param x	String array 
	  * @param n	number of values used in array
	  * @param descending	if descending is true,the elements are sorted high to low; otherwise they are sorted low to high.
	  */
	 public static void mergeSort2(String[] x, int n, boolean descending){
		 String [] temp = new String[n];
		 int wid;
		 int i;
		 if(!descending){
			 for (wid = 1; wid < 2*n; wid = 2*wid){
				 for (i = 0; i < n ; i = i + 2*wid){
					 int left = i;
					 int mid;
					 int right;
					 if(i+wid<n){
						 mid = wid+i;
					 }
					 else{
						 mid = n;
					 }
					 if(i+2*wid<n){
						 right = i+2*wid;
					 }
					 else{
						 right = n;
					 }
					 
					 int first1 = left;
					 int last1 = mid;
					 int first2 = mid;
					 int last2 = right;			//divided the array into 2
					 
					 while (first1<last1 && first2 < last2){
						 //if both arrays are not empty
						 //find the smaller one and add to the array temp
						 if(x[first1].compareTo(x[first2])<0){
							 temp[left]= x[first1];
							 left++;
							 first1++;
						 }
						 else{
							 temp[left]=x[first2];
							 left++;
							 first2++;
						 }
					 }
					 
					 while (first1 < last1){
						 //the second array is empty
						 temp[left]=x[first1];
						 left++;
						 first1++;
					 }
					 
					 while (first2 < last2){
						 //the first array is empty
						 temp[left]=x[first2];
						 left++;
						 first2++;
					 }
					 
				 }
				 //update x
				 String[] t = x;
				 x = temp;
				 temp = t;
				 
			 }
		 }
		 
		 else{
			 for (wid = 1; wid < 2*n; wid = 2*wid){
				 for (i = 0; i < n ; i = i + 2*wid){
					 int left = i;
					 int mid;
					 int right;
					 if(i+wid<n){
						 mid = wid+i;
					 }
					 else{
						 mid = n;
					 }
					 if(i+2*wid<n){
						 right = i+2*wid;
					 }
					 else{
						 right = n;
					 }
					 
					 int first1 = left;
					 int last1 = mid;
					 int first2 = mid;
					 int last2 = right;			//divided the array into 2
					 
					 while (first1<last1 && first2 < last2){
						 //if both arrrays are not empty
						 //find the smaller one and add to the array temp
						 if(x[first1].compareTo(x[first2])>0){
							 temp[left]= x[first1];
							 left++;
							 first1++;
						 }
						 else{
							 temp[left]=x[first2];
							 left++;
							 first2++;
						 }
					 }
					 
					 while (first1 < last1){
						 //the second array is empty
						 temp[left]=x[first1];
						 left++;
						 first1++;
					 }
					 
					 while (first2 < last2){
						 //the first array is empty
						 temp[left]=x[first2];
						 left++;
						 first2++;
					 }
					 
				 }
				 //update x
				 String[] t = x;
				 x = temp;
				 temp = t;
				 
			 }
		 }
	 }
	 


	/**
	  * This method finds the maximum or minimum value of the array and switch it to the front.
	  * Then finds the second maximum or minimum value and put it to the second position.
	  * Do this many times until the array is sorted.
	  * @param String array x 
	  * @param n number of values used in x
	  * @param descending  if descending is true,the elements are sorted high to low; otherwise they are sorted low to high.
	  */
	 public static void selectionSort(String[] x, int n, boolean descending){
		 
		 if (descending){
			 
			 for (int i = 0; i < n;i++){
				 String temp = x[i];
				 for (int j = i+1; j < n ; j++){
					 if (temp.compareTo(x[j])<0){
						 temp=x[j];
						 x[j]=x[i];
						 x[i]=temp;
					 }
				 }
			 }
		 }
		 else{
			 for (int i = 0; i < n;i++){
				 String temp = x[i];
				 for (int j = i+1; j < n ; j++){
					 if (temp.compareTo(x[j])>0){
						 temp=x[j];
						 x[j]=x[i];
						 x[i]=temp;
					 }
				 }
			 }
		 }
		 
	 }

}


