package org.example.week8;

import java.util.*;

class PROG_2020카카오인턴십_수식최대화 {
    /*
    
    절댓값이 가장 큰 수식의 계산 결과를 구해야함
    
    브루트포스인가?
    
    문자열을 어떻게 파싱해서 계산할까?
    
    연산자와 피연산자로 분리
    연산자만 보고 피연산자를 차례로 계산
    연산자중에서 우선순위인 것만 계산해야 하는데,
    같은 우선순위에 있는 연산자만 먼저 계산하기 위해서 큐를 두개를 써야하나
    */
    
    long answer = 0;
    List<Long> nums = new ArrayList<>();
    List<Character> opers = new ArrayList<>();
    List<Character> set = new ArrayList<>();
    char[] selected;
    boolean[] visited;
    
    public long solution(String expression) {
        String[] tokens = expression.split("[*+-]");
        for(String token : tokens){
            nums.add(Long.parseLong(token));
        }
        for(int i = 0; i < expression.length(); i++){
            char ch = expression.charAt(i);
            if(ch == '*' || ch == '+' || ch =='-'){
                opers.add(ch);
                if(!set.contains(ch)){
                    set.add(ch);   
                }
            }
        }
        System.out.println("size : " + set.size());
    
        // 모든 경우의수 생성  
        selected = new char[set.size()];
        visited = new boolean[set.size()];
        Arrays.fill(selected, ' ');
        simulate(0);
        
        return answer;
    }
    
    private void simulate(int depth){        
        if(depth >= set.size()){
            long result = evaluate();
            System.out.println("result : " + result);
            answer = Math.max(Math.abs(result), answer);
            return;
        }
        for(int i = 0; i < set.size(); i++){
            if(!visited[i]){
                visited[i] = true;
                selected[depth] = set.get(i);
                System.out.println("depth : " + depth + " selected" + selected[depth]);

                simulate(depth + 1);
                selected[depth] = ' ';
                visited[i] = false;
            }
        }
    }
    
    private long evaluate(){
        Queue<Long> temp;
        Queue<Character> temp2;
        Queue<Long> seenNum = new LinkedList<>();
        Queue<Long> unseenNum = new LinkedList<>(nums);
        Queue<Character> seenOper = new LinkedList<>();
        Queue<Character> unseenOper = new LinkedList<>(opers);
        
        // 연산자 우선순위가 큰 것부터 계산한다
        for(int i = 0; i < selected.length; i++){
            long first = unseenNum.poll();
            while(!unseenOper.isEmpty()){
                long second = unseenNum.poll();
                char oper = unseenOper.poll();
                
                System.out.println("oper : " + oper + " first : " + first + " second : " + second);
                
                if(oper == selected[i]){
                    if(oper == '*'){
                        first = first * second;
                    }
                    if(oper == '+'){
                        first = first + second;
                    }
                    if(oper == '-'){
                        first = first - second;
                    }
                }
                else{
                    seenNum.add(first);
                    first = second;
                    seenOper.add(oper);
                }
                if(unseenOper.isEmpty()){
                    seenNum.add(first);
                }
            }
            temp = unseenNum;
            unseenNum = seenNum;
            seenNum = temp;
            
            temp2 = unseenOper; 
            unseenOper = seenOper;
            seenOper = temp2;
        }
        
        return unseenNum.poll();
    }
}