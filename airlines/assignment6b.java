package assignment6;
//I did this homework alone, with only this semester's material.
//Shaocheng Yang


import java.util.*;

public class assignment6b {

    Map<String, Vertex> vertices;

    Map<String, ArrayList<Edge>> adjacent;

    List<String> searchSteps;
    public static void main(String[] args){
        // all the vertices are connected undirected, so addedge method is called twice on both side for the same edge.
		String[] a = {"1","2","3","4","5","6","7"};
		assignment6b graph = new assignment6b();
		for(int i=0;i<a.length;i++){
			graph.addVertex(a[i]);
		}
		graph.addEdge(a[0], a[1], 5);
		graph.addEdge(a[1], a[0], 5);
		graph.addEdge(a[1], a[2], 1);
		graph.addEdge(a[2], a[1], 1);
		graph.addEdge(a[3], a[0], 3);
		graph.addEdge(a[0], a[3], 3);
		graph.addEdge(a[2], a[3], 2);
		graph.addEdge(a[3], a[2], 2);
		graph.addEdge(a[4], a[2], 4);
		graph.addEdge(a[2], a[4], 4);
		graph.addEdge(a[5], a[1], 3);
		graph.addEdge(a[1], a[5], 3);
		graph.addEdge(a[6], a[3], 6);
		graph.addEdge(a[3], a[6], 6);

		System.out.println(graph);
		System.out.println("total weight of the graph:");
		System.out.println(graph.totalWeight());
		System.out.println("show the prim's method:");
		System.out.println(graph.prim("1"));


		System.out.println("minimum spanning tree by kruskal method:");
		System.out.println(graph.kruskal());

        String[] airport = {"MSP","ATL","JFK","SFO","LAS"};
        assignment6b airline = new assignment6b();
        for (int i = 0; i < airport.length;i++){
            airline.addVertex(airport[i]);
        }


        airline.addEdge(airport[0],airport[1],907);
        airline.addEdge(airport[0],airport[2],1026);
        airline.addEdge(airport[0],airport[3],1585);
        airline.addEdge(airport[0],airport[4],1297);
        airline.addEdge(airport[1],airport[0],907);
        airline.addEdge(airport[1],airport[2],759);
        airline.addEdge(airport[1],airport[3],2134);
        airline.addEdge(airport[1],airport[4],1742);
        airline.addEdge(airport[2],airport[0],1026);
        airline.addEdge(airport[2],airport[1],759);
        airline.addEdge(airport[2],airport[3],2580);
        airline.addEdge(airport[2],airport[4],2243);
        airline.addEdge(airport[3],airport[0],1585);
        airline.addEdge(airport[3],airport[1],2134);
        airline.addEdge(airport[3],airport[2],2580);
        airline.addEdge(airport[3],airport[4],413);
        airline.addEdge(airport[4],airport[0],1297);
        airline.addEdge(airport[4],airport[1],1742);
        airline.addEdge(airport[4],airport[2],2243);
        airline.addEdge(airport[4],airport[3],413);


        System.out.println("original airlines:");
        System.out.println(airline);
        System.out.println("original total cost:");
        System.out.println(airline.totalWeight());
        System.out.println("prime method:");
        System.out.println(airline.prim("MSP"));
        System.out.println("prime cost:");
        System.out.println(airline.prim("MSP").totalWeight());


        String[] b = {"1","2","3","4","5","6"};
        assignment6b graph1 = new assignment6b();
        for(int i=0;i<b.length;i++){
            graph1.addVertex(b[i]);
        }
        graph1.addEdge(b[0], b[1], 16);
        graph1.addEdge(b[0], b[2], 13);
        graph1.addEdge(b[2], b[1], 4);
        graph1.addEdge(b[1], b[3], 12);
        graph1.addEdge(b[3], b[2], 9);
        graph1.addEdge(b[2], b[4], 14);
        graph1.addEdge(b[4], b[3], 7);
        graph1.addEdge(b[3], b[5], 20);
        graph1.addEdge(b[4], b[5], 4);

        System.out.println(graph1);
        System.out.println("The maximum flow of the graph is: " + Double.toString(graph1.maxFlow(b[0], b[5])));


        Double reviewerPerPaper = 3.0;
        Double paperPerReviewer = 2.0;
        List<String> topicList = new ArrayList<>();
        topicList.add("biology");
        topicList.add("linguistics");
        topicList.add("physics");
        topicList.add("cs");
        topicList.add("religion");

        List<Set<String>> specialtyList = new ArrayList<>();
        Set<String> reader1 = new HashSet<>();
        reader1.add("biology");
        reader1.add("linguistics");
        Set<String> reader2 = new HashSet<>();
        reader2.add("physics");
        reader2.add("linguistics");
        reader2.add("cs");
        Set<String> reader3 = new HashSet<>();
        reader3.add("biology");
        reader3.add("religion");
        Set<String> reader4 = new HashSet<>();
        reader4.add("physics");
        reader4.add("cs");
        specialtyList.add(reader1);
        specialtyList.add(reader2);
        specialtyList.add(reader3);
        specialtyList.add(reader4);

        assignment6b conference = new assignment6b();
        conference.Conference(reviewerPerPaper, paperPerReviewer, topicList, specialtyList);
    }



