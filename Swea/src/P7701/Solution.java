package P7701;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.TreeSet;

public class Solution {
	private static TreeSet<String> ts[];
	private static int T, N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			ts = new TreeSet[51];
			for(int i = 1; i <= 50; i++) {
				ts[i] = new TreeSet<String>();
			}
			N = Integer.parseInt(br.readLine());
			for(int i = 0; i<N; i++) {
				String str = br.readLine();
				int len = str.length();
				ts[len].add(str);				
			}
			sb.append("#").append(t).append('\n');
			for(int i = 1; i <= 50; i++) {
				Iterator<String> iter = ts[i].iterator();
				while(iter.hasNext()) {
					sb.append(iter.next());
					sb.append('\n');
				}
			}
			System.out.println(sb);
		}
	}
}
