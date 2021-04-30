package DevMatching다단계;

import java.util.*;

public class Solution {
	Map<String, String> parentMap;
	Map<String, Integer> sumMap;
	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		int len = referral.length;
		int[] answer = new int[len];
        parentMap = new HashMap<>();
        sumMap = new HashMap<>();
        
        for(int i = 0; i<len; i++) {
        	String parent = referral[i];
        	String child = enroll[i];
        	parentMap.put(child, parent);
        }
        
        for(int i = 0; i<seller.length; i++) {
        	String s = seller[i];
        	int sell = amount[i];
        	selling(s, sell*100);
        }
        int i = 0;
        for(String s : enroll) {
        	answer[i++] = sumMap.getOrDefault(s, 0);
        }
        
        return answer;
    }
	
	private void selling(String person, int amount) {
		if("-".equals(person)) {
			return;
		}
		
		int money = amount - amount/10;
		sumMap.put(person, sumMap.getOrDefault(person, 0)+money);
		selling(parentMap.get(person), amount/10);
	}
	
	public static void main(String[] args){
		Solution sol = new Solution();
		String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
		String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
		String[] seller = {"young", "john", "tod", "emily", "mary"};
		int[] amount = {12, 4, 2, 5, 10};
		System.out.println(Arrays.toString(sol.solution(enroll, referral, seller, amount)));
	}

}
