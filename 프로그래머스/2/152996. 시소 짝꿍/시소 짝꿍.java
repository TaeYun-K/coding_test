/**
시소
중심으로부터 2, 3, 4 지점에 좌석이 하나씩 있다

탑승한 사람의 무게와 좌석 거리의 곱이 양쪽 다 같다면 시소 짝꿍

weights : 사람들의 몸무게
n : 10만

풀이법
최소 공배수가 존재하는가?

1. 3 / 2 
2. 4 / 3
3. 4 / 2


*/
import java.util.*;
class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        int N = weights.length;
        long[] cnts = new long[1001]; // 나온 값들 저장
        Arrays.sort(weights);
        
        for(int i = 0; i < N; i++) cnts[weights[i]]++; // 나온 몸무게 카운트
        
        for(int i = 100; i < 1001; i++) {  
            
            if(cnts[i] == 0) continue;
            
            answer += (cnts[i] * (cnts[i] - 1)) / 2; // nCr 같은 무게끼리
            
            if(i * 3 % 2 == 0) { // 내 짝궁이 정수일 때
                int pair = i * 3 / 2;
                if(pair <= 1000) answer += cnts[i] * cnts[pair];
            }         
            
            if(i * 4 % 2 == 0) {
                int pair = i * 4 / 2;
                if(pair <= 1000) answer += cnts[i] * cnts[pair];
            }

            if(i * 4 % 3 == 0) {
                int pair = i * 4 / 3;
                if(pair <= 1000) answer += cnts[i] * cnts[pair];
            }
        }
        
        return answer;
    }
}