package P1249;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
	private static int[] dr = {-1, 1,0,0};
	private static int[] dc = {0,0,1,-1};
	private static int T, N, Answer;
	private static int[][] map;
	private static class Point implements Comparable<Point>{
		int i, j, movement;
		public Point(int r, int c, int m) {
			i = r;
			j = c;
			movement = m;
		}
		@Override
		public int compareTo(Point p) {
			if(this.movement < p.movement) {
				return -1;
			}else if(this.movement > p.movement) {
				return 1;
			}else {
				return 0;
			}
		}
	}	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String s =  br.readLine();
				for (int j = 0; j < s.length(); j++) {
					map[i][j] = s.charAt(j) - '0';
				}
			}		
			Answer = BFS(0, 0);					
			System.out.println("#" + t + " " + Answer);
		}
		
	}
	private static int BFS(int i, int j) {
		int[][] dist = new int[N][N];
		PriorityQueue<Point> que = new PriorityQueue<>();	
		dist[i][j] = 0;		
		que.offer(new Point(i, j, 0));	
		for(int r = 0; r<N; r++) {
			for (int c = 0; c < N; c++) {
				if(r == 0 && c == 0) {
					continue;
				}
				dist[r][c] = Integer.MAX_VALUE;
			}
		}
		while(!que.isEmpty()) {
			Point p = que.poll();
			if(dist[p.i][p.j] >= p.movement) {
				for(int d = 0; d < 4; d++) {
					int r = p.i + dr[d];
					int c = p.j + dc[d];
					if(r >= 0 && r<N&&c>=0&&c<N) {
						int m = map[r][c];
						if(dist[r][c] > m + p.movement) {
							dist[r][c] = m + p.movement;
							que.offer(new Point(r, c, dist[r][c]));
						}
					}
				}		
			}
		}
		return dist[N-1][N-1];
	}

}
