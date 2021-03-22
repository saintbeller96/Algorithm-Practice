package Longest_Palindromeic_Substring;

import java.util.*;

public class Solution {
	public String longestPalindrome(String s) {
		int n = s.length();
		boolean[][] mem = new boolean[n][n];
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				mem[i][j] = false;
			}
		}
		int l = 0; int r = 0;
		int length = 1;
		
		for(int i = 0; i<n; i++) {
			mem[i][i] = true;
			if(i+1<n && s.charAt(i) == s.charAt(i+1)) {
				mem[i][i+1] = true;
				l = i;
				r = i+1;
				length = 2;
			}
		}
		
		for(int i = n-1; i>=0; i--) {
			for(int j = i+1; j<n; j++) {
				if(mem[i+1][j-1] && s.charAt(i) == s.charAt(j)) {
					mem[i][j] = true;
					if(j-i+1>length) {
						l = i;
						r = j;
						length = j-i+1;
					}
				}
			}
		}
		
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				if(mem[i][j]) System.out.print("1");
				else System.out.print("0");
			}
			System.out.println();
		}
        
		return s.substring(l, r+1);
    }

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.longestPalindrome("aaaaa"));
	}

}
