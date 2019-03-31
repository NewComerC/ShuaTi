package test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import test.TreeProcess.TreeNode;

public class Search {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	
	public class BSTIterator {
		Queue<TreeNode> node;
	    public BSTIterator(TreeNode root) {
	        node=new LinkedList<TreeNode>();
	        this.getNode(root);
	        
	    }
	    
	    public void getNode(TreeNode root)
	    {
	    		if(root==null) return;
	    		getNode(root.left);
	    		node.add(root);
	    		getNode(root.right);
	    }
	    /** @return whether we have a next smallest number */
	    public boolean hasNext() {
	        return !node.isEmpty();
	    }

	    /** @return the next smallest number */
	    public int next() {
	        return node.poll().val;
	    }
	}
	
	public int kthSmallest(TreeNode root, int k) {
		Queue<TreeNode> q=new LinkedList<TreeNode>();
        getNode(root,q);
        for(int i=1;i<k;i++)
        {
        		q.poll();
        }
        return q.poll().val;
    }
	
	 public void getNode(TreeNode root,Queue<TreeNode> q)
	 {
	    		if(root==null) return;
	    		getNode(root.left,q);
	    		q.add(root);
	    		getNode(root.right,q);
     }
	
	public void flatten(TreeNode root) {
		Queue<TreeNode> re=new LinkedList<TreeNode>();
		levelMaker1(re,root);
        while(!re.isEmpty())
        {
        		TreeNode pre=re.poll();
        		TreeNode cur=re.peek();
        		pre.left=null;
        		pre.right=cur;
        }
        
    }
	
	public static void levelMaker1(Queue<TreeNode> q, TreeNode root) {
		if (root == null)
			return;
		q.add(root);
		levelMaker1(q, root.left);
		levelMaker1(q, root.right);
	}
	// public List<List<Integer>> levelOrderBottom(TreeNode root) {
	// //using BFS
	// List<List<Integer>> list=new LinkedList<List<Integer>>();
	// Queue<TreeNode> queue=new LinkedList<>();
	//
	// if(root==null) return list;
	// queue.offer(root);
	// while(!queue.isEmpty())
	// {
	// List<Integer> sublist=new LinkedList<Integer>();
	// int levelNum=queue.size();
	// for(int i=0;i<levelNum;i++)
	// {
	// if(queue.peek().left!=null) queue.offer(queue.peek().left);
	// if(queue.peek().right!=null) queue.offer(queue.peek().right);
	// sublist.add(queue.poll().val);
	// }
	// list.add(0,sublist);
	// }
	// return list;
	// }

	public int countNodes(TreeNode root) {
		 int leftH=leftNode(root);
		 int rightH=rightNode(root);
		 if(leftH==rightH) return (1<<leftH)-1;
		 else return 1+countNodes(root.left)+countNodes(root.right);
    }
	
	public static int leftNode(TreeNode root) {
		int dep=0;
		while(root!=null)
		{
			root=root.left;
			dep++;
		}
		return dep;
	}
	
