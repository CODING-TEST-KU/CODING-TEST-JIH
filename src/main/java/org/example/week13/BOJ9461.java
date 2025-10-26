package org.example.week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9461 {
    /*
    25/10/26 19:44 ~ 20:00

    https://www.acmicpc.net/problem/9461

    규칙성이 있을까?
    dp[6] = 3 = dp[5] + dp[1]
    dp[7] = 4 = dp[6] + dp[2]
    dp[8] = 5 = dp[7] + dp[3]
    dp[9] = 7 = dp[8] + dp[4]
    dp[10] = 9 = dp[9] + dp[5]

    dp[n] = dp[n - 1] + dp[n - 5] if n > 6
    */

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        int T;
        long [] dp = new long[101];
        dp[1] = dp[2] = dp[3] = 1;
        dp[4] = dp[5] = 2;
        for(int i = 6; i <= 100; i++){
            dp[i] = dp[i - 1] + dp[i - 5];
        }

        T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[n]);
        }
    }
}