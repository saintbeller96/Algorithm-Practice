package P2186문자판;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,M,K;
	static int L;//단어의 길이
	static int answer = 0;
	static char[][] map;
	static int[][][] mem;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		map = new char[N][M];
		for(int i = 0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		String word = br.readLine();
		L = word.length();
		//이미 한번 탐색을 진행한 곳을 기억하는 배열
		mem = new int[N][M][L+1];
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				for(int k=0;k<=L;k++) {
					mem[i][j][k] = -1;
				}
			}
		}
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(map[i][j] == word.charAt(0)) {
					answer+=dfs(word, i, j, 1);
				}
			}
		}
		System.out.println(answer);
	}
	
	
	static int dfs(String word, int r, int c, int depth) {
		if(mem[r][c][depth] != -1) {
			return mem[r][c][depth];
		}
		if(depth==L) {
			return 1;
		}
		mem[r][c][depth] = 0;
		for(int l = 1; l<=K; l++) {
			for(int d = 0; d<4; d++) {
				int nr = r+ dr[d]*l;
				int nc = c+ dc[d]*l;
				if(nr>=0 && nr<N && nc >= 0 && nc <M) {
					if(map[nr][nc] == word.charAt(depth)) {
						int ret = dfs(word, nr, nc, depth+1);
						if(ret > 0) {
							mem[r][c][depth] += ret;
						}
					}
				}
			}
		}
		return mem[r][c][depth];
	}
}
