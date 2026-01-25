package org.example.week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 백준_12852 {
    /*
    https://www.acmicpc.net/problem/12852

    26/1/18 17:11 ~ 17:29

    1로 만들기 위한 최소 연산 횟수를 구하자

    dp[i] : i를 1로 만들기 위한 최소 연산 횟수

    dp[i] = min ( 3으로 나누어 떨어지면 dp[i / 3] ,
        2로 나누어 떨어지면 dp[i / 2],
        dp[i - 1]) + 1;

    */

    public static void main(String[] args) throws IOException {
        백준_12852 main = new 백준_12852();
        main.solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];
        int[] prev = new int[N + 1];
        Arrays.fill(dp, 2000_000);
        dp[1] = 0;
        prev[1] = -1;
        for(int i = 2; i <= N; i++){
            if(i % 3 == 0){
                dp[i] = dp[i / 3] + 1;
                prev[i] = i / 3;
            }
            if(i % 2 == 0){
                if(dp[i] > dp[i / 2] + 1){
                    dp[i] = dp[i / 2] + 1;
                    prev[i] = i / 2;
                }
            }
            if(dp[i] > dp[i - 1] + 1){
                dp[i] = dp[i - 1] + 1;
                prev[i] = i - 1;
            }
        }

//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[N]);
        int i = N;
        System.out.print(N + " ");
        while(prev[i] != -1){
            System.out.print(prev[i] + " ");
            i = prev[i];
        }
    }
}