package org.example.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Prob2293 {
    /*
    https://www.acmicpc.net/problem/2293

    25/7/29 23:18 ~ 00:07

    10 = 1 + 1 + 1 + ... + 1
    = 2 + (1 + 1 + 1 + ... + 1)
    = 2 + (2 + 1 + 1 + ... + 1)
    = 2 + (2 + 2 + 1 + 1 + 1 + 1 + 1)
    = 2 + (2 + 2 + 2 + 1 + 1)
    = 2 + (2 + 2 + 2 + 2)

    1만을 이용할 때
    dp[] = 1 1 1 1 1 1 1 1 1 1

    2를 이용할 때
    dp[] = 1 1+1 1+1 1+2 1+2

    dp[k][i] : i번 동전까지 이용해서 k을 만드는 경우의 수

    dp[j][1] = j % coins[i] == 0 ? 1 : 0;

    dp[k][i] = dp[k][i - 1] + dp[k - coins[i]][i]

    dp[k][n]이 답
    */

    public static void main(String[] args) throws IOException{
        int N, K;
        int[] coins;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coins = new int[N + 1];
        for(int i = 1; i <= N; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(coins);

        if(K < coins[0]){
            System.out.println(0);
            return;
        }

        // dp 계산
        int[][] dp = new int[N + 1][K + 1];
        for(int j = 1; j <= K; j++){
            if(j % coins[1] == 0){
                dp[1][j] = 1;
            }
        }
        for(int i = 1; i <= N; i++){
            dp[i][0] = 1;
        }
        for(int i = 2; i <= N; i++){
            for(int j = 1; j <= K; j++){
                dp[i][j] = dp[i - 1][j];
                if(j - coins[i] >= 0) dp[i][j] += dp[i][j - coins[i]];
            }
        }

        // 답 출력
        System.out.println(dp[N][K]);
    }
}