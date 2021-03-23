package Decode_Ways;

import java.util.*;

public class Solution {
	int answer;
	Set<Integer> decode = new HashSet<>();
	public int numDecodings(String s) {
        answer = 0;
		int[] dp = new int[s.length()];
		Arrays.fill(dp, -1);
		for(int i = 1; i<=26; i++) {
			decode.add(i);
		}
		
		return dp(s, dp, 0);
    }
	
	int dp(String s, int[] dp, int depth) {
		if(depth == s.length()) {
			return 1;
		}
		if(dp[depth] != -1) return dp[depth];
		
		int first = s.charAt(depth) - '0';
		int ret = 0;
		if(decode.contains(first)) {
			ret = dp(s, dp, depth+1);
		}else if(first == 0) {
			return 0;
		}
		int ret2 = 0;
		if(depth+1<s.length()) {
			int second = s.charAt(depth+1) - '0';
			if(first*10 + second >=1 && first*10 + second <= 26) {
				ret2 = dp(s, dp, depth+2);
			}
		}
		
		return dp[depth] = ret + ret2;
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.numDecodings("10"));
	}
}
