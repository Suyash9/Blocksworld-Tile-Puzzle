import java.util.*;

public class Node{

    private State state;
    private Node parent;
    private int depth = 0;
    private static int nodesExpanded = 0;
    private String direction;

    public Node(State startState){
        this.state = startState;
    }

    private Node(Node parent, State state, String direction){
        this.parent = parent;
        this.state = state;
        if(parent != null){
            this.depth = parent.depth + 1;
        }
        this.direction = direction;
        nodesExpanded++;
    }

    public ArrayList<Node> possibleMoves() {

        ArrayList<Node> neighbours = new ArrayList<>();
        int pos = state.getAgentPos();

        if (pos > 3 && (state.getCurrentState()[pos-4] != '#')) {
            State temp = new State(state.getCurrentState().clone());
            temp.moveAgent("up");
            neighbours.add(new Node(this, temp, "Up"));
        }if (pos < 12 && (state.getCurrentState()[pos+4] != '#')) {
            State temp = new State(state.getCurrentState().clone());
            temp.moveAgent("down");
            neighbours.add(new Node(this, temp, "Down"));
        } if (pos % 4 != 0 && (state.getCurrentState()[pos-1] != '#')) {
            State temp = new State(state.getCurrentState().clone());
            temp.moveAgent("left");
            neighbours.add(new Node(this, temp, "Left"));
        } if (pos % 4 != 3 && (state.getCurrentState()[pos+1] != '#')){
            State temp = new State(state.getCurrentState().clone());
            temp.moveAgent("right");
            neighbours.add(new Node(this, temp, "Right"));
        }
        return neighbours;
    }

    public ArrayList<String> getSolutionPath(){
        ArrayList<String> solutionPath = new ArrayList<>();
        Node node = this;
        while(node.parent != null){
            solutionPath.add(0, node.direction);
            node = node.parent;
        }
        return solutionPath;
    }

    public static void resetNodesExpanded(){
        nodesExpanded = 0;
    }

    public int getDepth() {
        return depth;
    }

    public int getNodesExpanded(){
        return nodesExpanded;
    }

    public State getState(){
        return state;
    }
}