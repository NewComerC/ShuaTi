package test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.PriorityQueue;

import test.NewStart.ListNode;
import test.Search.TreeNode;
import test.Trie;
public class JianZhi {

	
		
		
		//1.二维数组查找
		//矩阵搜索类例题！
		 public boolean searchMatrix(int[][] matrix, int target) {
			 if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
					return false;
		     boolean[][] used=new boolean[matrix.length][matrix[0].length];   
		     for(int i=0;i<matrix.length;i++)
		    	 for(int j=0;j<matrix[0].length;j++)
		    		 used[i][j]=false;
			 return SearchCon(matrix,used,0,0,target);
		    }
		 
		 public boolean SearchCon(int[][] matrix,boolean[][] used,int i,int j, int target)
		 {
			 	if(matrix[i][j]==target) return true;
				used[i][j]=true;
				if(i+1<matrix.length&&!used[i+1][j]&&matrix[i+1][j]<=target) {
				    if(SearchCon(matrix,used,i+1,j,target)) return true;
				}
				if(j+1<matrix[0].length&&!used[i][j+1]&&matrix[i][j+1]<=target) {
				    if(SearchCon(matrix,used,i,j+1,target)) return true;
				}
				return false;
			 
		 }
		 
		 //2.替换空格
		 public static String replaceSpace(StringBuffer str) {
	    		int i=0;
	    		while(i<str.length()){
	    			if(str.charAt(i)==' ') {
	    				str.replace(i, i+1, "%20");
	    				i=i+2;
	    			}
	    			i++;
	    		}
	    		return str.toString();
	    }
	
		 //3.从头到尾打印链表
		 
		 public static class ListNode {
			   	int val;
			    ListNode next = null;
			    ListNode(int val) {
			       this.val = val;
			    }
		 
		}
		 
