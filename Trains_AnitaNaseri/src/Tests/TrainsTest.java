package Tests;

import main.Graph;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author anitanaseri
 *
 */
public class TrainsTest {
	private Graph graph;
	
	@Before
    public void initData(){
		graph = new Graph("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");;
    }
	
	@Test
    public void displayDistancereturnZeroIfPathDoesntExists(){
        // assert statements
        assertEquals("NO SUCH ROUTE", graph.displayDistanceIfExists("ABE"));
    }
	@Test
    public void displayPathIfExists() {
		assertEquals("9", graph.displayDistanceIfExists("ABC"));
	}
	
	@Test
    public void calculateDistanceTest() {
		assertEquals(9, graph.calculateDistance("ABC"));
	}
	
	@Test
    public void calculateDistanceIfRouteDoesntExist() {
		assertEquals(-1, graph.calculateDistance("ABE"));
	}
	
	@Test
	public void calculateNumberOfTripsTest() {
		assertEquals(2, graph.calculateNumberOfTrips("C", "C", t -> t <= 3, 3));
	}
	
	@Test
	public void calculateShortestPathTest() {
		assertEquals(9, graph.calculateShortestPath("A", "C"));
	}
	
	@Test
	public void calculateRoutesCountTest() {
		assertEquals(7, graph.calculateRoutesCount("C", "C", 30));
	}
}
