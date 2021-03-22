package P1824혁진이의프로그램검증;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int T, R, C;
	static char[][] map;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			stk = new StringTokenizer(br.readLine());
			R = Integer.parseInt(stk.nextToken());
			C= Integer.parseInt(stk.nextToken());
			
			map = new char[R][C];
			for(int i = 0; i<R; i++) {
				map[i] = br.readLine().toCharArray();
			}
			System.out.println("#" + tc + " " + bfs());
		}
	}
	
	static String bfs() {
		Queue<int[]> que = new LinkedList<>();
		boolean[][][][] state = new boolean[R][C][4][16];
		que.offer(new int[] {0, 0, 0, 0}); // r, c, 이동방향, 현재 메모리
		while(!que.isEmpty()) {
			int[] p = que.poll();
			int r = p[0], c= p[1];

			char cur = map[r][c];

			if(cur != '?') {
				int nr = -1, nc = -1;
				int memory = p[3], dir = p[2];
				
				if(cur >= '0' && cur <='9') {
					nr = (R + r + dr[p[2]])%R;
					nc = (C+c + dc[p[2]])%C;
					memory = cur-'0';
				}else if(cur == '-' || cur =='+') {
					nr = (R + r + dr[p[2]])%R;
					nc = (C+c + dc[p[2]])%C;
					int a = (cur=='-')?-1:1;
					memory = (16 + memory +a)%16;
				}else if(cur == '<' || cur == '>' ||cur=='v' || cur =='^') {
					dir = nextDir(cur);
					nr = (R + r + dr[dir])%R;
					nc = (C+c + dc[dir])%C;
				}else if(cur == '_') {
					dir = 2;
					if(p[3] == 0) dir = 0;
					nr = (R + r + dr[dir])%R;
					nc = (C+c + dc[dir])%C;
				}else if(cur == '|') {
					dir = 3;
					if(p[3] == 0) dir = 1;
					nr = (R + r + dr[dir])%R;
					nc = (C+c + dc[dir])%C;
				}else if(cur == '.') {
					nr = (R + r + dr[p[2]])%R;
					nc = (C+c + dc[p[2]])%C;
				}else if(cur == '@') {
					return "YES";
				}
				if(!state[nr][nc][dir][memory]) {
					state[nr][nc][dir][memory] = true;
					que.offer(new int[] {nr, nc, dir, memory});
				}
			}else{
				for(int d = 0; d<4; d++) {
					int nr = (R + r + dr[d])%R;
					int nc = (C+c + dc[d])%C;
					if(!state[nr][nc][d][p[3]]) {
						que.offer(new int[] {nr, nc, d, p[3]});
						state[nr][nc][d][p[3]] = true;
					}
				}
			}
		}	
		return "NO";
	}
	
	static int nextDir(char c) {
		if(c == '>') return 0;
		else if(c == 'v') return 1;
		else if(c == '<') return 2;
		else return 3;
	}
}
