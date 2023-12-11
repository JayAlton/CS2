//Project 2 by: Ryan Rogers and Jayden Alton

package Project2;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;

public class AStarSearch implements GraphSearchAlgorithm {

    private Set<State> touched;

    @Override
    public Path search(State start, State goal) {
        touched = new HashSet<>();
        PriorityQueue<PathCostPair> openSet = new PriorityQueue<>();
        
        openSet.add(new PathCostPair(new Path(start), 0)); // Initialize the priority queue
        
        while (!openSet.isEmpty()) {
            PathCostPair currentPair = openSet.poll();
            Path currentPath = currentPair.getPath();
            State currentState = currentPath.getLastState();
            
            if (currentState.equals(goal)) {
                return currentPath; // Return the path if goal is reached
            }
            
            touched.add(currentState);
            
            for (Action action : currentState.getActions()) {
                State nextState = action.getNextState();
                
                if (!touched.contains(nextState)) {
                    double tentativeCost = currentPair.getCost() + action.getCost();
                    double heuristic = nextState.heuristicTo(goal);
                    double totalCost = tentativeCost + heuristic;
                    
                    Path newPath = new Path(currentPath, action);
                    openSet.add(new PathCostPair(newPath, totalCost));
                }
            }
        }
        
        return null; // Return null if no path to the goal is found
    }

    
    // Define a helper class to pair paths with their associated cost
    private class PathCostPair implements Comparable<PathCostPair> {
        private Path path;
        private double cost;
        

        public PathCostPair(Path path, double cost) {
            this.path = path;
            this.cost = cost;
        }

        public Path getPath() {
            return path;
        }

        public double getCost() {
            return cost;
        }

        @Override
        public int compareTo(PathCostPair other) {
            return Double.compare(this.cost, other.cost);
        }
    }
}
