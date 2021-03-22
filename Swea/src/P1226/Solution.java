package P1226;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};
    private static int[][] map = new int[16][16];
    private static boolean[][] visited;
    private static int T, N;
    private static int Answer = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t = 1; t<= 10 ; t++) {
			visited = new boolean[16][16];
			Answer = 0;
			T = Integer.parseInt(br.readLine());
			for (int i = 0; i < 16; i++) {
				String str = br.readLine();
				for (int j = 0; j < 16; j++) {
					map[i][j] = str.charAt(j) - '0';
				}
			}
			Answer = BFS(1, 1);
			
			System.out.println("#" + t +" " + Answer);
		}

	}

	private static void DFS(int r, int c) {
		if (map[r][c] == 3) {
			Answer = 1;
			return;
		}
		visited[r][c] = true;
		for (int d = 0; d < 4; d++) {
			int i = r + dr[d];
			int j = c + dc[d];
			if (i >= 0 && i < 16 && j >= 0 && j < 16 && map[i][j] != 1 && !visited[i][j]) {
				DFS(i, j);
			}
		}
	}
	
	private static int BFS(int r, int c) {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {r, c});		
		while(!que.isEmpty()) {
			int[] p = que.poll();
			visited[p[0]][p[1]] = true;
			for (int d = 0; d < 4; d++) {
				int i = p[0] + dr[d];
				int j = p[1] + dc[d];
				if (i >= 0 && i < 16 && j >= 0 && j < 16 && map[i][j] != 1 && !visited[i][j]) {
					if(map[i][j] == 3) {
						return 1;
					}
					que.offer(new int[] {i, j});
				}
			}
		}
		return 0;
	}

}
