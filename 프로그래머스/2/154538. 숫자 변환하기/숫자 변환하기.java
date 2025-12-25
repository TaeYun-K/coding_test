/**
x -> y
1. x * n
2. x * 2
3. x * 3

dp[i] : i 가 되기 위한 최소 연산 횟수

*/
import java.util.*;
class Solution {
    public int solution(int x, int y, int n) {
        int answer = 0;
        
        int[] dp = new int[y+1];
        
        for(int i = x + 1; i <= y; i++) dp[i] = Integer.MAX_VALUE;

        for(int i = x; i <= y; i++) {
            
            if(dp[i] == Integer.MAX_VALUE) continue; // 도달할 수 없는 값은 연산 x
            
            if(i + n <= y) {
                dp[i + n] = Math.min(dp[i + n], dp[i] + 1);
            }
            
            if(i * 2 <= y) {
                dp[i * 2] = Math.min(dp[i * 2], dp[i] + 1);
            }    
            
            if(i * 3 <= y) {
                dp[i * 3] = Math.min(dp[i * 3], dp[i] + 1);
            }            
        }
        //System.out.println(Arrays.toString(dp));
        return dp[y] == Integer.MAX_VALUE ? -1 : dp[y];
    }
}