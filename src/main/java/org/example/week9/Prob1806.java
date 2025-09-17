package org.example.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob1806 {
    /*
    25/9/11 00:45  ~ 01:05
    https://www.acmicpc.net/problem/1806

    연속된 수들의 부분합 중에서 조건을 만족하는 부분합을 구해야한다
    시간 제약을 봐서는 N log N 정도의 시간이 소요되어야할 듯
    투 포인터인가?

    5 50
1 2 3 4 5
    */
    public static void main(String[] args) throws IOException{
        int N, S;
        int[] A;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 슬라이딩 윈도우로 가장 짧은 길이 찾기
        int answer = 200000;
        int left = 0;
        int right = 0;
        int sum = 0;
        while(right < N){
            sum += A[right];
            while(sum - A[left] >= S){
                sum -= A[left];
                left++;
            }
            if(sum >= S){
                answer = Math.min(answer, right - left + 1);
            }
            right++;
        }
        if(answer == 200000){
            System.out.println(0);
            return;
        }
        System.out.println(answer);
    }
}