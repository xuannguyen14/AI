package EightQueenPuzzle;

import java.util.List;

public class Vertex {
    private State state;
    private Path<Vertex> path;
    
    public Vertex(){
        this.state = new State();
        this.path = new Path<>();
    }
    
    public Vertex(State state){
        this.state = state;        
        this.path = new Path<>();
    }
    
    public State getState(){
        return state;
    }        

    public void setState(State state){
        this.state = state;
    }
    
    public void addToPath() {
        this.path.addVertex(this);
    }
    
    public List<Vertex> getPath(){
        return path.getPath();
    }
    
    public void setPath(List<Vertex> path){
        this.path.setPath(path);
    }

    public void printPath(){
        path.printPath();
    }        

    @Override
    public boolean equals(Object obj) {
        Vertex v = (Vertex) obj;        
        
	if (!(v instanceof Vertex))
            return false;	
        return (this.getState().equals(v.getState()));
    }        
    
    @Override
    public String toString(){
        return state.toString();
    }
}