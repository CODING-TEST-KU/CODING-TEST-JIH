package org.example.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prob9095 {
    /*
    https://www.acmicpc.net/problem/9095

    25/7/28 00:43 ~ 00:59

    dp[n] : n을 만들 수 있는 경우의 수

    dp[n] = dp[n - 1] + dp[n - 2] + dp[n - 3]
    */

    public static void main(String[] args) throws IOException{
        int T, N;
        int[] dp;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        dp = new int[12];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= 11; i++){
            dp[i] += dp[i - 1];
            if(i >= 2) dp[i] += dp[i - 2];
            if(i >= 3) dp[i] += dp[i - 3];
        }

        StringBuilder sb = new StringBuilder();
        while(T-- > 0){
            N = Integer.parseInt(br.readLine());
            sb.append(dp[N] + "\n");
        }
        System.out.println(sb.toString());
    }
}