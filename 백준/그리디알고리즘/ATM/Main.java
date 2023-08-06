package 백준.그리디알고리즘.ATM;

import java.util.ArrayList;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		ArrayList<Integer> numArr = new ArrayList<Integer>();
		int minimum=1000;
		int total=0;
		int total2 = 0;
		int deleteKey = 0;
		
		for(int i = 0 ; i < num ; i++) {
			numArr.add(in.nextInt());
			if(numArr.get(i)<minimum) {
				minimum=numArr.get(i);
				deleteKey=i;
			}
		}
		total += minimum;
		total2 += total;
		numArr.remove(numArr.get(deleteKey));
		while(true) {
			minimum=1000;
			if(numArr.size()==0) {
				System.out.println(total2);
				break;
			}
			for(int i = 0 ; i < numArr.size() ; i++) {
				if(numArr.get(i)<minimum) {
					minimum=numArr.get(i);
					deleteKey=i;
				}
			}
			total += minimum;
			total2 += total;
			numArr.remove(numArr.get(deleteKey));
		}
	}
}