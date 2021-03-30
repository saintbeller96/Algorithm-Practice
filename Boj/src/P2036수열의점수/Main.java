package P2036수열의점수;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

public class Main {
	static int N;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		List<Long> minus = new ArrayList<>();
		List<Long> plus = new ArrayList<>();
		int zero = 0;
		for(int i = 0; i<N; i++) {
			long a = Long.parseLong(br.readLine());
			if(a>0) plus.add(a);
			else if(a<0) minus.add(a);
			else zero++;
		}
		Collections.sort(plus, Collections.reverseOrder());
		Collections.sort(minus);
		long answer = 0L;
		
		for(int i = 0; i<plus.size(); i++) {
			long p = plus.get(i);
			if(p == 1 || i == plus.size()-1) answer += p;
			else {
				long p2 = plus.get(i+1);
				if(p2 == 1) answer += p + 1;
				else answer += p*plus.get(i+1);
				i++;
			}
		}
		
		for(int i = 0; i<minus.size(); i++) {
			long p = minus.get(i);
			if(i == minus.size()-1) answer+=zero>0?0:p;
			else {
				answer += p*minus.get(i+1);
				i++;
			}
		}
		
		System.out.println(answer);		
	}
}
