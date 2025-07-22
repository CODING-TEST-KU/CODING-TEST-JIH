package org.example.week3;

import java.io.*;
import java.util.StringTokenizer;

public class Prob6603 {
    /*
    https://www.acmicpc.net/problem/6603

    25/7/15 10:56 ~ 11:02 11:11 ~ 11:35

    S에서 6개의 수를 고르는 모든 경우의 수를 사전순으로 출력
     */

    private static int K;
    private static int[] S;
    private static int[] combination;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        // 각 케이스에 대해서 계산
        while(true){
            // 입력
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);

            K = Integer.parseInt(st.nextToken());
            if(K == 0){
                break;
            }

            S = new int[K];
            for(int i = 0; i < K; i++){
                S[i] = Integer.parseInt(st.nextToken());
            }

            // 모든 경우의 수 출력
            combination = new int[6];
            printCombinations(0);
//            printCombinations2(0, -1);

            // 빈줄 출력
            bw.write("\n");
        }
        bw.flush();
    }

    // 솔루션1
    private static void printCombinations(int depth) throws IOException{
        if(depth == 6){
            for(int i = 0; i < 6; i++){
                bw.write(combination[i] + " ");
            }
            bw.write("\n");
            return;
        }

        for(int i = 0; i < K; i++){
            if(depth > 0 && S[i] <= combination[depth - 1]) continue;
            combination[depth] = S[i];
//            System.out.println("pick = " + S[i]);
            printCombinations(depth + 1);
        }
    }

    // 솔루션2
    private static void printCombinations2(int depth, int prev) throws IOException{
        if(depth == 6){
            for(int i = 0; i < 6; i++){
                bw.write(combination[i] + " ");
            }
            bw.write("\n");
            return;
        }

        for(int i = prev + 1; i < K; i++){
            combination[depth] = S[i];
            printCombinations2(depth + 1, i);
        }
    }
}