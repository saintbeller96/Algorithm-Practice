package P22252정보상인호석_우선순위큐;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int Q;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Q = Integer.parseInt(br.readLine());
		Map<String, Queue<Integer>> info = new HashMap<>();
		long answer = 0L;
		for(int i = 0; i<Q; i++){
			String[] tokens = br.readLine().split(" ");
			int query = Integer.parseInt(tokens[0]);
			if(query == 1){
				String gName = tokens[1];
				int k = Integer.parseInt(tokens[2]);
				info.putIfAbsent(gName, new PriorityQueue<>((a, b)->b-a));
				for(int j = 3; j<3+k; j++){
					info.get(gName).offer(Integer.parseInt(tokens[j]));
				}
			}

			if(query == 2){
				String gName = tokens[1];
				int b = Integer.parseInt(tokens[2]);
				if(!info.containsKey(gName)) continue;
				Queue<Integer> values = info.get(gName);
				for(int j = 0; j<b; j++){
					if(values.isEmpty()) break;
					answer+= values.poll();
				}
			}
		}
		System.out.println(answer);
	}
}
