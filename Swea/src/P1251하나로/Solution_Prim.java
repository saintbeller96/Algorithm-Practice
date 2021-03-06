package P1251하나로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_Prim {
	private static int T, N;
	private static double E;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			long[][] islands = new long[N][2];
			//PriorityQueue<Vertex> que = new PriorityQueue<Edge>((x, y)->Double.compare(x.dist, y.dist));
			stk = new StringTokenizer(br.readLine());
			for(int i = 0; i<N; i++) {
				islands[i][0] = Long.parseLong(stk.nextToken());
			}
			
			double[] minEdge = new double[N];
			boolean[] isInMst = new boolean[N];
			
			stk = new StringTokenizer(br.readLine());
			for(int i = 0; i<N; i++) {
				islands[i][1] = Long.parseLong(stk.nextToken());
				minEdge[i] = Double.MAX_VALUE;
			}
			E = Double.parseDouble(br.readLine());
			
			double answer = 0, min;
			int minVertex = 0;
			minEdge[0] = 0;
			for(int v = 0; v<N; v++) {
				min = Double.MAX_VALUE;
				minVertex = 0;
				for(int i = 0; i<N; i++) {
					if(!isInMst[i] && min > minEdge[i]) {
						min = minEdge[i];
						minVertex = i;
					}
				}
				answer += min;
				isInMst[minVertex] = true;
				long x1 = islands[minVertex][0];
				long y1 = islands[minVertex][1];
				for (int i = 0; i < N; i++) {
					if(!isInMst[i]) {
						long x2 = islands[i][0];
						long y2 = islands[i][1];
						double e = ((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2))*E;
						if(e < minEdge[i]) {
							minEdge[i] = e;
						}
					}
				}
			}
			System.out.println("#" + t+" " + (long)(answer+0.5));
		}

	}

}
