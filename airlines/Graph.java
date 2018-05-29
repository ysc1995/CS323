package assignment6;
//I did this homework alone, with only this semester's material.
//Shaocheng Yang

import java.util.*;

public class Graph {

	Map<String, Vertex> vertices;
	
	Map<String, ArrayList<Edge>> adjacent;
	
	List<String> searchSteps;
	public static void main(String[] args){
		//all the vertices are connected undirected, so addedge method is called twice on both side for the same edge.

		
		
		Graph graph = new Graph();
		graph.addEdge("a", "b", 3);
		graph.addEdge("a", "c", 4);
		graph.addEdge("c", "b", 2);
		graph.addEdge("b", "c", -1);
		graph.addEdge("c", "d", 7);
		graph.addEdge("b", "d", 4);
		graph.addEdge("d", "e", -3);
		graph.addEdge("c", "e", 5);
		
		System.out.println(graph);
		System.out.println("no negative edge?");
		System.out.println(graph.bellmanFord("a"));
		for(String key: graph.vertices.keySet()){
			System.out.println("path from 'a' to " + key + " :"+ graph.path("a", key));
		}
		
		Graph g = new Graph();
		g.addEdge("a", "b", 3);
		g.addEdge("a", "c", 4);
		g.addEdge("c", "b", 2);
		g.addEdge("b", "c", 1);
		g.addEdge("c", "d", 7);
		g.addEdge("b", "d", 4);
		g.addEdge("d", "e", 3);
		g.addEdge("c", "e", 5);
		
		System.out.println(g);
		System.out.println("no negative edge?");
		System.out.println(g.dijkstra("a"));
		for(String key: g.vertices.keySet()){
			System.out.println("path from 'a' to " + key + " :"+ g.path("a", key));
		}
		
		
		

	}
	
	

	public Graph() {
		//create a graph with a treemap of all the vertices, a hashmap for all the adjacent node, and a array for searchSteps
		vertices = new TreeMap<String, Vertex>();
		adjacent = new HashMap<String, ArrayList<Edge>>();
		searchSteps = new ArrayList<String>();
	}

	public void addVertex(String key) {
		//add a new vertex to the graph
		Vertex v = new Vertex(key);
		vertices.put(key, v);
		adjacent.put(key, new ArrayList<Edge>());
	}
	
	public void addEdge(String source, String target, double weight) {
		//add a edge between two vertices
		//if any vertex is not in the graph, add it to the graph
		if (!vertices.containsKey(source)) {
			addVertex(source);
		}
		if (!vertices.containsKey(target)) {
			addVertex(target);
		}
		//get source's adjacent list
		ArrayList<Edge> edges = adjacent.get(source);
		Edge e = new Edge(vertices.get(source), vertices.get(target), weight);
		edges.add(e);
		//add edge to source's adjacent list
	}

	/**
	 * this method edits all the vertices and edge in the Graphviz DOT format.
	 */
	public String toString() {
		
		StringBuilder s = new StringBuilder();
		s.append("digraph{\n");
		//edit all vertices 
		
		for (String key : vertices.keySet()) {
			String color = "";
			if(vertices.get(key).color==0){
				color+=",style=dashed";
			}
			if(vertices.get(key).color==1){
				color+=",style=filled,fillcolor=gray";
			}
			if(vertices.get(key).color==2){
				color+=",style=bold";
			}
			s.append(vertices.get(key)+" [label=\""+vertices.get(key)+"\"" + color+""+"]"+"\n");
		}
		
		//edit all edges 
		
		for (String key : vertices.keySet()) {
			ArrayList<Edge> edges = adjacent.get(key);
			for (Edge e : edges) {
				s.append(e.source+"->"+e.target+" [label=\""+e.weight+"\"]" + "\n");
			}
		}
		s.append("\n}");
		return s.toString();
	}
	
