package K2018뉴스클러스터링;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Solution {
	public int solution(String str1, String str2) {
        int answer = 0;
        List<String> set1 = new ArrayList<>();
        List<String> set2 = new ArrayList<>();
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        for(int i = 0; i<str1.length()-1; i++) {
        	char c1 = str1.charAt(i);
        	char c2 = str1.charAt(i+1);
        	if(c1 <'a' || c1>'z' || c2 <'a' || c2>'z') continue;
        	String s = String.format("%c%c", c1, c2);
        	set1.add(s);
        }
        for(int i = 0; i<str2.length()-1; i++) {
        	char c1 = str2.charAt(i);
        	char c2 = str2.charAt(i+1);
        	if(c1 <'a' || c1>'z' || c2 <'a' || c2>'z') continue;
        	String s = String.format("%c%c", c1, c2);
        	set2.add(s);
        }
        List<String> tempList = set1.stream().collect(Collectors.toList());
        List<String> unions = new ArrayList<>();
        for(String s : set2) {
        	if(tempList.contains(s)) {
        		tempList.remove(s);
        	}
        	unions.add(s);
        }
        unions.addAll(tempList);
        
        tempList = set1.stream().collect(Collectors.toList());
        List<String> intersections = new ArrayList<>();
        for(String s : set2) {
        	if(tempList.contains(s)) {
        		tempList.remove(s);
        		intersections.add(s);
        	}
        }
        
        if(intersections.size() == 0 && unions.size() == 0) return 65536;
        return (int)(intersections.size()/(double)unions.size()*65536);
    }
	
	
	public static void main(String[] args){
		Solution sol = new Solution();
		String str1 ="aa1+aa2";
		String str2 = "AAAA12";
		
		System.out.println(sol.solution(str1, str2));

	}

}
