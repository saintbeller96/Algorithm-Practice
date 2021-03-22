package P3234양팔저울;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, answer;
	static int[] chu;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringTokenizer stk = null;
		for(int t = 1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			stk = new StringTokenizer(br.readLine());
			
			chu = new int[N];
			Arrays.sort(chu);
			answer = 0;
			for(int i = 0; i<N; i++) {
				chu[i] = Integer.parseInt(stk.nextToken());
			}
			nPr(0,0,0);
			System.out.println("#" + t + " " + answer);
		}
	}
	static void nPr(int left, int right, int depth) {
		if(depth == N) {
			answer++;
			return;
		}
		else{
			for(int i = depth ; i<N; i++) {
				swap(i, depth);
				nPr(left + chu[depth], right, depth+1);
				if(left >= right+chu[depth])
					nPr(left, right+chu[depth], depth+1);
				swap(i, depth);
			}
		}
	}
	
	private static void swap(int i, int j) {
		int temp = chu[i];
		chu[i] = chu[j];
		chu[j] = temp;
	}
}
