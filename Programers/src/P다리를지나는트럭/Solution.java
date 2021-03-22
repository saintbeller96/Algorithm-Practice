package P다리를지나는트럭;

import java.util.*;
import java.util.Map.Entry;

public class Solution {
	
	public Integer[] solution(int[] progresses, int[] speeds) {
        int[] answer;
        int[] time = new int[speeds.length];
        
        for(int i = 0; i<speeds.length; i++){
            time[i] = (int)((100-progresses[i])/(double)speeds[i] + 0.5);
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        int cnt = 1;
        int p = time[0];
        for(int i = 1; i<speeds.length; i++){
            if(p >= time[i]){
                cnt++;
            }else{
                list.add(cnt);
                cnt = 1;
                p = time[i];
            }
        }
        list.add(cnt);
        return list.toArray(new Integer[0]);
    }
	
	public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		int[] progresses = {99, 1, 99, 1};
		int[] speeds = {1, 1, 1, 1};
		System.out.println(Arrays.toString(s.solution(progresses, speeds)));
	}
}
