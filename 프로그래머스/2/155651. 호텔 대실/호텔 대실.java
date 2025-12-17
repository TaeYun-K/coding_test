/**
최소한의 객실만 사용해서 손님 받기
한번 사용한 객실은 퇴실 시간 + 10 분 후 사용 가능

book_time : 예약 시간

풀이법
끝좌표 정렬 -> 만약 겹친다! -> 새로운 방을 줘야함
*/
import java.util.*;
class Solution {

    public int solution(String[][] book_time) {
        int answer = 0;
        int N = book_time.length;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 끝좌표 기준 정렬
        Arrays.sort(book_time, (a,b) -> a[0].compareTo(b[0]));
        
        for(int i = 0; i < N; i++) {
            String[] startTime = book_time[i][0].split(":");
            int startMin = Integer.parseInt(startTime[0]) * 60 + Integer.parseInt(startTime[1]);
            
            String[] endTime = book_time[i][1].split(":");
            int endMin = Integer.parseInt(endTime[0]) * 60 + Integer.parseInt(endTime[1]);

            // 방을 추가해야할 때
            if(pq.isEmpty()) {
                pq.offer(endMin);
                answer++;
            }
            
            // 가장 빨리 나오는 방이 찼을 때
            else if(pq.peek() + 10 > startMin) {
                answer++;
                pq.offer(endMin); // 다음 방 추가
            }
            
            // 새 방 줄 수 있을 때
            else {
                pq.poll(); // 넌 방빼
                pq.offer(endMin); // 다음 방 추가
            }
            
            
        } 
        
        return answer;
    }
}