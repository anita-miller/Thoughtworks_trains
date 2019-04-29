package main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author anitanaseri
 *
 */
public class Graph {
    private static final String[] VERTEX_LIST = {"A", "B", "C", "D", "E"};
    private final LinkedList<Edge>[] adj;
    private List<String> allPath;
    private int dest;
    private int maxDistance;
    private int stops;
    private int routesCount;
    private int tripsCount;
    
    private Graph(int n) {
        if (n < 0) throw new IllegalArgumentException("Number of vertices in a Graph must be positive");
        adj = new LinkedList[n];
        // Create a new list for each vertex 
        // so adjacent nodes can be stored 
        for (int i = 0; i < n; i++) {
        		adj[i] = new LinkedList<>();
        }       
    }
    
    public Graph(String inputGraph) {
        this(VERTEX_LIST.length);
        initializeGraph(inputGraph);
    }
    
    private void initializeGraph(String g){
        String[] inputString = g.split(",");
        for (String s : inputString) {
            s = s.trim();
            int origin= getIndex(s.substring(0, 1));
            int dest = getIndex(s.substring(1, 2));
            int weight = Integer.parseInt(s.substring(2));
            Edge e = new Edge(origin, dest, weight);
            addEdge(e);
        }
    }

    private void addEdge(Edge e) {
        int v = e.getOrigin();
        adj[v].add(e);
    }

    private List<Edge> adj(int v) {
        if (v < 0 || v >= VERTEX_LIST.length) throw new IndexOutOfBoundsException("vertex " + v + " not exists");
        return adj[v];
    }
    

    //for Outputs 1-5
    public String displayDistanceIfExists(String route){
        int distance = calculateDistance(route);
        return (distance != -1) ? String.valueOf(distance) : "NO SUCH ROUTE";
    }
    
    public int calculateDistance(String route){
        if(route == null) throw new IllegalArgumentException("Route is wrong");
        int distance = 0;
        String[] vertex = route.trim().split("");
        int origin, dest;
        
        for (int i = 0; i < vertex.length-1;) {
            boolean hasPath = false;
            origin = getIndex(vertex[i++]);
            dest = getIndex(vertex[i]);
            List<Edge> edgeList = adj(origin);
            for (Edge edge : edgeList) 
                if (edge.getDestination() == dest) {
                    distance += edge.getWeight();
                    hasPath = true;
                    break;
                }
            if(!hasPath) return -1;
        }
        
        return distance;
    }
    
    //for outputs 6,7
    public int calculateNumberOfTrips(String origin, String dest, Predicate<Integer> p, int stops){
        this.dest = getIndex(dest);
        this.stops = stops;
        this.tripsCount = 0;
        int startIndex = getIndex(origin);
        calculateNumberOfTrips(startIndex, String.valueOf(startIndex), p);
        
        return tripsCount;
    }
    
    private void calculateNumberOfTrips(int from, String path, Predicate<Integer> p) {
        List<Edge> edges = adj(from);
        for (Edge e: edges) {
            
            String next = path + e.getDestination();
            int stopCount = next.length()-1;
            
            if (this.dest == e.getDestination() && p.test(stopCount)) 
                tripsCount++;
            
            if(stopCount <= stops)
            	calculateNumberOfTrips(e.getDestination(), next, p);
        }
    }
    
  //for outputs 8,9
    public int calculateShortestPath(String from, String dest){
        allPath = new ArrayList<>();
        this.dest = getIndex(dest);
        int startIndex = getIndex(from);
        calculateShortestPath(startIndex, String.valueOf(startIndex));
        
        int shortestDistance = Integer.MAX_VALUE, currentDistance;
        for(String s: allPath){
            currentDistance = calculateDistance(s);
            if(currentDistance < shortestDistance)
                shortestDistance = currentDistance;
        }
        
        return shortestDistance;
    }
    
    private void calculateShortestPath(int from, String path) {
        List<Edge> edges = adj(from);
        for (Edge e: edges) {
            
            if (path.length()>1 && path.substring(1).contains(String.valueOf(e.getDestination()))) //checked visited or not
                continue;  
            
            String next = path + e.getDestination();

            if (this.dest == e.getDestination()) 
                allPath.add(getPathName(next)); 
            
            calculateShortestPath(e.getDestination(), next);
        }
    }

    //for outputs 10
    public int calculateRoutesCount(String from, String dest, int maxDistance){
        this.dest = getIndex(dest);
        this.maxDistance = maxDistance;
        this.routesCount = 0;
        int startIndex = getIndex(from);
        calculateRoutesCount(startIndex, String.valueOf(startIndex));
        
        return routesCount;
    }
    
    private void calculateRoutesCount(int from, String path) {
        List<Edge> edges = adj(from);
        for (Edge e: edges) {
            
            String next = path + e.getDestination();
            int distance = calculateDistance(getPathName(next));
            
            if (this.dest == e.getDestination() && (distance < maxDistance)) 
                routesCount++;
            
            if(distance < maxDistance)
                calculateRoutesCount(e.getDestination(), next);
        }
    }
      
    
    //helper functions

    private static int getIndex(String vertex) {
        int index = Arrays.binarySearch(VERTEX_LIST, vertex);
        if (index < 0) 
            throw new IllegalArgumentException("Wrong input");

        return index;
    }
    
    private String getVertexName(int index) {
        if (index < 0 || index >= VERTEX_LIST.length) 
            throw new IllegalArgumentException("Wrong index");

        return VERTEX_LIST[index];
    }
    
    private String getPathName(String path){
        String array[] = path.trim().split("");
        String name = "";
        for(String vertex: array)
            name += getVertexName(Integer.parseInt(vertex));
        
        return name;
    }
}

