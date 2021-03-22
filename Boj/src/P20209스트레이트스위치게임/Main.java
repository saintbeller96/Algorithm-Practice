package P20209스트레이트스위치게임;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N, K;
	static int[] cubes;
	static List<Integer>[] switches;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		
		cubes = new int[N+1];
		stk = new StringTokenizer(br.readLine());
		for(int i = 1; i<=N; i++) {
			cubes[i] = Integer.parseInt(stk.nextToken());
		}
		switches = new List[K];
		for(int k = 0; k<K; k++) {
			switches[k] = new ArrayList<>();
			stk = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(stk.nextToken());
			for(int i = 0; i<n; i++) {
				switches[k].add(Integer.parseInt(stk.nextToken()));
			}
		}
		System.out.println(bfs());
		return;
	}
 	
 	static class Element{
 		int[] cubeState;
 		int count;
		public Element(int[] cubeState, int count) {
			this.cubeState = cubeState;
			this.count = count;
		}
 	}
 	
 	static int bfs() {
 		Set<String> visited = new HashSet<>();
 		Queue<Element> que = new LinkedList<>();
 		que.offer(new Element(copy(cubes), 0));
 		visited.add(toString(cubes));
 		
 		while(!que.isEmpty()) {
 			Element p = que.poll();
 			if(checkState(p.cubeState)) {
 				return p.count;
 			}
 			
 			for(int i = 0; i<switches.length; i++) {
 				int[] newState = copy(p.cubeState);
 				for(int j : switches[i]) {
 					newState[j] = (newState[j] + i+1)%5;
 				}
 				String key = toString(newState);
 				if(!visited.contains(key)) {
 					visited.add(key);
 					que.offer(new Element(newState, p.count+1));
 				}

 			}
 		}
 		return -1;
 	}
 	
 	static boolean checkState(int[] src) {
 		int a = src[1];
 		for(int i = 1; i<src.length; i++) {
 			if(src[i] != a) return false;
 		}
 		return true;
 	}
 	
 	static String toString(int[] src) {
 		StringBuilder sb = new StringBuilder();
 		for(int i: src) {
 			sb.append(i);
 		}
 		return sb.toString();
 	}
 	static int[] copy(int[] src) {
 		int[] dest = new int[src.length];
 		System.arraycopy(src, 0, dest, 0, src.length);
 		return dest;
 	}
}
