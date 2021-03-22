package P1949등산로조성;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, K;
	static int[][] map;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			K= Integer.parseInt(stk.nextToken());
			
			map = new int[N][N];
			int h = -1;
			for(int i = 0; i<N; i++) {
				stk = new StringTokenizer(br.readLine());
				for(int j = 0; j<N; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
					h = Math.max(h, map[i][j]);
				}
			}
			
			int answer = 1;
			int[][][] dp = new int[N][N][21+K];
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					Arrays.fill(dp[i][j], -1);
				}
			}
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					if(map[i][j] == h) {
						int a = dfs(dp, i, j, map[i][j], false);
						System.out.println(i + " " + j + ": " + a);
						answer = Math.max(answer, a);
					}
				}
			}
			System.out.println(answer);
		}
		
		
	}
	
	static int dfs(int[][][] dp, int r, int c, int h, boolean flag) {
		//if(dp[r][c][h] != -1) return dp[r][c][h];
		int ret = 1;
		for(int d = 0; d<4; d++) {
			int nr = r+ dr[d];
			int nc = c+ dc[d];
			if(nr>=0 && nr<N && nc>=0 && nc<N) {			
				if(map[nr][nc] < h) {
					ret = Math.max(ret, dfs(dp, nr, nc, map[nr][nc], flag)+1);
				}
				if(!flag) {
					for(int k = 1; k<=K; k++) {
						if(map[nr][nr]-k < h) {
							map[nr][nc]-=k;
							ret = Math.max(ret, dfs(dp, nr, nc, map[nr][nc], true)+1);
							map[nr][nc]+=k;
						}
					}
				}
			}
		}
		
		return ret;
	}
}
