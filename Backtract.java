package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Backtract {

	/**
	 * leetcode 78 求数组所有子集
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		Arrays.sort(nums);
		backtrack(list, new ArrayList<>(), nums, 0);
		return list;
	}

	public static void backtrack(List<List<Integer>> list, List<Integer> templist, int[] nums, int start) {
		list.add(new ArrayList<>(templist));
		for (int i = start; i < nums.length; i++) {
			templist.add(nums[i]);
			backtrack(list, templist, nums, i + 1);
			templist.remove(templist.size() - 1);
		}

	}
	
	/**
	 * leetcode 90 求所有子集（包含重复元素）
	 * @param nums
	 * @return
	 */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list=new ArrayList<List<Integer>>();
    		Arrays.sort(nums);
    		backtrackSub(list,new ArrayList<>(),nums,0);
    		return list;
    }

    public static void backtrackSub(List<List<Integer>> list,List<Integer> templist,int[] nums,int start) {
    		list.add(new ArrayList<>(templist));
    		for(int i=start;i<nums.length;i++)
    		{
    			if(i>start && nums[i]==nums[i-1])  continue;
    			templist.add(nums[i]);
    			backtrackSub(list,templist,nums,i+1);
    			templist.remove(templist.size()-1);
    		}
    	
    }

	 /**
	  * leetcode 46 排列顺序
	  * @param nums
	  * @return
	  */
	 public static List<List<Integer>> permute(int[] nums) {
	 List<List<Integer>> list=new ArrayList<List<Integer>>();
	 backtrack(list,new ArrayList<Integer>(),nums);
	 return list;
	 }
	
	
	 public static void backtrack(List<List<Integer>> list,List<Integer>
	 templist,int[] nums) {
	 if(templist.size()==nums.length) list.add(new ArrayList<>(templist));
	 else
	 {
	 for(int i=0;i<nums.length;i++)
	 {
	 if(templist.contains(nums[i])) continue;
	 templist.add(nums[i]);
	 backtrack(list,templist,nums);
	 templist.remove(templist.size()-1);
	 }
	 }
	 }

	 /**
	  * leetcode 47 排列组合（带重复数字）
	  * @param nums
	  * @return
	  */
	 public List<List<Integer>> permuteUnique(int[] nums) {
	 List<List<Integer>> list=new ArrayList<List<Integer>>();
	 backtrack(list,new ArrayList<Integer>(),nums,new boolean[nums.length]);
	 return list;
	 }
	 public static void backtrack(List<List<Integer>> list,List<Integer>
	 templist,int[] nums,boolean[] used) {
	 if(templist.size()==nums.length && !list.contains(templist)) list.add(new
	 ArrayList<>(templist));
	 else
	 {
	 for(int i=0;i<nums.length;i++)
	 {
	 if(used[i]) continue;
	 templist.add(nums[i]);
	 used[i]=true;
	 backtrack(list,templist,nums,used);
	 used[i]=false;
	 templist.remove(templist.size()-1);
	 }
	 }
	 }

	 /**
	  * leetcode 39 求组合为值sum的所有组合类型，数字可重复利用
	  * @param candidates
	  * @param target
	  * @return
	  */
	 public static List<List<Integer>> combinationSum(int[] candidates, int
	 target) {
	 List<List<Integer>> list=new ArrayList<List<Integer>>();
	 Arrays.sort(candidates);
	 backtrackCom(list,new ArrayList<Integer>(),candidates,0,target);
	 backtrackCom(list,new ArrayList<Integer>(),candidates,target,0);
	 return list;
	 }

	 public static void backtrackCom(List<List<Integer>> list,List<Integer>
	 templist,int[] nums,int remain,int start) {
	 if(remain<0) return;
	 else if(remain==0) list.add(new ArrayList<>(templist));
	 else
	 {
	 for(int i=start;i<nums.length;i++)
	 {
	 templist.add(nums[i]);
	 backtrackCom(list,templist,nums,remain-nums[i],i);
	 templist.remove(templist.size()-1);
	 }
	 }
	 }

	 /**
	  * leetcode 40 求组合为值sum的所有组合类型，数字不可重复利用
	  * @param candidates
	  * @param target
	  * @return
	  */
	 public List<List<Integer>> combinationSum2(int[] candidates, int target) {
	 List<List<Integer>> list=new ArrayList<List<Integer>>();
	 Arrays.sort(candidates);
	 backtrackCom2(list,new ArrayList<Integer>(),candidates,target,0);
	 return list;
	 }
	 public static void backtrackCom2(List<List<Integer>> list,List<Integer>
	 templist,int[] nums,int remain,int start) {
	 if(remain<0) return;
	 else if(remain==0) list.add(new ArrayList<>(templist));
	 else
	 {
	 for(int i=start;i<nums.length;i++)
	 {
	 if(i>start&&nums[i-1]==nums[i]) continue;
	 templist.add(nums[i]);
	 backtrackCom2(list,templist,nums,remain-nums[i],i+1);
	 templist.remove(templist.size()-1);
	 }
	 }
	 }

	// public static List<List<Integer>> combine(int n, int k) {
	// List<List<Integer>> list=new ArrayList<List<Integer>>();
	// int[] nums=new int[n];
	// for(int i=0;i<n;i++)
	// nums[i]=i+1;
	// backtrack(list,new ArrayList<Integer>(),nums,0,k);
	// return list;
	// }
	//
	// public static void backtrack(List<List<Integer>> list,List<Integer>
	// templist,int[] nums,int length,int targetlen) {
	// if(templist.size()==targetlen) {
	// list.add(new ArrayList<>(templist));
	// }
	// for(int i=length;i<nums.length;i++)
	// {
	// templist.add(nums[i]);
	// backtrack(list,templist,nums,i+1,targetlen);
	// templist.remove(templist.size()-1);
	// }
	// }

	// public static List<String> readBinaryWatch(int num) {
	// List<String> list=new ArrayList<String>();
	// int[] nums= {480,240,120,60,32,16,8,4,2,1};
	// backtrack(list,0,nums,0,num,new boolean[10]);
	// return list;
	// }
	//
	// public static void backtrack(List<String> list,int templist,int[] nums,int
	// length,int targetlen,boolean[] used) {
	// if(templist>=720||length>targetlen) return;
	// if(length==targetlen) {
	// int h=templist/60;
	// int m=templist-templist/60*60;
	// String ele=null;
	// if(m<10)
	// ele=String.format("%d:0%d", h,m);
	// else
	// ele=String.format("%d:%d", h,m);
	// if(!list.contains(ele))
	// list.add(ele);
	// }
	// for(int i=0;i<nums.length;i++)
	// {
	// if(used[i]) continue;
	// templist+=nums[i];
	// used[i]=true;
	// backtrack(list,templist,nums,length+1,targetlen,used);
	// used[i]=false;
	// templist-=nums[i];
	// }
	// }

	public static List<String> readBinaryWatch(int num) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 60; j++) {
				if (Integer.bitCount(i * 64 + j) == num)
					list.add(String.format("%d:%02d", i, j));
			}
		}
		return list;
	}

	

	// 搜索案例题
	public static boolean exist(char[][] board, String word) {
		boolean used[][] = new boolean[board.length][board[0].length];
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++) {
				used[i][j] = false;
			}
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == word.charAt(0)) {
					if (backtrack(board, word, 0, used, i, j))
						return true;
				}
			}
		return false;
	}

	public static boolean backtrack(char[][] board, String word, int i, boolean[][] used, int r, int c) {
		if (i == word.length() - 1)
			return true;
		used[r][c] = true;
		if (r + 1 < board.length && !used[r + 1][c] && board[r + 1][c] == word.charAt(i + 1)) {
			if (backtrack(board, word, i + 1, used, r + 1, c))
				return true;
		}
		if (r - 1 >= 0 && !used[r - 1][c] && board[r - 1][c] == word.charAt(i + 1)) {
			if (backtrack(board, word, i + 1, used, r - 1, c))
				return true;
		}
		if (c - 1 >= 0 && !used[r][c - 1] && board[r][c - 1] == word.charAt(i + 1)) {
			if (backtrack(board, word, i + 1, used, r, c - 1))
				return true;
		}
		if (c + 1 < board[0].length && !used[r][c + 1] && board[r][c + 1] == word.charAt(i + 1)) {
			if (backtrack(board, word, i + 1, used, r, c + 1))
				return true;
		}
		used[r][c] = false;
		return false;

	}

	public static int numIslands(char[][] grid) {
		boolean used[][] = new boolean[grid.length][grid[0].length];
		int sum = 0;
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++) {
				used[i][j] = false;
			}
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1' && !used[i][j]) {
					btIsland(grid, used, i, j);
					sum++;
				}
			}
		return sum;
	}

	public static void btIsland(char[][] grid, boolean[][] used, int r, int c) {
		if (grid[r][c] == 0 || used[r][c] == true)
			return;
		used[r][c] = true;
		if (r + 1 < grid.length && !used[r + 1][c]) {
			btIsland(grid, used, r + 1, c);
		}
		if (r - 1 >= 0 && !used[r - 1][c]) {
			btIsland(grid, used, r - 1, c);
		}
		if (c - 1 >= 0 && !used[r][c - 1]) {
			btIsland(grid, used, r, c - 1);
		}
		if (c + 1 < grid[0].length && !used[r][c + 1]) {
			btIsland(grid, used, r, c + 1);
		}

	}

	public static List<List<Integer>> getFactors(int n) {
		List<List<Integer>> res = new LinkedList<List<Integer>>();
		if (n == 1)
			return res;
		backTrack(res, new ArrayList<>(), n, 2);
		res.remove(res.size() - 1);
		return res;
	}

	public static void backTrack(List<List<Integer>> res, List<Integer> cur, int n, int start) {

		if (n <= 1) {
			if (cur.size() > 0) {
				res.add(new ArrayList<>(cur));
			}
			return;
		}

		for (int i = start; i <= n; i++) {
			if (n % i == 0) {
				cur.add(i);
				backTrack(res, cur, n / i, i);
				cur.remove(cur.size() - 1);
			}
		}
	}

	public static List<List<Integer>> combinationSum3(int k, int n) {
		int[] nums = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		backtrack(list, new ArrayList<Integer>(), nums, n, k, 0);
		return list;
	}

	public static void backtrack(List<List<Integer>> list, List<Integer> templist, int[] nums, int remain, int cons,
			int start) {
		if (remain < 0 || cons < 0)
			return;
		else if (remain == 0 && cons == 0)
			list.add(new ArrayList<>(templist));
		else {
			for (int i = start; i < nums.length; i++) {
				templist.add(nums[i]);
				backtrack(list, templist, nums, remain - nums[i], cons - 1, i + 1);
				templist.remove(templist.size() - 1);
			}
		}
	}

	// 全局变量与形式变量带来的速度是类似的，对效率影响非常小
	List<List<String>> res = new ArrayList<>();
	List<String> temp = new ArrayList<>();

	// 回文子串
	public List<List<String>> partition(String s) {

		backTrack(s, 0);
		return res;

	}

	public void backTrack(String s, int start) {
		if (temp.size() > 0 && start >= s.length()) {
			res.add(new ArrayList<>(temp));
		}
		for (int i = start; i < s.length(); i++) {
			if (isPalindrome(s, start, i)) {
				// 事实证明影响的点在这一步，获取substring消耗比char转化为string大
				if (i == start)
					temp.add(Character.toString(s.charAt(i)));
				else
					temp.add(s.substring(start, i + 1));
				backTrack(s, i + 1);
				temp.remove(temp.size() - 1);
			}

		}
	}

	// 这种判断回文的方式要比针对每个子字符串判断回文快很多。
	public boolean isPalindrome(String str, int l, int r) {
		if (l == r)
			return true;
		while (l < r) {
			if (str.charAt(l) != str.charAt(r))
				return false;
			l++;
			r--;
		}
		return true;
	}

	// 回溯法
	public static List<Integer> diffWaysToCompute(String input) {
		List<Integer> part = new LinkedList<Integer>();
		for (int i = 0; i < input.length(); i++) {
			// 针对每个运算符操作
			if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
				String part1 = input.substring(0, i);
				String part2 = input.substring(i + 1);
				List<Integer> part1Num = diffWaysToCompute(part1);
				List<Integer> part2Num = diffWaysToCompute(part2);
				for (Integer num1 : part1Num)
					for (Integer num2 : part2Num) {
						int res = 0;
						if (input.charAt(i) == '+') {
							res = num1 + num2;
						} else if (input.charAt(i) == '-') {
							res = num1 - num2;
						} else {
							res = num1 * num2;
						}
						part.add(res);
					}
			}

		}
		if (part.size() == 0) {
			part.add(Integer.parseInt(input));
		}
		return part;
	}

	// 实现乱序字符串
	Set<String> set = new HashSet<>();

	public List<String> generatePalindromes(String s) {
		int[] map = new int[128]; // map char对应的ascii码
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			map[s.charAt(i)]++;
			if (map[s.charAt(i)] % 2 == 0) {
				count--;
			} else {
				count++;
			}
		}
		if (count > 1)
			return new ArrayList<>();
		else {
			char c = 0;
			int k = 0;
			char[] charset = new char[s.length() / 2];
			for (int i = 0; i < map.length; i++) {
				if (map[i] % 2 == 1)
					c = (char) i;
				for (int j = 0; j < map[i] / 2; j++) {
					charset[k++] = (char) i;
				}
			}
			permuteBack(charset, 0, c);
			return new ArrayList<>(set);

		}
	}

	public void swap(char[] str, int i, int j) {
		char temp = str[i];
		str[i] = str[j];
		str[j] = temp;
	}

	public void permuteBack(char[] str, int l, char single) {
		if (l == str.length) {
			// 一种实现逆序然后拼接的方法
			set.add(new String(str) + (single == 0 ? "" : single) + new StringBuffer(new String(str)).reverse());
		} else {
			for (int i = 0; i < str.length; i++) {
				if (str[i] != str[l] || i == l) {
					swap(str, l, i);
					permuteBack(str, l + 1, single);
					swap(str, l, i);
				}
			}
		}

	}

	public static void main(String[] args) {

		List<Integer> res = diffWaysToCompute("2-1-1");
		for (Integer r : res) {
			System.out.println(r);
		}

	}

}
