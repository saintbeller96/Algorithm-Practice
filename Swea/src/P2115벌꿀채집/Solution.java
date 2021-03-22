package P2115벌꿀채집;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, C, answer;
	static int[][] map, maxMap;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			C = Integer.parseInt(stk.nextToken());
			answer = 0;
			
			map = new int[N][N];
			maxMap = new int[N][N];
			for(int i = 0; i<N; i++) {
				stk = new StringTokenizer(br.readLine());
				for(int j = 0; j<N; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			
			for(int i = 0; i<N; i++) {
				for(int j = 0; j <= N-M;j++) {
					subset(i, j, 0, 0, 0);
				}
			}
			
			for(int i = 0; i<N; i++) {
				for(int j = 0; j <= N-M;j++) {
					int honey1 = maxMap[i][j];
					//같은 행
					for(int c = j+M; c<=N-M; c++) {
						int honey2 = maxMap[i][c];
						answer = Math.max(answer, honey1 + honey2);
					}
					//다른 행
					for(int r = i+1; r<N; r++) {
						for(int c = 0; c<=N-M; c++) {
							int honey2 = maxMap[r][c];
							answer = Math.max(answer, honey1 + honey2);
						}
					}
				}
			}
			System.out.println("#" +t +  " " + answer);
		}
				
	}
	static void subset(int i, int j, int depth, int sum , int totalSum) {
		if(sum > C) return;
		if(depth == M) {
			maxMap[i][j-M] = Math.max(maxMap[i][j-M], totalSum);
			return;
		}
		subset(i, j+1, depth+1, sum, totalSum);
		subset(i, j+1, depth+1, sum+map[i][j], totalSum + map[i][j]*map[i][j]);
	}
}
