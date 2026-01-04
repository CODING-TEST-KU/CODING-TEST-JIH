package org.example.week21;

class 프로그래머스_2021카카오블라인드채용_신규아이디추천 {
    /*
    26/1/3 17:26 ~ 17:52
    */
    public String solution(String new_id) {
        String step1 = new_id.toLowerCase();
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < step1.length(); i++){
            if((step1.charAt(i) >= 'a' && step1.charAt(i) <= 'z') || (step1.charAt(i) >= '0' && step1.charAt(i) <= '9') || step1.charAt(i) == '-' || step1.charAt(i) == '_' || step1.charAt(i) == '.'){
                sb.append(String.valueOf(step1.charAt(i)));
            }
        }
        String step2 = sb.toString();
        
        sb = new StringBuilder();
        boolean foundDot = false;
        for(int i = 0; i <= step2.length() - 1; i++){
            if(step2.charAt(i) == '.'){
                if(!foundDot){
                    foundDot = true; 
                }
                else{
                    continue;
                }
            }
            else{
                foundDot = false;
            }
            sb.append(String.valueOf(step2.charAt(i)));
        }
        String step3 = sb.toString();
        
        String step4 = step3;
        if(step4.length() > 0 && step4.charAt(0) == '.'){
            step4 = step4.substring(1);
        }
        if(step4.length() > 0 && step4.charAt(step4.length() - 1) == '.'){
            step4 = step4.substring(0, step4.length() - 1);
        }
    
        String step5 = step4;
        if(step5.isEmpty()){
            step5 = "a";
        }
        
        String step6 = step5;
        if(step6.length() >= 16){
            step6 = step6.substring(0, 15);
            if(step6.charAt(14) == '.'){
                step6 = step6.substring(0, 14);
            }
        }
        
        String step7 = step6;
        if(step7.length() <= 2){
            while(step7.length() < 3){
                step7 += step7.substring(step7.length() - 1);
            }
        }
    
        return step7;
    }
}