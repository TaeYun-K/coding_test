/**
어피치가 n발을 다 쏜 후에 라이언이 n 발을 쏜다
쏜 후에 점수를 계산

0~10 중 더 많은 화살을 맞힌 선수가 점수를 가져감

둘다 0 0 이면 점수 안줌

n : 화살의 갯수
info : 어피치가 쏜 화살

풀이법
n 개의 화살을 1~10 중 배치하는 경우의 수 생성
어피치와 비교해서 점수 내기

우승하면 해당 결과 갱신
*/
import java.util.*;
class Solution {
    static int[] info;
    static int N;
    static int[] lions;
    static int maxSum;
    static int[] ansArr;
    
    // idx : 현재 점수, arrows : 화살 갯수
    static void dfs(int idx, int arrows) {

        // 화살을 다쐈거나, 과녁 끝에 왔을 때
        if(arrows == 0 || idx == 11) {
            
            // 만약 화살이 남아있다면 끝에 몰아줘
            if(arrows > 0) lions[10] += arrows;
            
            // 점수 계산 (0 이 10이다)
            int sum = 0; // 라이언의 점수
            for(int i = 0; i <= 10; i++) {
                int apeach = info[i];
                int lion = lions[i];
                
                // 0 이면 계산 X
                if(apeach == 0 && lion == 0) continue; 
                
                // 어피치 승
                if(apeach >= lion) sum -= 10 - i;
                
                // 라이언 승
                else sum += 10 - i;
            }
            
            // 라이언이 이겼고, 더 큰 점수차로 이겼을 때
            if(sum > 0 && maxSum <= sum) {
                // 만약 점수차이가 같으면 더 낮은 점수 맞췄는지 보기
                if(maxSum == sum) {
                    for(int i = 10; i >= 0; i--) {                        
                        if(ansArr[i] < lions[i]) { // 지금 lion 이 낮은 점수를 더 많이 맞췄을 때
                            for(int k = 0; k <= 10; k++) ansArr[k] = lions[k];
                            break; // 더 안봄
                        }
                        else if(ansArr[i] > lions[i]) break; // 갱신할 필요 없음
                    }
                }
                
                // 더 크면 그냥 바로 복사
                else {
                    for(int k = 0; k <= 10; k++) ansArr[k] = lions[k]; // 복사
                }
                maxSum = Math.max(maxSum, sum);
            }
            
            // 계산 끝나고 다시 원상복구 
            if(arrows > 0) lions[10] -= arrows;
            
            return;
        }
        
        // 현재 점수에 쏜다!
        if(arrows > 0) {                                
            lions[idx] += 1;
            dfs(idx, arrows - 1);
            lions[idx] -= 1; // 백트래킹
        }
        
        // 현재 점수 안쏜다
        dfs(idx + 1, arrows);                
    }
    public int[] solution(int n, int[] info) {
        int[] answer = {};
        this.info = info;
        this.N = n;
        
        lions = new int[11];
        maxSum = -1; // 최대 점수 차
        ansArr = new int[11]; // 정답 배열
        
        dfs(0, n);     
        return maxSum == -1 ? new int[]{-1} : ansArr;
    }
}