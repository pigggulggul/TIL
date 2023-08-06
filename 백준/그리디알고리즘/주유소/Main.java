package 백준.그리디알고리즘.주유소;

import java.util.Scanner;

//N개의 도시
//N-1개의 길
//N개의 도시의 오일가격
// 2 3 1
//5 2 4 1
//총길이 : 5 * 2 + 2 * 1> 5 * 3
// 5 * 2 + 2 * 3 + 4 * 1 < 5 * 2 + 2 * 4 

//길길이  2 3 4
//오일값 2 3 4 5
// 00 저장
// 11 > 01
// 01 저장

// 값 : 2*2 + 2*3 + 2*4
//구하는 법 : (priceOil[i] * pathOil[j])+priceOil[i+1]=A 랑 priceOil[i] * pathOil[j] + priceOil[i]=B 비교
//(오일과 길이 알맞는 경우) A <= B면 ([i]*[j] + [i+1]*[j+1])
//(오일이 더 싸서 다음길까지 주유하는경우)A > B면 ([i]*[j] + [i]*[j+1]) 

//길길이  2 3 4 2
//오일값 2 1 3 1 4
//00 저장
//11 < 01
//11 저장
//11 11

//22 > 12
//12 저장
//11 01

//33 < 13
//13 저장
public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int city = in.nextInt();
        int[] pathOil = new int[city-1];
        int[] priceOil = new int[city];
        int j = 1;
        int control = 1;
        for(int i = 0 ; i < city-1 ; i++){
            pathOil[i] = in.nextInt();
        }
        for(int i = 0 ; i < city ; i++){
            priceOil[i] = in.nextInt();
        }
        int result = priceOil[0]*pathOil[0];
        for(int i = 1 ; i < pathOil.length; i++){
            int A =priceOil[i] * pathOil[i];
            int B = priceOil[i-control] * pathOil[i];
            if(A>B){
                result += B;
                control+=1;
            }
            else if(A<=B){
                result += A;
            }
        }
        System.out.println(result);
    }
}