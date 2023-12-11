//Project 2 by: Ryan Rogers and Jayden Alton

package Project2;

import java.util.Set;
import java.util.HashSet;

public class DepthFirstSearch implements GraphSearchAlgorithm {

	private Set<State> touched;
	
    public Path search(State start, State goal) {
        touched = new HashSet<>();
        return depthFirstSearch(start, goal, new Path(start));
    }
    
    private Path depthFirstSearch(State current, State goal, Path path) {
    	touched.add(current);
    	
    	if(current.equals(goal)) {
    		return path;
    	}
    	
    	for(Action action : current.getActions()) {
    		State neighbor = action.getNextState();
    		if(!touched.contains(neighbor)) {
    			Path newPath = new Path(path, action);
    			Path result = depthFirstSearch(neighbor, goal, newPath);
    			if(result != null) {
    				return result;
    			}
    		}
    	}
    	return null;
    }

}
