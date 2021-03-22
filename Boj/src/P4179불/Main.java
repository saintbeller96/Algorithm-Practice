package P4179불;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		char[][] map = new char[R][];
		
		int ji = -1, jj = -1;
		Queue<int[]> fireQueue = new LinkedList<int[]>();
		for(int i = 0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j = 0; j<C; j++) {
				if(map[i][j] == 'J') {
					ji = i;
					jj = j;
					map[i][j] = '.';
				}
				if(map[i][j] == 'F') {
					fireQueue.offer(new int[] {i, j});
				}
			}
		}
		System.out.println(BFS(map, ji, jj, fireQueue));
	}
	
	static String BFS(char[][] map, int ji, int jj, Queue<int[]> fireQueue) {
		Queue<int[]> que = new LinkedList<int[]>();
		boolean[][] visited = new boolean[R][C];
		visited[ji][jj] = true;
		que.offer(new int[] {ji, jj, 1});
		
		while(!que.isEmpty()) {
			int fs = fireQueue.size();
			for(int i = 0; i<fs; i++) {
				int[] fire = fireQueue.poll();
				for(int d = 0; d<4; d++) {
					int r = fire[0] + dr[d];
					int c = fire[1] + dc[d];
					if(r>=0 && r<R && c>=0 && c<C) {
						if(map[r][c] != '#' && map[r][c] != 'F') {
							map[r][c] = 'F';
							fireQueue.offer(new int[] {r, c});
						}
					}
				}
			}
			
			int qs = que.size();
			for(int i = 0; i < qs; i++) {
				int[] p = que.poll();
				
				if(p[0] == 0 || p[0] == R-1 || p[1] == 0 || p[1] == C-1) {
					return ""+p[2];
				}
				for(int d = 0; d<4; d++) {
					int r = p[0] + dr[d];
					int c = p[1] + dc[d];
					if(r>=0 && r<R && c>=0 && c<C) {
						if(!visited[r][c] && map[r][c] == '.') {
							visited[r][c] = true;
							que.offer(new int[] {r, c, p[2]+1});
						}
					}
				}
			}
		}		
		return "IMPOSSIBLE";
	}

}
