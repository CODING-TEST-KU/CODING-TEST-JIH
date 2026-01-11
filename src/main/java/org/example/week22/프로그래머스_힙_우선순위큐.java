package org.example.week22;

import java.util.*;

class 프로그래머스_힙_우선순위큐 {
    /*
    26/1/8 23:05 ~ 
    
    최댓값과 최솟값의 쌍을 반환한다

    우선순위 큐의 사용법 공부 필요
    */
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int i = 0;
        for(String op : operations){
            String[] tokens = op.split(" ");
            if(tokens[0].equals("I")){
                int num = Integer.parseInt(tokens[1]);
                maxHeap.offer(num);
                minHeap.offer(num);
                i++;
            }
            if(tokens[0].equals("D")){
                if(maxHeap.isEmpty()){
                    continue;
                }
                if(tokens[1].equals("1")){
                    int poll = maxHeap.poll();
                    minHeap.remove(poll); // 주의
                }
                else{
                    int poll = minHeap.poll();
                    maxHeap.remove(poll); // 주의
                }
            }
        }
        if(!maxHeap.isEmpty()) {
            answer[0] = maxHeap.poll();
            answer[1] = minHeap.poll();
        }
        return answer;
    }
}