package P1992쿼드트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	private static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		sb = new StringBuilder();

		if (N == 64) {
			long[] img = new long[N/2];
			String[] strs = new String[N];
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				strs[i] = br.readLine();
				for (int j = 0; j < N; j++) {
					if (strs[i].charAt(j) == '1') {
						if(i == 0) cnt++;
					}
				}
			}
			boolean flag = false;
			if (cnt == N || cnt == 0) {
				for (int i = 1; i < N; i++) {
					if (!strs[i].equals(strs[0])) {
						flag = true;
						break;
					}
				}
			}
			if (!flag && (cnt == N || cnt == 0)) {
				if (cnt == N) {
					sb.append(1);
				}else if(cnt == 0) {
					sb.append(0);
				}
			} else {
				sb.append('(');
				int idx = 0;
				for (int i = 0; i < N / 2; i++) {
					img[idx++] = Long.parseLong(strs[i].substring(0, 32), 2);
				}
				DAC(img, 32);
				idx = 0;
				for (int i = 0; i < N / 2; i++) {
					img[idx++] = Long.parseLong(strs[i].substring(32, 64), 2);
				}
				DAC(img, 32);
				idx = 0;
				for (int i = N / 2; i < N; i++) {
					img[idx++] = Long.parseLong(strs[i].substring(0, 32), 2);
				}
				DAC(img, 32);
				idx = 0;
				for (int i = N / 2; i < N; i++) {
					img[idx++] = Long.parseLong(strs[i].substring(32, 64), 2);
				}
				DAC(img, 32);
				sb.append(')');
			}
		} else {
			long[] img = new long[N];
			for (int i = 0; i < N; i++) {
				img[i] = Long.parseLong(br.readLine().trim(), 2);
			}
			DAC(img, N);
		}
		System.out.println(sb);
	}

	private static void DAC(long[] img, int k) {
		if (k <= 1) {
			sb.append(img[0]);
			return;
		}

		long a = img[0];
		boolean flag = true;
		if (a == 0 || a + 1L == Math.pow(2, k)) {
			flag = false;
			for (int i = 1; i < img.length; i++) {
				if (k >= 2 && a == 1) {
					flag = true;
					break;
				}
				if ((a ^ img[i]) != 0) {
					flag = true;
					break;
				}
			}
		}

		if (flag) {
			int n = img.length / 2;
			long[][] subImg = new long[4][n];
			for (int i = 0; i < 4; i++) {
				subImg[i] = new long[n];
			}

			for (int j = 0; j < n; j++) {
				subImg[0][j] = img[j] >> n;
				subImg[1][j] = ((img[j] >> n) << n) ^ img[j];
			}
			int idx = 0;
			for (int j = n; j < img.length; j++) {
				subImg[2][idx] = img[j] >> n;
				subImg[3][idx++] = ((img[j] >> n) << n) ^ img[j];
			}
			sb.append('(');
			for (int i = 0; i < 4; i++) {
				DAC(subImg[i], k / 2);
			}
			sb.append(')');
		} else {
			sb.append(a & 1);
			return;
		}

	}

}
