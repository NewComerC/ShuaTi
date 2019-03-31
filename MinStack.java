package test;

public class MinStack {
	static int cur=0;
	int[] s=new int[100000];
	/** initialize your data structure here. */
    public MinStack() {
    		
    }
    
    public void push(int x) {
    		s[cur++]=x;
    }
    
    public void pop() {
        cur--;
    }
    
    public int top() {
    	   return s[cur-1];
    }
    
    public int getMin() {
    		int min=s[0];
    		for(int i=1;i<cur;i++)
    		{
    			if(s[i]<min)
    				min=s[i];
    		}
    		return min;
    }
    
    public static void main(String[] args) {
    		MinStack min=new MinStack();
    		min.push(2);
    		min.push(0);
    		min.push(3);
    		min.push(0);
    		System.out.print(min.getMin());
    		min.pop();
    		System.out.print(min.getMin());
    		min.pop();
    		System.out.print(min.getMin());
    		min.pop();
    		System.out.print(min.getMin());
    }
}

