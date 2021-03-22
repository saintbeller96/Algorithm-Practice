package P1700멀티탭스케줄링;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N, K;
	static int[] orders;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		
		int[] orders = new int[K];
		stk = new StringTokenizer(br.readLine());
		
		for(int i = 0; i<K; i++) {
			orders[i] = Integer.parseInt(stk.nextToken());
		}
		
		int cnt = 0;
		TreeSet<Integer> outlets = new TreeSet<>();
		
		for(int i = 0; i<K; i++) {
			int item = orders[i];
			if(outlets.size() < N) outlets.add(item);
			else if(!outlets.contains(item)) {
				//꽂혀있는 기기 중 가장 나중에 사용될 기기를 뽑음
				
				int out = -1;
				int out_idx = -1;
				for(int in : outlets) {
					int idx = indexOf(orders, i+1, in);
					if(idx == 1001) {
						out = in;
						break;
					}else if(out_idx < idx) {
						out_idx = idx;
						out = in;
					}
				}
				outlets.remove(out);
				outlets.add(item);
				cnt++;
			}
		}
		System.out.println(cnt);
	}
 	
 	static int indexOf(int[] orders, int start, int item) {
 		for(int i = start; i<K; i++) {
 			if(orders[i] == item) return i;
 		}
 		return 1001;
 	}
}
