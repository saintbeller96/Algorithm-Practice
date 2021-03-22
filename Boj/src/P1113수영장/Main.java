package P1113수영장;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int N, M, Answer;
	private static int[][] map;
	private static int[] dr = { 1, -1, 0, 0 };
	private static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		map = new int[N][M];
		int max = 0;
		int min = 9;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
				max = Math.max(max, map[i][j]);
				min = Math.min(min, map[i][j]);
			}
		}
		if(min == 0) {
			min = 1;
		}
		for (int h = min; h <= max; h++) {
			find(h);
		}
		System.out.println(Answer);

	}

	private static void find(int h) {
		boolean[][] visit = new boolean[N][M];
		for (int i = 1; i <= N - 2; i++) {
			for (int j = 1; j <= M - 2; j++) {
				if (map[i][j] == h && !visit[i][j]) {
					BFS(visit, i, j, h);
				}
			}
		}
	}
	
	private static void BFS(boolean[][] visit,  int i, int j, int h) {
		boolean[][] b = new boolean[N][M];//���� ä������ Ȯ���ϴ� �迭
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {i, j, h});
		b[i][j] = true;
		visit[i][j] = true;
		int minH = howTall(i, j, h);
		if(minH == -1) {
			return;
		}
		while(!que.isEmpty()) {
			int[] p = que.poll();
			for (int d = 0; d < 4; d++) {
				int r = p[0] + dr[d];
				int c = p[1] + dc[d];
				if (r >= 0 && r < N && c >= 0 && c < M ) {
					if (!b[r][c]) {
						if(map[r][c] == h) {
							int hh = howTall(r, c, h);
							if(hh == -1) {
								return;
							}
							b[r][c] = true;
							visit[r][c] = true;
							que.offer(new int[] {r, c, h});
							minH = minH < hh ? minH : hh;
						}					
					}
				}
			}		
		}
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if(b[r][c] == true) {
					int tmp = map[r][c];
					map[r][c] = minH;
					Answer += minH - tmp;				
				}		
			}
		}	
	}

	private static int howTall(int i, int j, int h) {
		int min = 9;
		for (int d = 0; d < 4; d++) {
			int r = i + dr[d];
			int c = j + dc[d];
			if (r >= 0 && r <= N - 1 && c >= 0 && c <= M - 1) {
				if(map[r][c] > h) {
					min = Math.min(min, map[r][c]);
				}else if(map[r][c] == h) {
					continue;
				}else {
					return -1;
				}
			}else {
				return -1;
			}
		}
		return min;
	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(" " + map[i][j]);
			}
			System.out.println();
		}
		System.out.println("-------------------");
	}
}
