package P11062์นด๋๊ฒ์;

import java.util.Scanner;

public class Main2 {
	static int map[][];
	static int result[];

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		map = new int[102][102];
		for (int i = 1; i <= N; i++) {
			int C = scan.nextInt();
			int R = scan.nextInt();
			int w = scan.nextInt();
			int h = scan.nextInt();

			for (int r = R; r < R + h; r++) {
				for (int c = C; c < C + w; c++) {
					map[r][c] = i;
				}
			}
		}
		result = new int[N + 1];
		for (int j = 1; j <= N; j++) {

			for (int r = 0; r < 102; r++) {
				for (int c = 0; c < 102; c++) {
					if (map[r][c] == j) {
						result[j] += 1;
					}
				}
			}
		}
		for (int j = 1; j <= N; j++) {
			System.out.println(result[j]);
		}

	}
}