	/**
	 * this method does the bfs algorithm to search the map
	 * if the vertex has not been searched, it is white
	 * if the vertex is currently being searched, it is gray
	 * if the vertex is searched, it is black 
	 * @param startVertex
	 */
	public void breadthFirstSearch(String startVertex) {
		searchSteps.clear();
		Vertex sv = vertices.get(startVertex);
		//find the startvertex
		for (String key:vertices.keySet()){
			Vertex u = vertices.get(key);
			if(!sv.equals(u)){
				u.color=u.WHITE;
				u.distance=Double.POSITIVE_INFINITY;
				u.parent=null;
			}
		}
		this.searchSteps.add(toString());
		sv.color=sv.GRAY;
		this.searchSteps.add(toString());
		sv.distance=0;
		sv.parent=null;
		sv.discoverStep=this.searchSteps.size();
		
		ArrayDeque<Vertex> q = new ArrayDeque();
		q.push(sv);
		while(!q.isEmpty()){
			Vertex u = q.pollLast();
			for(Edge x :adjacent.get(u.label)){
				Vertex v = x.target;
				if(v.color==v.WHITE){
					v.color=v.GRAY;
					this.searchSteps.add(toString());
					v.distance=u.distance+1;
					v.parent=u;
					v.discoverStep=this.searchSteps.size();
					q.push(v);
				}
			}
			u.color=u.BLACK;
			this.searchSteps.add(toString());
			u.finishStep=this.searchSteps.size();
		}
	}
	
	/**
	 * this method does the DFS algorithm
	 * first initial all the vertices to be white
	 * then check each vertex's adjacency list 
	 * search through the adjacency list
	 * then goes to the next vertex
	 */
	public void depthFirstSearch() {
		this.searchSteps.clear();
		//set all white
		for(String key: vertices.keySet()){
			Vertex u = vertices.get(key);
			u.color=u.WHITE;
			u.parent=null;
		}
		
		this.searchSteps.add(this.toString());
		for(String key:vertices.keySet()){
			Vertex u = vertices.get(key);
			if(u.color==u.WHITE){
				DFSVisit(u);
			}
		}
		
		
	}
	
	private void DFSVisit(Vertex u) {
		//set gray when in the searching array
		u.color=u.GRAY;
		searchSteps.add(toString());
		u.discoverStep=searchSteps.size();
		for(Edge edge : adjacent.get(u.label)){
			Vertex v = edge.target;
			//if not searched 
			//set its parent to be u and do the DFS
			if (v.color==v.WHITE){
				v.parent=u;
				v.distance=u.distance+edge.weight;
				DFSVisit(v);
			}
		}
		//set black when searched 
		u.color=u.BLACK;
		searchSteps.add(toString());
		u.finishStep=searchSteps.size();
		
	}

	/**
	 * this method finds the path between two vertices: startVertex and endVertex
	 * it back traces from the end vertex to start vertex by tracing its parent 
	 * @param startVertex
	 * @param endVertex
	 * @return	ArrayList path
	 */
	public List<String> path(String startVertex, String endVertex) {
		List<String> path = new ArrayList<String>();
		ArrayDeque<String> phelper = new ArrayDeque<String>();
		Vertex start = vertices.get(startVertex);
		Vertex end = vertices.get(endVertex);
		if (start==null||end==null){
			//if start or end vertex is not in the map
			System.out.println("start or end vertex not found.");
			return path;
		}
		if (end.parent==null){
			//if end vertex is not been searched
			System.out.println("end vertex is not been searched");
			return path;
		}
		//trace back from the end vertex to start vertex by finding its parent
		Vertex curr = end;
		while(!curr.equals(start)){
			
			phelper.push(curr.toString());
			curr=curr.parent;
		}
		//add the start vertex to the array
		path.add(start.toString());
		//copy it to path
		while(!phelper.isEmpty()){
			path.add(phelper.pop());
		}
		return path;
	}
	
	/**
	 * this method finds the path weight in the path between two vertices startVertex and endVertex
	 * First find the path 
	 * then start from the first vertex in the path, find the weight to the next vertex in the path and add the path to pathweight
	 * @param startVertex
	 * @param endVertex
	 * @return pweight (pathweight) if the path is not exist, return infinity
	 */
	public double pathWeight(String startVertex, String endVertex) {
		List<String> path = this.path(startVertex, endVertex);
		double pweight = 0;
		if(path.size()==0){
			//if there is no path
			return Double.POSITIVE_INFINITY;
		}
		else{
			for(int i = 0; i < path.size()-1;i++){
				String curr = path.get(i);
				ArrayList<Edge> edge = adjacent.get(curr);
				for(Edge j : edge){
					String curr2 = path.get(i+1);
					if(j.target.label.equals(curr2)){
						pweight=pweight+j.weight;
					}
				}
			}
			return pweight;
		}
	}
	
