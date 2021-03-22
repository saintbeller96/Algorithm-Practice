package P16234인구인동;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, L, R;
	static int[][] map;
	static boolean flag;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		L = Integer.parseInt(stk.nextToken());
		R = Integer.parseInt(stk.nextToken());
		
		map = new int[N][N];
		for(int i = 0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 0 ; j<N; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		int answer = 0;
		while(true) {
			int cnt = 0;
			boolean[][] visited = new boolean[N][N];
			for(int i = 0; i<N; i++) {
				for(int j = 0 ; j<N; j++) {
					if(!visited[i][j]) {
						int n = bfs(i, j, visited);
						if(n == 0) {
							cnt++;
						}
					}
				}
			}
			if(cnt == N*N) {
				break;
			}
			answer++;
		}
		System.out.println(answer);
		return;
	}
 	
 	static int bfs(int i, int j, boolean[][] visited) {
 		Queue<int[]> que = new LinkedList<>();
 		que.offer(new int[] {i, j});
 		visited[i][j] = true;
 		int sum = map[i][j];
 		
 		ArrayList<int[]> result = new ArrayList<>();
 		
 		while(!que.isEmpty()) {
 			int[] p = que.poll();
 			result.add(p);
 			int r = p[0], c = p[1];
 			
 			for(int d = 0; d<4; d++) {
 				int nr = r + dr[d];
 				int nc = c+ dc[d];
 				if(nr >=0 && nr<N && nc>=0 && nc<N && !visited[nr][nc]) {
 					int abs = Math.abs(map[r][c] - map[nr][nc]);
 					if(abs >= L && abs <= R) {
 						sum+=map[nr][nc];
 						que.offer(new int[] {nr, nc});
 						visited[nr][nc] = true;
 					}
 				}
 			}
 		}
 		if(result.size() <= 1) {
 			return 0;
 		}
 		
 		int n = sum/result.size();
 		for(int[] p : result) {
 			int r = p[0], c = p[1];
 			map[r][c] = n;
 		}
 		
 		return 1;
 	}
 	
 	static void print() {
 		for(int i = 0; i<N; i++) {
			for(int j = 0 ; j<N; j++) {
				System.out.printf("%4d", map[i][j]);			
			}
			System.out.println();
		}
 		System.out.println();
 	}
}
