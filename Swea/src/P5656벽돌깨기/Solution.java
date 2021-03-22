package P5656벽돌깨기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, W, H, answer;
	static int[][] map;
	static Queue<int[]> que = new LinkedList<int[]>();
	static int[] dr = {-1, 1, 0 ,0};
	static int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			W = Integer.parseInt(stk.nextToken());
			H = Integer.parseInt(stk.nextToken());
			
			map = new int[H][W];
			for(int i = 0; i<H; i++) {
				stk = new StringTokenizer(br.readLine());
				for(int j = 0; j<W; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			answer = Integer.MAX_VALUE;
			int[] input = new int[W];
			for(int i = 0; i<W; i++) {
				input[i] = i;
			}
			permutation(new int[N], input, W, N, 0);
			
			System.out.println("#" + t + " " + answer);
		}
	}
	
	static void permutation(int[] order, int[] input, int N, int R, int cnt) {
		if(answer == 0) return;
		if(cnt == R) {
			answer = Math.min(answer, play(order));
			return;
		}
		for(int i = 0; i<N; i++) {
			order[cnt] = input[i];
			//swap(input, cnt, i);
			permutation(order, input, N, R, cnt+1);
			//swap(input, cnt, i);
		}
	}
	static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
	
	static int play(int[] order) {
		int cnt = 0;
		int[][] cmap = new int[H][W];
		for(int i = 0; i<H; i++) {
			for(int j = 0; j<W; j++) {
				cmap[i][j] = map[i][j];
			}
		}
		for(int c : order) {
			que.clear();
			int r = 0;
			for(r = 0;r<H;r++) {
				if(cmap[r][c] != 0) break;
			}
			if(r == H) continue;
			
			//폭발 범위
			int size = cmap[r][c];
			if(size == 1) {
				cmap[r][c] = 0;
				continue;
			}
			
			boom(cmap, r, c, size);
			
			while(!que.isEmpty()) {
				int[] p = que.poll();
				boom(cmap, p[0], p[1], cmap[p[0]][p[1]]);
			}
			down(cmap);
		}
		for(int i = 0; i<H; i++) {
			for(int j = 0; j<W; j++) {
				if(cmap[i][j] != 0) cnt++;
			}
		}
		
		return cnt;
	}
	
	static void boom(int[][] map, int r, int c, int size){
		for(int i = 1; i<size; i++) {
			if(r + i < H) {
				if(map[r+i][c]!=0) que.offer(new int[] {r+i, c});
			}
			if(r - i >= 0) {
				if(map[r-i][c]!=0) que.offer(new int[] {r-i, c});
			}
			if(c + i < W) {
				if(map[r][c+i]!=0) que.offer(new int[] {r, c+i});
			}
			if(c - i >=0) {
				if(map[r][c-i]!=0) que.offer(new int[] {r, c-i});
			}
		}
		map[r][c] = 0;
	}
	
	static void down(int[][] map) {
		for(int c = 0; c<W; c++) {
			for(int r = H-1; r>=0; r--) {
				if(map[r][c] != 0) {
					for(int k = H-1; k>r; k--) {
						if(map[k][c] == 0) {
							int t = map[r][c];
							map[r][c] = map[k][c];
							map[k][c] = t;
						}
					}
				}
			}
		}
	}
	static void print(int[][] map) {
		for(int i = 0; i<H; i++) {
			for(int j = 0; j<W; j++) {
				System.out.printf("%2d", map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
