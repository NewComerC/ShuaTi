package test;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import test.Test.Interval;
import test.Test.ListNode;
import test.Trie;
import static test.Print.*;

public class NewStart {
	
	public int[] searchRange(int[] nums, int target) {
		int[] re=new int[2];
        int i=0,start=-1,end=-1;
        if(nums.length==0)
        {
        		re[0]=start;
    			re[1]=end;
    			return re;
        }
        while(i<nums.length&&nums[i]!=target) i++;
        if(i<nums.length) 
        {
        		start=i;
        		while(i<nums.length&&nums[i]==target) i++;
        		end=i-1;
        }
        re[0]=start;
		re[1]=end;
		return re;
    }
	
//	public int firstMissingPositive(int[] nums) {
//        int i=0;
//        while(i<nums.length)
//        {
//        		if(nums[i]<=0||nums[i]>nums.length||nums[i]==i+1) i++;
//        		else if(nums[nums[i]-1]!=nums[i]) 
//        		{
//        			swap(nums,nums[i]-1,i);
//        		}else 
//        		i++;
//        }
//        for(i=0;i<nums.length;i++)
//        	if(nums[i]!=i+1) break;
//        return i+1;
//    }
	
	public int firstMissingPositive(int[] nums) {
        int i;
        for(i=0;i<nums.length;i++)
        {
        		if(nums[i]<=0||nums[i]>nums.length||nums[i]==i+1) continue;
        		else if(nums[nums[i]-1]!=nums[i]) 
        		{
        			swap(nums,nums[i]-1,i);
        			i--;
        		}
        }
        for(i=0;i<nums.length;i++)
        	if(nums[i]!=i+1) break;
        return i+1;
    }
	
