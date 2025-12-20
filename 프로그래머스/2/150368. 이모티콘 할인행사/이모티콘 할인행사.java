/**
이모티콘 플러스 가입자 수를 늘리려 함
1. 이모티콘 플러스 가입자를 최대한 늘리는 것
2. 이모티콘 판매액을 최대한 늘리는 것

n 명의 카카오톡 사용자들에게 m개를 할인하여 판매
할인율은 10, 20, 30, 40

사용자들
1. 자신의 기준에 따라 일정 비율 이상 할인하는 이모티콘을 모두 구매
2. 구매 비용의 합이 일정 가격 이상이 된다면, 이모티콘 구매 취소하고 플러스에 가입

user : 비율, 가격 -> 비율 % 할인하는 이모티콘 모두 구매, 가격을 넘으면 플러스로 전환
emoticons : 이모티콘 가격

이모티콘 플러스 가입자 수가 최대일 때, 이모티콘 매출액 return

풀이법
emoticons 를 각각 10, 20, 30, 40 을 선택해서 할인율 정하기, 정한 할인율 int 배열 저장
-> 다 정하면 users 마다 할인율에 해당하는 이모티콘 구매
-> 가격 넘으면 플러스 + 1
-> 플러스 서비스가 최대일 때 갱신

*/
import java.util.*;
class Solution {
    static int[] discount = {10, 20, 30 ,40};
    static int[] selectedDiscount;
    static int N,M;
    static int[][] users;
    static int[] emoticons;
    static int maxSub, maxPrice;
    
    // emoticons 의 할인율 정하기
    static void dfs(int idx) {        
        // 종료
        if(idx == M) {       
            //System.out.println(Arrays.toString(selectedDiscount));
            // 이모티콘 체크
            int price = 0; // 현재 할인율 조합에서 구매 비용
            int subscribers = 0; // 현재 할인율 조합에서 가입자 수
            for(int i = 0; i < N; i++) {
                int targetDiscount = users[i][0];
                int targetPrice = users[i][1];
                
                int curPrice = 0;
                int curSubscribers = 0;
                for(int j = 0; j < M; j++) {
                    
                    if(selectedDiscount[j] >= targetDiscount) { 
                        curPrice += emoticons[j] * (1 - selectedDiscount[j] * 0.01);
                        //System.out.println(emoticons[j] * (1 - targetDiscount * 0.01));
                        
                        if(curPrice >= targetPrice) {
                            curSubscribers++; // 구독자 수 증가
                            curPrice = 0; // 구매 취소
                            break; // 뒤는 볼필요 없음
                        }
                    }
                }
                price += curPrice;
                subscribers += curSubscribers;
            }
            
            // 최대 구독자일 때 갱신
            if(maxSub <= subscribers) {
                if(maxSub == subscribers) maxPrice = Math.max(price, maxPrice); // 같으면 더 큰 값으로
                else maxPrice = price; // 아니면 덮어쓰기
                maxSub = subscribers;
            }          
            return;
        }
        
        for(int i = 0; i < 4; i++) {
            selectedDiscount[idx] = discount[i];
            dfs(idx + 1);
        }
    }
    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        N = users.length;
        M = emoticons.length;
        selectedDiscount = new int[M];
        maxSub = 0; 
        maxPrice = 0;       
        
        dfs(0);
        
        int[] answer = new int[2];
        answer[0] = maxSub;
        answer[1] = maxPrice;
        return answer;
    }
}