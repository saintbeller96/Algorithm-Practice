package P8382방향전환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int T;
	static int x1, y1, x2, y2;
	static int[] dd= {-1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			stk = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(stk.nextToken()) + 100;
			y1 = Integer.parseInt(stk.nextToken()) + 100;
			x2 = Integer.parseInt(stk.nextToken()) + 100;
			y2 = Integer.parseInt(stk.nextToken()) + 100;
			
			System.out.println("#" + tc +" "+ bfs());
		}
	}
	
	static int bfs() {
		boolean[][][] visited = new boolean[201][201][2];
		Queue<int[]> que = new LinkedList<>();
		//0 가로 이동 1 세로 이동
		que.offer(new int[] {x1, y1, 0, 0});
		que.offer(new int[] {x1, y1, 1, 0});
		visited[x1][y1][0] = true;
		visited[x1][y1][1] = true;
		
		while(!que.isEmpty()) {
			int[] p = que.poll();
			int r = p[0], c = p[1];
			if(r == x2 && c == y2) {
				return p[3];
			}
			
			if(p[2] == 0) {//이전 이동이 가로인 경우
				for(int d = 0; d<2; d++) {
					int nc = c + dd[d];
					if(nc>=0 && nc<=200 && !visited[r][nc][1]) {
						que.offer(new int[] {r, nc, 1, p[3]+1});
						visited[r][nc][1] = true;
					}
				}
			}else {//이전 이동이 세로인 경우
				for(int d = 0; d<2; d++) {
					int nr = r + dd[d];
					if(nr>=0 && nr<=200 && !visited[nr][c][0]) {
						que.offer(new int[] {nr, c, 0, p[3]+1});
						visited[nr][c][0] = true;
					}
				}
			}
		}
		
		return -1;
	}
}
