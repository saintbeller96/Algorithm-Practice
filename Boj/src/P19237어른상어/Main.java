package P19237어른상어;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, k;
	static int[][] map;
	static int[] dr = {0,-1,1, 0,0};
	static int[] dc = {0, 0,0,-1,1};
	static class Shark{
		int num, r, c, d;
		//상어의 현재 방향에 따른 이동 방향의 우선순위 저장
		int[][] priorityDir;
		public Shark(int num, int r, int c) {
			this.num = num;
			this.r = r;
			this.c = c;
			this.d = -1;
			priorityDir = new int[5][5];
			
		}		
	}
	//냄새 정보를 저장하는 클래스
	static class Scent{
		int num, time;
		public Scent(int num, int time) {
			this.num = num;
			this.time = time;
		}
	}
	static Scent[][] scent;
	static Shark[] sharks;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		k = Integer.parseInt(stk.nextToken());
		map = new int[N][N];
		scent = new Scent[N][N];
		sharks = new Shark[M+1];
		for(int i = 0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				int s = Integer.parseInt(stk.nextToken());
				if(s != 0) {
					sharks[s] = new Shark(s, i, j);
					scent[i][j] = new Scent(s, k);
				}else {
					scent[i][j] = new Scent(0, 0);
				}
				map[i][j] = s;
				
			}
		}
		//상어의 초기 방향 입력
		stk = new StringTokenizer(br.readLine());
		for(int i = 1; i<=M; i++) {
			sharks[i].d = Integer.parseInt(stk.nextToken());
		}
		//상어마다 이동 방향 우선순위 입력
		for(int i = 1; i<=M; i++) {
			for(int j = 1; j<=4; j++) {
				stk = new StringTokenizer(br.readLine());
				for(int d = 1; d<=4; d++) {
					sharks[i].priorityDir[j][d] = Integer.parseInt(stk.nextToken());
				}
			}
		}
		//시뮬레이션 진행
		for(int i = 1; i<=1000; i++) {
			int a = simulation();
			if(a == 1) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(-1);
		
	}
	//살아있는 상어의 수 반환
	static int simulation() {
		int cnt = 0;
		//이동 후 겹치는 상어를 찾기 위해 이동 후의 상어 상태를 큐에 넣음
		Queue<Shark> que = new LinkedList<Shark>();
		for(int i = 1; i<=M; i++) {
			Shark shark = sharks[i];
			if(shark != null) {
				map[shark.r][shark.c] = 0;
				int dir = findDirection(shark);
				shark.d = dir;
				shark.r += dr[dir];
				shark.c += dc[dir];
				que.offer(shark);
			}
		}
		
		//이동 후 냄새를 줄임
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (scent[i][j].time > 0) {
					scent[i][j].time--;
					if (scent[i][j].time == 0) {
						scent[i][j].num = 0;
					}
				}
			}
		}
		
		while(!que.isEmpty()) {
			Shark shark = que.poll();
			int r = shark.r;
			int c = shark.c;
			if(map[r][c]!=0 && map[r][c] < shark.num ) {
				//현재 위치에 있는 상어보다 더 큰 번호를 가진 상어가 들어오면 들어온 상어를 null로 변경
				sharks[shark.num] = null;
				continue;
			}
			//맵에 마킹
			map[shark.r][shark.c] = shark.num;
			//냄새 뿌리기
			scent[shark.r][shark.c].num = shark.num;
			scent[shark.r][shark.c].time = k;
			cnt++;
		}

		return cnt;
	}
	
	//상어의 현재 방향과 주위 상태에 따라 이동할 방향을 반환
	static int findDirection(Shark shark) {
		int r = shark.r;
		int c = shark.c;
		ArrayList<Integer> noScent = new ArrayList<Integer>();
		ArrayList<Integer> directions = new ArrayList<Integer>();
		for(int d=1; d<=4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr>=0 && nr < N && nc >=0 && nc < N) {
				//아무 냄새도 없는 칸이면 해당 방향 저장
				if(scent[nr][nc].time == 0) {
					noScent.add(d);
				}
				//자신의 냄새와 같은 칸 저장
				else if(scent[nr][nc].num == shark.num) {
					directions.add(d);
				}
			}
		}
		int curDir = shark.d;
		int[] dArr = shark.priorityDir[curDir];
		//먼저 냄새가 없는 칸으로 이동하는 방향을 확인
		if(noScent.size() != 0) {
			for(int d : dArr) {
				if(d == 0) continue;
				//우선순위가 가장 높은 방향을 반환
				//왜나하면 priorityDir는 우선 순위 순서로 저장됨
				for(int goDir : noScent) {
					if(goDir == d) {
						return d;
					}
				}
			}
		}
		//그 다음 자신의 냄새와 같은 칸으로 이
		for(int d : dArr) {
			if(d == 0) continue;
			//우선순위가 가장 높은 방향을 반환
			for(int goDir : directions) {
				if(goDir == d) {
					return d;
				}
			}
		}
		return -1;
	}
	static void print() {
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				System.out.print("(" + scent[i][j].num + ", " + scent[i][j].time+")");
			}
			System.out.println();
		}
		System.out.println();
	}
}
