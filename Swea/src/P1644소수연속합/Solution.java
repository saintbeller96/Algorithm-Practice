package P1644소수연속합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		if(N == 1) {
			System.out.println(0);
			return;
		}
		int[] arr = new int[N+1];
		Integer[] primes = calcPrimeNumber(arr, N);
		int cnt = 0;
		int e = primes.length-1;
		if(primes[e] == N) {
			cnt++;
			e--;
		}
		int sum = 0;
		int idx = 0;
		for(int i = 0; i<=e; i++) {
			sum += primes[i];
			if(sum == N){
				cnt++;
			}else if(sum > N) {
				while(sum > N) {
					sum -= primes[idx++];
				}
				if(sum == N) cnt++;
			}
		}
		System.out.println(cnt);
	}
	
	static Integer[] calcPrimeNumber(int[] arr, int N) {
		for(int i =2 ; i<=N; i++) {
			arr[i] = i;
		}
		
		for(int i = 2; i<=N; i++) {
			if(arr[i] != 0) {
				for(int j = 2*i; j<=N; j+=i) {
					arr[j] = 0;
				}
			}
		}
		
		ArrayList<Integer> list = new ArrayList<>();
		
		for(int i = 2; i<=N; i++) {
			if(arr[i] != 0) list.add(arr[i]);
		}
		
		return list.toArray(new Integer[0]);
	}
}
