package Most_Profit_Assigning_Work;

import java.util.*;

public class Solution {
	
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int answer = 0;
        int N = profit.length;
        int[][] dp = new int[N][2];
        for(int i = 0; i<N; i++) {
        	dp[i][0] = difficulty[i];
        	dp[i][1] = profit[i];
        }
        Arrays.sort(dp, (a, b)->Integer.compare(a[0], b[0]));
        Arrays.sort(worker);
        
        int idx = 0;
        int best = 0;
        for(int w : worker) {
        	while(idx < N && dp[idx][0] <= w) {
        		best = Math.max(best, dp[idx++][1]);
        	}
        	answer += best;     	
        }
        return answer;
    }

	public static void main(String[] args) {
		Solution s = new Solution();
		int[] difficulty = {2,4,6,8,10};
		int[] profit = {10,20,30,40,50};
		int[] worker = {4,5,6,7};
		System.out.println(s.maxProfitAssignment(difficulty, profit, worker));
	}

}
