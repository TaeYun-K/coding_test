/**
t 초동안 붕대를 감으면서 1초마다 x 체력 회복
t 초 연속으로 붕대를 감으면 y만큼의 체력을 추가로 회복
최대 체력 존재
1. 1 초마다 + x
2. t 초 연속으로 감으면 + y
3. 최대체력 이상으로는 안커짐

몬스터에게 공격당하면 
1. 연속 성공 시간이 0으로 초기화
2. 피해량만큼 체력 줄어듬
3. 0 이하가 되면 죽음

bandage : 시전 시간, 초당 회복량, 추가 회복량
health : 현재 체력
attacks : 정렬됨, 모두 다름, [공격 시간, 피해량]
*/
import java.util.*;
class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        
        int timeIdx = 0;
        int needTime = bandage[0];
        int healthPerTime = bandage[1];
        int bonusHealth = bandage[2];
        int maxHealth = health;
        
        int attacksIdx = 0;
        int time = 0;
        int successedCnt = 0;
        while(true) {
            int attackTime = attacks[attacksIdx][0];
            int damage = attacks[attacksIdx][1];
            
            // 아직 공격 시간이 아니면
            if(attackTime != time) {
                health += healthPerTime; // 초당 회복량
                successedCnt++; // 연속 회복 성공
                if(successedCnt == needTime) { // 연속 회복 성공
                    health += bonusHealth; //추가 회복
                    successedCnt = 0; // 연속 카운트 초기화 
                }
                
                if(health > maxHealth) health = maxHealth; // 최대 회복량 보다 커질 수 없음   
            }
            
            // 공격 시간일 때
            else {
                successedCnt = 0; // 연속 카운트 초기화
                health -= damage;
                attacksIdx++; // 다음 공격 시간으로 pass
                if(health <= 0) return -1; // 죽음
                if(attacksIdx == attacks.length) break; // 다음 공격이 없으면 종료
            }
            
            //System.out.println("현재 체력 : " + health);
            time++; // 시간 증가
            
        }
        return health;
    }
}