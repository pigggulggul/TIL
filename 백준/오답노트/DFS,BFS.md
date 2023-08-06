## 백준 11724 - 연결 요소의 개수

[11724번: 연결 요소의 개수](https://www.acmicpc.net/problem/11724)

```java
import java.util.*;
import java.io.*;
public class Main {
	static int N;
	static int M;
	static int[] visited;
	static ArrayList<ArrayList<Integer>> arrayList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int num;
		int count=0;
		arrayList = new ArrayList<>();
		visited = new int[Integer.parseInt(st.nextToken())+1];
		num = Integer.parseInt(st.nextToken());

		for(int i = 0 ; i < visited.length;i++) {
			arrayList.add(new ArrayList<Integer>());
		}

		int a,b;
		for(int i = 0 ; i < num ; i++) {
			st = new StringTokenizer(br.readLine());
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			arrayList.get(a).add(b);
			arrayList.get(b).add(a);

		}
		for(int i = 1 ; i < visited.length ; i++) {
			if(visited[i]==0) {
				dfs(i);
				count+=1;
			}
		}

		System.out.println(count);
	}
	public static void dfs(int i) {
		if(visited[i]==0) {
			visited[i]=1;
		}
		for(int number = 0; number < arrayList.get(i).size(); number++) {
			int number2 = arrayList.get(i).get(number);
			if(visited[number2]==0) {
				dfs(number2);
			}
		}
	}
}
```

이 문제가 어려운 이유: 어렵진 않았다. 하지만 무방향 그래프의 특성을 이용해야하고 arrrayList에 a와 b를 둘 다 추가했어야했다.

## 백준 2178 - 미로탐색

[2178번: 미로 탐색](https://www.acmicpc.net/problem/2178)

```java
import java.io.*;
import java.util.*;

//(1,1)에서 시작하여 (N,M)까지 가는 최소 거리

public class Main {
	static int N;
	static int M;
	static int[][] Arr;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int count=0;
	static Deque<Integer> deqX = new ArrayDeque<>();
	static Deque<Integer> deqY = new ArrayDeque<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		Arr= new int[N][M];
		String str;
		for(int i = 0 ; i < N ; i++) {
			str = br.readLine();
			for(int j = 0 ; j < M ; j++) {
				Arr[i][j] = str.charAt(j)-'0';
			}
		}
		System.out.println(dfs(0,0));
	}

	public static int dfs(int x, int y) {
		deqX.add(0);
		deqY.add(0);

		while(!deqX.isEmpty()&&!deqY.isEmpty()) {
			int first = deqX.peek();
			int second = deqY.peek();
			deqX.pop();
			deqY.pop();

			for(int i = 0 ; i < 4 ; i++) {
				int nx = first+dx[i];
				int ny = second+dy[i];

				if(nx<0 || nx>=N || ny<0|| ny>=M) {
					continue;
				}
				else if( Arr[x][y]==0) {
					continue;
				}
				if(Arr[nx][ny]==1) {
					Arr[nx][ny]=Arr[first][second]+1;
					deqX.add(nx);
					deqY.add(ny);
				}
			}
		}
		return Arr[N-1][M-1];
	}
}
```

이 문제가 어려운 이유: DFS,BFS와 Queue, Stack의 활용이 어려웠다. 모든요소를 탐색하여 1값이 없을때까지 반복하는데 현재 값을 꺼내고 상하좌우값을 nx, ny로 지정해두고 상하좌우를 1번씩 돌면서 1(방문 안 한 상태)일 때 자기 자신값+1을 하여 대입해주고 그 위치를 큐에 저장한다. 그럼 while문으로 돌아가 큐가 차있으니 위 과정을 다시 반복한다. 1일 때만 동작하게하고 큐에 저장하기 때문에 중복 될 걱정이 없다.
