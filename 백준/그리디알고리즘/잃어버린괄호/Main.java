package 백준.그리디알고리즘.잃어버린괄호;

import java.util.Scanner;
//55 - 50 + 40  / -35
//50 - 30 - 35 + 40 / -55
//50 - 30 - 35 + 40 - 30 + 30 /
// 규칙 : - 다음에 +가 오면 +에 가로를 쳐서 계산한다
//[50,30,35+40,30+30]
public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] str2 = str.split("-");
        String[] plusStr;
        int plusresult = 0;
        int result = 0;
        ///// 확인과정
        // for(int i = 0 ; i < str2.length ; i++){
        //     System.out.println(str2[i]);
        // } 
        for(int i = 0 ; i < str2.length;i++){
            if(str2[i].contains("+")){
                plusStr=str2[i].split("\\+");
                for(int j = 0 ; j < plusStr.length;j++){
                    plusresult +=Integer.parseInt(plusStr[j]);
                }
                str2[i]=String.valueOf(plusresult);
                plusresult=0;
            }
            if(i==0){
                result = Integer.parseInt(str2[0]);
            }
            else{
                result -=Integer.parseInt(str2[i]);
            }
        }
        System.out.println(result);
    } 
}
