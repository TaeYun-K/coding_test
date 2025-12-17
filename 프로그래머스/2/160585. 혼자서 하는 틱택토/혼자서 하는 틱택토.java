/**
선공이 O, 후공이 X
가로 세로 대각선으로 3개가 같은 표시가 만들어지면 승리
더이상 표시할 수 없을 경우 무승부

실수
1. O를 표시할 차례인데 X 를 표시하거나 그반대 경우
2. 게임이 종료됐는데도 계속 진행

규칙을 지켜서 나올 수 있는 상황이면 1, or 0

X 가 연속으로 있을 때 -> 0
O를 연속으로 표시하거나, X를 연속으로 표시한 경우를 어떻게 판별?
-> O와 X의 차이가 2 이상이 날 때!

1. map 돌면서 O,X 갯수 새기 -> 차이 비교
2. X가 연속으로 있는지 찾기


O 하나를 놓았을 때, 두 줄 이상이 완성되는 경우도 있음 ..!
따라서 승리 여부를 먼저 체크하고, 
같이 승리하는 건 불가능,

승리한 쪽의 돌 갯수가 정직한지 체크
(게임이 끝났을 때 돌을 더 놓았는지?)

*/
import java.util.*;
class Solution {
    public int solution(String[] board) {
        int answer = -1;
        int oCnt = 0;
        int xCnt = 0;
        
        char[][] map = new char[3][3];
        for(int i = 0; i < 3; i++) {
            String str = board[i];
            map[i] = str.toCharArray();
        }
                
        // O, X 갯수 체크
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(map[i][j] == 'O') oCnt++;
                else if(map[i][j] == 'X') xCnt++;                
            }
        }
        if(xCnt > oCnt) return 0; // 후공이 더 많을 수 없음
        if(oCnt - xCnt > 1) return 0; // X 차례에 O를 칠했을 경우
              
        int oCntChk = 0; 
        int xCntChk = 0;
        boolean oWin = false;
        boolean xWin = false;
        
        // 행 체크
        for(int i = 0; i < 3; i++) {
            oCntChk = 0; 
            xCntChk = 0;
            for(int j = 0; j < 3; j++) {
                if(map[i][j] == 'O') oCntChk++;  
                else if(map[i][j] == 'X') xCntChk++;
            }
            if(oCntChk == 3) oWin = true;
            else if(xCntChk == 3) xWin = true;
        }

        
        // 열 체크
        for(int i = 0; i < 3; i++) {
            oCntChk = 0; 
            xCntChk = 0;
            for(int j = 0; j < 3; j++) {
                if(map[j][i] == 'O') oCntChk++;  
                else if(map[j][i] == 'X') xCntChk++;
            }
            if(oCntChk == 3) oWin = true;
            else if(xCntChk == 3) xWin = true;
        }
        
        // 대각선 체크 
        oCntChk = 0; 
        xCntChk = 0;
        for(int i = 0; i < 3; i++) {
            if(map[i][i] == 'O') oCntChk++;  
            else if(map[i][i] == 'X') xCntChk++;
        }
        if(oCntChk == 3) oWin = true;
        else if(xCntChk == 3) xWin = true;


        // 반대 대각선 체크
        oCntChk = 0; 
        xCntChk = 0;    
        for(int i = 0; i < 3; i++) {
            if(map[2-i][i] == 'O') oCntChk++;  
            else if(map[2-i][i] == 'X') xCntChk++;
        }
        if(oCntChk == 3) oWin = true;
        else if(xCntChk == 3) xWin = true;
    
        // 동시에 이기는 건 불가능    
        if(oWin && xWin) return 0; 
    
        // o 가 이겼을 때
        if(oWin) { 
            if(oCnt == xCnt) return 0; // o가 막타이므로, 갯수가 같으면 안됨
        }
    
        // x 가 이겼을 때
        if(xWin) {
            if(oCnt > xCnt) return 0; // x가 막타이므로, o가 더 많으면 안됨
        }
        
        return 1;
    }
}