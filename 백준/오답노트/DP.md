## 백준 2579 - 계단오르기

[2579번: 계단 오르기](https://www.acmicpc.net/problem/2579)

```java
import java.io.*;
import java.util.*;
public class Main {
	static int[] numArr;
	static int[] dpArr;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		numArr = new int[num+1];
		dpArr= new int[num+1];
		for(int i = 1 ; i < num+1 ; i++) {
			numArr[i] = sc.nextInt();
		}
		dpArr[0]=0;
		dpArr[1]=numArr[1];

		if(num >= 2) {
			dpArr[2] = numArr[1] + numArr[2];
		}
		System.out.println(dp(num));
	}
	public static int dp(int i) {
		if(i<3) {
			return dpArr[i];
		}
		if(dpArr[i]==0) {
			dpArr[i] = Math.max(dp(i-2), dp(i-3)+numArr[i-1])+numArr[i];
		}

		return dpArr[i];
	}
}
```

이 문제가 어려운 이유: 계단에 도착하기 위한 조건을 찾지 못하였다.
