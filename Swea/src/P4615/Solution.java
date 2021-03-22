package P4615;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	private static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };
	private static int T, N, M;
	private static int[][] map;
	private static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			map = new int[N + 1][N + 1];
			map[N / 2 + 1][N / 2 + 1] = 2;
			map[N / 2][N / 2] = 2;
			map[N / 2 + 1][N / 2] = 1;
			map[N / 2][N / 2 + 1] = 1;

			for (int k = 0; k < M; k++) {
				stk = new StringTokenizer(br.readLine());
				int i = Integer.parseInt(stk.nextToken());
				int j = Integer.parseInt(stk.nextToken());
				int stone = Integer.parseInt(stk.nextToken());
				flag = false;
				map[i][j] = stone;
				play(i, j, -1, stone);
			}
			
			int white =0, black = 0;
			for(int i = 1; i<= N; i++) {
				for(int j = 1; j<= N; j++) {
					if(map[i][j] == 1) {
						black++;
					}else if(map[i][j] == 2) {
						white++;
					}
				}
			}
			System.out.println("#" + t +" "+black + " " + white);
		}
	}

	private static void play(int r, int c, int dir, int stone) {
		if(dir != -1) {
			if(r <= 0 || r > N || c <= 0 || c > N || map[r][c] == 0) {
				return;
			}
			if(map[r][c] == stone) {
				flag = true;
				return;
			}else {
				play(r + dr[dir], c + dc[dir], dir, stone);
				if(flag)
					map[r][c] = stone;			
			}			
		}	
		else {
			for(int d = 0; d < 8; d++) {
				int i = r + dr[d];
				int j = c + dc[d];
				play(i, j, d, stone);
				flag = false;
			}		
		}
	}
}
