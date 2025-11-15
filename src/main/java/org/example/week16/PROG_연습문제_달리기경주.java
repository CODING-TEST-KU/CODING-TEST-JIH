package org.example.week16;

import java.util.*;

class PROG_연습문제_달리기경주 {
    /*
    15:19 ~ 15:52
    
    선수들의 달리기 순위를 순서대로 반환해야 함

    mumu soe poe kai mine
    
    rank[i]
    i      : 1 2 3 4 5
    rank[] : m s p k m 
             m s k p m
    
    */
    public String[] solution(String[] players, String[] callings) {
        Map<Integer, String> playerMap = new HashMap<>();
        Map<String, Integer> playerPos = new HashMap<>();
        int[] rank = new int[players.length]; // 0번이 1등
        for(int i = 0; i < players.length; i++){
            rank[i] = i;
            playerMap.put(i, players[i]);
            playerPos.put(players[i], i);
        }
        
        for(String c : callings){
            int pos = playerPos.get(c);
            int temp = rank[pos];
            rank[pos] = rank[pos - 1];
            rank[pos - 1] = temp;
            playerPos.put(players[rank[pos - 1]], pos - 1);
            playerPos.put(players[rank[pos]], pos);
        }
        
        String[] answer = new String[players.length];
        for(int i = 0; i < rank.length; i++){
            answer[i] = playerMap.get(rank[i]);
        }
        return answer;
    }
}