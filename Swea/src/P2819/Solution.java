package P2819;

import java.util.Scanner;
import java.util.HashSet;

public class Solution {
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	private static int[][] map;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);	
		int T = scan.nextInt();
		for(int t = 1; t<= T; t++) {
			HashSet<String> hs = new HashSet<String>();
			map = new int[4][4];
			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					map[i][j] = scan.nextInt();
				}
			}
			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					go(hs, Integer.toString(map[i][j]), i, j, 0);
				}
			}
			System.out.println("#" + t + " " + hs.size());
		}
		scan.close();
	}
	private static void go(HashSet<String> hs, String str, int i, int j, int cnt) {
		if(cnt == 6) {
			hs.add(str);
			return;
		}
		for (int d = 0; d < 4; d++) {
			if (i + dr[d] >= 0 && i + dr[d] < 4 && j + dc[d] >= 0 && j + dc[d] < 4) {
				i += dr[d];
				j += dc[d];
				go(hs, str + map[i][j], i, j, cnt + 1);
				i -= dr[d];
				j -= dc[d];
			}
		}
	}

}
