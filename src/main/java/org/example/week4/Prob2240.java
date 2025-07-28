package org.example.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob2240 {
    /*
    https://www.acmicpc.net/problem/2240

    25/7/28 19:05

    자두의 최대 개수를 구하자

    특정 나무에서 자두가 많이 떨어지면 그곳에 있는 것이 나음

    만약 횟수 제한이 없으면 모든 자두를 다 먹으면 됨

    내가 t시간에 움직일지 말지를 결정

    dp[i][j]: i초까지 j번 움직여서 얻는 최대 자두 개수

    과거에 모든 최대 이익 + 내가 움직일지 선택해서 얻는 이익

    dp[1][0] = profit(1, 0) = 1번에 떨어지면 1, 아니면 0
    dp[1][1] = profit(1, 1) = 2번에 떨어지면 1, 아니면 0

    dp[2][0] = max(dp[1][0]) + profit[2, 0]
    dp[2][1] = max(dp[1][1], dp[1][0]) + profit[2, 1]
    dp[2][2] = max(dp[1][1]) + profit[2, 2]

    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1] + profit(i, j) (i - 1 >= j)

    정답은 dp[N][0] ~ dp[N][W] 중 최대값

10 5
1
2
1
2
1
2
1
2
1
2

// 8
     */

    public static void main(String[] args) throws IOException{
        int T, W;
        int[] tree;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        tree = new int[T + 1];
        for(int i = 1; i <= T; i++){
            tree[i] = Integer.parseInt(br.readLine());
        }

        // dp 계산
        int[][] dp = new int[T + 1][W + 1];
        dp[1][0] = tree[1] == 1 ? 1 : 0;
        dp[1][1] = tree[1] == 2 ? 1 : 0;
        for(int i = 2; i <= T; i++){
            dp[i][0] = dp[i - 1][0] + (tree[i] == 1 ? 1 : 0);
        }
        for(int i = 2; i <= T; i++){
            int limit = Math.min(i, W);
            for(int j = 1; j <= limit; j++){
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]) + (j % 2 == 0 && tree[i] == 1 || j % 2 == 1 && tree[i] == 2 ? 1 : 0);
            }
        }

//        System.out.println("dp = ");
//        for(int i = 0; i <= T; i++){
//            for(int j = 0; j <= W; j++){
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }

        // 최댓값 출력
        int limit = Math.min(T, W);
        int maxValue = 0;
        for(int i = 0; i <= limit; i++){
            maxValue = Math.max(maxValue, dp[T][i]);
        }
        System.out.println(maxValue);
    }
}