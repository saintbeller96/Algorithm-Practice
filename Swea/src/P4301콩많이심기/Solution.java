package P4301콩많이심기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int T, N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
						
			int answer = 0;
			if(N == 2 && M == 2) {
				answer = 4;
			}else if(N == 1 && M == 1){
				answer = 1;
			}
			else if(N%2 ==0 && M%2 == 0) {
				answer = N*M/2;
			}else {
				if(N%2 ==0 && M%2 == 1 || M%2 ==0 && N%2 ==1){
					if((N*M/2)%2 == 0){
						answer = N*M/2;
					}else{
						answer = N*M/2 +1;
					}
				}else{
					answer = N*M/2 +1;
				}
			}					
			System.out.println("#" + t+ " " + answer);			
		}
	}
}
