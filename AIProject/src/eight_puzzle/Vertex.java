package eight_puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Vertex {
    public static List<String> getSuccessors(String state) {
        List<String> successors = new ArrayList<>();

        // đổi vị trí theo từng ô được đánh số từ 0 -> 8
        switch (state.indexOf("0")) {
            case 0 ->  {
                successors.add(state.replace(state.charAt(0), '*').replace(state.charAt(1), state.charAt(0)).replace('*', state.charAt(1)));
                successors.add(state.replace(state.charAt(0), '*').replace(state.charAt(3), state.charAt(0)).replace('*', state.charAt(3)));
            }
            case 1 ->  {
                successors.add(state.replace(state.charAt(1), '*').replace(state.charAt(0), state.charAt(1)).replace('*', state.charAt(0)));
                successors.add(state.replace(state.charAt(1), '*').replace(state.charAt(2), state.charAt(1)).replace('*', state.charAt(2)));
                successors.add(state.replace(state.charAt(1), '*').replace(state.charAt(4), state.charAt(1)).replace('*', state.charAt(4)));
            }
            case 2 ->  {

                successors.add(state.replace(state.charAt(2), '*').replace(state.charAt(1), state.charAt(2)).replace('*', state.charAt(1)));
                successors.add(state.replace(state.charAt(2), '*').replace(state.charAt(5), state.charAt(2)).replace('*', state.charAt(5)));
            }
            case 3 ->  {
                successors.add(state.replace(state.charAt(3), '*').replace(state.charAt(0), state.charAt(3)).replace('*', state.charAt(0)));
                successors.add(state.replace(state.charAt(3), '*').replace(state.charAt(4), state.charAt(3)).replace('*', state.charAt(4)));
                successors.add(state.replace(state.charAt(3), '*').replace(state.charAt(6), state.charAt(3)).replace('*', state.charAt(6)));
            }
            case 4 ->  {
                successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(1), state.charAt(4)).replace('*', state.charAt(1)));
                successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(3), state.charAt(4)).replace('*', state.charAt(3)));
                successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(5), state.charAt(4)).replace('*', state.charAt(5)));
                successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(7), state.charAt(4)).replace('*', state.charAt(7)));
            }
            case 5 ->  {
                successors.add(state.replace(state.charAt(5), '*').replace(state.charAt(2), state.charAt(5)).replace('*', state.charAt(2)));
                successors.add(state.replace(state.charAt(5), '*').replace(state.charAt(4), state.charAt(5)).replace('*', state.charAt(4)));
                successors.add(state.replace(state.charAt(5), '*').replace(state.charAt(8), state.charAt(5)).replace('*', state.charAt(8)));
            }
            case 6 ->  {
                successors.add(state.replace(state.charAt(6), '*').replace(state.charAt(3), state.charAt(6)).replace('*', state.charAt(3)));
                successors.add(state.replace(state.charAt(6), '*').replace(state.charAt(7), state.charAt(6)).replace('*', state.charAt(7)));

            }
            case 7 ->  {
                successors.add(state.replace(state.charAt(7), '*').replace(state.charAt(4), state.charAt(7)).replace('*', state.charAt(4)));
                successors.add(state.replace(state.charAt(7), '*').replace(state.charAt(6), state.charAt(7)).replace('*', state.charAt(6)));
                successors.add(state.replace(state.charAt(7), '*').replace(state.charAt(8), state.charAt(7)).replace('*', state.charAt(8)));
            }
            case 8 ->  {
                successors.add(state.replace(state.charAt(8), '*').replace(state.charAt(5), state.charAt(8)).replace('*', state.charAt(5)));
                successors.add(state.replace(state.charAt(8), '*').replace(state.charAt(7), state.charAt(8)).replace('*', state.charAt(7)));
            }
        }

        return successors;


    }

    public static void printSolution(State goalNode, Set<String> visitedNodes, State root) {
        Stack<State> solutionStack = new Stack<>();
        solutionStack.push(goalNode);
        while (!goalNode.getState().equals(root.getState())) {
            solutionStack.push(goalNode.getParent());
            goalNode = goalNode.getParent();
        }
        String sourceState = root.getState();
        String goalState; // trạng thái đích

        for (int i = solutionStack.size() - 1; i >= 0; i--) {
            goalState = solutionStack.get(i).getState();
            if (!sourceState.equals(goalState)) {
                System.out.println("\nMove " + goalState.charAt(sourceState.indexOf('0')) + " " + findTransition(sourceState, goalState));
            }

            sourceState = goalState;
            System.out.println("" + solutionStack.get(i).getState().substring(0, 3));
            System.out.println("" + solutionStack.get(i).getState().substring(3, 6));
            System.out.println("" + solutionStack.get(i).getState().substring(6, 9));

        }
        
        System.out.println("\nSố bước thực hiện:  " + (solutionStack.size() - 1));

    }
    
    public enum MovementType {
    UP,
    DOWN,
    LEFT,
    RIGHT;
}
    
    public static MovementType findTransition(String source, String destination) {
        int zero_position_difference = destination.indexOf('0') - source.indexOf('0');
        switch (zero_position_difference) {
            case -3 -> {
                return MovementType.DOWN;
            }
            case 3 -> {
                return MovementType.UP;
            }
            case 1 -> {
                return MovementType.LEFT;
            }
            case -1 -> {
                return MovementType.RIGHT;
            }
        }
        return null;
    }
}



