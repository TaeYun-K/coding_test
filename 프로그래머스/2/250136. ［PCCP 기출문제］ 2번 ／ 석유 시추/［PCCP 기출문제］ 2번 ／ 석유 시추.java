/**
n,m 격자 모양의 땅 속에서 석유 발견

시추관을 수직으로 단 하나만 뚫을 수 있음

가장 많은 석유를 뽑을 수 있는 시추관의 위치?

만약 시추관이 일부를 지나면 해당 덩어리에 속한 모든 석유를 뽑을 수 있음

풀이법
1. flood fill로 덩어리에 닿았을 때 뽑을 수 있는 시추관 저장
flood fill 할 때, Idx를 부여해서 기록해서 저장
해당 Idx 에 해당하는 석유량 Map 에 저장

2. 열 하나씩 옮겨가며 가장 많은 시추관 합 저장
찾은 Idx 중복 체크 하면서, 새로운 Idx 나올 때마다 더하기

*/
import java.util.*;
class Solution {
    static int[][] land, idxLand;
    static HashMap<Integer, Integer> map;
    static int[] dr = {0,0,-1,1};
    static int[] dc = {1,-1,0,0};
    static boolean[][] visited;
    static int N,M;
    static int idx;
    
    // 1. flood fill 하면서 Idx 저장
    // 2. Idx 에 해당하는 석유량 Map 에 저장
    static void bfs(int startR, int startC, int idx) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {startR,startC}); // 시작 좌표
        visited[startR][startC] = true;
        idxLand[startR][startC] = idx;
        
        //System.out.println("idx : " + idx);
        int cnt = 1;
        while(!q.isEmpty()) {
            int[] coor = q.poll();
            int r = coor[0];
            int c = coor[1];
                        
            //System.out.println("bfs 중 " + r + " " + c + " " + cnt);
            
            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if(nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc] || land[nr][nc] == 0) continue;  
                
                visited[nr][nc] = true; // 방문 처리
                idxLand[nr][nc] = idx; // idx 기록                
                q.offer(new int[] {nr,nc});
                cnt++;
            }
        }
        map.put(idx, cnt);
    }
    public int solution(int[][] land) {
        int answer = 0;
        this.land = land;
        N = land.length;
        M = land[0].length;
        map = new HashMap<>();
        idxLand = new int[N][M]; // 석유 Idx 를 기록하는 배열
        visited = new boolean[N][M];
        
        // flood fill
        int idx = 1;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!visited[i][j] && land[i][j] == 1) {
                    bfs(i,j,idx); // 현재 idx로 flood fill 해줘
                    idx++;
                }
            }
        }

        // 열 하나씩 돌면서 시추관 꽂아보기
        int maxSum = 0;
        for(int i = 0; i < M; i++) {
            int sum = 0;
            boolean[] founded = new boolean[idx]; // 중복 시추 방지
            for(int j = 0; j < N; j++) {
                int curLandIdx = idxLand[j][i];
                if(land[j][i] == 1 && !founded[curLandIdx]) { // 새로운 석유 발견
                    sum += map.get(curLandIdx);
                    founded[curLandIdx] = true; 
                }                
            }
            maxSum = Math.max(maxSum, sum); // 최댓값 갱신
        }
        
        // for(int i = 0; i < N; i++) {
        //     System.out.println(Arrays.toString(land[i]));
        // }
        
//         System.out.println();    
        
//         for(int i = 0; i < N; i++) {
//             System.out.println(Arrays.toString(idxLand[i]));
//         }
        
        return maxSum;
    }
}