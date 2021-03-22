package P2666벽장문의이동;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] doors;
	static int M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int o1 = Integer.parseInt(stk.nextToken());
		int o2 = Integer.parseInt(stk.nextToken());
		
		M = Integer.parseInt(br.readLine());
		doors = new int[M];
		for(int i = 0; i<M; i++) {
			doors[i] = Integer.parseInt(br.readLine());
		}
		
		int[][][] dp = new int[N+1][N+1][M];
		for(int i = 0; i<=N; i++) {
			for(int j = 0; j<=N; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		
		System.out.println(moveDoors(dp, o1, o2, 0));

	}
	
	static int moveDoors(int[][][] dp, int d1, int d2, int idx) {
		if(idx >= M) return 0;
		if(dp[d1][d2][idx] != -1) return dp[d1][d2][idx];
		int curDoor = doors[idx];
		
		//[열린 문1][열린 문2][현재 열어야 하는 문의 인덱스]
		//문의 이동 횟수 = 열어야 하는문과 열린문들과이 차이
		//고로 현재까지 이동한 문의 개수  + 현재 문의 이동 갯수가 정답
		return dp[d1][d2][idx] = Math.min(Math.abs(d1 - curDoor) + moveDoors(dp, d2, curDoor, idx+1),
									      Math.abs(d2 - curDoor) + moveDoors(dp, d1, curDoor, idx+1));
	}
}
