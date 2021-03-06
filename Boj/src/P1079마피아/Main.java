package P1079마피아;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, eunjin, answer;
	static int[][] R;
	static int[] guilts;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		N = Integer.parseInt(br.readLine());
		
		R = new int[N][N];
		guilts = new int[N];
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		boolean[] isSurvive = new boolean[N];
		for(int i = 0; i<N; i++) {
			guilts[i] = Integer.parseInt(stk.nextToken());
			isSurvive[i] = true;
		}
		for(int i = 0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				R[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		eunjin = Integer.parseInt(br.readLine());
		game(isSurvive, N, 0);
		
		System.out.println(answer);

	}
	static void game(boolean[] isSurvive, int survive, int day) {	
		if(survive%2 == 0) {
			if(survive == 2) {
				answer  = Math.max(answer, day+1);
				return;
			}
			
			for(int i = 0; i<N; i++) {
				if(i != eunjin && isSurvive[i]) {
					for(int j = 0; j<N; j++) {
						guilts[j] += R[i][j];
					}
					isSurvive[i] = false;
					game(isSurvive, survive-1, day+1);
					isSurvive[i] = true;
					for(int j = 0; j<N; j++) {
						guilts[j] -= R[i][j];
					}
				}
			}
		}else {
			int h = Integer.MIN_VALUE;
			int idx = -1;
			for(int i = 0; i<N; i++) {
				if(isSurvive[i] && guilts[i] > h) {
					h = guilts[i];
					idx = i;
				}
			}
			if(idx == eunjin) {
				answer  = Math.max(answer, day);
				return;
			}
			
			isSurvive[idx] = false;
			game(isSurvive, survive-1, day);
			isSurvive[idx] = true;
		}
	}
}
