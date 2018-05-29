package assignment2;
//i did this homework alone, with only this semester's material
//Shaocheng Yang

public class Heap {

	public static void main(String[] args) {
		int n =7;
		String [] x = new String [n];
		
		
		x[4]="4";
		x[0]="0";
		x[6]="6";
		x[3]="3";
		x[2]="2";
		x[1]="1";
		x[5]="5";
		System.out.println("String Array from 0 to 6, with 7 number of nodes been used.");
		System.out.println("build min heap:");
		buildMinHeap(x,n);
		System.out.println(toTreeString(x,n));
		System.out.println("build max heap:");
		buildMaxHeap(x,n);
		System.out.println(toTreeString(x,n));
		
		System.out.println("We want to add '8' into the heap:");
		System.out.print("Can we add it? ");
		System.out.println(addToHeap("8", x ,n));
		System.out.println("Print the heap:");
		System.out.println(toTreeString(x,n));

		System.out.println("Now create a new string array with 8 positions but only using 6 now.");
		int m = 6;
		String [] y = new String[8];
		y[0]="5";
		y[1]="4";
		y[2]="3";
		y[3]="2";
		y[4]="1";
		y[5]="0";
		System.out.println("build max heap:");
		buildMaxHeap(y,m);
		System.out.println("Print the heap:");
		System.out.println(toTreeString(y,m));
		System.out.println("We want to add '8' into the heap:");
		System.out.print("Can we add it? ");
		if(addToHeap("8",y,m)){
			System.out.println("Yes!");
			System.out.println("Print the heap:");
			System.out.println(toTreeString(y,m+1));
		}
		else{
			System.out.println("No!");
			System.out.println("Print the heap:");
			System.out.println(toTreeString(y,m));
		}
		
		System.out.println("build min heap:");
		buildMinHeap(y,m+1);
		System.out.println("Print the heap:");
		System.out.println(toTreeString(y,m+1));
		System.out.println("We want to add '9' into the heap:");
		System.out.print("Can we add it? ");
		if(addToHeap("9",y,m+1)){
			System.out.println("Yes!");
			System.out.println("Print the heap:");
			System.out.println(toTreeString(y,m+2));
		}
		else{
			System.out.println("No!");
			System.out.println("Print the heap:");
			System.out.println(toTreeString(y,m+1));
		}
		
		
	}
	
	/**
	 * this method calls method MxHeapify to build a max heap
	 * @param x	String array
	 * @param n	number of values actually used in x
	 */
	public static void buildMaxHeap(String[] x, int n){
		for (int i = (n+1)/2;i>=0;i--){
			//check all the non-leave nodes
			MaxHeapify(x,n, i);
		}
	}


	public static void MaxHeapify(String[] x,int n, int i) {
		int l = left(i);		//left child and right child
		int r = right(i);
		int largest;			//largest node's index
		
		//find the largest node in a triangle 
		if (l<=n-1 && x[l].compareTo(x[i])>0){	
			largest = l;
		}
		else {
			largest = i;
		}
		if (r<=n-1 && x[r].compareTo(x[largest])>0){
			largest = r;
		}
		if(largest != i){		//switch x[i] and x[largest]
			String temp;
			temp = x[i];
			x[i] = x[largest];
			x[largest]= temp;
			MaxHeapify(x,n,largest);
		}
		
	}
	/**
	 * this method is similar to buildMaxHeap
	 * @param x
	 * @param n
	 */
	public static void buildMinHeap(String [] x, int n){
		for ( int i = (n+1)/2; i >= 0; i --){
			MinHeapify(x,n,i);
		}
	}


	public static void MinHeapify(String[] x, int n, int i) {
		int l = left(i);
		int r = right(i);
		int smallest;
		
		if ( l <= n-1 && x[l].compareTo(x[i])<0){
			smallest = l;
		}
		else {
			smallest = i;
		}
		if (r<=n-1 && x[r].compareTo(x[smallest])<0){
			smallest = r;
		}
		if (smallest != i){
			String temp;
			temp = x[i];
			x[i] = x[smallest];
			x[smallest]= temp;
			MinHeapify(x,n,smallest);
		}
	}
	
	
	/**
	 * this method add a string s to the string array x
	 * this method detects if x is max heap or a min heap
	 * if there is not enough information to determine, it is a min heap
	 * @param s	string need to be added
	 * @param x	string array
	 * @param n number of values in x
	 * @return true for successfully added, false for not successfully added
	 */
	public static boolean addToHeap(String s, String[] x, int n){
		boolean ismaxheap = false;		//default min heap
		if (x.length == n){		//if the array is full, s can't be added
								//return false 
			return false;
		}
		
		if (x[0].compareTo(x[1])>0){	
			ismaxheap = true;
		}
		
		if (ismaxheap){					//if it is a max heap 
			addmaxheap(s,x,n);
		}
		else{							//if it is a min heap
			addminheap(s,x,n);
		}
		
		return true;
	}


	/**
	 * this method adds the string s to the last position to the heap
	 * compare to its parent and switch
	 * @param s
	 * @param x
	 * @param n
	 */
	private static void addminheap(String s, String[] x, int n) {
		x[n]=s;
		while (n>0&&x[n].compareTo(x[parent(n)])<0){
			String temp;
			temp = x[n];
			x[n] = x[parent(n)];
			x[parent(n)]= temp;
			n = parent(n);
		}
		
	}

	/**
	 * similar to addminheap
	 * @param s
	 * @param x
	 * @param n
	 */
	private static void addmaxheap(String s, String[] x, int n) {
		x[n]=s;
		while (n>0&&x[parent(n)].compareTo(x[n])<0){
			String temp;
			temp = x[n];
			x[n] = x[parent(n)];
			x[parent(n)]= temp;
			n = parent(n);
		}
		
		
	}
	
	public static String toTreeString(String[] x, int n){
		return toTreeString(x,n,0);
	}


	private static String toTreeString(String[] x, int n, int i) {
		if (i >= n){
			return "";
		}
		else{
			return "(" + x[i] + toTreeString(x,n,left(i)) + toTreeString(x,n,right(i))+")";
		}
		
	}


	private static int right(int i) {			//right child's index
		
		return 2*i+2;
	}


	private static int left(int i) {			//left child's index
		
		return 2*i+1;
	}
	
	private static int parent(int i){			//parent's index
		
		return ((i+1)/2)-1;
	}
	
	
}

