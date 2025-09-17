package org.example.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob1182 {
    /*
    25/9/11 20:04 ~
    https://www.acmicpc.net/problem/1182

    완전탐색 복잡도 -> 2^N
    */

    private static int N, S;
    private static int[] A;
    private static int count;

    public static void main(String[] args) throws IOException{
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        A = new int[N + 1];
        for(int i = 1; i <= N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 부분 수열 개수 계산
        count = 0;
        combination(0, 1, 0);

        // 정답 출력
        System.out.println(count);
    }

    private static void combination(int selected, int cur, int sum){
//        System.out.println(selected + " " + cur + " " + sum);
        if(cur > N){
            if(selected > 0 && sum == S){
                count++;
            }
            return;
        }
        combination(selected + 1,cur + 1, sum + A[cur]);
        combination(selected, cur + 1, sum);
    }
}