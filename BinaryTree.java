package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import test.Search.TreeNode;

public class BinaryTree {

	TreeNode root;
	  class TreeNode{
			int val;
			TreeNode left;
			TreeNode right;
			
			public TreeNode(int paraValue) 
			{
				this.val = paraValue;
			}
		}
		
	    public BinaryTree(int[]array)
	    {
	    		root = createBinaryTreeByArray(array, 0);
	    }
	  
		private TreeNode createBinaryTreeByArray(int []array,int index)
		{
		    TreeNode tn = null;
			if (index<array.length) {
				    int value = array[index];
					tn = new TreeNode(value);
					tn.left = createBinaryTreeByArray(array, 2*index+1);
					tn.right = createBinaryTreeByArray(array, 2*index+2);
					return tn;
				}		
			return tn;
		}
	
		public List<List<Integer>> pathSum(TreeNode root, int sum) {
			List<List<Integer>> list=new LinkedList<>();
			LevelMaker(list,new ArrayList<Integer>(),root, 0, sum);
			return list;
		}

		public static void LevelMaker(List<List<Integer>> list,List<Integer> cur,TreeNode root, int sum, int target) {
			if (root == null)
				return;
			sum+=root.val;
			cur.add(root.val);
			if (root.left == null && root.right == null) {
				if(sum==target) {
					list.add(cur);
				}
				cur.remove(cur.size()-1);
				return;
			}
			LevelMaker(list,cur, root.left,sum, target);
			LevelMaker(list,cur, root.right,sum , target);
			cur.remove(cur.size()-1);
		}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
