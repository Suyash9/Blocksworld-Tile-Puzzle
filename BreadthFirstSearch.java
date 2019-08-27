import java.util.*;

public class BreadthFirstSearch {

    private char[] goalState;

    public BreadthFirstSearch(char[] startState, char[] goalState) {
        State state = new State(startState);
        this.goalState = goalState;
        bfs(new Node(state));
    }

    private Node bfs (Node node){

        long startTime = System.currentTimeMillis();
        long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        if (node.getState().goalStateReached(goalState)) {
            System.out.println("Nodes Expanded: " + node.getNodesExpanded());

            double endTime = (System.currentTimeMillis() - startTime) / 1000.0;
            System.out.println("Time taken by BFS: " + endTime + " seconds");

            long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            double actualMemUsed = (afterUsedMem - beforeUsedMem)/1000000.0;
            System.out.println("Memory used by BFS: " + actualMemUsed + " MB");

            node.getState().displayState();
            return node;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty()){
            Node current = queue.poll();

            List<Node> neighbours = current.possibleMoves();

            for(Node n : neighbours){
                if (n.getState().goalStateReached(goalState)) {
                    System.out.println("Nodes Expanded: " + n.getNodesExpanded());

                    double endTime = (System.currentTimeMillis() - startTime) / 1000.0;
                    System.out.println("Time taken by BFS: " + endTime + " seconds");

                    long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                    double actualMemUsed = (afterUsedMem - beforeUsedMem)/1000000.0;
                    System.out.println("Memory used by BFS: " + actualMemUsed + " MB");

                    n.getState().displayState();

                    System.out.println();
                    System.out.println("Solution Path: " + n.getSolutionPath());
                    System.out.println("No. of Moves made: " + n.getSolutionPath().size());
                    return n;
                }
                queue.add(n);
            }
        }
        System.out.println("No solution was found.");
        return null;
    }
}
