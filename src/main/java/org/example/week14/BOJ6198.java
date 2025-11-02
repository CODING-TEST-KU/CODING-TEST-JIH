package org.example.week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ6198 {
    /*
    25/10/29 13:42 ~

    https://www.acmicpc.net/problem/6198

    관리인들이 볼 수 있는 빌딩의 총 개수를 구하자

    자신보다 높은 빌딩이 나오기 전까지 낮은 빌딩이 몇개인지 알아야 함

    10  3  7  4  12  2

    완전탐색은 어려울 것임

    자신보다 처음으로 높은 빌딩이 오른쪽에서 무엇인지를 알아야 함

    맨 오른쪽은 무조건 0개

    이번에도 뒤에서 시작해야할 것 같은 느낌
    현재 높이가 이전 높이 보다 높으면 이전 높이를 배열에서 지운다.
    배열에 자신보다 높은 건물이 있으면 거리를 구해서 저장
    배열에 현재 높이를 저장한다

    5 5 4

반례
6
1
2
3
1
2
3

    */

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N;
        int[] h;

        N = Integer.parseInt(br.readLine());
        h = new int[N];
        for(int i = 0; i < N; i++){
            h[i] = Integer.parseInt(br.readLine());
        }

        int [] seeableBuildings = new int[N];
        seeableBuildings[N - 1] = 0;
        Stack<int []> st = new Stack<>(); // 높이와 인덱스 저장
        st.push(new int [] { h[N - 1], N - 1 });

        for(int i = N - 2; i >= 0; i--){
            while(!st.isEmpty() && h[i] > st.peek()[0]){
                st.pop();
            }
            if(st.isEmpty()){
                seeableBuildings[i] = N - i - 1;
                st.push(new int[] { h[i], i });
                continue;
            }
            seeableBuildings[i] = st.peek()[1] - i - 1;
            st.push(new int[] { h[i], i });
        }

        long answer = 0;
        for(int i = 0; i < N; i++){
            answer += seeableBuildings[i];
        }
        System.out.println(answer);
    }
}