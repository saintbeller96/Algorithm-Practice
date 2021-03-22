package P7573고기잡이;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N, I, M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		I = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());

		int[][] fish = new int[M][2];
		for (int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			fish[i][0] = Integer.parseInt(stk.nextToken());
			fish[i][1] = Integer.parseInt(stk.nextToken());
		}
		int answer = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 1; k < I / 2; k++) {
					int width = k;// 그물 너비
					int height = I / 2 - k;// 그물 높이
					answer = Math.max(answer, net(fish, fish[i][0], fish[j][1], width, height));
				}
			}
		}
		System.out.println(answer);
	}

	public static int net(int[][] fish, int i, int j, int w, int h) {
		int cnt = 0;
		for (int idx = 0; idx < M; idx++) {
			int fishI = fish[idx][0];
			int fishJ = fish[idx][1];
			if (i <= fishI && fishI <= i + w && j <= fishJ && fishJ <= j + h) {
				cnt++;
			}
		}
		return cnt;
	}
}
