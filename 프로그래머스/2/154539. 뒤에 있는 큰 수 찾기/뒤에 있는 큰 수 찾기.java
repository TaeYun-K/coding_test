/**
스택 : 아직 뒤에 있는 큰 수를 찾지 못한 수들의 인덱스
*/

import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        int N = numbers.length;
        int[] answer = new int[N];
                    
        Deque<Integer> stk = new LinkedList<>();
        stk.addLast(0); // 첫 idx 담고 시작
        
        for(int i = 1; i < N; i++) {            
            int cur = numbers[i];
            
            // 가장 최근 값과 현재 값을 비교
            while(!stk.isEmpty()) {
                int lastIdx = stk.peekLast();
                //System.out.println(numbers[lastIdx]);
                
                if(numbers[lastIdx] < cur) {
                    answer[lastIdx] = cur;
                    stk.pollLast();
                }
                
                else break; // 크면 break;
            }
            stk.addLast(i);
        }
        
        // 여전히 stk 에 남아있는 값들은 -1 들
        while(!stk.isEmpty()) {
            int idx = stk.pollLast();
            answer[idx] = -1;
        }
        
        return answer;
    }
}