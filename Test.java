package test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Test {
	
	public static int removeElement(int[] nums, int val) {
		int l = nums.length;
		int i = 0, j = l - 1, m;
		if (l == 0)
			return 0;
		while (j >= i && nums[j] == val)
			j--;
		while (i < j) {
			if (nums[i] == val) {
				m = nums[i];
				nums[i] = nums[j];
				nums[j] = m;
				while (j >= i && nums[j] == val)
					j--;
			}
			i++;
		}
		return j + 1;
	}

	public static int lengthOfLastWord(String s) {
		int i = s.length() - 1, j = 0;
		if (s.length() == 0)
			return 0;
		while (s.charAt(i) == ' ' && i > 0)
			i--;
		for (j = i; j >= 0; j--) {
			if (s.charAt(j) == ' ')
				break;
		}
		return i - j;
	}

	public static void sortColors(int[] nums) {
		int i = 0, j = nums.length - 1, m = 0;
		while (i < j) {
			while (nums[j] > 0 && j > i)
				j--;
			while (nums[i] == 0 && i < j)
				i++;
			if (i >= j)
				break;
			m = nums[i];
			nums[i] = nums[j];
			nums[j] = m;
		}
		if (nums[i] == 0 && i + 1 < nums.length)
			i++;
		j = nums.length - 1;
		while (i < j) {
			while (nums[j] > 1 && j > i)
				j--;
			while (nums[i] == 1 && i < j)
				i++;
			if (i >= j)
				break;
			m = nums[i];
			nums[i] = nums[j];
			nums[j] = m;
		}

	}


	public static boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '{' || s.charAt(i) == '[' || s.charAt(i) == '(')
				stack.push(s.charAt(i));
			else if (s.charAt(i) == '}' && !stack.empty() && stack.peek() == '{')
				stack.pop();
			else if (s.charAt(i) == ']' && !stack.empty() && stack.peek() == '[')
				stack.pop();
			else if (s.charAt(i) == ')' && !stack.empty() && stack.peek() == '(')
				stack.pop();
			else
				return false;
		}
		return stack.empty();
	}

	static String[] ele = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

	public static List<String> letterCombinations(String digits) {
		List<String> ret = new ArrayList<String>();
		if (digits.equals(""))
			return ret;
		combination("", digits, 0, ret);
		return ret;

	}

	private static void combination(String prefix, String digits, int offset, List<String> ret) {
		if (offset >= digits.length()) {
			ret.add(prefix);
			return;
		}
		String letter = ele[digits.charAt(offset) - '0'];
		for (int i = 0; i < letter.length(); i++) {
			combination(prefix + letter.charAt(i), digits, offset + 1, ret);
		}
	}
	
	// int i,j,sum=1,n,f;
	// List<String> list=new ArrayList<String>();
	// String[] ele= {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
	// for(i=0;i<digits.length();i++)
	// {
	// sum*=ele[digits.charAt(i)-'0'].length();
	// }
	// StringBuilder[] str1=new StringBuilder[sum];
	// for(i=0;i<sum;i++)
	// {
	// str1[i]=new StringBuilder(digits.length());
	// }
	// for(i=0;i<digits.length();i++)
	// {
	// n=sum;
	// for(j=0;j<=i;j++)
	// n/=ele[digits.charAt(i)-'0'].length();
	// f=sum/n;
	// String element=ele[digits.charAt(i)-'0'];
	// for(j=0;j<element.length();j++)
	// {
	// for(int k=j*n;k<(j+1)*n;k++)
	// {
	// str1[k].append(element.charAt(j));
	// }
	// }
	// }
	// if(sum!=1)
	// for(i=0;i<sum;i++)
	// {
	// list.add(str1[i].toString());
	//
	// }
	// return list;

	public int searchInsert(int[] nums, int target) {
		int i;
		if (target < nums[0])
			return 0;
		for (i = 0; i < nums.length; i++) {
			if (target == nums[i])
				return i;
			if (i != nums.length - 1 && target > nums[i] && target < nums[i])
				return i;
		}
		return i;
	}

	public static double myPow(double x, int n) {
		if (n == 0)
			return 1.0;

		// if(n==1) return x;
		if (n < 0) {
			if (n <= Integer.MIN_VALUE)
				if (x > 1)
					return 0;
				else
					return 1;
			n = -n;
			x = 1 / x;
		}
		return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);

	}

	public static boolean canJump(int[] nums) {
		int l=nums.length,last=l-1;
		for(int i=l-2;i>=0;i--)
		{
			if(i+nums[i]>=last) last=i;
		}
		return last<=0;

	}

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m = nums1.length;
		int n = nums2.length;
		if (m > n) {
			int[] num = nums1;
			nums1 = nums2;
			nums2 = num;
			int c = m;
			m = n;
			n = c;
		}
		// if(m==0)
		// if(n%2==1) return nums2[(n-1)/2];
		// else return (nums2[(n-1)/2]+nums2[n/2])/2.0;
		int imin = 0, imax = m, half = (m + n + 1) / 2;
		while (imin <= imax) {
			int i = (imin + imax) / 2;
			int j = half - i;
			if (i < imax && nums2[j - 1] > nums1[i]) {
				imin++;
			} else if (i > 0 && nums1[i - 1] > nums2[j]) {
				imax--;
			} else {
				int maxleft = 0;
				if (i == 0)
					maxleft = nums2[j - 1];
				else if (j == 0)
					maxleft = nums1[i - 1];
				else
					maxleft = Math.max(nums1[i - 1], nums2[j - 1]);
				if ((m + n) % 2 == 1)
					return maxleft;

				int maxright = 1;
				if (i == m)
					maxright = nums2[j];
				else if (j == n)
					maxright = nums1[i];
				else
					maxright = Math.min(nums1[i], nums2[j]);
				return (maxleft + maxright) / 2.0;
			}

		}
		return 0.0;
	}

	public String intToRoman(int num) {
		String[] word = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		int[] value = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		int i = 0;
		StringBuilder str = new StringBuilder();
		while (num > 0) {
			if (num - value[i] >= 0) {
				str.append(word[i]);
				num -= value[i];
			} else
				i++;
		}
		return str.toString();
	}

	public static int[] GetNumber(String s, String[] word, int[] value, int ind, int i) {
		int[] result = { 0, i };
		if (i >= s.length())
			return result;
		if (i + 1 < s.length() && s.substring(i, i + 2).equals(word[ind])) {
			result[0] += value[ind];
			i = i + 2;
			result[1] = i;
		}
		ind++;
		if (i >= s.length())
			return result;
		if (s.charAt(i) == word[ind].charAt(0)) {
			result[0] += value[ind];
			i++;
			result[1] = i;
		}
		ind++;
		if (i >= s.length())
			return result;
		if (i + 1 < s.length() && s.substring(i, i + 2).equals(word[ind])) {
			result[0] += value[ind];
			i = i + 2;
			result[1] = i;
		}
		ind++;
		if (i >= s.length())
			return result;
		while (i < s.length() && s.charAt(i) == word[ind].charAt(0)) {
			result[0] += value[ind];
			i++;
		}
		result[1] = i;
		return result;
	}

	public static int romanToInt(String s) {
		int re = 0;
		String[] word = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		int[] value = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		int i = 0;
		while (i < s.length() && s.charAt(i) == 'M') {
			re += 1000;
			i++;
		}
		re += GetNumber(s, word, value, 1, i)[0];
		i = GetNumber(s, word, value, 1, i)[1];
		re += GetNumber(s, word, value, 5, i)[0];
		i = GetNumber(s, word, value, 5, i)[1];
		re += GetNumber(s, word, value, 9, i)[0];
		i = GetNumber(s, word, value, 9, i)[1];
		return re;
	}

	public static int removeDuplicates(int[] nums) {
		int i = 0, lengs = 0, loc = 1, m;
		while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
			i++;
			lengs++;
		}
		i++;
		while (i < nums.length) {
			while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
				i++;
				lengs++;
			}
			if (i < nums.length) {
				m = nums[i];
				nums[i] = nums[loc];
				nums[loc] = m;
				loc++;
			}
			i++;
		}
		return nums.length - lengs;
	}

	public static int[] plusOne(int[] digits) {
		StringBuilder sb = new StringBuilder();
		int i, j, sum = 0, flag = 0;
		char num;
		for (i = digits.length - 1; i >= 0; i--) {
			sb.append(digits[i]);
		}
		for (i = 0; i < digits.length; i++) {
			if (i == 0)
				sum = sb.charAt(i) + -47;
			else
				sum = sb.charAt(i) + flag - 48;
			if (sum >= 10) {
				sum = sum - 10;
				flag = 1;
			} else
				flag = 0;
			num = (char) (sum + 48);
			sb.setCharAt(i, num);
			if (i == digits.length - 1 && flag == 1) {
				sb.append('1');
			}
		}
		int[] re = new int[sb.length()];
		for (i = sb.length() - 1, j = 0; i >= 0; i--, j++) {
			re[j] = sb.charAt(i) - 48;
		}
		return re;
	}

	public static int uniquePaths(int m, int n) {
		int p1 = 1, p2 = 1, min, i;
		m--;
		n--;
		min = Math.min(m, n);
		for (i = 1; i <= min; i++) {
			p1 *= i;
		}
		for (i = m + n; min > 0; min--, i--) {
			p2 *= i;
		}
		return p2 / p1;
	}

	public String longestCommonPrefix(String[] strs) {
		String prefix = strs[0];
		if (strs.length == 0)
			return "";
		for (int i = 1; i < strs.length; i++) {
			while (strs[i].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() - 1);
				if (prefix.isEmpty() == true)
					return "";
			}
		}
		return prefix;
	}

	/**
	 * leetcode 22 括号生成
	 * @param n
	 * @return
	 */
	
	public static List<String> generateParenthesis(int n) {
		List<String> result = new ArrayList<String>();
		getParenthesis(result, "", 0, 0, n);
		return result;
	}

	public static void getParenthesis(List<String> result, String str, int open, int close, int max) {
		if (str.length() == max * 2) {
			result.add(str);
			return;
		}
		if (open < max)
			getParenthesis(result, str + '(', open + 1, close, max);
		if (close < open)
			getParenthesis(result, str + ')', open, close + 1, max);
	}

	public static String countAndSay(int n) {
		String[] arr = new String[n];
		arr[0] = "1";
		int j, num;
		char qua, number;
		for (int i = 1; i < n; i++) {
			String curr = arr[i - 1];
			StringBuilder next = new StringBuilder();
			j = 0;
			while (j < curr.length()) {
				num = 1;
				number = curr.charAt(j);
				while (j < curr.length() - 1 && curr.charAt(j) == curr.charAt(j + 1)) {
					num++;
					j++;
				}
				qua = (char) (num + 48);
				next.append(qua);
				next.append(number);
				j++;
			}
			arr[i] = next.toString();
		}
		return arr[n - 1];
	}

	// public List<List<String>> groupAnagrams(String[] strs) {
	// List<List<String>> list=new ArrayList<List<String>>();
	// String[] example=new String[strs.length];
	// int loc=0,i;
	// for(String str:strs)
	// {
	// char[] c=str.toCharArray();
	// Arrays.sort(c);
	//
	//
	// }
	//
	// }

	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		int i = m - 1, j = n - 1, k = m + n - 1;
		while (j >= 0 || i >= 0) {
			if (j < 0)
				nums1[k--] = nums1[i--];
			else if (i < 0)
				nums1[k--] = nums2[j--];
			else
				nums1[k--] = (nums2[j] > nums1[i]) ? nums2[j--] : nums1[i--];

		}
	}

	public static List<List<Integer>> generate(int numRows) {
		// 关于list的方法基本都在这了
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		if (numRows == 0)
			return list;
		int j, sum;
		List<Integer> former = new ArrayList<Integer>();
		List<Integer> cur = new ArrayList<Integer>();
		former.add(1);
		list.add(former);
		for (int i = 0; i < numRows - 1; i++) {
			cur = new ArrayList<Integer>();
			cur.add(1);
			j = 0;
			while (j < former.size() - 1) {
				sum = former.get(j) + former.get(j + 1);
				cur.add(sum);
				j++;
			}
			cur.add(1);
			list.add(cur);
			former = new ArrayList<Integer>();
			former = cur.subList(0, cur.size());
		}
		return list;

	}

	public static int[] twoSum(int[] numbers, int target) {
		int[] re = new int[2];
		for (int i = 0; i < numbers.length - 1;) {
			while (numbers[i] == numbers[i + 1]) {
				if (numbers[i] + numbers[i + 1] == target) {
					re[0] = i + 1;
					re[1] = i + 2;
					return re;
				}
				i++;
			}
			for (int j = i + 1; j < numbers.length; j++) {
				if (numbers[i] + numbers[j] > target)
					break;
				if (numbers[i] + numbers[j] == target) {
					re[0] = i + 1;
					re[1] = j + 1;
					return re;
				}
			}
			i++;
		}
		return re;
	}

	public static int maxSubArray(int[] nums) {
		int len = nums.length;
		int i, max = nums[0];
		int[] d = new int[len];
		d[0] = nums[0];
		for (i = 1; i < len; i++) {
			d[i] = nums[i];
			if (d[i - 1] + nums[i] > d[i]) {
				d[i] = d[i - 1] + nums[i];
			}
			if (d[i] > max)
				max = d[i];
		}
		return max;
	}

	public static String addBinary(String a, String b) {

		String s = new String();
		String l = new String();
		int flag = 0, sum, n1, n2, i, j;
		StringBuilder r = new StringBuilder();
		if (a.length() > b.length()) {
			s = b;
			l = a;
		} else {
			s = a;
			l = b;
		}
		for (i = l.length() - 1, j = s.length() - 1; i >= 0; i--, j--) {
			if (j >= 0) {
				n1 = l.charAt(i) - 48;
				n2 = s.charAt(j) - 48;
				sum = n1 + n2 + flag;
			} else {
				n1 = l.charAt(i) - 48;
				sum = n1 + flag;

			}
			if (sum >= 2) {
				flag = 1;
				sum = sum - 2;
			} else {
				flag = 0;
			}
			r.append(sum);
		}
		if (flag == 1)
			r.append('1');
		StringBuilder result = new StringBuilder();
		for (i = r.length() - 1; i >= 0; i--) {
			result.append(r.charAt(i));
		}
		return result.toString();

	}

	public int singleNumber(int[] nums) {
		int re = 0;
		for (int i = 0; i < nums.length; i++) {
			re ^= nums[i];
		}
		return re;
	}

	public static boolean isPalindrome(String s) {
		int i = 0, j = s.length() - 1, flag = 0;
		if (s.equals(""))
			return true;
		String ss = s.toLowerCase();
		while (i < j) {
			while (i < j && !((ss.charAt(i) >= 'a' && ss.charAt(i) <= 'z') || Character.isDigit(ss.charAt(i))))
				i++;
			while (i < j && !((ss.charAt(j) >= 'a' && ss.charAt(j) <= 'z') || Character.isDigit(ss.charAt(j))))
				j--;
			// if(i==j && flag==0) return true;
			if (ss.charAt(i) != ss.charAt(j)) {
				flag = 1;
				break;
			}
			i++;
			j--;
		}
		if (flag == 1)
			return false;
		else
			return true;
	}

	public int trailingZeroes(int n) {
		int re = 0, x = 5;
		while (n > x) {
			re += n / x;
			x *= 5;
		}
		return re;
	}

	public static boolean isAnagram(String s, String t) {
		char[] str1 = s.toCharArray();
		char[] str2 = t.toCharArray();
		Arrays.sort(str1);
		Arrays.sort(str2);
		String re1 = new String(str1);
		String re2 = new String(str2);
		return re1.equals(re2);
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public static int countPrimes(int n) {
		int sum = 0;
		boolean[] notprime = new boolean[n];
		for (int i = 2; i < n; i++) {
			if (notprime[i] == false) {
				sum++;
				for (int j = 2; i * j < n; j++) {
					notprime[i * j] = true;
				}
			}

		}
		return sum;
	}

	public static List<Integer> getRow(int rowIndex) {
		List<Integer> re = new ArrayList<Integer>();
		re.add(1);
		for (int i = 1; i <= rowIndex; i++) {
			re.add(1);
			for (int j = i - 1; j > 0; j--) {
				re.set(j, re.get(j) + re.get(j - 1));
			}
		}
		return re;
	}

	public int maxProfit(int[] prices) {
		int max = 0, cur = 0;
		for (int i = 1; i < prices.length; i++) {
			cur = Math.max(0, cur += prices[i] - prices[i - 1]);
			max = Math.max(max, cur);
		}
		return max;
	}

	public boolean hasCycle(ListNode head) {
		List<ListNode> list = new ArrayList<ListNode>();
		while (head != null) {
			if (list.contains(head)) {
				return true;
			}
			list.add(head);
			head = head.next;
		}
		return false;
	}

	// public int maxProfit2(int[] prices) {
	// int max=0,cur=0;
	// int[] used=new int[prices.length];
	// for(int i=1;i<prices.length;i++)
	// {
	// cur=Math.max(0, cur+=prices[i]-prices[i-1]);
	// max=Math.max(max, cur);
	// }
	// return max;
	// }

	// public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
	//
	// }

	public void nextPermutation(int[] nums) {
		int l = nums.length;
		int i = l - 2;
		while (i >= 0 && nums[i] > nums[i + 1]) {
			i--;
		}
		if (i >= 0) {
			int j = l - 1;
			while (j >= 0 && nums[j] <= nums[i]) {
				j--;
			}
			swap(nums, i, j);
		}
		reverse(nums, i + 1);

	}

	public void reverse(int[] nums, int start) {
		for (int i = start, j = nums.length - 1; i < j; i++, j--) {
			swap(nums, i, j);
		}
	}

	public void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> list = new LinkedList<Integer>();
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

	public int[][] generateMatrix(int n) {
		int i, j = 1;
		int[][] matrix = new int[n][n];
		int left = 0, right = n - 1, head = 0, bottom = n - 1;
		while (left <= right && bottom >= head) {
			for (i = left; i <= right; i++) {
				matrix[head][i] = j++;
			}
			head++;
			if (head > bottom)
				continue;
			for (i = head; i <= bottom; i++) {
				matrix[i][right] = j++;
			}
			right--;
			if (right < left)
				continue;
			for (i = right; i >= left; i--) {
				matrix[bottom][i] = j++;
			}
			bottom--;
			if (head > bottom)
				continue;
			for (i = bottom; i >= head; i--) {
				matrix[i][left] = j++;
			}
			left++;
		}
		return matrix;
	}

	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return false;
		int h = matrix.length, l = matrix[0].length;
		if (target < matrix[0][0])
			return false;
		if (target > matrix[h - 1][l - 1])
			return false;
		for (int i = 0; i < h; i++) {
			if (target >= matrix[i][0] && target <= matrix[i][l - 1])
				for (int j = 0; j < l; j++) {
					if (matrix[i][j] == target)
						return true;
				}

		}
		return false;
	}

	public static String getPermutation(int n, int k) {
		int i, pro = 1, l = 0;
		// k--;
		List<Integer> nums = new ArrayList<Integer>();
		char[] str = new char[n];
		for (i = 1; i <= n; i++) {
			pro *= i;
			nums.add(i);
		}
		// if(k==pro)
		// {
		// for(i=n;i>=1;i--)
		// str[l++]=(char)(i+48);
		// return new String(str);
		// }
		while (pro > 1) {
			pro /= n;
			str[l++] = (char) (nums.get(k / pro) + 48); // int转char
			nums.remove(k / pro);
			n--;
			k = k - k / pro * pro;
		}
		str[l] = (char) (nums.get(0) + 48);
		return new String(str);
	}

	public List<Integer> grayCode(int n) {
		int i, pro = 1, loc = 2;
		for (i = 0; i < n; i++)
			pro *= 2;
		int[] re = new int[pro];
		re[0] = 0;
		List<Integer> list = new LinkedList<Integer>();
		list.add(0);
		if (n == 0)
			return list;
		re[1] = 1;
		for (i = 1; i < n; i++) {
			for (int j = loc; j < loc * 2; j++) {
				re[j] = re[2 * loc - j - 1] + loc;
			}
			loc *= 2;
		}
		for (i = 1; i < re.length; i++)
			list.add(re[i]);
		return list;
	}

	// public static String convertToTitle(int n) {
	// int dig,i=(n-1)/26;
	// char[] str=new char[i+1];
	// while(n>0)
	// {
	// dig=(n-1)%26;
	// str[i--]=(char) (65+dig);
	// n=(n-1)/26;
	// }
	// return new String(str);
	// }

	public String convertToTitle(int n) {
		StringBuilder str = new StringBuilder();
		while (n > 0) {
			n--;
			str.insert(0, (char) ('A' + n % 26));
			n = n / 26;
		}
		return new String(str);
	}

	public static int titleToNumber(String s) {
		int n = 1, re = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			re += (s.charAt(i) - 'A' + 1) * n;
			n *= 26;

		}
		return re;
	}

	
	public void rotate(int[] nums, int k) {
		int l = nums.length;
		if (k >= l)
			k = k % l;
		reverse(nums, 0, l - 1);
		reverse(nums, 0, k - 1);
		reverse(nums, k, l - 1);
	}

	public void reverse(int[] nums, int start, int end) {
		for (int i = start, j = end; i < j; i++, j--) {
			swap(nums, i, j);
		}
	}

	public int rob(int[] nums) {
		int l = nums.length, i;
		if (l == 0)
			return 0;
		else if (l == 1)
			return nums[0];
		else if (l == 2)
			return Math.max(nums[0], nums[1]);
		int w[] = new int[l];
		w[0] = nums[0];
		w[1] = nums[1];
		for (i = 2; i < l; i++) {
			int max = 0;
			for (int j = 0; j < i - 1; j++) {
				if (w[j] > max)
					max = w[j];
			}
			w[i] = nums[i] + max;
		}
		return w[l - 1];
	}

	public static class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}
	
	
	
