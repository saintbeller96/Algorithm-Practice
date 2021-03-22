package P10725빛의왕과거울의미로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, x, y, answer;
	static int sr, sc, er, ec;
	static char[][] maze;
	static boolean[][] b;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1 ,1};
 	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		x = Integer.parseInt(stk.nextToken());
		y = Integer.parseInt(stk.nextToken());
		
		maze = new char[N+2][M+2];
		b = new boolean[N+2][M+2];

		for(int i = 1; i<N+1;i++) {
			String str = br.readLine();
			for(int j = 0; j<M+2; j++) {
				if(j ==0 || j == M+1) {
					maze[i][j] = 0;
				}
				else {
					maze[i][j] = str.charAt(j-1);
				}
			}
		}
		int cnt = 1;
		int dir = -1;
		for(int c = 1; c<=M; c++) {
			cnt++; 
			if(cnt-1 == x) { sr = 0; sc = c; dir=1;}
			if(cnt-1 == y) { er = 0; ec = c;}
		}
		for(int r = 1; r<=N; r++) {
			cnt++;
			if(cnt-1 == x) { sr = r; sc = 0;dir=3;}
			if(cnt-1 == y) { er = r; ec = 0;}
		}
		for(int r = 1; r<=N; r++) {
			cnt++;
			if(cnt-1 == x) { sr = r; sc = M+1; dir = 2;}
			if(cnt-1 == y) { er = r; ec = M+1;}
		}
		for(int c = 1; c<=M; c++) {
			cnt++;
			if(cnt-1 == x) { sr = N+1; sc = c; dir = 0;}
			if(cnt-1 == y) { er = N+1; ec = c;}
		}
		
		sr += dr[dir];
		sc += dc[dir];
		dfs(sr, sc, dir, 0);
		
		System.out.println(answer%10007);	
	}
 	
 	static void dfs(int r, int c, int dir, int cnt) {
 		if(r==0||r==N+1||c==0||c==M+1) {
 			if(r == er && c == ec) {
 				//print();
 				int sum = 0;
 				for(int i = 1; i<N+1; i++) {
 					for(int j = 1; j<M+1; j++) {
 						if(!b[i][j] && maze[i][j] == '?') {
 							sum++;
 						}
 					}
 				}
 				if(sum >= 1) {
 					answer += (int)Math.pow(3, sum);
 				}else {
 					answer++;
 				}
 			}
 			return;
 		}
 		b[r][c] = true;
 		char m = maze[r][c];
 		if(m == '.') {
 			dfs(r+dr[dir], c+ dc[dir], dir, cnt+1);
 		}else if(m == '/') {
 			switch(dir) {
 			case 0:
 				dfs(r+dr[3], c+dc[3], 3, cnt+1);
 				break;
 			case 1:
 				dfs(r+dr[2], c+dc[2], 2, cnt+1);
 				break;
 			case 2:
 				dfs(r+dr[1], c+dc[1], 1, cnt+1);
 				break;
 			case 3:
 				dfs(r+dr[0], c+dc[0], 0, cnt+1);
 				break;
 			}
 		}else if(m == '\\') {
 			switch(dir) {
 			case 0:
 				dfs(r+dr[2], c+dc[2], 2, cnt+1);
 				break;
 			case 1:
 				dfs(r+dr[3], c+dc[3], 3, cnt+1);
 				break;
 			case 2:
 				dfs(r+dr[0], c+dc[0], 0, cnt+1);
 				break;
 			case 3:
 				dfs(r+dr[1], c+dc[1], 1, cnt+1);
 				break;
 			}
 		}else if(m == '?') {
 			switch(dir) {
 			case 0:
 				maze[r][c] = '.';
 				dfs(r+dr[0], c+dc[0], 0, cnt+1);
 				maze[r][c] = '\\';
 				dfs(r+dr[2], c+dc[2], 2, cnt+1);
 				maze[r][c] = '/';
 				dfs(r+dr[3], c+dc[3], 3, cnt+1);
 				maze[r][c] = '?';
 				break;
 			case 1:
 				maze[r][c] = '.';
 				dfs(r+dr[1], c+dc[1], 1, cnt+1);
 				maze[r][c] = '/';
 				dfs(r+dr[2], c+dc[2], 2, cnt+1);
 				maze[r][c] = '\\';
 				dfs(r+dr[3], c+dc[3], 3, cnt+1);
 				maze[r][c] = '?';
 				break;
 			case 2:
 				maze[r][c] = '\\';
 				dfs(r+dr[0], c+dc[0], 0, cnt+1);
 				maze[r][c] = '/';
 				dfs(r+dr[1], c+dc[1], 1, cnt+1);
 				maze[r][c] = '.';
 				dfs(r+dr[2], c+dc[2], 2, cnt+1);
 				maze[r][c] = '?';
 				break;
 			case 3:
 				maze[r][c] = '/';
 				dfs(r+dr[0], c+dc[0], 0, cnt+1);
 				maze[r][c] = '\\';
 				dfs(r+dr[1], c+dc[1], 1, cnt+1);
 				maze[r][c] = '.';
 				dfs(r+dr[3], c+dc[3], 3, cnt+1);
 				maze[r][c] = '?';
 				break;
 			}
 		}
		b[r][c] = false;
 	}
}
