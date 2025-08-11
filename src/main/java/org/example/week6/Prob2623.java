package org.example.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Prob2623 {
    /*
    https://www.acmicpc.net/problem/2623

    25/8/11 17:29 ~ 18:11

    주어진 그래프로 위상정렬을 수행한다
    만약 정렬을 못하면 0을 출력한다

    위상 정렬이 안되는 경우는?
    -> 사이클이 있는 그래프인 경우인가...?

             1 - 4 - 3
             / - \ - /
        6 - 2 - 5

    */
    private static List<Integer>[] graph;

    private static int[] visited;


    public static void main(String[] args) throws IOException{
        int N, M;
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
        for(int i = 1 ; i <= M; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            int numberOfSinger = Integer.parseInt(st.nextToken());
            int cur = Integer.parseInt(st.nextToken());
            for(int j = 2; j <= numberOfSinger; j++){
                int next = Integer.parseInt(st.nextToken());
                if(!graph[cur].contains(next)) {
                    graph[cur].add(next);
                    inDegrees[next]++;
                }
                cur = next;
            }
        }

        visited = new int[N + 1];
        for(int i = 1; i <= N; i++){
            if(hasCycle(i)){
                System.out.println(0);
                return;
            }
        }

        // 위상 정렬 수행
        Queue<Integer> qu = new LinkedList<>();
        List<Integer> answer = new ArrayList();
        for(int i = 1; i <= N; i++){
            if(inDegrees[i] == 0){
                qu.offer(i);
            }
        }

        while(!qu.isEmpty()){
            int cur = qu.poll();
            answer.add(cur);
            for(int next : graph[cur]){
                inDegrees[next]--;
                if(inDegrees[next] == 0){
                    qu.offer(next);
                }
            }
        }

        for(int i = 1; i <= N; i++){
            if(inDegrees[i] > 0){ // 남아 있는 경우
                System.out.println(0);
                return;
            }
        }

        // 정답 출력
        StringBuilder sb = new StringBuilder();
        for(int a : answer){
            sb.append(a + "\n");
        }
        System.out.println(sb.toString());
    }

    private static boolean hasCycle(int cur){
//        System.out.println("cur = " + cur);

        if(visited[cur] == 2){
            return false;
        }
        else if (visited[cur] == 1) {
            return true;
        }

        boolean ret = false;
        visited[cur] = 1;
        for(int next : graph[cur]){
            ret = ret || hasCycle(next);
        }
        visited[cur] = 2;
        return ret;
    }
}