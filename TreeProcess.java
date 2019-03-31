package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import test.Search.TreeNode;

public class TreeProcess {
	
	
	 public boolean isBalanced(TreeNode root) {
 		if(root==null) return true;
 		return GetDepth(root)!=-1;
 }
 
 public int GetDepth(TreeNode root)
 {
 		if(root==null) return 0;
 		int left=GetDepth(root.left);
 		if(left==-1) return -1;
 		int right=GetDepth(root.right);
 		if(right==-1) return -1;
 		if(left-right>1||left-right<-1) return -1;
 		return (left>right?left:right)+1;
 }
 
 public boolean hasPathSum(TreeNode root, int sum) {
 		if(root==null) return false;
     	return getSum(root,sum,0);
 }
 
 public static boolean getSum(TreeNode root,int sum,int cur)
 {
 	
 		if(root.left==null && root.right==null) 
 		{
 			if(sum==cur+root.val) return true;
 			else return false;
 		}else if(root.left==null)
 			return getSum(root.right,sum,cur+root.val);
 		else if(root.right==null)
 			return getSum(root.left,sum,cur+root.val);
 		else if(getSum(root.left,sum,cur+root.val) || getSum(root.right,sum,cur+root.val)) return true;
 		else return false;
 		
 }

 public int minDepth(TreeNode root) {
 		if(root==null) return 0;
 		return mD(root);
 }
 
 public int mD(TreeNode root) {
 		if(root.left==null && root.right==null) 
		{
			return 1;
		}else if(root.left==null)
			return mD(root.right)+1;
		else if(root.right==null)
			return mD(root.left)+1;
		else
		{
 			int left=mD(root.left);
 			int right=mD(root.right);
 			return (left<right?left:right)+1;
		}
 }
 
 public int climbStairs(int n) {
 	  	if(n==1) return 1;
     if(n==2) return 2;
 		int[] r=new int[n];
     r[0]=1;
     r[1]=2;
     for(int i=2;i<n;i++)
     {
     		r[i]=r[i-2]+r[i-1];
     }
     return r[n-1];
 }
 
