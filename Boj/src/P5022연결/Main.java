package P5022연결;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] point;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, -1 ,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		point = new int[4][2];
		for(int i = 0; i<4; i++) {
			stk = new StringTokenizer(br.readLine());
			point[i][0] = Integer.parseInt(stk.nextToken());
			point[i][1] = Integer.parseInt(stk.nextToken());
		}
		int answer = 0;
		//A -> B
		boolean[][] connected = new boolean[N+1][M+1];
		int a = connectFirst(connected, 0, 1);
		int b = findShortest(connected, 2, 3);
		answer = (b != -1)?(a+b):Integer.MAX_VALUE;

		//B -> A
		connected = new boolean[N+1][M+1];
		int c = connectFirst(connected, 2, 3);
		int d = findShortest(connected, 0, 1);
		answer = Math.min(answer, (d != -1)?(c+d):Integer.MAX_VALUE);

		if(answer == Integer.MAX_VALUE) {
			System.out.println("IMPOSSIBLE");
		}else {
			System.out.println(answer);
		}
		
	}
	static int connectFirst(boolean[][] connected, int p1, int p2) {
		boolean[][] visited = new boolean[N+1][M+1];
		Queue<int[]> que = new LinkedList<>();
		//경로 저장할 배열
		int[][][] path = new int[N+1][M+1][2];
		
		int pointR = point[p1][0];
		int pointC = point[p1][1];
		int endR = point[p2][0];
		int endC = point[p2][1];
		
		que.add(new int[] {pointR, pointC, 0});
		visited[pointR][pointC] = true;
		
		int len = -1;
		
		while(!que.isEmpty()) {
			int[] p = que.poll();
			if(p[0] == endR && p[1] == endC) {
				len = p[2];
				break;
			}
			for(int d = 0; d<4; d++) {
				int nr = p[0] + dr[d];
				int nc = p[1] + dc[d];
				if(nr >= 0 && nr <= N && nc >=0 && nc <=M && !visited[nr][nc]) {
					//현재 최단 경로에 다름 점들이 위치하는지 판판해야 함
					boolean flag = true;
					for(int i = 0; i<4; i++) {
						if(i != p1 && i!= p2) {
							if(nr == point[i][0] && nc == point[i][1]) {
								flag = false;
								break;
							}
						}
					}
					//현재 경로에 다른 점이 없으면 계속 진행
					if(flag) {
						que.offer(new int[] {nr, nc, p[2]+1});
						visited[nr][nc] = true;
						path[nr][nc][0] = p[0];
						path[nr][nc][1] = p[1];
					}
				}
			}
		}
		//최단 경로 기록
		int r = endR, c = endC;
		while(true) {
			connected[r][c] = true;
			if(r == pointR && c == pointC) break;
			int tr = path[r][c][0];
			int tc = path[r][c][1];
			r = tr; c = tc;
		}
		
		return len;
	}
	
	static int findShortest(boolean[][] connected, int p1, int p2) {
		Queue<int[]> que = new LinkedList<>();
		int pointR = point[p1][0];
		int pointC = point[p1][1];
		int endR = point[p2][0];
		int endC = point[p2][1];
		
		//현재 기록된 최단 경로에 이미 점들이 있는 경우 불가능
		if(connected[pointR][pointC] || connected[endR][endC]) {
			return -1;
		}
		connected[pointR][pointC] = true;
		que.add(new int[] {pointR, pointC, 0});

		while(!que.isEmpty()) {
			int p[] = que.poll();
			if(p[0] == endR && p[1] == endC) {
				return p[2];
			}
			for(int d = 0; d<4; d++) {
				int nr = p[0] + dr[d];
				int nc = p[1] + dc[d];
				if(nr >= 0 && nr <= N && nc >=0 && nc<=M && !connected[nr][nc]) {
					que.offer(new int[] {nr, nc, p[2]+1});
					connected[nr][nc] = true;
				}
			}
		}
		
		
		return -1;
	}
}
