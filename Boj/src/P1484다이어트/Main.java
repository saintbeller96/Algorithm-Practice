package P1484다이어트;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine());
		
		int max = (int)Math.sqrt(G)*100;
		Set<Integer> result = new TreeSet<>();
		//G = (A-B)(A+B) = C x D (C < D)
		for(int i = 1; i*i<=G; i++) {
			if(G%i == 0 && i != G/i){
				int d = G/i;
				int c = i;
				if((c+d)%2 == 0 && (c-d)%2 == 0) {
					result.add((c+d)/2);
				}
			}
		}
		
		StringBuilder answer = new StringBuilder();		
		if(result.size() == 0) {
			answer.append(-1);
		}else {
			result.forEach(n -> answer.append(n).append('\n'));
		}
		System.out.println(answer);
	}
}
