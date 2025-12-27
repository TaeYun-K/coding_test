/**
마법의 엘리베이터
-1, +1, -10, +10 .. 10의 배수인 양/음 정수들을 더한 층으로 이동

0층 이하로는 안내려가요

16 -> 1, 6 -> 7개 가능
but 2, -4 -> 6개로 가능

최소한의 버튼을 누르는 법

위에서 내려오는 게 나은지
아래에서 올라가는게 나은지 

2 + 5 + 6 + 6 -> 19

2 + 5 + 5 + 4 -> 16

0이 아닌 제일 마지막 자리 수가 5 이상이면 -> 위에서 오는 게 더 빠름
or 그냥 자릿 수 다 더하기
*/
import java.util.*;
class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while(true) {
            if(storey == 0) break;
            
            int num = storey % 10;
            
            // 정직하게 더하는게 이득
            if(num < 5) {
                answer += num;
                storey /= 10;
            }
            
            // 위에서 내려오는게 이득
            else if(num > 5) {
                answer += 10 - num; // 위에서 내려옴
                storey = storey / 10 + 1; // 다음 자리 더해줌
            }
            
            else if(num == 5) {
                int nextNum = (storey / 10) % 10;
                if(nextNum >= 5) {
                    answer += 10 - num; // 다음 자리가 5보다 크면 위에서 내려오는 게 이득
                    storey = storey / 10 + 1;
                }
                else {
                    answer += num;
                    storey /= 10;
                }
                
            }           
        }
        
        //System.out.println(list.toString());
        return answer;
    }
}