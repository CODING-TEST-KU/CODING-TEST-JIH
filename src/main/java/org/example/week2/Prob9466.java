package org.example.week2;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Prob9466 {
    /*
    https://www.acmicpc.net/problem/9466

    25/7/12 11:55 ~ 12:52
    13:00 ~ 13:33

    학생들의 팀원 선택 방향을 단방향 그래프로 나타내자
    그래프에서 사이클에 속한 사람들만 한 팀을 이룰 수 있
    사이클에 속하지 않은 노드들의 수를 세면 된다.

    단방향 그래프에서 사이클에 있는 노드의 수는 어떻게 구하지?
    1. dfs를 이용해서 사이클을 탐지한다
    2. 사이클 위에 있는 점에 대해서 다시 dfs를 돌린다.
    3. 두번째 dfs에서 방문한 노드가 사이클위의 점이다

    모든 학생은 한 사람만 택하니 이렇게 할 수 있을 듯??

    시간 초과가 발생하므로 이 풀이는 맞지 않은 듯 함

    이전 풀이의 문제점
    - 최악의 경우 각 dfs에서 O(N), 즉 전체에서 O(N^2)의 시간복잡도를 보일 수 있음.
    - 이 경우에 시간초과가 발생할 것임

    힌트를 참고하니
    - 큐를 이용하는 사람이 있음.
    - 어떤 사람은 방문한 정점에서 일부 정점을 제거하는 방식을 사용함.

    해결 방법
    - dfs 도중에 빠르게 사이클 위에 있는 정점을 판단하는 방법을 찾는 것이 포인트였음
    - 나는 링크드 리스트를 이용해서 사이클 위에 없는 정점을 제거했음.

     새로 알게된 점
    - LinkedList의 peekLast()는 리스트의 제일 뒷 노드를 반환함
     */
    public static void main(String[] args) throws IOException{
        int T, N;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 케이스 별로 입력 처리
        T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            // 그래프를 형성
            N = Integer.parseInt(br.readLine());
            int[] graph = new int[N + 1];
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            for(int i = 1; i <= N; i++){
                graph[i] = Integer.parseInt(st.nextToken());
            }

            // 그래프에서 사이클에 있는 노드의 개수를 센다
            boolean[] visited = new boolean[N + 1];
            List<Integer> cycleNodes = new ArrayList<>(N);
            for(int v = 1; v <= N; v++){
                if(!visited[v]) {
//                    System.out.println("visit node " + v);
                    LinkedList<Integer> visitNodes = new LinkedList();
                    dfs(visited, v, graph, visitNodes, cycleNodes);
                }

//                System.out.println("cycleNodes = ");
//                for(int node : cycleNodes){
//                    System.out.print(node + " ");
//                }
//                System.out.println();
            }

            // 답을 구한다
            bw.write(String.valueOf(N - cycleNodes.size()) + "\n");
        }
        bw.flush();
    }

    private static void dfs(boolean[] visited, int current, int[] graph, LinkedList<Integer> visitNodes, List<Integer> cycleNodes){
        if (visited[current]) {
            while (!visitNodes.isEmpty()) {
                if (visitNodes.peek() != graph[visitNodes.peekLast()]) {
                    visitNodes.poll();
                }
                else {
                    break;
                }
            }

            while (!visitNodes.isEmpty()) {
                int v = visitNodes.poll();
                cycleNodes.add(v);
            }

            return;
        }

        visited[current] = true;
        visitNodes.add(current);

        dfs(visited, graph[current], graph, visitNodes, cycleNodes);
    }
}