    public assignment6b() {
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
        this.depthFirstSearch();
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
//            System.out.println("end vertex is not been searched");
            return path;
        }
        //trace back from the end vertex to start vertex by finding its parent
        Vertex curr = end;
        while(curr != null && !curr.equals(start)){

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



    /**
     * this method use kruskal's algorithm to find the minimum spanning tree in the graph.
     * put all the edges in the array first, then sorted the array
     * poll the edges from the sorted array
     * if two ends of the edge is not in the same set
     * union the two sets
     * record the edge
     * @return
     */
    public Graph kruskal(){
        Queue<Edge> alledges = new ArrayDeque<Edge>();
        Queue<Edge> alledgescopy = new ArrayDeque<Edge>();
        Queue<Edge> minedges = new ArrayDeque<Edge>();
        Queue<Edge> edgeselected = new ArrayDeque<Edge>();
        DisjointSet set = new DisjointSet();
        int n = 0;
        for(String key: vertices.keySet()){
            //add all edges, make all sets

            set.makeset(vertices.get(key));
            ArrayList<Edge> edges = adjacent.get(key);

            for(Edge e: edges){
                alledges.add(e);
                n++;		//find the number of all edges
            }
        }



        for(int i = 0; i < n ; i++){
            Edge min = new Edge(null, null, Double.POSITIVE_INFINITY);

            while(!alledges.isEmpty()){	//find the minimum weight edge

                Edge curr = alledges.poll();

                if(curr.weight<min.weight){

                    alledgescopy.add(min);
                    min = curr;

                }
                else{
                    alledgescopy.add(curr);
                }
            }

            minedges.add(min);


            while(!alledgescopy.isEmpty()){			//update the array
                alledges.add(alledgescopy.poll());

            }
        }


        while(!minedges.isEmpty()){	//check if the two ends are in the same set. if not, union the two sets and record the edge.
            Edge curredge = minedges.poll();
            if(set.Findset(curredge.source)!=set.Findset(curredge.target)){

                set.Union(curredge.source, curredge.target);
                edgeselected.add(curredge);
            }

        }

        Graph a = new Graph();

        while(!edgeselected.isEmpty()){
            //create the graph
            Edge curredge = edgeselected.poll();
            a.addEdge(curredge.source.label, curredge.target.label, curredge.weight);
            a.addEdge(curredge.target.label, curredge.source.label, curredge.weight);
        }


        return a;
    }

    /**
     *
     * @param source
     * @param target
     * @return
     */
    public double maxFlow(String source, String target) {
        //initialize the flow of all edges in the original graph to 0
        //create a residual network based on the original graph
        Graph residualNet = new Graph();
        List<Edge> originalEdges = new ArrayList<>();
        for (String label : adjacent.keySet()) {
            List<Edge> edges = adjacent.get(label);
            for (Edge edge : edges) {
                edge.setCurFlow(0);
                String sourceLabel = edge.source.label;
                String targetLabel = edge.target.label;
                residualNet.addEdge(sourceLabel, targetLabel, edge.weight);
                originalEdges.add(edge);
            }
        }

        List<Edge> augmentPath = findAugmentPath(residualNet, source, target);

        if (!augmentPath.isEmpty()) {
            while (!augmentPath.isEmpty()) {
                double residualWeight = getResidualWeight(augmentPath);

                for (Edge edge : augmentPath) {
                    if (originalEdges.contains(edge)) {
                        edge.capacity = edge.capacity - residualWeight;
                    } else {

                        for (Edge edge1: adjacent.get(edge.target.label)) {
                            if (edge1.target.label.equals(edge.source.label)) {
                                edge1.capacity = edge1.capacity + residualWeight;
                            }
                        }
                    }
                }

                residualNet = new Graph();

                for (Edge edge : originalEdges) {
                    if (edge.capacity != 0) {
                        residualNet.addEdge(edge.source.label, edge.target.label, edge.capacity);

                        if (edge.capacity != edge.weight ) {
                            residualNet.addEdge(edge.target.label, edge.source.label, edge.weight - edge.capacity);
                        }
                    } else {
                        residualNet.addEdge(edge.target.label, edge.source.label, edge.weight);
                    }
                }

                augmentPath = findAugmentPath(residualNet, source, target);
            }
        }

        Double maxFlow = 0.0;

        for (Edge edge : adjacent.get(source)) {
            edge.setCurFlow(edge.weight - edge.capacity);
            maxFlow += edge.getCurFlow();
        }
        return maxFlow;
    }


    private List<Edge> findAugmentPath(Graph residual, String source, String target) {

        List<Edge> augPath = new ArrayList<>();

        List<String> augPathLabel = residual.path(source, target);

        if (!augPathLabel.isEmpty()) {
            for (int i = 0; i < augPathLabel.size() - 1; i ++) {
                String startLabel = augPathLabel.get(i);
                String endLabel = augPathLabel.get(i + 1);

                List<Edge> edges = adjacent.get(startLabel);
                for (Edge edge : edges) {
                    if (edge.target.label.equals(endLabel)) {
                        augPath.add(edge);
                    }
                }


                if (augPath.size() != i + 1) {
                    List<Edge> reverseEdges = adjacent.get(endLabel);

                    for (Edge edge : reverseEdges) {
                        if (edge.target.label.equals(startLabel)) {
                            Edge newEdge = new Edge(edge.target, edge.source, edge.weight - edge.capacity);
                            augPath.add(newEdge);
                        }
                    }
                }
            }
        }

        return augPath;
    }

    private double getResidualWeight(List<Edge> augPath) {


        double residualCap = augPath.get(0).getCapacity();

        for (int i = 1; i < augPath.size(); i ++) {
            double flow = augPath.get(i).getCapacity();

            if ( flow < residualCap) {
                residualCap = flow;
            }
        }

        return residualCap;
    }


    public void Conference(Double X, Double Y, List<String> topicList, List<Set<String>> specialtyList) {
        Graph conferenceGraph = new Graph();

        for (String topic : topicList) {
            for (Set<String> specialty : specialtyList) {
                if (specialty.contains(topic)) {
                    String specialtyString = "";
                    for (String spec : specialty) {
                        specialtyString += spec + "&";
                    }
                    conferenceGraph.addEdge(topic, specialtyString, 1);
                }
            }

            conferenceGraph.addEdge("s", topic, X);
        }

        for (Set<String> specialty : specialtyList) {

            String specialtyString = "";
            for (String spec : specialty) {
                specialtyString += spec + "&";
            }
            conferenceGraph.addEdge(specialtyString, "t", Y);
        }

        System.out.println(conferenceGraph);
        System.out.println("The total number of papers that can be read is: " + conferenceGraph.maxFlow("s", "t"));

        
    }
}
//create the vertex class
//each vertex represents a node
//each vertex has a label, color, distance, parent, discoverStep, and a finishstep

class Vertex {

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
