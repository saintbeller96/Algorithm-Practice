package P17470배열돌리기5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int[] rotations;
	static int N, M, R;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		R = Integer.parseInt(stk.nextToken());
		map = new int[N][M];
		rotations = new int[R];
		for(int i = 0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		stk = new StringTokenizer(br.readLine());
		int state_rotation = 0;
		int state_neg1 = 0;
		int state_neg2 = 0;
		int state_move = 0;
		for(int i = 0; i<R; i++) {
			rotations[i] = Integer.parseInt(stk.nextToken());
		}
		
		for(int r = 0; r<R; r++) {
			switch(rotations[r]) {
			case 1:
				state_neg1++;
				break;
			case 2:
				state_neg2++;
				break;
			case 3:
				state_rotation = (state_rotation+1)%4;
				break;
			case 4:
				state_rotation--;
				if(state_rotation == -1) state_rotation = 3;
				break;
			case 5:
				state_move = (state_move+1)%4;
				break;
			case 6:
				state_move--;
				if(state_move == -1) state_move = 3;
				break;
			}
		}
		
		//System.out.println(state_neg1 + " "+state_neg2 + " " + state_rotation + " " + state_move);
		
		for(int n = 0; n<state_neg1%2; n++) {
			rotation1();
		}
		for(int n = 0; n<state_neg2%2; n++) {
			rotation2();
		}
		for(int n = 0; n<state_rotation%4; n++) {
			rotation3();
		}
		for(int n = 0; n<state_move%4; n++) {
			rotation5();
		}
		
		print();
	}
	
	static void rotation1() {
		int[] temp = new int[M];
		for(int i = 0; i<N/2; i++) {
			for(int j = 0; j<M; j++) temp[j] = map[i][j];
			for(int j = 0; j<M; j++) map[i][j] = map[N-1-i][j];
			for(int j = 0; j<M; j++) map[N-1-i][j] = temp[j];
		}
	}
	
	static void rotation2() {
		int[] temp = new int[M];
		for(int j = 0; j<M/2; j++) {
			for(int i = 0; i<N; i++) temp[i] = map[i][j];
			for(int i = 0; i<N; i++) map[i][j] = map[i][M-1-j];
			for(int i = 0; i<N; i++) map[i][M-1-j] = temp[i];
		}
	}
	
	static void rotation3() {
		int[][] temp = new int[M][N];
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				temp[j][N-1-i] = map[i][j];
			}
		}
		int t = N;
		N = M;
		M = t;
		map = temp;
	}
	
	static void rotation4() {
		for(int n = 0; n<3; n++) {
			rotation3();
		}
	}
	
	static void rotation5() {
		int[][] temp = new int[N/2][M/2];
		
		//temp <- 4
		for(int i = 0; i<N/2; i++) {
			for(int j = 0; j<M/2; j++) {
				temp[i][j] = map[N/2+i][j];
			}
		}
		//4 <- 3
		for(int i = 0; i<N/2; i++) {
			for(int j = 0; j<M/2; j++) {
				map[N/2+i][j] = map[N/2+i][M/2+j];
			}
		}
		//3 <- 2
		for(int i = 0; i<N/2; i++) {
			for(int j = 0; j<M/2; j++) {
				map[N/2+i][M/2+j] = map[i][M/2+j];
			}
		}
		//2 <- 1
		for(int i = 0; i<N/2; i++) {
			for(int j = 0; j<M/2; j++) {
				map[i][M/2+j] = map[i][j];
			}
		}
		//1 <- temp 
		for(int i = 0; i<N/2; i++) {
			for(int j = 0; j<M/2; j++) {
				map[i][j] = temp[i][j];
			}
		}
	}
	static void rotation6() {
		int[][] temp = new int[N/2][M/2];
		
		//temp <- 4
		for(int i = 0; i<N/2; i++) {
			for(int j = 0; j<M/2; j++) {
				temp[i][j] = map[N/2+i][j];
			}
		}
		//4 <- 1
		for(int i = 0; i<N/2; i++) {
			for(int j = 0; j<M/2; j++) {
				map[N/2+i][j] = map[i][j];
			}
		}
		//1 <- 2
		for(int i = 0; i<N/2; i++) {
			for(int j = 0; j<M/2; j++) {
				map[i][j] = map[i][M/2+j];
			}
		}
		//2 <- 3
		for(int i = 0; i<N/2; i++) {
			for(int j = 0; j<M/2; j++) {
				map[i][M/2+j] = map[N/2+i][M/2+j];
			}
		}
		//3 <- temp 
		for(int i = 0; i<N/2; i++) {
			for(int j = 0; j<M/2; j++) {
				map[N/2+i][M/2+j] = temp[i][j];
			}
		}
	}

	
	static void print() {
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
