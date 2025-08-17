package org.example.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob17281 {
    /*
    https://www.acmicpc.net/problem/17281

    2025/8/13 14:22 ~ 15:33

    야구에서 최대 득점을 구해야 한다
    완전탐색 내지 시뮬레이션인가?
    시간 복잡도 :8! * N
    1번 타자는 4번으로 정해짐
    아마 가지치기가 발생하니까 더 줄어들지 않을까 기대
    */

    private static int N;
    private static int[][] hits;
    private static int scoreSum;
    private static int maxScoreSum;
    private static boolean[] selected;
    private static int[] order;
    private static int hitter;
    private static int[] ru;

    public static void main(String[] args) throws IOException{
        // 입력을 처리

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        hits = new int[N + 1][10];
        for(int i = 1; i <= N; i++){
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            for(int j = 1; j <= 9; j++)
                hits[i][j] = Integer.parseInt(st.nextToken());
        }

        // 각 이닝마다
            // 선수들의 가능한 타순 생성
            // 3아웃 전까지 선수들의 득점을 계산
            // 총 점에 누적
        selected = new boolean[10];
        order = new int[10];
        maxScoreSum = 0;
        orderPlayers(0);

        // 결과 출력
        System.out.println(maxScoreSum);
    }
    private static void orderPlayers(int depth){
        if(depth == 9){
            scoreSum = 0;
            hitter = 0;
            for(int e = 1; e <= N; e++){
                ru = new int[4];
                scoreSum += playGame(hits[e]);
            }
            maxScoreSum = Math.max(maxScoreSum, scoreSum);
            return;
        }
        if(depth == 3){
            order[depth] = 1;
            orderPlayers(depth + 1);
        }
        else{
            for(int player = 2; player <= 9; player++){
                if(selected[player]) continue;
                selected[player] = true;
                order[depth] = player;
                orderPlayers(depth + 1);
                selected[player] = false;
            }
        }
    }

    private static int playGame(int[] hits){
        int score = 0;
        int outNumber = 0;
        while(true){
            int player = order[hitter];
            hitter = (hitter + 1) % 9;
            if(hits[player] == 0) outNumber += 1;
            if(outNumber >= 3){
                break;
            }
            score += getGain(hits[player]);
        }
        return score;
    }

    private static int getGain(int hitType){
        int score = 0;
        if(hitType == 1){
            if(ru[3] == 1) score += 1;
            ru[3] = ru[2];
            ru[2] = ru[1];
            ru[1] = 1;
        }
        if(hitType == 2){
            if(ru[3] == 1) score += 1;
            if(ru[2] == 1) score += 1;
            ru[3] = ru[1];
            ru[2] = 1;
            ru[1] = 0;
        }
        if(hitType == 3){
            if(ru[3] == 1) score += 1;
            if(ru[2] == 1) score += 1;
            if(ru[1] == 1) score += 1;
            ru[3] = 1;
            ru[2] = 0;
            ru[1] = 0;
        }
        if(hitType == 4){
            if(ru[3] == 1) score += 1;
            if(ru[2] == 1) score += 1;
            if(ru[1] == 1) score += 1;
            score += 1;
            ru[3] = ru[2] = ru[1] = 0;
        }
        return score;
    }
}