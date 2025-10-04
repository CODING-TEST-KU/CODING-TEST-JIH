package org.example.week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1300 {
    /*
    25/10/3 18:11 ~

    https://www.acmicpc.net/problem/1300

N = 3
1 2 3
2 4 6
3 6 9


B[]
1 : 1
2 : 2
3 : 2
4 : 3
5 : 3
6 : 4


N log N 이하의 알고리즘이 필요함

t로 나누었을 때 몫이 0이면 t보다 작은 값임

6은 9 ~ 10번째 -> 6이하인 수가 10개
8은 11 ~ 12번째 ->  8이하인 수가 12개

7같은 경우는 여기 없는 수인데 어떻게 아는가?
7이하인 수를 계산하면서 7을 못찾으면 이건 없는 수임

 a < k <= b 인 b의 하한을 찾아야 한다?
    */

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, K;
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        int lo = 0;
        int hi = 1000000001; // 오버 플로우 주의
        while(lo + 1 < hi){
            int mid = (lo + hi) / 2;
            if(count(mid, N) >= K){
                hi = mid;
            }
            else{
                lo = mid;
            }
        }
        System.out.println(hi);
    }

    private static int count(int number, int N){
        int count = 0;
        for(int i = 1; i <= N; i++) {
            count += Math.min(N, number / i); // 개수를 계산하는 방법의 최적화 + 범위 예외 처리
        }
        return count;
    }
}