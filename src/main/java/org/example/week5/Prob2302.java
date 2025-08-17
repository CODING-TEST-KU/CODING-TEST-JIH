package org.example.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prob2302 {
    /*
    https://www.acmicpc.net/problem/2302

    25/8/17 00:12 ~ 00:47

    가능한 좌석 배치 경우의 수를 계산해야 한다

    1 2 3 4 5 6 7 8 9
    2 1 3 4 5 6 7 8 9

    1 2 3 4 5 6 7 9 8

    두 좌석이 고정석이 아니면 뒤짚거나 뒤짚지 않을 수 있다
    앞의 좌석들이 이후의 좌석들을 배치에 영향을 주지 않음
    좌석 배치의 경우의수가 중복됨

    dp[i] : i번까지 사람을 배치하는 경우의 수

    앞자리와 바꾸거나 바꾸지 않아야 함
    바꿀 수 있으면 dp[i] = dp[i - 2] + dp[i - 1]
    바꿀 수 없으면 dp[i - 1]

    1 2 3 3 3 6 6 6 12
    */

    public static void main(String[] args) throws IOException{
        int N, M;
        boolean[] isVip;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        isVip = new boolean[N + 1];
        for(int i = 1; i <= M; i++){
            int seat = Integer.parseInt(br.readLine());
            isVip[seat] = true;
        }

        // dp 계산
        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= N; i++){
            if(!isVip[i] && !isVip[i - 1]) dp[i] = dp[i - 2] + dp[i - 1];
            else dp[i] += dp[i - 1];
        }

        // 출력
        System.out.println(dp[N]);
    }
}