package P2208보석줍기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] gems;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		gems = new int[N];
		for(int i = 0; i<N; i++) {
			gems[i] = Integer.parseInt(br.readLine());
		}
		int max = 0;
		int min = 0;
		int totalSum = 0;
		int subSum = 0;
		for(int i = 0; i<M; i++) {
			totalSum += gems[i];
		}
		for(int i = M; i<N; i++) {
			totalSum += gems[i];
			subSum += gems[i-M];
			min = Math.min(min, subSum);
			max = Math.max(max, totalSum - min);
		}
		System.out.println(max);
	}
 	
 	/*
 	1. 구간 (x, y)의 합
		- sum(x+1, y) (x<=y) = psum(y) - psum(x)

	2. y(y>=m)에서 길이가 m이상인 최대 구간합
		- max_sum(y) = psum(y) - min(psum(y-m), psum(y-m-1), psum(y-m-2), ... ,psum(0))
		- max_sum(y+1) = psum(y+1) - min(psum(y+1-m), psum(y-m), ... ,psum(0))
	3. 점화식 max_sum(n) = psum(n) - min({psum(k) | y-m >= k >= 0})
 	 */
 	
 	//m = 4
 	//        -1 -1  1  1| 1  1 -1  2
 	//psum    -1 -2 -1  0| 1  2  1  3
 	//ss                  -1 -2 -1  0 
 	//min                 -1 -2 -2 -2
 	//max      0  0  0  0| 2  4  3  5
}
