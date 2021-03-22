package P13460구슬탈출2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, oR, oC;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static class State{
		char[][] map;
		int[][] balls;
		int prevDir;
		int cnt;
		public State(char[][] map, int[][] balls, int prevDir, int cnt) {
			this.map = map;
			this.balls = balls;
			this.prevDir = prevDir;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		char[][] map = new char[N][M];
		int[][] balls = new int[2][2];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'R') {
					balls[0][0] = i;
					balls[0][1] = j;
				} else if (map[i][j] == 'B') {
					balls[1][0] = i;
					balls[1][1] = j;
				} else if (map[i][j] == 'O') {
					oR = i;
					oC = j;
				}
			}
		}
		System.out.println(BFS(map, balls));
		
	}
	static int BFS(char[][] map, int[][] balls) {
		Queue<State> que = new LinkedList<State>();
		for(int d = 0; d<4; d++) {
			int[][] _ball = new int[2][2];
			_ball[0][0] = balls[0][0];
			_ball[0][1] = balls[0][1];
			_ball[1][0] = balls[1][0];
			_ball[1][1] = balls[1][1];
			que.offer(new State(copy(map), _ball, d, 1));
		}
		while(!que.isEmpty()) {
			State state = que.poll();
			if(state.cnt > 10) {
				return -1;
			}
			int ret = move(state.map, state.balls, state.prevDir);
			if(ret == 1) {
				return state.cnt;
			}
			for(int d = 0; d<4; d++) {
				if(state.prevDir/2 == 0 && d/2 == 0) continue;
				if(state.prevDir/2 == 1 && d/2 == 1) continue;
				int[][] _ball = new int[2][2];
				_ball[0][0] = state.balls[0][0];
				_ball[0][1] = state.balls[0][1];
				_ball[1][0] = state.balls[1][0];
				_ball[1][1] = state.balls[1][1];
				que.offer(new State(copy(state.map), _ball, d, state.cnt+1));
			}
		}
		return -1;
	}
	static char[][] copy(char[][] map){
		char[][] cmap = new char[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				cmap[i][j] = map[i][j];
			}
		}
		return cmap;
	}
	static int move(char[][] map, int[][] balls, int d) {
		int rR = balls[0][0];
		int rC = balls[0][1];
		int bR = balls[1][0];
		int bC = balls[1][1];
		Queue<Character> que = new LinkedList<>();

		while (map[rR + dr[d]][rC + dc[d]] != '#') {
			rR += dr[d];
			rC += dc[d];
			if (map[rR][rC] == 'O') {
				que.offer('R');
				break;
			}
		}
		while (map[bR + dr[d]][bC + dc[d]] != '#') {
			bR += dr[d];
			bC += dc[d];
			if (map[bR][bC] == 'O') {
				que.offer('B');
				break;
			}
		}
		if (que.size() == 1) {
			char ball = que.poll();
			if (ball == 'B')
				return -1;
			else
				return 1;
		} else if (que.size() == 2) {
			return -1;
		}
		map[balls[0][0]][balls[0][1]] = '.';
		map[balls[1][0]][balls[1][1]] = '.';
		
		if (rR == bR && rC == bC) {
			if (d == 0) {
				balls[0][1] = rC;
				balls[1][1] = bC;
				if (balls[0][0] < balls[1][0]) {
					balls[0][0] = rR;
					balls[1][0] = bR+1;
				} else {
					balls[0][0] = rR+1;
					balls[1][0] = bR;
				}
			} else if (d == 1) {
				balls[0][1] = rC;
				balls[1][1] = bC;
				if (balls[0][0] > balls[1][0]) {
					balls[0][0] = rR;
					balls[1][0] = bR-1;
				} else {
					balls[0][0] = rR-1;
					balls[1][0] = bR;
				}
			} else if (d == 2) {
				balls[0][0] = rR;
				balls[1][0] = bR;
				if (balls[0][1] < balls[1][1]) {
					balls[0][1] = rC;
					balls[1][1] = bC+1;
				} else {
					balls[0][1] = rC+1;
					balls[1][1] = bC;
				}
			} else {
				balls[0][0] = rR;
				balls[1][0] = bR;
				if (balls[0][1] > balls[1][1]) {
					balls[0][1] = rC;
					balls[1][1] = bC-1;
				} else {
					balls[0][1] = rC-1;
					balls[1][1] = bC;
				}
			}
		}else {
			balls[0][0] = rR;
			balls[1][0] = bR;
			balls[0][1] = rC;
			balls[1][1] = bC;
		}
		map[balls[0][0]][balls[0][1]] = 'R';
		map[balls[1][0]][balls[1][1]] = 'B';
		return 0;
	}
	static void print(char[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
