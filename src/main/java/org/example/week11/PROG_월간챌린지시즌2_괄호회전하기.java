package org.example.week11;

import java.util.*;

class PROG_월간챌린지시즌2_괄호회전하기 {
    /*
    10:07 ~ 
    
    s의 최대 길이가 1000이니 완전탐색으로 풀 수 있을 듯
    
    [{()}]
    [{}]()
    */
    public int solution(String s) {
        int answer = 0; // 올바른 괄호인 경우의 수
        
        // 문자열을 한칸 씩 회전
            // 올바른 괄호인지 판정
            // 개수 카운트
        
        for(int i = 0; i < s.length(); i++){
            String rotated = s.substring(i) + s.substring(0, i);
            Stack<Character> st = new Stack();
            boolean isCorrect = true;
            for(int j = 0; j < rotated.length(); j++){
                char cur = rotated.charAt(j);
                if(cur == ')'){
                    if(!st.isEmpty() && st.peek() == '('){
                        st.pop();
                    }
                    else{
                        isCorrect = false;
                        break;
                    }
                }
                else if(cur == '}'){
                    if(!st.isEmpty() && st.peek() == '{'){
                        st.pop();
                    }
                    else{
                        isCorrect = false;
                        break;
                    }
                }
                else if(cur == ']'){
                    if(!st.isEmpty() && st.peek() == '['){
                        st.pop();
                    }
                    else{
                        isCorrect = false;
                        break;
                    }
                }
                else{
                    st.push(cur);
                }
            }
            if(isCorrect && st.isEmpty()){
                answer++;
            }
            
        }
        
        return answer;
    }
}