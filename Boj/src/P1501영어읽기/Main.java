package P1501영어읽기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

public class Main {
	static int N, M;
	static String[] words;
	static String[] strings;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Map<String, Integer> dict = new HashMap<>();
		for(int k = 0; k<N; k++) {
			String word = br.readLine();
			String key = "";
			String value = "";
			Map<Character, Integer> count = new TreeMap<>();
			for(int i = 0; i<word.length(); i++) {
				char c = word.charAt(i);
				if(i ==0 || (i == word.length()-1 && word.length()>1)) {
					key += c;
				}else {
					count.put(c, count.getOrDefault(c, 0)+1);
				}
			}
			for(Entry<Character, Integer> e : count.entrySet()) {
				value += e.getKey().toString() + e.getValue().toString();
			}
			key  = key + value;
			dict.put(key, dict.getOrDefault(key, 0)+1);
		}
		
		M = Integer.parseInt(br.readLine());
		for(int k = 0; k<M; k++) {
			String str = br.readLine();
			String[] words = str.split(" ");
			int answer = 1;
			for(String word: words) {
				String key = "";
				String value = "";
				Map<Character, Integer> count = new TreeMap<>();
				for(int i = 0; i<word.length(); i++) {
					char c = word.charAt(i);
					if(i ==0 || i==word.length()-1) {
						key += c;
					}else {
						count.put(c, count.getOrDefault(c, 0)+1);
					}
				}
				for(Entry<Character, Integer> e : count.entrySet()) {
					value += e.getKey().toString() + e.getValue().toString();
				}
				key += value;
				answer *= dict.getOrDefault(key, 0); 
			}
			System.out.println(answer);
		}
	}
}
