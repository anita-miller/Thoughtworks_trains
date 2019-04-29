package main;
/**
 * @author anitanaseri
 *
 */
public class Edge {
    
    private final int origin;
    private final int destination;
    private final int weight;
 
    public Edge(int origin, int destination, int weight) {
        if (origin < 0)   throw new IllegalArgumentException("Vertex names must be positive Integer");
        if (destination < 0)     throw new IllegalArgumentException("Vertex names must be positive Integer");
        if (weight < 0) throw new IllegalArgumentException("Weight must be positive");
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
    }
 
    public int getOrigin() {
        return origin;
    }
 
    public int getDestination() {
        return destination;
    }
 
    public int getWeight() {
        return weight;
    }
}