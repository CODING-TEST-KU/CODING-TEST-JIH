package org.example.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob15486 {
    /*
    https://www.acmicpc.net/problem/15486

    25/7/28 02:03 ~ 2:40

    dp[i][0] : i일에 있는 일을 맡지 않았을 때 i일부터 N일까지 버는 최대 이익
    dp[i][1] : i일에 있는 일을 맡았을 때 i일부터 N일까지 버는 최대 이익

    dp[i][0] = max(dp[i + 1][0], dp[i + 1][1])
    dp[i][1] = max(dp[i + days[i]][0], dp[i + days[i]][1]) + P[i]
     */

    public static void main(String[] args) throws IOException{
        int N;
        int[] T;
        int[] P;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        T = new int[N + 1];
        P = new int[N + 1];
        for(int i = 1; i <= N; i++){
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 2][2];
        dp[N][0] = 0;
        dp[N][1] = T[N] > 1 ? 0 : P[N];
        for(int i = N - 1; i >= 1; i--){
            dp[i][0] = Math.max(dp[i + 1][0], dp[i + 1][1]);
            if(i + T[i] <= N + 1){
                dp[i][1] = Math.max(dp[i + T[i]][0], dp[i + T[i]][1]) + P[i];
            }
        }

//        for(int i = 0; i <= 1; i++){
//            for(int j = 1; j <= N; j++){
//                System.out.print(dp[j][i] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }
}