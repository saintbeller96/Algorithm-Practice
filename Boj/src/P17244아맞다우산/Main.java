package P17244아맞다우산;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int C, R;
	static char[][] map;
	static class Point{
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static List<char[]> orders;
	static int[] dr = {1, -1, 0 ,0};
	static int[] dc = {0, 0, 1, -1};
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		C = Integer.parseInt(stk.nextToken());
		R = Integer.parseInt(stk.nextToken());
		map = new char[R][C];
		Point start = null;
		orders = new ArrayList<>();
		
		char idx = '1';
		for(int i = 0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j = 0; j<C; j++) {
				if(map[i][j] == 'S') {
					start = new Point(i, j);
				}else if(map[i][j] == 'X') {
					map[i][j] = (char)(idx++);
				}
			}
		}
		int n = idx-'1';
		char[] input = new char[n];
		for(int i = 0; i<n; i++) {
			input[i] = (char)('1' + i);
		}
		
		permutation(n,n,0,new char[n], input, 0);
		
		int answer = Integer.MAX_VALUE;
		for(char[] order:orders) {
			Point thisStart = new Point(start.r, start.c);
			int total = 0;
			for(char target : order) {
				total += bfs(target, thisStart);
			}
			total += bfs('E', thisStart);
			answer = Math.min(answer, total);
		}
		
		System.out.println(answer);
		return;
	}
 	static int bfs(char target, Point start) {
 		Queue<int[]> que = new LinkedList<>();
 		boolean[][] visited = new boolean[R][C];
 		
 		que.offer(new int[] {start.r, start.c, 0});
 		visited[start.r][start.c] = true;
 		
 		while(!que.isEmpty()) {
 			int[] p = que.poll();
 			int r = p[0];
 			int c = p[1];
 			if(map[r][c] == target) {
 				start.r = r;
 				start.c = c;
 				return p[2];
 			}
 			for(int d = 0; d<4; d++) {
 				int nr = r + dr[d];
 				int nc = c + dc[d];
 				if(nr>=0 && nr<R && nc >=0 && nc<C && !visited[nr][nc] && map[nr][nc] != '#') {
 					visited[nr][nc] = true;
 					que.offer(new int[] {nr, nc, p[2]+1});
 				}
 			}
 		}
 		return -1;
 	}
 	
 	static void permutation(int n, int r, int depth, char[] output, char[] input, int flag) {
 		if(depth == r) {
 			char[] temp = new char[n];
 			System.arraycopy(output, 0, temp, 0, n);
 			orders.add(temp); 			
 			return;
 		}
 		for(int i = 0; i<n; i++) {
 			if((flag&(1<<i)) != 0) continue;
 			output[depth] = input[i];
 			permutation(n, r, depth+1, output, input, flag|(1<<i));
 		}
 	}
}
