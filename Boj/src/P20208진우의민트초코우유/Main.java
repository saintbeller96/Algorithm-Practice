package P20208진우의민트초코우유;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, H;
	static int[] home = {-1, -1};
	static int[][] mints;
	static int numofMint;
	static boolean flag;
	static int answer;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		H = Integer.parseInt(stk.nextToken());
		
		mints = new int[10][2];
		numofMint = 0;
		for(int i = 0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				int k = Integer.parseInt(stk.nextToken());
				if(k == 1) {
					home[0] = i;
					home[1] = j;
				}else if(k == 2) {
					mints[numofMint][0] = i;
					mints[numofMint][1] = j;
					numofMint++;
				}
			}
		}
		int[] input = new int[numofMint];
		for(int i = 0; i<numofMint; i++) {
			input[i] = i;
		}
		answer = 0;
		permutation(0, new int[numofMint], input);
		
		return;
 	}
 	
 	static void permutation(int depth, int[] perm, int[] input) {
 		if(depth == numofMint) {
 			int cnt = 0;
 			int curM = M;
 			int l = Math.abs(home[0] - mints[0][0]) + Math.abs(home[1] - mints[0][1]);
 			
 			if(l > curM) return;
 			int[] curLocation = mints[0];
 			curM  = curM - l + H;
 			
 			for(int i = 1; i<numofMint; i++) {
 				l = Math.abs(curLocation[0] - mints[i][0]) + Math.abs(curLocation[1] - mints[i][1]);
 				if(l > curM);
 				
 				
 				curLocation = mints[i];
 			}
 			
 			answer = Math.max(answer, cnt);
 			
 			return;
 		}
 		for(int i = depth; i<numofMint; i++) {
 			
 			perm[depth] = input[i];
 			swap(depth, i, input);
 			permutation(depth+1, perm, input);
 			swap(depth, i, input);
 		}
 		
 	}
 	
 	static void swap(int i, int j, int[] arr) {
 		int t = arr[i];
 		arr[i]=arr[j];
 		arr[j] = t;
 	}
}
