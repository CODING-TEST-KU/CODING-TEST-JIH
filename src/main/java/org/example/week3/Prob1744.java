package org.example.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Prob1744 {
    /*
    https://www.acmicpc.net/problem/1744

    25/7/21 15:46 ~ 16:47

    수들을 정렬 한 후에 가장 큰 두 수를 묶는다?
    그런데 조건을 보면 음수도 존재함.

    그렇다면 음수와 양수로 나눈 후에 절댓값이 가장 큰 두 수를 곱해서 더한다. 개수가 홀수 개이면 수가 남을 수도 있다.

    정당성?
    - 절댓값이 a, b, c, d 순으로 커진다라 가정
    - c * d + a * b가 최대라는 것을 증명해야
    - a * c + b * d 를 위의 식에서 빼자
    - c * (d - a) + b * (a - d) = (b - c) * (a - d) > 0 이므로 원래 값이 더 큼
    - 더 큰 수열에 대해서도 이것이 성립할 것으로 보임

    양수와 음수를 곱해봤자 음수이니 더 작아지는 건 알았으니 양수와 음수는 나눠야 함.

    -> 수를 양수와 양이 아닌수로 분할

    예외 케이스

    0이 홀수개라서 하나 남을 때
    - 음수가 남아 있으면 음수와 곱한다
    - 양수만 남아 있으면 양수와 더한다

    양수에서 2개씩 짝지을 때 1이 섞여 있다면
    - 그 두 수를 더한다
     */

    public static void main(String[] args) throws IOException{
        int N;
        int[] arr;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 양이 아닌 수와 양수로 나누어서 최대합 계산 후 결과 합치기
        Arrays.sort(arr);

        List<Integer> nonPositives = new ArrayList<>();
        int front = 0;
        for(;front < N; front++){
            if(arr[front] <= 0)
                nonPositives.add(arr[front]);
            else
                break;
        }

//        System.out.print("nonPositives = ");
//        for(int i = 0; i < nonPositives.size(); i++){
//            System.out.print(nonPositives.get(i) + " ");
//        }
//        System.out.println();

        List<Integer> positives = new ArrayList<>();
        for(int end = N - 1; end >= front; end--){
            if(arr[end] > 0)
                positives.add(arr[end]);
            else
                break;
        }

//        System.out.print("positives = ");
//        for(int i = 0; i < positives.size(); i++){
//            System.out.print(positives.get(i) + " ");
//        }
//        System.out.println();

        int sum = 0;
        int i = 0;
        int leftNonPositive = 0;
        for(; i < nonPositives.size(); i += 2){
            if(i + 1 < nonPositives.size()){
                if(nonPositives.get(i + 1) < 0){ // 양이 아닌 수에 0이 있다. 두 수 중에 0이 제일 작으면 곱이 0이니 더할 이유가 없다
                    sum += nonPositives.get(i) * nonPositives.get(i + 1);
                }
            }
            else{
                leftNonPositive = nonPositives.get(i);
                break;
            }
        }

//        System.out.println("left neg = " + leftNonPositive);

        int j = 0;
        int leftPositive = -1;
        for(; j < positives.size(); j += 2){
            if(j + 1 < positives.size()){
                if(positives.get(j + 1) == 1){ // 양수에서 제일 작은 수가 1이면 곱하지 말고 그냥 더해야 더 크다.
                    sum += positives.get(j) + 1;
                }
                else{
                    sum += positives.get(j) * positives.get(j + 1);
                }
            }
            else{
                leftPositive = positives.get(j);
                break;
            }
        }

//        System.out.println("left non neg = " + leftPositive);

        if(leftNonPositive < 0){ // 음수가 남았다
            if(leftPositive > 0){  // 양수가 남았다
                sum += leftNonPositive + leftPositive;
            }
            else{
                sum += leftNonPositive;
            }
        }
        else{
            if(leftPositive > 0){
                sum += leftPositive;
            }
        }

        System.out.println(sum);
    }
}