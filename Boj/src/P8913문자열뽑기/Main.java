package P8913문자열뽑기;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			String str = br.readLine();
			flag = false;
			dfs(new StringBuilder().append(str));
			if (flag) System.out.println(1);
			else System.out.println(0);
		}
	}

	static void dfs(StringBuilder str) {
		if (flag) return;
		if (str == null || str.length() == 0) {
			flag = true;
			return;
		}

		int i = 0;
		while (i < str.length()) {
			char c = str.charAt(i);
			int j = i;
			while (i < str.length() && str.charAt(i) == c) {
				i++;
			}
			if (i-j > 1) {
				StringBuilder sb = new StringBuilder();
				for (int k = 0; k < str.length(); k++) {
					if(k < j || k >= i) {
						sb.append(str.charAt(k));
					}
				}
				dfs(sb);
				if (flag) return;
			}
		}
	}
}
