package eight_puzzle;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DFS {
    private State root;
    private String goalSate;

    public State getRoot() {
        return root;
    }

    public void setRoot(State root) {
        this.root = root;
    }

    public String getGoalSate() {
        return goalSate;
    }

    public void setGoalSate(String goalSate) {
        this.goalSate = goalSate;
    }

    public DFS(State root, String goalSate) {
        this.root = root;
        this.goalSate = goalSate;
    }
    
    public void depthFirstSearch() {
        Set<String> stateSets = new HashSet<>();
        State node = new State(root.getState());
        MyQueue<State> mainQueue = new MyQueue<>();
        MyQueue<State> successorsQueue = new MyQueue<>();
        
        State currentNode = node;
        while (!currentNode.getState().equals(goalSate)) {
            stateSets.add(currentNode.getState());
            List<String> nodeSuccessors = Vertex.getSuccessors(currentNode.getState());
            for (String n : nodeSuccessors) {
                if (stateSets.contains(n))
                    continue;
                stateSets.add(n);
                State child = new State(n);
                currentNode.addChild(child);
                child.setParent(currentNode);
                successorsQueue.enqueue(child);

            }
            mainQueue.addQueue(successorsQueue);
            successorsQueue.clear();
            currentNode = mainQueue.dequeue();
            nodeSuccessors.clear();
        }
        Vertex.printSolution(currentNode, stateSets, root);
    }
}
