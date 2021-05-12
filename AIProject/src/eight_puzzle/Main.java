package eight_puzzle;

public class Main {
    final static private String START = "834021765";
    final static private String GOAL_STATE = "123804765";


    public static void main(String[] args) {
        String rootState = START;
        State node = new State(rootState);
        DFS search = new DFS(node, GOAL_STATE);
        search.depthFirstSearch();
    }
    
}

