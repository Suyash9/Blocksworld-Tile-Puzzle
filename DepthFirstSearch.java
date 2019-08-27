import java.util.*;

public class DepthFirstSearch {

    private char[] goalState;

    public DepthFirstSearch(char[] startState, char[] goalState) {
        State state = new State(startState);
        this.goalState = goalState;
        dfs(new Node(state));
    }

    private Node dfs(Node node){

        long startTime = System.currentTimeMillis();
        long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        Stack<Node> stack = new Stack<>();
        stack.add(node);

        while(!stack.isEmpty()){
            Node current = stack.pop();

            if (current.getState().goalStateReached(goalState)) {
                System.out.println("Nodes Expanded: " + current.getNodesExpanded());

                double endTime = (System.currentTimeMillis() - startTime) / 1000.0;
                System.out.println("Time taken by DFS: " + endTime + " seconds");

                long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                double actualMemUsed = (afterUsedMem - beforeUsedMem)/1000000.0;
                System.out.println("Memory used by DFS: " + actualMemUsed + " MB");

                current.getState().displayState();
                System.out.println();
                System.out.println("Solution Path: " + current.getSolutionPath());
                System.out.println("No. of Moves made: " + current.getSolutionPath().size());
                return current;
            }

            List<Node> neighbours = current.possibleMoves();
            Collections.shuffle(neighbours);

            for(Node n : neighbours){
                stack.push(n);
            }
        }
        System.out.println("No solution was found.");
        return null;
    }
}
