package org.example.week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_9465 {
    /*
    https://www.acmicpc.net/problem/9465

    26/1/10 17:07 ~

    스티커 점수 합의 최댓값을 구하자

    같은 변을 공유하지 않는 스티커들끼리만 점수의 합을 구해야한다

    dp[i][j] : 내가 i(0 ~ n-1)번째 열에서 j번 선택을 했을 때 (0 :아무것도 안 고름, 1: 위쪽 택, 2 : 아래쪽 택)
    dp[i][0] = max(max(dp[i - 1][0], dp[i - 1][1], dp[i - 1][2])
    dp[i][1] = max(dp[i - 1][0], dp[i - 1][2]) + sticker[i][0])
    */
    public static void main(String[] args) throws IOException {
        백준_9465 main = new 백준_9465();
        main.solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T, n;
        int[][] stickers;

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            n = Integer.parseInt(br.readLine());
            stickers = new int[2][n];
            for(int i = 0; i < 2; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dp = new int[n][3];
            dp[0][0] = 0;
            dp[0][1] = stickers[0][0];
            dp[0][2] = stickers[1][0];
            for(int i = 1; i < n; i++){
                dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2]));
                dp[i][1] = stickers[0][i] + Math.max(dp[i - 1][0], dp[i - 1][2]);
                dp[i][2] = stickers[1][i] + Math.max(dp[i - 1][0], dp[i - 1][1]);
            }

            int answer = Math.max(dp[n - 1][0], Math.max(dp[n - 1][1], dp[n - 1][2]));
            System.out.println(answer);
        }
    }
}