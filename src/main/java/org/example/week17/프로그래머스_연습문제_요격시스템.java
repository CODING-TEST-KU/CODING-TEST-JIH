package org.example.week17;

import java.util.*;

class 프로그래머스_연습문제_요격시스템 {
    /*
    11/23 00:07 ~ 00:41
    
    필요한 미사일의 최소 갯수를 구하자

    미사일을 시작순, 끝순으로 정렬?
    제일 먼저 끝나는 앞 미사일이 끝나기 전에 다른 미사일이 시작되면 그 미사일은 같이 요격할 수 있음
    그렇지 않으면 다른 미사일을 요격하기 위해서 미사일이 추가로 필요함
    
    (1, 4)
    (3, 7) 1
    (4, 5) 2
    (4, 8) 
    (5, 12) 3 
    (10,14) 
    (11,13) 
    
    실패함. 이유는 끝순으로 먼저 정렬해야함!
    */
    public int solution(int[][] targets) {
        Arrays.sort(targets, (a, b) -> {
            if(a[1] - b[1] == 0){
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        
        // for(int i = 0; i < targets.length; i++)
        //     System.out.print(Arrays.toString(targets[i]));

        int answer = 1;
        int[] cur = targets[0];
        for(int i = 1; i < targets.length; i++){
            if(cur[1] <= targets[i][0]){
                answer++;
                cur = targets[i];
            }
        }
        return answer;
    }
}