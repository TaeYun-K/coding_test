/**
각 칸마다 색이 칠해진 2차원 격자 보드판이 있다
한 칸을 골랐을 때, 4방 중 같은 색깔로 칠해진 칸의 갯수

board : 각 칸에 칠해진 색깔이 이름이 담긴 리스트
h, w : 칸의 위치
*/
class Solution {
    public int solution(String[][] board, int h, int w) {

        int[] dr = {0,0,-1,1};
        int[] dc = {1,-1,0,0};
        
        int N = board.length;
        int M = board[0].length;
        
        String target = board[h][w];
        int cnt = 0;
        for(int d = 0; d < 4; d++) {
            int nr = h + dr[d];
            int nc = w + dc[d];
            
            if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
            
            String nextColor = board[nr][nc];
            if(target.equals(nextColor)) cnt++;
        }
        
        return cnt;
    }
}