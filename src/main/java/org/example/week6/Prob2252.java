package org.example.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Prob2252 {
    /*
    https://www.acmicpc.net/problem/2252

    25/8/10 23:14 ~

    두 사림의 키 관계는 두 노드가 엣지로 연결된 것. 전체는 그래프임.
    위상 정렬을 이용하여서 그래프의 모든 노드를 정렬할 수 있음

    N * N 이면 시간 초과할 것이라 생각 했는데 일단 통과하긴 함.
    그런데 불필요한 inDegree 탐색을 어떻게 해결할 것인가?
    -> 힌트를 참고하니 큐를 도입하면 된다!
    */

    public static void main(String[] args) throws IOException{
        solution2();
    }

    private static void solution2() throws IOException {
        int N, M;
        List<Integer>[] graph;
        int[] inDegrees;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }
        inDegrees = new int[N + 1];
        for(int i = 0; i < M; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            int small = Integer.parseInt(st.nextToken());
            int big = Integer.parseInt(st.nextToken());
            graph[small].add(big);
            inDegrees[big]++;
        }

        Queue<Integer> qu = new LinkedList<>(); // 큐를 이용하여 solution1의 비효율성을 해결
        for(int i = 1; i <= N; i++){
            if(inDegrees[i] == 0){
                qu.offer(i);
            }
        }

        // 위상 정렬
        StringBuilder sb = new StringBuilder();
        while(!qu.isEmpty()){
            int poll = qu.poll();
            sb.append(poll + " ");
            for(int next : graph[poll]){
                inDegrees[next]--;
                if(inDegrees[next] == 0){
                    qu.offer(next);
                }
            }
        }

        // 출력
        System.out.println(sb.toString());
    }

    private static void solution1() throws IOException {
        int N, M;
        List<Integer>[] graph;
        int[] inDegrees;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }
        inDegrees = new int[N + 1];
        for(int i = 0; i < M; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            int small = Integer.parseInt(st.nextToken());
            int big = Integer.parseInt(st.nextToken());
            graph[small].add(big);
            inDegrees[big]++;
        }

        // 위상 정렬
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(inDegrees[j] == 0){
                    sb.append(j + " ");
                    inDegrees[j] = -1;
                    for(int next : graph[j]){
                        if(inDegrees[next] != -1) inDegrees[next]--;
                    }
                }
            }
        }

        // 출력
        System.out.println(sb.toString());
    }
}