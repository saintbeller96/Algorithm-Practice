package P2382미생물;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Solution {
	private static int[] dr = {0, -1, 1,0,0}; //위, 아래, 좌, 우
	private static int[] dc = {0, 0,0,-1,1};
	private static int T, N, M, K, Answer;
	private static class Microbe{
		int i, j, cluster, direction;
		public Microbe(int i, int j, int c, int d) {
			this.i = i;
			this.j = j;
			this.cluster = c;
			this.direction = d;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		T = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= T; t++) {
			stk = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			K = Integer.parseInt(stk.nextToken());
			Answer = 0;
				ArrayList<Microbe> list = new ArrayList<Microbe>();
			for(int k = 1; k<=K; k++) {
				stk = new StringTokenizer(br.readLine().trim());
				int i = Integer.parseInt(stk.nextToken());
				int j = Integer.parseInt(stk.nextToken());
				int c = Integer.parseInt(stk.nextToken());
				int d = Integer.parseInt(stk.nextToken());
				list.add(new Microbe(i, j, c, d));
			}
			for(int m = 0; m<M; m++) {
				simulation2(list);
			}
			for(Microbe m : list) {
				if(m != null) {
					Answer += m.cluster;
				}
			}
			System.out.println("#" + t + " " + Answer);
		}

	}
	private static void simulation2(ArrayList<Microbe> list) {
		HashMap<String, ArrayList<Microbe>> hashMap = new HashMap<String, ArrayList<Microbe>>();
		for(Microbe m : list) {
			int r = m.i + dr[m.direction];
			int c = m.j + dc[m.direction];
			if(r >= 0 && r < N && c >= 0 && c < N) {
				if(r == 0 || r == N-1 || c == 0 || c == N-1) {
					m.cluster /= 2;
					switch (m.direction) {
						case 1:m.direction = 2 ;break;
						case 2:m.direction = 1 ;break;
						case 3:m.direction = 4 ;break;
						case 4:m.direction = 3 ;break;
					}
				}
				StringBuilder sb = new StringBuilder();
				sb.append(r).append(' ').append(c);
				String str = sb.toString();
				if(hashMap.containsKey(str)) {
					hashMap.get(str).add(m);
				}else {
					ArrayList<Microbe> a = new ArrayList<Microbe>();
					a.add(m);
					hashMap.put(sb.toString(), a);
				}	
			}
		}
		for(Entry<String, ArrayList<Microbe>> e : hashMap.entrySet()) {
			StringTokenizer stk = new StringTokenizer(e.getKey());
			int r = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());

			ArrayList<Microbe> alist = e.getValue();
			int sum = 0;
			int idx = 0;
			int max = -1;
			int len = alist.size();
			for (int i = 0; i < len; i++) {
				if(max < alist.get(i).cluster) {
					idx = i;
					max = alist.get(i).cluster;
				}
				sum += alist.get(i).cluster;
			}
			Microbe m =  alist.get(idx);
			if(m.cluster > 0) {
				m.cluster = sum;
				m.i = r;
				m.j = c;
				alist.remove(idx);
			}					
			for (Microbe micro : alist) {
				list.remove(micro);
			}
		}		
	}
}
