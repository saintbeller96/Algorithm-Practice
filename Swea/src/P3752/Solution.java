package P3752;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

//DP 문제임
public class Solution {
	private static int T, N, Answer;
	private static boolean[] check = new boolean[10001];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			Answer = 0;
			N = Integer.parseInt(br.readLine());
			
			int[] scores = new int[N];
			Arrays.fill(check, false);
			stk = new StringTokenizer(br.readLine());	
			
			for (int i = 0; i < scores.length; i++) {
				scores[i] = Integer.parseInt(stk.nextToken());
			}
			
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(0);
			check[0] = true;
			for (int i = 0; i < N; i++) {
				for(Integer j : new ArrayList<Integer>(list)) {
					int cur = j + scores[i];
					if(!check[cur]) {
						list.add(cur);
						check[cur] = true;
					}
				}
			}

			System.out.println("#" + t + " " + list.size());
		}
	}
//	private static void recur(int[] scores, HashSet<Integer> hs, int sum, int cnt) {
//		if(cnt == scores.length) {
//			hs.add(sum);
//			return;
//		}
//		if(check[cnt] != -1) {
//			
//		}
//		else {
//			recur(scores, hs, sum, cnt+1);
//			recur(scores, hs, sum + scores[cnt], cnt+1);
//		}	
//	}
}