 public static class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
 }
 
 public static TreeNode sortedArrayToBST(int[] nums) {
    TreeNode root=insertBSD(nums,0,nums.length-1);
    return root;
 }

 public static TreeNode insertBSD(int[] nums,int left,int right)
 {
	 if(left>right) return null;
	 int mid=(left+right)/2;
	 TreeNode node=new TreeNode(nums[mid]);
	 node.left=insertBSD(nums,left,mid-1);
	 node.right=insertBSD(nums,mid+1,right);
	 return node;
 }
 
 public TreeNode searchBST(TreeNode root, int val) {
     while(root!=null) {
    	 	if(root.val==val) {
    	 		return root;
    	 	}else if(root.val>val) {
    	 		return searchBST(root.left, val);
    	 	}else {
    	 		return searchBST(root.right, val);
    	 	}
     }
     return null;
 }
 
 public TreeNode insertIntoBST(TreeNode root, int val) {
	 TreeNode res=root;
     insertNode(root,val);
     return res;
 }
 
 public void insertNode(TreeNode root,int val) {
	 if(val<root.val) {
		 if(root.left==null) {
			 root.left=new TreeNode(val);
			 return;
		 }else {
			 insertNode(root.left,val);
		 }
	 }else if(val>root.val) {
		 if(root.right==null) {
			 root.right=new TreeNode(val);
			 return;
		 }else {
			 insertNode(root.right,val);
		 }
	 }
 }
 
 public static void ch(int[][] map) {
	 map[2][2]=1;
 }
 
 public static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
     if(p.val>q.val) {
    	 	TreeNode temp=p;
    	 	p=q;
    	 	q=temp;
    	 		
     }
     while(root.val<p.val||root.val>q.val) {
    	 	if(root.val<p.val) {
    	 		root=root.right;
    	 	}else {
    	 		root=root.left;
    	 	
    	 	}
     }
     return root;
 }
 
 
 private static int[] StrToIntArray(String str) {
     str = str.substring(1, str.length() - 1);
     String []strs = str.split(",");
     int []arr = new int[strs.length];

     for (int i = 0; i < arr.length; i++) {
         if (strs[i].equals("null")) {
             arr[i] = Integer.MAX_VALUE;
         } else {
             arr[i] = Integer.parseInt(strs[i]);
         }
     }

     return arr;
 }

 
 public static TreeNode mkTree(String str) {

     int []arr = StrToIntArray(str);
     TreeNode []nodes = new TreeNode[arr.length + 1];
     for (int i = 1; i < nodes.length; i++) {
         if (arr[i - 1] != Integer.MAX_VALUE) {
             nodes[i] = new TreeNode(arr[i - 1]);
         }else {
             nodes[i] = null;
         }
     }

     TreeNode node = null;
     for (int i = 1; i < nodes.length / 2; i++) {
         node = nodes[i];
         if (node == null) continue;
         node.left = nodes[2 * i];
         node.right = nodes[2 * i + 1];
     }
     return nodes[1];
 }
 
 	List<Integer> order1;
 	List<Integer> order2;
  
 	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
 		List<Integer> capa=new ArrayList<Integer>();
 		capa.add(-1);
 		levelMaker(capa, root, p,q);
 		int l=Math.min(order1.size(),order2.size());
 		int i;
 		for(i=0;i<l-1;i++) {
 			if(order1.get(i)==order2.get(i)&&(order1.get(i+1))==order2.get(i+1)) {
 				continue;
 			}else {
 				break;
 			}
 		}
 		
 		if(i<l-1) {
 			for(int j=0;j<=i;j++) {
 				int dir=order1.get(j);
 				if(dir==0) {
 					root=root.left;
 				}else  if(dir==1) {
 					root=root.right;
 				}
 			}
 			return root;
 		}else {
 			if(order1.size()>order2.size()) return q;
 	 		else return p;
 		}
 		
 	}

 	public void levelMaker(List<Integer> list,TreeNode root,TreeNode p,TreeNode q) {
		if (root == null)
			return;
		if(root==p) {
			order1=new ArrayList<>(list);
		}
		if(root==q) {
			order2=new ArrayList<>(list);
		}
		list.add(0);
		levelMaker(list, root.left,p,q);
		list.remove(list.size()-1);
		
		list.add(1);
		levelMaker(list,root.right,p,q);
		list.remove(list.size()-1);
	}
 	
 	public class ListNode {
 		      int val;
 		      ListNode next;
 		      ListNode(int x) { val = x; }
 		  }
 	
 	
 	public TreeNode sortedListToBST(ListNode head) {
 			if(head==null) return null;
 			TreeNode res=toBST(head,null);
 			return res;
 		}

 	public TreeNode toBST(ListNode head,ListNode tail) {
 		
 		if(head==tail) return null;
 		ListNode fast=head;
 		ListNode slow=head;
 		
 		
 		while(fast!=tail&&fast.next!=tail) {
 			fast=fast.next.next;
 			slow=slow.next;
 		}
 		
 		TreeNode res=new TreeNode(slow.val);
 		res.left=toBST(head,slow);
 		res.right=toBST(slow.next,tail);
 		return res;
 	}
 	
 	
 // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
    	 			if (root == null)
				return "null,";
				StringBuilder temp=new StringBuilder();
				temp.append(root.val);
				temp.append(",");
				temp.append(serialize(root.left));
				temp.append(serialize(root.right));
				return temp.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
    		String[] nodes=data.split(",");
    		//arrays.aslist没有指定list的具体类型所以需要加上new linkedlist
    		List<String> list=new LinkedList<String>(Arrays.asList(nodes));
    		return desOpe(list);
    }
    
    public TreeNode desOpe(List<String> nodes) {
    		if(nodes.get(0).equals("null")) {
    			nodes.remove(0);
    			return null;
    		}
    		
    		TreeNode node=new TreeNode(Integer.parseInt(nodes.get(0)));
    		nodes.remove(0);
    		node.left=desOpe(nodes);
    		node.right=desOpe(nodes);
    		return node;
    }
 
    
    public List<Integer> partitionLabels(String S) {
        int[] last=new int[26];
        for(int i=0;i<S.length();i++) {
        		last[S.charAt(i)-'a']=i;
        }
        
        List<Integer> res=new LinkedList<Integer>();
        int start=0,end=0;
        for(int i=0;i<S.length();i++) {
        		end=Math.max(end, last[S.charAt(i)-'a']);
        		if(i==end) {
        			res.add(end-start+1);
        			start=i+1;
        		}
        }
        return res;
    }
    
    public int countUnivalSubtrees(TreeNode root) {
    	if(root==null) return 0;
		int num=0;
		if(isUni(root)) num=1;
		return num+countUnivalSubtrees(root.left)+countUnivalSubtrees(root.right);
    }
       
    
    public boolean isUni(TreeNode root) {
    		if(root.left==null&&root.right==null) return true;
    		else if(root.right==null) {
    			return root.val==root.left.val&&isUni(root.left);
    		}else if(root.left==null) {
    			return root.val==root.right.val&&isUni(root.right);
    		}else {
    			return root.val==root.left.val&&root.val==root.right.val&&isUni(root.left)&&isUni(root.right);
    		}
    		
    }
    
    int max=0;
    public int longestConsecutive(TreeNode root) {
        getConse(root);
        return max;
    }
    
    public int getConse(TreeNode root) {
    		//一种新的解题思路，分别获得左右子树的最大长度然后求较大值
    		  if(root==null) return 0;
    		  //无论什么条件都要遍历整棵树
    		  int L=1+getConse(root.left);
    		  int R=1+getConse(root.right);
    		  
    		  if(root.right==null||root.right.val!=root.val+1) {
    			  R=1;
    		  }
    		  if(root.left==null||root.left.val!=root.val+1) {
    			  L=1;
    		  }
    		  max=Math.max(max, Math.max(L, R));
    		  return Math.max(L, R);
    }
    
 public static void main(String[] args) {
		TreeNode root=mkTree("[3,5,1,6,2,0,8,null,null,7,4]");
		TreeNode p=root.left;
		TreeNode q=root.right;
		TreeProcess tp=new TreeProcess();
		
		TreeNode res=tp.lowestCommonAncestor(root,p,q);
		System.out.println(res.val);
	}
}





