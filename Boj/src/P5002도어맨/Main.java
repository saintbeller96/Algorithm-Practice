package P5002도어맨;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int X;
	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		X = Integer.parseInt(br.readLine());
		if(X == 0) {
			System.out.println(0);
			return;
		}
		char[] p = br.readLine().toCharArray();
		ArrayList<Integer> list = new ArrayList<>();
		for(char c : p) {
			if(c == 'M') list.add(0);
			else list.add(1);
		}
		int[] num = new int[2];
		while(list.size() != 0) {
			int n = list.get(0);
			if(Math.abs(num[0] - num[1]) == X) {
				if(num[n] > num[n^1]) {
					if(list.size() > 1) {
						int k = list.get(1);
						if(k == n) {
							break;
						}else {
							num[k]++;
							list.remove(1);
						}
					}else {
						break;
					}
				}else {
					num[n]++;
					list.remove(0);
				}
			}else{
				list.remove(0);
				num[n]++;
			}
		}
		
		System.out.println(num[0] + num[1]);
	}
}
