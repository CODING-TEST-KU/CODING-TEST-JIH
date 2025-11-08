package org.example.week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_17413 {
    /*
    25/11/2 19:44 ~ 20:02

    https://www.acmicpc.net/problem/17413

    단어는 공백으로 구분한다
    태그는 단어와 붙어있다.

    */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        String line = br.readLine();

        Stack<Character> st = new Stack<>();

        StringBuilder sb = new StringBuilder();
        boolean inTag = false;
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) == '<'){
                while(!st.isEmpty()){
                    sb.append(st.pop());
                }
                inTag = true;
                sb.append(line.charAt(i));
                continue;
            }
            if(line.charAt(i) == '>'){
                sb.append(line.charAt(i));
                inTag = false;
                continue;
            }
            if(line.charAt(i) == ' '){
                while(!st.isEmpty()){
                    sb.append(st.pop());
                }
                sb.append(' ');
                continue;
            }
            if(inTag){
                sb.append(line.charAt(i));
                continue;
            }
            st.push(line.charAt(i));
        }
        while(!st.isEmpty()){
            sb.append(st.pop());
        }


        System.out.println(sb.toString());
    }
}