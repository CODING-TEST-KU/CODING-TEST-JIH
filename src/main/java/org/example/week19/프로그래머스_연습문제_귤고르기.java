package org.example.week19;

import java.util.*;

class 프로그래머스_연습문제_귤고르기 {
    /*
    25/12/7 16:45 ~ 16:55
    
    k개의 수를 선택할 때, 종류를 최소화할 때 종류의 개수를 구하자
    
    종류를 가장 적게 하려면? 
    개수가 가장 많은 종류부터 선택하면 되지 않나
    */
    public int solution(int k, int[] tangerine) {
        // 각 종류의 개수를 집계
        Map<Integer, Integer> counter = new HashMap<>();
        for(int t : tangerine){
            counter.put(t, counter.getOrDefault(t, 0) + 1);
        }
        
        // 개수별로 종류를 내림차순 정렬
        PriorityQueue<int[]> qu = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for(int key : counter.keySet())
            qu.offer(new int[] { key, counter.get(key) });
        
        // System.out.println(list.get(0)[1]);
        
        // 개수 많은 종류부터 선택 k개 될때까지
        int answer = 0;
        int sum = 0;
        while(sum < k){
            sum += qu.poll()[1];
            answer += 1; 
        }
        
        // 사용된 종류 반환
        return answer;
    }
}