		 public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
			   ArrayList<Integer> res=new ArrayList<>();
		       while(listNode!=null) {
		    	   		res.add(listNode.val);
		    	   		listNode=listNode.next;
		       }
		       Collections.reverse(res);
		       return res;
		 }
	
		 //4.重建二叉树
		 public static class TreeNode {
			      int val;
			      TreeNode left;
			      TreeNode right;
			      TreeNode(int x) { val = x; }
		 }
		 
		 public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
			 return constructe(pre, 0, pre.length - 1, in, 0, in.length - 1); 
		 }
		 
		 private TreeNode constructe(int[] preOrder, int startPre, int endPre, int[] inOrder,  
		            int startIn, int endIn) {  
			 if((endPre-startPre)!=(endIn-startIn)) return null;
			 if (preOrder == null || inOrder == null || preOrder.length == 0 || inOrder.length == 0) return null;  
		     int rootValue=preOrder[startPre];
		     TreeNode root=new TreeNode(rootValue);
		     if(startPre==endPre&&startIn==endIn) return root;
		     int rootIdx=-1,count=0;
		     for(int i=startIn;i<=endIn;i++,count++)
		     {
		    	 	if(rootValue==inOrder[i])
		    	 	{
		    	 		rootIdx=i;
		    	 		break;
		    	 	}
		     }
		     if (rootIdx == -1) {  
		            return null;  
		        }  
		  
		        if (count > 0) {  
		            // 构建左子树  
		            root.left=(constructe(preOrder, startPre + 1, startPre + count, inOrder, startIn,  
		                    rootIdx - 1));  
		        }  
		  
		        if (endPre > startPre + count) {  
		            // 构建右子树  
		            root.right=(constructe(preOrder, startPre + count + 1, endPre, inOrder,  
		                    rootIdx + 1, endIn));  
		        }  
		        return root;    
		 }
		 
		 //5.用两个栈实现队列
		 public class Solution {
			    Stack<Integer> stack1 = new Stack<Integer>();
			    Stack<Integer> stack2 = new Stack<Integer>();
			    
			    public void push(int node) {
			        stack1.push(node);
			    }
			    
			    public int pop() {
			    		while(!stack1.isEmpty()) {
			    			stack2.push(stack1.pop());
			    		}
			    		
			    		int res=stack2.pop();
			    		
			    		while(!stack2.isEmpty()) {
			    			stack1.push(stack2.pop());
			    		}
			    		return res;
			    }
		}
		 
		 
		//6.旋转数组
		 public static int minNumberInRotateArray(int [] array) {
			 	if(array==null||array.length==0) return 0;
			 	int left=0,right=array.length-1;
		        while(left<right) {
		        		int mid=left+(right-left)/2;
		        		if(array[mid]>array[right]) {
		        			left=mid+1;
		        		}else {
		        			right=mid;
		        		}
		        }
		        return array[left];
		 }
		 
		 //7.跳台阶
		 public int JumpFloor(int target) {
			 if(target==1) return 1;
			 else if(target==2) return 2;
			 int[] dp=new int[target+1];
			 dp[0]=0;
			 dp[1]=1;
			 dp[2]=2;
			 for(int i=3;i<=target;i++) {
				 dp[i]=dp[i-2]+dp[i-1];
			 }
			 return dp[target];
		 }
		 
		 //8.变态跳台阶
		 public int JumpFloorII(int target) {
			 if(target==1) return 1;
			 else if(target==2) return 2;
			 int[] dp=new int[target+1];
			 dp[0]=0;
			 dp[1]=1;
			 dp[2]=2;
			 for(int i=3;i<=target;i++) {
				 dp[i]=0;
				 for(int j=i-1;j>0;j--) {
					 dp[i]+=dp[j];
				 }
				 dp[i]++;
			 }
			 return dp[target];
		 }
		 
		 //9.矩阵覆盖
		 public int RectCover(int target) {
			 if(target==0) return 0;
			 else if(target==1) return 1;
			 else if(target==2) return 2;
			 int[] dp=new int[target+1];
			 dp[1]=1;
			 dp[2]=2;
			 for(int i=3;i<=target;i++) {
				 dp[i]=dp[i-1]+dp[i-2];
			 }
			 return dp[target];
		 }
		 
		//10.左旋转字符串
		 public static String LeftRotateString(String str,int n) {
			 if(str.length()==0) return "";
			 n=n%str.length();
			 return String.format("%s%s", str.substring(n, str.length()),str.substring(0,n));
		 }
		 
		 //11.翻转单词顺序
		 public static String ReverseSentence(String str) {
			 	if(str==null||str.length()==0) return "";
		        int i=0;
		        while(i<str.length()&&str.charAt(i)==' ') {
		        		i++;
		        }
		        if(i==str.length()) return str;
		        String[] strs=str.trim().split(" "); 
		        
		        StringBuffer res=new StringBuffer();
		        for(int j=strs.length-1;j>=0;j--) {
		        		if(j==0) {
		        			res.append(strs[j]);
		        		}else {
		        			res.append(strs[j]+" ");
		        		}
		        		
		        }
		        
		        return res.toString();
		 }
		 
		 //数值的整数次方
		 public static double Power(double base, int exponent) {
			 double res=1;
			 if(exponent<0) {
				 base=1/base;
				 exponent=-exponent;
			 }
			 
			 for(int i=0;i<exponent;i++) {
				 res*=base;
			 }
			 return res;
			 
		 }
		 
		 //二叉树的镜像
		 public void Mirror(TreeNode root) {
				
			 	if(root==null) return;
			 	
				 Queue<TreeNode> queue=new LinkedList<>();
				
				 queue.offer(root);
				 while(!queue.isEmpty())
				 {
					 TreeNode node=queue.poll();
					 TreeNode temp=node.left;
					 node.left=node.right;
					 node.right=temp;
					 if(node.left!=null)
					 queue.offer(node.left);
					 if(node.right!=null)
					 queue.offer(node.right);
				 }
		    }
		 
		 //顺序打印矩阵
		 public ArrayList<Integer> printMatrix(int [][] matrix) {
			 ArrayList<Integer> list = new ArrayList<Integer>();
				int i, j;
				if (matrix == null || matrix.length == 0)
					return list;
				int left = 0, right = matrix[0].length - 1, head = 0, bottom = matrix.length - 1;
				while (left <= right && bottom >= head) {
					for (i = left; i <= right; i++) {
						list.add(matrix[head][i]);
					}
					head++;
					if (head > bottom)
						continue;
					for (i = head; i <= bottom; i++) {
						list.add(matrix[i][right]);
					}
					right--;
					if (right < left)
						continue;
					for (i = right; i >= left; i--) {
						list.add(matrix[bottom][i]);
					}
					bottom--;
					if (head > bottom)
						continue;
					for (i = bottom; i >= head; i--) {
						list.add(matrix[i][left]);
					}
					left++;
				}
				return list;
		 }
		 
		 //把数列排成最小的树
		 private static class stringNumberComparator implements Comparator<String>{
	    		@Override
	    		public int compare(String a,String b) {
	    			String num1=a+b;
	    			String num2=b+a;
	    			return num2.compareTo(num1);
	    		}
	    }
		 
		 public static String PrintMinNumber(int [] numbers) {
			 if(numbers==null||numbers.length==0) return "";
			 String[] strs=new String[numbers.length];
		        for(int i=0;i<numbers.length;i++) {
		        		strs[i]=String.valueOf(numbers[i]);
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
		 
		 //丑数
		 public static int GetUglyNumber_Solution(int index) {
			 	if(index==0) return 0;
				int[] ugly=new int[index];
		       	ugly[0]=1;
		       	int num2=2,num3=3,num5=5;
		       	//这里的index是在ugly数组中的因为所有ugly数字的因子都是2,3,5即都是数组中的数字，所以不是简单的+1的方式
		       	int index2=0,index3=0,index5=0;
		       	for(int i=1;i<index;i++) {
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
		       	return ugly[index-1];
		    }
		 
		 public static int FirstNotRepeatingChar(String str) {
		        HashSet<Character> single=new HashSet<>();
		        HashSet<Character> dup=new HashSet<>();
		        for(int i=0;i<str.length();i++) {
		        		char cur=str.charAt(i);
		        		if(dup.contains(cur)) {
		        			continue;
		        		}else if(single.contains(cur)){
		        			dup.add(str.charAt(i));
		        			single.remove(cur);
		        		}else {
		        			single.add(cur);
		        		}
		        }
		        for(int i=0;i<str.length();i++) {
		        		if(single.contains(str.charAt(i))) {
		        			return i;
		        		}
		        }
		        return -1;
		        
		 }
		 
		//链表中倒数第k个结点
		 public ListNode FindKthToTail(ListNode head,int k) {
			 ListNode first=head;
			 int l=0;
			 while(head!=null) {
				 l++;
				 head=head.next;
				 
			 }
			 
			 if(l<k) return null;
			 
			 k=l-k+1;
			 for(int i=1;i<k;i++) {
				first=first.next;
	
			 }
				 
			 return first;
		}
		 
		 //翻转链表
		 public static ListNode ReverseList(ListNode head) {
			 	if(head==null||head.next==null) return head;
			 	
			 	ListNode pre=head;
			 	ListNode now=head.next;
			 	ListNode next=now;
			 	while(now!=null) {
			 		next=now.next;
			 		now.next=pre;
			 		pre=now;
			 		now=next;
			 		
			 	}
			 	head.next=null;
			 	return pre;
		    }
		 
		 //对称的二叉树
		 boolean isSymmetrical(TreeNode pRoot)
		 {
			 return isMirror(pRoot,pRoot);
		 }
		 
		 public boolean isMirror(TreeNode left,TreeNode right) {
			 if(left==null&&right==null) return true;
			 else if(left==null||right==null) return false;
			 return left.val==right.val && isMirror(left.left,right.right) && isMirror(left.right,right.left);
			 
		 }
		 
		 //调整数组顺序使奇数位于偶数前面
		 public void reOrderArray(int [] array) {
		        int mid=(array.length-1)/2;
		        
		        List<Integer> even=new ArrayList<>();
		        List<Integer> odd=new ArrayList<>();
		        
		        for(int i=0;i<array.length;i++) {
		        		if(array[i]%2==0) {
		        			even.add(array[i]);
		        		}else {
		        			odd.add(array[i]);
		        		}
		        }
		        
		        for(int i=0;i<odd.size();i++) {
		        		array[i]=odd.get(i);
		        }
		        for(int i=odd.size(),j=0;i<odd.size()+even.size();i++,j++) {
		        		array[i]=even.get(j);
		        }
		    }
		 
		 //栈的压入弹出顺序
		 public boolean IsPopOrder(int [] pushA,int [] popA) {
		      Stack<Integer> stack=new Stack<>();
		      int i=0,j=0;
		      while(i<pushA.length&&j<popA.length) {
		    	  		while(i<pushA.length&&pushA[i]!=popA[j]) {
		    	  			stack.push(pushA[i]);
		    	  			i++;
		    	  		}
		    	  		if(i<pushA.length)
		    	  		stack.push(pushA[i++]);
		    	  		while(j<popA.length) {
		    	  			if(popA[j]==stack.peek()) {
				    	  		stack.pop();
				    	  		j++;
				    	  	}else if(i<popA.length) {
				    	  		break;
				    	  	}else {
				 
				    	  		return false;
				    	  	}
		    	  		}
		      }
		      return true;
		      
		    }
		 
		 //从上到下遍历二叉树
		 public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
				 //using BFS
				 ArrayList<Integer> list=new ArrayList<Integer>();
				 Queue<TreeNode> queue=new LinkedList<>();
				
				 if(root==null) return list;
				 queue.offer(root);
				 while(!queue.isEmpty())
				 {
					 int levelNum=queue.size();
					 for(int i=0;i<levelNum;i++)		
					 {
						 if(queue.peek().left!=null) queue.offer(queue.peek().left);
						 if(queue.peek().right!=null) queue.offer(queue.peek().right);
						 list.add(queue.poll().val);
					 }
				 }
				 return list;
		 }
		  
		 //判断是否为后序遍历
		 public boolean VerifySquenceOfBST(int [] sequence) {
			  if(sequence==null||sequence.length==0) return false;
		       return judge(sequence,0,sequence.length-1);
		    }
		 
		 public boolean judge(int[] sequence,int left,int right) {
			 if(left>=right) return true;
			 int i=right,rootVal=sequence[right];
			 while(i>=left&&sequence[i]>=rootVal) {
				 i--;
			 }
			 
			 for(int j=i;j>=left;j--) {
				 if(sequence[j]>rootVal) return false;
			 }
			 return judge(sequence,left,i)&&judge(sequence,i+1,right-1);
		 }
		 
		 
		 //二叉树上和为某一节点的路径
		 public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
		        ArrayList<ArrayList<Integer>> res=new ArrayList<ArrayList<Integer>>();
		        LevelMaker(res,new ArrayList<Integer>(), root, target);
		        return res;
		    }
		 
		 public static void LevelMaker(ArrayList<ArrayList<Integer>> list,List<Integer> cur,TreeNode root, int sum) {
				if (root == null)
					return;
				cur.add(root.val);
				if (root.left == null && root.right == null && sum==root.val) {
					//!一定要用new linkedlist否则添加的为可变动的cur list
					list.add(new ArrayList<Integer>(cur));
					cur.remove(cur.size()-1);
					return;
				}else {
					LevelMaker(list,cur, root.left,sum-root.val);
					LevelMaker(list,cur, root.right,sum-root.val);
				}
				cur.remove(cur.size()-1);
			}
		 
		 //大小王
		 public boolean isContinuous(int [] numbers) {
			 if(numbers==null||numbers.length==0) return false;
			 List<Integer> list=new ArrayList<>();
			 int g=0;
			 for(int i=0;i<numbers.length;i++) {
				 if(numbers[i]==0) {
					 g++;
				 }else {
					 list.add(numbers[i]);
				 }
			 }
			 Collections.sort(list);
			 int sum=0;
			 for(int i=list.size()-1;i>0;i--) {
				 if(list.get(i)==list.get(i-1)) return false;
				 sum+=list.get(i)-list.get(i-1)-1;
				 
			 }
			 if(sum<=g) return true;
			 else return false;
		    }
		 
		 
		 public int LastRemaining_Solution(int n, int m) {
			 	if(n==0) return -1;
		        List<Integer> list=new LinkedList<>();
		        for(int i=0;i<n;i++) {
		        		list.add(i);
		        }
		        int i=0;
		        while(list.size()>1) {
		        		i=(i+m-1)%list.size();
		        		list.remove(i);
		        }
		        return list.get(0);
		    }
		 
		 //合并两个递增链表
		 public static ListNode Merge(ListNode list1,ListNode list2) {
		        ListNode head=new ListNode(0);
		        ListNode res=head;
		        head.next=null;
		        
		        while(list1!=null&&list2!=null) {
		        		if(list1.val<=list2.val) {
		        			head.next=list1;
		        			list1=list1.next;
		        		}else {
		        			head.next=list2;
		        			list2=list2.next;
		        		}
		        		head=head.next;
		        }
		        if(list1!=null) {
		        		head.next=list1;
		        		head=head.next;
		        		list1=list1.next;
		        }
		        if(list2!=null) {
	        			head.next=list2;
	        			head=head.next;
	        			list1=list2.next;
		        }
		        res=res.next;
		        return res;
		    }
		 
		 //树的子结构
		 public static boolean HasSubtree(TreeNode root1,TreeNode root2) {
			 	if(root1==null) return false;
			 	if(root2==null) return false;
		        if(root1.val==root2.val) {
		        		if(isSubTree(root1,root2)) {
		        			return true;
		        		}else {
		        			return HasSubtree(root1.left, root2)||HasSubtree(root1.right, root2);
		        		}
		        }else {
		        		return false;
		        }
		 }
		 
		 public static boolean isSubTree(TreeNode root1,TreeNode root2) {
			 if(root2==null) return true;
			 if(root1==null) return false;
			 else {
				 if(root2.val==root1.val) {
					 return isSubTree(root1.left,root2.left)&&isSubTree(root1.right, root2.right);
				 }else {
					 return false;
				 }
			 }
		 }
		 
		 //包含min函数的栈
		 public class StackSolution {

			 	Stack<Integer> stack=new Stack<>();
			 
			    public void push(int node) {
			        stack.push(node);
			    }
			    
			    public void pop() {
			        stack.pop();
			    }
			    
			    public int top() {
			        return stack.peek();
			    }
			    
			    public int min() {
			        Iterator<Integer> iterator=stack.iterator();
			        int min=Integer.MAX_VALUE;
			        while(iterator.hasNext()) {
			        		int num=iterator.next();
			        		if(num<min) min=num;
			        }
			        return min;
			    }
			}
		 
		 //最小第K个数
//		 1.最简单的方法：将n个数排序，排序后的前k个数就是最大的k个数，这种算法的复杂度是O（nlogn）
//		 2.O（n）的方法：利用快排的patition思想，基于数组的第k个数来调整，将比第k个数小的都位于数组的左边，比第k个数大的都调整到数组的右边，这样调整后，位于数组右边的k个数最大的k个数(这k个数不一定是排好序的）
//		 3.O(nlogk）的方法：先创建一个大小为k的最小堆，接下来我们每次从输入的n个整数中读入一个数，如果这个数比最小堆的堆顶元素还要大，那么替换这个最小堆的堆顶并调整。
		 public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
			 	ArrayList<Integer> res=new ArrayList<>();
			 	if(k>input.length) return res;
			 	Arrays.sort(input);
			 	
			 	for(int i=0;i<k;i++) {
			 		res.add(input[i]);
			 	}
			 	return res;
		    }
		 //这是搜索三次数组相对是比较慢的o(mn)
