package P17619개구리점프;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int[] line;
	private static int[] rank;
	private static int[][] log;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(stk.nextToken());
		int Q = Integer.parseInt(stk.nextToken());
		log = new int[N+1][3];
		line = new int[N+1];
		rank = new int[N+1];
		for(int i = 1; i<=N; i++) {
			line[i] = i;
			rank[i] = 1;
			stk = new StringTokenizer(br.readLine());
			log[i][0] = i;
			log[i][1] = Integer.parseInt(stk.nextToken());
			log[i][2] = Integer.parseInt(stk.nextToken());
			int ny = Integer.parseInt(stk.nextToken());
		}
		//Arrays.sort(log, (a, b) -> Integer.compare(a[1], b[1]));
		int x1 = log[1][1], x2 = log[1][2];
		int s = log[1][0];
		for(int i = 2; i<=N; i++) {
			int n = log[i][0];
			int nx1 = log[i][1];
			int nx2 = log[i][2];
			//x1은 항상 nx1 보다 작음
			if(x2 < nx1) {
				s = n;
				x1 = nx1;
				x2 = nx2;
			}else {
				x1 = Math.min(x1, nx1);
				x2 = Math.max(x2, nx2);
				union(s, n);
				s = n;
			}
		}
		
		for(int i = 0; i<Q; i++) {
			stk = new StringTokenizer(br.readLine());
			int s1 = Integer.parseInt(stk.nextToken());
			int s2 = Integer.parseInt(stk.nextToken());
			if(find(s1) == find(s2)) {
				System.out.println(1);
			}else {
				System.out.println(0);
			}
		}
	}
	private static int find(int s) {
		if(line[s] == s) {
			return s;
		}
		return line[s] = find(line[s]);
	}
	private static void union(int s1, int s2) {
		int x = find(s1);
		int y = find(s2);
		if(x != y) {
			if(rank[x] > rank[y]) {
				line[y] = x;
			}else {
				line[x] = y;
			}
			if(rank[x] == rank[y]) {
				rank[x]++;
			}
		}
	}

}
