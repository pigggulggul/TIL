package 백준.그리디알고리즘.동전;

import java.util.ArrayList;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		int totalGold = in.nextInt();
		ArrayList<Integer> arrGold = new ArrayList<Integer>();
		int count = 0;
		for(int i = 0 ; i < num ; i++) {
			arrGold.add(in.nextInt());
		}
		while(true) {
			for(int i = (arrGold.size()-1) ; i >=0;i--) {
				if(totalGold / arrGold.get(i) > 0) {
					count += totalGold / arrGold.get(i);
					totalGold %= arrGold.get(i);
				}
			}
			System.out.println(count);
			break;
		}
	}
}