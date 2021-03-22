package P20056마법사상어와파이어볼;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[] dr = {-1, -1, 0, 1, 1,  1,  0, -1};
	static int[] dc = { 0,  1, 1, 1, 0, -1, -1, -1};
	static class FireBall{
		int r, c, m, s, d;
		public FireBall(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
		@Override
		public String toString() {
			return "FireBall [r=" + r + ", c=" + c + ", m=" + m + ", s=" + s + ", d=" + d + "]";
		}
		
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		
		Queue<FireBall> fireBalls = new LinkedList<>();
		
		for(int i = 0; i<M; i++) {
			stk = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			int m = Integer.parseInt(stk.nextToken());
			int s = Integer.parseInt(stk.nextToken());
			int d = Integer.parseInt(stk.nextToken());
			fireBalls.offer(new FireBall(r, c, m, s, d));
		}
		for(int k = 0; k<K; k++) {
			simulation(fireBalls);
			//System.out.println(fireBalls);
		}
		
		int answer = 0;
		while(!fireBalls.isEmpty()) {
			FireBall fireBall = fireBalls.poll();
			answer += fireBall.m;
		}
		System.out.println(answer);
	}
	static void simulation(Queue<FireBall> fireBalls) {
		
		ArrayList<FireBall>[][] map = new ArrayList[N+1][N+1];
		
		//큐에서 하나씩 빼내고 파이어볼 이동
		while(!fireBalls.isEmpty()) {
			FireBall fireBall = fireBalls.poll();
			
			int nr = (fireBall.r + fireBall.s*dr[fireBall.d])%N;
			int nc = (fireBall.c + fireBall.s*dc[fireBall.d])%N;
			if(nr <= 0) nr = N + nr;
			if(nc <= 0) nc = N + nc;
			if(map[nr][nc] == null) {
				map[nr][nc] = new ArrayList<FireBall>();
			}
			map[nr][nc].add(new FireBall(nr,nc, fireBall.m,fireBall.s,fireBall.d));
		}
		
		//같은 위치에 있는 파이어볼 분리시켜 다시 넣음
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				ArrayList<FireBall> movedBalls = map[i][j];
				if(movedBalls != null) {
					//파이어볼이 뭉친 경우
					if(movedBalls.size() > 1) {
						int m = 0;
						int s = 0;
						int n = movedBalls.size();
						boolean isOdd = false, isEven = false;
						for(FireBall ball : movedBalls) {
							m += ball.m;
							s += ball.s;
							if(ball.d%2 == 0) isEven = true;
							else isOdd = true;
						}
						m /= 5;
						s /= n;
						if(m > 0) {
							if(isOdd != isEven) {
								for(int k = 0; k<8; k++) {
									if(k%2==0) fireBalls.offer(new FireBall(i, j, m, s, k));
								}
							}else {
								for(int k = 0; k<8; k++) {
									if(k%2!=0) fireBalls.offer(new FireBall(i, j, m, s, k));
								}
							}
						}
					}
					//파이어볼이 하나만 있는 경우
					else {
						fireBalls.offer(movedBalls.get(0));
					}
				}
			}
		}
	}
}
