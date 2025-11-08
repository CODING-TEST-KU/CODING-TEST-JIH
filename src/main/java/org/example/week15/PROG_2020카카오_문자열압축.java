package org.example.week15;

class PROG_2020카카오_문자열압축 {
    public int solution(String s) {
        int answer = s.length();
        for(int length = 1; length <= s.length() / 2; length++){
            String result = encode(s, length);
            // System.out.println(result);
            answer = Math.min(answer, result.length());
        }
        return answer;
    }
    
    String encode(String s, int length){
        String matching = s.substring(0, length);
        int matchCount = 1;
        StringBuilder sb = new StringBuilder();
        for(int i = length; i <= s.length(); i += length){
            String cur = s.substring(i, Math.min(i + length, s.length()));
            if(matching.equals(cur)){
                matchCount += 1;
            }
            else{
                if(matchCount > 1){
                    sb.append(matchCount + matching);
                }
                else{
                    sb.append(matching);
                }
                matchCount = 1;
                matching = cur;
            }
        }
        if(matchCount > 1){
            sb.append(matchCount + matching);
        }
        else{
            sb.append(matching);
        }
        return sb.toString();
    }
}