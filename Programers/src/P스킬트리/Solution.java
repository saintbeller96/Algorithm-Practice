package P스킬트리;

import java.util.*;
import java.util.Map.Entry;

public class Solution {
	
	public int solution(String skill, String[] skill_trees) {
        int answer = 0;
       Set<String> set = new TreeSet<>();
       
       for(int i = 0; i<skill.length(); i++) {
    	   String str = skill.substring(0, i+1);
    	   set.add(str);
       }
       for(String skill_tree: skill_trees) {
    	   String temp = "";
        	for(char s : skill_tree.toCharArray()) {
        		if(skill.contains(s+"")) {
        			temp += s;
        		}
        	}
        	if("".equals(temp) || set.contains(temp)) {
        		answer++;
        	}
       }
       return answer;
    }
	
	public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		String skill = "CBD";
		String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
		System.out.println((s.solution(skill, skill_trees)));
	}
}
