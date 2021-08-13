package P22232가희와파일탐색기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
		List<String> files = new ArrayList<>();
		for(int i = 0; i<N; i++){
			files.add(br.readLine());
		}

		Set<String> extensions = new TreeSet<>();
		for (int i = 0; i < M; i++) {
			extensions.add(br.readLine());
		}
		files.sort(new Comparator<>() {
			@Override
			public int compare(String s1, String s2) {
				String[] st1 = s1.split("\\.");
				String[] st2 = s2.split("\\.");
				int cmp = st1[0].compareTo(st2[0]);
				if(cmp == 0){
					String et1 = st1[1];
					String et2 = st2[1];
					if(extensions.contains(et1) && extensions.contains(et2)){
						return et1.compareTo(et2);
					}else if(extensions.contains(et1)){
						return -1;
					}else if(extensions.contains(et2)){
						return 1;
					}else{
						return et1.compareTo(et2);
					}
				}
				return cmp;
			}
		});
		StringBuilder sb = new StringBuilder();
		for(String s : files){
			sb.append(s).append('\n');
		}
		System.out.println(sb);
	}
}
