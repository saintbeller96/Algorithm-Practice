package P2632피자판매;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(stk.nextToken());
		int n = Integer.parseInt(stk.nextToken());
		
		
		int[] pA = new int[m];
		int sumA = 0;
		for(int i = 0; i<m; i++) {
			pA[i] = Integer.parseInt(br.readLine());
			sumA += pA[i];
		}
		
		int[] pizzaA = new int[20000001];
		sum(pA, pizzaA);
		pizzaA[0] = 1;
		pizzaA[sumA] = 1;
		
		int[] pB = new int[n];
		int sumB = 0;
		for(int i = 0; i<n; i++) {
			pB[i] = Integer.parseInt(br.readLine());
			sumB += pB[i];
		}
		
		int[] pizzaB = new int[20000001];
		sum(pB, pizzaB);
		pizzaB[0] = 1;
		pizzaB[sumB] = 1;
		
		int cnt = 0;
		for(int i = 0; i<=N; i++) {
			cnt += pizzaA[i]*pizzaB[N-i];
		}
		
		System.out.println(cnt);
	}
	
	static void sum(int[] pizza, int[] pizzaSize) {
		int len = pizza.length;
		for(int i = 0; i<len; i++) {
			int sum = 0;
			for(int j = 0; j<len-1; j++) {
				sum += pizza[(i+j)%len];
				pizzaSize[sum]++;
			}
		}
	}

}