	public void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
		}
	}
	
	public boolean isPalindrome(ListNode head) {
		ListNode slow=head;
		ListNode fast=head;
		while(fast!=null&&fast.next!=null)
		{
			slow=slow.next;
			fast=fast.next.next;
		}
		if(fast!=null) slow=slow.next;
		slow=reverse(slow);
		while(slow!=null)
		{
			if(slow.val!=head.val) return false;
			slow=slow.next;
			head=head.next;
		}
		return true;
    }
	
	//reverse 列表的标准格式
	public ListNode reverse(ListNode head)
	{
		ListNode pre=null;
		while(head!=null)
		{
			ListNode next=head.next;
			head.next=pre;
			pre=head;
			head=next;
		}
		return pre;
	}

	

	
	
	public ListNode deleteDuplicates(ListNode head) {
		if(head==null) return null;
		//create a fake start node to deal with the first node
        ListNode FakeNode=new ListNode(0);
        FakeNode.next=head;
        ListNode pre=FakeNode;
        while(head!=null)
        {
        		while(head.next!=null&&head.val==head.next.val)
        		head=head.next;
        		if(pre.next==head) {			//in the situation that no duplicate=
        			pre=pre.next;
        		}else {
        			pre.next=head.next;
        		}
        		head=head.next;
        }
        return FakeNode.next;
    }
	
	public class Vector2D implements Iterator<Integer> {
		List<List<Integer>> list;
	    public Vector2D(List<List<Integer>> vec2d) {
	        list=new ArrayList<List<Integer>>(vec2d);
	    }

	    @Override
	    public Integer next() {
	    		while(list.get(0).isEmpty()) list.remove(0);
	        List<Integer> l=list.get(0);
	        int re=l.get(0);
	        l.remove(0);
	        
	        return re;
	    }

	    @Override
	    public boolean hasNext() {
	        if(list.isEmpty())
	        	return false;
	        else {
	        		for(List<Integer> l:list)
	        		if(!l.isEmpty())
	        			return true;
	        }
	        	return false;
	    }
	}
	
	
	public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] used=new boolean[s.length()+1];
        used[0]=true;
        for(int i=0;i<s.length()+1;i++)
        for(int j=0;j<i;j++)
        {
        		//很有趣的情况是单词因为substring end index 为实际位置+1，所以取end index i为起始位置则正好是实际下一单词的起始地址
        		if(used[j]&&wordDict.contains(s.substring(j, i)))
        		{
        			used[i]=true;
        			break;
        		}
        }
        return used[s.length()];
    }
	
	
	
	public String longestPalindrome(String s) {
		int l=s.length();
		String res=null;
        boolean[][] dp=new boolean[l][l];
        for(int i=l-1;i>=0;i--)
            for(int j=i;j<l;j++)
            {
            		dp[i][j]=s.charAt(i)==s.charAt(j)&&(j-i<3||dp[i+1][j-1]);
            		if(dp[i][j]&&(res==null||(j-i+1)>res.length()))
            		{
            			res=new String(s.substring(i, j+1));
            		}
            }
        return res;
        
    }
	
	public static String getHint(String secret, String guess) {
		int l=secret.length(),b=0,c=0,i;
        int[] digitS=new int[10];
        int[] digitG=new int[10];
        boolean[] used=new boolean[l];
        for(i=0;i<l;i++)
        {
        		if(secret.charAt(i)==guess.charAt(i)) 
        		{
        			b++;
        			used[i]=true;
        		}else 
        			digitS[secret.charAt(i)-'0']++;
        }
        for(i=0;i<l;i++)
        {
        		if(used[i]) continue;
        		else digitG[guess.charAt(i)-'0']++;
        }
        for(i=0;i<10;i++)
        {
        		c+=Math.min(digitS[i], digitG[i]);
        }
        return new String(b+"A"+c+"B");
    }
	
	public class ZigzagIterator {
		List<Integer> v1;
		List<Integer> v2;
		int i1,i2,flag;
	    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
	        this.v1=new ArrayList<Integer>(v1);
	        this.v2=new ArrayList<Integer>(v2);
	        i1=0;
	        i2=0;
	        flag=0;
	    }

	    public int next() {
	    		int r;
	        if(flag==0&&v1.size()!=0) {
	        		r=v1.get(i1++);
	        		if(i2!=v2.size()) flag=1;
	        }else {
	        		r=v2.get(i2++);
	        		if(i1!=v1.size()) flag=0;
	        }
	        return r;
	    }

	    public boolean hasNext() {
	       if(i1<v1.size()||i2<v2.size()) return true;
	       else return false;
	    }
	}
	
	
	
	
	 public ListNode oddEvenList(ListNode head) {
	        if(head==null||head.next==null||head.next.next==null) return head;
	        ListNode slow=head;
	        ListNode fast=head.next;
	        ListNode even=fast;
	        while(fast!=null&&fast.next!=null)
	        {
	        		ListNode n1=slow.next.next;
	        		ListNode n2=fast.next.next;
	        		slow.next=n1;
	        		fast.next=n2;
	        		slow=n1;
	        		fast=n2;
	        }
	        slow.next=even;
	        return head;
	    }
	 
	
	 public List<String> summaryRanges(int[] nums) {
		 List<String> list=new LinkedList<String>();
		 int i=0;
		 while(i<nums.length)
		 {
			 int start=i,end=i;
			 while(i<nums.length-1&&nums[i+1]==(nums[i]+1)) 
			{
				 i++;
				 end++;
			}
			if(end-start==0) list.add(String.valueOf(nums[i]));
			else list.add(String.format("%d->%d",nums[start],nums[end]));
			i++;
			 
		 }
		 return list;
		 
	 }
	 
	 public boolean containsDuplicate(int[] nums) {
	     HashSet<Integer> h=new HashSet<Integer>();
		 for(int i=0;i<nums.length;i++)
		 {
			 if(!h.contains(nums[i])) h.add(nums[i]);
			 else return true;
		 }
		 return false;
	    }
	 
	 public int maximalSquare(char[][] matrix) {
		 	if(matrix.length==0) return 0;
		 	int row=matrix.length,col=matrix[0].length,max=0;
		 	int[][] dp=new int[row+1][col+1];
		 	for(int i=1;i<=row;i++)
		 		for(int j=1;j<=col;j++)
		 		if(matrix[i-1][j-1]=='1')
		 		{
		 			dp[i][j]=Math.min(dp[i-1][j], Math.min(dp[i-1][j-1],dp[i][j-1]))+1;
		 			if(dp[i][j]>max) max=dp[i][j];
		 			
		 		}
		 		return max*max;
		 
	    }
	 
	 
	 public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
	        int x1,y1,x2,y2,x3,y3,x4,y4;
	        int tempX,tempY;
	        int area1=Math.abs(A-C)*Math.abs(B-D);
	        int area2=Math.abs(E-G)*Math.abs(F-H);
	        int area3=0;
	        if(A>C) {
	        		tempX=A;A=C;C=tempX;
	        		tempY=B;B=D;D=tempY;
	        }
	        if(E>G) {
	        		tempX=E;E=G;G=tempX;
	        		tempY=F;F=H;H=tempY;
	        }
	        if(A<E) {
	        		x1=A;y1=B;x2=C;y2=D;
	        		x3=E;y3=F;x4=G;y4=H;
	        }else {
	        		x1=E;y1=F;x2=G;y2=H;
	        		x3=A;y3=B;x4=C;y4=D;
	        }
	        if(x2<x3||Math.max(y1, y2)<Math.min(y3, y4)||Math.max(y3, y4)<Math.min(y1, y2)) {
	        		return area1+area2;
	        }
	        int length=0,width=0;
	        if(Math.min(y1, y2)<=Math.min(y3, y4)&&Math.max(y1, y2)>=Math.max(y3, y4))
	        		width=Math.abs(y3-y4);
	        else if(Math.min(y1, y2)>=Math.min(y3, y4)&&Math.max(y1, y2)<=Math.max(y3, y4))
	        		width=Math.abs(y1-y2);
	        else if(Math.max(y1, y2)>=Math.max(y3, y4)){
	        		width=Math.abs(Math.min(y1,y2)-Math.max(y3, y4));
	        }else {
	        		width=Math.abs(Math.min(y3,y4)-Math.max(y1, y2));
	        }
	        if(x2>x4) {
	        		length=x4-x3;
//	        		if(Math.min(y1, y2)<Math.min(y3, y4)&&Math.max(y1, y2)>Math.max(y3, y4))
//	        			return area1;
//	        		else if(Math.min(y1, y2)>Math.min(y3, y4)&&Math.max(y1, y2)<Math.max(y3, y4)) 
//	        			return area2;
//	        		else if(Math.max(y1, y2)>Math.max(y3, y4)){
//	        			area3=Math.abs(Math.min(y1,y2)-Math.max(y3, y4))*(x4-x2);
//	        			return area1+area2-area3;
//	        		}else {
//	        			area3=Math.abs(Math.min(y3,y4)-Math.max(y1, y2))*(x4-x2);
//	        			return area1+area2-area3;
//	        }
	        } else {
	        		length=x2-x3;
	        }
	        area3=length*width;
	        return area1+area2-area3;
	    }
	 
	 //n皇后问题
	 public static List<List<String>> solveNQueens(int n) {
	        char[][] board = new char[n][n];
	        for(int i = 0; i < n; i++)
	            for(int j = 0; j < n; j++)
	                board[i][j] = '.';
	        List<List<String>> res = new ArrayList<List<String>>();
	        dfs(board, 0, res);
	        return res;
	    }
	    
	    private static void dfs(char[][] board, int colIndex, List<List<String>> res) {
	        if(colIndex == board.length) {
	            res.add(construct(board));
	            return;
	        }
	        
	        for(int i = 0; i < board.length; i++) {
	            if(validate(board, i, colIndex)) {
	                board[i][colIndex] = 'Q';
	                dfs(board, colIndex + 1, res);
	                board[i][colIndex] = '.';
	            }
	        }
	    }
	    
	    private static boolean validate(char[][] board, int x, int y) {
	        for(int i = 0; i < board.length; i++) {
	            for(int j = 0; j < y; j++) {
	                if(board[i][j] == 'Q' && (x + j == y + i || x + y == i + j || x == i))
	                    return false;
	            }
	        }
	        
	        return true;
	    }
	    
	    private static List<String> construct(char[][] board) {
	        List<String> res = new LinkedList<String>();
	        for(int i = 0; i < board.length; i++) {
	            String s = new String(board[i]);
	            res.add(s);
	        }
	        return res;
	    }
	 
	 
	    public static int removeDuplicates(int[] nums) {
	    		Boolean Dup[]=new Boolean[nums.length];
	    		int i,j,sum=0;
	    		for(i=0;i<nums.length;i++)
	    		{
	    			Dup[i]=false;
	    		}
	        Map<Integer,Integer> map=new HashMap<Integer,Integer>();
	        i=0;
	        while(i<nums.length-sum) {
	        		if(!map.containsKey(nums[i])) 
	        		{
	        			map.put(nums[i], 1);
	        			i++;
	        		}
	        		
	        		else 
	        		{
	        			map.put(nums[i], map.get(nums[i])+1);
	        			if(map.get(nums[i])>2)
	        			{
	        				for(j=i;j<nums.length-sum-1;j++)
	        					nums[j]=nums[j+1];
	        				sum++;
	        				
	        			}else {
	        				i++;
	        			}
	        			
	        		}
	        
	        }
	        return nums.length-sum;
	    }
	    
	    //单链表操作案例
	    public ListNode partition(ListNode head, int x) {
	        ListNode Small=new ListNode(0);
	        ListNode Large=new ListNode(0);
	        ListNode s=Small;
	        ListNode l=Large;
	        while(head!=null)
	        {
	        		if(head.val>=x) 
	        		{
	        			Large.next=head;
	        			Large=Large.next;
	        		}else {
	        			Small.next=head;
	        			Small=Small.next;
	        		}
	        		head=head.next;
	        }
	        Small.next=l.next;
	        Large.next=null;
	        return s.next;
	    }
	    
	    class RandomizedSet {
	    		Set<Integer> s;
	        /** Initialize your data structure here. */
	        public RandomizedSet() {
	            s=new HashSet<Integer>();
	        }
	        
	        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
	        public boolean insert(int val) {
	            if(s.contains(val)) return false;
	            else {
	            		s.add(val);
	            		return true;
	            }
	        }
	        
	        /** Removes a value from the set. Returns true if the set contained the specified element. */
	        public boolean remove(int val) {
	            if(!s.contains(val)) return false;
	            else {
	            		s.remove(val);
	            		return true;
	            }
	        }
	        
	        /** Get a random element from the set. */
	        public int getRandom() {
	            Random r=new Random();
	            int ran=r.nextInt(s.size());
	            int i = 0;  
	            for (int num : s) {  
	                if(i==ran){  
	                    return num;  
	                }  
	                i++;  
	            }  
	            return -1;
	        }
	    }
	    
	    
	    public static int calculate(String s) {
	        Stack<Character> ope=new Stack<Character>();
	        Stack<Integer> nums=new Stack<Integer>();
	        int i=0,j;
	        while(i<s.length()) {
	        		if(s.charAt(i)>='0'&&s.charAt(i)<='9'){
	        			j=i;
	        			while(j<s.length()&&s.charAt(j)>='0'&&s.charAt(j)<='9') {
	        				j++;
	        			}
	        			nums.push(Integer.parseInt(s.substring(i, j)));
	        			i=j-1;
	        		}else if(s.charAt(i)=='(') 
	        			ope.push('(');
	        		else if(s.charAt(i)=='+'||s.charAt(i)=='-')
	        		{
	        			if(!ope.isEmpty()&&ope.peek()!='(')
	        			calculate(nums,ope.pop());
	        			ope.push(s.charAt(i));
	        		}else if(s.charAt(i)==')') {
	        			while(ope.peek()!='(') {
	        				calculate(nums,ope.pop());
	        			}
	        			ope.pop();
	        		}
	        		i++;
	        }
	        while(!ope.isEmpty()) {
	        		calculate(nums,ope.pop());
	        }
	        return nums.peek();
	    }   
	    
	    public static int calculate1(String s) {
	    	Stack<Character> ope=new Stack<Character>();
	        Stack<Integer> nums=new Stack<Integer>();
	        int i=0,j;
	        while(i<s.length()) {
	        		if(s.charAt(i)>='0'&&s.charAt(i)<='9'){
	        			j=i;
	        			while(j<s.length()&&s.charAt(j)>='0'&&s.charAt(j)<='9') {
	        				j++;
	        			}
	        			nums.push(Integer.parseInt(s.substring(i, j)));
	        			i=j-1;
	        		}else if(s.charAt(i)=='+'||s.charAt(i)=='-'||s.charAt(i)=='*'||s.charAt(i)=='/')
	        		{
	        			if(ope.isEmpty()) ope.push(s.charAt(i));
	        			else if(getPriority(s.charAt(i))>getPriority(ope.peek())) {
	        				ope.push(s.charAt(i));
	        			}else {
	        				while(!ope.isEmpty()&&getPriority(s.charAt(i))<=getPriority(ope.peek())) {
	        					calculate(nums,ope.pop());
	        				}
	        				ope.push(s.charAt(i));
	        			}
	        		}
	        		i++;
	        }
	        while(!ope.isEmpty()) {
	        		calculate(nums,ope.pop());
	        }
	        return nums.peek();
	    }
	    
//	    public static void calculate(Stack<Long> nums,char ope) {
//	    		Long num2=nums.pop();
//	    		Long num1=nums.pop();
//	    		if(ope=='+') nums.push(num1+num2);
//	    		else if(ope=='-') nums.push(num1-num2);
//	    		else if(ope=='*') nums.push(num1*num2);
//	    		else nums.push(num1/num2);
//	    }
	    
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
	    		if(ope=='*'||ope=='/') return 2;
	    		else if(ope=='+'||ope=='-') return 1;
	    		else return 0;
	    }
	    
