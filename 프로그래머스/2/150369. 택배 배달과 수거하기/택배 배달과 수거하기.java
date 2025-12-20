/**
일렬로 나열된 n 개의 집에 배달

배달할 물건은 모두 크기가 같은 택배 상자, 
배달을 다니면서 빈 재활용 택배 상자들을 수거

i 번째 집은 물류 창고에서 i 만큼 떨어져 있음
i 번째 집은 j 번째 집과 j - i 만큼 떨어져 있음

cap : 트럭에 실을 수 있는 택배 상자

트럭은 배달할 재활용 택배 상자들을 실어 물류창고에서 출발,
빈 재활용 택배 상자들을 수거해 물류 창고에 내림

모든 배달과 수거를 마치고 물류창고까지 돌아오는 최소 이동 거리!

풀이법
가장 멀리 있는 곳 -> 가까운 것 순으로
1. 거리 안에 있는 배달할 상자 갯수 확인하면서 차감 -> cap 보다 커지면 종료
2. 거리 안에 있는 수거할 상자 갯수 확인하면서 차감 -> cap 보다 커지면 종료

배달&수거할 상자 있는 가장 먼 지점 부터 다시 확인


새풀이법
현재 거리에 얼마나 왕복해야 하는지?
times = ( (box + 1) / cap ) + 1 

*/
import java.util.*;
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        long dBox = 0;
        long pBox = 0;
        // 끝에서부터 보면서 필요한 box를 누적해서 더함
        for(int i = n-1; i >= 0; i--) {
            dBox += deliveries[i];
            pBox += pickups[i];
            
            if(dBox == 0 && pBox == 0) continue; // 배달할 박스가 없을 때
            
            long dTimes = (dBox + cap - 1) / cap;  // 왕복 횟수
            long pTimes = (pBox + cap - 1) / cap;  // 나머지가 있으면 올림
            
            long maxTimes = Math.max(dTimes, pTimes);
            answer += (i + 1) * maxTimes * 2L;
            
            dBox -= cap * maxTimes;
            pBox -= cap * maxTimes;
        }
        
        return answer;
    }
}