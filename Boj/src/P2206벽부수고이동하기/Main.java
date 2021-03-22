package P2206벽부수고이동하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static int[] dr = {0,0,-1,1};
	static int[] dc = {1, -1, 0, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		M = Integer.parseInt(stk.nextToken());
		N = Integer.parseInt(stk.nextToken());
		
		map = new int[M][N];
		
		for(int i = 0; i<M; i++) {
			String str = br.readLine();
			for(int j = 0; j<N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		System.out.println(BFS());
		
	}
	
	static int BFS() {
		Queue<int[]> que = new LinkedList<>();
		boolean[][][] visited = new boolean[2][M][N];
		que.offer(new int[] {0, 0, 1, 1});
		visited[0][0][0] = true;
		visited[1][0][0] = true;
		while(!que.isEmpty()) {
			int[] p = que.poll();
			if(p[0] == M-1 && p[1] == N-1) {
				return p[3];
			}
			
			for(int d = 0; d<4; d++) {
				int r = p[0] + dr[d];
				int c = p[1] + dc[d];
				if(r >= 0 && r < M && c >= 0 && c< N) {
					if(map[r][c] == 1 && p[2] == 1) {
						visited[1][r][c] = true;
						que.offer(new int[] {r, c, 0, p[3]+1});
					}
					else if(map[r][c] == 0 && !visited[p[2]][r][c]) {
						visited[p[2]][r][c] = true;
						que.offer(new int[] {r, c, p[2], p[3] +1});
					}
				}
			}

		}
		return -1;
	}
}
