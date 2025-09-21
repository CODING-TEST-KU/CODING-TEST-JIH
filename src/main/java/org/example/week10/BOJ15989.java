package org.example.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15989 {
    /*
    25/9/17 12:27 ~

    https://www.acmicpc.net/problem/15989

    다이나믹 프로그래밍

    dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3] 인가? 아니다
    순서가 다른 것은 동일한 것으로 간주해야한다.


    dp[0] = 1
    for(j)
        for(i)
            dp[i] += dp[i - num[j]];

    1 1
    2

    1 1 1
    1 2
    3

    1 1 1 1
    1 1 2
    1 3
    2 2

    */

    public static void main(String[] args) throws IOException{
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] dp = new int[10001];
        int[] num = { 1, 2, 3 };
        dp[0] = 1;
        for(int j = 0; j < 3; j++){
            for(int i = 1; i <= 10000; i++){
                if(i - num[j] >=0) dp[i] += dp[i - num[j]];
            }
        }

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < T; i++){
            int N = Integer.parseInt(br.readLine());
            sb.append(dp[N] + "\n");
        }
        System.out.println(sb.toString());
    }
}