//	    public static int calculate2(String s) {
//	    	Stack<Character> ope=new Stack<Character>();
//	        Stack<Long> nums=new Stack<Long>();
//	        int i=0,j;
//	        while(i<s.length()) {
//	        		if(s.charAt(i)>='0'&&s.charAt(i)<='9'){
//	        			j=i;
//	        			while(j<s.length()&&s.charAt(j)>='0'&&s.charAt(j)<='9') {
//	        				j++;
//	        			}
//	        			nums.push(Long.parseLong(s.substring(i, j)));
//	        			i=j-1;
//	        		}else if(s.charAt(i)=='(') 
//	        			ope.push('(');
//	        		else if(s.charAt(i)=='+'||s.charAt(i)=='-'||s.charAt(i)=='*'||s.charAt(i)=='/')
//	        		{
//	        			if(ope.isEmpty()) ope.push(s.charAt(i));
//	        			else if(getPriority(s.charAt(i))>getPriority(ope.peek())) {
//	        				ope.push(s.charAt(i));
//	        			}else {
//	        				while(!ope.isEmpty()&&getPriority(s.charAt(i))<=getPriority(ope.peek())) {
//	        					calculate(nums,ope.pop());
//	        				}
//	        				ope.push(s.charAt(i));
//	        			}
//	        		}else if(s.charAt(i)==')') {
//	        			while(ope.peek()!='(') {
//	        				calculate(nums,ope.pop());
//	        			}
//	        			ope.pop();
//	        		}
//	        		i++;
//	        }
//	        while(!ope.isEmpty()) {
//	        		calculate(nums,ope.pop());
//	        }
//	        return nums.peek().intValue();
//	    }
	    
//	    public int shortestDistance(String[] words, String word1, String word2) {
//	    		int min=Integer.MAX_VALUE;
//	        for(int i=0;i<words.length;i++)
//	        {
//	        		if(words[i].equals(word1)) 
//	        			for(int j=0;j<words.length;j++) {
//	        				if(words[j].equals(word2)) 
//	        					if(Math.abs(i-j)<min) min=Math.abs(i-j);
//	        				
//	        			}
//	        		
//	        }
//	        return min;
//	    }
	    
	    //better method
	    public int shortestDistance(String[] words, String word1, String word2) {
    			int min=Integer.MAX_VALUE;
    			int i1=-1,i2=-1;
    			for(int i=0;i<words.length;i++)
    			{
    				if(words[i].equals(word1)) {
    					i1=i;
    				}else if(words[i].equals(word2)) {
    					i2=i;
    				}
    				if(i1!=-1&&i2!=-1)
    	        		min=Math.min(min, Math.abs(i1-i2));
    			}
        		
        		return min;
        		
        		
    }
	    
	    public int islandPerimeter(int[][] grid) {
	    		int re=0;
	        for(int i=0;i<grid.length;i++)
	        	for(int j=0;j<grid[0].length;j++)
	        	{
	        		if(grid[i][j]==1) {
	        			int cur=4;
	        			if(i>0&&grid[i-1][j]==1) cur--;
	        			if(i<grid.length-1&&grid[i+1][j]==1) cur--;
	        			if(j<grid[0].length-1&&grid[i][j+1]==1) cur--;
	        			if(j>0&&grid[i][j-1]==1) cur--;
	        			re+=cur;
	        		}
	        		
	        	}
	        return re;
	    }
	    
	    
	  //取三个数系列
		public int threeSumClosest(int[] nums, int target) {
	        int i=0,j,k,sum=0,min=1000;
			int l=nums.length;
		    if(nums.length<3) 
		    {
		    		for(i=0;i<nums.length;i++)
		    		sum+=nums[i];
		    		return sum;
		    }
		    Arrays.sort(nums);
			while(i<l-2)
			{
				j=i+1;
				k=l-1;
				while(j<k) {
					sum=nums[i]+nums[j]+nums[k];
					if(sum==target) return target;
					if(Math.abs(sum-target)<Math.abs(min)) 
					{
						
						min=sum-target;
					}
					if(sum<=target) while(nums[j]==nums[++j]&&j<k);
					if(sum>=target) while(nums[k]==nums[--k]&&j<k);
				}
				while(nums[i]==nums[++i]&&i<l-2);
			}
			return target+min;
	    }
		
		public int threeSumSmaller(int[] nums, int target) {
			int l=nums.length;
		    if(nums.length<3) return 0;
		    int i=l-1,j,k,sum=0,count=0;
		    Arrays.sort(nums);
			while(i>=2)
			{
				j=i-1;
				k=0;
				while(j>k) {
					sum=nums[i]+nums[j]+nums[k];
					if(sum<target) {
						count+=j-k;
						k++;
					}else if(sum>=target) 
						j--;
				}
				i--;
			}
			return count;
	    }
		
		public int triangleNumber(int[] nums) {
			int l=nums.length;
		    if(l<3) return 0;
		    int i=l-1,j,k,side,count=0;
		    Arrays.sort(nums);
			while(i>=2)
			{
				j=i-1;
				k=0;
				side=nums[i];
				while(j>k) {
					if(nums[j]+nums[k]>side) {
						count+=j-k;
						j--;
					}else
						k++;
				}
				i--;
			}
			return count;
	    }
	   
		public int findLengthOfLCIS(int[] nums) {
			if(nums.length<=1) return nums.length;
	        int i=0,j,max=0;
	        while(i<nums.length-1) {
	        		j=i;
	        		while(i<nums.length-1&&nums[i]<nums[i+1]) i++;
	        		if(i-j+1>max) max=i-j+1;
	        		i++;
	        }
	       return max;
 	    }
