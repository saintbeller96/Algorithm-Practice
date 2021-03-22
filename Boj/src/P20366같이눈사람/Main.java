package P20366같이눈사람;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

public class Main {
	static int N;
	static int answer;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
		}
		Arrays.sort(arr);
		
		TreeMap<Integer, List<int[]>> map = new TreeMap<>();
		for(int i = 0; i<N-1; i++) {
			for(int j = i+1; j<N; j++) {
				int n = arr[i] + arr[j];
				map.putIfAbsent(n, new ArrayList<>());
				map.get(n).add(new int[] {i, j});
			}
		}
		List<Integer> sumList = new ArrayList<>(map.keySet());
		answer = Integer.MAX_VALUE;
		for(int i = 0; i < sumList.size()-1; i++) {
			int n = sumList.get(i);
			int m = sumList.get(i+1);
			
			boolean flag = false;
			for(int[] arr1 : map.get(n)) {			
				for(int[] arr2 : map.get(m)) {
					Set<Integer> temp = new TreeSet<>(Arrays.asList(arr1[0], arr1[1], arr2[0], arr2[1]));
					if(temp.size() == 4) {
						flag = true;
						break;
					}
				}
				if(flag) break;
			}
			if(flag) answer = Math.min(answer, m-n);
		}
		System.out.println(answer);
	}
}
