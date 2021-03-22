package P9229한빈이와Spotmart;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	private static int T, N, M;
    private static int Answer = 0;
    private static int[] snacks;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer stk = null;		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			stk = new StringTokenizer(br.readLine());			
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			snacks = new int[N];
			stk = new StringTokenizer(br.readLine());	
			for(int i = 0; i< N; i++) {
				snacks[i] = Integer.parseInt(stk.nextToken());
			}
			Answer = 0;
			Arrays.sort(snacks);
			for(int i = 0; i< N; i++) {
				int curM = M-snacks[i];
				int curC = snacks[i];
				if(curM > 0) {
					int idx = lower(i, curM)-1;
					if(idx < 0) continue;
					else if(idx > 0 && snacks[idx-1] == snacks[idx]) idx--;//중복 방지
					curC += snacks[idx];
					if(curC < M) Answer = Answer > curC ? Answer : curC;
					else if(curC == M){
						Answer = curC;
						break;
					}
				}
			}
			if(Answer == 0) Answer = -1;
			sb.append('#').append(t).append(' ').append(Answer).append('\n');
			//System.out.println("#" + t+ " " + Answer);
		}	
		System.out.print(sb);
	}
	private static int lower(int end, int n) {
		int s = 0;
		int e = end;
		int mid = -1;
		while(e > s) {
			mid = (e+s)/2;
			if(snacks[mid] <= n) s = mid +1;
			else e = mid;
		}	
		return e;
	}
}
