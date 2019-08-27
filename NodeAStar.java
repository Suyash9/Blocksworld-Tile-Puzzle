import java.util.*;

public class NodeAStar implements Comparable{

    private StateAStar state;
    private NodeAStar parent;
    private int depth = 0;
    private String direction;
    private static int nodesExpanded = 0;
    private int manhattanDistance;


    public NodeAStar(StateAStar startState){
        this.state = startState;
    }

    private NodeAStar(NodeAStar parent, StateAStar state, String direction){
        this.parent = parent;
        this.state = state;
        if(parent != null){
            this.depth = parent.depth + 1;
        }
        this.direction = direction;
        nodesExpanded++;
    }

    public ArrayList<NodeAStar> possibleMoves() {
        ArrayList<NodeAStar> neighbours = new ArrayList<>();
        int i = state.getAgentI();
        int j = state.getAgentJ();

        if (i > 0 && state.getCurrentState()[i-1][j] != '#') {
            char [][] temp = createNewState(state.getCurrentState());
            StateAStar s = new StateAStar (temp);
            s.moveAgent("up");
            neighbours.add(new NodeAStar(this, s, "Up"));
        }if (i < state.getSize()-1 && state.getCurrentState()[i+1][j] != '#') {
            char[][] temp = createNewState(state.getCurrentState());
            StateAStar s = new StateAStar(temp);
            s.moveAgent("down");
            neighbours.add(new NodeAStar(this, s, "Down"));
        }if (j > 0 && state.getCurrentState()[i][j-1] != '#') {
            char [][] temp = createNewState(state.getCurrentState());
            StateAStar s = new StateAStar (temp);
            s.moveAgent("left");
            neighbours.add(new NodeAStar(this, s, "Left"));
        }if (j < state.getSize()-1 && state.getCurrentState()[i][j+1] != '#'){
            char [][] temp = createNewState(state.getCurrentState());
            StateAStar s = new StateAStar (temp);
            s.moveAgent("right");
            neighbours.add(new NodeAStar(this, s, "Right"));
        }
        return neighbours;
    }

    public ArrayList<String> getSolutionPath(){
        ArrayList<String> solutionPath = new ArrayList<>();
        NodeAStar node = this;
        while(node.parent != null){
            solutionPath.add(0, node.direction);
            node = node.parent;
        }

        return solutionPath;
    }


    private char[][] createNewState(char[][] startState){
        if (startState == null) return null;
        final char[][] newState = new char[startState.length][];

        for (int i = 0; i < startState.length; i++) {
            newState[i] = Arrays.copyOf(startState[i], startState[i].length);
        }
        return newState;
    }

    public void calculateManhattanDistance(char[][] goalState) {

        int aCostI = Math.abs(getA_I(goalState) - getA_I(state.getCurrentState()));
        int aCostJ = Math.abs(getA_J(goalState) - getA_J(state.getCurrentState()));
        int bCostI = Math.abs(getB_I(goalState) - getB_I(state.getCurrentState()));
        int bCostJ = Math.abs(getB_J(goalState) - getB_J(state.getCurrentState()));
        int cCostI = Math.abs(getC_I(goalState) - getC_I(state.getCurrentState()));
        int cCostJ = Math.abs(getC_J(goalState) - getC_J(state.getCurrentState()));

        this.manhattanDistance = aCostI + aCostJ + bCostI + bCostJ + cCostI + cCostJ + getDepth();
    }

    @Override
    public int compareTo(Object node) {
        if(manhattanDistance < ((NodeAStar) node).manhattanDistance){
            return -1;
        }
        else if(manhattanDistance == ((NodeAStar) node).manhattanDistance){
            return 0;
        }
        else return 1;
    }

    private int getA_I(char[][] state){
        int N = state.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (state[i][j] == 'A') {
                    return i;
                }
            }
        }
        return 0;
    }

    private int getA_J(char[][] state){
        int N = state.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (state[i][j] == 'A') {
                    return j;
                }
            }
        }
        return 0;
    }

    private int getB_I(char[][] state){
        int N = state.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (state[i][j] == 'B') {
                    return i;
                }
            }
        }
        return 0;
    }

    private int getB_J(char[][] state){
        int N = state.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (state[i][j] == 'B') {
                    return j;
                }
            }
        }
        return 0;
    }

    private int getC_I(char[][] state){
        int N = state.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (state[i][j] == 'C') {
                    return i;
                }
            }
        }
        return 0;
    }

    private int getC_J(char[][] state){
        int N = state.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (state[i][j] == 'C') {
                    return j;
                }
            }
        }
        return 0;
    }

    public int getDepth() {
        return depth;
    }

    public StateAStar getState(){
        return state;
    }

    public int getNodesExpanded(){
        return nodesExpanded;
    }
}