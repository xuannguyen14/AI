package EightQueenPuzzle;

import java.util.ArrayList;
        
public final class State {

    private char[][] Current;
    private int controlledCells;
    private int depth;
    private int queens;
    
    
    public State(){}
    
    public State(char[][] C, int d, int h){
        Current = C;
        depth = d;
        controlledCells = h;
        initQueen();        
    }

    public void initQueen(){
        queens = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(Current[i][j] == 'Q')
                    queens++;
            }
        } 
    }
    
    public char[][] getCurrent() {
        return Current;
    }

    public void setCurrent(char[][] Current) {
        this.Current = Current;
    }

    public int getControlledCells() {
        return controlledCells;
    }

    public void setControlledCells(int controlledCells) {
        this.controlledCells = controlledCells;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getQueens() {
        return queens;
    }

    public void setQueens(int queens) {
        this.queens = queens;
    }
    
    @Override
    public boolean equals(Object obj) {
        State s = (State) obj;        
        
	if (!(s instanceof State))
            return false;	

        return (s.getCurrent().equals(this.getCurrent()));
    }
    
    @Override
    public String toString(){
        return "Queens: " + queens + " - "  + "Heuristic: " + controlledCells + " \n" + Current[0][0] + "  " + Current[0][1] + "  " + Current[0][2] + "  " + Current[0][3] + "  " + Current[0][4] + "  " + Current[0][5] + "  " + Current[0][6] + "  " + Current[0][7] + " \n" + Current[1][0] + "  " + Current[1][1] + "  " + Current[1][2] + "  " + Current[1][3] + "  " + Current[1][4] + "  " + Current[1][5] + "  " + Current[1][6] + "  " + Current[1][7] + " \n" + Current[2][0] + "  " + Current[2][1] + "  " + Current[2][2] + "  " + Current[2][3] + "  " + Current[2][4] + "  " + Current[2][5] + "  " + Current[2][6] + "  " + Current[2][7] + " \n" + Current[3][0] + "  " + Current[3][1] + "  " + Current[3][2] + "  " + Current[0][3] + "  " + Current[3][4] + "  " + Current[3][5] + "  " + Current[3][6] + "  " + Current[3][7] + " \n" + Current[4][0] + "  " + Current[4][1] + "  " + Current[4][2] + "  " + Current[4][3] + "  " + Current[4][4] + "  " + Current[4][5] + "  " + Current[4][6] + "  " + Current[4][7] + " \n" + Current[5][0] + "  " + Current[5][1] + "  " + Current[5][2] + "  " + Current[5][3] + "  " + Current[5][4] + "  " + Current[5][5] + "  " + Current[5][6] + "  " + Current[5][7] + " \n" + Current[6][0] + "  " + Current[6][1] + "  " + Current[6][2] + "  " + Current[6][3] + "  " + Current[6][4] + "  " + Current[6][5] + "  " + Current[6][6] + "  " + Current[6][7] + " \n" + Current[7][0] + "  " + Current[7][1] + "  " + Current[7][2] + "  " + Current[7][3] + "  " + Current[7][4] + "  " + Current[7][5] + "  " + Current[7][6] + "  " + Current[7][7] + "\n__________\n";
    }
    
    public static char [][] initArray(){
        char[][] array = {{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'}};
        return array;
    }
    
    public static char[][] convertArray(String s){
        char[][] array = initArray();
        int count = 0;
        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++){
                array[i][j] = s.charAt(count);
                count++;
            }
        
        return array;
    }
    
    public static String convertString(char[][] s){
        String st ="";
        
        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++)
                st += s[i][j];
        
        return st;
    }    
    
    public static ArrayList<char[][]> Child(char[][] state){
        ArrayList<char[][]> childList = new ArrayList<>();
        
        String note = "12345678";
        
        int depth = 0;
        String s = convertString(state);       
        
        for(int i = 0; i < 8; i++){
            if(state[depth][i] == 'Q'){
                depth++;
                i = -1;
            }
        }
        
        char x = note.charAt(depth);
        for(int i = 0; i < 8; i++){
            if(!note.contains("" + state[depth][i])){
                state[depth][i] = 'Q';
                
                int temp = 1;
                for(int k = depth + 1; k < 8; k++){
                    if(!note.contains("" + state[k][i]))
                        state[k][i] = x;
                    
                    if((i - temp) >= 0 && !note.contains("" + state[k][i-temp]))
                        state[k][i-temp] = x;
                    
                    if((i + temp) <=7 && !note.contains("" + state[k][i+temp]))
                        state[k][i+temp] = x;
                    
                    temp++;
                }
                
                for(int q = 0; q < 8; q++){
                    if(!note.contains("" + state[depth][q]) && state[depth][q] != 'Q')
                        state[depth][q] = x;
                }
                
                childList.add(state);
                
                state = convertArray(s);
            }
        }        
        return childList;
    }
    
    public static int Heuristic(char[][] Current, int depth){
        int h = 0;
        String note = "12345678";
        
        char x = note.charAt(depth);
        for(int i = 0; i < 8; i++){
            if(Current[depth][i] == 'Q'){
                for(int j = 0; j < 8; j++){
                    if(Current[depth][j] == x)
                        h++;
                }
                int temp = 1;
                
                if(depth < 7){
                    for(int k = depth + 1; k < 8; k++){
                        if(Current[k][i] == x)
                            h++;

                        if((i - temp) >= 0 && Current[k][i-temp] == x)
                            h++;

                        if((i + temp) <= 7 && Current[k][i+temp] == x)
                            h++;

                        temp++;
                    }
                }
               break; 
            }
        }
        return h;
    }   
    
}