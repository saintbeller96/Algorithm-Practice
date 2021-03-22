package P19535ㄷㄷㄷㅈ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long gTree, dTree;
	static HashMap<Integer, ArrayList<Integer>> adjList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adjList = new HashMap<Integer, ArrayList<Integer>>();
		for(int i = 1; i<N; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(stk.nextToken());
			int v = Integer.parseInt(stk.nextToken());
			if(!adjList.containsKey(u)) {
				adjList.put(u, new ArrayList<Integer>());
			}
			if(!adjList.containsKey(v)) {
				adjList.put(v, new ArrayList<Integer>());
			}
			adjList.get(u).add(v);
			adjList.get(v).add(u);
		}
		gTree = 0;
		for(Entry<Integer, ArrayList<Integer>> e : adjList.entrySet()) {
			//한 정점과 연결된 정점이 3개 이상이면 ㅈ 그래프 가능
			//size의 최대 크기 = 300,000 -1 => int 범위 넘어갈 수 있음
			long size = e.getValue().size();
			if(size == 3) {
				gTree++;
			}else if(size > 3) {
				//n개의 점 중 3개를 뽑는 경우의 수 -> nC3
				gTree += (size)*(size-1)*(size-2)/6L;
			}
		}
		dTree = 0;
		DFS(1, 0, 1);
		System.out.println(dTree +" " + gTree);
		if(dTree < gTree*3) {
			System.out.println("G");
		}else {
			if(dTree == gTree*3) {
				System.out.println("DUDUDUNGA");
			}
			else {
				System.out.println("D");
			}
		}

	}
	static void DFS(int v, int parent, int flag) {
		int childs = adjList.get(v).size()-1;
		if(parent == 0) childs++;
		
		for(int z : adjList.get(v)) {
			if(z == parent) continue;
			int descendents = adjList.get(z).size() -1;
			if(childs -1 > 0 && descendents > 0) {
				dTree += (childs -1)*descendents;
			}
				
			if(parent != 0 && descendents >= 1) {
				dTree += descendents;
			}

			if((flag&(1<<z))!=0) continue;
			DFS(z, v, flag|(1<<z));
		}
	}
}
