/**
각 칸은 통로 or 벽
통로로 된 칸만 이동할 수 있음

통로 중에서는 미로를 빠져나가는 문이 있는데, 레버를 당겨야 열 수 있다.
레즉, 레버 먼저 간 후에, 미로를 빠져나가는 문으로 가야함

출구를 지나갈 수 있지만 레버 안당기면 탈출은 못함

각 칸은 여러번 지나갈 수 있다.

풀이법
bfs
레버 발견 -> boolean true
출구 발견 -> boolean true 체크 -> 탈출
*/
import java.util.*;
class Solution {
    static char[][] map;
    static int N, M;
    static int[] dr = {0,0,-1,1};
    static int[] dc = {1,-1,0,0};
    static boolean[][] visited; 
    static int startR, startC; // 시작 위치
    
    static class Node {
        int r;
        int c;
        int time;
        
        Node(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }       
    }
    
    static int bfs(int R, int C, char goal) {
        Queue<Node> q = new LinkedList<>();
        visited[R][C] = true;
        q.offer(new Node(R, C, 0));
        
        while(!q.isEmpty()) {
            Node node = q.poll();
            int r = node.r;
            int c = node.c;
            int time = node.time;
            
            // 목표 지점 도착 !
            if(map[r][c] == goal) {
                startR = r; // 시작 위치 레버로 이동
                startC = c;
                return time;
            }
            
            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                // 벽이나 맵을 벗어날 때
                if(nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == 'X') continue;
            
                // 이미 방문한 곳일 때
                if(visited[nr][nc]) continue;
                visited[nr][nc] = true;                         
                q.offer(new Node(nr, nc, time + 1));
            }
        }
        return -1;
    }
    public int solution(String[] maps) {
        int answer = 0;
        N = maps.length;
        M = maps[0].length();
        map = new char[N][M];
        visited = new boolean[N][M];
        
        startR = 0;
        startC = 0;
              
        for(int i = 0; i < N; i++) {
            map[i] = maps[i].toCharArray();
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 'S') {
                    startR = i;
                    startC = j;
                }
            }
        }
        
        // 레버까지의 최단 경로 찾기
        int a = bfs(startR, startC, 'L');
        if(a == -1) return -1;
        
        // 출구까지의 최단 경로 찾기
        visited = new boolean[N][M];
        int b = bfs(startR, startC, 'E');    
        if(b == -1) return -1;
        
        return a + b;
    }
}