//		 public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
//			 	ArrayList<Integer> res= new ArrayList<>();
//			 	if(k>input.length) return res;
//			 	boolean[] used=new boolean[input.length];
//			 	for(int i=0;i<k;i++) {
//			 		int min,o=0;
//			 		while(used[o]) o++;
//			 		min=input[o];
//			 		for(int j=1;j<input.length;j++) {
//			 			if(!used[j]&&input[j]<min) {
//			 				min=input[j];
//			 				o=j;
//			 			}
//			 		}
//			 		used[o]=true;
//			 		res.add(min);
//			 	}
//			 	return res;
//		    }
		 
		 //把二叉树打印成多行
//		 ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
//			 //using BFS
//				 ArrayList<ArrayList<Integer>> list=new ArrayList<ArrayList<Integer>>();
//				 Queue<TreeNode> queue=new LinkedList<>();
//				
//				 if(pRoot==null) return list;
//				 queue.offer(pRoot);
//				 while(!queue.isEmpty())
//				 {
//					 ArrayList<Integer> sublist=new ArrayList<Integer>();
//					 int levelNum=queue.size();
//					 for(int i=0;i<levelNum;i++)
//					 {
//						 if(queue.peek().left!=null) queue.offer(queue.peek().left);
//						 if(queue.peek().right!=null) queue.offer(queue.peek().right);
//						 sublist.add(queue.poll().val);
//					 }	
//					 list.add(sublist);
//				 }
//				 return list;
//		    }
		 
		 //获得树的深度
		 public int TreeDepth(TreeNode root) {
			 if(root==null) return 0;
			 else return maxDepth(root);
		    }
		 
