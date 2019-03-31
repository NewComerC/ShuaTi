package test;

import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.Arrays;
import java.util.HashSet;
public class Main {

	public static void calculate(Stack<Integer> nums,char ope) {
		int num2=nums.pop();
		int num1=nums.pop();
		if(ope=='+') nums.push(num1+num2);
		else if(ope=='-') nums.push(num1-num2);
		else if(ope=='*') nums.push(num1*num2);
		else nums.push(num1/num2);
	}
    
    public static int getPriority(char ope)
    {
    		if(ope=='*'||ope=='/') return 1;
    		else if(ope=='+'||ope=='-') return 2;
    		else return 0;
    }
	
	
    public static void main(String args[]) {
    	
        Scanner sc = new Scanner(System.in);
       	while(sc.hasNext()) {
       		String str=sc.nextLine();
       		int i=0,j;
       		Stack<Integer> nums=new Stack<>();
       		Stack<Character> signs=new Stack<>();
       		while(i<str.length()) {
	        		if(str.charAt(i)>='0'&&str.charAt(i)<='9'){
	        			j=i;
	        			while(j<str.length()&&str.charAt(j)>='0'&&str.charAt(j)<='9') {
	        				j++;
	        			}
	        			nums.push(Integer.parseInt(str.substring(i, j)));
	        			i=j-1;
	        		}else if(str.charAt(i)=='+'||str.charAt(i)=='-'||str.charAt(i)=='*'||str.charAt(i)=='/')
	        		{
	        			if(signs.isEmpty()) signs.push(str.charAt(i));
	        			else if(getPriority(str.charAt(i))>getPriority(signs.peek())) {
	        				signs.push(str.charAt(i));
	        			}else {
	        				while(!signs.isEmpty()&&getPriority(str.charAt(i))<=getPriority(signs.peek())) {
	        					calculate(nums,signs.pop());
	        				}
	        				signs.push(str.charAt(i));
	        			}
	        		}
	        		i++;
	        }
	        while(!signs.isEmpty()) {
	        		calculate(nums,signs.pop());
	        }
	        System.out.println(nums.peek());
       		
       	}
       	
       	
       	
       		
       		
       		
       		
       		
       		
       		
       		
       		
       		
       		
       		
       		
       		
       		
       		
       		
       		
//       	for(int i=1;i<=n;i++) {
//       		int a=sc.nextInt();
//       		int b=sc.nextInt();
//       		int k=2;
//       		while(k-1<=b) {
//       			if(k*2-1>=b) break;
//       			else k*=2;
//       		}
//       		if(k-1>=a) {
//       			int res=k-1;
//       			System.out.println("Case "+i+": "+res);
//       		}else {
//       			int max=Integer.bitCount(a),res=a;
//       			for(int m=a+1;m<=b;m++) {
//       				int num=Integer.bitCount(m);
//       				if(num>max) {
//       					max=num;
//       					res=m;
//       				}
//       			}
//       			System.out.println("Case "+i+": "+res);
//       		}
//       		
//       	}
        
    }
}
