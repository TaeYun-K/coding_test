/**
칸토어 집합은 0~1 부터 시작하여 각 구간을 3등분하여 가운데 구간을 반복적으로 제외

유사 칸토어 비트열
1. 0 번째 유사 칸토어 비트열은 1
2. n 번째 유사 칸토어 비트열은 
n - 1 번째 유사 칸토어 비트열에서 1을 11011로 치환하고 0 을 00000 으로 치환하여 만듬

n 번째 유사 칸토어 비트열에서 특정 구간 내의 1의 개수가 몇개 일까
l ~ r : 구간

그 구간 내의 1의 갯수 return

풀이법
dp[i][j] : i번째 비트열에서 0 ~ j 까지 1의 개수
dp[i-1][j] == 1 -> 
dp[i][j] : dp[i][j-1] + 1
dp[i][j+1] : dp[i][j-1] + 1
dp[i][j+2] : dp[i][j-1] + 1
dp[i][j+3] : dp[i][j-1]
dp[i][j+4] : dp[i][j-1] + 1
dp[i][j+5] : dp[i][j-1] + 1


그냥 구현
1 -> 11011
0 -> 00000
시간초과나요


현재 구간이 범위에 완전히 포함되는가?
완전히 포함되면 pow(4,k) -> 현재 구간에 모든 1 의 개수
부분만 포함되면 5등분 하여 재귀

포함되지 않으면? 종료

*/
import java.util.*;

class Solution {
    static int answer;
    static long l, r;
    
    public int solution(int n, long l, long r) {
        answer = 0;
        this.l = l;
        this.r = r;
        long startIdx = (long) Math.pow(5,n);
        
        dfs(n,1,startIdx); // l
        return answer;
    }
    
    // idx 는 현재 n 번째, 분할정복 시작, 끝 인덱스
    // 시작과 끝 사이에 있는 1 의 갯수 더하고 종료
    static void dfs(int idx, long startIdx, long endIdx) {
        
        
        // 구간 밖일 때 (현재 시작 점이 구간 끝보다 큰 경우, 현재 끝점이 구간 시작점 보다 작은 경우)
        if(startIdx > r || endIdx < l) return; 
        
        // 구간에 완전히 포함될 때
        if(startIdx >= l && endIdx <= r) {
            answer += Math.pow(4,idx);
            return;
        }
        
        // 걸쳐 있을 때
        // 구간 나누기
        long nextLength = (endIdx - startIdx + 1) / 5; // 이전 구간의 길이

        for(int i = 0; i < 5; i++) {
            if(i == 2) continue; // 0은 제외
            long nextStart = startIdx + i * nextLength;
            long nextEnd = nextStart + nextLength - 1;
            dfs(idx - 1, nextStart, nextEnd);
        }
    }
}