package thapHN;

import java.util.Stack;

class State{
    private int g = 0;
    private int h;
    private int f;

    private Stack<Integer> tower1= new Stack();
    private Stack<Integer> tower2= new Stack();
    private Stack<Integer> tower3= new Stack();
        
    public State(){}
    
    public State(Stack<Integer> tower1, Stack<Integer> tower2, Stack<Integer> tower3) {
        this.tower1 = tower1;
        this.tower2 = tower2;
        this.tower3 = tower3;
    }
    
    public void createBegin(int n){
        for (int i = n; i > 0; i--){
            tower1.push(i);
        }
    }
    public void createGoal(int n){
        for (int i = n; i > 0; i--){
            tower3.push(i);
        }
    }
    
    public State tower1_to_tower2(){
        Stack<Integer> to1 = (Stack<Integer>) tower1.clone();
        Stack<Integer> to2 = (Stack<Integer>) tower2.clone();
        to2.push(to1.pop());
        return new State(to1, to2, tower3);
    }
    
    public State tower1_to_tower3(){
        Stack<Integer> to1 = (Stack<Integer>) tower1.clone();
        Stack<Integer> to3 = (Stack<Integer>) tower3.clone();
        to3.push(to1.pop());
        return new State(to1, tower2, to3);
    }
    public State tower2_to_tower1(){
        Stack<Integer> to1 = (Stack<Integer>) tower1.clone();
        Stack<Integer> to2 = (Stack<Integer>) tower2.clone();
        to1.push(to2.pop());
        return new State(to1, to2, tower3);
    }
    public State tower2_to_tower3(){
        Stack<Integer> to3 = (Stack<Integer>) tower3.clone();
        Stack<Integer> to2 = (Stack<Integer>) tower2.clone();
        to3.push(to2.pop());
        return new State(tower1, to2, to3);
    }
    public State tower3_to_tower1(){
        Stack<Integer> to1 = (Stack<Integer>) tower1.clone();
        Stack<Integer> to3 = (Stack<Integer>) tower3.clone();
        to1.push(to3.pop());
        return new State(to1, tower2, to3);
    }
    public State tower3_to_tower2(){
        Stack<Integer> temp3 = (Stack<Integer>) tower3.clone();
        Stack<Integer> temp2 = (Stack<Integer>) tower2.clone();
        temp2.push(temp3.pop());
        return new State(tower1, temp2, temp3);
    }
    @Override
    public boolean equals(Object obj){
        State s=(State) obj;
        if(!(s instanceof State)) return false;
        return (s.tower1.equals(tower1)&&s.tower2.equals(tower2)&&s.tower3.equals(tower3));
    }
    @Override
    public String toString(){
        return (tower1.toString() + " ___ " + tower2.toString() + " ___ " + tower3.toString());
    }
    
    public Stack<Integer> getTower1() {
        return tower1;
    }

    public void setTower1(Stack<Integer> tower1) {
        this.tower1 = tower1;
    }

    public Stack<Integer> getTower2() {
        return tower2;
    }

    public void setTower2(Stack<Integer> tower2) {
        this.tower2 = tower2;
    }

    public Stack<Integer> getTower3() {
        return tower3;
    }

    public void setTower3(Stack<Integer> tower3) {
        this.tower3 = tower3;
    }    
    
    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public void GFH(int n,int g){
        Stack<Integer> temp = (Stack<Integer>) getTower3().clone();
        
        // tinh h
        int tr = 0, fa = 0; // goi tr là số vị trí đúng, fa là số vị trí sai so với cột 3
        int n1 = getTower3().size();
        if(n1 == 0){
            h = n;
        } else {        
            int a = n - (n - n1) + 1;
            for (int i = a; i <= n; i++){
                if(!temp.empty())
                if(temp.pop() == i) tr++;
                else fa++;
            }
            h = n - tr + fa;
        }
        
        this.g = g + 1;
        f = g + h + 1;
    }
}
