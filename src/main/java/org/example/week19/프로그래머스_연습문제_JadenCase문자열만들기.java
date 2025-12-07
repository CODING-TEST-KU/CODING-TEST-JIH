package org.example.week19;

class 프로그래머스_연습문제_JadenCase문자열만들기 {
    /*
    25/12/7 16:20 ~ 16:39
    
    공백이 여러개일 수 있다..
    */
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        boolean isWordHead = true;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(ch == ' '){
                sb.append(ch);
                isWordHead = true;
            }
            else{
                if(isWordHead){
                    if(ch >= 'a' && ch <= 'z'){
                        sb.append((char)('A' + ch - 'a'));
                    }
                    else{
                        sb.append(ch);
                    }
                    isWordHead = false;
                }
                else{
                    if(ch >= 'A' && ch <= 'Z'){
                        sb.append((char)('a' + ch - 'A'));  
                    }
                    else{
                        sb.append(ch);
                    }
                }
            }
        }
        
        return sb.toString();
    }
}