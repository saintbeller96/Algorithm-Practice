package P20057마법사상어와토네이도;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;      	//0 1 2 3 상 하 좌 우
	static int[] dr = {1, 1, 1, 0,-1,-1,-1,-2, 2};
	static int[] dc = {1, 0,-1,-2,-1, 0, 1, 0, 0};
	static double[] spread = {0.01, 0.07, 0.1, 0.05, 0.1, 0.07, 0.01, 0.02, 0.02};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		for(int i = 0; i< N; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		System.out.println(simulation(map));
		//print(map);
	}
	static int simulation(int[][] map) {
		int r = N/2;
		int c = N/2;
		int k = -1;
		int l = 1;
		int removedSand = 0;
		
		loop: while(r >= 0 || c >= 0) {
			for(int i = 0; i<l; i++) {
				c += k;
				if(c < 0) break loop;
				//현재 토네이도의 위치 map[r][c]

				//토네이도 한칸씩 이동
				int directionValue = -k;
				int remain = map[r][c];
				
				for(int d = 0; d<9; d++) {
					int nr = r + dr[d]*directionValue;
					int nc = c + dc[d]*directionValue;
					int spreadSand = (int)(spread[d]*map[r][c]);
					remain -= spreadSand;
					if(nr >= 0 && nr<N && nc>=0 && nc<N) {
						map[nr][nc] += spreadSand;
					}else {
						removedSand += spreadSand;
					}
				}
				//a위치에 남은 모래 이동
				if(c + k >=0 && c+k < N) {
					map[r][c+k] += remain;
				}else {
					removedSand += remain;
				}
				map[r][c] = 0;
//				System.out.println(r + " " + c);
//				print(map);
			}
			for(int i = 0; i<l; i++) {
				r -= k;
				//토네이도 한칸씩 이동
				int directionValue = k;
				int remain = map[r][c];
				for(int d = 0; d<9; d++) {
					int nr = r + dc[d]*directionValue;
					int nc = c + dr[d]*directionValue;
					int spreadSand = (int)(spread[d]*map[r][c]);
					remain -= spreadSand;
					if(nr >= 0 && nr<N && nc>=0 && nc<N) {
						map[nr][nc] += spreadSand;
					}else {
						removedSand += spreadSand;
					}
				}
				//a위치에 남은 모래 이동
				if(r - k >=0 && r-k < N) {
					map[r-k][c] += remain;
				}else {
					removedSand += remain;
				}
				map[r][c] = 0;
//				System.out.println(r + " " + c);
//				print(map);
			}
			
			l++;
			k*=-1;
		}
		return removedSand;
	}
	static void print(int[][] map) {
		for(int i = 0; i< N; i++) {
			for(int j = 0; j<N; j++) {
				System.out.printf("%3d", map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
