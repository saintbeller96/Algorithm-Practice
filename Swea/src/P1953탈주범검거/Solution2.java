package P1953탈주범검거;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2 {
	static int N, M, R, C, L;
	static int[][] map;
	static int[][] visited;
	static int[][] type = {{}, {0, 3, 1, 2}, {0,3}, {1,2}, {0, 2}, {2, 3}, {3, 1}, {0, 1}};
	static int[] dr = {-1 ,0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		int T = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= T; t++) {
			stk = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			R = Integer.parseInt(stk.nextToken());
			C = Integer.parseInt(stk.nextToken());
			L = Integer.parseInt(stk.nextToken());
			map = new int[N][M];
			visited = new int[N][M];
			for(int i = 0 ; i<N; i++) {
				stk = new StringTokenizer(br.readLine());
				for(int j = 0; j<M; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
					visited[i][j] = Integer.MAX_VALUE;
				}
			}
			dfs(R, C, 1);
			System.out.println("#" + t + " " + getCount());
		}
	}
	static void dfs(int r, int c, int time) {
		visited[r][c] = time;
		if(time == L) {
			return;
		}
		int nr, nc;
		for(int d : type[map[r][c]]) {
			nr = r + dr[d];
			nc = c + dc[d];
			if(nr>=0 && nr<N && nc>=0 && nc<M && map[nr][nc] != 0 && visited[nr][nc] > time) {
				for(int d2 : type[map[nr][nc]]) {
					if(d2 == 3-d) {
						dfs(nr, nc, time+1);
						break;
					}
				}
			}
		}
	}
	static int getCount() {
		int cnt = 0;
		for(int i = 0 ; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(visited[i][j] != Integer.MAX_VALUE) cnt++;
			}
		}
		return cnt;
	}
}
