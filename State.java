public class State {

    private final Integer N;
    private char[] currentState;
    private final char agent = '*';
    private int agentPos;

    public State(char[] startState){
        this.N = startState.length;
        this.currentState = startState;
        agentPos = getAgentPos();
    }

    public State moveAgent (String direction){
        switch (direction) {
            case "up":
                swap(agentPos, agentPos - 4);
                agentPos -= 4;
                return this;
            case "down":
                swap(agentPos, agentPos + 4);
                agentPos += 4;
                return this;
            case "left":
                swap(agentPos, agentPos - 1);
                agentPos -= 1;
                return this;
            case "right":
                swap(agentPos, agentPos + 1);
                agentPos += 1;
                return this;
        }
        return null;
    }

    private char[] swap(int pos, int newPos){
        char temp = currentState[pos];
        currentState[pos] = currentState[newPos];
        currentState[newPos] = temp;
        return currentState;
    }

    public boolean goalStateReached(char[] goalState){
        for(int i = 0; i < N; i++){
            if (currentState[i] != 'A' && goalState[i] == 'A'){
                return false;
            }if (currentState[i] != 'B' && goalState[i] == 'B') {
                return false;
            }if(currentState[i] != 'C' && goalState[i] == 'C'){
                return false;
            }
        }
        return true;
    }

    public void displayState(){
        System.out.println(currentState[0] + " " + currentState[1] + " " + currentState[2] + " " + currentState[3]);
        System.out.println(currentState[4] + " " + currentState[5] + " " + currentState[6] + " " + currentState[7]);
        System.out.println(currentState[8] + " " + currentState[9] + " " + currentState[10] + " " + currentState[11]);
        System.out.println(currentState[12] + " " + currentState[13] + " " + currentState[14] + " " + currentState[15]);
    }

    public int getAgentPos(){
        for (int i = 0; i < N; i++) {
            if (currentState[i] == agent) {
                return i;
            }
        }
        return 0;
    }

    public char[] getCurrentState() {
        return currentState;
    }
}
