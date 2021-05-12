package EightQueenPuzzle;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
 
public class AKT {
    public static int GOAL;
    
    public static SetComparator comparator = new SetComparator();
    public static PriorityQueue<Vertex> OPEN;    
    public static Set<Vertex> CLOSE = new HashSet<Vertex>(){
        @Override
        public boolean contains(Object obj) {
            Vertex vertex = (Vertex) obj;
        
            for (Vertex v : this) {
                if (vertex.getState().getCurrent() == v.getState().getCurrent()){
                    return true;
                }
            }  
            return false;
        }
    };
   
    public static void usingAKT(){
        GOAL = 8;
        char[][] Start = State.initArray();
        int depth = 0;
        int heuristic = 0;
        
        OPEN = new PriorityQueue<>(10,comparator){
            public boolean contains(Object obj) {
                Vertex vertex = (Vertex) obj;

                for (Vertex v : this) {
                    if ((vertex.getState().getCurrent() == v.getState().getCurrent())){
                        return true;
                    }
                }  
                return false;
            }      
        };
        
        //queue = new PriorityQueue<>();
        
	Vertex initialVertex = new Vertex(new State(Start,depth,heuristic));
        OPEN.add(initialVertex); 
        int check = 0;
        
	while(!OPEN.isEmpty()){  
            Vertex currentVertex = OPEN.poll();
            currentVertex.addToPath();
            if(CLOSE.contains(currentVertex))
                continue;
             
            char[][] ChooseSt = currentVertex.getState().getCurrent();              
            if(check == 0){
                System.out.println("_____INIT_____");
                System.out.println("State Level: " + currentVertex.getState().getDepth());
                System.out.println("Controlled cells: " + currentVertex.getState().getControlledCells());
                for(int i = 0; i < 8; i++){
                    for(int j = 0; j < 8; j++){
                        System.out.print(ChooseSt[i][j] + "  ");
                    }
                    System.out.print(" \n");
                }  
            } else{ 
                System.out.println("\n__Choose case__");
                System.out.println("State Level: " + (currentVertex.getState().getDepth() + 1));
                System.out.println("Controlled cells: " + currentVertex.getState().getControlledCells());
                for(int i = 0; i < 8; i++){
                    for(int j = 0; j < 8; j++){
                        System.out.print(ChooseSt[i][j] + "  ");
                    }
                    System.out.print(" \n");
                }  
            }
            
            CLOSE.add(currentVertex);
            
            if(currentVertex.getState().getQueens() == GOAL){
                System.out.println("__________RESULT__________");
                currentVertex.printPath(); 
                break;
            }            
            
            ArrayList<Vertex> newVertices = new ArrayList<>();
            ArrayList<char[][]> childList = new ArrayList<>();
            char[][] Current = currentVertex.getState().getCurrent();
            char[][] C = State.initArray();
            for(int i = 0; i < 8; i++)
                for(int j = 0; j < 8; j++)
                    C[i][j] = Current[i][j];
            
            childList.addAll(State.Child(C));
            depth = currentVertex.getPath().size()-1;
            
            for(char[][] S : childList){
               heuristic = State.Heuristic(S,depth);
               newVertices.add(new Vertex(new State(S,depth,heuristic)));
            }

            for (Vertex newVertex : newVertices){
                char[][] InitSt = newVertex.getState().getCurrent();
                
                System.out.println("____________________"); 
                System.out.println("Case: ");
                System.out.println("State Level: " + (newVertex.getState().getDepth() + 1));
                System.out.println("Controlled cells: " + newVertex.getState().getControlledCells());
                
                for(int i = 0; i < 8; i++){
                    for(int j = 0; j < 8; j++){
                        System.out.print(InitSt[i][j] + "  ");
                    }
                    System.out.print(" \n");
                }               
                
                if(!OPEN.contains(newVertex)){
                    newVertex.setPath(currentVertex.getPath());
                    OPEN.add(newVertex);   
                }                
                check = 1;
            }             
	}
    } 
}

class SetComparator implements Comparator<Vertex> {
    @Override
    public int compare(Vertex x, Vertex y) {        
        if (x.getState().getControlledCells()< y.getState().getControlledCells()) {
            return -1;
        }
        if (x.getState().getControlledCells() > y.getState().getControlledCells()) {
            return 1;
        }
        return 0;
    }
    
}