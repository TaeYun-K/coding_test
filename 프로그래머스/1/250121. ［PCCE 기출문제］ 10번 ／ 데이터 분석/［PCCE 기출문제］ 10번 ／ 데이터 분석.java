/**
데이터를 분석하자

data : 코드 번호, 제조일, 최대수량, 현재수량

제조일이 20300501 이전인 물건들을 현재 수량이 적은 순서로 정렬

ext : 어떤 정보를 기준으로 뽑아낼지를 의미하는 문자열
val_ext : 정보의 기준값
sort_by : 정보를 정렬할 기준 -> 오름차순

기준 : code, date, maximum, remain
*/
import java.util.*;
class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        
        ArrayList<int[]> list = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("code", 0);
        map.put("date", 1);
        map.put("maximum", 2);
        map.put("remain", 3);
        
        int targetIdx = map.get(ext);
        int sortIdx = map.get(sort_by);
        Arrays.sort(data, (a,b) -> a[sortIdx] - b[sortIdx]); // 미리 정렬
        
        int N = data.length;
        int M = data[0].length;
        
        for(int i = 0; i < N; i++) {
            if(data[i][targetIdx] < val_ext) { // 더작은 데이터만 뽑기
                list.add(data[i]);
            }
        }
        int size = list.size();
        //System.out.println(size);
        int[][] answer = new int[size][4];
        for(int i = 0; i < size; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}