//		if(nums.length<=1) return nums.length;
//		int[] dp=new int[nums.length+1];
//        dp[1]=1;
//        for(int i=2;i<=nums.length;i++)
//        {
//        		dp[i]=1;
//        		for(int j=1;j<i;j++)
//        		if(nums[i-1]>=nums[j-1])
//        		{
//        			dp[i]=Math.max(dp[i], dp[j]+1);
//        		}
//        }
//        int max=1;
//        for(int i=1;i<=nums.length;i++)
//        {
//        		if(dp[i]>max) max=dp[i];
//        }
//       return max;
		
		//链表reverse！！！！
		public ListNode reverseBetween(ListNode head, int m, int n) {
			ListNode dummy=new ListNode(0);
			dummy.next=head;
			ListNode pre=dummy;
			int i;
			for(i=0;i<m-1;i++) {
				pre=pre.next;
			}
			
			ListNode start=pre.next;
			ListNode then=start.next;
			for(i=0;i<n-m;i++) {
				start.next=then.next;
				then.next=pre.next;
				pre.next=then;
				then=start.next;
			}
			
			return dummy.next;
	    }
		
		public int maxProfit(int[] prices) {
	        int cur=0,max=0;
	        for(int i=1;i<prices.length;i++) {
	        		if(prices[i]-prices[i-1]>0) {
	        			cur+=prices[i]-prices[i-1];
	        		}
	        		if(cur>max) {
	        			max=cur;
	        		}
	        }
	        return cur;
	    }
	
		
		public int findDuplicate(int[] nums) {
	        Arrays.sort(nums);
	        for(int i=0;i<nums.length-1;i++) {
	        		if(nums[i]==nums[i+1]) {
	        			return nums[i];
	        		}
	        }
	        return -1;
	    }
		
		public int minCost(int[][] costs) {
			if(costs==null||costs.length==0) return 0;
			int l=costs.length;
			
	        for(int i=1;i<costs.length;i++) {
	        		costs[i][0]+=Math.min(costs[i-1][1],costs[i-1][2]);
	        		costs[i][1]+=Math.min(costs[i-1][0],costs[i-1][2]);
	        		costs[i][2]+=Math.min(costs[i-1][0],costs[i-1][1]);
	        }
	        return Math.min(costs[l-1][0],Math.min(costs[l-1][1], costs[l-1][2]));
	        
	    }
		
		//排序后，将2,3 4,5依次对换
		public void wiggleSort(int[] nums) {
	        Arrays.sort(nums);
	        for(int i=1;i<nums.length-1;i=i+2) {
	        		int temp=nums[i];
	        		nums[i]=nums[i+1];
	        		nums[i+1]=temp;
	        }
	        
	    }
		
		
		public int lastRemaining(int n) {
			int step=1;
			int remaining=n;
			int head=1;
			Boolean left=true;
			while(remaining>1) {
				if(left||remaining%2==1) {
					head+=step;
				}
				step*=2;
				remaining/=2;
				left=!left;
			}
			return head;
	    }
		
		
		public int hammingWeight(int n) {
			//转化成二进制
			String num=Integer.toBinaryString(n);
			
			int res = 0;
			for(int i=0;i<32;i++) {
				if((n&1)==1) res++;
				n>>=1;
			}
			return res;
			
			//获得二进制数种1的个数
//			return Integer.bitCount(n);
			
			
			
	    }
		
		public static int findMin(int[] nums) {        
			int left=0,right=nums.length-1;
			while(left<right) {
				int mid=left+(right-left)/2;
				if(nums[mid]>nums[right]) {
					left=mid+1;
				}else {
					//mid<right所以最小值可能还是mid所以right=mid
					right=mid;
				}
			}
		
			return nums[right];
		}
		
		public List<String> findMissingRanges(int[] nums, int lower, int upper) {
			List<String> res=new LinkedList<>();
			int l=nums.length;
			
			if(nums.length==0) {
				if(lower<=upper-1) {
					res.add(String.format("%d->%d", lower,upper));
				}else {
					res.add(String.valueOf(lower));
				}
				return res;
			}
			
			if((long)lower<(long)nums[0]-1) {
				res.add(String.format("%d->%d", lower,nums[0]-1));
			}else if((long)lower==(long)(nums[0]-1)) {
				res.add(String.valueOf(lower));
			}
			
			for(int i=0;i<nums.length-1;i++) {
				if((long)nums[i]<(long)nums[i+1]-2) {
					res.add(String.format("%d->%d", nums[i]+1,nums[i+1]-1));
				}else if((long)nums[i]==(long)(nums[i+1]-2)) {
					res.add(String.valueOf(nums[i]+1));
				}
			}
			
			if((long)nums[l-1]<(long)upper-1) {
				res.add(String.format("%d->%d", nums[l-1]+1,upper));
			}else if((long)nums[l-1]==(long)(upper-1)) {
				res.add(String.valueOf(upper));
			}
			
			return res;
		}
		
	
	    
	    public List<String> findRepeatedDnaSequences(String s) {
	        HashSet<String> seen=new HashSet<>();
	        HashSet<String> result=new HashSet<>();
	        for(int i=0;i+9<s.length();i++) {
	        		if(seen.contains(s.substring(i, i+10))) {
	        			result.add(s.substring(i, i+10));
	        		}else {
	        			seen.add(new String(s.substring(i, i+10)));
	        		}
	        }
	        return new ArrayList<>(result);
	    }
	    
	    public int trap(int[] height) {
	    		int res=0;
	        for(int i=1;i<height.length-1;i++) {
	        		int max_left=0,max_right=0;
	        		for(int j=i;j>=0;j--) {
	        			max_left=Math.max(max_left, height[j]);
	        		}
	        		for(int j=i;j<height.length;j++) {
	        			max_right=Math.max(max_right, height[j]);
	        		}
	        		res+=Math.min(max_left, max_right)-height[i];
	        }
	        return res;
	    }
		
	    class WordDistance {

	    		private HashMap<String, List<Integer>> map;
	    		
	        public WordDistance(String[] words) {
	        		map=new HashMap<>();
	        		for(int i=0;i<words.length;i++) {
	        			List<Integer> list=map.getOrDefault(words[i], new ArrayList<>());
	        			list.add(i);
	        			map.put(words[i], list);
	        		}
	        }
	        
	        public int shortest(String word1, String word2) {
	        	
	        		List<Integer> l1=map.get(word1);
	        		List<Integer> l2=map.get(word2);
	        		
	        		int min=Integer.MAX_VALUE;
	        		for(int i=0;i<l1.size();i++) {
	        			for(int j=0;j<l2.size();j++) {
	        				min=Math.min(min, Math.abs(l1.get(i)-l2.get(j)));
	        			}
	        		}
        		
	        		return min;
	        	}
	    	}
	    
	    private class stringNumberComparator implements Comparator<String>{
	    		@Override
	    		public int compare(String a,String b) {
	    			String num1=a+b;
	    			String num2=b+a;
	    			return	num2.compareTo(num1);
	    		}
	    }
	    
	    public String largestNumber(int[] nums) {
	    		String[] strs=new String[nums.length];
	        for(int i=0;i<nums.length;i++) {
	        		strs[i]=String.valueOf(nums[i]);
	        }
	        
	        Arrays.sort(strs,new stringNumberComparator());
	        
	        if(strs[0].equals("0")) return "0";
	        else {
	        		StringBuilder res=new StringBuilder();
	        		for(int i=0;i<strs.length;i++) {
	        			res.append(strs[i]);
	        		}
	        		return res.toString();
	        }
	    }
	    
	    public static int nthUglyNumber(int n) {
	       	int[] ugly=new int[n];
	       	ugly[0]=1;
	       	int num2=2,num3=3,num5=5;
	       	//这里的index是在ugly数组中的因为所有ugly数字的因子都是2,3,5即都是数组中的数字，所以不是简单的+1的方式
	       	int index2=0,index3=0,index5=0;
	       	for(int i=1;i<n;i++) {
	       		ugly[i]=Math.min(num2, Math.min(num3, num5));
	       		if(ugly[i]==num2) {
	       			num2=2*ugly[++index2];
	       		}
	       		if(ugly[i]==num3) {
	       			num3=3*ugly[++index3];
	       		}
	       		if(ugly[i]==num5) {
	       			num5=5*ugly[++index5];
	       		}
	       	}
	       	return ugly[n-1];
	 }
	    
	    
	    public int reverseBits(int n) {
	        int res=0;
	        for(int i=0;i<32;i++) {
	        		res<<=1;
	        		//0&1=0,1&1=1,所以&结果就是n二进制个位数字
	        		if((n&1)==1) {
	        			res+=1;
	        		}
	        		n>>=1;
	        }
	        return res;
	    }
	    
	    public boolean isOneEditDistance(String s, String t) {
	        for(int i=0;i<Math.min(s.length(),t.length());i++) {
	        		if(s.charAt(i)!=t.charAt(i)) {
	        			if(s.length()==t.length()) return s.substring(i+1, s.length()).equals(t.substring(i+1, t.length()));
	        			else if(s.length()<t.length()) {
	        				return s.substring(i,s.length()).equals(t.substring(i+1,t.length()));
	        			}else {
	        				return s.substring(i+1,s.length()).equals(t.substring(i,t.length()));
	        			}
	        		}
	        }
	        return Math.abs(s.length()-t.length())==1;
	    }
	    
	    public static String toLowerCase(String str) {
	    		char[] res=str.toCharArray();
	    		
	        for(int i=0;i<res.length;i++) {
	        		if(res[i]>='A'&&res[i]<='Z')
	        		res[i]+=32;
	        }
	        return String.valueOf(res);
	    }
	    
	    public static int[] intersect(int[] nums1, int[] nums2) {
	    		
	    		boolean[] used=new boolean[nums1.length];
	    		int l=0;
	    		for(int i=0;i<nums2.length;i++)
	    		{
	    			for(int j=0;j<nums1.length;j++) {
	    				if(!used[j]&&nums2[i]==nums1[j]) {
	    					used[j]=true;
	    					l++;
	    					break;
	    				}
	    			}
	    		}
	    		int[] res=new int[l];
	    		int k=0;
	    		for(int i=0;i<used.length;i++) {
	    				if(used[i]==true) {
	    					res[k++]=nums1[i];
	    				}
	    		}
	    		return res;
	        
	    }
	    
	    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//	        List<ListNode> list=new ArrayList<>();
