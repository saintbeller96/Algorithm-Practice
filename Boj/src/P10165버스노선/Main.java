package P10165버스노선;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static long[][] bus;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		long N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		bus = new long[M][3];
		for(int i = 0; i<M; i++) {
			stk = new StringTokenizer(br.readLine());
			long a = Long.parseLong(stk.nextToken());
			long b = Long.parseLong(stk.nextToken());
			bus[i][0] = i;
			if(a > b) {
				bus[i][1] = a;
				bus[i][2] = b+N;
			}else {
				bus[i][1] = a + N;
				bus[i][2] = b + N;
			}
		}
		Arrays.sort(bus, new Comparator<long[]>() {
			public int compare(long[] o1, long[] o2) {
				if(o1[1] > o2[1]) return 1;
				else if(o1[1] < o2[1]) return -1;
				else {
					return Long.compare(o2[2], o1[2]);
				}
			}			
		});
		ArrayList<long[]> stations = new ArrayList<long[]>();
		TreeSet<Integer> ts = new TreeSet<Integer>();
		for(int i = 0; i<M; i++) {
			//System.out.println(bus[i][0]+1);
			long s = bus[i][1];
			long e = bus[i][2];
			boolean flag = true;

			for(int j = stations.size()-1; j >=0 ; j--) {
				long ss = stations.get(j)[1];
				long se = stations.get(j)[2];
				//포함되는 경우
				if(ss<= s && e <= se) {
					flag = false;
					break;
				}else if(s >= N && s - N >= ss && e>=N && e-N<=se) {
					flag = false;
					break;
				}
			}
			if(flag) {
				stations.add(bus[i]);
				ts.add((int)bus[i][0]);
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i : ts) {
			sb.append(i + 1).append('\n');
		}
		System.out.println(sb);
	}
}