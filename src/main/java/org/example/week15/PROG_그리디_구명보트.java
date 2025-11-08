package org.example.week15;

import java.util.*;

class PROG_그리디_구명보트 {
    /*
    필요한 구명보트 수의 최솟값 구하기
    
    N <= 50000
    
    N log N?
    
    사람들을 무게로 정렬한 후에 제한을 넘지 않는 선에서 태우면 될 것 같음
    
    가정이 틀렸음
    
    보트에는 두명밖에 못 탄다!!
    
    그러면 제한에 가깝게 사람들을 태우면 어떨까?
    
    30 50 50 70 80
    
    50 50 70 80
    
    투 포인터!!
    */
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int answer = 0;
        int accumSum = 0;
        int left = 0;
        int right = people.length - 1;
        while(left <= right){
            if(left == right){
                answer++;
                break;
            }
            int sum = people[left] + people[right];
            if(sum <= limit){
                accumSum += sum;
                left++;
                right--;
            }
            else{
                right--;
            }
            answer++;
        }
        return answer;
    }
}