//		 public int maxDepth(TreeNode root) {
//			 if(root.left==null && root.right==null) 
//				{
//					return 1;
//				}else if(root.left==null)
//					return TreeDepth(root.right)+1;
//				else if(root.right==null)
//					return TreeDepth(root.left)+1;
//				else
//				{
//		 			int left=TreeDepth(root.left);
//		 			int right=TreeDepth(root.right);
//		 			return (left>right?left:right)+1;
//				}
//		 }
		 
		 public int maxDepth(TreeNode root) {
			 if(root==null) return 0;
			 else return Math.max(maxDepth(root.left), maxDepth(root.right))+1;
		 }
		 
		 public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
			 	ArrayList<Integer> res=new ArrayList<>();
			 	if(array.length<2) return res;
			 	int min=Integer.MAX_VALUE,o=-1,k=-1;
		        for(int i=0;i<array.length-1;i++) {
		        		for(int j=i+1;j<array.length;j++) {
		        			if(array[i]+array[j]==sum) {
		        				if(o==-1&&k==-1) {
		        					min=array[i]*array[j];
		        					o=i;
		        					k=j;
		        				}else if(array[i]*array[j]<min) {
		        					min =array[i]*array[j];
		        					o=i;
		        					k=j;
		        				}
		        			}
		        		}
		        }
		        if(o==-1&&k==-1) return res;
		        
		        res.add(array[o]);
		        res.add(array[k]);
		        return res;
		 }
		 
		 public static ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
			   ArrayList<ArrayList<Integer>> res=new ArrayList<ArrayList<Integer>>();
		       for(int n=(int)Math.sqrt(sum*2);n>=2;n--) {
		    	   		ArrayList<Integer> temp=new ArrayList<>();
		    	   		if(((n&1)==1)&&(sum%n==0)||((sum%n)*2==n)) {
		    	   			for(int j=0,k=(sum/n)-(n-1)/2;j<n;j++,k++) {
		    	   				temp.add(k);
		    	   			}
		    	   			res.add(temp);
		    	   		}
		    	   		
		       }
		       return res;
		    }
		 
		 
		 //链表第一个公共结点
		 public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
			 if(pHead1==null||pHead2==null) return null;
	    		ListNode a=pHead1;
	    		ListNode b=pHead2;
	    		while(a!=b) {
	    			a=a==null?pHead2:a.next;
	    			b=b==null?pHead1:b.next;
	    		}
	    		return a;
		    }
		 
		 //数组在排序数组中出现次数
		 public int GetNumberOfK(int [] array , int k) {
			   int res=0;
		       for(int i=0;i<array.length;i++) {
		    	   	if(array[i]==k) res++;
		    	   	if(array[i]>k) break;
		       }
		       return res;
		    }
		 
		 //不用乘除做加减
		 public int Add(int num1,int num2) {
			 while(num2!=0) {
	        		int carry=num1&num2;
	        		num1=num1^num2;
	        		num2=carry<<1;
	        }
	        return num1;
		    }
		 
