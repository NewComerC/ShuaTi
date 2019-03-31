package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DP {

	//91题 数字转字母解码方式
	 public int numDecodings(String s) {
		 if(s.length()==0) return 0;
		 int l=s.length();
	     int dp[]=new int[l+1]; 
		 dp[0]=1;
		 dp[1]=s.charAt(0)=='0'? 0:1;
		 for(int i=2;i<l+1;i++)
		 {
			 Integer first=Integer.valueOf(s.substring(i-1, i));
			 Integer second=Integer.valueOf(s.substring(i-2,i));
			 if(first!=0) dp[i]+=dp[i-1];
			 if(second>=10&&second<=26) dp[i]+=dp[i-2];
		 }
		 return dp[l];
	    }
	
	
	 public int lengthOfLIS(int[] nums) {
		 if(nums.length==0) return 0;
	     int[] dp=new int[nums.length];
	     dp[0]=1;
	     for(int i=1;i<nums.length;i++)
	     {
	    	 	dp[i]=1;
	    	 	for(int j=0;j<i;j++) {
	    	 		if(nums[j]<nums[i]&&dp[j]+1>dp[i]) dp[i]=dp[j]+1;
	    	 		
	    	 	}
	     }
	     int max=dp[0];
	     for(int i=0;i<nums.length;i++)
	    	 if(max<dp[i]) max=dp[i];
		 return max;
	    }
	 
		public static int minDistance(String word1, String word2) {
//			int len1 = word1.length();
//			int len2 = word2.length();
//		 
//			// len1+1, len2+1, because finally return dp[len1][len2]
//			int[][] dp = new int[len1 + 1][len2 + 1];
//		 
//			for (int i = 0; i <= len1; i++) {
//				dp[i][0] = i;
//			}
//		 
//			for (int j = 0; j <= len2; j++) {
//				dp[0][j] = j;
//			}
//		 
//			//iterate though, and check last char
//			for (int i = 0; i < len1; i++) {
//				char c1 = word1.charAt(i);
//				for (int j = 0; j < len2; j++) {
//					char c2 = word2.charAt(j);
//		 
//					//if last two chars equal
//					if (c1 == c2) {
//						//update dp value for +1 length
//						dp[i + 1][j + 1] = dp[i][j];
//					} else {
//						int replace = dp[i][j] + 1;
//						int insert = dp[i][j + 1] + 1;
//						int delete = dp[i + 1][j] + 1;
//		 
//						int min = replace > insert ? insert : replace;
//						min = delete > min ? min : delete;
//						dp[i + 1][j + 1] = min;
//					}
//				}
//			}
//		 
//			return dp[len1][len2];
			int l1=word1.length(),l2=word2.length();
			int[][] dp=new int[l1+1][l2+1];
			for(int i=0;i<=l1;i++) {
				dp[i][0]=i;
			}
			
			for(int i=0;i<=l2;i++) {
				dp[0][i]=i;
			}
			
			for(int i=1;i<=l1;i++)
				for(int j=1;j<=l2;j++) {
					if(word1.charAt(i-1)==word2.charAt(j-1)) dp[i][j]=dp[i-1][j-1];
					else dp[i][j]=Math.min(Math.min(dp[i-1][j], dp[i][j-1]),dp[i-1][j-1])+1;
				}
			return dp[l1][l2];
		}
		
		/**
	     * @param m 表示背包的最大容量
	     * @param n 表示商品个数
	     * @param w 表示商品重量数组
	     * @param p 表示商品价值数组
	     */
	    public static int[][] BackPack_Solution(int m, int n, int[] w, int[] p) {
	        //c[i][v]表示前i件物品恰放入一个重量为m的背包可以获得的最大价值
	        int c[][] = new int[n + 1][m + 1];
	        for (int i = 0; i < n + 1; i++)
	            c[i][0] = 0;
	        for (int j = 0; j < m + 1; j++)
	            c[0][j] = 0;
	 
	        for (int i = 1; i < n + 1; i++) {
	            for (int j = 1; j < m + 1; j++) {
	                //当物品为i件重量为j时，如果第i件的重量(w[i-1])小于重量j时，c[i][j]为下列两种情况之一：
	                //(1)物品i不放入背包中，所以c[i][j]为c[i-1][j]的值
	                //(2)物品i放入背包中，则背包剩余重量为j-w[i-1],所以c[i][j]为c[i-1][j-w[i-1]]的值加上当前物品i的价值
	                if (w[i - 1] <= j) {
	                    if (c[i - 1][j] < (c[i - 1][j - w[i - 1]] + p[i - 1]))
	                        c[i][j] = c[i - 1][j - w[i - 1]] + p[i - 1];
	                    else
	                        c[i][j] = c[i - 1][j];
	                } else
	                    c[i][j] = c[i - 1][j];
	            }
	        }
	        return c;
	    }
	
	    
	    public static boolean isMatch(String s, String p) {
    		Boolean[][] dp=new Boolean[s.length()+1][p.length()+1];
    		return proceed(dp, 0, 0, s, p);
        
    }
    
    public static boolean proceed(Boolean[][] dp,int i,int j,String text,String pattern) {
    	if(dp[i][j]!=null) {
    		return dp[i][j];
    	}
    	boolean res;
    		if(j==pattern.length()) {
    			res=i==text.length();
    		}else {
    			boolean firstMatch=(i<text.length()&&(text.charAt(i)==pattern.charAt(j)||pattern.charAt(j)=='.'));
	    		
	    		if(j<pattern.length()-1&&pattern.charAt(j+1)=='*') {
	    			res=proceed(dp, i, j+2, text, pattern)||(firstMatch&&proceed(dp,i+1,j,text,pattern));
	    		}else {
	    			return firstMatch&&proceed(dp, i+1, j+1, text, pattern);
	    		}
	    	
    		}
    		dp[i][j]=res;
    		return res;
    }
    
    
//    enum Result {
//        TRUE, FALSE
//    }
//
//    
//        static Result[][] memo;
//
//        public static boolean isMatch(String text, String pattern) {
//            memo = new Result[text.length() + 1][pattern.length() + 1];
//            return dp(0, 0, text, pattern);
//        }
//
//        public static boolean dp(int i, int j, String text, String pattern) {
//            if (memo[i][j] != null) {
//                return memo[i][j] == Result.TRUE;
//            }
//            boolean ans;
//            if (j == pattern.length()){
//                ans = i == text.length();
//            } else{
//                boolean first_match = (i < text.length() &&
//                                       (pattern.charAt(j) == text.charAt(i) ||
//                                        pattern.charAt(j) == '.'));
//
//                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
//                    ans = (dp(i, j+2, text, pattern) ||
//                           first_match && dp(i+1, j, text, pattern));
//                } else {
//                    ans = first_match && dp(i+1, j+1, text, pattern);
//                }
//            }
//            memo[i][j] = ans ? Result.TRUE : Result.FALSE;
//            return ans;
//        }
    
    
    
//    public boolean isMatch(String s, String p) {
//		if(p.isEmpty()) return s.isEmpty();
//    boolean firstMatch=(!s.isEmpty()&&s.charAt(0)==p.charAt(0)||p.charAt(0)=='.');
//    
//    	if(p.length()>=2&&p.charAt(1)=='*') {
//    		return isMatch(s, p.substring(2))||(firstMatch&&isMatch(s.substring(1), p));
//    	}else {
//    		return firstMatch&&isMatch(s.substring(1), p.substring(1));
//    	}
    
    
    public int minCostClimbingStairs(int[] cost) {
		int l=cost.length;
		if(l==0) return 0;
		else if(l==1) return cost[0];
		else {
			int[] dp=new int[cost.length];
			dp[0]=cost[0];
			dp[1]=cost[1];
			for(int i=2;i<l;i++) {
				dp[i]=Math.min(dp[i-1], dp[i-2])+cost[i];
			}
			return Math.min(dp[l-1], dp[l-2]);
		}
		
}

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
    		//这个题是Dijkstra在java中的标准实现，他用优先队列代替了C语言中每次都要搜索dist[]数组的操作
    		//用hashmap来实现之前的dist数组
    		int[][] graph=new int[n][n];
    		for(int[] flight:flights) {
    			graph[flight[0]][flight[1]]=flight[2];
    		}
    		Map<Integer, Integer> best=new HashMap<Integer, Integer>();
    		
    		//优先队列按距离起点距离从小到大排列
    		PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[0]-b[0]);
    		pq.offer(new int[] {0,0,src});
    		while(!pq.isEmpty()) {
    			int[] cur=pq.poll();
    			int cost=cur[0],k=cur[1],place=cur[2];
    			//通过k不仅判断伦次，而且如果有重复经过同一个点，会被处理成两个不同的情况（并没有说是单连通图）
    			if(k-1>K||cost>best.getOrDefault(k*1000+place, Integer.MAX_VALUE)){
    				continue;
    			}
    			if(place==dst) return cost;
    			for(int i=0;i<n;i++) {
    				//省去了初始化矩阵元素为Integer.max_value的活
    				if(graph[place][i]>0) {
    					int newCost=cost+graph[place][i];
    					if(newCost<best.getOrDefault(k*1000+i,Integer.MAX_VALUE)) {
    						pq.offer(new int[] {newCost,k+1,i});
    						best.put((k+1)*1000+i,newCost);
    					}
    				}
    				
    			}
    		}
        return -1;
    }
    

	public int uniquePaths(int m, int n) {
        int[][] step=new int[m][n];
        int i,j;
        for(i=0;i<m;i++)
        	step[i][0]=1;
        for(j=0;j<n;j++)
        step[0][j]=1;
        for(i=1;i<m;i++)
        {
        		for(j=1;j<n;j++)
        		{
        			step[i][j]=step[i-1][j]+step[i][j-1];
        		}
        }
        return step[m-1][n-1];
    }
	
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int row=obstacleGrid.length,col=obstacleGrid[0].length;
        int[][] dp=new int[row][col];
        for(int i=0;i<row;i++) {
        		if(obstacleGrid[i][0]==0) {
        			if(i==0||obstacleGrid[i-1][0]==0) dp[i][0]=1;
        			else {
        				obstacleGrid[i][0]=1;
        			}
        		}
        }
       
        for(int i=0;i<col;i++) {
    		if(obstacleGrid[0][i]==0) {
    			if(i==0||obstacleGrid[0][i-1]==0) dp[0][i]=1;
    			else {
    				obstacleGrid[0][i]=1;
    			}
    		}
    }
        for(int i=1;i<row;i++)
        	for(int j=1;j<col;j++) {
        		if(obstacleGrid[i][j]==0) {
        			
        			int n1=(obstacleGrid[i-1][j]==0)?dp[i-1][j]:0;
        			int n2=(obstacleGrid[i][j-1]==0)?dp[i][j-1]:0;
        			if(n1+n2==0) obstacleGrid[i][j]=1;
        			else dp[i][j]=n1+n2;
        		}
        	}
        return dp[row-1][col-1];
        
    }
    
	public int numTrees(int n) {
		if(n==0) return 0;
		else if(n==1) return 1;
		else if(n==2) return 2;
        int[] dp=new int[n+1];
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<=n;i++) {
        		for(int j=1;j<=i;j++) {
        			dp[i]+=dp[j-1]*dp[i-j];
        		}
        }
        return dp[n];
    }
    
	public int coinChange(int[] coins, int amount) {
		if(coins.length<=0) return -1;
        int[] dp=new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0]=0;
        for(int i=1;i<dp.length;i++) {
        		for(int j=0;j<coins.length;j++) {
        			if(coins[j]<=i) {
        				if(dp[i-coins[j]]!=Integer.MAX_VALUE)
        				dp[i]=Math.min(dp[i], dp[i-coins[j]]+1);
        			}
        		}
        }
        return dp[amount]==Integer.MAX_VALUE?-1:dp[amount];
        		
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DP dp=new DP();
		System.out.println(dp.coinChange(new int[] {2}, 3));
	}

}