	public List<String> topologicalSort() {
		List<String> sortlist = new ArrayList();
		String [] a = new String[searchSteps.size()+1];
		
		this.depthFirstSearch();	//do the dfs first
		//based on the finished step, insert the vertices into array
		for(String vert:this.vertices.keySet()){
			a[this.vertices.get(vert).finishStep]=vert;
		}
		//put into the sortlist
		for (int i = 0; i < a.length;i++){
			if(a[i]!=null){
				sortlist.add(a[i]);
			}
		}
		
		return sortlist;
	}	
	
	
	
	//-----------------------------------hw04

	
	
	
	
	/**
	 * this method calculates all the weights of all the edges in the graph
	 * by finding all the edges and adding their weights
	 * @return double totalWeight
	 */
	public double totalWeight(){
		double totalWeight = 0;
		for(String key: vertices.keySet()){
			ArrayList<Edge> edges = adjacent.get(key);
			
			for(Edge e: edges){
				totalWeight += e.weight;
			}
		}
		return totalWeight/2;
	}
	
	
	
	/**
	 * this method use Prim's algorithm to find the minimum spanning tree 
	 * poll the current vertex's adjacent edge to the queue and find the minimum weight edge and update current vertex as the edge's target
	 * @param root
	 * @return	the minimum spanning tree
	 */
	public Graph prim(String root){
		Vertex start = vertices.get(root);
		
		int n = 0;
		for(String key : vertices.keySet()){
			//find the number of vertex and set them all unlinked
			n++;
			vertices.get(key).linked=false;
			
		}
		
		start.linked=true;
		
		Stack<Edge> edgestack = new Stack<Edge>();
		
		Queue<Edge> edgequeue = new ArrayDeque();
		Queue<Edge> edgequeuecopy = new ArrayDeque();

		
		int i  = 1;
		while (i<n){
			i++;
			
			Edge minedge = new Edge(null, null, Double.POSITIVE_INFINITY);
			ArrayList<Edge> edges = adjacent.get(start.label);
		
			for (Edge e: edges) {
				//add all the adjacent edges to the array
				edgequeue.add(e);
				
				
			}


			while(!edgequeue.isEmpty()){
				Edge curr = edgequeue.poll();
				
			
				if(curr.weight<minedge.weight&&curr.target.linked == false){
					//check if it is minimum weight edge and if its target is not linked
					edgequeuecopy.add(minedge);
					minedge=curr;
				}
				else{
					edgequeuecopy.add(curr);
					
				}
			}
			
			
			while(!edgequeuecopy.isEmpty()){
				//add all the edges back except the minimum edge 
				edgequeue.add(edgequeuecopy.poll());
			}
			
			start = minedge.target;
			//update current vertex
			minedge.target.linked=true;
			//set linked 
			edgestack.push(minedge);
			//record the edge
		}
	
		
		Graph a = new Graph();
		
		while(!edgestack.isEmpty()){
			//create the graph
			Edge curredge = edgestack.pop();
			a.addEdge(curredge.source.label, curredge.target.label, curredge.weight);
			a.addEdge(curredge.target.label, curredge.source.label, curredge.weight);
		}
		
		
		return a;
		
	}
	
	
	
	


	
	//-------------------hw6
	
	/**
	 * this method uses bellman ford's algorithm to find the shortest path of the graph from a given vertex source
	 * @param source string
	 * @return false if there is a negative cycle or true if the path can be find 
	 */
	public boolean bellmanFord(String source){
		for (String key : vertices.keySet()){	//initialize all
			Vertex currvertex = vertices.get(key);
			
			currvertex.parent=null;
			currvertex.color=currvertex.WHITE;
			currvertex.distance=Double.POSITIVE_INFINITY;
			
		}
		
		Vertex headvertex = vertices.get(source);
		headvertex.distance = 0;
		for(int i = 1; i < vertices.size()-1; i++){
			for (String key : vertices.keySet()){
				Vertex currvertex = vertices.get(key);
				for(Edge e : adjacent.get(key)){
					relax(e.source, e.target,e);
				}
			}
		}
		
		for(String key : vertices.keySet()){
			for(Edge e :adjacent.get(key)){
				if(e.target.distance > e.target.distance+e.weight){
					return false;
				}
			}
		}
		
		return true;
	}



