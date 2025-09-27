package org.example.week11;

import java.util.*;

class PROG_스택큐_기능개발 {
    /*
    25/9/25 17:10 ~ 
    
    앞의 작업이 끝나야 뒤에 있는 걸 배포할 수 있다

    리스트를 배열롤 변환하려면 다음 식을 사용해야 한다!
    answer.stream().mapToInt(i -> i).toArray();
    */
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        int cur = 0;
        int todayCompleted = 0;
        for(int day = 0; day < 100; day++){
            while(cur < progresses.length && progresses[cur] >= 100){
                cur++;
                todayCompleted++;
            }
            if(todayCompleted > 0){
                answer.add(todayCompleted);
                todayCompleted = 0;
            }
            if(cur >= progresses.length){
                break;
            }
            for(int j = 0; j < progresses.length; j++){
                if(j >= cur) progresses[j] += speeds[j];
            }
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}