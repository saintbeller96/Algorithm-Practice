package P1469숌사이수열_백트래킹;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
	static int N;
	static int[] X;
	static boolean find;
	static int[] answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer stk = new StringTokenizer(br.readLine());
		X = new int[N];
		for(int i = 0; i<N; i++){
			X[i] = Integer.parseInt(stk.nextToken());
		}
		Arrays.sort(X);
		int[] S = new int[N*2];
		Arrays.fill(S, -1);
		answer = new int[N*2];
		dfs(S,  new boolean[N], 0, 0);
		if(!find){
			System.out.println(-1);
		}
		else{
			for(int s : answer) System.out.print(s + " ");
		}
	}
	static void dfs(int[] S, boolean[] check, int depth, int cnt){
		if(find) return;
		if(cnt == N){
			find = true;
			System.arraycopy(S, 0, answer, 0, N*2);
			return;
		}
		if(S[depth] != -1) return;
		for(int i = 0; i<N; i++){
			if(check[i]) continue;
			check[i] = true;
			S[depth] = X[i];
			if(depth + 1 + X[i] < 2*N && S[depth+1+X[i]] == -1){
				S[depth+1+X[i]] = X[i];
				int d = depth+1;
				for(; d<2*N; d++){
					if(S[d] == -1){
						dfs(S, check, d, cnt+1);
						break;
					}
				}
				if(d == 2*N && cnt+1 == N){
					dfs(S, check, d, cnt+1);
				}
				S[depth+1+X[i]] = -1;
			}
			check[i] = false;
			S[depth] = -1;
		}
	}
}
