import java.util.Stack;

public class GameStack {
    Stack<Integer> firstPlayer, secondPlayer; //LIFO

    public GameStack(String firstPlayer, String secondPlayer) {
        this.firstPlayer = new Stack<>();
        this.secondPlayer = new Stack<>();
        for(int i = 4; i >= 0; i--){
            this.firstPlayer.push(Integer.parseInt(firstPlayer.substring(i, i+1)));
            this.secondPlayer.push(Integer.parseInt(secondPlayer.substring(i, i+1)));
        }
    }

    private void pushBack(Stack<Integer> s, Integer item){
        Stack<Integer> ns = new Stack<>();
        while (!s.isEmpty())
            ns.push(s.pop());
        s.push(item);
        while (!ns.isEmpty())
            s.push(ns.pop());
    }

    public String play(){
        int count = 0;
        while(!firstPlayer.isEmpty() && !secondPlayer.isEmpty() && count < 106){
            if((firstPlayer.peek() == 0) && (secondPlayer.peek() == 9)){
                pushBack(firstPlayer, firstPlayer.pop());
                pushBack(firstPlayer, secondPlayer.pop());
            }
            else if((firstPlayer.peek() == 9) && (secondPlayer.peek() == 0)){
                pushBack(secondPlayer, firstPlayer.pop());
                pushBack(secondPlayer, secondPlayer.pop());
            }
            else if(firstPlayer.peek() > secondPlayer.peek()){
                pushBack(firstPlayer, firstPlayer.pop());
                pushBack(firstPlayer, secondPlayer.pop());
            }else{
                pushBack(secondPlayer, firstPlayer.pop());
                pushBack(secondPlayer, secondPlayer.pop());
            }
            count++;
        }
        String res = "";
        if(firstPlayer.isEmpty()) res += "second ";
        else res += "first ";
        res += count;
        if(count >= 106) res = "botva";
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new GameStack("13579", "24680").play());
        System.out.println(new GameStack("19046", "57823").play());
        System.out.println(new GameStack("04876", "93215").play());
    }

}