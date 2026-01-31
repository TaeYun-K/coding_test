
/**
빵 : 1
야채 : 2
고기 : 3
*/
import java.util.*;
class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        LinkedList<Integer> stk = new LinkedList<>();
        int N = ingredient.length;
        
        if(N < 3) return 0;
        
        stk.add(ingredient[0]);
        stk.add(ingredient[1]);
        stk.add(ingredient[2]);
        
        for(int i = 3; i < N; i++) {
            int S = stk.size();
            if(ingredient[i] == 1 && S >= 3) {
                if(stk.get(S - 1) == 3 && stk.get(S - 2) == 2 && stk.get(S - 3) == 1) {
                    answer++;
                    for(int k = 0; k < 3; k++) stk.removeLast();
                }
                else stk.add(1);
            }
            else stk.add(ingredient[i]);
        }    
        
        return answer;
    }
}