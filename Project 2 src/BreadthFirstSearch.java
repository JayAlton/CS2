//Project 2 by: Ryan Rogers and Jayden Alton

package Project2;

import java.util.Queue;
import java.util.Set;
import java.util.LinkedList;
import java.util.HashSet;

public class BreadthFirstSearch implements GraphSearchAlgorithm {

    public Path search(State start, State goal) {
        Queue<Path> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        
        queue.add(new Path(start));
        
        while (!queue.isEmpty()) {
        	Path currentPath = queue.poll();
        	State currentState = currentPath.getLastState();
        	
        	if(currentState.equals(goal)) {
        		return currentPath;
        	}
        	
        	visited.add(currentState);
        	
        	for(Action action : currentState.getActions()) {
        		State nextState = action.getNextState();
        		if(!visited.contains(nextState)) {
        			Path newPath = new Path(currentPath, action);
        			queue.add(newPath);
        			visited.add(nextState);
        		}
        	}
        }
        
        return null;
    }

}
