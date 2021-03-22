package P19537전투시뮬레이션;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, H, W, M, K;
	static int[][] map, points;
	static int[] cost;
	static class Unit{
		int m, t, r, c;
		public Unit(int m, int t, int r, int c) {
			this.m = m;
			this.t = t;
			this.r = r;
			this.c = c;
		}
	}
	static Unit[] units;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0 ,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		H = Integer.parseInt(stk.nextToken());
		W = Integer.parseInt(stk.nextToken());
		
		map = new int[H][W];
		points = new int[H][W];
		cost = new int[N+1];
		for(int i = 0; i<H; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 0; j<W; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		stk = new StringTokenizer(br.readLine());
		for(int i = 1; i<=N; i++) {
			cost[i] = Integer.parseInt(stk.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		units = new Unit[M+1];
		for(int i = 1; i<=M; i++) {
			stk = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(stk.nextToken());
			int t = Integer.parseInt(stk.nextToken());
			int r = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			
			Unit unit = new Unit(m, t, r, c);
			units[i] = unit;
			points[unit.r][unit.c] = i;
		}
		
		K = Integer.parseInt(br.readLine());
		int[][] movements = new int[K][3];
		for(int i = 0; i<K; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int k = 0; k<3; k++) {
				movements[i][k] = Integer.parseInt(stk.nextToken());
			}
		}
		for(int i = 0; i<K; i++) {
			move(movements[i][0], movements[i][1], movements[i][2]);
		}
		
		for(Unit u : units) {
			if(u == null) continue;
			System.out.println(u.r + " " + u.c);
		}
		
	}
	static void move(int u, int a, int b) {
		if(points[a][b] != 0) return;
		if(cost[map[a][b]] == -1) return;
		Unit unit = units[u];
		boolean[][] visited = new boolean[H][W];
		int r = unit.r;
		int c = unit.c;

		visited[r][c] = true;
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {r, c, unit.m});
		
		boolean flag = false;
		while(!que.isEmpty()) {
			int[] p = que.poll();
			if(a == p[0] && b == p[1]) {
				flag = true;
				break;
			}
			for(int d = 0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr>=0&&nr<H&&nc>=0&&nc<W&&!visited[nr][nc]){
					if(!search(unit, nr, nc) && cost[map[nr][nc]] != -1 && cost[map[nr][nc]] <= p[2]){
						que.offer(new int[] {nr, nc, p[2]-cost[map[nr][nc]]});
						visited[nr][nc] = true;
					}
				}
			}
		}
		if(flag) {
			points[unit.r][unit.c] = 0;
			unit.r = a;
			unit.c = b;
			points[unit.r][unit.c] = u;
		}
	}
	
	static boolean search(Unit unit, int r, int c) {
		if(unit.r == r && unit.c == c) return false;
		for(int d = 0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr>=0&&nr<H&&nc>=0&&nc<W) {
				if(points[nr][nc] != 0) {
					Unit encountUnit = units[points[nr][nc]];
					if(encountUnit.t != unit.t) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
