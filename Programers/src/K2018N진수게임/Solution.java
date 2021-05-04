package K2018N진수게임;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class Solution {
	char[] alpha = new char[16];
	public String solution(int n, int t, int m, int p) {
		for(int i = 10; i<16; i++) {
			alpha[i] = (char)('A'+i-10);
		}
		StringBuilder answer = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<=100000; i++) {
        	sb.append(toN(n, i));
        }
        for(int i = p; i<p+t*m; i+=m) {
        	answer.append(sb.charAt(i-1));
        }
        
        return answer.toString();
    }
	
	private String toN(int N, int num) {
		if(num == 0) return "0";
		StringBuilder sb = new StringBuilder();
		while(num != 0) {
			if(num%N > 9) {
				sb.append(alpha[num%N]);
			}else {
				sb.append(num%N);
			}
			num/=N;
		}
		return sb.reverse().toString();
	}
	
	public static void main(String[] args){
		Solution sol = new Solution();
		int n = 2;
		int t = 4;
		int m = 2;
		int p = 1;
		System.out.println(sol.solution(n, t, m, p));
	}
}
