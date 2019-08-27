import java.util.*;

public class AStarSearch {

    private char[][] goalState;

    public AStarSearch(char[][] startState, char[][] goalState) {
        StateAStar state = new StateAStar(startState);
        this.goalState = goalState;
        aStar(new NodeAStar(state));
    }

    private NodeAStar aStar(NodeAStar node){

        long startTime = System.currentTimeMillis();
        long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        if (node.getState().goalStateReached(goalState)) {
            System.out.println("Nodes Expanded: " + node.getNodesExpanded());

            double endTime = (System.currentTimeMillis() - startTime) / 1000.0;
            System.out.println("Time taken by A*: " + endTime + " seconds");

            long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            double actualMemUsed = (afterUsedMem - beforeUsedMem)/1000000.0;
            System.out.println("Memory used by A*: " + actualMemUsed + " MB");

            node.getState().displayState();
            return node;
        }

        PriorityQueue<NodeAStar> queue = new PriorityQueue<>();
        node.calculateManhattanDistance(goalState);
        queue.add(node);

        while(!queue.isEmpty()){
            NodeAStar current = queue.poll();

            if (current.getState().goalStateReached(goalState)) {
                System.out.println("Nodes Expanded: " + current.getNodesExpanded());

                double endTime = (System.currentTimeMillis() - startTime) / 1000.0;
                System.out.println("Time taken by A*: " + endTime + " seconds");

                long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                double actualMemUsed = (afterUsedMem - beforeUsedMem)/1000000.0;
                System.out.println("Memory used by A*: " + actualMemUsed + " MB");

                current.getState().displayState();

                System.out.println();
                System.out.println("Solution Path: " + current.getSolutionPath());
                System.out.println("No. of Moves made: " + current.getSolutionPath().size()/2);
                return current;
            }

            List<NodeAStar> neighbours = current.possibleMoves();

            for(NodeAStar n : neighbours){
                n.calculateManhattanDistance(goalState);
                queue.add(n);
            }
        }
        System.out.println("No solution was found.");
        return null;
    }
}

