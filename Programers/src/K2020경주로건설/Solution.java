package K2020경주로건설;

import java.util.*;

public class Solution {
	private int[] dr = {-1, 0, 1, 0};
	private int[] dc = {0, 1, 0, -1};
	private int N;
	static class State{
		int r, c;
		int prev;
		int cost;
		public State(int r, int c, int prev, int cost) {
			this.r = r;
			this.c = c;
			this.prev = prev;
			this.cost = cost;
		}
	}
	public int solution(int[][] board) {
		N = board.length;
		return dijk(board);
	}
	
	private int dijk(int[][] board) {
		PriorityQueue<State> pque = new PriorityQueue<>((a, b)->Integer.compare(a.cost, b.cost));
		int[][][] dist = new int[N][N][4];
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				for(int k = 0; k<4; k++) {
					dist[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}
		for(int k = 0; k<4; k++) {
			dist[0][0][k] = 0;
		}
		pque.offer(new State(0,0,2,0));
		pque.offer(new State(0,0,1,0));
		
		while(!pque.isEmpty()) {
			State state = pque.poll();
			int r = state.r;
			int c = state.c;
			int p = state.prev;
			if(state.cost > dist[r][c][p]) continue;
			for(int d = 0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr>=0 && nr<N && nc>=0 && nc<N && board[nr][nc] == 0) {
					int cost = 0;
					//직선
					if(d == p) {
						cost += 100;
					}
					//곡선
					else if((d+p)%2==1) {
						cost += 600;
					}
					if(dist[nr][nc][d] > cost + state.cost) {
						dist[nr][nc][d] = cost + state.cost;
						pque.offer(new State(nr, nc, d, cost + state.cost));
					}
				}
			}
		}
		
		int answer = Integer.MAX_VALUE;
		for(int k = 0; k<4; k++) {
			answer = Math.min(answer, dist[N-1][N-1][k]);
		}
		
		return answer;
	}
	
	public static void main(String[] args) {
		Solution s = new Solution();
		
		int[][] board = {{0,0,1,0},{0,0,0,0},{0,1,0,1},{1,0,0,0}};
		int[][] board2 = {
		        {0, 0, 0, 0, 0},
		        {0, 1, 1, 1, 0},
		        {0, 0, 1, 0, 0},
		        {1, 0, 0, 0, 1},
		        {0, 1, 1, 0, 0}
		    };
		System.out.println(s.solution(board));
	}
}
