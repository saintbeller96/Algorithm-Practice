package P19535ㄷㄷㄷㅈ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Main2 {
	static int N;
	static long gTree, dTree;
	static HashMap<Integer, ArrayList<Integer>> adjList;
	static ArrayList<int[]> edges;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adjList = new HashMap<Integer, ArrayList<Integer>>();
		edges = new ArrayList<int[]>();
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
			
			edges.add(new int[] {u, v});
		}
		gTree = 0;
		dTree = 0;
		for(Entry<Integer, ArrayList<Integer>> e : adjList.entrySet()) {
			//�� ������ ����� ������ 3�� �̻��̸� �� Ʈ���� ��
			//size�� �ִ� ũ�� = 300,000 -1 => int ���� �Ѿ �� ����
			long size = e.getValue().size();
			if(size == 3) {
				gTree++;
			}else if(size > 3) {
				//n���� �� �� 3���� �̴� ����� �� -> nC3
				gTree += (size)*(size-1)*(size-2)/6L;
			}
		}
		
		//�߽� ������ �ϳ� �� �� �� ������ leaf ��尡 �ƴϸ� �� Ʈ���� ��
		for(int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			
			int n1 = adjList.get(u).size();
			int n2 = adjList.get(v).size();
			
			dTree+=(n1-1)*(n2-1);
		}

		//System.out.println(dTree +" " + gTree);
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
}
