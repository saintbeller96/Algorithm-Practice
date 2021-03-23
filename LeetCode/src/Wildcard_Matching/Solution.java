package Wildcard_Matching;

import java.util.*;

public class Solution {
	public boolean isMatch(String s, String p) {
		
		int[][] memo = new int[s.length()][p.length()];
		for(int i = 0; i<s.length(); i++) {
			for(int j = 0; j<p.length(); j++) {
				memo[i][j] = -1;
			}
		}
		
		return dp(memo, s, p, 0, 0)>0;
    }
	
	int dp(int[][] memo, String s, String p, int i, int j) {
		if(memo[i][j] != -1) return memo[i][j];
		
		if(j==p.length()) {
			if(i == s.length()) return 1;
			else return 0;
		}
		
		int ret = 0;
		//매칭
		if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
			ret = dp(memo, s, p, i+1, j+1);
		}
		//와일드카드 *
		else if(p.charAt(j) == '*') {
			for(int k = i; k<s.length(); k++) {
				ret += dp(memo, s, p, k, j+1);
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		String s = "";
		String p = "****";
		System.out.println(sol.isMatch(s, p));
	}
}