//	public List<Interval> merge(List<Interval> intervals) {
//		Collections.sort(intervals, new IntervalComparator());
//		LinkedList<Interval> result=new LinkedList<Interval>();
//		//注意getlast是linkedlist特有的接口
//		for(Interval val:intervals)
//		{
//				if(result.isEmpty()||result.getLast().end<val.start)
//					result.add(val);
//				else 
//					result.getLast().end=Math.max(val.end,result.getLast().end);
//		}
//		return result;
//    }
	
	private static class IntervalComparator implements Comparator<Interval>
	{
			@Override
			public int compare(Interval a,Interval b)
			{
				return a.start<b.start?-1:a.start==b.start?0:1;
			}
	}
	
	public boolean canAttendMeetings(Interval[] intervals) {
		Arrays.sort(intervals, new IntervalComparator());
		LinkedList<Interval> result=new LinkedList<Interval>();
		for(Interval val:intervals)
		{
				if(result.isEmpty()||result.getLast().end<=val.start)
					result.add(val);
				else 
					return false;
		}
		return true;
    }
	
	
	public static int minMeetingRooms(Interval[] intervals) {
		if(intervals==null||intervals.length==0) return 0;
		//比较器的lambda表示方法
		//比较器负数为小于，0是等于，正数是大于
        Arrays.sort(intervals,(x,y)->x.start==y.start?x.end-y.end:x.start-y.start);
        
        //优先度列,整数型默认按从小到大建堆
        Queue<Integer> result=new PriorityQueue<Integer>();
        //添加元素
        result.offer(intervals[0].end);
        
        for(int i=1;i<intervals.length;i++) {
        		if(intervals[i].start>=result.peek()) result.poll();
        		result.offer(intervals[i].end);
        }
        
		return result.size();
    }
	
	
	// returns the number of one-bits
	public int hammingDistance(int x, int y) {
		return Integer.bitCount(x ^ y);
	}

	public char findTheDifference(String s, String t) {
		int sums = t.charAt(s.length());
		for (int i = 0; i < s.length() - 1; i++) {
			sums -= s.charAt(i);
			sums += t.charAt(i);
		}
		return (char) sums;
	}

	public static int findNthDigit(int n) {

		int i = 1, d = 9, sum = 0, now = 0;
		// if(n<10) return n;
		while (sum < 788888889 && sum + i * d < n) {
			now += d;
			sum += i * d;
			i++;
			d *= 10;
		}
		int y = (n - sum - 1) / i;
		int s = (n - sum - 1) % i;
		now = now + y + 1;
		for (int j = 0; j < i - s - 1; j++) {
			now /= 10;
		}
		return now % 10;
	}

	public List<String> fizzBuzz(int n) {
		List<String> list = new LinkedList<String>();
		for (int i = 1; i <= n; i++) {
			if (i % 3 == 0 && i % 5 == 0)
				list.add(new String("FizzBuzz"));
			else if (i % 3 == 0)
				list.add(new String("Fizz"));
			else if (i % 5 == 0)
				list.add(new String("Buzz"));
			else
				list.add(String.valueOf(i));
		}
		return list;
	}

	public static String addStrings(String num1, String num2) {
		StringBuilder result = new StringBuilder();
		int i = num1.length() - 1, j = num2.length() - 1, n1, n2, flag = 0, sum = 0;
		while (i >= 0 || j >= 0) {
			n1 = i < 0 ? 0 : (num1.charAt(i--) - '0');
			n2 = j < 0 ? 0 : (num2.charAt(j--) - '0');
			sum = n1 + n2 + flag;
			if (sum >= 10) {
				flag = 1;
				sum -= 10;
			} else {
				flag = 0;
			}
			result.append(String.format("%c", sum + '0'));
		}
		if (flag == 1)
			result.append("1");
		result.reverse();
		return result.toString();
	}

	public static class TwoSum {
		private Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		private List<Integer> list = new ArrayList<Integer>();

		/** Initialize your data structure here. */
		public TwoSum() {

		}

		/** Add the number to an internal data structure.. */
		public void add(int number) {
			if (map.containsKey(number))
				map.put(number, map.get(number) + 1);
			else
				map.put(number, 1);
			list.add(number);

		}

		/** Find if there exists any pair of numbers which sum is equal to the value. */

		public boolean find(int value) {
			for (int i = 0; i < list.size(); i++) {
				int num1 = list.get(i);
				int num2 = value - num1;
				if (num1 == num2 && map.get(num2) > 1 || (num1 != num2 && map.containsKey(num2))) // !!hashmap的搜索速度要比List快很多用list.contain会超时
					return true;
			}
			return false;
		}
	}

	public ListNode removeElements(ListNode head, int val) {
		while (head != null && head.val == val)
			head = head.next;
		if (head == null)
			return head;
		ListNode result = head;
		ListNode latter = head.next;
		while (latter != null) {
			ListNode n = latter.next;
			if (latter.val == val) {
				head.next = n;
				latter = n;
			} else {
				head = head.next;
				latter = latter.next;
			}
		}
		return result;
	}

	public void setZeroes(int[][] matrix) {
		boolean fr = false, fc = false;
		int i, j;
		for (i = 0; i < matrix.length; i++)
			for (j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					if (i == 0)
						fr = true;
					if (j == 0)
						fc = true;
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		for (i = 0; i < matrix.length; i++)
			for (j = 0; j < matrix[0].length; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0)
					matrix[i][j] = 0;
			}
		if (fc) {
			for (i = 0; i < matrix.length; i++)
				matrix[i][0] = 0;
		}
		if (fr) {
			for (i = 0; i < matrix[0].length; i++)
				matrix[0][i] = 0;
		}
	}

	/**
	 * leetcode 48 转置矩阵
	 * @param matrix
	 */
	public void rotate(int[][] matrix) {
		reverse(matrix);
		reversesym(matrix);
	}

	public void reverse(int[][] matrix) {
		int m;
		for (int i = 0; i < matrix[0].length; i++) {
			for (int j = 0; j < matrix.length / 2; j++) {
				m = matrix[i][j];
				matrix[i][j] = matrix[i][matrix.length - 1 - j];
				matrix[i][matrix.length - 1 - j] = m;
			}
		}
	}

	public void reversesym(int[][] matrix) {
		int m;
		for (int i = 0; i < matrix.length - 1; i++) {
			for (int j = i + 1; j < matrix[0].length; j++) {
				m = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[i][j] = m;
			}
		}
	}

	public List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List> hashm = new HashMap<String, List>();
		for (String str : strs) {
			char[] w = str.toCharArray();
			Arrays.sort(w);
			String key = String.valueOf(w);
			if (!hashm.containsKey(key))
				hashm.put(key, new ArrayList<String>());
			hashm.get(key).add(str);

		}
		return new ArrayList(hashm.values());
	}

	public int missingNumber(int[] nums) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += i + 1;
			sum -= nums[i];
		}
		return sum;
	}

	class MyQueue {
		Queue<Integer> q = new LinkedList<Integer>();
		Stack<Integer> st = new Stack<Integer>();

		/** Initialize your data structure here. */
		public MyQueue() {

		}

		/** Push element x to the back of queue. */
		public void push(int x) {
			q.offer(x);
			st.push(x);
		}

		/** Removes the element from in front of queue and returns that element. */
		public int pop() {
			// Stack<Integer> tran=new Stack<Integer>();
			// while(st.size()>1)
			// {
			//
			// tran.push(st.pop());
			// }
			// int result=st.pop();
			// while(!tran.isEmpty())
			// {
			// st.push(tran.pop());
			// }
			// return result;
			Queue<Integer> tran = new LinkedList<Integer>();
			while (q.size() > 1) {

				tran.offer(q.poll());
			}
			int result = q.poll();
			while (!tran.isEmpty()) {
				q.offer(tran.poll());
			}
			return result;
		}

		/** Get the front element. */
		public int peek() {
			Stack<Integer> tran = new Stack<Integer>();
			while (st.size() > 1) {

				tran.push(st.pop());
			}
			int result = st.peek();
			while (!tran.isEmpty()) {
				st.push(tran.pop());
			}
			return result;
		}

		/** Returns whether the queue is empty. */
		public boolean empty() {
			return st.isEmpty();
		}
	}

	public static boolean isIsomorphic(String s, String t) {
		int[] m1 = new int[256];
		int[] m2 = new int[256];
		int n = s.length();
		for (int i = 0; i < n; i++) {
			if (m1[s.charAt(i)] != m2[t.charAt(i)])
				return false;
			m1[s.charAt(i)] = i + 1;
			m2[t.charAt(i)] = i + 1;
		}
		return true;
	}

	public int minimumTotal(List<List<Integer>> triangle) {
		List<Integer> tran = new ArrayList<Integer>();
		int i, j, min = Integer.MAX_VALUE;
		for (i = 0; i < triangle.size(); i++) {
			List<Integer> cur = new ArrayList<Integer>(triangle.get(i));
			if (i == 0) {
				tran.add(cur.get(0));
				continue;
			}
			for (j = cur.size() - 1; j >= 0; j--) {
				if (j == cur.size() - 1)
					tran.add(cur.get(j) + tran.get(j - 1));
				else if (j == 0)
					tran.set(0, cur.get(0) + tran.get(0));
				else
					tran.set(j, Math.min(tran.get(j), tran.get(j - 1)) + cur.get(j));
			}
		}
		for (j = 0; j < i; j++) {
			if (tran.get(j) < min)
				min = tran.get(j);
		}
		return min;

	}

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

	
	public static String multiply(String num1, String num2) {
		int l1=num1.length(),l2=num2.length();
		int[] pos=new int[l1+l2];
		for(int i=l1-1;i>=0;i--)
			for(int j=l2-1;j>=0;j--)
			{
				int cur=(num1.charAt(i)-'0')*(num2.charAt(j)-'0');
				int p1=i+j,p2=i+j+1;
				int sum=cur+pos[p2];
				pos[p1]+=sum/10;
				pos[p2]=sum%10;
				
			}
		StringBuilder str=new StringBuilder();
		for(int num:pos) 
			if(!(str.length()==0&&num==0))
			str.append(num);
		return str.length()==0?"0":str.toString();
    }
	
	public String simplifyPath(String path) {
		Deque<String> st=new LinkedList<String>();
        List<String> sig=Arrays.asList("..",".","");
        for(String ele:path.split("/"))
        {
        		if(ele.equals("..")&&!st.isEmpty())  st.pollLast();
        		else if(!sig.contains(ele)) st.addLast(ele);
        }
        String res="";
        for(String ele:st)
        {
        		res=res+"/"+ele;
        }
        return res==""?"/":res;
    }//在这个题里面说明：deque的结构和stack一样，队头是在栈顶，但是当把它拆分开的时候，又是queue的结构，首先获得的是队尾的数据
	
	public void moveZeroes(int[] nums) {
        int j=0,m;
        for(int i=0;i<nums.length;i++)
        {
        		if(nums[i]!=0)
        		{
        			m=nums[i];
        			nums[i]=nums[j];
        			nums[j]=m;
        			j++;
        		}
        }
   }
	
	public int minPathSum(int[][] grid) {
		int lowN=grid.length,colN=grid[0].length;
		for(int i=0;i<lowN;i++)
			for(int j=0;j<colN;j++)
			{
				if(i==0&&j!=0) grid[i][j]=grid[i][j-1]+grid[i][j];
				else if(j==0&&i!=0) grid[i][j]=grid[i-1][j]+grid[i][j];
				else if(i==0&&j==0) grid[i][j]=grid[i][j];
				else grid[i][j]=Math.min(grid[i-1][j], grid[i][j-1])+grid[i][j];
			}
        return grid[lowN-1][colN-1];
        
    }
	
	public static boolean wordBreak(String s, List<String> wordDict) {
        StringBuilder st=new StringBuilder(s);
        for(int i=0;i<wordDict.size();i++)
        {
        		int loc=st.indexOf(wordDict.get(i));
        		if(loc!=-1)
        		{
        			st.delete(loc, wordDict.get(i).length());
        			wordDict.remove(i);
        			i=-1;
        		}
        }
        if(st.length()==0) return true;
        else return false;
    }
	
	public static boolean isPowerOfThree(int n) {
		int max=(int) Math.pow(3, (int)(Math.log(Integer.MAX_VALUE)/Math.log(3)));
		return max%n==0;
    }
	
	
	public int evalRPN(String[] tokens) {
        Stack<String> st=new Stack<String>();
        for(String str:tokens)
        {
        		if(str.equals("+")) 
        		{
        			int num1=Integer.valueOf(st.pop());
        			int num2=Integer.valueOf(st.pop());
        			st.push(String.valueOf(num1+num2));
        		}else if(str.equals("-")) {
        			int num1=Integer.valueOf(st.pop());
        			int num2=Integer.valueOf(st.pop());
        			st.push(String.valueOf(num2-num1));
        		}else if(str.equals("*")) {
        			int num1=Integer.valueOf(st.pop());
        			int num2=Integer.valueOf(st.pop());
        			st.push(String.valueOf(num1*num2));
        		}else if(str.equals("/")) {
        			int num1=Integer.valueOf(st.pop());
        			int num2=Integer.valueOf(st.pop());
        			st.push(String.valueOf(num2/num1));
        		}else {
        			st.push(str);
        		}
        }
        return Integer.valueOf(st.pop());
    }
	
	public boolean isUgly(int num) {
		if(num<=1) return false;
        while(num%2==0)
        	num/=2;
        while(num%3==0)
        	num/=3;
        while(num%5==0)
        	num/=5;
        if(num==1) return true;
        else return false;
    }
	
	
	public boolean canPermutePalindrome(String s) {
        int[] num=new int[256];
        int i,sum=0;
        for(i=0;i<s.length();i++)
        {
        		num[s.charAt(i)]++;
        }
        for(i=0;i<256;i++)
        {
        		if(num[i]%2==1) sum++;
        }
        if(sum<=1) return true;
        else return false;
    }
	
	public static String reverseVowels(String s) {
        Set<Character> vowel=new HashSet<Character>();
        char[] str=s.toCharArray();
        int start=0,end=s.length()-1;
        vowel.add('a');
        vowel.add('e');
        vowel.add('i');
        vowel.add('o');
        vowel.add('u');
        vowel.add('A');
        vowel.add('E');
        vowel.add('I');
        vowel.add('O');
        vowel.add('U');
        while(start<end)
        {
        		while(start<end&&!vowel.contains(str[start])) start++;
        		while(start<end&&!vowel.contains(str[end])) end--;
        		char temp=str[start];
        		str[start]=str[end];
        		str[end]=temp;
        		start++;
        		end--;
        }
        return new String(str);
    }
	
	public static int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }
	
	public boolean isPowerOfFour(int num) {
		if(num<=0) return false;
		int max=0x55555555;
        if((num&(num-1))==0)
        	if((num&max)!=0)  return true;
        return false;
    }
	
	public int majorityElement(int[] nums) {
        Map<Integer,Integer> ele=new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++)
        {
        		if(ele.containsKey(nums[i])) ele.put(nums[i], ele.get(nums[i])+1);
        		else ele.put(nums[i], 1);
        }
        Map.Entry<Integer, Integer> majorityEle=null;
        for(Map.Entry<Integer, Integer> element:ele.entrySet())
        {
        		if(majorityEle==null||element.getValue()>majorityEle.getValue())
        		{
        			majorityEle=element;
        		}
        }
        return majorityEle.getKey();
    }
	
	public int firstUniqChar(String s) {
		if(s==null||s.length()==0) return -1;
		Set<Character> dup=new HashSet<Character>();
        for(int i=0;i<s.length()-1;i++)
		{
        		if(dup.contains(s.charAt(i))) continue;
			String sub=s.substring(i+1, s.length());
			if(sub.indexOf(s.substring(i,i+1))==-1)
				return i;
			else dup.add(s.charAt(i));
		}
		if(dup.contains(s.charAt(s.length()-1))) return -1;
		else return s.length()-1;
    }
	
	public int calPoints(String[] ops) {
        Stack<String> q=new Stack<String>();
        int sum=0,temp;
        for(int i=0;i<ops.length;i++)
        {
        		if(ops[i].equals("C")) {
        			sum-=Integer.valueOf(q.pop());
        		}else if(ops[i].equals("D")) {
        			q.push(String.valueOf(2*Integer.valueOf(q.peek())));
        			sum+=Integer.valueOf(q.peek());
        		}else if(ops[i].equals("+")) {
        			temp=Integer.valueOf(q.pop());
        			int cur=temp+Integer.valueOf(q.peek());
        			q.push(String.valueOf(temp));
        			q.push(String.valueOf(cur));
        			sum+=Integer.valueOf(q.peek());
        		}else {
        			q.push(ops[i]);
        			sum+=Integer.valueOf(q.peek());
        		}
        }
        return sum;
    }
	
	
