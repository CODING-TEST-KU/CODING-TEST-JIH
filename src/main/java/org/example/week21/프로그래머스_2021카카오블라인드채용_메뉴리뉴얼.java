package org.example.week21;

import java.util.*;

class 프로그래머스_2021카카오블라인드채용_메뉴리뉴얼 {
    /*
    26/1/4 19:02 ~ 20:11
    
    코스요리 메뉴 개수에 해당하는 코스요리 조합을 구하는 문제
    근데 2명 이상의 손님으로부터 공통으로 선택된 조합만 구해야함
    없으면 정답에 포함하지 않음

    */
    public String[] solution(String[] orders, int[] course) {
        // 입력 정규화
        List<String> temp = new ArrayList<>();
        for(String order : orders){
            PriorityQueue<String> pq = new PriorityQueue<>();
            for(int i = 0; i < order.length(); i++){
                pq.offer(order.substring(i, i + 1));
            }   
            String normalized = "";
            while(!pq.isEmpty()){
                normalized += pq.poll();
            }
            temp.add(normalized);
        }
        orders = temp.stream().toArray(String[]::new);
        
        // 각 주문에서 부분문자열을 구한다
        // 각 부분 문자열의 갯수를 구한다.
        
        HashMap<String, Integer> map = new HashMap<>();
        for(String order : orders){
            genSubstring(map, order, "", 0);
        }
        // 갯수가 2이상인 애들을 크기에 따라 나누고 정렬한다
        // 가장 빈도수가 큰 애들을 정답 배열에 넣는다
        List<String>[] combs = new ArrayList[course.length];
        for(int i = 0; i < combs.length; i++)    
            combs[i] = new ArrayList<>();
        int[] combsCount = new int[combs.length];
        for(String key : map.keySet()){
            int count = map.get(key);
            if(count >= 2){
                System.out.println(key);
                for(int i = 0; i < course.length; i++){
                    if(key.length() == course[i]){
                        if(count == combsCount[i]){
                            combs[i].add(key);
                        }  
                        else if (count > combsCount[i]){
                            combs[i].clear();
                            combs[i].add(key);
                            combsCount[i] = count;
                        }
                    }
                }                
            }
        }
        List<String> answer = new ArrayList<>();
        for(int i = 0; i < combs.length; i++){
            answer.addAll(combs[i]);
        }
        
        // 정답 배열을 정렬한다.
        PriorityQueue<String> pq = new PriorityQueue<>(answer);
        answer.clear();
        while(!pq.isEmpty()){
            answer.add(pq.poll());
        }
        return answer.stream().toArray(String[]::new);
    }
    
    private void genSubstring(HashMap<String, Integer> ret, String original, String sub, int depth){
        if(depth == original.length()){
            if(sub.equals("")){
                return;
            }
            if(ret.get(sub) == null){
                ret.put(sub, 1);
            }
            else{
                ret.put(sub, ret.get(sub) + 1);
            }
            return;
        }
        genSubstring(ret, original, sub + original.substring(depth, depth + 1), depth + 1);
        genSubstring(ret, original, sub + "", depth + 1);
    }
}