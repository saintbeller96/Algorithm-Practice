package P22944죽음의비_백트래킹;

import java.io.*;
import java.util.*;

public class Main {
	static int N, H, D, K;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		H = Integer.parseInt(stk.nextToken());
		D = Integer.parseInt(stk.nextToken());
		char[][] map = new char[N][N];
		int sr = -1, sc = -1;
		char k = '0';
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'S'){
					sr = i;
					sc = j;
				}
				if (map[i][j] == 'U') {
					map[i][j] = k++;
					K++;
				}
			}
		}
		System.out.println(bfs(map, sr, sc));
		return;
	}

	private static int bfs(char[][] map, int sr, int sc) {
		boolean[][][] visited = new boolean[N][N][1<<K];
		Queue<State> que = new ArrayDeque<>();
		que.offer(new State(sr, sc, H, 0, 0, 0));
		visited[sr][sc][0] = true;
		while (!que.isEmpty()) {
			State state = que.poll();
			if(map[state.r][state.c] == 'E'){
				return state.time;
			}
			for (int d = 0; d < 4; d++) {
				int nr = state.r + dr[d];
				int nc = state.c + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
					int umbrella = state.umbrella;
					int hp = state.hp;
					int k = state.bit;
					if(Character.isDigit(map[nr][nc])) {
						int temp = map[nr][nc]-'0';
						if ((k & (1 << temp)) == 0) {
							umbrella = D;
						}
						k = k | (1<<temp);
					}
					if (map[nr][nc] != 'E') {
						if (umbrella <= 0) {
							hp--;
						}else{
							umbrella--;
						}
					}
					if (hp > 0 && !visited[nr][nc][k]) {
						visited[nr][nc][k] = true;
						que.offer(new State(nr, nc, hp, umbrella, state.time+1, k));
					}
				}
			}
		}
		return -1;
	}

	private static class State {
		int r,c,hp,umbrella,time,bit;
		public State(int r, int c, int hp, int umbrella, int time, int bit) {
			this.r = r;
			this.c = c;
			this.hp = hp;
			this.umbrella = umbrella;
			this.time = time;
			this.bit = bit;
		}
	}
}
