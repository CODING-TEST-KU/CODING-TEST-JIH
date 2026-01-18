package org.example.week23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_11055 {
    /*
    https://www.acmicpc.net/problem/11055

    26/1/12 19:02 ~ 19:31

    합이 가장 큰, 증가하는 부분수열을 구하자

    dp[i] : i번 원소까지를 포함할 때, 합이 최대인 증가하는 부분 수열
    dp[i] = max(A[i] + dp[k]) for k in 1 ~ i - 1, 단 A[i] > A[k]인 경우만

    반례
    input:
    4
    100 40 50 60

    result:
    110
    */
    public static void main(String[] args) throws IOException {
        백준_11055 main = new 백준_11055();
        main.solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1];
        dp[1] = A[1];
        int answer = dp[1];
        for(int i = 2; i <= N; i++){
            for(int j = 0; j < i; j++){ // A[i]만 포함하는 경우를 빠뜨릴뻔 함
                if(A[i] > A[j]){
                    dp[i] = Math.max(dp[i], A[i] + dp[j]);
                }
            }
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}