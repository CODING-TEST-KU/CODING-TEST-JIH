package org.example.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Prob12865 {
    /*
    25/9/11 01:09 ~
    https://www.acmicpc.net/problem/12865

    배낭문제
    이건 완전탐색을 해야하는 거 아닌가?
    시간초과가 일어나네

    시간을 단축할 방법이 없나?

    놀랍게도 다이나믹 프로그래밍으로 풀 수가 있다

4 5
1 1
1 1
1 1
1 1

4 5
2 1
5 5
3 6
4 2

4 5
6 10
6 10
6 10
6 10

4 5
1 0
1 0
1 0
1 0

    한 단계에서는 물건을 담을지 말지를 선택함
    물건을 선택함에 따라서 담을 수 있는 무게가 줄어듬.

    dp[i][j] : i번 이후의 물건을 뽑고 가방에 j만큼 담을 수 있을 때, 최대로 담을 수 있는 가치

    dp[i][j] = max(dp[i - 1][j - w[k]] + v[k], dp[i - 1][j]);
    */

    private static int N, K;
    private static int[] W;
    private static int[] V;
    private static int[][] dp;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        W = new int[N + 1];
        V = new int[N + 1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        // dp 계산
        dp = new int[N + 1][K + 1];
        for(int i = 0; i <= N; i++){
            Arrays.fill(dp[i], -1);
        }

        System.out.println(backpack(1, K));
    }

    // bag의 여유 공간이 있고 cur 이후에 있는 걸 선택해서 얻을 수 있는 이익의 최대
    private static int backpack(int cur, int bag){
        if(cur > N){
            return 0;
        }
        if(bag == 0){
            return 0;
        }

        int ret = dp[cur][bag];
        if(ret != -1){
            return ret;
        }
        if(bag - W[cur] >= 0){
            ret = Math.max(ret, backpack(cur + 1, bag - W[cur]) + V[cur]);
        }
        ret = Math.max(ret, backpack(cur + 1, bag));
        return dp[cur][bag] = ret;
    }

    // prev 이후에 있는 것들만 선택해서 K - weightSum만큼 담았을 떄 최대 이익을 반환하는 함수
    private static int backpack0(int prev, int weightSum, int valueSum){
        if(weightSum > K){
            return valueSum - V[prev];
        }
        if(prev >= N){
            return valueSum;
        }
        int ret = dp[prev][K - weightSum];
        if(ret != -1){
            return ret;
        }
        for(int i = prev + 1; i <= N; i++){
            ret = Math.max(ret, backpack0(i, weightSum + W[i], valueSum + V[i]));
        }
        return dp[prev][K - weightSum] = ret;
    }
}