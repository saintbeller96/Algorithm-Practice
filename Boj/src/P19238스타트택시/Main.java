package P19238스타트택시;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int tr, tc;
	
	static class Fare{
		int sr, sc, destr, destc;
		int cost;
		public Fare(int sr, int sc, int destr, int destc) {
			this.sr = sr;
			this.sc = sc;
			this.destr = destr;
			this.destc = destc;
			this.cost = 0;
		}
	}
	static ArrayList<Fare> fares;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		int fuel = Integer.parseInt(stk.nextToken());
		
		map = new int[N+1][N+1];
		for(int i = 1; i<=N; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 1; j<=N; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		stk = new StringTokenizer(br.readLine());
		int sr = Integer.parseInt(stk.nextToken());
		int sc = Integer.parseInt(stk.nextToken());
		
		fares = new ArrayList<Fare>();
		for(int i = 0; i<M; i++) {
			stk = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			int dr = Integer.parseInt(stk.nextToken());
			int dc = Integer.parseInt(stk.nextToken());
			fares.add(new Fare(r, c, dr, dc));
		}

		Fare fare = new Fare(0, 0, sr, sc);
		for(int i = 0; i<M; i++) {
			//태울 승객을 찾음
			fare = findNextFare(fare.destr, fare.destc, fuel);
			if(fare == null) {
				//승객을 태울 수 없다면 -1 출력
				System.out.println(-1);
				return;
			}
			//승객을 태우기 위해 이동한 거리만큼 연료 차감
			fuel = fuel - fare.cost;
			
			//승객을 목적지까지 보냄
			int l = getPathLength(fare);
			if(l == -1 || fuel - l < 0) {
				System.out.println(-1);
				return;
			}
			fuel = fuel + l;
		}
		System.out.println(fuel);
		
	}
	static Fare findNextFare(int sr, int sc, int curFuel) {
		boolean[][] visited = new boolean[N+1][N+1];
		Queue<int[]> que = new LinkedList<>();
		PriorityQueue<Fare> pque = new PriorityQueue<Fare>(new Comparator<Fare>() {
			@Override
			public int compare(Fare f1, Fare f2) {
				if(f1.sr < f2.sr) return -1;
				else if(f1.sr > f2.sr) return 1;
				else {
					if(f1.sc < f2.sc) return -1;
					else if(f1.sc > f2.sc) return 1;
					else return 0;
				}
			}
		});
		
		que.offer(new int[] {sr, sc, 0});
		visited[sr][sc] = true;
		while(!que.isEmpty()) {
			int l = que.size();
			for(int i = 0; i<l; i++) {
				int[] p = que.poll();
				if(p[2] > curFuel) {
					return null;
				}
				for(int j = 0; j<fares.size(); j++) {
					Fare f = fares.get(j);
					if(f.sr == p[0] && f.sc == p[1]) {
						f.cost = p[2];
						pque.offer(f);
						//System.out.println(f.sr + " " + f.sc + " " + p[2]);
					}
				}
				for(int d = 0; d<4; d++) {
					int r = p[0] + dr[d];
					int c = p[1] + dc[d];
					if(r >=1 && r<=N && c>=1 && c<= N && map[r][c] == 0) {
						if(!visited[r][c]) {
							que.offer(new int[] {r, c, p[2]+1});
							visited[r][c] = true;
						}
					}
				}
			}
			if(!pque.isEmpty()) {
				Fare f = pque.poll();
				fares.remove(f);
				return f;
			}
		}
		
		return null;
	}
	static int getPathLength(Fare fare) {
		boolean[][] visited = new boolean[N+1][N+1];
		Queue<int[]> que = new LinkedList<>();
		visited[fare.sr][fare.sc] = true;
		que.offer(new int[] {fare.sr, fare.sc, 0});

		while(!que.isEmpty()) {
			int[] p = que.poll();
			if(p[0] == fare.destr && p[1] == fare.destc) {
				return p[2];
			}
			for(int d = 0; d<4; d++) {
				int r = p[0] + dr[d];
				int c = p[1] + dc[d];
				if(r >=1 && r<=N && c>=1 && c<= N && map[r][c] == 0) {
					if(!visited[r][c]) {
						que.offer(new int[] {r, c, p[2]+1});
						visited[r][c] = true;
					}
				}
			}
		}
		return -1;
	}
}
