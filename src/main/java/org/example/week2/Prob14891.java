package org.example.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob14891 {
    /*
    https://www.acmicpc.net/problem/14891

    25/7/11 22:02 ~ 22:43

    톱니가 맞닿은 부분은 9시 = 7번째, 3시 = 3번째 방향

    시계 방향은 문자열의 뒷문자를 앞으로 이동한 것
     */
    public static void main(String[] args) throws IOException{
        String[] wheels;
        int K;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력을 받는다
        wheels = new String[4];
        for(int i = 0; i < 4; i++){
            wheels[i] = br.readLine();
        }
        K = Integer.parseInt(br.readLine());

        // K번 차례로 돌린다
        for(int i = 0; i < K; i++){
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int wheel = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            // 3개의 맞닿은 지점을 확인한다.
            int[] directions = new int[4];
            directions[wheel - 1] = direction;
            for(int left = wheel - 2; left >= 0; left--){
                if(wheels[left].charAt(2) != wheels[left + 1].charAt(6)){
                    directions[left] = -directions[left + 1];
                }
                else{
                    break;
                }
            }
            for(int right = wheel; right < 4; right++){
                if(wheels[right].charAt(6) != wheels[right - 1].charAt(2)){
                    directions[right] = -directions[right - 1];
                }
                else{
                    break;
                }
            }

//            System.out.println("===debug===");
            // 알맞게 각 바퀴를 돌린다
            for(int j = 0; j < 4; j++){
                if(directions[j] == 1){
                    wheels[j] = wheels[j].substring(wheels[j].length() - 1) + wheels[j].substring(0, wheels[j].length() - 1);
                }
                else if(directions[j] == -1){
                    wheels[j] = wheels[j].substring(1) + wheels[j].substring(0, 1);
                }


//                System.out.println(wheels[j]);
            }

        }
        // 점수를 계산한다
        int sum = 0;
        int score = 1;
        for(int i = 0; i < 4; i++){
            if(wheels[i].charAt(0) == '1'){
                sum += score;
            }
            score *= 2;
        }
        System.out.println(sum);
    }
}