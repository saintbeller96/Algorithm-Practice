package P4929수열걷기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			String[] lines = new String[2];
			lines[0] = br.readLine();
			if(lines[0].equals("0")) break;
			lines[1] = br.readLine();
			solve(lines);
		}
	}
	
	public static void solve(String[] lines) {
		StringTokenizer stk = null;
		stk = new StringTokenizer(lines[0]);
		int N1 = Integer.parseInt(stk.nextToken());
		int[] arr1 = new int[N1+1];
		int[] presum1 = new int[N1+1];
		int[] value_idx = new int[20003];
		for(int i = 1; i<=N1; i++) {
			arr1[i] = Integer.parseInt(stk.nextToken());
			value_idx[10000+arr1[i]] = i;
			presum1[i] = arr1[i];
			presum1[i] += presum1[i-1];	
		}
		stk = new StringTokenizer(lines[1]);
		int N2 = Integer.parseInt(stk.nextToken());
		int[] arr2 = new int[N2+1];
		int[] presum2 = new int[N2+1];
		int pre1 = 0,pre2 = 0;
		int answer = 0;
		for(int i = 1; i<=N2; i++) {
			arr2[i] = Integer.parseInt(stk.nextToken());
			presum2[i] = arr2[i];
			presum2[i] += presum2[i-1];	
			if(value_idx[arr2[i]+10000] != 0) {
				int k = value_idx[arr2[i]+10000];
				int sum1 = presum1[k] - presum1[pre1];
				int sum2 = presum2[i] - presum2[pre2];
				pre1 = k;
				pre2 = i;
				answer += Math.max(sum1, sum2);
			}
		}
		int sum1 = presum1[N1] - presum1[pre1];
		int sum2 = presum2[N2] - presum2[pre2];
		answer += Math.max(sum1, sum2);
		System.out.println(answer);
	}
}
