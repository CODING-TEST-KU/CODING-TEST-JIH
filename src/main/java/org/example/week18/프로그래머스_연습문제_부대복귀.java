package org.example.week18;

import java.util.*;

class 프로그래머스_연습문제_부대복귀 {
    /*
    11/26 16:50 ~ 17:15
    
    각 부대원의 최단 복귀시간을 구하자
    
    */
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < roads.length; i++){
            graph.get(roads[i][0]).add(roads[i][1]);
            graph.get(roads[i][1]).add(roads[i][0]);
        }
        
        // dest에서 bfs를 수행
        Queue<int[]> qu = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int[] minDists = new int[n + 1];
        Arrays.fill(minDists, -1);
        
        qu.offer(new int[] { destination, 0 });
        visited[destination] = true;
        minDists[destination] = 0;
        while(!qu.isEmpty()){
            int[] cur = qu.poll(); 
            for(int next : graph.get(cur[0])){
                if(!visited[next]){
                    qu.offer(new int[] { next, cur[1] + 1 });
                    visited[next] = true;
                    minDists[next] = cur[1] + 1;
                }
            }
        }
        
        // 각 지점의 최단거리를 반환
        // 만약 최단거리가 -1이면 도달 불가능하니 -1 반환
        int[] answer = new int[sources.length];
        for(int i = 0; i < sources.length; i++){
            answer[i] = minDists[sources[i]];
        }
        return answer;
    }
}