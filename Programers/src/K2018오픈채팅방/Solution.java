package K2018오픈채팅방;

import java.util.*;

public class Solution {
	public String[] solution(String[] record) {
        List<String[]> result = new ArrayList<>();
        Map<String, String> uidNickname = new HashMap<>();
        
        for(String line : record) {
        	String[] str = line.split(" ");
        	String op = str[0];
        	String uid = str[1];
        	
        	String nickname ="";
        	if(str.length == 3) nickname = str[2];
        	
        	if("Enter".equals(op)) {
        		uidNickname.put(uid, nickname);
        		result.add(new String[] {uid, "님이 들어왔습니다."});
        	}else if("Leave".equals(op)) {
        		result.add(new String[] {uid, "님이 나갔습니다."});
        	}else {
        		uidNickname.put(uid, nickname);
        	}
        }
        String[] answer = new String[result.size()];
        int i = 0;
        for(String[] s : result) {
        	answer[i++] = uidNickname.get(s[0]) + s[1];
        }
        
        return answer;
    }
	
	
	public static void main(String[] args){
		Solution sol = new Solution();
		String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		System.out.println(sol.solution(record));

	}

}