//	        while(headA!=null) {
//	        		list.add(headA);
//	        		headA=headA.next;
//	        }
//	        while(headB!=null) {
//	        		if(list.contains(headB)) {
//	        			return headB;
//	        		}
//	        		headB=headB.next;
//	        }
//	        return null;
	    	
	    		if(headA==null||headB==null) return null;
	    		ListNode a=headA;
	    		ListNode b=headB;
	    		while(a!=b) {
	    			a=a==null?headB:a.next;
	    			b=b==null?headA:b.next;
	    		}
	    		return a;
	    }
	    
	    public int getSum(int a, int b) {
	    		
	        while(b!=0) {
	        		int carry=a&b;
	        		a=a^b;
	        		b=carry<<1;
	        }
	        return a;
	    }
	   
	    
	    public static int myAtoi(String str) {
	        int i=0,flag=0,ans;
	        while(i<str.length()&&str.charAt(i)==' ') {
	        		i++;
	        }
	        if(i==str.length()) return 0;
	        if(str.charAt(i)=='-') {
        			flag=1;
        			i++;
	        }else if(str.charAt(i)=='+') {
	        		i++;
	        }
	        if(i==str.length()) return 0;
	        str=str.substring(i, str.length());
	        if(str.charAt(0)<'0'||str.charAt(0)>'9') {
        			return 0;
	        }
	        
	        i=0;
	        while(i<str.length()&&str.charAt(i)=='0') {
	        		i++;
	        }
	        str=str.substring(i,str.length());
	        
	        if(str.length()==0||!(str.charAt(0)>='0'&&str.charAt(0)<='9')) return 0;
	        
	        StringBuilder res=new StringBuilder();
	        
	        
	        for(i=0;i<str.length();i++) {
	        		if(str.charAt(i)>='0'&&str.charAt(i)<='9') res.append(str.charAt(i));
	        		else break;
	        }
	        
	        if(res.length()>10) {
	        		if(flag==0) return Integer.MAX_VALUE;
	        		else return Integer.MIN_VALUE;
	        }
	        
	        if(flag==0&&Long.parseLong(res.toString())>=(long)Integer.MAX_VALUE) {
	        		ans=Integer.MAX_VALUE;
	        }else if (flag==1 && Long.parseLong(res.toString())*-1<=(long)Integer.MIN_VALUE) {
	        		ans=Integer.MIN_VALUE;
	        }else {
	        		
	        		ans=Integer.parseInt(res.toString());
	        		if(flag==1) ans=-ans;
	        }
	        return ans;
	        
	        
	    }
	    
	    
	    public ListNode mergeKLists(ListNode[] lists) {
	        Queue<ListNode> queue=new PriorityQueue<>((a,b)->a.val-b.val);
	        
	        ListNode head=new ListNode(0),res=head;
	        
	        for(int i=0;i<lists.length;i++) {
	        		if(lists[i]!=null) {
	        			queue.offer(lists[i]);
	        		}
	        }
	        
	        while(!queue.isEmpty()) {
	        		head.next=queue.poll();
	        		head=head.next;
	        		if(head.next!=null)
	        		queue.offer(head.next);
	        }
	        
	        return res.next;
	        
	    }
	    
	    public static int divide(int dividend, int divisor) {
	    	
	        return ((int)(dividend*Math.pow(divisor, -1)));
	    }
	    
	    
	    public static boolean isValidSudoku(char[][] board) {
	        Set<String> set=new HashSet<>();
	        for(int i=0;i<board.length;i++) {
	        		for(int j=0;j<board[i].length;j++) {
	        			if(board[i][j]!='.') {
	        				String num="("+board[i][j]+")";
		        			if(!set.add(i+num)||!set.add(num+j)||!set.add(i/3+num+j/3))
		        				return false;
	        			}
	        		}
	        }
	        return true;
	    }
	    
	    public static int largestRectangleArea(int[] heights) {
	    	Stack<Integer> s = new Stack<>();
	        int max_area = 0; // 最大矩形面积
	        int tp; // 栈顶
	        int area_with_top;

	        int i = 0;
	        int n = heights.length;
	        while (i < n) {
	            if (s.empty() || heights[s.peek()] <= heights[i]) {
	                s.push(i++);
	            } else {
	                tp = s.pop();
	                area_with_top = heights[tp] * (s.empty() ? i : i - s.peek() - 1);
	                max_area = Math.max(max_area, area_with_top);
	            }
	        }

	        while (!s.empty()) {
	            tp = s.pop();
	            area_with_top = heights[tp] * (s.empty() ? i : i - s.peek() - 1);
	            max_area = Math.max(max_area, area_with_top);
	        }
	        return max_area;
	    }
	    
	    public static boolean verifyPreorder(int[] preorder) {
			if(preorder==null||preorder.length==0) return true;
		       return judge(preorder,0,preorder.length-1);
	    }
		 
		 public static boolean judge(int[] sequence,int left,int right) {
			 if(left>=right) return true;
			 int i=left,rootVal=sequence[left];
			 while(i<=right&&sequence[i]<=rootVal) {
				 i++;
			 }
//			 if(i!=right)
			 for(int j=i;j<=right;j++) {
				 if(sequence[j]<rootVal) return false;
			 }
			 return judge(sequence,left+1,i-1)&&judge(sequence,i,right);
		 }
	    
		 public static String reverseWords(String s) {
			 	if(s==null||s.length()==0) return s;
			 	int i=0,j=s.length()-1;
			 	while(i<=j&&s.charAt(i)==' ') i++;
			 	if(i==s.length()) return "";
			 	while(s.charAt(j)==' ') j--;
			 	
			 	s=s.substring(i, j+1);
			 	
			 	if(s==null||s.length()==0) return s;
		        String[] str=s.split("\\s+");
		        
		        StringBuilder res=new StringBuilder();
		        for(i=str.length-1;i>=0;i--) {
		        		res.append(str[i]+" ");
		        }
		        res.deleteCharAt(res.length()-1);
		        return res.toString();
		        
		    }
		 
		 
		 
		 
		 public static int compareVersion(String version1, String version2) {
		        String[] str1=version1.split("\\.");
		        if(str1.length==0) str1=new String[] {version1};
		        String[] str2=version2.split("\\.");
		        if(str2.length==0) str2=new String[] {version2};
		        int i=0;
		        while(i<str1.length&&i<str2.length) {
		        		//形如2.10与2.2直接比较字符串不可行，因为字符串比较是从首字母逐个比较，因此2仍然比10大
//		        		int j=0;
//		        		String p1=str1[i];
//		        		while(j<p1.length()&&p1.charAt(j)=='0') j++;
//		        		if(j==p1.length()) p1=new String("0");
//		        		else p1=p1.substring(j, p1.length());
//		        		
//		        		String p2=str2[i];
//		        		
//		        		j=0;
//		        		while(j<p2.length()&&p2.charAt(j)=='0') j++;
//		        		if(j==p2.length()) p2=new String("0");
//		        		else p2=p2.substring(j, p2.length());
		        		
		        		int p1=Integer.parseInt(str1[i]);
		        		int p2=Integer.parseInt(str2[i]);
		        		int temp=p1-p2;
		        		if(temp>0) {
		        			return 1;
		        		}else if(temp<0) {
		        			return -1;
		        		}
		        		i++;
		        }
		        
		        if(i<str1.length) {
		        		while(i<str1.length) {
		        			if(Integer.parseInt(str1[i++])==0) {
		        				
		        			}else {
		        				return 1;
		        			}
		        		}
		        		return 0;
		        }
		        
		        if(i<str2.length) {
	        		while(i<str2.length) {
	        			if(Integer.parseInt(str2[i++])==0) {
	        			}else {
	        				return -1;
	        			}
	        		}
	        		
	        }
		        return 0;
		    }
		 
		 
		 public static boolean canFinish(int numCourses, int[][] prerequisites) {
		        int[][] rela=new int[numCourses][numCourses];
		        int[] precourses=new int[numCourses];
		        for(int i=0;i<prerequisites.length;i++) {
		        		int c=prerequisites[i][0];
		        		int pre=prerequisites[i][1];
		        		if(rela[pre][c]==0) {
		        			rela[pre][c]=1;
			        		precourses[c]++;
		        		}
		        }
		        
		        int count=0;
		        Queue<Integer> queue=new LinkedList<Integer>();
		        for(int i=0;i<numCourses;i++)
		        	if(precourses[i]==0) queue.offer(i);
		        
		        while(!queue.isEmpty()) {
		        		int course=queue.poll();
		        		count++;
		        		for(int i=0;i<numCourses;i++) {
		        			if(rela[course][i]==1) {
		        				precourses[i]--;
		        				if(precourses[i]==0) {
			        				queue.offer(i);
			        			}
		        			}
		        			
		        		}
		        }
		        
		        return count==numCourses;
		        
		    }
		 
		 public int[] findOrder(int numCourses, int[][] prerequisites) {
			 int[] res=new int[numCourses];
			 int pos=0;
			 int[][] rela=new int[numCourses][numCourses];
		        int[] precourses=new int[numCourses];
		        for(int i=0;i<prerequisites.length;i++) {
		        		int c=prerequisites[i][0];
		        		int pre=prerequisites[i][1];
		        		if(rela[pre][c]==0) {
		        			rela[pre][c]=1;
			        		precourses[c]++;
		        		}
		        }
		        
		        int count=0;
		        Queue<Integer> queue=new LinkedList<Integer>();
		        for(int i=0;i<numCourses;i++)
		        	if(precourses[i]==0) {
		        		res[pos++]=i;
		        		queue.offer(i);
		        	}
		        
		        while(!queue.isEmpty()) {
		        		int course=queue.poll();
		        		count++;
		        		for(int i=0;i<numCourses;i++) {
		        			if(rela[course][i]==1) {
		        				precourses[i]--;
		        				if(precourses[i]==0) {
		        					res[pos++]=i;
			        				queue.offer(i);
			        			}
		        			}
		        			
		        		}
		        }
		        if(count==numCourses) {
		        		return res;
		        		
		        }else {	
		        		return new int[0];
		        }
		        
	
		    }
		 
		 public void reverseWords(char[] str) {
			 	if(str.length==0) return;
		        reverse(str,0,str.length-1);
		        int i=0;
		        while(i<str.length) {
		        		int j=i;
		        		while(i<str.length&&str[i]!=' '){
		        			i++;
		        		}
		        		reverse(str,j,i-1);
		        		i++;
		        }
		    }
		 
		 public void reverse(char[] str,int start,int end) {
			 for(int i=start,j=end;i<=start+(end-start)/2;i++,j--) {
				 char temp=str[i];
				 str[i]=str[j];
				 str[j]=temp;
			 }
		 }
		 
		 public boolean containsNearbyDuplicate(int[] nums, int k) {
		        Map<Integer, Integer> map=new HashMap<>();
		        for(int i=0;i<nums.length;i++) {
		        		int n=nums[i];
		        		if(!map.containsKey(n)) {
		        			map.put(n, i);
		        		}else {
		        			if(i-map.get(n)<=k) {
		        				return true;
		        			}else {
		        				map.put(n, i);
		        			}
		        		}
		        }
		        return false;
		    }
		 
		 
		 public static List<Integer> majorityElement(int[] nums) {
			 	List<Integer> res=new ArrayList<>();
			 	if(nums.length==0) return res;
		        //moore vote
			 	//设定两个候选人1,2,并初始化他们的票数为0，遍历数组，如果投给1或者2，票数count增加，否则，看其中一人票数是否为0，
			 	//若有一人为0，则取代他成为候选人，若两人都不为0，则两位候选人票数都减一。
			 	//但只能找到最多的两个数字，还需要遍历整个数组来看是否满足大于n/3的要求
			 	int candidate1=nums[0];
			 	int candidate2=nums[0];
			 	int count1=0;
			 	int count2=0;
			 	
			 	
			 	for(int num:nums) {
			 		if(candidate1==num) {
			 			count1++;
			 			continue;
			 		}
			 		
			 		if(candidate2==num) {
			 			count2++;
			 			continue;
			 		}
			 		
			 		if(count1==0) {
			 			candidate1=num;
			 			count1++;
			 			continue;
			 		}
			 		
			 		if(count2==0) {
			 			candidate2=num;
			 			count2++;
			 			continue;
			 		}
			 		count1--;
			 		count2--;
			 	}
			 	count1=0;
			 	count2=0;
			 	for(int i=0;i<nums.length;i++) {
			 		if(nums[i]==candidate1) {
			 			count1++;
			 		}else if(nums[i]==candidate2) {
			 			count2++;
			 		}
			 	}
			 	
			 	
			 	if(count1>nums.length/3) res.add(candidate1);
			 	if(count2>nums.length/3) res.add(candidate2);
			 	return res;
		    }
		 
		 
		 
		 public int[] singleNumber(int[] nums) {
			 int[] res=new int[2];
			 if(nums.length<2) return null;
		        int flag=1,temp=0;
		        for(int i=0;i<nums.length;i++)
		        	temp^=nums[i];
		        while((temp&flag)==0) flag<<=1;
		        
		        for(int i=0;i<nums.length;i++) {
		        		if((nums[i]&flag)==0) res[1]^=nums[i];
		        		else res[0]^=nums[i];
		        }
		        return res;
		    }
		 
		 public int shortestWordDistance(String[] words, String word1, String word2) {
 			int min=Integer.MAX_VALUE;
 			int i1=-1,i2=-1,flag=0;
 			
 			if(word1.equals(word2)) {
 				for(int i=0;i<words.length;i++)
 	 			{
 	 				if(words[i].equals(word1)) {
 	 					if(flag==0) {
 	 						i1=i;
 	 						flag=1;
 	 					}else {
 	 						i2=i;
 	 						flag=0;
 	 					}
 	 				}
 	 				if(i1!=-1&&i2!=-1)
 	 	        		min=Math.min(min, Math.abs(i1-i2));
 	 			}
 			}else {
 				for(int i=0;i<words.length;i++)
 	 			{
 	 				if(words[i].equals(word1)) {
 	 					i1=i;
 	 				}else if(words[i].equals(word2)) {
 	 					i2=i;
 	 				}
 	 				if(i1!=-1&&i2!=-1)
 	 	        		min=Math.min(min, Math.abs(i1-i2));
 	 			}
 			}
     		return min;
	
     	}
		 
		 
		 
		 
		 public boolean isStrobogrammatic(String num) {
			 	if(num==null||num.length()==0) return true;
		        Map<Character, Character> map=new HashMap<>();
		        map.put('0', '0');
		        map.put('1', '1');
		        map.put('6', '9');
		        map.put('8', '8');
		        map.put('9', '6');
		        
		        if(num.length()==1) {
		        		char c=num.charAt(0);
		        		if(c=='1'||c=='8') return true;
		        		else return false;
		        }
		        
		        for(int i=0,j=num.length()-1;i<=(num.length()-1)/2;i++,j--) {
		        		Character temp=map.get(num.charAt(i));
		        		if(temp==null) return false;
		        		if(num.charAt(j)!=temp.charValue()) {
		        			return false;
		        		}
		        }
		        return true;
		    }
		 
		 public List<String> findStrobogrammatic(int n) {
		        List<String> res=new LinkedList<String>();
		        res=findStr(n,n);
		        return res;
		    }
		 
		 public List<String> findStr(int m,int n){
			 if(m==0) return new LinkedList<>(Arrays.asList(""));
			 if(m==1) return new LinkedList<>(Arrays.asList("0","1","8"));
			 
			 List<String> child=findStr(m-2,n);
			 List<String> res=new LinkedList<>();
			 for(String num:child) {
			 if(n!=m) res.add("0"+num+"0");
		     	res.add("1"+num+"1");
		     	res.add("6"+num+"9");
		     	res.add("8"+num+"8");
		     	res.add("9"+num+"6");
			 }
			 return res;
		 }
		 
		 class NumArray {

			    private int[] sum;
			    public NumArray(int[] nums) {
			    		int l=nums.length;
			    		if(l==0) return;
			    		sum=new int[l];
			    		sum[0]=nums[0];
			    		for(int i=1;i<l;i++)
			    		{
			    			sum[i]=sum[i-1]+nums[i];
			    		}
			    
			    }
			    
			    public int sumRange(int i, int j) {
			    		if(i==0) return sum[j];
			    		else
			    		return sum[j]-sum[i-1];
			    }
			}
		 
		 
		 class NumMatrix {

			 
			 	int[][] sum;
			 	
			    public NumMatrix(int[][] matrix) {
			    		if(matrix.length==0||matrix[0].length==0) return;
			        sum=new int[matrix.length+1][matrix[0].length+1];
			        for(int i=0;i<matrix.length;i++) {
			        		for(int j=0;j<matrix[0].length;j++) {
			        			sum[i+1][j+1]=sum[i+1][j]+sum[i][j+1]-sum[i][j]+matrix[i][j];
			        		}
			        }
			    }
			    
			    public int sumRegion(int row1, int col1, int row2, int col2) {
			        return sum[row2+1][col2+1]-sum[row2+1][col1]-sum[row1][col2+1]+sum[row1][col1];
			    }
			}
		 
		 
		 
		 
		   	public int[] productExceptSelf(int[] nums) {
		   		int[] B=new int[nums.length];
	    		int sum=1,i;
	    		for(i=0;i<nums.length;i++) {
	    			sum*=nums[i];
	    		}
	    		
	    		if(sum==0) {
	    			int pos=0,num=0;
	    			sum=1;
	    			for(i=0;i<nums.length;i++) {
	    				if(nums[i]==0&&num==1) {
	    					for(int j=0;j<nums.length;j++) {
	    						B[j]=0;
	    					}
	    					num++;
	    					break;
	    				}if(nums[i]==0&&num==0) {
	    					pos=i;
	    					num++;
	    				}else {
	    					sum*=nums[i];
	    				}
	    			}
	    			if(num<2)
	    			for(i=0;i<nums.length;i++) {
	    				if(i==pos) B[i]=sum;
	    				else {
	    					B[i]=0;
	    				}
	    			}
	    		}else {
	    			for(i=0;i<nums.length;i++) {
	    				B[i]=(int) (sum*Math.pow(nums[i], -1));
	    			}
	    		}
	    		return B;
		    }
		   	
