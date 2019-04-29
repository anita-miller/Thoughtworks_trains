package main;
/**
 * @author anitanaseri
 *
 */

public class Main {
    
    public static void main(String[] args) {
        
        parser(args);
        //initializing the graph with the input 
        Graph graph = new Graph(args[0]);
        
        System.out.println("Output #1: " + graph.displayDistanceIfExists("ABC"));
        System.out.println("Output #2: " + graph.displayDistanceIfExists("AD"));
        System.out.println("Output #3: " + graph.displayDistanceIfExists("ADC"));
        System.out.println("Output #4: " + graph.displayDistanceIfExists("AEBCD"));
        System.out.println("Output #5: " + graph.displayDistanceIfExists("AED"));
        System.out.println("Output #6: " + graph.calculateNumberOfTrips("C", "C", t -> t <= 3, 3));
        System.out.println("Output #7: " + graph.calculateNumberOfTrips("A", "C", t -> t == 4, 4));
        System.out.println("Output #8: " + graph.calculateShortestPath("A", "C"));
        System.out.println("Output #9: " + graph.calculateShortestPath("B", "B"));
        System.out.println("Output #10: " + graph.calculateRoutesCount("C", "C", 30));
    }
    
    private static void parser(String... args){
        
        if(args.length != 1)
            throw new IllegalArgumentException("Wrong input!");
        
        String arr[] = args[0].split(",");
        for(String s: arr){
            s = s.trim();
            if(s.length() < 3 || (!s.substring(2).matches("-?\\d+"))) {
            		throw new IllegalArgumentException("Wrong input!");
            }
        }
    }
}

