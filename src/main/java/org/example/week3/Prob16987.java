package org.example.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prob16987 {
    /*
    https://www.acmicpc.net/problem/16987

    25/7/22 13:16 ~ 13:49 14:18 ~ 14:40

    계란을 서로 깨면 내구도가 상대방의 무게만큼 깎인다

    최대로 깰 수 있는 계란의 개수를 구해야한다.

    완전탐색에서 시작하자
    */

    private static int N;
    private static int[] naegudos;
    private static int[] weights;

    private static int maxBrokenEggs = 0;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        N = Integer.parseInt(br.readLine());
        naegudos = new int[N];
        weights = new int[N];
        for(int i = 0; i < N; i++){
            String line = br.readLine();
            String[] tokens = line.split(" ");
            naegudos[i] = Integer.parseInt(tokens[0]);
            weights[i] = Integer.parseInt(tokens[1]);
        }

        // 계란치기 수행
        hitWithTarget(0);

        // 정답 출력
        System.out.println(maxBrokenEggs);
    }

    public static void hitWithTarget(int egg){
        // 마지막 계란에 도달하면 최대 값을 갱신한다
        if (egg == N){
            int brokenEggs = 0;

            for(int i = 0; i < N; i++){
                if(naegudos[i] <= 0){
                    brokenEggs++;
                }
            }

//            System.out.print("naegudos = ");
//            for(int i = 0; i < N; i++) {
//                System.out.print(naegudos[i] + " ");
//            }
//            System.out.println();

            if(maxBrokenEggs < brokenEggs){
                maxBrokenEggs = brokenEggs;
            }
            return;
        }
        // 왼쪽부터 깨지지 않은 계란을 선택
        if(naegudos[egg] > 0) {
            // 나머지 중에서 깨지지 않은 계란과 맞부딪힌다
            boolean noTarget = true;
            for(int target = 0; target < N; target++) {
                if (naegudos[target] > 0 && target != egg) {
                    noTarget = false;

//                    System.out.println("hit egg = " + egg + " with " + target);

                    naegudos[egg] -= weights[target];
                    naegudos[target] -= weights[egg];

//                    System.out.print("naegudos = ");
//                    for(int i = 0; i < N; i++) {
//                        System.out.print(naegudos[i] + " ");
//                    }
//                    System.out.println();

                    hitWithTarget(egg + 1);

                    naegudos[egg] += weights[target];
                    naegudos[target] += weights[egg];
                }
            }
            if(noTarget){
                hitWithTarget(egg + 1);
            }
        }
        else{
            hitWithTarget(egg + 1);
        }
    }
}