//		   	public ListNode detectCycle(ListNode head) {
//		        HashSet<ListNode> set=new HashSet<>();
//		        while(head!=null) {
//		        		if(set.contains(head)) {
//		        			return head;
//		        		}else {
//		        			set.add(head);
//		        		}
//		        		head=head.next;
//		        }
//		        return null;
//		    }
		   	
		   	//需要继续理解
		    public ListNode getInteract(ListNode head) {
		    		ListNode fast=head;
		    		ListNode slow=head;
		    		while(fast!=null&&fast.next!=null) {
		    			fast=fast.next.next;
		    			slow=slow.next;
		    			if(fast==slow) return fast;
		    		}
		    		return null;
		    }
		   	
		   	public ListNode detectCycle(ListNode head) {
		   		if(head==null) return null;
		        ListNode interact=getInteract(head);
		        if(interact==null) {
		        		return null;
		        }else {
		        		
		        		while(head!=interact) {
		        			head=head.next;
		        			interact=interact.next;
		        		}
		        		return head;
		        }
		    }
		   	
		   	public static void reorderList(ListNode head) {
		   		
		        ListNode fast=head;
		        ListNode slow=head;
		        
		        if(head==null||head.next==null) return;
		      //多了一个next判断以后，对于奇数节点数无影响，但偶数时中点为两个数中前面那个，否则为后面那个
		        while(fast.next!=null&&fast.next.next!=null) {
		        		fast=fast.next.next;
		        		slow=slow.next;
		        }
		        
		       
		        ListNode pre=slow;
		        ListNode now=slow.next;
		        //反转链表！固定模式
		        while(now.next!=null) {
		        		ListNode next=now.next;
		        		now.next=next.next;
		        		next.next=slow.next;
		        		pre.next=next;
		        }
		        
		        slow=head;
		        fast=pre.next;
		        while(slow!=pre) {
		        		pre.next=fast.next;
		        		fast.next=slow.next;
		        		slow.next=fast;
		        		slow=fast.next;
		        		fast=pre.next;
		        }
		        
		    }	   	
		   	
		   	class RandomListNode {
	 		      int label;
	 		      RandomListNode next, random;
	 		      RandomListNode(int x) { this.label = x; }
	 		  };
	 	
	 	HashMap<RandomListNode, RandomListNode> map=new HashMap<>();
	 	public RandomListNode copyRandomList(RandomListNode head) {
	        if(head==null) return null;
	        
	        if(map.get(head)!=null) {
	        		return map.get(head);
	        }
	        
	        RandomListNode temp=new RandomListNode(head.label);
	        map.put(head, temp);
	        
	        temp.next=copyRandomList(head.next);
	        temp.random=copyRandomList(head.random);
	        return temp;
	        
	    }
	 	
	 	
	 	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
	 		if(!wordList.contains(endWord)) return 0;
	        HashSet<String> beginSet=new HashSet<>();
	        HashSet<String> endSet=new HashSet<>();
	        int len=1;
	        HashSet<String> visited=new HashSet<>();
	        beginSet.add(beginWord);
	        endSet.add(endWord);
	        while(!beginSet.isEmpty()&&!endSet.isEmpty()) {
	        	
	        		if(beginSet.size()>endSet.size()) {
	        			HashSet<String> set=beginSet;
	        			beginSet=endSet;
	        			endSet=set;
	        		}
	        		
	        		HashSet<String> temp=new HashSet<>();
	        		for(String word:beginSet) {
	        			char[] charWord=word.toCharArray();
	        			for(int i=0;i<charWord.length;i++) {
	        				char old=charWord[i];
	        				for(char c='a';c<='z';c++) {			
	        					charWord[i]=c;
	        					String str=String.valueOf(charWord);
	        					if(endSet.contains(str)) {
	        						return len+1;
	        					}
	        					
	        					if(!visited.contains(str)&&wordList.contains(str)) {
	        						temp.add(str);
	        						visited.add(str);
	        					}
	        					
	        				}
	        				charWord[i]=old;
	        			}
	        			
	        		}
	        		beginSet=temp;
	        		len++;
	        		
	        }
	        return 0;
	    }
		   
	 	
	 	public static int canCompleteCircuit(int[] gas, int[] cost) {
	 		//当前邮箱内汽油，欠下的汽油树，当前没有遇到到达不了车站的起始车站
	        int tank=0,total=0,start=0;
	        for(int i=0;i<gas.length;i++) {
	        		if((tank=tank+gas[i]-cost[i])<0) {
	        			total+=tank;
	        			tank=0;
	        			start=i+1;
	        		}
	        }
	        return total+tank<0?-1:start;
	    }
	 	
	 	public ListNode insertionSortList(ListNode head) {
	 		if(head==null) return null;
	        ListNode first=new ListNode(0);
	        first.next=head;
	        ListNode tail=first;
	        while(head!=null) {
	        		ListNode trans=first;
	        		while(trans.next!=null&&trans.next.val<head.val) trans=trans.next;
	        		if(trans.next==null||trans.next==head) {
	        			tail=head;
	        			head=head.next;
	        		}else {
	        			tail.next=head.next;
	        			head.next=trans.next;
	        			trans.next=head;
	        			head=tail.next;
	        		}
	        		
	        }
	        return first.next;
	    }
		   	
	 
	 	
