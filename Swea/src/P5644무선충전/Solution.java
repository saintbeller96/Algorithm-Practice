package P5644무선충전;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int[][] movements;
	static ArrayList<Integer>[][] map;
	static int[] dr = {0, -1, 0, 1, 0};
	static int[] dc = {0, 0, 1, 0, -1};
	static int[] P;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			
			map = new ArrayList[11][11];
			int M = Integer.parseInt(stk.nextToken());
			int A = Integer.parseInt(stk.nextToken());
			P = new int[A+1];
			movements = new int[2][M];
			
			for(int k = 0; k<2; k++) {
				stk = new StringTokenizer(br.readLine());
				for(int i = 0; i<M; i++) {
					movements[k][i] = Integer.parseInt(stk.nextToken());
				}
			}
			
			for(int a = 1; a<=A; a++) {
				stk = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(stk.nextToken());
				int c = Integer.parseInt(stk.nextToken());
				int C = Integer.parseInt(stk.nextToken());
				int p = Integer.parseInt(stk.nextToken());
				P[a] = p;
				bfs(r, c, a, C);
			}
			System.out.println("#" + t + " " + move(M));
		}
	}
	
	static void bfs(int r, int c, int n, int a) {
		boolean[][] visited = new boolean[11][11];
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {c, r, 0});
		visited[c][r] = true;
		while(!que.isEmpty()) {
			int[] p = que.poll();
			if(map[p[1]][p[0]] == null) {
				map[p[1]][p[0]]= new ArrayList<>();
			}
			map[p[1]][p[0]].add(n);
			if(p[2] == a) continue;
			for(int d = 1; d<=4; d++) {
				int nc = p[0] + dc[d];
				int nr = p[1] + dr[d];
				if(nr>=1 && nr<=10 && nc>=1 && nc<=10 && !visited[nc][nr]) {
					visited[nc][nr] = true;
					que.offer(new int[] {nc, nr, p[2]+1});
				}
			}
		}
	}
	
	static int move(int M) {
		int answer = 0;
		int ar = 1, ac = 1;
		int br = 10, bc = 10;
		
		answer += getMaxP(ac, ar, bc, br);
		
		for(int m = 0; m<M; m++) {
			int da = movements[0][m];
			int db = movements[1][m];
			ar += dr[da];
			ac += dc[da];
			br += dr[db];
			bc += dc[db];
			
			answer += getMaxP(ac, ar, bc, br);
		}
		return answer;
	}
	
	static int getMaxP(int ac, int ar, int bc, int br) {
		if(map[ac][ar] != null && map[bc][br] != null) {
			boolean flag = false;
			for(int a : map[ac][ar]) {
				for(int b : map[bc][br]) {
					if(a == b) flag = true;
				}
			}

			//둘이 다른 공간에 있는 경우
			if(!flag) {
				int maxA = 0;
				for(int n : map[ac][ar]) maxA = Math.max(maxA, P[n]);
				
				int maxB = 0;
				for(int n : map[bc][br]) maxB = Math.max(maxB, P[n]);
				return maxA + maxB;
			}
			//둘이 같은 공간에 있는 경우
			else {
				int max1 = 0, max2 = 0;
				for(int a : map[ac][ar]) {
					for(int b : map[bc][br]) {
						if(a == b) max1 = Math.max(max1, P[a]);
						else max2 = Math.max(max2, P[a] + P[b]);
					}
				}
				return Math.max(max1, max2);
			}
		}else if(map[ac][ar] != null) {
			int max = 0;
			for(int n : map[ac][ar]) max = Math.max(max, P[n]);
			return max;
		}else if(map[bc][br] != null) {
			int max = 0;
			for(int n : map[bc][br]) max = Math.max(max, P[n]);
			return max;
		}
		return 0;
	}
	
	static void print() {
		for(int i = 1; i<=10; i++) {
			for(int j = 1; j<=10; j++) {
				System.out.print(map[j][i] + " ");
			}
			System.out.println();
		}
	}
}