	public static int rightNode(TreeNode root) {
		int dep=0;
		while(root!=null)
		{
			root=root.right;
			dep++;
		}
		return dep;
	}
	
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> list=new LinkedList<Integer>();
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
				 if(i==levelNum-1)
					 list.add(queue.peek().val);
				 queue.poll();
			 }
		 }
		 return list;
    }
	
	public class TreeLinkNode {
		      int val;
		      TreeLinkNode left, right, next;
		      TreeLinkNode(int x) { val = x; }
	}
	
	public void connect(TreeLinkNode root) {
		if(root==null) return;
		Queue<TreeLinkNode> queue=new LinkedList<>();
		 queue.offer(root);
		 while(!queue.isEmpty())
		 {
			 int levelNum=queue.size();
			 for(int i=0;i<levelNum;i++)
			 {
				 if(queue.peek().left!=null) queue.offer(queue.peek().left);
				 if(queue.peek().right!=null) queue.offer(queue.peek().right);
				 if(i==levelNum-1)
				 {
					 TreeLinkNode pre=queue.peek();
					 pre.next=null;
					 queue.poll();
				 }else {
					 TreeLinkNode pre=queue.peek();
					 queue.poll();
					 TreeLinkNode cur=queue.peek();
					 pre.next=cur;
				 }
				 
			 }
		 }
    }

	public int sumNumbers(TreeNode root) {
		if (root == null)
			return 0;
		int sum = 0;
		List<Integer> list = new ArrayList<Integer>();
		LevelMaker(list, root, 0, 0);
		for (int num : list) {
			sum += num;
		}
		return sum;
	}

	public static void LevelMaker(List<Integer> list, TreeNode root, int level, int curNum) {
		if (root == null)
			return;
		if (root.left == null && root.right == null) {
			list.add(curNum * 10 + root.val);
			return;
		}
		LevelMaker(list, root.left, level + 1, curNum * 10 + root.val);
		LevelMaker(list, root.right, level + 1, curNum * 10 + root.val);
	}

	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		// using DFS
		List<List<Integer>> list = new LinkedList<List<Integer>>();
		levelMaker(list, root, 0);
		return list;
	}

	public static void levelMaker(List<List<Integer>> list, TreeNode root, int level) {
		if (root == null)
			return;
		if (level >= list.size())
			list.add(0, new LinkedList<>());
		levelMaker(list, root.left, level + 1);
		levelMaker(list, root.right, level + 1);
		list.get(list.size() - level - 1).add(root.val);
	}

	public List<String> binaryTreePaths(TreeNode root) {
		List<String> list=new LinkedList<String>();
		levelMaker(list,"",root,0);
		return list;
    }
	//遍历各个分支
	public static void levelMaker(List<String> list, String cur,TreeNode root, int level) {
		if(root==null) return;
		String newStr;
		if(level==0) 
		{
			newStr=String.valueOf(root.val);
		}
		else
		{
			newStr=cur+"->"+String.valueOf(root.val);
		}
		levelMaker(list,newStr, root.left, level + 1);
		levelMaker(list,newStr ,root.right, level + 1);
		if (root.left == null&&root.right==null)
		{
			list.add(newStr);
			return;
		}
	}
	
	
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> list = new LinkedList<Integer>();
		levelMaker1(list, root, 0);
		return list;
	}

	public static void levelMaker1(List<Integer> list, TreeNode root, int level) {
		if (root == null)
			return;
		list.add(root.val);
		levelMaker1(list, root.left, level + 1);
		levelMaker1(list, root.right, level + 1);
	}

	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> list = new LinkedList<Integer>();
		levelMaker2(list, root);
		return list;
    }
	
	public static void levelMaker2(List<Integer> list, TreeNode root) {
		if (root == null)
			return;
		levelMaker2(list, root.left);
		levelMaker2(list, root.right);
		list.add(root.val);
	}
	
	public void levelOrderBottom(TreeNode root, int layer, List<List<String>> list) {
		if (root == null)
			return;
		List<String> cur;
		if (list.size() < layer)
			cur = new ArrayList<String>();
		else
			cur = list.get(layer);
		cur.add(String.valueOf(root.val));

	}

	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> list = new LinkedList<List<Integer>>();
		Queue<TreeNode> queue = new LinkedList<>();
		if (root == null)
			return list;
		queue.offer(root);
		while (!queue.isEmpty()) {
			List<Integer> sublist = new LinkedList<Integer>();
			int levelNum = queue.size();
			for (int i = 0; i < levelNum; i++) {
				if (queue.peek().left != null)
					queue.offer(queue.peek().left);
				if (queue.peek().right != null)
					queue.offer(queue.peek().right);
				sublist.add(queue.poll().val);
			}
			list.add(sublist);
		}
		return list;
	}

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> list = new LinkedList<List<Integer>>();
		Deque<TreeNode> deque = new ArrayDeque<TreeNode>();
		if (root == null)
			return list;
		deque.offer(root);
		int flag = 0;
		while (!deque.isEmpty()) {
			List<Integer> sublist = new LinkedList<Integer>();
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

	
	public int sumOfLeftLeaves(TreeNode root) {
		List<Integer> list = new LinkedList<Integer>();
		levelMaker1(list, root, 0,0);
		int sum=0;
		for(int num:list)
			sum+=num;
		return sum;
    }
	
	public static void levelMaker1(List<Integer> list, TreeNode root, int level,int flag) {
		if (root == null)
			return;
		if(flag==1&&root.left==null&&root.right==null) 
		list.add(root.val);
		levelMaker1(list, root.left, level + 1,1);
		levelMaker1(list, root.right, level + 1,0);
	}
	
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> list=new LinkedList<Integer>();
        Stack<TreeNode> st=new Stack<TreeNode>();
        while(root!=null||!st.isEmpty())
        {
        		while(root!=null)
        		{
        			st.push(root);
        			root=root.left;
        		}
        		root=st.pop();
        		list.add(root.val);
        		root=root.right;
        		
        }
        return list;
    }
	
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        		TreeNode res=null;
        		while(root!=null)
        		{
        			if(root.val<=p.val) {
        				root=root.right;
        			}else {
        				res=root;
        				root=root.left;
        			}
        		}
        		return res;
       
    }
	
	
	public boolean isValidBST(TreeNode root) {
		return isValid(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }
	
	public static boolean isValid(TreeNode root,long LowBound,long HighBound) {
		if(root==null) return true;
        if(root.val<HighBound&&root.val>LowBound) 
        	return (isValid(root.left,LowBound,root.val)&&isValid(root.right,root.val,HighBound));
        else return false;
		
    }
	
//仅适用于所有元素为正的情况
//	public TreeNode buildTree(int[] preorder, int[] inorder) {
//        int[] mapIndex=new int[256];
//		for(int i=0;i<inorder.length;i++)
//		{
//			mapIndex[inorder[i]]=i;
//		}
//		return buildInorderPreorder(mapIndex,preorder,0,preorder.length,0);
//		
//    }
//	
//	public TreeNode buildInorderPreorder(int[] mapIndex,int[] pre,int start, int n, int offset){
//		if(n==0) return null;
//		int rootVal=pre[start];
//		int i=mapIndex[rootVal]-offset;
//		TreeNode root=new TreeNode(rootVal);
//		root.left=buildInorderPreorder(mapIndex,pre,start+1,i,offset);
//		root.right=buildInorderPreorder(mapIndex,pre,start+i+1,n-i-1,offset+i+1);
//		return root;
//	}
	
//	public TreeNode buildTree(int[] preorder, int[] inorder) {
//		return constructe(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1); 
//	}
	
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
	 
	 public TreeNode buildTree(int[] inorder, int[] postorder) {
		 return constructeInPost(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1); 
	    }
	 
	 private TreeNode constructeInPost(int[] inOrder, int startIn, int endIn, int[] postOrder,  
	            int startPost, int endPost) {  
		 if(endIn<startIn||startPost>endPost) return null;
		 if((endPost-startPost)!=(endIn-startIn)) return null;
		 if (inOrder == null || inOrder == null || postOrder.length == 0 || inOrder.length == 0) return null;  
	     int rootValue=postOrder[endPost];
	     TreeNode root=new TreeNode(rootValue);
	     if(startPost==endPost&&startIn==endIn) return root;
	     int rootIdx=-1,count=0;
	     for(int i=endIn;i>=startIn;i--,count++)
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
	            // 构建右子树  
	        		root.right=constructeInPost(inOrder,rootIdx+1,endIn,postOrder,endPost-count,endPost-1);
	        }  
	        if (startPost <= endPost - count) {  
	            // 构建左子树  
	        		root.left=(constructeInPost(inOrder, startIn,rootIdx - 1,postOrder, startPost , endPost-count-1));   
	        }  
	        return root;  
	     
	 }
	 
	 public int closestValue(TreeNode root, double target) {
		 double min=Math.abs(root.val-target);
		 int close=root.val;
		 while(root!=null)
		 {
			 if(min>Math.abs(root.val-target)) 
			 {
				 	min=Math.abs(root.val-target);
				 	close=root.val;
			 }
			 if(target>root.val) root=root.right;
			 else root=root.left;
		 }
		 return close;
	 }
	 
	 public int search(int[] nums, int target) {
		 	
	        for(int i=0;i<nums.length;i++)
	        {
	        		if(nums[i]==target) return i;
	        }
	        return -1;
	    }
	 
	 public int findPeakElement(int[] nums) {
	        int i=0;
	        while(i<nums.length-1&&nums[i]<nums[i+1])
	        {
	        		i++;
	        }
	        return i;
	    }
	 
	 public List<String> generatePossibleNextMoves(String s) {
		 	List<String> re=new LinkedList<String>();
	        for(int i=0;i<s.length();i++)
	        {
	        		if(s.charAt(i)=='+')
	        		{
	        			if(i<s.length()-1&&s.charAt(i+1)=='+')
	        			{
	        				StringBuilder newStr=new StringBuilder(s);
	        				re.add(new String(newStr.replace(i, i+2,"--")));
	        			}
	        		}
	        }
	        return re;
	    }
	 
	 //easy,将两棵树合并为一颗
	 //处理合并树的范例
	 public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		 if(t1==null)
		 return t2;
		 if(t2==null)
		 return t1;
		 t1.val+=t2.val;
		 t1.left=mergeTrees(t1.left,t2.left);
		 t1.right=mergeTrees(t1.right,t2.right);
		 return t1;
	 }
	 
	 public TreeNode invertTree(TreeNode root) {
		 	if(root==null) return null;
			 Queue<TreeNode> queue=new LinkedList<>();
			 queue.offer(root);
			 while(!queue.isEmpty())
			 {
				 TreeNode cur=queue.poll();
				 //java中树节点交换方式
				 TreeNode temp=cur.left;
				 cur.left=cur.right;
				 cur.right=temp;
				 if(cur.left!=null) queue.offer(cur.left);
				 if(cur.right!=null) queue.offer(cur.right);
				 
				 
			 }
			 return root;
	 }
	 
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
		
	 	public List<List<Integer>> pathSum(TreeNode root, int sum) {
			List<List<Integer>> list=new LinkedList<>();
			LevelMaker(list,new LinkedList<Integer>(),root, sum);
			return list;
		}

		public static void LevelMaker(List<List<Integer>> list,List<Integer> cur,TreeNode root, int sum) {
			if (root == null)
				return;
			cur.add(root.val);
			if (root.left == null && root.right == null && sum==root.val) {
				//!一定要用new linkedlist否则添加的为可变动的cur list
				list.add(new LinkedList<Integer>(cur));
				cur.remove(cur.size()-1);
				return;
			}else {
				LevelMaker(list,cur, root.left,sum-root.val);
				LevelMaker(list,cur, root.right,sum-root.val);
			}
			cur.remove(cur.size()-1);
		}
	
		
		
		//130 处理包围情况的例题
		 public void solve(char[][] board) {
		        
		   		if(board==null||board.length<=2||board[0].length<=2) return;
		   		
		   		int row=board.length;
		   		int colomn=board[0].length;
		   		
		   		for(int i=0;i<row;i++) {
		   			if(board[i][0]=='O') dfsAns(board,i,0);
		   			if(board[i][colomn-1]=='O') dfsAns(board,i,colomn-1); 
		   		}
		   		
		   		for(int i=0;i<colomn;i++) {
		   			if(board[0][i]=='O') dfsAns(board,0,i);
		   			if(board[row-1][i]=='O') dfsAns(board,row-1,i); 
		   		}
		   		
		   		for(int i=0;i<row;i++)
		   			for(int j=0;j<colomn;j++) {
		   				if(board[i][j]=='O') {
		   					board[i][j]='X';
		   				}else if(board[i][j]=='*') {
		   					board[i][j]='O';
		   				}
		   			}
	   		
		    }
		   	
		   	public void dfsAns(char[][] board,int i,int j) {
		   		board[i][j]='*';
		   		if(i>1&&board[i-1][j]=='O') dfsAns(board, i-1, j);
		   		if(i<board.length-2&&board[i+1][j]=='O') dfsAns(board,i+1,j);
		   		if(j>1&&board[i][j-1]=='O') dfsAns(board, i, j-1);
		   		if(j<board[0].length-2&&board[i][j+1]=='O') dfsAns(board,i,j+1);
		   	}
		
		public static void main(String[] argv) {
			Search search=new Search();
			
			TreeNode res=search.buildTree(new int[] {9,3,15,20,7},new int[] {9,15,7,20,3});
			
		}
    
	
		
}