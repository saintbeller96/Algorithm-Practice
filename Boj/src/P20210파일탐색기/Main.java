package P20210파일탐색기;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static String[] strings;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		strings = new String[N];
		for(int i = 0; i<N; i++) {
			strings[i] = br.readLine();		
		}
		Arrays.sort(strings, (a, b)->compare(a, b));
		for(String s : strings) {
			System.out.println(s);
		}
		return;
	}
 	
 	static int compare(String s1, String s2) {
 		int N = s1.length();
 		int M = s2.length();
 		if(N == 0 && M == 0) return 0;
 		else if(N==0) return -1;
 		else if(M == 0) return 1;
 		
 		int i = 0, j =0;
 		
 		while(i<N && j<M) {
 			char a = s1.charAt(i);
 			char b = s2.charAt(j);
 			
 			//둘다 알파벳인 경우
 			if(Character.isAlphabetic(a) && Character.isAlphabetic(b)) {
 				if(a == b) {
 	 				i++;
 	 				j++;
 	 			}else {
 	 				return Character.compare(a, b);
 	 			}
 			}
 			//둘다 숫자인 경우
 			else if(Character.isDigit(a) && Character.isDigit(b)) {
 				StringBuilder sb1 = new StringBuilder();
 				while(i<N) {
 					if(!Character.isDigit(s1.charAt(i))) break;
 					sb1.append(s1.charAt(i));
 					i++;
 				}
 				StringBuilder sb2 = new StringBuilder();
 				while(j<M) {
 					if(!Character.isDigit(s2.charAt(j))) break;
 					sb2.append(s2.charAt(j));
 					j++;
 				}
 				String ns1 = sb1.toString();
 				String ns2 = sb2.toString();
 				int ni = 0, nj = 0;
 				int c1 = 0;
 				while(ni<ns1.length()) {
 					if(ns1.charAt(ni++) != '0') break; 
 					c1++;
 				}
 				int c2 = 0;
 				while(nj<ns2.length()) {
 					if(ns2.charAt(nj++) != '0') break; 
 					c2++;
 				} 				
 				ni--;
 				nj--;
 				
 				//숫자 비교
 				while(ni<ns1.length() && nj<ns2.length()) {
 					if(ns1.charAt(ni) == ns2.charAt(nj)) {
 						ni++;
 						nj++;
 					}else {
 						return (ns1.length()-ni) - (ns2.length()-nj);
 					}
 				}
 				
 				if(ni == ns1.length() && nj == ns2.length()) {
 					if(c1 > c2) return 1;
 	 				else if(c1 < c2) return -1;
 				}
 				//1이 더 작음
 				else if(ni == ns1.length()) {
 					return -1;
 				}
 				//2가 더 작음
 				else {
 					return 1;
 				}
 			}
 			//둘이 다른 경우
 			else {
 				if(Character.isDigit(a) && !Character.isDigit(b)) return -1;
 	 			if(Character.isDigit(b) && !Character.isDigit(a)) return 1;
 			}
 		}
 		
 		if(i == N && j == M) return 0;
 		if(i<N) return 1;
 		else return -1;
 	}
}
