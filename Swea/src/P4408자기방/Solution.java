package P4408자기방;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int T, N, M;
	private static int[] room;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;		

		T = Integer.parseInt(br.readLine().trim());
		
		for(int t = 1; t<=T; t++) {		
			N = Integer.parseInt(br.readLine().trim());
			room = new int[201];
			for(int i = 0; i<N; i++) {
				stk = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(stk.nextToken());
				int d = Integer.parseInt(stk.nextToken());
				if(s%2 == 1) s = s/2 +1;
				else s = s/2;
				if(d%2 == 1) d = d/2 +1;
				else d = d/2;
				int source = s < d?s:d;
				int dest = s >= d?s:d;
				
				for(int k = source; k<=dest; k++) {
					room[k]++;
				}				
			}
			int answer = 0;
			for(int i = 1; i<=200; i++) {
				answer = answer>room[i]?answer:room[i];
			}
			System.out.println("#" + t + " " + answer);
		}

	}

}