//		 char[] str=new char[100000];
//		 int cur=0;
//		 public void Insert(char ch)
//		    {
//		        str[cur++]=ch;
//		    }
//		  //return the first appearence once char in current stringstream
//		    public char FirstAppearingOnce()
//		    {
//		    		List<Character> list=new ArrayList<>();
//		    		for(int i=0;i<cur;i++) {
//		    			if(list.contains(str[i])) {
//		    				list.remove(str[i]);
//		    			}else {
//		    				list.add(str[i]);
//		    			}
//		    		}
//		    		if(list.size()==0) return '#';
//		    		return list.get(0);
//		    }
		 
		 //滑动窗口最大值
		 public ArrayList<Integer> maxInWindows(int [] num, int size)
		    {
			 	ArrayList<Integer> res=new ArrayList<>();
			 	if(size==0) return res;
			 	if(size>num.length) return res;
		        for(int i=0;i<=num.length-size;i++) {
		        		ArrayList<Integer> temp=new ArrayList<>();
		        		for(int j=i;j<i+size;j++) {
		        			temp.add(num[j]);
		        		}
		        		Collections.sort(temp);
		        		res.add(temp.get(temp.size()-1));
		        }
		        return res;
		    }
		 
		 //计算1+2+……+n
		 public int Sum_Solution(int n) {
			 	int sum=n;
		        boolean res=(n>0)&&((sum+=Sum_Solution(n-1))>0);
		        return sum;
		    }
		 
		 public static ListNode deleteDuplication(ListNode pHead)
		    {
			 	if(pHead==null) return null;
			 	int f;
			 	if(pHead.val==Integer.MIN_VALUE) {
			 		f=0;
			 	}else {
			 		f=pHead.val-1;
			 	}
			 	ListNode head=new ListNode(f);
			 	head.next=pHead;
			 	ListNode res=head;
			 	while(pHead.next!=null) {
			 		if(pHead.val==pHead.next.val) {
			 			while(pHead.next!=null&&pHead.val==pHead.next.val){
			 				pHead=pHead.next;
			 			}
			 			pHead=pHead.next;
			 			head.next=pHead;
			 			if(pHead==null) break;
			 		}else {
			 			head=head.next;
				 		pHead=pHead.next;
			 		}
			 		
			 		
			 	}
			 	return res.next;
		    }
		 
		 //字符串转换成整数
		 public int StrToInt(String str) {
			 
			 if(str==null||str.length()==0) return 0;
			 
			 char[] s=str.toCharArray();
			 
			 int flag=0,sum=0,i=0;
			 if(s[0]=='-') {
				 flag=1;
				 i++;
			 }
			 
			 for(;i<str.length();i++) {
				 if(s[i]=='+') continue;
				 else if(s[i]<'0'||s[i]>'9') return 0;
				 else {
					 sum=sum*10+s[i]-'0';
				 }
			 }
			 if(flag==1) sum=-sum;
			 return sum;
		  }
		 
		 
