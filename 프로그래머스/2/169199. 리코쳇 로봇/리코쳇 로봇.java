/**
시작점에서 bfs 시작
해당 좌표에서 4방으로 장애물 만나는 지 확인
장애물 or 벽만나면 bfs 넣기
방문처리

꺼냈는데 G 면 종료

다 방문해서 Q가 비워지면 도달할 수 없음
*/
import java.util.*;
class Solution {
    static char[][] map;
    static int N,M;
    static boolean[][] visited;  
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,-1,0,1};
    
    static int bfs(int startR, int startC) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {startR, startC, 0});
        visited[startR][startC] = true;
        
        while(!q.isEmpty()) {
            int[] coor = q.poll();
            int r = coor[0];
            int c = coor[1];
            int cnt = coor[2];
            
            if(map[r][c] == 'G') return cnt;
            
            // 4방향으로 장애물 or 벽을 만나는지 체크
            for(int d = 0; d < 4; d++) {
                int nr = r;
                int nc = c;
                    
                while(true) {
                    nr += dr[d];
                    nc += dc[d];
                
                    // 벽이나 장애물 만났을 때
                    if(nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == 'D') {
                        nr -= dr[d];
                        nc -= dc[d]; // 롤백
                        
                        if(visited[nr][nc]) break; // 이미 탐색한 곳이라면 취소
                        visited[nr][nc] = true;
                        q.offer(new int[] {nr, nc, cnt + 1});
                        break;
                    }
                }
            }
        }
        return -1;
    }
    public int solution(String[] board) {
        int answer = 0;
        N = board.length;
        M = board[0].length();
        
        map = new char[N][M];
        visited = new boolean[N][M];
        
        int startR = 0;
        int startC = 0;
        for(int i = 0; i < N; i++) {
            map[i] = board[i].toCharArray();
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 'R') {
                    startR = i;
                    startC = j;
                }
            }
        }             
        
        // for(int i = 0; i < N; i++) {
        //     System.out.println(Arrays.toString(map[i]));
        // }           
        // System.out.println(startR + " " + startC);
        
        return bfs(startR, startC);
    }
}