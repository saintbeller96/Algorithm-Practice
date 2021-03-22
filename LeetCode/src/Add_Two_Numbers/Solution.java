package Add_Two_Numbers;

import java.util.*;

public class Solution {
	
	static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
		@Override
		public String toString() {
			return "[" + val + ", " + next + "]";
		}
		  
	}
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<Integer> numbers = new ArrayList<>();
        int added = 0;
        while(l1 != null && l2 != null){
            int sum = added + l1.val + l2.val;
            if(sum >= 10){
                sum = sum%10;
                added = 1;
            }else added = 0;
            numbers.add(sum);
            l1 = l1.next;
            l2 = l2.next;
        }
        
        if(l1 == null) {
        	while(l2 != null) {
        		int sum = l2.val + added;
        		if(sum >= 10) {
        			sum = sum%10;
        			added = 1;
        		}else {
        			added = 0;
        		}
        		numbers.add(sum);
        		l2 = l2.next;
        	}
        }
        if(l2 == null) {
        	while(l1 != null) {
        		int sum = l1.val + added;
        		if(sum >= 10) {
        			sum = sum%10;
        			added = 1;
        		}else {
        			added = 0;
        		}
        		numbers.add(sum);
        		l1 = l1.next;
        	}
        }
        
        ListNode answer = new ListNode(numbers.get(0));
        ListNode prev = answer;
        for(int i = 1; i<numbers.size(); i++) {
        	ListNode next = new ListNode(numbers.get(i));
        	prev.next = next;
        	prev = next;
        }
        return answer;
    }

	public static void main(String[] args) {
		Solution s = new Solution();
		ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
		ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
		System.out.println(s.addTwoNumbers(l1, l2));
	}

}
