package 홈방범서비스;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, M, K, Answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = null;
        T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            stk = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(stk.nextToken());
            M = Integer.parseInt(stk.nextToken());
            Answer = 0;
            int[][] map = new int[N][N];
            
            int maxCost = 0;
            for(int i = 0; i< N; i++) {
            	stk = new StringTokenizer(br.readLine());
            	for(int j = 0; j<N; j++) {
            		map[i][j] = Integer.parseInt(stk.nextToken());
            		if(map[i][j] == 1) {
            			maxCost += M;
            		}
            	}
            }
            
            for(int k = 1; ; k++) {
            	int pCost = k*k + (k-1)*(k-1);
            	if(pCost > maxCost) break;
            	int W = 2*k-1;
            	
            	int[][] padMap = new int[N+2*W-2][N+2*W-2];
            	int r = W-1;
            	int c = W-1;
            	for(int i = r; i<r+N-1; i++) {
            		for(int j = c; j<c+N-1; j++) {
            			padMap[i][j] = map[i-r][j-c];
            		}
            	}
            	System.out.println(W);
            	print(padMap);
            	
            	for(int i = 0; i< N+W-1; i++) {
            		for(int j = 0; j<N+W-1; j++) {
            			int cnt = find(padMap, i, j, W);
            			Answer = Math.max(Answer, cnt);
            			break;
            		}
            	}
            	
            }
            System.out.println("#" + t + " " + Answer);
            
        }

	}
	static int find(int[][] map, int r, int c, int W) {
		int cnt = 0;
		int center = (r+W)/2;
		for(int i = r; i<r+W; i++) {
			if(map[i][center] == 1) {
				cnt++;
			}
		}
		int q = 1;
		for(int k = 2; W-k >= 1; k+=2) {
			for(int i = r+q; i < r+q + W-k; i++) {
				if(map[i][center-q] == 1) cnt++;
				if(map[i][center+q] == 1) cnt++;
			}
			
			q++;
		}
		return cnt;
	}
	
	static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
