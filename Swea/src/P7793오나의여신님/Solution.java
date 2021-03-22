package P7793오나의여신님;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, M;
	static int rS, cS;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			stk = new StringTokenizer(br.readLine());

			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());

			Queue<int[]> demonQ = new LinkedList<int[]>();

			char[][] map = new char[N][M];

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = str.charAt(j);
					if (map[i][j] == 'S') {
						rS = i;
						cS = j;
						map[i][j] = '.';
					} else if (map[i][j] == '*') {
						demonQ.offer(new int[] { i, j });
					}
				}
			}
			int answer = BFS(map, demonQ);
			if(answer != 0) {
				System.out.println("#" + t + " " + answer);
			}else {
				System.out.println("#" + t + " " +"GAME OVER");
			}
		}
	}

	static int BFS(char[][] map, Queue<int[]> demonQ) {
		Queue<int[]> jiQ = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N][M];
		jiQ.offer(new int[] { rS, cS, 0 });
		visited[rS][cS] = true;
		while (!jiQ.isEmpty()) {
			int s = demonQ.size();
			for (int i = 0; i < s; i++) {
				int[] demon = demonQ.poll();
				for (int d = 0; d < 4; d++) {
					int r = demon[0] + dr[d];
					int c = demon[1] + dc[d];
					if (r >= 0 && r < N && c >= 0 && c < M && map[r][c] == '.') {
						map[r][c] = '*';
						demonQ.offer(new int[] {r,c});
					}
				}
			}
			int j = jiQ.size();
			for (int i = 0; i < j; i++) {
				int[] p = jiQ.poll();
				for (int d = 0; d < 4; d++) {
					int r = p[0] + dr[d];
					int c = p[1] + dc[d];
					if (r >= 0 && r < N && c >= 0 && c < M) {
						if(!visited[r][c]) {
							if (map[r][c] == 'D') {
								return p[2] + 1;
							}
							if (map[r][c] == '.') {
								visited[r][c] = true;
								jiQ.offer(new int[] { r, c, p[2] + 1 });
							}
						}
					}
				}
			}
		}
		return 0;
	}

}
