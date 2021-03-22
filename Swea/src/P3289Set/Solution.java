package P3289Set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int T, N, M;
	private static int[] set;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		StringBuilder sb = null;
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb = new StringBuilder();
			sb.append('#').append(t).append(' ');
			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());	
			set = new int[N+1];
			for(int i = 1; i<= N; i++) {
				set[i] = i;
			}
			for(int i = 0; i<M; i++) {
				stk = new StringTokenizer(br.readLine());
				int op = Integer.parseInt(stk.nextToken());
				int a = Integer.parseInt(stk.nextToken());
				int b = Integer.parseInt(stk.nextToken());
				int s1 = a <= b?a:b;
				int s2 = a > b?a:b;	
				if(op == 0) {
					union(s1, s2);
				}
				else {
					if(isSameSet(s1, s2)) sb.append(1);
					else sb.append(0);					
				}
			}	
			System.out.println(sb);
		}			
	}
	private static void union(int s1, int s2) {
        int parent = find_set(s1);
        find_set(s2, parent);
    }
	private static int find_set(int s) {
		while(s != set[s]) {
			s = set[s];
		}
		return set[s];
	}
	private static int find_set(int s, int p) {
		while(s != set[s]) {
			int temp = set[s];
			set[s] = p;
			s = set[temp];
		}
		set[s] = p;
		return set[s];
	}
    private static boolean isSameSet(int s1, int s2) {
        if(find_set(s1) == find_set(s2)) return true;
        else return false;
    }
    private static int find_setR(int s) {
    	while(s != set[s]) {
    		s = find_setR(set[s]);
    	}
    	return set[s];
    }
    private static void unionR(int s1, int s2) {
    	set[find_setR(s2)] = find_setR(s1);
    }
}
