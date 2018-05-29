package assignment4;
//I did this homework alone, with only this semester's material.
//Shaocheng Yang

import java.util.*;

public class Graph {

	Map<String, Vertex> vertices;
	
	Map<String, ArrayList<Edge>> adjacent;
	
	List<String> searchSteps;
	public static void main(String[] args){
		String[] a = {"1","2","3","4","5"};
		Graph graph = new Graph();
		for(int i=0;i<a.length;i++){
			graph.addVertex(a[i]);
		}
		graph.addEdge(a[1], a[2], 5);
		graph.addEdge(a[2], a[3], 2);
		graph.addEdge(a[3], a[4], 1);
		graph.addEdge(a[4], a[0], 7);
		graph.addEdge(a[0], a[3], 7);
		graph.addEdge(a[4], a[2], 9);
		
	
		System.out.println(graph);
		System.out.println("Breadth First Search:");
		graph.breadthFirstSearch(a[1]);
		for(int i =0;i<graph.searchSteps.size();i++){
			
			System.out.println("\n"+graph.searchSteps.get(i));
		}
		System.out.println("path 2 - 5:");
		System.out.println(graph.path(a[1], a[4]));
		System.out.println("path weight:");
		System.out.println(graph.pathWeight(a[1], a[4]));
		System.out.println("topological sort:");
		System.out.println(graph.topologicalSort());
		System.out.println("------------------------------");
		System.out.println("Depth First Search:");
		graph.depthFirstSearch();
		for(int i =0;i<graph.searchSteps.size();i++){
			
			System.out.println("\n"+graph.searchSteps.get(i));
		}
		
		
		System.out.println("book's example:");
		String[] b = {"v","s","w","q","t","x","z","y","r","u"};
		Graph g = new Graph();
		for(int i=0;i<b.length;i++){
			g.addVertex(b[i]);
		}
		//set all weight 1
		g.addEdge(b[1], b[0], 1);
		g.addEdge(b[0], b[2], 1);
		g.addEdge(b[2], b[1], 1);
		g.addEdge(b[3], b[2], 1);
		g.addEdge(b[3], b[1], 1);
		g.addEdge(b[3], b[4], 1);
		g.addEdge(b[7], b[3], 1);
		g.addEdge(b[4], b[5], 1);
		g.addEdge(b[5], b[6], 1);
		g.addEdge(b[6], b[5], 1);
		g.addEdge(b[4], b[7], 1);
		g.addEdge(b[8], b[7], 1);
		g.addEdge(b[9], b[7], 1);
		g.addEdge(b[8], b[9], 1);
		
		System.out.println(g);
		g.breadthFirstSearch(b[3]);
		for(int i =0;i<g.searchSteps.size();i++){
			System.out.println(i+"\n"+g.searchSteps.get(i));
		}
		g.depthFirstSearch();
		for(int i =0;i<g.searchSteps.size();i++){
			System.out.println("\n"+g.searchSteps.get(i));
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
	
}
//create the vertex class
//each vertex represents a node 
//each vertex has a label, color, distance, parent, discoverStep, and a finishstep

class Vertex {
	
	public static final int WHITE = 0;
	public static final int GRAY = 1;
	public static final int BLACK = 2;

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

}

class Edge {
	//two nodes source and target with a weight between these two nodes
	
	Vertex source;
	
	Vertex target;
	
	double weight;
	//method Edge creates the edge between two nodes and adds weight to it
	public Edge(Vertex source, Vertex target, double weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
	}
	
	//toString method 
	public String toString() {
		return "(" + source + " -> " + target + " : " + weight + ")";
	}
	
}
