package org.example.week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1699 {
    /*
    25/10/26 20:33 ~ 20:45

    https://www.acmicpc.net/problem/1699

    항의 최소 개수를 구하자

    11 = 1^2 + 1^2 + 3^2
    = 1^2 + 10

    11의 항의 최소 개수 = 1 + 10의 항의 최소 개수

    다이나믹 프로그래밍인가?

    dp[i] : i의 항의 최소 개수
    dp[0] = 1
    dp[k] = for(1<=i<=root(k)) min(1 + dp[k - i^2], dp[k])
    */

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N;

        // 입력 처리
        N = Integer.parseInt(br.readLine());

        // dp
        int[] dp = new int[N + 1];
        Arrays.fill(dp, 100001);
        dp[0] = 0;
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= Math.sqrt(i); j++){
                dp[i] = Math.min(dp[i], 1 + dp[i - j*j]);
            }
        }
        System.out.println(dp[N]);
    }
}