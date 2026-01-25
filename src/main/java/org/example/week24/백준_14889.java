package org.example.week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_14889 {
    /*
    https://www.acmicpc.net/problem/14889

    26/1/18 17:44 ~ 18:08

    스타트팀과 링크팀의 능력치의 차이를 최소로 만들었을 때 차이를 구하자

    팀의 능력치는 모든 쌍의 능력치의 합이다

    N <= 20임
    브루트 포스가 가능할까?
    팀을 짜는데 : C(N, N/2) < 2^20
    각 팀의 능력을 계산하는데 : N
    -> 가능할 듯
    */

    private int N;
    private int[][] S;
    private boolean[] isStartTeam;
    private int answer;

    public static void main(String[] args) throws IOException {
        백준_14889 main = new 백준_14889();
        main.solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력 처리
        N = Integer.parseInt(br.readLine());
        S = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        isStartTeam = new boolean[N + 1];
        answer = 3000;

        // 모든 팀에 할당 조합에 대해서 능력 차이 계산
        assignToTeam(0, 0);

        // 정답 출력
        System.out.println(answer);
    }

    private void assignToTeam(int picked, int prev){
        if(picked == N / 2){
            // 차이의 최솟값을 갱신
            int startTeamSum = getTeamSum(true);
            int linkTeamSum = getTeamSum(false);
            answer = Math.min(answer, Math.abs(startTeamSum - linkTeamSum));

//            System.out.println(Arrays.toString(isStartTeam));
//            System.out.println(startTeamSum + " <-> " + linkTeamSum + " = " + answer);

            return;
        }
        // 분배
        for(int i = prev + 1; i <= N; i++){
            isStartTeam[i] = true;
            assignToTeam(picked + 1, i);
            isStartTeam[i] = false;
        }
    }

    private int getTeamSum(boolean team){
        int sum = 0;
        for(int i = 1; i <= N; i++){
            if(isStartTeam[i] != team){
                continue;
            }
            for(int j = 1; j <= N; j++){
                if(isStartTeam[j] != team){
                    continue;
                }
                sum += S[i][j];

            }
        }
        return sum;
    }
}