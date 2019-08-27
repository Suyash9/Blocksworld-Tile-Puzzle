public class StateAStar {

    private final Integer N;
    private char[][] currentState;
    private final char agent = '*';
    private Integer agentI;
    private Integer agentJ;

    public StateAStar(char[][] startState){
        this.N = startState.length;
        this.currentState = startState;
        agentI = getAgentI();
        agentJ = getAgentJ();
    }

    public StateAStar moveAgent (String direction){
        switch (direction) {
            case "up":
                swap(agentI, agentJ, agentI - 1, agentJ);
                agentI -= 1;
                return this;
            case "down":
                swap(agentI, agentJ, agentI + 1, agentJ);
                agentI += 1;
                return this;
            case "right":
                swap(agentI, agentJ, agentI, agentJ + 1);
                agentJ += 1;
                return this;
            case "left":
                swap(agentI, agentJ, agentI, agentJ - 1);
                agentJ -= 1;
                return this;
        }
        return null;
    }

    private char[][] swap(int i, int j, int temp1, int temp2){
        char temp = currentState[i][j];
        currentState[i][j] = currentState[temp1][temp2];
        currentState[temp1][temp2] = temp;
        return currentState;
    }

    public int getAgentI(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (currentState[i][j] == agent) {
                    return i;
                }
            }
        }
        return 0;
    }

    public int getAgentJ(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (currentState[i][j] == agent) {
                    return j;
                }
            }
        }
        return 0;
    }

    public boolean goalStateReached(char[][] goalState){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (currentState[i][j] != 'A' && goalState[i][j] == 'A'){
                    return false;
                }if (currentState[i][j] != 'B' && goalState[i][j] == 'B') {
                    return false;
                }if(currentState[i][j] != 'C' && goalState[i][j] == 'C'){
                    return false;
                }
            }
        }
        return true;
    }

    public void displayState(){
        for (int i = 0; i < currentState.length; i++) {
            System.out.println();
            for (int j = 0; j < currentState[i].length; j++) {
                System.out.print(currentState[i][j] + " ");
            }
        }
        System.out.println();
    }

    public int getSize() {
        return N;
    }

    public char[][] getCurrentState() {
        return currentState;
    }
}
