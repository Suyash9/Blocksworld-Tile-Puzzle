public class Main {

    public static void main (String[] args){

        char[] startState = {' ', ' ', ' ', ' ',
                             ' ', ' ', ' ', ' ',
                             ' ', ' ', ' ', ' ',
                             'A', 'B', 'C', '*'};

        char[] startState2 = {' ', ' ', ' ', ' ',
                              ' ', '*', 'A', ' ',
                              ' ', 'B', ' ', ' ',
                              ' ', 'C', ' ', ' '};

        char[] startState3 = {' ', ' ', 'A', ' ',
                              ' ', ' ', ' ', ' ',
                              ' ', ' ', 'C', 'B',
                              ' ', ' ', ' ', '*'};

        char[] startStateExtra = {' ', ' ', ' ', ' ',
                                  ' ', ' ', ' ', ' ',
                                  ' ', ' ', '#', ' ',
                                  'A', 'B', 'C', '*'};

        char[] goalState = {' ', ' ', ' ', ' ',
                            ' ', 'A', ' ', ' ',
                            ' ', 'B', ' ', ' ',
                            ' ', 'C', ' ', '*'};

        char[][] startStateA = {{' ', ' ', ' ', ' '},
                                {' ', ' ', ' ', ' '},
                                {' ', ' ', ' ', ' '},
                                {'A', 'B', 'C', '*'}};

        char[][] startStateA2 = {{' ', ' ', ' ', ' '},
                                 {' ', '*', 'A', ' '},
                                 {' ', 'B', ' ', ' '},
                                 {' ', 'C', ' ', ' '}};

        char[][] startStateA3 = {{' ', ' ', 'A', ' '},
                                 {' ', ' ', ' ', ' '},
                                 {' ', ' ', 'C', 'B'},
                                 {' ', ' ', ' ', '*'}};

        char[][] startStateAExtra = {{' ', ' ', ' ', ' '},
                                     {' ', ' ', ' ', ' '},
                                     {' ', ' ', '#', ' '},
                                     {'A', 'B', 'C', '*'}};

        char[][] goalStateA = {{' ', ' ', ' ', ' '},
                               {' ', 'A', ' ', ' '},
                               {' ', 'B', ' ', ' '},
                               {' ', 'C', ' ', '*'}};

        System.out.println("Breadth-First Search");
        BreadthFirstSearch BFS = new BreadthFirstSearch(startState, goalState);
        Node.resetNodesExpanded();
        System.gc();
        System.out.println();

        System.out.println("Depth-First Search");
        DepthFirstSearch DFS = new DepthFirstSearch(startState, goalState);
        Node.resetNodesExpanded();
        System.gc();
        System.out.println();

        System.out.println("Iterative Deepening Search");
        IterativeDeepeningSearch IDS = new IterativeDeepeningSearch(startState, goalState);
        Node.resetNodesExpanded();
        System.gc();
        System.out.println();

        System.out.println("A* Search");
        AStarSearch AStar = new AStarSearch(startStateA, goalStateA);
        Node.resetNodesExpanded();
    }
}
