package main;
/**
 * @author anitanaseri
 *
 */
public class Town {
	private String name;
	private boolean visited;
	
	public Town(String name) {
		this.name = name;
		this.visited = false;
	}

	@Override
	public boolean equals(Object obj) {
	    if (obj == this){
	      return true;
	    }

		if (obj.getClass() != getClass() || obj == null ){
	        return false;
	    }

		Town tempTown = (Town)obj;
		return this.name.equals(tempTown.name);
	}

	@Override
	public int hashCode() {
		if(this.name == null) return 0;
		return this.name.hashCode();
	}
	
	@Override
	public String toString() {
		return this.name.toString();
	}

	public String getName() {
		return name;
	}
	
	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

}
