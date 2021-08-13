package P18116로봇조립_집합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static final int MAX = 1000003;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] set = new int[MAX];
		int[] children = new int[MAX];
		for(int i = 0; i<MAX; i++){
			set[i] = i;
			children[i] = 1;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<N; i++){
			String[] tokens = br.readLine().split(" ");
			String op = tokens[0];
			if(op.equals("I")){
				int a = Integer.parseInt(tokens[1]);
				int b = Integer.parseInt(tokens[2]);
				int x = find(set, a);
				int y = find(set, b);
				if(x != y){
					set[y] = x;
					children[x] += children[y];
					children[y] = 0;
				}
			}
			else if(op.equals("Q")){
				int c = Integer.parseInt(tokens[1]);
				int x = find(set, c);
				sb.append(children[x]).append('\n');
			}
		}
		System.out.println(sb);
	}
	static int find(int[] set, int x){
 		if(x == set[x]) return x;
 		return set[x] = find(set, set[x]);
	}
}