//	public int maxProduct(int[] nums) {
//		if(nums==null||nums.length==0) return 0;
//        int[] pro=new int[nums.length];
//        pro[0]=nums[0];
//        for(int i=0;i<nums.length;i++)
//        {
//        		pro[i]=
//        }
//    }
	
	public int findDuplicate(int[] nums) {
		int sum=0,total=0;
        	for(int i=0;i<nums.length;i++)
        	{
        		if(i==nums.length-1) 
        		{
        			sum+=nums[i];
        			break;
        		}
        		sum+=nums[i];
        		total+=i+1;
        		
        	}
        	return sum-total;
    }
	
	public static boolean isHappy(int n) {
       Set<Integer> en=new HashSet<Integer>(); //existing number
       while(!en.contains(n))
       {
    	   		en.add(n);
    	   		if(n==1) return true;
    	   		int sum=0;
    	   		en.add(n);
    	   		while(n/10>0)
    	   		{
    	   			sum+=Math.pow(n%10, 2);
    	   			n/=10;
    	   		}
    	   		sum+=Math.pow(n%10, 2);
    	   		n=sum;
       }
       return false;
    }
	
	public int findKthLargest(int[] nums, int k) {
		int max=0;
		boolean[] used=new boolean[nums.length];
        for(int i=0;i<k;i++)
        {
        		int o=0;
        		while(used[o]) o++;
        		max=nums[o];
        		for(int j=0;j<nums.length;j++)
        		{
        			if(!used[j]&&nums[j]>max)
        			{
        				o=j;
        				max=nums[j];
        			}
        		}
        		if(i==k-1) return max;
        		used[o]=true;
        		
        }
        return max;
    }
	
	public static int rangeBitwiseAnd(int m, int n) {
		//Math.log是以e为底
		if(m==0||n==0) return 0;
		if(m==n) return m;
		int sum=0,cur;
        while((int)(Math.log(m)/Math.log(2))==(int)(Math.log(n)/Math.log(2)))
        {
        		cur=(int)Math.pow(2,(int)(Math.log(m)/Math.log(2)));
        		sum+=cur;
        		m-=cur;
        		n-=cur;
        }
        return sum;
    }
	
	
	public int rob2(int[] nums) {
        int l=nums.length,i,r1,r2;
        if(l==0) return 0;
        else if(l==1) return nums[0];
        else if(l==2) return Math.max(nums[0], nums[1]);
    		int w[]=new int[l];
        w[0]=nums[0];
        w[1]=nums[1];
        for(i=2;i<l-1;i++)
        {
        		int max=0;
        		for(int j=0;j<i-1;j++)
        		{
        			if(w[j]>max) max=w[j];
        		}
        		w[i]=nums[i]+max;
        }
        r1=Math.max(w[l-2],w[l-3]);
        w[1]=nums[1];
        for(i=2;i<l;i++)
        {
        		int max=0;
        		for(int j=1;j<i-1;j++)
        		{
        			if(w[j]>max) max=w[j];
        		}
        		w[i]=nums[i]+max;
        }
        r2=Math.max(w[l-1],w[l-2]);
        return Math.max(r1,r2);
    }	
	
	public static int[] sortTransformedArray(int[] nums, int a, int b, int c) {
		int[] result=new int[nums.length];
		int i=0,j,k=0;
		if(a==0&&b<0)
		{
			for(i=nums.length-1,j=0;i>=0;i--,j++)
			result[j]=nums[i]*b+c;
			return result;
		}else if(a==0&&b>0) {
			for(i=0,j=0;i<nums.length;i++,j++)
				result[j]=nums[i]*b+c;
				return result;
		}
			
		double l=-b*1.0/(2*a);
		while(nums[i]<l) i++;
		if(i>0) i--;
		if(i==nums.length-1) j=nums.length-1;
		else j=i+1;
		while(i>=0&&j<nums.length)
		{
			if(Math.abs(nums[i]-l)<Math.abs(nums[j]-l))
			{
				result[k]=nums[i--];
			}else 
				result[k]=nums[j++];
			k++;
		}
		if(i==-1)
		while(j<nums.length)
		result[k++]=nums[j++];
		if(j==nums.length)
		while(i>=0)
		result[k++]=nums[i--];
		if(a<0)
		for(i=0,j=result.length-1;i<result.length/2;i++,j--)
		{
			int temp=result[i];
			result[i]=result[j];
			result[j]=temp;
		}
		for(i=0;i<result.length;i++)
		result[i]=a*result[i]*result[i]+result[i]*b+c;
		return result;
	}
    
	//线段树例题
	class NumArray {
		int[] num;
		int l;
	    public NumArray(int[] nums) {
	    		if(nums.length>0) {
	    			l=nums.length;
		    		num=new int[l*2];
		        for(int i=l,j=0;j<nums.length;i++,j++) {
		        		num[i]=nums[j];
		        }
		        for(int i=l-1;i>0;i--) {
		        		num[i]=num[i*2]+num[i*2+1];
		        }
	    		}
	    		
	    }
	    
	    public void update(int i, int val) {
	    		i=i+l;
	    		num[i]=val;
	    		
	    		
	    		while(i>0) {
	    			int left=i,right=i;
	    			if(i%2==0) {
		    			right++;
		    		}else {
		    			left--;
		    		}
	    			num[i/2]=num[left]+num[right];
	    			i/=2;
	    		}
	        
	    }
	    
	    public int sumRange(int i, int j) {
	    		i+=l;
	    		j+=l;
	    		int sum=0;
	    		while(i<=j) {
	    			if(i%2==1) {
	    				sum+=num[i];
	    				i++;
	    			}
	    			if(j%2==0) {
	    				sum+=num[j];
	    				j--;
	    			}
	    			i/=2;
	    			j/=2;
	    		}
	        return sum;
	    }
	}
	
	public int longestPalindrome(String s) {
        int[] map=new int[128];
        for(int i=0;i<s.length();i++) {
        		map[s.charAt(i)]++;
        }
        int sum=0;
        for(int i=0;i<128;i++) {
        		int temp=map[i];
        		sum+=temp/2*2;
        		if(sum%2==0&&temp%2==1) sum++;
        }
        return sum;
    }
	
	
	
	public static void main(String[] args) {
		
		Interval i1=new Interval(0,30);
		Interval i2=new Interval(5,10);
		Interval i3=new Interval(15,20);
		Interval[] ins=new Interval[3];
		ins[0]=i1;
		ins[1]=i2;
		ins[2]=i3;
		
		System.out.println(multiply("20000000000000000000","30000000000000000000"));
		
	

	}
}