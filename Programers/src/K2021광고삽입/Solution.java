package K2021광고삽입;


class Solution {
	long[] arr;
	public String solution(String play_time, String adv_time, String[] logs) {
        int n = parse(play_time);
        int adv = parse(adv_time);
        arr = new long[n+1];
        for(String log : logs) {
        	String[] t = log.split("-");
        	int s = parse(t[0]);
        	int e = parse(t[1]);
        	arr[s]++;
        	arr[e]--;
        }
        
        for(int i = 1; i<n; i++) {
        	arr[i] = arr[i]+arr[i-1];
        }
        for(int i = 1; i<n; i++) {
        	arr[i] = arr[i]+arr[i-1];
        }
        
        long max = arr[adv-1];
        int max_time = 0;
        for(int i = adv; i<n; i++) {
        	if(max < arr[i] - arr[i-adv]) {
        			max = Math.max(max, arr[i] - arr[i-adv]);
            		max_time = i-adv+1;
        	}
        }

        String h = max_time/3600 + "";
        String m = (max_time%3600)/60 + "";
        String s = (max_time%60) + "";
        if(h.length()<2) h = "0" + h;
        if(m.length()<2) m = "0" + m;
        if(s.length()<2) s = "0" + s;
        
        return h+":"+m+":"+s;
    }
	
	private int parse(String time) {
		String[] stk = time.split(":");
		int result = Integer.parseInt(stk[0])*3600 + Integer.parseInt(stk[1])*60 + Integer.parseInt(stk[2]);
		return result;
	}

    
    public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		String play_time = "50:00:00";
		String adv_time = "50:00:00";
		String[] logs = { "15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"};
		System.out.println(s.solution(play_time, adv_time, logs));
	}
}
