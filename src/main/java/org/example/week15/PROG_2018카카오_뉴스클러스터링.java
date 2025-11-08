package org.example.week15;

import java.util.*;

class PROG_2018카카오_뉴스클러스터링 {
    /*
    
    36분만에 해결 
    
    확장된 자카드 유사도를 구하자
    
    답은 65536를 곱하고 소수를 버린 값이다.
    
    두 집합이 공집합이면 유사도는 1이다.
    문자열은 두글자씩 자르는데, 영문자가 아니면 버린다
    대소문자 구분 x
    
    다중 집합을 만들어야함
    
    aa aa 
    aa aa aa    2 / 3 = 0.6666  
    
    교집합과 합집합 구하려면?
    
    - 교집합을 구하기 위해서 맵을 이용 
    - 같은 원소면 두 맵 중에서 개수가 가장 작은 것을 저장하면 됨
    
    - 합집합은?
    - 같은 원소면 두 맵 중에서 개수가 큰 것을 선택
    
    aa bc
    bc bc
    */
    public int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        // System.out.println((int)(((double) 2 / 3 ) * 65536));
        
        Map<String, Integer> set1 = makeSet(str1);
        Map<String, Integer> set2 = makeSet(str2);
        
        if(set1.size() == 0 && set2.size() == 0){
            return 65536;
        }
        
        Set<String> keys = new HashSet<>();
        for(String key : set1.keySet()){
            keys.add(key);
        }
        for(String key : set2.keySet()){
            keys.add(key);
        }
        
        int intersect = 0;
        for(String key : keys){
            intersect += Math.min(set1.getOrDefault(key, 0), set2.getOrDefault(key, 0));
            // System.out.println(key);
        }
        System.out.println(intersect);
        
        int union = 0;
        for(String key : keys){
            union += Math.max(set1.getOrDefault(key, 0), set2.getOrDefault(key, 0));
            // System.out.println(key);
        }
        System.out.println(union);
        
        double jacard = (double) intersect / union;
        int answer = (int) (jacard * 65536);
        return answer;
    }
    
    private Map<String, Integer> makeSet(String str1){
        Map<String, Integer> set1 = new HashMap<>();
        for(int i = 0; i + 1 < str1.length(); i += 1){
            String elem = str1.substring(i, i + 2);
            if(elem.matches("^[a-z]*$")){
                set1.put(elem, set1.getOrDefault(elem, 0) + 1);
            }
        }
        return set1;
    }
}