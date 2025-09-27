package org.example.week11;

import java.util.*;

class PROG_2021카카오블라인드채용_합승택시요금 {
    /*
    25/9/25 17:52 ~ 
    
    두 사람의 택시 요금의 합을 최소화해야 한다
    각자 타고 가는 최소 비용은 다익스트라 같은 알고리즘을 쓰면 된다
    
    그렇다면 둘이 합승할 때 최소 비용은 어떻게 계산하지?
    생각해보니 다익스트라를 이용하면 출발지로부터 각 지점까지의 최소 비용을 구할 수 있ㄷ.
    여기까지 합승한다고 치자
    이후에는 각자의 집으로 가는 최소비용을 구한다
    이렇게 세개의 값을 합하면 합승할 때의 최소 비용이다
    
    모든 지점 사이의 최단경로를 알아내야 한다 
    플로이드 와샬인가?
    */
    public int solution(int n, int s, int a, int b, int[][] fares) {
        final int INF = 500000000;
        int[][] dp = new int[201][201];
        for(int i = 1; i <= n; i++)
            Arrays.fill(dp[i], INF);
        for(int i = 1; i <= n; i++){
            dp[i][i] = 0;
        }
        
        for(int i = 0; i < fares.length; i++){
            int ss = fares[i][0];
            int ee = fares[i][1];
            int ff = fares[i][2];
            dp[ss][ee] = dp[ee][ss] = ff;
        }
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    dp[i][j] = Math.min(dp[i][k] + dp[k][j], dp[i][j]);
                }
            }
        }
        
        int answer = dp[s][a] + dp[s][b];
    
        for(int mid = 1; mid <= n; mid++){
            int cost = dp[s][mid] + dp[mid][a] + dp[mid][b];
            // System.out.println(mid + " " + cost);
            if(cost < answer){
                answer = cost;
            }
        }
    
        return answer;
    }
}