package 백준.그리디알고리즘.설탕배달;

//3N 과 5M이 있다.
//경우 1. 5M 
//만드는 방법 : 5M + 3N = Number
//가장 5M을 최대로 지정하고 M의 수를 1개씩 제거하면서 3N을 늘려간다.
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int totalkg = in.nextInt();
		int kg = totalkg;
		int topNum=kg / 5;
		int secondNum=0;
		while(true) {
			kg = totalkg - (5 * topNum);
			if((kg % 3) == 0) {
				secondNum=kg / 3;
				System.out.println(topNum+secondNum);
				break;
			}
			if(topNum==0) {
				System.out.println(-1);
				break;
			}
			topNum-=1;
		}
	}
}