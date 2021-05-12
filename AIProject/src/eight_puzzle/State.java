package eight_puzzle;

import java.util.ArrayList;

public class State {
    private boolean visited;

    private String state;
    private final ArrayList<State> children;
    private State parent;
    private int depth;

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setState(String state) {
        this.state = state;
    }

    public State getParent() {
        return parent;
    }

    public void setParent(State parent) {
        this.parent = parent;
    }

    public State(String state) {
        this.state = state;
        children = new ArrayList<>();
    }

    public String getState() {
        return state;
    }

    public ArrayList<State> getChildren() {
        return children;
    }

    public void addChild(State child) {
        children.add(child);
    }
}