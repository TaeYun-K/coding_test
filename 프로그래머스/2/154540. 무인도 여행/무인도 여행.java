/**
격자의 각 칸에는 'X' or 1~9
X : 바다
숫자 : 무인도, 식량

상하좌우로 연결된 땅은 하나의 무인도

무인도에서 최대 며칠동안 머물 수 있는가?
각 섬에서 최대 며칠씩 머무를 수 있는지?

풀이법
Flood fill

*/
import java.util.*;

class Solution {
    ArrayList<Integer> list;
    static int N,M;
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {0,0,-1,1};
    static int[] dc = {1,-1,0,0};
    
    static int bfs(int startR, int startC) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {startR, startC});
        visited[startR][startC] = true;
        
        int days = map[startR][startC] - '0'; // 머물 수 있는 기간
    
        while(!q.isEmpty()) {
            int[] coor = q.poll();
            int r = coor[0];
            int c = coor[1];
            
            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                // 맵 밖
                if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue; 
                
                // 섬이 아니거나 맵 밖인 경우
                if(map[nr][nc] == 'X' || visited[nr][nc]) continue;
                
                visited[nr][nc] = true;
                days += map[nr][nc] - '0';
                q.offer(new int[] {nr, nc});               
            }
        }
        return days; // 생존일 수 return
    }
    
    public int[] solution(String[] maps) {
        list = new ArrayList<>(); // 정렬 전 며칠씩 머물 수 있는지 정보
        N = maps.length;
        M = maps[0].length();
        map = new char[N][M];
        visited = new boolean[N][M];
        
        for(int i = 0; i < N; i++) {
            map[i] = maps[i].toCharArray();
        }
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] != 'X' && !visited[i][j]) {
                    int days = bfs(i,j);
                    list.add(days);
                }
            }
        }
        
        int size = list.size();
        
        if(size == 0) return new int[] {-1};
        
        int[] answer = new int[size];
        Collections.sort(list);
        for(int i = 0; i < size; i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}