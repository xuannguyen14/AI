package thapHN;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class AKT {
    public static ArrayList<Vertex> OPEN = new ArrayList<Vertex>();
    public static Set<Vertex> CLOSE = new HashSet<Vertex>(){
        @Override
        public boolean contains(Object obj) {
            Vertex vertex = (Vertex) obj;
        
            for (Vertex v : this) {
                if ((vertex.equals(v))) {
                    return true;
                }
            }        
            return false;
        }
    };
    
    public static void akt(int n) {
        Vertex begin = new Vertex();
        Vertex goal = new Vertex();

        begin.getState().getG();
        begin.createBegin(n);
        goal.createGoal(n);

        OPEN.add(begin);
        CLOSE.add(begin);
        
        while(!OPEN.isEmpty()){             

            Vertex currentVertex = poll();
            CLOSE.add(currentVertex);    
            currentVertex.addToPath();                        

            if(currentVertex.equals(goal)){
                currentVertex.printPath();                                                   
                break;
            }            

            ArrayList<Vertex> newVertices = new ArrayList<>();
            Stack<Integer> tower1 = currentVertex.getState().getTower1();
            Stack<Integer> tower2 = currentVertex.getState().getTower2();
            Stack<Integer> tower3 = currentVertex.getState().getTower3();
            Vertex v;

            if(!tower1.empty()){
                if(tower2.empty() || tower1.peek() < tower2.peek()){
                    int g = currentVertex.getState().getG();
                    v = currentVertex.tower1_to_tower2();
                    v.GFH(n,g);
                    newVertices.add(v);                    
                }
                if(tower3.empty() || tower1.peek() < tower3.peek()){
                    int g = currentVertex.getState().getG();
                    v = currentVertex.tower1_to_tower3();
                    v.GFH(n,g);
                    newVertices.add(v);
                }
            }

            if(!tower2.empty()){
                if(tower1.empty() || tower2.peek() < tower1.peek()){
                    int g = currentVertex.getState().getG();
                    v = currentVertex.tower2_to_tower1();
                    v.GFH(n,g);
                    newVertices.add(v);
                }
                if(tower3.empty() || tower2.peek() < tower3.peek()){
                    int g = currentVertex.getState().getG();
                    v = currentVertex.tower2_to_tower3();
                    v.GFH(n,g);
                    newVertices.add(v);
                }
            }

            if(!tower3.empty()){
                if(tower1.empty() || tower3.peek() < tower1.peek()){
                    int g = currentVertex.getState().getG();
                    v = currentVertex.tower3_to_tower1();
                    v.GFH(n,g);
                    newVertices.add(v);
                }
                if(tower2.empty() || tower3.peek() < tower2.peek()){
                    int g = currentVertex.getState().getG();
                    v = currentVertex.tower3_to_tower2();
                    v.GFH(n,g);
                    newVertices.add(v);
                }
            }

            for (Vertex newVertex : newVertices){                                    
                if(!CLOSE.contains(newVertex)){                        
                    newVertex.setPath(currentVertex.getPath());
                    OPEN.add(newVertex);         
                }                                             
            }      
	}
    }
    
    public static Vertex poll(){
        Vertex temp = OPEN.get(0);
        OPEN.remove(0);
        return temp;
    }
}
