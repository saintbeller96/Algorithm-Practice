package P16678모독;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> list = new ArrayList<>();
		
		for(int i = 0; i<N; i++) {
			int a = Integer.parseInt(br.readLine());
			list.add(a);
		}
		Collections.sort(list);
		
		long answer = 0;
		int cnt = 1;
		for(int n : list) {
			if(n >= cnt) {
				answer += n-cnt;
				cnt++;
			}
		}
		
		System.out.println(answer);
 	}
}