//	    public int hIndex(int[] citations) {
//	    		Arrays.sort(citations);
//	    		int l=citations.length,i;
//	        for(i=0;i<l;i++) {
//	        	    if(citations[l-1-i]>i)  continue;
//	        	    else break;
//	        }
//	        return i;
//	    }
	    
	    public int hIndex(int[] citations) {
	    		int l=citations.length;
	        int low=0,high=citations.length-1;
	        while(low<=high) {
	        		int mid=(low+high)/2;
	        		if(citations[mid]==l-mid) return citations[mid];
	        		else if(citations[mid]<l-mid) low=mid+1;
	        		else high=mid-1;
	        }
	        return l-low;
	    }
	 	
	    
	    public boolean wordPattern(String pattern, String str) {
	    		String[] strs=str.split(" ");
	    		if(strs.length!=pattern.length()) return false;
	    		HashMap<Character, String> link=new HashMap<>();
	        for(int i=0;i<pattern.length();i++) {
	        		char c=pattern.charAt(i);
	        		String temp=link.get(c);
	        		if(temp==null) {
	        			if(link.containsValue(strs[i])) return false;
	        			else link.put(c, strs[i]);
	        		}else {
	        			if(!temp.equals(strs[i])) {
	        				return false;
	        			}
	        		}
	        }
	        return true;
	    }
	    
	   
	    public int numJewelsInStones(String J, String S) {
	    		int res=0;
	    		Set<Character> set=new HashSet<>();
	    		for(int i=0;i<J.length();i++) {
	    			set.add(J.charAt(i));
	    		}
	    		for(int i=0;i<S.length();i++) {
	    			if(set.contains(S.charAt(i))) {
	    				res++;
	    			}
	    		}
	    		return res;
	    }
	    
	    class LRUCache {

	        private Map<Integer, Node> cache;
	    	private int capacity;
	    	private Node head;
	    	private Node last;
	    	
	    	private class Node{
	    		Node prev;
	    		Node next;
	    		int key;
	    		int value;
	    	}
	    	
	    	public LRUCache(int capacity) {
	            cache=new HashMap<>(capacity);
	            
	            this.capacity=capacity;
	        }
	        
	        public int get(int key) {
	        		if(cache.containsKey(key)) {
	    			Node node=cache.get(key);
	    			moveToHead(node);
	    			return node.value;
	    		}else {
	    			return -1;
	    		}
	        }
	        
	        public void put(int key, int value) {
	        		Node node=cache.get(key);
	        		if(node==null) {
	        			if(cache.size()>=capacity) {
	        				cache.remove(last.key);
	        				removeLast();
	        			}
	        			node=new Node();
	        		}
	        		node.key=key;
	        		node.value=value;
	        		cache.put(key, node);
	        		moveToHead(node);

	        }
	        
	    	public void removeLast() {
	    		if(last!=null) {
	    			if(last.prev!=null) {
	    				last.prev.next=null;
	    			}else {
	    				head=null;
	    			}
	    			last=last.prev;
	    		}
	    	}
	    	

	        public void moveToHead(Node node) {
	        		if(node==head) {
	        			return;
	        		}
	        		if(node.prev!=null) {
	        			node.prev.next=node.next;
	        		}
	        		if(node.next!=null) {
	        			node.next.prev=node.prev;
	        		}
	        		if(last==node) {
	        			last=node.prev;
	        		}
	        		if(head!=null) {
	        			node.next=head;
	        			head.prev=node;
	        		}
	        		
	        		head=node;
	        		node.prev=null;
	        		if(last==null) {
	        			last=node;
	        		}
	        }
	    }

	    /**
	     * Your LRUCache object will be instantiated and called as such:
	     * LRUCache obj = new LRUCache(capacity);
	     * int param_1 = obj.get(key);
	     * obj.put(key,value);
	     */
	    
	    class Logger {

	    		HashMap<String, Integer> map;
	        /** Initialize your data structure here. */
	        public Logger() {
	            map=new HashMap<>();
	        }
	        
	        /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
	            If this method returns false, the message will not be printed.
	            The timestamp is in seconds granularity. */
	        public boolean shouldPrintMessage(int timestamp, String message) {
	        		Integer temp=map.get(message);
	        		if(temp==null) {
	        			map.put(message, timestamp);
	        			return true;
	        		}else {
	        			if(timestamp-temp>=10) {
	        				map.put(message,timestamp);
	        				return true;
	        			}else {
	        				return false;
	        			}
	        		}
	            
	        }
	    }
	    
	    public static boolean isPerfectSquare(int num) {
	    		int start=0,end=num;
	        while(start<=end) {
	        		long mid=start+(end-start)/2;
	        		if(mid*mid==num) return true;
	        		else if(mid*mid>num) {
	        			end=(int)mid-1;
	        		}else {
	        			start=(int)mid+1;
	        		}
	        }
	        return false;
	    }
	    
	    public String reverseString(String s) {
	    		StringBuilder res=new StringBuilder();
	    		for(int i=s.length()-1;i>=0;i--) {
	    			res.append(s.charAt(i));
	    		}
	    		return res.toString();
	    		
	    }
	    
	     class UndirectedGraphNode {
	    	      int label;
	    	      List<UndirectedGraphNode> neighbors;
	    	      UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
	    
	    	      HashMap<UndirectedGraphNode, UndirectedGraphNode> map=new HashMap<>();
	    	      public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
	    	    	  	if(node==null) return null;
	    	    	  	if(map.containsKey(node)) return map.get(node);
	    	    	  	
	    	    	  	
	    	    	  	UndirectedGraphNode copy=new UndirectedGraphNode(node.label);
	    	    	  	map.put(node, copy);
	    	    	  	for(UndirectedGraphNode n:node.neighbors) {
	    	    	  		copy.neighbors.add(cloneGraph(n));
	    	    	  	}
	    	    	  	
	    	    	  	return copy;
	    	      }
	     }
	    
	     private static int[] sets;
	     public static boolean validTree(int n, int[][] edges) {
	    	 	 if(n-1!=edges.length) return false;
	         //一个建立子set的例题，将同一set的元素设置为相同的值
	    	 	 sets=new int[n];
	    	 	 Arrays.fill(sets, -1);
	    	 	 for(int i=0;i<edges.length;i++) {
	    	 		 int set1=find(edges[i][0]);
	    	 		 int set2=find(edges[i][1]);
	    	 		 if(set1==set2) return false;
	    	 		 
	    	 		 sets[set1]=set2;
	    	 		 
	    	 	 }
	    	 	 return true;
	     }
	     
	     public static int find(int ele) {
	    	 	if(sets[ele]==-1) return ele;
	    	 	else return find(sets[ele]);
	     }
	    
	    public static class WordDictionary {

	    	 		private TrieNode root;
	    	 
	    	 		private class TrieNode{
	    	 			
	    	 			private TrieNode[] links;
	    	 			
	    	 			private boolean end;
	    	 			
	    	 			private static final int R=26;
	    	 			
	    	 			public TrieNode() {
	    	 				links=new TrieNode[R];
	    	 			}
	    	 		
	    	 			public boolean containsKey(char key) {
	    	 				return links[key-'a']!=null;
	    	 			}
	    	 			
	    	 			public void put(char c,TrieNode node) {
	    	 				links[c-'a']=node;
	    	 			}
	    	 			
	    	 			public TrieNode get(char c) {
	    	 				return links[c-'a'];
	    	 			}
	    	 			
	    	 			public void setEnd() {
	    	 				end=true;
	    	 			}
	    	 			
	    	 			public boolean isEnd() {
	    	 				return end;
	    	 			}
	    	 			
	    	 			
	    	 		}
			    /** Initialize your data structure here. */
			    public WordDictionary() {
			    		root=new TrieNode();
			    }
			    
			    /** Adds a word into the data structure. */
			    public void addWord(String word) {
			    	TrieNode node=root;
			        for(int i=0;i<word.length();i++) {
			        		char cur=word.charAt(i);
			        		if(!node.containsKey(cur)) {
			        			node.put(cur, new TrieNode());
			        		}
			        		node=node.get(cur);
			        }
			        node.setEnd();
			    }
			    
			    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
			    public boolean search(String word) {
			        return searchWord(word.toCharArray(), 0, root);
			    }
			    
			    public boolean searchWord(char[] word,int k,TrieNode root) {
			    		if(k==word.length) return root.isEnd();
			    		
			    		char c=word[k];
			    		if(c!='.') {
			    			return root.containsKey(c)&&searchWord(word, k+1, root.get(c));
			    		}else {
			    			for(int i=0;i<root.links.length;i++) {
			    				if(root.links[i]!=null) {
			    					if(searchWord(word, k+1, root.links[i])) {
			    						return true;
			    					}
			    				}
			    			}
			    		}
			    		return false;
			    }
			}
	     
	    public int minSubArrayLen(int s, int[] nums) {
	    		//两点法，因为是连续的子区间，i指向区间左边，j指向区间右边，找到每个i,j之间的和小于s的区间，找到数字最少的区间
	       int i=0,j=0,sum=0,l=nums.length,min=Integer.MAX_VALUE;
	       while(j<l) {
	    	   		sum+=nums[j++];
	    	   		while(sum>=s) {
	    	   			min=Math.min(min, j-i);
	    	   			sum-=nums[i++];
	    	   		}
	       }
	       return min==Integer.MAX_VALUE?0:min;
	    } 
	    
	    public int mySqrt(int x) {
	   
	        long left=1,right=x;
	        while(left<right){
	            long mid=left+(right-left)/2;
	            //因为x/mid去掉小数部分，因此如果这个式子成立，说明mid*mid必然大于x
	            if(mid>x/mid){
	            		right=mid-1;
	                
	            }else{
	            		//满足mid*mid<=x并且(mid+1)*(mid+1)>x为结果
	            		if((mid+1)>x/(mid+1)) {
	            			return (int)mid;
	            		}
	            		left=mid+1; 
	            		
	            }
	        }
	        return (int)right;
	    }
	   
	    
	    public static int numSquares(int n) {
	        int[] dp=new int[n+1];
	        Arrays.fill(dp, Integer.MAX_VALUE);
	        dp[0]=0;
//	        for(int i:dp) {
//	        		System.out.println(i);
//	        }
	        for(int i=1;i<=n;i++) {
	        		for(int j=1;j*j<=i;j++) {
	        			dp[i]=Math.min(dp[i],dp[i-j*j]+1);
	        		}
	        }
	      
	        return dp[n];
	        
	    }
	    
	    
	    public boolean isAdditiveNumber(String num) {
	    		//i,j为前两个数字的位数
	    		for(int i=1;i<=num.length();i++)
	    			for(int j=1;Math.max(i, j)<=num.length()-i-j;j++) {
	    				if(isValid(i,j,num)) return true;
 	    			}
	    		return false;
	    }
	    
	    public boolean isValid(int i,int j,String num) {
	    		//刚开始的两个数存在前置0，则0必须作为单个数进行计算
	    		if(num.charAt(0)=='0'&&i>1) return false;
	    		if(num.charAt(i)=='0'&&j>1) return false;
	    		BigInteger x1=new BigInteger(num.substring(0, i));
	    		BigInteger x2=new BigInteger(num.substring(i,i+j));
	    		String sum=null;
	    		for(int k=i+j;k<num.length();k+=sum.length()) {
	    			//1 1 2 3 5 8
	    			//计算出和，并向前推进
	    			x2=x1.add(x2);
	    			x1=x2.subtract(x1);
	    			sum=x2.toString();
	    			//startswith很精妙，查看字符串后续的前缀是否有所得的和，如果没有，说明无法继续
	    			//因为确定前两个数以后，后续的累加操作是固定的
	    			
	    			if(!num.startsWith(sum, k)) return false;
	    		}
	    		return true;
	    }
	    
	    public int[][] multiply(int[][] A, int[][] B) {
	    		int row=A.length,col=B[0].length,time=A[0].length;
	    		int[][] res=new int[row][col];
//	    		for(int i=0;i<row;i++)
//	    			for(int j=0;j<col;j++) {
//	    				if(A[i][j]!=0) {
//	    					int temp=0;
//		    				for(int k=0;k<A[0].length;k++)
//		    					temp+=A[i][k]*B[k][j];
//		    				res[i][j]=temp;
//	    				}
//	    				
//	    			}
	    		for(int i=0;i<row;i++)
	    			for(int k=0;k<time;k++) {
	    				//通过调整计算顺序，以及不计算稀疏矩阵中关于0的计算，可以省下大量计算
	    				if(A[i][k]!=0) {
	    					for(int j=0;j<col;j++) {
	    						if(B[k][j]!=0) {
	    							res[i][j]+=A[i][k]*B[k][j];
	    						}
	    					}
	    				}
	    			}
	    		return res;
	    }
	    
	    
	    //contains duplicate 3 位桶解决
	    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
	    		if(t<0) return false; 
	    		int m=1+t;
	    		//hashmap的每个元素就是一个位桶，因为差值为t的元素在k范围内不存在，存在即结束返回true.
	    		//当每个位桶只有一个元素，用hashmap存放位桶号与元素对应内容，并且在i>=k的时候每次推进都移除列表首的元素
	    		HashMap<Integer, Integer> map=new HashMap<>();
	    		for(int i=0;i<nums.length;i++) {
	    			int d=getId(nums[i],m);
	    			if(map.containsKey(d)) return true;
	    			if(map.containsKey(d-1)&&Math.abs(map.get(d-1)-nums[i])<m) return true;
	    			if(map.containsKey(d+1)&&Math.abs(map.get(d+1)-nums[i])<m) return true;
	    			map.put(d, nums[i]);
	    			if(i>=k)
	    			map.remove(getId(nums[i-k],m));
	    		}
	    		return false;
	    }
	    
	    public int getId(int x,int w) {
	    		//针对负数如-1/5=-1，而-5/5=-1,-6/5=-2;可以记住
	    		return x<0?(x+1)/w-1:x/w;
	    }
	    
	  //旋转数组二分查找样题
	    public int search(int[] nums, int target) {
		      int start=0,end=nums.length-1,mid;
		      while(start<=end) {
		    	  	mid=start+(end-start)/2;
		    	  	if(nums[mid]==target) return mid;
		    	  	if(nums[mid]<nums[end]) {
		    	  		//需要增加目标数与结尾数关系的判断来确定目标数位置
		    	  		if(nums[mid]<target&&target<=nums[end]) {
		    	  			start=mid+1;
		    	  		}else {
		    	  			end=mid-1;
		    	  		}
		    	  	}else {
		    	  		if(nums[mid]>target&&target>nums[end]) {
		    	  			end=mid-1;
		    	  		}else {
		    	  			start=mid+1;
		    	  		}
		    	  	}
		      }
		      return -1;
	    }
	    
	    
	    public static void quickSort(int[] nums,int start,int end) {
	    		if(start>end) return;
	    		int low=nums[start],mid=start;
	    		for(int i=start+1;i<=end;i++) {
	    			if(nums[i]<low) {
	    				swap1(nums, ++mid, i);
	    			}
	    		}
	    		swap1(nums, mid, start);
	    		quickSort(nums, start, mid-1);
	    		quickSort(nums, mid+1, end);
	    }
	    
	    public static void swap1(int[] nums,int i,int j) {
	    		int temp=nums[i];
	    		nums[i]=nums[j];
	    		nums[j]=temp;
	    }
	    
	    
	    /**
		 * leetcode 400
		 * @param n
		 * @return
		 */
		public static int findNthDigit(int n) {

			int len = 1;
			long count = 9;
			int start = 1;

			while (n > len * count) {
				n -= len * count;
				len += 1;
				count *= 10;
				start *= 10;
			}

			start += (n - 1) / len;
			String s = Integer.toString(start);
			return Character.getNumericValue(s.charAt((n - 1) % len));
		}
	    
		
		
	public static void main(String[] args) {

//		int[] nums=new int[] {1,2,5,2,-1,2};
//		quickSort(nums,0,nums.length-1);
//		for(int i=0;i<nums.length;i++)
//			System.out.println(nums[i]);
		System.out.println(findNthDigit(100));
	}
	
	
	
}
