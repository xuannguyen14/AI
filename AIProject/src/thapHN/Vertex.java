package thapHN;

import java.util.List;

public class Vertex{
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
    public void createGoal(int n){
        this.getState().createGoal(n);
    }
    public void createBegin(int n){
        this.getState().createBegin(n);
    }
   
    
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
    public Vertex tower1_to_tower2(){
        return new Vertex(state.tower1_to_tower2());
    }
    public Vertex tower1_to_tower3(){
        return new Vertex(state.tower1_to_tower3());
    }
    public Vertex tower2_to_tower1(){
        return new Vertex(state.tower2_to_tower1());
    }
    public Vertex tower2_to_tower3(){
        return new Vertex(state.tower2_to_tower3());
    }
    public Vertex tower3_to_tower1(){
        return new Vertex(state.tower3_to_tower1());
    }
    public Vertex tower3_to_tower2(){
        return new Vertex(state.tower3_to_tower2());
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
    
    public void GFH(int n, int g){
        state.GFH(n, g);
    }
}
