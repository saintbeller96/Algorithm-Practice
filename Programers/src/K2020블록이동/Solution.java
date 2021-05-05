package K2020블록이동;

import java.util.*;

public class Solution {
	int N;
	int[] dr = {0, 1, 0, -1};
	int[] dc = {1, 0, -1, 0};
	static class Robot{
		int r1, c1;
		int r2, c2;
		int time;
		public Robot(int r1, int c1, int r2, int c2, int time) {
			this.r1 = r1;
			this.c1 = c1;
			this.r2 = r2;
			this.c2 = c2;
			this.time = time;
		}
	}
	
	public int solution(int[][] board) {
		N = board.length;
        return bfs(board);
    }
	
	private int bfs(int[][] board) {
		boolean[][][][] visited = new boolean[N][N][N][N];
		Queue<Robot> que = new ArrayDeque<>();
		visited[0][0][0][1] = true;
		que.offer(new Robot(0,0,0,1,0));
		while(!que.isEmpty()) {
			Robot robot = que.poll();
			int r1 = robot.r1;
			int c1 = robot.c1;
			int r2 = robot.r2;
			int c2 = robot.c2;
			if((r1 == N-1 && c1==N-1)||(r2 == N-1 && c2==N-1)) {
				return robot.time;
			}
			int[][] map = new int[N][N];
			map[r1][c1] = 1;
			map[r2][c2] = 2;
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			System.out.println();
			for(int d = 0; d<4; d++) {
				int nr1 = r1 + dr[d];
				int nr2 = r2 + dr[d];
				int nc1 = c1 + dc[d];
				int nc2 = c2 + dc[d];
				if(nr1>=0 && nc1>=0 && nr2<N && nc2<N && nr2>=0 && nc2>=0 && nr1<N && nc1<N && 
						board[nr1][nc1] == 0 && board[nr2][nc2] ==0 &&
						!visited[nr1][nc1][nr2][nc2]) {
					visited[nr1][nc1][nr2][nc2] = true;
					que.offer(new Robot(nr1, nc1, nr2, nc2, robot.time+1));
				}
			}
			//회전
			//로봇이 가로일 때
			if(r1 == r2) {
				if(r1>0 && board[r1-1][c1] ==0 && board[r2-1][c2] == 0) {
					if(!visited[r1-1][c1][r2][c2-1]) {
						visited[r1-1][c1][r2][c2-1] = true;
						que.offer(new Robot(r1-1, c1, r2, c2-1, robot.time+1));
					}
					if(!visited[r1-1][c1+1][r2][c2]) {
						visited[r1-1][c1+1][r2][c2] = true;
						que.offer(new Robot(r1-1, c1+1, r2, c2, robot.time+1));
					}
				}
				if(r1<N-1 && board[r1+1][c1] ==0 && board[r2+1][c2] == 0) {
					if(!visited[r1][c1][r2+1][c2-1]) {
						visited[r1][c1][r2+1][c2-1] = true;
						que.offer(new Robot(r1, c1, r2+1, c2-1, robot.time+1));
					}
					if(!visited[r1][c1+1][r2+1][c2]) {
						visited[r1][c1+1][r2+1][c2] = true;
						que.offer(new Robot(r1, c1+1, r2+1, c2, robot.time+1));
					}
				}
			}
			//로봇이 세로일 때
			else if(c1 == c2) {
				if(c1>0 && board[r1][c1-1] ==0 && board[r2][c2-1] == 0) {
					if(!visited[r1][c1-1][r2-1][c2]) {
						visited[r1][c1-1][r2-1][c2] = true;
						que.offer(new Robot(r1, c1-1, r2-1, c2, robot.time+1));
					}
					if(!visited[r1+1][c1-1][r2][c2]) {
						visited[r1+1][c1-1][r2][c2] = true;
						que.offer(new Robot(r1+1, c1-1, r2, c2, robot.time+1));
					}
				}
				if(c1<N-1 && board[r1][c1+1] ==0 && board[r2][c2+1] == 0) {
					if(!visited[r1][c1][r2-1][c2+1]) {
						visited[r1][c1][r2-1][c2+1] = true;
						que.offer(new Robot(r1, c1, r2-1, c2+1, robot.time+1));
					}
					if(!visited[r1+1][c1][r2][c2+1]) {
						visited[r1+1][c1][r2][c2+1] = true;
						que.offer(new Robot(r1+1, c1, r2, c2+1, robot.time+1));
					}
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		int[][] board = {{0, 0, 0, 1, 1},{0, 0, 0, 1, 0},{0, 1, 0, 1, 1},{1, 1, 0, 0, 1},{0, 0, 0, 0, 0}};
		System.out.println(s.solution(board));
	}

}
