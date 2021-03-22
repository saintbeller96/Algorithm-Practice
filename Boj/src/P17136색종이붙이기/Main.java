package P17136색종이붙이기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[10][10];
		for(int i = 0; i<10; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			for(int j = 0; j<10; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
	}
	
	static void DFS(int a, int b, int c, int d, int e) {
		for(int i = 0; i<10; i++) {
			for(int j = 0; j<10; j++) {
				if(map[i][j] == 1) {
					
					
					
				}
			}
		}
	}
}
