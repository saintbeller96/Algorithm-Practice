package P15723n단논법_플로이드워셜;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] matrix = new int[26][26];
		init(matrix);
		for(int i = 0; i<n; i++){
			String[] tokens = br.readLine().split(" ");
			int a = tokens[0].charAt(0) -'a';
			int b = tokens[2].charAt(0) - 'a';
			matrix[a][b] = 1;
		}
		floydWarshall(matrix);
		int m = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();
		for(int i = 0; i<m; i++){
			String[] tokens = br.readLine().split(" ");
			int a = tokens[0].charAt(0) -'a';
			int b = tokens[2].charAt(0) - 'a';
			if(matrix[a][b] != Integer.MAX_VALUE/3) answer.append('T').append('\n');
			else answer.append('F').append('\n');
		}
		System.out.println(answer);
	}
	static void floydWarshall(int[][] dist){
		for (int k = 0; k < 26; k++) {
			//k는 거쳐가는 곳
			for(int i = 0; i<26; i++){
				for(int j = 0; j<26; j++){
					if(dist[i][k] + dist[k][j] < dist[i][j]){
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
	}
	static void init(int[][] matrix) {
		for(int i = 0; i<26; i++){
			for(int j = 0; j<26; j++){
				matrix[i][j] = Integer.MAX_VALUE/3;
			}
		}
	}

}