	private void relax(Vertex source, Vertex target, Edge e) {
		if(target.distance>source.distance+e.weight){
			target.distance = source.distance+e.weight;
			//set parent 
			target.parent = source;
		}
		
	}
	
	
	
	/**
	 * this method uses dijkstra's method to find the shortest path from vertex source
	 * @param source	string
	 * @return false if there is a negative edge or true if there is no negative edge
	 */
	public boolean dijkstra(String source){
		
		for (String key : vertices.keySet()){	//initialize all
			Vertex currvertex = vertices.get(key);
			
			currvertex.parent=null;
			currvertex.color=currvertex.WHITE;
			currvertex.distance=Double.POSITIVE_INFINITY;
			
		}
		Vertex headvertex = vertices.get(source);
		headvertex.distance = 0;
		
		PriorityQueue<Vertex> priq = new PriorityQueue<Vertex>();
		for(String key: vertices.keySet()){
			
			priq.add(vertices.get(key));
		}
		
		
		while(!priq.isEmpty()){
			Vertex u = priq.poll();
			for (Edge e : adjacent.get(u.label)){
				relax(e.source,e.target,e);
			}
		}
		
		for(String key : vertices.keySet()){
			for(Edge e :adjacent.get(key)){
				if(e.target.distance > e.target.distance+e.weight){
					return false;
				}
			}
		}
		return true;
		
	}
	
	
   
}

	
	
	

//create the vertex class
//each vertex represents a node 
//each vertex has a label, color, distance, parent, discoverStep, and a finishstep

class Vertex implements Comparable<Vertex>{
	
	public boolean linked;
	public static final int WHITE = 0;
	public static final int GRAY = 1;
	public static final int BLACK = 2;
	
	public Vertex p;
	public int rank;

	String label;
	
	int color;
	
	double distance;
	
	Vertex parent;
	
	int discoverStep;
	
	int finishStep;
	//create a node with its label
	public Vertex(String label) {
		this.label = label;
		color = WHITE;
		distance = Double.POSITIVE_INFINITY;
		parent = null;
		discoverStep = 0;
		finishStep = 0;
	}
	 
	
	public String toString() {
		return label;
	}
	
	public int compareTo(Vertex vertex){
		if(this.distance< vertex.distance){
			return -1;
		}
		else if(this.distance>vertex.distance){
			return 1;
		}
		else{
			return 0;
		}
	}

}

class Edge {
    //two nodes source and target with a weight between these two nodes

    Vertex source;

    Vertex target;

    double weight;

    double capacity;

    double curFlow;

    boolean visited;


    //method Edge creates the edge between two nodes and adds weight to it
    public Edge(Vertex source, Vertex target, double weight) {;
        this.source = source;
        this.target = target;
        this.weight = weight;
        this.capacity = weight;
        this.visited = false;
    }

    //toString method
    public String toString() {
        return "(" + source + " -> " + target + " : " + weight + ")";
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCurFlow(double newCurFlow) {
        this.curFlow = newCurFlow;
    }

    public double getCurFlow() {
        return this.curFlow;
    }




/**
 * this class creates the set
 * it has three methods: make a new set, union two sets and find a set
 * @author Shaocheng Yang
 *
 */
 class DisjointSet{


    public DisjointSet(){
    }

    public void makeset(Vertex x){
        x.p=x;
        x.rank=0;
    }

    public void Union(Vertex x, Vertex y){
        Link(Findset(x),Findset(y));
    }

    public void Link(Vertex x, Vertex y){
        if(x.rank > y.rank){
            y.p=x;
        }
        else {
            x.p=y;
            if (x.rank == y.rank){
                y.rank++;
            }
        }
    }

    public Vertex Findset(Vertex x){
        if (x!= x.p){
            x.p = Findset(x.p);
        }
        return x.p;
    }

}
}

