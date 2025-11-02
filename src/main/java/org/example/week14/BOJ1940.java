package org.example.week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1940 {
    /*
    25/11/2 13:03 ~

    https://www.acmicpc.net/problem/1940

    수열과 M이 주어졌을 때 M을 몇개나 만들 수 있는가를 구해야 함

    N이 15000이니 N log N 정도의 시간이 적당할 것 같음

    a가 있을 때 M - a가 있으면 M을 만들 수 있다

    1 2 3 4 5 6 7 8과 9

    1 + 8 = 9
    2 + 7 = 9
    ..
    총 4개 만들 수 있음

    근데 가만 보니 수열이 정렬되어 있다면 투 포인터를 쓸 수 있어 보인다!

테스트 케이스

8
9
1 2 3 4 5 6 7 8
->4

8
9
1 2 3 4 5 6 7 9
-> 3

8
10
1 2 3 4 5 6 7 9
-> 3

8
16
1 2 3 4 5 6 7 9
-> 1

8
17
1 2 3 4 5 6 7 9
-> 0
    */

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        int N, M;
        int []A;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);

        // 투 포인터 이용
        // 처음에는 left와 right가 양 끝에 있음
        // left < right일때까지
            // left + right > M이면 right를 왼쪽으로
            // == M이면 카운트하고 left를 오른쪽, right를 왼쪽으로 이동
            // < M이면 left를 오른쪽으로 이동
        int answer = 0;
        int left = 0;
        int right = N - 1;
        while(left < right){
            int sum = A[left] + A[right];
            if(sum > M){
                right--;
            }
            else if(sum == M){
                answer++;
                left++;
                right--;
            }
            else{
                left++;
            }
        }

        // 카운트 값을 출력
        System.out.println(answer);
    }
}