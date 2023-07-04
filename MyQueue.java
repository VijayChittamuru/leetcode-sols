import java.util.Stack;

public class MyQueue {
    Stack<Integer> queue;
    Stack<Integer> temp;
    public MyQueue() {
        queue = new Stack<>();
        temp = new Stack<>();
    }
    
    public void push(int x) {
        while(!queue.isEmpty()) {
            temp.push(queue.pop());
        }

        temp.push(x);

        while(!temp.isEmpty())
            queue.push(temp.pop());
    }
    
    public int pop() {
        return queue.pop();
    }
    
    public int peek() {
        return queue.peek();
    }
    
    public boolean empty() {
        return queue.isEmpty();
    }
}