import java.util.*;

public class IterativeDeepeningSearch {

    private char[] goalState;

    public IterativeDeepeningSearch(char[] startState, char[] goalState) {
        State state = new State(startState);
        this.goalState = goalState;
        ids(new Node(state));
    }

    public Node dls(Node current, int limit, long startTime, long beforeUsedMem){

        if(limit == 0){
            if(current.getState().goalStateReached(goalState)) {
                System.out.println("Nodes Expanded: " + current.getNodesExpanded());

                double endTime = (System.currentTimeMillis() - startTime) / 1000.0;
                System.out.println("Time taken by IDS: " + endTime + " seconds");

                long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                double actualMemUsed = (afterUsedMem - beforeUsedMem)/1000000.0;
                System.out.println("Memory used by IDS: " + actualMemUsed + " MB");

                return current;
            }
        }if (limit > 0){
            List<Node> neighbours = current.possibleMoves();
            Collections.shuffle(neighbours);
            for (Node n: neighbours) {
                Node found = dls(n, limit-1, startTime, beforeUsedMem);
                if (found != null){
                    return found;
                }
            }
        }else if (current.possibleMoves() == null){
            return null;
        }
        return null;
    }

    public Node ids(Node node){

        long startTime = System.currentTimeMillis();
        long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        int limit = 0;

        while(true) {
            Node result = dls(node, limit, startTime, beforeUsedMem);
            limit++;
            if (result != null) {
                result.getState().displayState();
                System.out.println();
                System.out.println("Solution Path: " + result.getSolutionPath());
                System.out.println("No. of Moves made: " + result.getSolutionPath().size());
                return result;
            }
        }
    }
}