//		 //链表中环的入口结点
//		 public ListNode EntryNodeOfLoop(ListNode pHead)
//		 {
//		        
//		 }
		 
		//矩阵中的路径
		 public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
		    {
				if (matrix == null || matrix.length == 0 )
					return false;
				char[][] dict=new char[rows][cols];
				for(int i=0;i<rows;i++){
					for(int j=0;j<cols;j++) {
						dict[i][j]=matrix[i*cols+j];
					}
				boolean[][] used=new boolean[dict.length][dict[0].length];
				for(int j=0;j<dict.length;j++)
					for(int k=0;k<dict[0].length;k++) {
							if(!used[j][k]&&dict[j][k]==str[0]) {
								if(SearchCon(dict,used,j,k,str,0)) {
									return true;
								}
							}	
						}
					
				}
				return false;
		    }
		 
		 public static boolean SearchCon(char[][] matrix,boolean[][] used,int i,int j, char[] str,int index)
		 {
			 	if(matrix[i][j]==str[index]) {
			 		
			 		used[i][j]=true;
			 		if(index==str.length-1) return true;
			 		else {
			 			if(i+1<matrix.length&&!used[i+1][j]&&matrix[i+1][j]==str[index+1]) {
						    if(SearchCon(matrix,used,i+1,j,str,index+1)) return true;
						}
						if(j+1<matrix[0].length&&!used[i][j+1]&&matrix[i][j+1]==str[index+1]) {
						    if(SearchCon(matrix,used,i,j+1,str,index+1)) return true;
						}
						if(i-1>=0&&!used[i-1][j]&&matrix[i-1][j]==str[index+1]) {
						    if(SearchCon(matrix,used,i-1,j,str,index+1)) return true;
						}
						if(j-1>=0&&!used[i][j-1]&&matrix[i][j-1]==str[index+1]) {
						    if(SearchCon(matrix,used,i,j-1,str,index+1)) return true;
						}
			 		}
			 		
			 	}
				used[i][j]=false;
				return false;
			 
		 }
		 
		 //机器人的运动范围
		 public int movingCount(int threshold, int rows, int cols)
		 {
		    boolean[][] used=new boolean[rows][cols];
		    
		    return SearchPath(used, 0, 0, rows, cols,threshold);
		 }
		 
		 public int SearchPath(boolean[][] used,int i,int j,int rows,int cols,int threshold)
		 {
			 	if(i<0||i>=rows||j<0||j>=cols) return 0;
			 	if(used[i][j]) return 0;
			 	int sum=0,x=i,y=j;
			 	while(x!=0) {
			 		sum+=x%10;
			 		x/=10;
			 	}
			 	while(y!=0) {
			 		sum+=y%10;
			 		y/=10;
			 	}
			 	if(sum>threshold) return 0;
				used[i][j]=true;
				return 1+SearchPath(used, i+1, j, rows, cols,threshold)
				+SearchPath(used, i-1, j, rows, cols,threshold)
				+SearchPath(used, i, j+1, rows, cols,threshold)
				+SearchPath(used, i, j-1, rows, cols,threshold);
			 
		 }
		 
		 //数据流中的中位数
		 LinkedList<Integer> nums=new LinkedList<>();
		 public void Insert(Integer num) {
			    if(nums.size()==0||num<nums.getFirst()) {
			    		nums.addFirst(num);
			    		return;
			    }
			    
			    int index=0,flag=0;
			    for(int i=0;i<nums.size();i++) {
			    		
			    		if(nums.get(i)>num) {
			    			index=i;
			    			nums.add(index, num);
			    			flag=1;
			    			break;
			    		}
			    }
			    if(flag==0) {
			    		nums.addLast(num);
			    }
			    
		    }

		    public Double GetMedian() {
		    		if(nums.size()==0) return null;
		    		Double ans;
		    		int l=nums.size();
		        if(l%2==0) {
		        		ans=(nums.get((l-1)/2)+nums.get(l/2))*1.0/2;
		        }else {
		        		ans=nums.get(l/2)*1.0;
		        }
		        return ans;
		    }
		 
		    //二叉搜索树的第k个结点
		    TreeNode KthNode(TreeNode pRoot, int k)
		    {	
		    		int i=1;
		        Stack<TreeNode> st=new Stack<TreeNode>();
		        while(pRoot!=null||!st.isEmpty())
		        {
		        		while(pRoot!=null)
		        		{
		        			st.push(pRoot);
		        			pRoot=pRoot.left;
		        		}
		        		if(i==k) return st.pop();
		        		pRoot=st.pop();
		        		i++;
		        		pRoot=pRoot.right;
		        		
		        }
		        return pRoot;
		    }
		    
		    //平衡二叉树
		    public boolean IsBalanced_Solution(TreeNode root) {
		        if(root==null) return true;
		        if(Math.abs(TreeDepth(root.left)-TreeDepth(root.right))<=1) {
		        			return IsBalanced_Solution(root.left)&&IsBalanced_Solution(root.right);
		        		}else {
		        			return false;
		        }
		        
		    }
		    
		    //数组中只出现一次的数字
		    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
		    		if(array.length<2) return ;
		        int flag=1,temp=0;
		        for(int i=0;i<array.length;i++)
		        	temp^=array[i];
		        while((temp&flag)==0) flag<<=1;
		        
		        for(int i=0;i<array.length;i++) {
		        		if((array[i]&flag)==0) num1[0]^=array[i];
		        		else num2[0]^=array[i];
		        }
		        
		    }
		    
		    //之字形顺序打印二叉树
		    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
		    		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
				Deque<TreeNode> deque = new ArrayDeque<TreeNode>();
				if (pRoot == null)
					return list;
				deque.offer(pRoot);
				int flag = 0;
				while (!deque.isEmpty()) {
					ArrayList<Integer> sublist = new ArrayList<Integer>();
					int levelNum = deque.size();
					if (flag == 0) {
						for (int i = 0; i < levelNum; i++) {
							if (deque.peekFirst().left != null)
								deque.offer(deque.peekFirst().left);
							if (deque.peekFirst().right != null)
								deque.offer(deque.peekFirst().right);
							sublist.add(deque.pollFirst().val);
						}
						flag = 1;
					} else {
						for (int i = 0; i < levelNum; i++) {
							if (deque.peekLast().right != null)
								deque.offerFirst(deque.peekLast().right);
							if (deque.peekLast().left != null)
								deque.offerFirst(deque.peekLast().left);
							sublist.add(deque.pollLast().val);
						}
						flag = 0;
					}
					list.add(sublist);
				}
				return list;
		    }
		    
		    //链表入口
		    public ListNode EntryNodeOfLoop(ListNode pHead)
		    {
		        HashSet<ListNode> set=new HashSet<>();
		        while(pHead!=null) {
		        		if(set.contains(pHead)) {
		        			return pHead;
		        		}else {
		        			set.add(pHead);
		        			pHead=pHead.next;
		        		}
		        }
		        return null;
		    }
		    
		    public class TreeLinkNode {
		        int val;
		        TreeLinkNode left = null;
		        TreeLinkNode right = null;
		        TreeLinkNode next = null;

		        TreeLinkNode(int val) {
		            this.val = val;
		        }
		    }
		    
		    //二叉树的下一个结点
		    public TreeLinkNode GetNext(TreeLinkNode pNode)
		    {
		        if(pNode==null) {
		        		return null;
		        }
		        if(pNode.right!=null) {
		        		pNode=pNode.right;
		        		while(pNode.left!=null) {
		        			pNode=pNode.left;
		        		}
		        		return pNode;
		        }
		        while(pNode.next!=null) {
		        		if(pNode.next.left==pNode) {
		        			return pNode.next;
		        		}else {
		        			pNode=pNode.next;
		        		}
		        }
		        	return null;
		    }
		    
		    //正则表达式匹配
		    public boolean match(char[] str, char[] pattern)
		    {
		    		Boolean[][] dp=new Boolean[str.length+1][pattern.length+1];
		    		return proceed(dp, 0, 0, str, pattern);
		    }
		    
		    public static boolean proceed(Boolean[][] dp,int i,int j,char[] text,char[] pattern) {
		    	if(dp[i][j]!=null) {
		    		return dp[i][j];
		    	}
		    	boolean res;
		    		if(j==pattern.length) {
		    			res=i==text.length;
		    		}else {
		    			boolean firstMatch=(i<text.length&&(text[i]==pattern[j]||pattern[j]=='.'));
			    		
			    		if(j<pattern.length-1&&pattern[j+1]=='*') {
			    			res=proceed(dp, i, j+2, text, pattern)||(firstMatch&&proceed(dp,i+1,j,text,pattern));
			    		}else {
			    			return firstMatch&&proceed(dp, i+1, j+1, text, pattern);
			    		}
			    	
		    		}
		    		dp[i][j]=res;
		    		return res;
		    }
		    
		    
		    //字符串中的排列
		    public static ArrayList<String> Permutation(String str) {
		    		ArrayList<String> list=new ArrayList<String>();
		    		if(str.isEmpty()) return list;
				backtrack(list,new StringBuilder(),str,new boolean[str.length()]);
				return list;
		    }
		    
		    public static void backtrack(ArrayList<String> list,StringBuilder templist,String str,boolean[] used) {
				if(templist.length()==str.length() && !list.contains(templist.toString())) list.add(templist.toString());
				else 
				{
					for(int i=0;i<str.length();i++)
					{
						if(used[i]) continue;
						templist.append(str.charAt(i));
						used[i]=true;
						backtrack(list,templist,str,used);
						used[i]=false;
						templist.deleteCharAt(templist.length()-1);
					}
				}
		    }
		    
		    public int MoreThanHalfNum_Solution(int [] array) {
		    		int l=array.length;
		    		if(l==1) return array[0];
		    		l/=2;
		    	
		    		HashMap<Integer,Integer> map=new HashMap<>();
		        for(int i=0;i<array.length;i++) {
		        		int n=array[i];
		        		if(map.containsKey(n)) {
		        			int occur=map.get(n)+1;
		        			if(occur>l) return n;
		        			map.put(n,occur);
		        		}else {
		        			map.put(n, 1);
		        		}
		        }
		        return 0;
		    }

		    //构建乘积数组
		    public int[] multiply(int[] A) {
		    		int[] B=new int[A.length];
		    		int sum=1,i;
		    		for(i=0;i<A.length;i++) {
		    			sum*=A[i];
		    		}
		    		
		    		if(sum==0) {
		    			int pos=0,num=0;
		    			sum=1;
		    			for(i=0;i<A.length;i++) {
		    				if(A[i]==0&&num==1) {
		    					for(int j=0;j<A.length;j++) {
		    						B[j]=0;
		    					}
		    					num++;
		    					break;
		    				}if(A[i]==0&&num==0) {
		    					pos=i;
		    					num++;
		    				}else {
		    					sum*=A[i];
		    				}
		    			}
		    			if(num<2)
		    			for(i=0;i<A.length;i++) {
		    				if(i==pos) B[i]=sum;
		    				else {
		    					B[i]=0;
		    				}
		    			}
		    		}else {
		    			for(i=0;i<A.length;i++) {
		    				B[i]=(int) (sum*Math.pow(A[i], -1));
		    			}
		    		}
		    		return B;
		    }
		    
		    //数组中重复的数字
		    public boolean duplicate(int numbers[],int length,int [] duplication) {
		        HashSet<Integer> set=new HashSet<>();
		        for(int i=0;i<length;i++) {
		        		int n=numbers[i];
		        		if(set.contains(n)) {
		        			duplication[0]=n;
		        			return true;
		        		}else {
		        			set.add(n);
		        		}
		        }
		        return false;
		    }
		    
		    //二叉搜索树与双向链表   
		    public TreeNode Convert(TreeNode pRootOfTree) {
		    	    if(pRootOfTree==null) return pRootOfTree;
		        PriorityQueue<TreeNode> queue=new PriorityQueue<TreeNode>((a,b)->a.val-b.val);
		        levelMaker1(queue, pRootOfTree);
		        TreeNode res=queue.peek();
		        while(!queue.isEmpty()) {
		        		TreeNode temp=queue.poll();
		        		TreeNode next=queue.peek();
		        		if(next==null) {
		        			temp.right=null;
		        		}else {
		        			temp.right=next;
			        		next.left=temp;
		        		}
		        }
		        res.left=null;
		        return res;
		    }
		    
		    public void levelMaker1(Queue<TreeNode> queue, TreeNode root) {
				if (root == null)
					return;
				queue.offer(root);
				levelMaker1(queue, root.left);
				levelMaker1(queue, root.right);
			}
		    
		    //连续子数组的最大和
		    public int FindGreatestSumOfSubArray(int[] array) {
		    		if(array.length==0) return 0;
		        int dp[]=new int[array.length];
		        dp[0]=array[0];
		        for(int i=1;i<array.length;i++) {
		        		if(dp[i-1]<=0) dp[i]=array[i];
		        		else dp[i]=dp[i-1]+array[i];
		        }
		        int max=dp[0];
		        for(int i=1;i<array.length;i++) {
		        		if(dp[i]>max) max=dp[i];
		        }
		        return max;
		    }
		    
		    
		    public int NumberOf1Between1AndN_Solution(int n) {
		    		int sum=0;
		    		for(int i=1;i<=n;i++) {
		    			String num=String.valueOf(i);
		    			for(int j=0;j<num.length();j++) {
		    				if(num.charAt(j)=='1') {
		    					sum++;
		    				}
		    			}
		    		}
		    		return sum;
		    }
		    
		    //表示数值的字符串
		    public boolean isNumeric(char[] str) {
		    		int i=0;
		    		if(str[i]=='-'||str[i]=='+') {
		        		i++;
			    }
		    		int flag=0;
		    		while(i<str.length&&str[i]!='e'&&str[i]!='E') {
		    			if(str[i]=='.'&&flag==1) return false;
		    			if(str[i]=='.') {
		    				flag=1;
		    			}else if(str[i]<'0'||str[i]>'9') {
		    				return false;
		    			}
		    			i++;
		    		}
		    		if(i==str.length) return true;
		    		i++;
		    		if(i==str.length) return false;
		    		if(str[i]=='-'||str[i]=='+') i++;
		    		for(;i<str.length;i++) {
		    			if(str[i]<'0'||str[i]>'9') {
		    				return false;
		    			}
		    		}
		        return true;
		    }
		    
		    
		    String Serialize(TreeNode root) {
		        if (root == null)
				return "#,";
				StringBuilder temp=new StringBuilder();
				temp.append(root.val);
				temp.append(",");
				temp.append(Serialize(root.left));
				temp.append(Serialize(root.right));
				return temp.toString();
		    }
		    
		      int index=-1;
		      TreeNode Deserialize(String str) {
		    	  	index++;
		         String[] nodes=str.split(",");
		         TreeNode t=null;
		         if(!(nodes[index].equals("#"))) {
		        	 	t=new TreeNode(Integer.parseInt(nodes[index]));
		        	 	t.left=Deserialize(str);
		        	 	t.right=Deserialize(str);
		         }
		         return t;
		    }
		      
		      class RandomListNode {
	 		      int label;
	 		      RandomListNode next, random;
	 		      RandomListNode(int x) { this.label = x; }
	 		  };
		      
		      HashMap<RandomListNode, RandomListNode> map=new HashMap<>();
		   	public RandomListNode Clone(RandomListNode pHead) {
		          if(pHead==null) return null;
		          
		          if(map.get(pHead)!=null) {
		          		return map.get(pHead);
		          }
		          
		          RandomListNode temp=new RandomListNode(pHead.label);
		          map.put(pHead, temp);
		          
		          temp.next=Clone(pHead.next);
		          temp.random=Clone(pHead.random);
		          return temp;
		          
		      }
		   	  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JianZhi jz=new JianZhi();
		char[] str=new String("-1E-16").toCharArray();
		System.out.println(jz.isNumeric(str));
//		PriorityQueue<Integer> queue=new PriorityQueue<>();
//		System.out.println(queue.peek());
		
//		//java遍历map
//		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//		 
//		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//		 
//		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//		 
//		}
		
		
	}

}
