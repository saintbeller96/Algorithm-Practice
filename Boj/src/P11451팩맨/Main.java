package P11451팩맨;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int T, R, C;
	static char[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static char[] dir = {'N', 'E', 'S', 'W'};
	static class PacMan{
		int r,c;
		public PacMan(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static class PacManPair{
		PacMan p1, p2;
		String track;
		public PacManPair(PacMan p1, PacMan p2,  String track) {
			this.p1 = p1;
			this.p2 = p2;
			this.track = track;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		StringBuilder answer = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			stk = new StringTokenizer(br.readLine());
			R = Integer.parseInt(stk.nextToken());
			C = Integer.parseInt(stk.nextToken());
			map = new char[R][C];
			int[][] positions = new int[2][2];
			int idx = 0;
			for(int i = 0; i<R; i++) {
				map[i] = br.readLine().toCharArray();
				for(int j = 0; j<C; j++) {
					if(map[i][j] == 'P') {
						positions[idx][0] = i;
						positions[idx++][1] = j;
						map[i][j] = '.';
					}
				}
			}
			answer.append(bfs(positions)).append('\n');
		}
		System.out.println(answer);
	}
	static String bfs(int[][] pos) {
		Queue<PacManPair> que = new LinkedList<>();
		boolean[][][][] visited = new boolean[R][C][R][C];
		
		que.offer(new PacManPair(new PacMan(pos[0][0], pos[0][1]), new PacMan(pos[1][0], pos[1][1]), ""));
		visited[pos[0][0]][pos[0][1]][pos[1][0]][pos[1][1]] = true;
		while(!que.isEmpty()) {
			PacManPair p = que.poll();

			if(p.p1.r == p.p2.r && p.p1.c == p.p2.c) {
				//System.out.println(p.track);
				return p.track.length() + " " + p.track;
			}
			
			for(int d = 0; d<4; d++) {
				int nr1 = (R + p.p1.r + dr[d])%R;
				int nc1 = (C + p.p1.c + dc[d])%C;
				int nr2 = (R + p.p2.r + dr[d])%R;
				int nc2 = (C + p.p2.c + dc[d])%C;
				if(map[nr1][nc1] == 'X') {
					nr1 = p.p1.r;
					nc1 = p.p1.c;
				}
				if(map[nr2][nc2] == 'X') {
					nr2 = p.p2.r;
					nc2 = p.p2.c;
				}
				if(map[nr1][nc1] == 'G' || map[nr2][nc2] == 'G') continue;
				if(!visited[nr1][nc1][nr2][nc2]) {
					visited[nr1][nc1][nr2][nc2] = true;
					PacManPair pp = new PacManPair(new PacMan(nr1, nc1), new PacMan(nr2, nc2), p.track + dir[d]);
					que.offer(pp);
				}

			}
			
		}
		return "IMPOSSIBLE";
	}
}
