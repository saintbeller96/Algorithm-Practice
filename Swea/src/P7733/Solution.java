package P7733;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Solution {
	private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};
    private static int[][] map;
    private static int T, N;
    private static Queue<Point> que = null;
	private static class Point {
		public int i, j;
		public Point(int a, int b) {
			i = a;
			j = b;
		}
	}
    
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = null;
        T = Integer.parseInt(br.readLine());
         
        for(int t = 1; t<= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            
            int max = 0;
            for (int i = 0; i < N; i++) {
                stk = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(stk.nextToken());
                    if(max < map[i][j]) {
                    	max = map[i][j];
                    }
                }
            }
         
            int answer = solve(max);
                     
            System.out.println("#" + t + " " +answer);
        }
        br.close();
	}
	
	private static int solve(int day) {	
		int max = 0;
		for (int d = 0; d <= day; d++) {
			int cnt = 0;
			boolean[][] visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] > d && !visited[i][j]) {
						BFS(visited, i, j, d);			
						cnt++;
					}
				}
			}
			if(cnt > max) {
				max = cnt;
			}
		}		
		return max;
	}
	
	private static void BFS(boolean[][] visited, int i, int j, int day) {
		que = new LinkedList<Point>();
		que.offer(new Point(i, j));
		visited[i][j] = true;
		while(!que.isEmpty()) {
			Point p = que.poll();
			for (int d = 0; d < 4; d++) {
				int r = p.i + dr[d];
				int c = p.j + dc[d];
				if(r >= 0 && r < N && c>=0 && c < N) {
					if(map[r][c] > day && !visited[r][c]) {
						que.offer(new Point(r, c));
						visited[r][c] = true;
					}
				}
			}			
		}
	}

}
