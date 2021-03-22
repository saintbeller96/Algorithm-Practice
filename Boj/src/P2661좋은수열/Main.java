package P2661좋은수열;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static StringBuilder answer;
	static boolean finished;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		answer = new StringBuilder();
		finished = false;
		
		dfs(0);
	}
 	
 	static void dfs(int n) {
 		if(finished) return;
 		if(n == N) {
 			System.out.println(answer);
 			finished = true;
 			return;
 		}
		if(n == 0) {
			for (int a = 1; a <= 3; a++) {
				answer.append(a);
				dfs(n+1);
				answer.deleteCharAt(n);
			}
		}else {
			for (int a = 1; a <= 3; a++) {
				answer.append(a);
				boolean flag = true;
				
				for(int i = 0; i<=(n-1)/2; i++) {
					String r = answer.substring(n-i, n+1);
					String l = answer.substring(n-2*i-1, n-i);
					if(r.equals(l)) {
						flag = false;
						break;
					}
				}
				if(flag) {
					dfs(n+1);
					
				}
				answer.deleteCharAt(n);
			}
			
		}
		
 	}
}
