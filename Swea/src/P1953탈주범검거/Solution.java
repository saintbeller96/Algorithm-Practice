package P1953탈주범검거;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, R, C, L;
	static int[][] map;
	//상우하좌
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	//처음 시작 파이프에 따라 연결할 수 있는 방향 저장
	static int[][] flowOut = {{}, {0, 1, 2, 3}, {0, 2}, {1, 3}, {0, 1}, {1, 2}, {2, 3}, {0, 3}};
	//각 파이프에 들어올 수 있는 방향
	static int[][] flowIn = {{}, {0, 1, 2, 3}, {0, 2}, {1, 3}, {2, 3}, {0, 3}, {0, 1}, {1, 2}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		int T = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= T; t++) {
			stk = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			R = Integer.parseInt(stk.nextToken());
			C = Integer.parseInt(stk.nextToken());
			L = Integer.parseInt(stk.nextToken());
			map = new int[N][M];
			for(int i = 0 ; i<N; i++) {
				stk = new StringTokenizer(br.readLine());
				for(int j = 0; j<M; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			System.out.println("#" + t + " " + bfs(L));
			
			
		}
	}
	static int bfs(int time) {
		int cnt = 1;
		Queue<int[]> que = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		visited[R][C] = true;
		
		//최초 파이프에서 연결될 수 있는 파이프 연결
		int pipe = map[R][C];
		for(int d : flowOut[pipe]) {
			int r = R + dr[d];
			int c = C + dc[d];
			if(r>=0 && r<N && c>=0 && c<M && map[r][c] != 0 && time > 1) {
				if(canFlow(map[r][c], d)) {
					que.offer(new int[] {r, c, d, time-1});
					visited[r][c] = true;
					cnt++;
				}
			}
		}
		while(!que.isEmpty()) {
			int[] p = que.poll();
			int r = p[0], c = p[1];
			int curD = p[2];
			int curTime = p[3];
			pipe = map[r][c];
			if(p[3] <= 1) continue;
			
			ArrayList<Integer> nextD = nextDirection(pipe, curD);
			for(int d : nextD) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr>=0 && nr<N && nc>=0 && nc<M && map[nr][nc] != 0) {
					if(!visited[nr][nc] && canFlow(map[nr][nc], d)) {
						que.offer(new int[] {nr, nc, d, curTime-1});
						visited[nr][nc] = true;
						cnt++;
					}
				}
			}
		}
		
		return cnt;
	}
	
	//파이프와 들어온 방향에 따라 다음에 흐르는 방향 반환
	static ArrayList<Integer> nextDirection(int pipe, int direction) {
		ArrayList<Integer> d = new ArrayList<Integer>();
		switch(pipe) {
		case 2:
		case 3:
			d.add(direction);
			break;
		case 4://ㄴ
			if(direction == 3) d.add(0);
			else if(direction == 2) d.add(1);
			break;
		case 5://[
			if(direction == 0) d.add(1);
			else if(direction == 3) d.add(2);
			break;
		case 6://ㄱ
			if(direction == 0) d.add(3);
			else if(direction == 1) d.add(2);
			break;
		case 7://]
			if(direction == 1) d.add(0);
			else if(direction == 2) d.add(3);
			break;
		case 1:
			for(int k = 0; k<4; k++) {
				if(k == (direction + 2)%4) continue;
				d.add(k);
			}
			break;
		}
		return d;
	}
	//해당 파이프에 해당 방향으로 들어올 수 있는지 확인
	static boolean canFlow(int pipe, int direction) {
		for(int d : flowIn[pipe]) {
			if(d == direction) return true;
		}
		return false;
	}
}
